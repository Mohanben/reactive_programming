package com.mohan.myapplication;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

class ObservableDemo {
    private List<String> mNameList;
    private Observable<String> mNameObservable;

    @SuppressLint("CheckResult")
    ObservableDemo(List<String> nameList) {
        mNameList = nameList;
        //Using create
        mNameObservable = Observable.create(this::onNameChanged);
        mNameObservable.subscribe(name -> Log.e("Mohan", "Name : " + name));
        mNameObservable.subscribe(newName -> Log.e("Mohan", "New Name List : " + newName));
        mNameObservable.scan(new StringBuilder(),
            StringBuilder::append).subscribe(
            stringBuilder -> Log.e("Mohan", "Append Name : " + stringBuilder.toString()));
        //map using rxJava
        Observable<String> map = mNameObservable.map(this::mapName);
        Log.e("Mohan", "map :" + map.subscribe(s -> Log.e("Mohan", "My Name " + s)));
        //Using from
    }

    private String mapName(String name) {
        if (name.equals("Mohan")) {
            return name;
        } else {
            return null;
        }
    }

    private void onNameChanged(ObservableEmitter nameEmitter) {
        for (String name : mNameList) {
            nameEmitter.onNext(name);
        }
        nameEmitter.onComplete();
    }
}
