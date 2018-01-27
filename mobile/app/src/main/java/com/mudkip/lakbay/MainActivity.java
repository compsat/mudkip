package com.mudkip.lakbay;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction mFragmentTransaction;
    private MainFragment mMainFragment;
    private ProfileFragment mProfileFragment;
    private SettingsFragment mSettingsFragment;
    private QuestFragment mQuestFragment;
    private StopsFragment mStopsFragment;
    private HelpFragment mHelpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, Main2Activity.class);
//        startActivity(intent);

        mMainFragment = new MainFragment();
        mProfileFragment = new ProfileFragment();
        mSettingsFragment = new SettingsFragment();
        mQuestFragment = new QuestFragment();
        mStopsFragment = new StopsFragment();
        mHelpFragment = new HelpFragment();

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mMainFragment);
        mFragmentTransaction.commit();

        LocationHandler.initialize(this);
    }

    public void changeToProfile(){
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mProfileFragment);
        mFragmentTransaction.commit();
    }

    public void changeToMain(){
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mMainFragment);
        mFragmentTransaction.commit();
    }

    public void changeToSettings(){
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mSettingsFragment);
        mFragmentTransaction.commit();
    }

    public void changeToQuest(){
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mQuestFragment);
        mFragmentTransaction.commit();
    }

    public void changeToStops(){
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mStopsFragment);
        mFragmentTransaction.commit();
    }

    public void changeToHelp(){
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mHelpFragment);
        mFragmentTransaction.commit();
    }
}