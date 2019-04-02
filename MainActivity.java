package com.mohan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameList = new ArrayList<>();
        mNameList.add("Mohan");
        mNameList.add("Chandrasekar");
        mNameList.add("Ben");
        mNameList.add("Swedish");
        mNameList.add("Adhish Chandra");

        //new RxData();

        //new FlowableDemo();
        new ObservableDemo(mNameList);

        new FlowableExample(mNameList);


    }
}

