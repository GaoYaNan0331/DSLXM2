package com.baway.dslxm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.baway.dslxm.Bean.MyBean;
import com.baway.dslxm.Url.Myurl;
import com.baway.dslxm.adapter.MyRecycleradapter;
import com.example.administrator.gaolibrary.Gsons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp.OkHttpManager;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<MyBean.DataBean> mData;
    private ArrayList< MyBean.DataBean> list=new ArrayList<>();
    private MyRecycleradapter mMyRecycleradapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        findViewById(R.id.commit).setOnClickListener(this);
        initData();
        //实例化布局管理器传入参数：参数一是当前；参数二是显示垂直显示；参数三是默认正向加载
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //给recyclerview添加布局管理器
        mRecyclerView.setLayoutManager(manager);
        //设置item的尺寸
        mRecyclerView.setHasFixedSize(true);
        //实例化适配器

    }

    @Override
    public void onClick(View v) {
        //获取你选中的item
        Map<Integer, Boolean> map = mMyRecycleradapter.getMap();
//        定义个变量初始值给0；
        int as=0;
        //遍历集合判断点击框用一个变量来记录
        for (int i = 0; i < map.size(); i++) {

            if (map.get(i)) {
                ++as;
                MyBean.DataBean dataBean = mData.get(i);
                list.add(dataBean);

            }

        }
        Toast.makeText(MainActivity.this,"你选了：" + as+ "项",Toast.LENGTH_SHORT).show();
        //实现跳转并把记录的值传过去实现跳转
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("duix",list);
        startActivity(intent);
    }

    /**
     * @param menu
     * @return:下面代码和RecyclerView无关是菜单方便展示RecyclerView才使用
     * 此方法是显示全选和全不选的两个属性
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     * @param item
     * @return此方法是重写方法的点击事件，如果不重写此方法不会实现全选和不选的监听
     * 实现全选和不选的选择框的监听
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //调取全选在适配器的方法
            case R.id.all:
                Map<Integer, Boolean> map = mMyRecycleradapter.getMap();
                for (int i = 0; i < map.size(); i++) {
                    map.put(i, true);
                    mMyRecycleradapter.notifyDataSetChanged();
                }
                break;
            //调取全不选在适配器的方法
            case R.id.no_all:
                Map<Integer, Boolean> m = mMyRecycleradapter.getMap();
                for (int i = 0; i < m.size(); i++) {
                    m.put(i, false);
                    mMyRecycleradapter.notifyDataSetChanged();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 为列表添加测试数据
     */
    private void initData() {
        OkHttpManager.getAsync(Myurl.path, new OkHttpManager.DataCallBack() {



            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                MyBean myBean = Gsons.GetGsonfrom(result, MyBean.class);
                mData = myBean.getData();
                mMyRecycleradapter = new MyRecycleradapter(mData, MainActivity.this);
                //添加到recyclerview里面
                mRecyclerView.setAdapter( mMyRecycleradapter);
                //添加分割线
                mRecyclerView.addItemDecoration(new MyAddLines(MainActivity.this, MyAddLines.VERTICAL_LIST));
                mMyRecycleradapter.setRecyclerViewOnItemClickListener(new MyRecycleradapter.RecyclerViewOnItemClickListener() {
                    //单击选择框
                    @Override
                    public void onItemClickListener(View view, int position) {
                        //设置选中的项
                        mMyRecycleradapter.setSelectItem(position);
                    }
                    //实现长按显示选择框
                    @Override
                    public boolean onItemLongClickListener(View view, int position) {
                        mMyRecycleradapter.setShowBox();
                        //设置选中的项
                        mMyRecycleradapter.setSelectItem(position);
                        mMyRecycleradapter.notifyDataSetChanged();
                        return true;
                    }
                });
            }
        });


    }
}
