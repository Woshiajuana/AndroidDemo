package com.owulia.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonInsert, buttonUpdate, buttonClear, buttonDelete;

    WordViewModel wordViewModel;

    RecyclerView recyclerView;
    MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

//        wordViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(WordViewModel.class);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter.setAllWords(words);
                myAdapter.notifyDataSetChanged();
            }
        });

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello", "你好");
                Word word2 = new Word("World", "世界");
                wordViewModel.insertWords(word1, word2);
            }
        });

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi", "你好呀");
                word.setId(20);
                wordViewModel.updateWords(word);
            }
        });

        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                wordDao.deleteAllWords();
                wordViewModel.deleteAllWords();
            }
        });

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi", "你好呀");
                word.setId(17);
//                wordDao.deleteWords(word);
                wordViewModel.deleteWords(word);
            }
        });
    }

}
