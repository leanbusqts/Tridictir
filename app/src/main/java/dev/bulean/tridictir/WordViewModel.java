package dev.bulean.tridictir;

/*
* The ViewModel is a class whose role is to provide data to the UI and survive configuration changes.
* A ViewModel acts as a communication center between the Repository and the UI.
* The ViewModel is part of the lifecycle library.
* */

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application){
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        mRepository.insert(word);
    }
}
