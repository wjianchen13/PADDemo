package com.example.paddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imgv_test);
    }
    
    public void onTest(View v) {
        img.setImageBitmap(getImg("test.jpg"));//assets目录下文件名 
    }
    
    private Bitmap getImg(String file) {
        Bitmap bmp = null;
        //获取AssetsMng对象
        AssetManager am = getResources().getAssets();
        try {
            //打开文件,返回输入流
            InputStream is = am.open(file);
            //Bitmap工厂解码输入流,得到bmp对象
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
}