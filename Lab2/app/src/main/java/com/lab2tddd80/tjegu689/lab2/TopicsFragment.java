package com.lab2tddd80.tjegu689.lab2;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by tjegu689 on 04/03/16.
 */
public class TopicsFragment extends ListFragment {
    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the fragment layout file
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.topic_view,container,false);
        System.out.println("hello: " + view);
        // Create data source
        String[] topics = {"Sverige", "Finland", "Tidning", "TV"};
        // Create adapter
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,
                android.R.id.text1, topics);
        // Bind adapter to the listFragent
        this.setListAdapter(adapter);
        // Retian listfragment instance across configuration changes
        setRetainInstance(true);
        return view;
    }
    // Handling item click
    public  void onListItemClick(ListView listView, View view, int position, long id){
        MainActivit
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content,new DetailFragment(),"detailfragment");
        transaction.commit();
    }

}
