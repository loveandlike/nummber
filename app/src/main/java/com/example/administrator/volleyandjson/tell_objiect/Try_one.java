package com.example.administrator.volleyandjson.tell_objiect;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.volleyandjson.MainActivity;
import com.example.administrator.volleyandjson.R;

/**
 * 没有使用，待修改
 * Created by Administrator on 2016/9/1.
 */
public class Try_one extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    NotificationManager mNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //定义NotificationManager
        String ns = Context.NOTIFICATION_SERVICE;
        mNotificationManager = (NotificationManager) getSystemService(ns);
        init();
    }

    private void init() {
        //定义通知栏展现的内容信息
        int icon = R.mipmap.wallpaper_5230611;
        CharSequence tickerText = "我的通知栏标题";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);

        //定义下拉通知栏时要展现的内容信息
        Context context = getApplicationContext();
        CharSequence contentTitle = "我的通知栏标展开标题";
        CharSequence contentText = "我的通知栏展开详细内容";
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        //用mNotificationManager的notify方法通知用户生成标题栏消息通知
        mNotificationManager.notify(1, notification);
    }


}
