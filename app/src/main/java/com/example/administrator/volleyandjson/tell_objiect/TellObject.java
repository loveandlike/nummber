package com.example.administrator.volleyandjson.tell_objiect;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.example.administrator.volleyandjson.MainActivity;
import com.example.administrator.volleyandjson.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/1.
 */
public class TellObject extends Service {
    //通知栏相关参数
    private Notification notification;

    //    第一步：获取状态通知栏管理：
    private NotificationManager manager;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////    第二步：实例化通知栏构造器NotificationCompat.Builder：
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
//        mBuilder.setContentTitle("大唐盛世")//设置通知栏标题
//                .setContentText("测试内容")
////                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
//                .setNumber(6) //设置通知集合的数量
//                .setTicker("金爷来啦") //通知首次出现在通知栏，带上升动画效果的
//                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
//                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
//                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
//                .setOngoing(false)//设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
//                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
//                .setSmallIcon(R.mipmap.wallpaper_5230611);//设置通知小ICON
//    }


    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        initNotify();
    }

    //通知栏设置
    public void initNotify() {

        notification = new Notification();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.icon = R.mipmap.wallpaper_5230609;//设置图标
        notification.tickerText = "圣旨到";//显示通知栏通知，瞬时的
        //参数一包名   参数二是布局
        RemoteViews remoteviews = new RemoteViews(getPackageName(), R.layout.layout_notify);
        notification.contentView = remoteviews;//此处不能写反
        //参数一 id    参数二是文本
        long str = System.currentTimeMillis();//获取系统时间毫秒
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");
        Date date = new Date( System.currentTimeMillis());
        String result = format.format(date);//转换格式

        remoteviews.setTextViewText(R.id.title_notify, "盛世大唐");//给通知设置文本
        remoteviews.setTextViewText(R.id.artist_notify, "贺半仙");
        remoteviews.setTextViewText(R.id.item_time, result);
        remoteviews.setImageViewResource(R.mipmap.hjc_z,1);//给图片栏图片设置资源id
//        remoteviews.setProgressBar(R.id.pb_notify, 30, 70, false);
        // 给通知上的图片设置点击后，跳转到列表页面
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        remoteviews.setOnClickPendingIntent(R.id.img_notify, pendingintent);
//        Intent i2 = new Intent(Constants.MUSIC_PLAY);
//        PendingIntent p2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_ONE_SHOT);
        manager.notify(0x110, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
