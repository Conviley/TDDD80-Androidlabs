package com.lab2tddd80.tjegu689.lab3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by tjegu689 on 04/03/16.
 */
public class TopicsFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;
    final static int ARG_POSITION = 0;
    public ArrayList<String> items = new ArrayList<>();
    public ArrayAdapter adapter;
    public interface OnHeadlineSelectedListener{
        void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        getTopics();
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

        // Create adapter
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,
                android.R.id.text1, items);
        // Bind adapter to the listFragent
        this.setListAdapter(adapter);
        // Retian listfragment instance across configuration changes
       // setRetainInstance(true);

        System.out.println(items + "innan view");
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getListView().getLayoutParams().width = getWidestView(getActivity(),getListAdapter());
    }
    // Handling item click
    public void onListItemClick(ListView listView, View view, int position, long id){
        System.out.println("position: " + position);
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
    
    private void getTopics(){
        String url = "http://tddd80-afteach.rhcloud.com/api/groups";
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            JSONArray jsonArray = response.getJSONArray("grupper");
                            for (int i = 0; i <jsonArray.length(); i++) {
                                String item = jsonArray.getString(i);
                                items.add(item);
                                getListView().setAdapter(adapter);
                                getListView().getLayoutParams().width = getWidestView(getActivity(),getListAdapter());
                                System.out.println(items);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

}
