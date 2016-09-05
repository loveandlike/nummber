package com.example.administrator.volleyandjson;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.volleyandjson.entity.Image;
import com.example.administrator.volleyandjson.fvotate.Detail;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/31.
 */
public class ImageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView;
    private RequestQueue mRequestQueue;
    ImageAdapter imageAdapter;
    private SwipeRefreshLayout swipe_Refresh;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.imageview_fragment,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_image);
        mRequestQueue = Volley.newRequestQueue(getContext());
        swipe_Refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_image);
//下拉刷新------------------------------------------------------------------
        swipe_Refresh.setColorSchemeResources(R.color.zhi, R.color.se1, R.color.colorAccent);
        swipe_Refresh.setOnRefreshListener(this);
//  上拉加载更多
//        Mugen.with(recyclerView, new MugenCallbacks() {
//            @Override
//            public void onLoadMore() {
//
//            }
//
//
//            @Override
//            public boolean isLoading() {
//                return false;
//            }
//
//            @Override
//            public boolean hasLoadedAllItems() {
//                return false;
//            }
//        }).start();

    }
//    接收到消息停止刷新，更新视图-------------------------------------------》
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            swipe_Refresh.setRefreshing(false);
            imageAdapter.notifyDataSetChanged();
        }
    };
//  获取网络资源，资源适配  <---------------------------------------------------->
    public void getStringResultImage() {
        String httpurl ="http://apis.baidu.com/txapi/mvtp/meinv";
        String httpArg = "num=50";
        httpurl = httpurl + "?" + httpArg;
        StringRequest request = new StringRequest(httpurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Image image = new Gson().fromJson(s,Image.class);
                final List<Image.NewslistBean> newslist = image.getNewslist();
                Log.e("tag",image.toString());
                Log.e("tag",image.getNewslist().toString());
                imageAdapter=new ImageAdapter(getContext(),newslist);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
//                设置瀑布流
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(imageAdapter);
                imageAdapter.notifyDataSetChanged();
//                点击超链接跳转
                imageAdapter.setOnItemClickListener(new ImageAdapter.ItemClick() {
                    @Override
                    public void setOnItemClick(int position) {
                        Intent intent = new Intent(getContext(), Detail.class);
                        intent.putExtra("url", newslist.get(position).getUrl());
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> getheater = new HashMap<>();
                getheater.put("apikey", "12ae30b08326617b5c439939ec20eba6");
                return getheater;
            }
        };
        mRequestQueue.add(request);
    }

    @Override
    public void onRefresh() {
        getStringResultImage();
        //两秒后停止刷新并更新视图
        Snackbar.make(swipe_Refresh, "刷新中", Snackbar.LENGTH_SHORT).show();
        handler.sendEmptyMessageDelayed(0, 2000);
    }

}
