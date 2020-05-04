package com.owulia.words;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Word> allWords = new ArrayList<>();

    private WordViewModel wordViewModel;

    boolean useCardViews;

    public MyAdapter(boolean useCardViews, WordViewModel wordViewModel) {
        this.useCardViews = useCardViews;
        this.wordViewModel = wordViewModel;
    }

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (useCardViews) {
            itemView = layoutInflater.inflate(R.layout.cell_card_2, parent, false);
        } else  {
            itemView = layoutInflater.inflate(R.layout.cell_normal_2, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Word word = allWords.get(position);
        holder.textViewNumber.setText(String.valueOf(position + 1));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinse.setText(word.getChineseMeaning());
        // 解决触发setOnCheckedChangeListener
        holder.switchChineseInvisible.setOnCheckedChangeListener(null);
        if (word.isChineseInvisible()) {
            holder.textViewChinse.setVisibility(View.GONE);
            holder.switchChineseInvisible.setChecked(true);
        } else {
            holder.textViewChinse.setVisibility(View.VISIBLE);
            holder.switchChineseInvisible.setChecked(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://m.youdao.com/dict?le=en&q=" + holder.textViewEnglish.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.switchChineseInvisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    holder.textViewChinse.setVisibility(View.GONE);
                    word.setChineseInvisible(true);
                    wordViewModel.updateWords(word);
                } else {
                    holder.textViewChinse.setVisibility(View.VISIBLE);
                    word.setChineseInvisible(false);
                    wordViewModel.updateWords(word);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber, textViewEnglish, textViewChinse;
        Switch switchChineseInvisible;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
            textViewChinse = itemView.findViewById(R.id.textViewChinse);
            switchChineseInvisible = itemView.findViewById(R.id.switchChineseInvisible);
        }
    }


}
