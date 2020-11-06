package dev.bulean.tridictir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords;

    WordsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Word current = mWords.get(position);
        holder.wordView.setText(current.getWord());
    }
    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mWords != null){
            return mWords.size();
        }
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordView;

        private ViewHolder(View view){
            super(view);
            wordView = view.findViewById(R.id.wordView);
        }
    }
}
