package com.owulia.roombasic;

import androidx.appcompat.app.AppCompatActivity;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordDatabase = Room.databaseBuilder(this, WordDatabase.class, "word_database")
                .allowMainThreadQueries() // 强制允许在主线程执行
                .build();
        wordDao = wordDatabase.getWordDao();

        textView = findViewById(R.id.textView);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello", "你好");
                Word word2 = new Word("World", "世界");
                wordDao.insertWords(word1, word2);
                updateView();
            }
        });

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDelete = findViewById(R.id.buttonDelete);

        updateView();

    }

    void updateView () {
        List<Word> list = wordDao.getAllWords();
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            Word word = list.get(i);
            text += word.getId() + ":" + word.getWord() + "=" + word.getChineseMeaning();
        }
        textView.setText(text);
    }
}
