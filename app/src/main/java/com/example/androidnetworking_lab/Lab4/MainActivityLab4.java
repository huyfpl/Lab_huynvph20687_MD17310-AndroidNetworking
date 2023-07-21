package com.example.androidnetworking_lab.Lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnetworking_lab.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivityLab4 extends AppCompatActivity {
    EditText editText1, editText2, editText3;
    Button btnI, btnS;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab4);
        editText1= findViewById(R.id.edtname);
        editText2= findViewById(R.id.edtprices);
        editText3= findViewById(R.id.edt3);
        btnS= findViewById(R.id.btngetdata);
        btnI= findViewById(R.id.btninsert);
        tv= findViewById(R.id.txtdata);
        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRes();
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectRetrofit();
            }
        });
    }
    void insertRes(){
        Products product= new Products();
        product.setName(editText1.getText().toString());
        product.setPrice(editText2.getText().toString());
        product.setDescription(editText2.getText().toString());

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://anodyne-reader.000webhostapp.com/AndroidNetWorking/Lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceInsert interfaceInsert= retrofit.create(InterfaceInsert.class);
        Call<SvrResponseInsert> call = interfaceInsert.insertData(product.getName(), product.getPrice(),product.getDescription());
        call.enqueue(new Callback<SvrResponseInsert>() {
            @Override
            public void onResponse(Call<SvrResponseInsert> call, Response<SvrResponseInsert> response) {
                SvrResponseInsert serverResI = response.body();
                tv.setText(serverResI.getMessage());
            }



            @Override
            public void onFailure(Call<SvrResponseInsert> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });
    }
    String kq = "";
    ArrayList<Products> list;
    void selectRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://anodyne-reader.000webhostapp.com/AndroidNetWorking/Lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceSelect interfaceSelect = retrofit.create(InterfaceSelect.class);
        Call<SvrcResponseSelect> call= interfaceSelect.selectData();
        call.enqueue(new Callback<SvrcResponseSelect>() {
            @Override
            public void onResponse(Call<SvrcResponseSelect> call, Response<SvrcResponseSelect> response) {
                SvrcResponseSelect serverResS= response.body();
                list= new ArrayList<>(Arrays.asList(serverResS.getProducts()));
                kq = "";
                for (Products product: list){
                    kq +="Name: " +product.getName()+"Price: " +product.getPrice()+"Description: " +product.getDescription()+ "\n";

                }
                tv.setText(kq);
            }

            @Override
            public void onFailure(Call<SvrcResponseSelect> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });

    }
}