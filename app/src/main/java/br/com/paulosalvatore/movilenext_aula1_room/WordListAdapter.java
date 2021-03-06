package br.com.paulosalvatore.movilenext_aula1_room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

	class WordViewHolder extends RecyclerView.ViewHolder {
		private final TextView wordItemView;

		WordViewHolder(@NonNull View itemView) {
			super(itemView);
			wordItemView = itemView.findViewById(R.id.textView);
		}
	}

	private final LayoutInflater mInflater;

	private List<Word> mWords;

	public WordListAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	void setWords(List<Word> words) {
		mWords = words;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
		return new WordViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
		Word current = mWords.get(position);
		holder.wordItemView.setText(current.getWord());
	}

	@Override
	public int getItemCount() {
		return mWords != null ? mWords.size() : 0;
	}
}
