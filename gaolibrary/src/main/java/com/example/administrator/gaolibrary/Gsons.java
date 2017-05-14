package com.example.administrator.gaolibrary;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Created by lenovo on 2017/3/17.
 */

public class Gsons {

        /**
         * 定义一个私有的属性
         * 定义静态代码块进行判断
         * 如果为空的话就让他生成Gson
         */
        private static Gson gson=null;
        static {
            if (gson == null) {
                gson = new Gson();
            }
        }
        /**
         * 定义私有的构造放方法
         */
        private Gsons(){};

        /**
         * Json串转换成bean
         * @param gsonstr
         * @param cls
         * @param <T>
         * @return
         */
        public static <T> T GetGsonfrom(String gsonstr,Class<T> cls){
            T t=null;
            if (gson!=null){
                t=gson.fromJson(gsonstr,cls);
            }
            return t;
        }
        /**
         * 转成json
         *
         * @param object
         * @return
         */
        public static String GsonString(Object object) {
            String gsonString = null;
            if (gson != null) {
                gsonString = gson.toJson(object);
            }
            return gsonString;
        }
        /**
         * 转成list
         * 解决泛型问题
         * @param json
         * @param cls
         * @param <T>
         * @return
         */
        public static <T> List<T> jsonToList(String json, Class<T> cls) {
            Gson gson = new Gson();
            List<T> list = new ArrayList<T>();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for(final JsonElement elem : array){
                list.add(gson.fromJson(elem, cls));
            }
            return list;
        }
        /**
         * 转成list中有map的
         *
         * @param gsonString
         * @return
         */
        public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
            List<Map<String, T>> list = null;
            if (gson != null) {
                list = gson.fromJson(gsonString,
                        new TypeToken<List<Map<String, T>>>() {
                        }.getType());
            }
            return list;
        }
        /**
         * 转成map的
         *
         * @param gsonString
         * @return
         */
        public static <T> Map<String, T> GsonToMaps(String gsonString) {
            Map<String, T> map = null;
            if (gson != null) {
                map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
                }.getType());
            }
            return map;
        }
}
