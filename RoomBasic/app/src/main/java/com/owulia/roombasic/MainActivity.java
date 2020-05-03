package com.owulia.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordDatabase wordDatabase;
    WordDao wordDao;

    TextView textView;
    Button buttonInsert, buttonUpdate, buttonClear, buttonDelete;

    LiveData<List<Word>> allWordsLive;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordDatabase = Room.databaseBuilder(this, WordDatabase.class, "word_database")
                .allowMainThreadQueries() // 强制允许在主线程执行
                .build();
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
        allWordsLive.observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < words.size(); i++) {
                    Word word = words.get(i);
                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });

        textView = findViewById(R.id.textView);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello", "你好");
                Word word2 = new Word("World", "世界");
                wordDao.insertWords(word1, word2);
            }
        });

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi", "你好呀");
                word.setId(20);
                wordDao.updateWords(word);
            }
        });

        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteAllWords();
            }
        });

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi", "你好呀");
                word.setId(17);
                wordDao.deleteWords(word);
                updateView();
            }
        });

    }

    void updateView () {
        List<Word> list = wordDao.getAllWords();
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            Word word = list.get(i);
            text += word.getId() + ":" + word.getWord() + "=" + word.getChineseMeaning() + "\n";
        }
        textView.setText(text);
    }
}
