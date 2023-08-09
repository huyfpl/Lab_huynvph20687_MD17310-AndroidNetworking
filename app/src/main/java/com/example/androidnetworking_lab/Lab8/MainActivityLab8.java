package com.example.androidnetworking_lab.Lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import com.example.androidnetworking_lab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivityLab8 extends AppCompatActivity {
    TextView tvKQ;
    Context context = this;
    FirebaseFirestore database;
    String id = "";
    ToDo toDo = null;
    EditText edtTitle, edtContent;
    Button btnThem, btnSua, btnXoa, btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab8);

        tvKQ = findViewById(R.id.tvkq);
        edtTitle = findViewById(R.id.edttitle);
        edtContent = findViewById(R.id.edtcontent);
        btnThem = findViewById(R.id.btnthem);
        btnSua = findViewById(R.id.btnsua);
        btnXoa = findViewById(R.id.btnxoa);
        btnSelect = findViewById(R.id.btnselect);

        database = FirebaseFirestore.getInstance();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertFirebase();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFirebase();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFirebase();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDataFromFirebase();
            }
        });
    }

    public void insertFirebase() {
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();

        if (!title.isEmpty() && !content.isEmpty()) {
            id = UUID.randomUUID().toString();
            toDo = new ToDo(id, title, content);

            HashMap<String, Object> mapToDo = toDo.convertHashMap();
            database.collection("TODO").document(id)
                    .set(mapToDo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateFirebase() {
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();

        if (!title.isEmpty() && !content.isEmpty()) {
            id = "64475724-c58b-4968-82d3-aa2540c70e53"; // Replace with the actual document ID
            toDo = new ToDo(id, title, content);

            database.collection("TODO")
                    .document(toDo.getId())
                    .update(toDo.convertHashMap())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteFirebase() {
        id = "64475724-c58b-4968-82d3-aa2540c70e53"; // Replace with the actual document ID

        database.collection("TODO")
                .document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public ArrayList<ToDo> SelectDataFromFirebase() {
        ArrayList<ToDo> list = new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String strKQ = "";
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ToDo toDo1 = document.toObject(ToDo.class);
                                strKQ += "id: " + toDo1.getId() + "\n"+
                                        "title: " + toDo1.getTitle() + "\n"+
                                        "content: " + toDo1.getContent() + "\n\n";
                                list.add(toDo1);
                            }
                            Toast.makeText(context, strKQ, Toast.LENGTH_SHORT).show();
                            tvKQ.setText(strKQ);
                        } else {
                            Toast.makeText(context, "Lấy dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return list;
    }

}