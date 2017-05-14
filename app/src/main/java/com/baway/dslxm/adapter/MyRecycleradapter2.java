package com.baway.dslxm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.dslxm.Bean.MyBean;
import com.baway.dslxm.R;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MyRecycleradapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>
       {
    private int TIAO=0;
    private int TIAO1=1;
    private int TIAO2=2;
        //数据源
        private List<MyBean.DataBean> list;
        private Context context;
        //是否显示单选框,默认false
        private boolean isshowBox = false;
        // 存储勾选框状态的map集合
        private Map<Integer, Boolean> map = new HashMap<>();
           //构造方法为了调用
        public MyRecycleradapter2(List<MyBean.DataBean> list, Context context) {
            this.list = list;
            this.context = context;

        }

//设置itemViewType的种类，用来判断多条目的展示
    @Override
    public int getItemViewType(int position) {
        int i = position % 3;
        if (i == 0) {
           return TIAO;
        }else if (i==1){
            return TIAO1;
        }else if (i==2){
            return TIAO2;
        }

       return 0;
    }


    //视图管理第一个
        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;

            private View itemView;
         private ImageView rec_img;
            public ViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                title = (TextView) itemView.findViewById(R.id.tv);

                rec_img= (ImageView) itemView.findViewById(R.id.recyl_img);
            }
        }

    //视图管理第二个
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView title;

        private View itemView;
        private ImageView rec2_img1,rec2_img2;

        public ViewHolder2(View itemView) {
            super(itemView);
            //找到所需的控件
            title = (TextView) itemView.findViewById(R.id.recy2_tv2);
            rec2_img1= (ImageView) itemView.findViewById(R.id.rec2_img2);
            rec2_img2= (ImageView)itemView.findViewById(R.id.rec2_img3);
        }

    }
    //视图管理
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        private TextView title;

        private View itemView;
        private ImageView rec_img4;
        public ViewHolder3(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = (TextView)itemView.findViewById(R.id.recy3_tv3);
              rec_img4= (ImageView) itemView.findViewById(R.id.recy3_img4);
        }
    }
           //判断所要获取的条目数的集合的个数
        @Override
        public int getItemCount() {
            if (list != null && list.size()>0) {
                return list.size();
            }
            return 0;
        }

        //绑定视图管理者

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //判断多条目展示的三种情况，加载布局文件，并将将文件装载布局里面
            if (viewType==TIAO){
              View  root = LayoutInflater.from(parent.getContext()).inflate(R.layout.re_list_item1, parent, false);
                ViewHolder vh = new ViewHolder(root);
                return vh;
            }
            else if (viewType==TIAO1){
               View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.re_list_item2, parent, false);
                ViewHolder2 viewHolder2 = new ViewHolder2(root);
             return viewHolder2;

            }
            else if (viewType==TIAO2){
              View  root = LayoutInflater.from(parent.getContext()).inflate(R.layout.re_list_item3, parent, false);
                ViewHolder3 vh3 = new ViewHolder3(root);

              return vh3;
            }
            return null;
        }
            //对视图管理的图片的处理
           @Override
           public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
               //通过判断Viewholder是否包含在RecyclerView，如果在就进行对图片的处理
               if(holder instanceof ViewHolder){
                   ViewHolder vh1= (ViewHolder) holder;
                   vh1.title.setText(list.get(position).getGoods_name());
                   Glide
                           .with(context)
                           .load(list.get(position).getGoods_img())
                           //属性是加载的圆形图片
                           .bitmapTransform(new CropCircleTransformation(context))
                           //属性是把彩色的图片加载成黑白的颜色
//                    .bitmapTransform(new GrayscaleTransformation(context))
                           .into(vh1.rec_img);
               }else if (holder instanceof ViewHolder2) {
                   ViewHolder2 vh2= (ViewHolder2) holder;
                   vh2.title.setText(list.get(position).getGoods_name());
                   Glide
                           .with(context)
                           .load(list.get(position).getGoods_img())
                           //属性是加载的圆形图片
                           .bitmapTransform(new CropCircleTransformation(context))
                           //属性是把彩色的图片加载成黑白的颜色
//                    .bitmapTransform(new GrayscaleTransformation(context))
                           .into(vh2.rec2_img1);
                   Glide
                           .with(context)
                           .load(list.get(position).getGoods_img())
                           //属性是加载的圆形图片
                           .bitmapTransform(new CropCircleTransformation(context))
                           //属性是把彩色的图片加载成黑白的颜色
//                    .bitmapTransform(new GrayscaleTransformation(context))
                           .into(vh2.rec2_img2);


               }else if (holder instanceof ViewHolder3) {
                   ViewHolder3 vh3= (ViewHolder3) holder;
                     vh3.title.setText(list.get(position).getGoods_name());
                   Glide
                           .with(context)
                           .load(list.get(position).getGoods_img())
                           //属性是加载的圆形图片
                           .bitmapTransform(new CropCircleTransformation(context))
                           //属性是把彩色的图片加载成黑白的颜色
//                    .bitmapTransform(new GrayscaleTransformation(context))
                           .into(vh3.rec_img4);

               }
               }


       }
