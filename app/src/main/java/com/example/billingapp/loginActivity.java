package com.example.billingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {
    EditText loginUsername;
    EditText loginPhone;
    Button loginBtn;
    String registeredName;
    String dbName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername=(EditText) findViewById(R.id.loginUsername);
        loginPhone=(EditText) findViewById(R.id.loginPhone);
        loginBtn= (Button) findViewById(R.id.loginBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            FirebaseDatabase.getInstance().getReference().child("Grocery").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                  if(snapshot.exists()){

                        for(DataSnapshot snapshot1:snapshot.getChildren()){

                           Grocery item=snapshot1.getValue(Grocery.class);
                             String  username= item.getName();
                            String phone=item.getNumber();

                            System.out.println(username+" "+phone+" ");
                            if(username.equals(loginUsername.getText().toString())){
                                if(phone.equals(loginPhone.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "logged in", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Menu.class);
                                    startActivity(intent);
                                }

                            }



                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });


            }
        });



    }
}