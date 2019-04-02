package com.mohan.myapplication;

import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxData {
    private List<Country> mCountryDataList = new ArrayList<>();
    private Observer<String> mEmitter = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.e("Mohan", "onSubscribe");
        }

        @Override
        public void onNext(String s) {
            Log.e("Mohan", " " + s);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Mohan", "onError");
        }

        @Override
        public void onComplete() {
            Log.e("Mohan", "onComplete");
        }
    };
    private List<String> mCountryList = new ArrayList<>();

    private Observable<String> mObservable = Observable.fromArray("Mohan", "Chandrasekar",
            "Welcome to RxJava");

    RxData() {
        mCountryList.add("Sweden");
        mCountryList.add("USA");
        mCountryList.add("Canada");
        mCountryList.add("Dubai");
        mCountryList.add("Germany");
        mCountryList.add("California");
        mCountryList.add("Dargling");
        mCountryList.add("Harlanda");
        mCountryDataList.add(new Country("Sweden", "212100", "100"));
        mCountryDataList.add(new Country("India", "213220", "101"));
        mCountryDataList.add(new Country("America", "232300", "102"));
        mCountryDataList.add(new Country("AUS", "213230", "103"));

        mObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(
                Schedulers.io()).subscribe(mEmitter);

        Observable<String> countNameObservable = Observable.create(this::onCountryNameChanged);
        countNameObservable.subscribe(createNameObservable());
        Flowable<Country> countryFlowable = Flowable.create(this::onCountryDataChanged,
                BackpressureStrategy.LATEST);
        countryFlowable.subscribe(getCountryFlowable());
        countNameObservable.observeOn(Schedulers.io()).subscribeOn(
                AndroidSchedulers.mainThread()).subscribe(getOptionalCountry());

        Single<String> nameSingle = Single.create(this::onNameChanged);
        nameSingle.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                Log.e("Mohan", "String Name : " + s);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public Observable<String> getObservable() {
        return mObservable;
    }

    private Observer<? super String> getOptionalCountry() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e("Mohan", "Country Optional: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void onNameChanged(SingleEmitter<String> tSingleEmitter) {
        tSingleEmitter.onSuccess("Mohan capture the value");
    }

    private FlowableSubscriber<? super Country> getCountryFlowable() {
        return new FlowableSubscriber<Country>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Country country) {
                Log.e("Mohan", "Country : " + country.getCountryName());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void onCountryDataChanged(FlowableEmitter<Country> tFlowableEmitter) {
        for (Country country : mCountryDataList) {
            tFlowableEmitter.onNext(country);
        }
        tFlowableEmitter.onComplete();
    }

    private Observer<? super String> createNameObservable() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String countryName) {
                Log.e("Mohan", "Country Name : " + countryName);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void onCountryNameChanged(ObservableEmitter<String> emitter) {
        try {
            for (String countryname : mCountryList) {
                emitter.onNext(countryname);
            }
            emitter.onComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
