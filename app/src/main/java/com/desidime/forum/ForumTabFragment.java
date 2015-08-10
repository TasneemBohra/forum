package com.desidime.forum;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasneem on 16/7/15.
 */
public class ForumTabFragment extends Fragment implements ForumApi.ForumsListener {

    private ForumApi mForumApi;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<Forum> mForumList;

    public ForumTabFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

         /* Initializations of variables */
        mContext = rootView.getContext();
        mForumApi = new ForumApi(mContext, this);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mForumApi.fetchForums();

        return rootView;
    }

    @Override
    public void onResponse(JSONArray jsonArray) {
        Log.d("Response", jsonArray.toString());
        mForumList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                mForumList.add(new Forum(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mRecyclerView.setAdapter(new ForumListAdapter(getActivity(), mForumList));
    }

    @Override
    public void onError(VolleyError error) {
        Log.e("Error", error.toString());
    }
}
