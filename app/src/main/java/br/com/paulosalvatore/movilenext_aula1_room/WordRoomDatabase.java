package br.com.paulosalvatore.movilenext_aula1_room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

	private static WordRoomDatabase instance;

	public abstract WordDao wordDao();

	static WordRoomDatabase getDatabase(final Context context) {
		if (instance == null) {
			synchronized (WordRoomDatabase.class) {
				if (instance == null) {
					// Criar o banco de dados
					instance = Room.databaseBuilder(
							context.getApplicationContext(),
							WordRoomDatabase.class,
							"word_database")
							.addCallback(sCallback)
							.build();
				}
			}
		}

		return instance;
	}

	private static RoomDatabase.Callback sCallback =
			new RoomDatabase.Callback() {
				@Override
				public void onOpen(@NonNull SupportSQLiteDatabase db) {
					super.onOpen(db);
					new PopulateDbAsync(instance).execute();
				}
			};

	private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

		private final WordDao dao;

		PopulateDbAsync(WordRoomDatabase db) {
			dao = db.wordDao();
		}

		@Override
		protected Void doInBackground(final Void... params) {
			dao.deleteAll();

			Word word = new Word("Movile");
			dao.insert(word);

			word = new Word("Next");
			dao.insert(word);

			return null;
		}
	}
}
