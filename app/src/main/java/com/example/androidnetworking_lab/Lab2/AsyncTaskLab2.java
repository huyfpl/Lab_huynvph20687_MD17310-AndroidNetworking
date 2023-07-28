package com.example.androidnetworking_lab.Lab2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AsyncTaskLab2 extends AsyncTask<String, Void, Bitmap> {
    private InterfaceLab2 interfaceLab2;
    private Context context;
    public AsyncTaskLab2(InterfaceLab2 interfaceLab2, Context context) {
        this.interfaceLab2 = interfaceLab2;
        this.context = context;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap!= null){
            interfaceLab2.onLoadAnh((bitmap));
        }else {
            interfaceLab2.onError();
        }
    }
}
