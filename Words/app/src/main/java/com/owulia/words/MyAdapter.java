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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends ListAdapter<Word, MyAdapter.MyViewHolder> {

    private WordViewModel wordViewModel;

    boolean useCardViews;

    MyAdapter(boolean useCardViews, WordViewModel wordViewModel) {
        super(new DiffUtil.ItemCallback<Word>() {
            @Override
            public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return oldItem.getId() == newItem.getId();
            }
            @Override
            public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return (oldItem.getWord().equals(newItem.getWord())
                        &&oldItem.getChineseMeaning().equals(newItem.getChineseMeaning())
                        &&oldItem.isChineseInvisible() == newItem.isChineseInvisible()
                );
            }
        });
        this.useCardViews = useCardViews;
        this.wordViewModel = wordViewModel;
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

        final MyViewHolder holder = new MyViewHolder(itemView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri uri = Uri.parse("https://m.youdao.com/dict?le=en&q=" + holder.textViewEnglish.getText());
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(uri);
//                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.switchChineseInvisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Word word = (Word) holder.itemView.getTag(R.id.word_for_view_holder);
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

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Word word = getItem(position);
        holder.itemView.setTag(R.id.word_for_view_holder, word);
        holder.textViewNumber.setText(String.valueOf(position + 1));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinse.setText(word.getChineseMeaning());
        if (word.isChineseInvisible()) {
            holder.textViewChinse.setVisibility(View.GONE);
            holder.switchChineseInvisible.setChecked(true);
        } else {
            holder.textViewChinse.setVisibility(View.VISIBLE);
            holder.switchChineseInvisible.setChecked(false);
        }
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

    // 当 item 出现再屏幕上市


    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.textViewNumber.setText(String.valueOf(holder.getAdapterPosition() + 1));
    }
}
