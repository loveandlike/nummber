package com.example.administrator.volleyandjson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.volleyandjson.entity.Try_joke;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/31.
 */
public class RecyclerViewFragment extends Fragment {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private RequestQueue mRequestQueue;
    private SwipeRefreshLayout swipe_Refresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_fragment);
        mRequestQueue = Volley.newRequestQueue(getContext());
        swipe_Refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        //下拉刷新------------------------------------------------------------------
        swipe_Refresh.setColorSchemeResources(R.color.zhi, R.color.se1, R.color.colorAccent);
        swipe_Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getStringResult();
                //两秒后停止刷新并更新视图
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        });
        getStringResult();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            swipe_Refresh.setRefreshing(false);
            adapter.notifyDataSetChanged();
        }
    };

    //Volley联网，Gson解析j获取到的son数据-----------------------------------------------
    public void getStringResult() {
        String url = "http://apis.baidu.com/hihelpsme/chinajoke/getjokelist";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
//                Try_joke.ResBodyBean.JokeListBean  listBean=new Gson().fromJson(s,new TypeToken<Try_joke.ResBodyBean>(){}.getType());
//                Log.d("tag!!!!!!!!!!!!!!!!!!!!",info.getList().toString());
// adapter=new Adapter(getContext(),listBean.);
//        <----------------------------------------------------->
                Gson gson = new Gson();
                Type type = new TypeToken<Try_joke>() {
                }.getType();
                Try_joke try_joke = gson.fromJson(s, type);
                List<Try_joke.ResBodyBean.JokeListBean> jokeList = try_joke.getRes_body().getJokeList();
//      \  <------------------------------------------------------>
                adapter = new Adapter(getContext(), jokeList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //、联网失败出现意外的处理
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
}
