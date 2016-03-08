package com.lab2tddd80.tjegu689.lab2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.List;


/**
 * Created by tjegu689 on 04/03/16.
 */
public class TopicsFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;
    final static int ARG_POSITION = 0;
    public interface OnHeadlineSelectedListener{
        void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // Inflate the fragment layout file
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.topic_view,container,false);
        // Create data source
        String[] topics = {"Sverige", "Finland", "Tidning", "TV"};
        // Create adapter
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,
                android.R.id.text1, topics);
        // Bind adapter to the listFragent
        this.setListAdapter(adapter);
        // Retian listfragment instance across configuration changes
       // setRetainInstance(true);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getListView().getLayoutParams().width = getWidestView(getActivity(),getListAdapter());
    }
    // Handling item click
    public void onListItemClick(ListView listView, View view, int position, long id){
        System.out.println("position: "+position );
        mCallback.onArticleSelected(position);
    }


    public static int getWidestView(Context context, Adapter adapter) {
        int maxWidth = 0;
        View view = null;
        FrameLayout fakeParent = new FrameLayout(context);
        for (int i=0, count=adapter.getCount(); i<count; i++) {
            view = adapter.getView(i, view, fakeParent);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int width = view.getMeasuredWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

}
