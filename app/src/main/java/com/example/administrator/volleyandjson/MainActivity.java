package com.example.administrator.volleyandjson;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.volleyandjson.ViewPaggerAdapter.MainViewPaggerAdapter;
import com.example.administrator.volleyandjson.fragment_three_pager.Frafment_three;
import com.example.administrator.volleyandjson.scrollview.MainActivity_1;
import com.example.administrator.volleyandjson.tell_objiect.TellObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener {
    private Button iv_one, iv_two, iv_three;
    FragmentManager fm;//碎片管理器
    FragmentTransaction ft;//碎片操作器
    RecyclerViewFragment recyclerViewFragment;
    ImageFragment imageFragment;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    TellObject tellObject;
    //    设置viewpager
    private MainViewPaggerAdapter adapter;
    private ViewPager vp_main;
    private Fragment[] frags = new Fragment[3];
    //  为了按钮效果，按下保持变色，
    private Button[] btns = new Button[3];
    private int[] btnIds = {R.id.iv_one, R.id.iv_two, R.id.iv_three};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();//碎片管理器
        ft = fm.beginTransaction();//碎片操作器
        setContentView(R.layout.activity_main);
//        iv_one = (Button) findViewById(R.id.iv_one);
//        iv_two = (Button) findViewById(R.id.iv_two);
//        iv_three = (Button) findViewById(R.id.iv_three);
        navigationView = (NavigationView) findViewById(R.id.nav_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        recyclerViewFragment = new RecyclerViewFragment();
//        imageFragment = new ImageFragment();
////    设置viewpager
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        frags[0] =  new RecyclerViewFragment();;
        frags[1] =  new ImageFragment();
        frags[2] = new Frafment_three();
        adapter = new MainViewPaggerAdapter(getSupportFragmentManager(), frags);

        vp_main. setOnPageChangeListener(this);
        //  为了按钮效果，按下保持变色，<--------------------------------------------------------------
        for (int i = 0; i < btns.length; i++) {
            btns[i] = (Button) findViewById(btnIds[i]);
            btns[i].setOnClickListener(this);
        }
        vp_main.setAdapter(adapter);
        //添加视图<--------------------------------------------------------------->
//        ft.add(R.id.main, recyclerViewFragment).add(R.id.main, imageFragment);
//        ft.commit();
        //点击事件<--------------------------------------------------------->
//        iv_one.setOnClickListener(this);
//        iv_two.setOnClickListener(this);
//        iv_three.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);

        //toolbar<---------------------------------------------------------->
        toolbar.setTitle("贺半仙");
        setSupportActionBar(toolbar);
        //参数一 当前activity   参数二 drawerlayout
        //参数三toobar          参数四，五     划出和划入的文本说明
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();//将开关同步设置
        drawerLayout.addDrawerListener(toggle);
    }

    //主页面的点击事件<----------------------------------------------------------------------->
    @Override
    public void onClick(View view) {
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.iv_one:
                setBtnEnable(0);//为了按钮效果，按下保持变色，
//                ft.hide(recyclerViewFragment).hide(imageFragment);
//                ft.show(recyclerViewFragment);
                break;
            case R.id.iv_two:
                setBtnEnable(1);
//                ft.hide(recyclerViewFragment).hide(imageFragment);
//                ft.show(imageFragment);
                break;
            case R.id.iv_three:
                setBtnEnable(2);
                break;
        }
        ft.commit();
    }

    //    侧滑菜单部分的点击事件<-------------------------------------------------->
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, TellObject.class);
        switch (item.getItemId()) {
            case R.id.menu_first:
                tellObject = new TellObject();
                startService(intent);
                Snackbar.make(navigationView, "开启服务，显示通知栏", Snackbar.LENGTH_SHORT).setAction("myaction", null).show();
                break;
            case R.id.menu_second:
                stopService(intent);
//                stopService(intent2);
                Snackbar.make(navigationView, "关闭服务", Snackbar.LENGTH_SHORT).setAction("myaction", null).show();
                break;
            case R.id.menu_third:
                Intent intent1 = new Intent(MainActivity.this, MainActivity_1.class);
                startActivity(intent1);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //    <------------------------------------------------------------------------>
//此方法定义Menu的布局样式，返回false则不显示Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //toolbar 的点击<------------------------------------------------------->
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //注销账户
        if (id == R.id.action_pull) {
            Snackbar.make(navigationView, "金爷万岁,哈哈哈哈哈哈哈哈哈", Snackbar.LENGTH_SHORT).setAction("myaction", null).show();
            return true;
        } else if (id == R.id.action_out) {
            //退出整个应用
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
    //    设置viewpager
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setBtnEnable(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void setBtnEnable(int number) {
        vp_main.setCurrentItem(number);//viewpager设置画面
        for (int i = 0; i < btns.length; i++) {
            if (i == number) {
                btns[i].setEnabled(false);
            } else {
                btns[i].setEnabled(true);
            }
        }
    }
}
