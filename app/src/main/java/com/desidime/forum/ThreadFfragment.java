package com.desidime.forum;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tasneem on 17/7/15.
 */
import java.util.ArrayList;
import java.util.List;

public class ThreadFfragment extends Fragment {

    private ForumApi mForumApi;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<Forum> mForumList = new ArrayList<>();

    public ThreadFfragment() {
        //Require
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        return rootView;
    }
}
