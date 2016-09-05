package com.example.administrator.volleyandjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.volleyandjson.entity.Try_joke;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ItemView> {
    private Context context;

    List<Try_joke.ResBodyBean.JokeListBean> jokeList;

    public Adapter(Context context,List<Try_joke.ResBodyBean.JokeListBean> jokeList){
        this.context=context;
        this.jokeList=jokeList;
    }
    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_news, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        holder.tv_title.setText(jokeList.get(position).getJokeTitle());
        holder.tv_text.setText(jokeList.get(position).getJokeContent());
    }
    @Override
    public int getItemCount() {
        return jokeList.size();
    }
    public class ItemView extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_text;

        public ItemView(View itemView) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.textView2);
            tv_title = (TextView) itemView.findViewById(R.id.textView1);

        }
    }
}
