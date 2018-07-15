package br.com.paulosalvatore.movilenext_aula1_room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {

	public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

	private EditText etWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_word);

		etWord = findViewById(R.id.etWord);

		final Button button = findViewById(R.id.btSave);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View pView) {
				Intent replyIntent = new Intent();

				if (TextUtils.isEmpty(etWord.getText())) {
					setResult(RESULT_CANCELED, replyIntent);
				} else {
					String word = etWord.getText().toString();
					replyIntent.putExtra(EXTRA_REPLY, word);
					setResult(RESULT_OK, replyIntent);
				}

				finish();
			}
		});
	}
}
