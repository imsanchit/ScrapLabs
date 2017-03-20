package com.example.sanchit.scraplabs.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.sanchit.scraplabs.R;
import com.example.sanchit.scraplabs.SharedPrefrences.UserLocalStore;
import com.example.sanchit.scraplabs.Utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment2 extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    UserLocalStore userLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        userLocalStore = new UserLocalStore(getContext());

    //    func();

        if (new Util().check_connection(getActivity())){

//            fetchNews();
        }
        else
        {
  //          func();
        }

        return view;
    }

/*
    void fetchNews()
    {
        String url="https://dailyhunt.0x10.info/api/dailyhunt?type=json&query=list_news";
        Map<String, String> params = new HashMap<String, String>();

        CustomRequest1 jsObjRequest = new CustomRequest1(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                String body = response.toString();
                userLocalStore.setResponse5(body);
                func();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

            func();        //new Util().showerrormessage(getActivity(), "Time Out Error.....Try Later!!!");

                } else if (error instanceof AuthFailureError) {

                    func();
//                    new Util().showerrormessage(getActivity(), "Authentication Error.....Try Later!!!");
                } else if (error instanceof ServerError) {

                    func();
  //                  new Util().showerrormessage(getActivity(), "Server Error.....Try Later!!!");
                } else if (error instanceof NetworkError) {

                    func();
    //                new Util().showerrormessage(getActivity(), "Network Error.....Try Later!!!");
                } else if (error instanceof ParseError) {

                    func();
      //              Log.d("Response: ", error.toString());
        //            System.out.println("Response error: " + error.toString());
          //          new Util().showerrormessage(getActivity(), "Unknown Error.....Try Later!!!");
                }
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(jsObjRequest);
    }



    void func(){
        //String body1 = userLocalStore.getResponse5();

        final ArrayList<String> items1 = new ArrayList<String>();
        final ArrayList<String> items2 = new ArrayList<String>();
        final ArrayList<String> items3 = new ArrayList<String>();
        final ArrayList<String> items4 = new ArrayList<String>();

        try {
            JSONObject data_list = new JSONObject(body1);


            JSONArray obj = (JSONArray) data_list.get("articles");
            try {
                for (int j = 0; j < obj.length(); j++) {
                    JSONObject json = obj.getJSONObject(j);
                    String titles = json.getString("title");
                    items1.add(titles);
                    String images = json.getString("image");
                    items2.add(images);
                    String contents = json.getString("content");
                    items3.add(contents);
                    String urls = json.getString("url");
                    items4.add(urls);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
        }
        String[] ar1 = items1.toArray(new String[items1.size()]);
        String[] ar2 = items2.toArray(new String[items2.size()]);
        String[] ar3 = items3.toArray(new String[items3.size()]);
        String[] ar4 = items4.toArray(new String[items4.size()]);

      //  NewsCardAdapter a = new NewsCardAdapter(getActivity().getApplicationContext(), ar1, ar2, ar3, ar4, ar1.length);
        recyclerView.setAdapter(a);
    }
*/}