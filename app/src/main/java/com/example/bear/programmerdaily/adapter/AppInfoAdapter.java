package com.example.bear.programmerdaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bear.programmerdaily.R;
import com.example.bear.programmerdaily.data.bean.AppInfo;
import com.example.bear.programmerdaily.data.db.AppInfoEntity;

import java.util.List;

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.AppInfoViewHolder> {

    class AppInfoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView appIconIV;
        private final TextView appNameTV;
        private final TextView appKeywordTV;
        private final TextView visitCountTV;
        private final TextView delevoperTV;
        private final TextView starCountTV;
        private final TextView discussCountTV;

        private AppInfoViewHolder(View itemView) {
            super(itemView);
            appIconIV = itemView.findViewById(R.id.app_icon);
            appNameTV = itemView.findViewById(R.id.app_name);
            appKeywordTV = itemView.findViewById(R.id.key_word);
            visitCountTV = itemView.findViewById(R.id.visit_count);
            delevoperTV = itemView.findViewById(R.id.developer);
            starCountTV = itemView.findViewById(R.id.star_count);
            discussCountTV = itemView.findViewById(R.id.discuss_count);
        }
    }

    private final LayoutInflater mInflater;
    private List<AppInfoEntity> mAppInfos; // Cached copy of mAppInfos

    public AppInfoAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public AppInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.app_info_item, parent, false);
        return new AppInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AppInfoViewHolder holder, int position) {
        if (mAppInfos != null) {
            AppInfoEntity current = mAppInfos.get(position);
            holder.appNameTV.setText(current.getAppName());
            holder.appIconIV.setImageResource(R.mipmap.cherry);
            holder.appKeywordTV.setText(current.getKeyWord());
            holder.delevoperTV.setText(current.getAppDeveloper());
            int appStar = current.getAppStar() < 0?0:current.getAppStar();
            holder.starCountTV.setText(appStar + "");
            int appDiscussCount = current.getAppDiscussCount() < 0?0:current.getAppDiscussCount();
            holder.discussCountTV.setText(appDiscussCount + "");
            int appVisitCount = current.getAppVisitCount() < 0?0:current.getAppVisitCount();
            holder.visitCountTV.setText(appVisitCount+ "");
        } else {
            // Covers the case of data not being ready yet.
            holder.appNameTV.setText("无项目");
            holder.appIconIV.setImageResource(R.mipmap.boluo);
            holder.appKeywordTV.setText("无");
            holder.delevoperTV.setText("匿名");
            holder.starCountTV.setText(0);
            holder.discussCountTV.setText(0);
            holder.visitCountTV.setText(0);
        }
    }

    public void setAppInfoEntitys(List<AppInfoEntity> appInfoEntities){
        mAppInfos = appInfoEntities;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAppInfos != null)
            return mAppInfos.size();
        else return 0;
    }
}
