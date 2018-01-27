package com.mudkip.lakbay;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction mFragmentTransaction;
    private MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

//        mMainFragment = new MainFragment();
//
//        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
//        mFragmentTransaction.replace(R.id.content, mMainFragment);
//        mFragmentTransaction.commit();
    }
}