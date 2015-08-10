package com.desidime.forum;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by tasneem on 16/7/15.
 */
public class ForumListAdapter extends RecyclerView.Adapter<ForumListAdapter.ForumViewHolder> {

    private List<Forum> mForumList;
    private FragmentActivity mContext;

    public ForumListAdapter(FragmentActivity context, List<Forum> forumList) {
        mForumList = forumList;
        mContext = context;
    }

    @Override
    public ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forums_list_item, parent, false);
        return new ForumViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ForumViewHolder holder, int position) {
        holder.titleView.setText(decodeHtmlText(mForumList.get(position).getTitle()));
        holder.descriptionView.setText(decodeHtmlText(mForumList.get(position).getDescription()));
        holder.totalPostsView.setText(mForumList.get(position).getTotalPosts().toString());
        holder.totalTopicsView.setText(mForumList.get(position).getTotalTopics().toString());
        holder.lastActivityView.setText("3 hours ago");
    }

    @Override
    public int getItemCount() {
        return mForumList.size();
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleView;
        TextView descriptionView;
        TextView totalTopicsView;
        TextView totalPostsView;
        TextView lastActivityView;

        public ForumViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView)itemView.findViewById(R.id.forum_title);
            descriptionView = (TextView)itemView.findViewById(R.id.forum_desc);
            totalTopicsView = (TextView)itemView.findViewById(R.id.total_topics);
            totalPostsView = (TextView)itemView.findViewById(R.id.total_posts);
            lastActivityView = (TextView)itemView.findViewById(R.id.last_activity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "Clicked position "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
            mContext.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_layout, new ForumDetailFragment(),
                            mForumList.get(getAdapterPosition()).getTime())
                    .addToBackStack(null)
                    .commit();
        }
    }

    /**
     * - Replace \n to <br /> because HTML.fromHtml() method replaces \n to space.
     * - Decoded html text into android compatible text
     * @param text
     * @return String
     */
    public static String decodeHtmlText(String text) {
        String name = "";
        try {
            name = new String(text.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("Forum Adapter", e.getMessage());
        }
        return Html.fromHtml(name.replace("\n", "<br />")).toString().trim();
    }
}
