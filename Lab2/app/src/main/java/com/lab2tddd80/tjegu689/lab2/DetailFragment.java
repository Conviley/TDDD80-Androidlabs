package com.lab2tddd80.tjegu689.lab2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by tjegu689 on 29/02/16.
 */
public class DetailFragment extends Fragment{
    View detail_view;

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstance){
        detail_view = inflater.inflate(R.layout.details,container,false);

        Button backButton = (Button) detail_view.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        if (savedInstance != null) {
            mCurrentPosition = savedInstance.getInt(ARG_POSITION);
        }

        return detail_view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Bundle args = getArguments();
        if (args!= null){
            setContent(args.getInt(ARG_POSITION));
        }else if (mCurrentPosition != -1){
            setContent(mCurrentPosition);
        }
    }

    public void setContent(int positon){
        TextView description = (TextView) getActivity().findViewById(R.id.description);
        if (description == null){
            System.out.println("description is null");
        }
        switch (positon){
            case 0:
                description.setText("Sveriges TEXTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                break;
            case 1:
                description.setText("Finlands TEXT");
                break;
            case 2:
                description.setText("Tidnings TEXT");
                break;
            case 3:
                description.setText("TVs TEXT");
                break;
        }
        mCurrentPosition = positon;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }


}
