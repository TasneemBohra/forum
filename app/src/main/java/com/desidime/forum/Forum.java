package com.desidime.forum;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tasneem on 16/7/15.
 */
public class Forum {
    private String title;
    private String description;
    private Long totalTopics;
    private Long totalPosts;
    private String time;

    public Forum(JSONObject jsonObject) {
        try {
            if (jsonObject.has("title")) {
                title = jsonObject.getString("title");
            }
            if (jsonObject.has("description")) {
                description = jsonObject.getString("description");
            }
            if(jsonObject.has("topics_count")) {
                totalTopics = jsonObject.getLong("topics_count");
            }
            if (jsonObject.has("posts_count")) {
                totalPosts = jsonObject.getLong("posts_count");
            }
            if (jsonObject.has("last_activity_at")) {
                time = jsonObject.getString("last_activity_at");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Long getTotalTopics() {
        return totalTopics;
    }

    public Long getTotalPosts() {
        return totalPosts;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
