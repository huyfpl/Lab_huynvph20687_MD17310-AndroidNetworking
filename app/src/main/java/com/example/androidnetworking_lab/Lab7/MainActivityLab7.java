package com.example.androidnetworking_lab.Lab7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidnetworking_lab.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivityLab7 extends AppCompatActivity {
    Button btnthem,btnxoa,btnsua,btnselect;
    TextView tvkq;
    Context context=this;
    String strKQ="";
    EditText edtid,edtname,edtprice,edtdescription;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab7);
        btnthem=findViewById(R.id.btnthem);
        btnxoa=findViewById(R.id.btnxoa);
        btnsua=findViewById(R.id.btnsua);
        btnselect=findViewById(R.id.btnselect);
        tvkq=findViewById(R.id.tvkq);
        edtid=findViewById(R.id.edtid);
        edtname=findViewById(R.id.edtname);
        edtprice=findViewById(R.id.edtprice);
        edtdescription=findViewById(R.id.edtdescription);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertVolley();
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteVolley();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateVolley();
            }
        });
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectVolley();
            }
        });
    }
    private void deleteVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue= Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/delete_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvkq.setText(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                tvkq.setText(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("pid",edtid.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }
    private void updateVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/update_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvkq.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvkq.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("pid",edtid.getText().toString());
                mydata.put("name",edtname.getText().toString());
                mydata.put("price",edtprice.getText().toString());
                mydata.put("description",edtdescription.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }
    private void insertVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/create_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvkq.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvkq.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("name",edtname.getText().toString());
                mydata.put("price",edtprice.getText().toString());
                mydata.put("description",edtdescription.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }
    private void selectVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/get_all_product.php";
        //b4. Xac dinh loai request
        JsonObjectRequest request=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray products=response.getJSONArray("products");
                    strKQ="";
                    for (int i=0;i<products.length();i++)
                    {
                        JSONObject product=products.getJSONObject(i);
                        String pid=product.getString("pid");
                        String name=product.getString("name");
                        String price=product.getString("price");
                        String description=product.getString("description");
                        strKQ +="pid: "+pid +"\n";
                        strKQ +="name: "+name +"\n";
                        strKQ +="price: "+price +"\n";
                        strKQ +="description: "+description +"\n";
                    }
                    tvkq.setText(strKQ);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvkq.setText(error.getMessage());
            }
        });
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }
}
