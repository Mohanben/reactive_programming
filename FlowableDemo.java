package com.mohan.myapplication;

import static io.reactivex.Flowable.create;

import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableSubscriber;

public class FlowableDemo {
    private static String TAG = "FLowableDemo";
    private List<String> mHouseAreaList = new ArrayList<>();
    private Flowable<String> mAreaListFlowable;

    FlowableDemo() {
        mHouseAreaList.add("Goteborg");
        mHouseAreaList.add("Linkobing");
        mHouseAreaList.add("North Goteborg");
        mHouseAreaList.add("South Goteborg");
        mHouseAreaList.add("East Goteborg");
        mHouseAreaList.add("West Goteborg");
        mHouseAreaList.add("Stolkholm");
        mAreaListFlowable = create(this::onListUpdated,
            BackpressureStrategy.LATEST);
        //noinspection UnstableApiUsage
        mAreaListFlowable.subscribe(getAreaName());
    }

    @SuppressWarnings("UnstableApiUsage")
    private FlowableSubscriber<? super String> getAreaName() {
        return new FlowableSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.e(TAG, "onSubscribe is Called");
            }

            @Override
            public void onNext(String name) {
                Log.e(TAG, "Area Name : " + name);
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "Area Name : " + t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete is Called");
            }
        };
    }

    private void onListUpdated(FlowableEmitter<String> emitter) {
        try {
            for (String name : mHouseAreaList) {
                emitter.onNext(name);
            }
            emitter.onComplete();
        } catch (Exception e) {
            emitter.onError(e);
        }
    }
}
