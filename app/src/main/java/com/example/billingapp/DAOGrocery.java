package com.example.billingapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOGrocery {
    private DatabaseReference databaseReference;

    public DAOGrocery(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference=db.getReference(Grocery.class.getSimpleName());
    }

    public Task<Void> add(Grocery gro)
    {

        return databaseReference.push().setValue(gro);

    }

    public Task<Void> update(String key, HashMap<String,Object> hashMap)
    {

        return databaseReference.child(key).updateChildren(hashMap);

    }
    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

}
