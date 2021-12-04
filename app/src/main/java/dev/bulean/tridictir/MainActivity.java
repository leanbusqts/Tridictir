package dev.bulean.tridictir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import dev.bulean.tridictir.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private WordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(WordViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.toolbar);

        RecyclerView recycler = binding.recycler;
        final WordsAdapter adapter = new WordsAdapter(this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.setWords(words);
        });

        viewModel.contentET.observe(this, s -> {
            binding.outputTV.setText(Utils.replaceString(s));
            if(s.isEmpty()){
                binding.imgClear.setVisibility(View.GONE);
                binding.imgDone.setVisibility(View.GONE);
                binding.imgCopy.setVisibility(View.GONE);
                binding.outputTV.setText(getResources().getText(R.string.inpitHint));
            } else {
                binding.imgClear.setVisibility(View.VISIBLE);
                binding.imgDone.setVisibility(View.VISIBLE);
                binding.imgCopy.setVisibility(View.VISIBLE);
            }
        });

        binding.imgClear.setOnClickListener(view -> binding.inputET.getText().clear());

        binding.imgDone.setOnClickListener(view -> {
            viewModel.saveWord();
            binding.inputET.getText().clear();
            hideKey(view);
        });

        binding.imgCopy.setOnClickListener(v -> {
            Utils.copyToClipboard(this, binding.outputTV.getText().toString());
        });
        binding.inputET.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.saveWord();
                binding.inputET.getText().clear();
                hideKey(textView);
                handled = true;
            }
            return handled;
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    // We are not implementing onMove() in this app.
                    public boolean onMove(@NonNull RecyclerView recycler,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    // When the use swipes a word, delete that word from the database.
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Word word = adapter.getWordAtPosition(position);
                        viewModel.delete(word);
                    }
                });
        // Attach the item touch helper to the recycler view.
        helper.attachToRecyclerView(recycler);

        adapter.setOnItemClickListener((v, position) -> {
            Word word = adapter.getWordAtPosition(position);
            binding.inputET.setText(word.getWord());
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
        if (id == R.id.delete) {
            Toast.makeText(this, R.string.delete_data, Toast.LENGTH_SHORT).show();
            viewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}