package com.example.sanchit.scraplabs.Fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.sanchit.scraplabs.R;
import com.example.sanchit.scraplabs.SharedPrefrences.UserLocalStore;
import com.example.sanchit.scraplabs.Utils.Util;

public class Fragment1 extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    UserLocalStore userLocalStore;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        System.out.println("In activity : Fragment 1");

        layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);

        userLocalStore = new UserLocalStore(getContext());
//        func();

        if (new Util().check_connection(getActivity())) {

        }
        return view;
    }
}