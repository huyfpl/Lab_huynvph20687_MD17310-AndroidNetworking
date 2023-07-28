package com.example.androidnetworking_lab.Lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.androidnetworking_lab.R;

public class MainActivityLab2 extends AppCompatActivity implements InterfaceLab2 ,View.OnClickListener{
    ImageView imageView;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
        button =findViewById(R.id.btnclick);
        textView= findViewById(R.id.text);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new AsyncTaskLab2(this, this).execute("http://tinypic.com/images/goodbye.jpg");
    }

    @Override
    public void onLoadAnh(Bitmap bitmap) {
  imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
          textView.setText("Error");
    }
}