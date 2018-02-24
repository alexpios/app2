package com.example.alexandrkuchinsky.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.alexandrkuchinsky.myapplication.Interface.ItemClickListener;
import com.example.alexandrkuchinsky.myapplication.Model.RSSObject;
import com.example.alexandrkuchinsky.myapplication.R;
import com.example.alexandrkuchinsky.myapplication.ww;

import static android.support.v4.content.ContextCompat.startActivities;


/**
 * Created by Alexandr Kuchinsky on 08.02.2018.
 */

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
public TextView txtTitle, txtPubDate, txtContent;
private ItemClickListener itemClickListener;

    public FeedViewHolder(View itemView) {
        super(itemView);
        txtTitle = (TextView)itemView.findViewById(R.id.texttitle);
        txtPubDate = (TextView)itemView.findViewById(R.id.txtPubDate);
        txtContent = (TextView)itemView.findViewById(R.id.txtContent);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
         RSSObject rssObject;
         Context mContext;


itemClickListener.onClick(view, getAdapterPosition(),false);


              }




    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),true);
        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    private RSSObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public FeedAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
View itemView = inflater.inflate(R.layout.rforlab, parent, false);
return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
holder.txtTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtPubDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.txtContent.setText(rssObject.getItems().get(position).getContent());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!isLongClick){
                    //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    //mContext.startActivity(browserIntent);
                  // mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink())).setClass(mContext, ww.class));
               //     mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink())), mContext ,ww.class);

//                 Intent intent = new Intent(mContext,ww.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                 intent.putExtra("link",Uri.parse(rssObject.getItems().get(position).getLink()));
//                 mContext.startActivity(intent);

//                    Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
//                    sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    sharingIntent.setData(Uri.parse(rssObject.getItems().get(position).getLink()));
//                    Intent chooserIntent = Intent.createChooser(sharingIntent, "Open With");
//                    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mContext.startActivity(chooserIntent);

                    Intent intent = new Intent(mContext, ww.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(rssObject.getItems().get(position).getLink()));
                    mContext.startActivity(intent);
                }
            }
        });
    }

 
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }



    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}
