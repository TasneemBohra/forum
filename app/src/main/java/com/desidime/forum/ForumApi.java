package com.desidime.forum;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tasneem on 16/7/15.
 */
public class ForumApi {
    public interface ForumsListener {
        void onResponse(JSONArray jsonArray);
        void onError(VolleyError error);
    }

    private static final String FETCH_FORUMS_URL = "http://api.desidime.com/v1/forums.json";
    private static final String ACCESS_TOKEN = "68045fd226ab32029c98bf4533bfa98b3c50423094d292d70ca2702e61a9679b";
    private static final String CONTENT_TYPE = "application/json";

    private ForumsListener mForumListener;
    private Context mContext;

    public ForumApi(Context context, ForumsListener forumsListener) {
        mForumListener = forumsListener;
        mContext = context;
    }

    public void fetchForums() {
        Log.d("URL", FETCH_FORUMS_URL);
        RequestQueue requestQueue = VolleyAppController.getInstance(mContext).getRequestQueue();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, FETCH_FORUMS_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mForumListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mForumListener.onError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return setHeaders();
            }
        };
        requestQueue.add(arrayRequest);
    }

    /**
     * Sets header of any request
     * @return
     */
    private Map<String, String> setHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", CONTENT_TYPE);
        headers.put("X-Desidime-Client", ACCESS_TOKEN);
        return headers;
    }
}
