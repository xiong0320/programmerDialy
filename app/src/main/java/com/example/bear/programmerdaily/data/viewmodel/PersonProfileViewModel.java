package com.example.bear.programmerdaily.data.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.bear.programmerdaily.data.bean.Person;

/**
 * Created by bear on 2018/9/15.
 */

public class PersonProfileViewModel extends ViewModel {
    private MutableLiveData<Person> person;
    public LiveData<Person> getPerson(){
        if (person == null) {
            person = new MutableLiveData<Person>();
            loadPerson();
        }
        return person;
    }
    private void loadPerson(){
        person.setValue(new Person(false,"小熊老师","123456"));
    }
}
