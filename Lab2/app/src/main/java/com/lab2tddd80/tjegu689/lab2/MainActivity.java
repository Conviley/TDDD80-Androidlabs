package com.lab2tddd80.tjegu689.lab2;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

public class MainActivity extends FragmentActivity implements TopicsFragment.OnHeadlineSelectedListener{
    public int article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Tutorial https://www.youtube.com/watch?v=cUq_TxEC3Zo
        // Listfragment with tag
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("listfragment");
        if (listFragment == null){
            listFragment = new TopicsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content,listFragment,"listfragment");
            transaction.commit();
        }

    }

    @Override
    public void onArticleSelected(int position) {

            DetailFragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putInt(DetailFragment.ARG_POSITION, position);
            newFragment.setArguments(args);;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(android.R.id.content,newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
    }
}
