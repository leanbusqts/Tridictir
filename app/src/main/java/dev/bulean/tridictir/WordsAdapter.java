package dev.bulean.tridictir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Word> mWords;
    private static ClickListener clickListener;

    WordsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Word current = mWords.get(position);
        holder.wordView.setText(current.getWord());
        holder.wirdViiw.setText(current.getWird());
    }
    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mWords != null)
            return mWords.size();
        else return 0;
    }

    public Word getWordAtPosition(int position) {
        return mWords.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView wirdViiw;
        private final TextView wordView;

        private ViewHolder(View view){
            super(view);
            wirdViiw = view.findViewById(R.id.wirdViiw);
            wordView = view.findViewById(R.id.wordView);
            itemView.setOnClickListener(view1 -> clickListener.onItemClick(view1, getAdapterPosition()));
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        WordsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}
