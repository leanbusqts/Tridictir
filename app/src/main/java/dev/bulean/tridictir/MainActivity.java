package dev.bulean.tridictir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import dev.bulean.tridictir.databinding.ActivityMainBinding;
/*
* https://developer.android.com/training/keyboard-input/style
* */

public class MainActivity extends AppCompatActivity {

    private WordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(WordViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.toolbar);

        RecyclerView recycler = binding.recycler;
        final WordsAdapter adapter = new WordsAdapter(this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });

        viewModel.contentET.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.outputTV.setText(viewModel.getContent(s));
                if(s.isEmpty()){
                    binding.imgDone.setVisibility(View.INVISIBLE);
                }else
                    binding.imgDone.setVisibility(View.VISIBLE);
            }
        });

        binding.imgDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                viewModel.saveWord();
                binding.inputET.getText().clear();
                hideKey(view);
            }
        });

        binding.inputET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.saveWord();
                    binding.inputET.getText().clear();
                    hideKey(textView);
                    handled = true;
                }
                return handled;
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    // We are not implementing onMove() in this app.
                    public boolean onMove(RecyclerView recycler,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    // When the use swipes a word,
                    // delete that word from the database.
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Word word = adapter.getWordAtPosition(position);
                        viewModel.delete(word);
                    }
                });
        // Attach the item touch helper to the recycler view.
        helper.attachToRecyclerView(recycler);

        adapter.setOnItemClickListener(new WordsAdapter.ClickListener()  {
            @Override
            public void onItemClick(View v, int position) {
                Word word = adapter.getWordAtPosition(position);
                binding.inputET.setText(word.getWord());
            }
        });

    }
    private void hideKey(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear) {
            Toast.makeText(this, R.string.clear_data, Toast.LENGTH_LONG).show();
            viewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}