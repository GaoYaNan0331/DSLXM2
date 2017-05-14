package com.baway.dslxm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.baway.dslxm.Bean.MyBean;
import com.baway.dslxm.adapter.MyRecycleradapter2;

import java.util.ArrayList;


import static android.media.CamcorderProfile.get;


public class SecondActivity extends AppCompatActivity {

   private RecyclerView two_re;
    private RelativeLayout activity_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        //实例化布局管理器传入参数：参数一是当前；参数二是显示垂直显示；参数三是默认正向加载
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //给recyclerview添加布局管理器
      two_re.setLayoutManager(manager);
        //设置item的尺寸
        two_re.setHasFixedSize(true);
        two_re.addItemDecoration(new MyAddLines(SecondActivity.this, MyAddLines.VERTICAL_LIST));
        Intent intent = getIntent();
        ArrayList<MyBean.DataBean> duix = (ArrayList<MyBean.DataBean>) intent.getSerializableExtra("duix");
        MyRecycleradapter2 myRecycleradapter2 = new MyRecycleradapter2( duix,this);
       two_re.setAdapter(myRecycleradapter2);

    }

    private void initView() {
     two_re= (RecyclerView) findViewById(R.id.two_recy2);
        activity_two = (RelativeLayout) findViewById(R.id.activity_two);
    }
}
