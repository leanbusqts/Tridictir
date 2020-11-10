package dev.bulean.tridictir;

/*
* The ViewModel is a class whose role is to provide data to the UI and survive configuration changes.
* A ViewModel acts as a communication center between the Repository and the UI.
* The ViewModel is part of the lifecycle library.
* */

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;
    public MutableLiveData<String> contentET = new MutableLiveData<>();

   public WordViewModel(Application application){
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    String getContent(String s){
        return s.replace('a','i').replace('e','i')
                .replace('o','i').replace('u','i');
    }

    String toStr(MutableLiveData<String> contentET){
        return contentET.getValue();
    }

    public void saveWord(){
        String input = toStr(contentET);
        String inpit = getContent(input);
        Word word = new Word(input, inpit);
        insert(word);
    }

    public void insert(Word word){
        mRepository.insert(word);
    }
    public void deleteAll(){
       mRepository.deleteAll();
    }
    public void delete(Word word){
       mRepository.delete(word);
    }
}