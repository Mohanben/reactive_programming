package com.mohan.myapplication;

import android.annotation.SuppressLint;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

class FlowableExample {
    private List<String> mNameList;
    private Observable<String> mNameObservable;
    private Flowable<String> mNameFlowable;

    @SuppressLint("CheckResult")
    FlowableExample(List<String> nameList) {
        mNameList = nameList;
        mNameObservable = Observable.just("Mohan");
        mNameObservable.toFlowable(BackpressureStrategy.LATEST);

    }
}
