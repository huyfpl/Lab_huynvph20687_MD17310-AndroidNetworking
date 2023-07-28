package com.example.androidnetworking_lab.Lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetworking_lab.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivityLab3 extends AppCompatActivity {
    EditText edttoan, edtly, edtcanh;
    Button btntinh, btntinhthetich;
    TextView txtkq, txtkqthetich;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3);
        edttoan = findViewById(R.id.edttoan);
        edtly = findViewById(R.id.edtly);
        btntinh = findViewById(R.id.btntinh);
        txtkq = findViewById(R.id.txtkq);
        edtcanh = findViewById(R.id.edtcanh);
        btntinhthetich = findViewById(R.id.btntinhthetich);
        txtkqthetich = findViewById(R.id.txtkqthetich);
        btntinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GETAsyncTask().execute();//goi asynctask }
            }
        });
        btntinhthetich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new POSTAsyncTask().execute();//goi asynctask }
            }
        });
    }
//

    class GETAsyncTask extends AsyncTask<Void, Void, Void> {
        String ketqua = "";
        String path = "https://anodyne-reader.000webhostapp.com/AndroidNetWorking/Lab3/apiget.php";

        @Override
        protected Void doInBackground(Void... voids) {

            path += "?toan=" + edttoan.getText().toString() + "&ly=" + edtly.getText().toString();
            try {
                URL url = new URL(path);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((url.openConnection().getInputStream())));
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                ketqua = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            txtkq.setText(ketqua);
        }
    }
    class POSTAsyncTask extends AsyncTask<Void, Void, Void> {
        String kq = "";
        String pathPost = "https://anodyne-reader.000webhostapp.com/AndroidNetWorking/Lab3/apipost.php";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
//1.chuyen path thanh url
                URL url = new URL(pathPost);
//2. ma hoa tham so
                String param = "canh=" + URLEncoder.encode(edtcanh.getText().toString(), "utf-8");
//3. Mo ket noi
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//4. Thiet lap cac thuoc tinh cho ket noi
                urlConnection.setDoOutput(true);//lay ve ket qua//
                urlConnection.setRequestMethod("POST");//xac dinh phuong thuc
                urlConnection.setFixedLengthStreamingMode(param.getBytes().length);//do dai tham so
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//5. thiet lap tham so
                PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
                printWriter.print(param);
                printWriter.close();
//6. doc du lieu
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }
//7. tra ve ket qua
                kq = stringBuilder.toString();
                urlConnection.disconnect();//dong ket noi
            } catch (MalformedURLException e) {
                e.printStackTrace();
                txtkqthetich.setText(e.getMessage());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                txtkqthetich.setText(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                txtkqthetich.setText(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            txtkqthetich.setText(kq);//tra ket qua ve client }
        }

    }
}
