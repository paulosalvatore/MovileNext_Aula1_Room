package br.com.paulosalvatore.movilenext_aula1_room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
	private WordDao mWordDao;

	private LiveData<List<Word>> mAllWords;

	WordRepository(Application application) {
		WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
		mWordDao = db.wordDao();
		mAllWords = mWordDao.getAllWords();
	}

	public LiveData<List<Word>> getAllWords() {
		return mAllWords;
	}

	public void insert(Word word) {
		new insertAsyncTask(mWordDao).execute(word);
	}

	private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

		private WordDao mAsyncTaskDao;

		insertAsyncTask(WordDao dao) {
			mAsyncTaskDao = dao;
		}

		@Override
		protected Void doInBackground(Word... pWords) {
			mAsyncTaskDao.insert(pWords[0]);
			return null;
		}
	}
}
