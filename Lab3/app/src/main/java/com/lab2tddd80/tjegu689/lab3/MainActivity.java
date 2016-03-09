package com.lab2tddd80.tjegu689.lab3;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends FragmentActivity implements TopicsFragment.OnHeadlineSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Tutorial https://www.youtube.com/watch?v=cUq_TxEC3Zo
        View plac = findViewById(R.id.placeholder);
        if (findViewById(R.id.placeholder) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
             TopicsFragment firstFragment = new TopicsFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.placeholder, firstFragment).commit();
        }

    }

    @Override
    public void onArticleSelected(int position) {
        DetailFragment detailFragment = (DetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.details);
        System.out.println(getSupportFragmentManager().findFragmentById(R.id.details));

        if (findViewById(R.id.placeholder) == null){
            System.out.println("detailFragment is null");
            // if the placeholder is null then we are in two pane layout
            detailFragment.setContent(position);
        }else {
            DetailFragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            //sets position to keyvalue ARG_position
            args.putInt(DetailFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.placeholder,newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
