package com.example.administrator.volleyandjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.volleyandjson.entity.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemView> {
    private Context context;
    List<Image.NewslistBean> newslist;
    public ImageAdapter(Context context, List<Image.NewslistBean> newslist) {
        this.context = context;
        this.newslist = newslist;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ItemView(view);
    }
    @Override
    public void onBindViewHolder(ItemView holder, final int position) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              listener.setOnItemClick(position);
          }
      });
//        holder.tv_time.setText(newslist.get(position).getCtime());
        holder.tv_title.setText(newslist.get(position).getTitle());
        Picasso.with(context).load(newslist.get(position).getPicUrl()).into(holder.imageView);
    }
    public interface ItemClick {
        void setOnItemClick(int position);
    }

    private ItemClick listener;


    public void setOnItemClickListener(ItemClick listener) {
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return newslist.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        TextView tv_title,tv_time,tv_descption;
        ImageView imageView;

        public ItemView(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            tv_title = (TextView) itemView.findViewById(R.id.item_text);
//            tv_time = (TextView) itemView.findViewById(R.id.item_time);

        }
    }
}
