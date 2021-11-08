package com.example.billingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    public static final String Name="No_Name_Present";
    public static final String Phone="No_phone_Present";
    public static final String Address="No_Address_Present";


    private TextView registerHeader;

    private EditText registerName ;
    private EditText registerPhone ;
    private EditText registerAddress ;

    private Button clearBtn;
    private Button validateBtn;
    private Button registerBtn;
    private Button viewAllBtn;

    private boolean isValidated=false;
    private boolean isRegistered=false;

    private String userEnteredName;
    private String userEnteredPhone;
    private String userEnteredAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerHeader = (TextView)findViewById(R.id.registerHeader);

        registerName = (EditText)findViewById(R.id.registerName);
        registerPhone = (EditText)findViewById(R.id.registerPhone);
        registerAddress = (EditText)findViewById(R.id.registerEmail);

        clearBtn=(Button) findViewById(R.id.clearBtn);
        validateBtn=(Button) findViewById(R.id.validateBtn);
        registerBtn=(Button) findViewById(R.id.registerBtn);
        viewAllBtn=(Button)findViewById(R.id.viewBtn);
        DAOGrocery dao=new DAOGrocery();

        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),viewAllActivity.class);
                startActivity(intent);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerName.getText().clear();
                registerPhone.getText().clear();
                registerAddress.getText().clear();

                Toast.makeText(MainActivity.this,"All input fields cleared",Toast.LENGTH_LONG).show();


            }

        });

        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),loginActivity.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate(registerName.getText().toString(),registerPhone.getText().toString(),registerAddress.getText().toString());
                if(isValidated){
                    userEnteredName=registerName.getText().toString();
                    userEnteredPhone=registerPhone.getText().toString();
                    userEnteredAddress=registerAddress.getText().toString();
                }
                if(isValidated){
                    Intent intent = new Intent(view.getContext(),loginActivity.class);

                    intent.putExtra(Name,userEnteredName);
                    intent.putExtra(Phone,userEnteredPhone);
                    intent.putExtra(Address,userEnteredAddress);

                    Grocery emp=new Grocery(registerName.getText().toString(),registerPhone.getText().toString(),registerAddress.getText().toString());
                    dao.add(emp).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity.this,"Registering user",Toast.LENGTH_LONG).show();

                            Log.d("Message","Record is inserted");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Message",e.getMessage());

                        }
                    });



                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this,"Validate first before registering",Toast.LENGTH_LONG).show();

                }
            }
        });




    }

    private void validate(String name, String phone, String address) {
        if(name.length()>=3  && name.matches(".*\\d.*")==false){
            if(phone.length()==10){
                if(address.length()>=10){
                    Toast.makeText(MainActivity.this,"All data validated, proceed to register",Toast.LENGTH_LONG).show();
                    isValidated=true;
                }
                else{
                    Toast.makeText(MainActivity.this,"Address must have mininmum 10 characters",Toast.LENGTH_LONG).show();
                    isValidated=false;
                }
            }
            else{
                Toast.makeText(MainActivity.this,"Enter valid 10 digit phone number",Toast.LENGTH_LONG).show();
                isValidated=false;
            }
        }
        else{
            Toast.makeText(MainActivity.this,"Name must have minimum 3 characters and not contain numbers",Toast.LENGTH_LONG).show();
            isValidated=false;
        }
    }
}