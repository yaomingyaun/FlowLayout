package com.bw.ymy.demo1;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Netuil {
    private  static  Netuil instance;

    public Netuil() {
    }
    public  static  Netuil getInstance()
    {
        if(instance==null)
        {
            instance=new Netuil();
        }
        return  instance;
    }
    public  interface  Callback<T>
    {
        void onSuccess(T t);
    }
    public  void getRequest(final  String urlstr,final Class clazz,final Callback callback)
    {
       new AsyncTask<String,Void,Object>()
       {
           @Override
           protected Object doInBackground(String... strings) {
               return getRequest(urlstr,clazz);
           }

           @Override
           protected void onPostExecute(Object o) {
              callback.onSuccess(o);
           }
       }.execute(urlstr);
    }
    public  <T> T getRequest(String urlStr,Class clazz)
    {
        return (T) new Gson().fromJson(getRequest(urlStr),clazz);
    }
    public String getRequest(String urlStr){
        String result = "";
        try {
            //定义url地址
            URL url = new URL(urlStr);
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //设置请求格式
            urlConnection.setRequestMethod("GET");
            //设置超时
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            //获取请求码
            int responseCode = urlConnection.getResponseCode();
            if(responseCode == 200){
                result = stream2String(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String stream2String(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        for (String tmp = br.readLine();tmp!=null;tmp = br.readLine()){
            builder.append(tmp);
        }
        return builder.toString();
    }


}
