package com.lab2tddd80.tjegu689.lab3;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by tjegu689 on 29/02/16.
 */
public class DetailFragment extends Fragment{
    View detail_view;
    TextView description;
    final static String ARG_POSITION = "position";
    //Topic mCurrentPosition = -1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstance){
        detail_view = inflater.inflate(R.layout.details,container,false);
        description = (TextView) detail_view.findViewById(R.id.description);
        Button backButton = (Button) detail_view.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

//        if (savedInstance != null) {
//            mCurrentPosition = savedInstance.getInt(ARG_POSITION);
//        }

        return detail_view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Bundle args = getArguments();
        if (args!= null){
            Topic topic = (Topic) args.getSerializable(ARG_POSITION);
            setContent(topic);}
//        }else if (mCurrentPosition != -1){
//            setContent(mCurrentPosition);
//        }
    }

    public void setContent(Topic topic){

        getContent("http://tddd80-afteach.rhcloud.com/api/groups/"+topic.getTopic(), topic);
        if (description == null) {
            System.out.println("description is null");
        }
        //description.setText(topic.getEmail()+topic.getPerson());
       //mCurrentPosition = topic;
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        // Save the current article selection in case we need to recreate the fragment
//        outState.putInt(ARG_POSITION, mCurrentPosition);
//    }

    private Topic getContent(String url, final Topic topic){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        final StringBuilder members = new StringBuilder();
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            JSONArray jsonArray = response.getJSONArray("medlemmar");
                            for (int i = 0; i <jsonArray.length(); i++) {
                                String item = jsonArray.getString(i);
                                JSONObject details = jsonArray.getJSONObject(i);
                                String email = details.getString("epost");
                                String name = details.getString("namn");
                                //String reply = details.getString("svarade");
                                members.append(email + " " + name + "\n");
                                topic.setEmail(email);
                                topic.setPerson(name);
                                //topic.setReply(reply);
                                System.out.println(details);
                            }
                            description.setText(members.toString());
                            progressDialog.dismiss();
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
        return topic;
    }
}
