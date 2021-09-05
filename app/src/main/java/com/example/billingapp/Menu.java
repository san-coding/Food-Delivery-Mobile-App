package com.example.billingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    private ListView listView;
    private ListAdapter listAdapter;
    ArrayList<Product> products = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<Product> productOrders = new ArrayList<>();
    ArrayList<String> lOrderItems= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getProduct();
        listView = (ListView) findViewById(R.id.customListView);
        listAdapter = new ListAdapter(this,products);
        listView.setAdapter(listAdapter);
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void placeOrder()
    {
        /* Clear Product Orders for any existing data */
        productOrders.clear();
        lOrderItems.clear();

        /* Loop through each product. If user added any
         *  Quantity then add product in Product Order
         * */
        for(int i=0;i<listAdapter.listProducts.size();i++)
        {
            if(listAdapter.listProducts.get(i).CartQuantity > 0)
            {
                Product products = new Product(
                        listAdapter.listProducts.get(i).ProductName
                        ,listAdapter.listProducts.get(i).ProductPrice
                        ,listAdapter.listProducts.get(i).ProductImage
                );
                products.CartQuantity = listAdapter.listProducts.get(i).CartQuantity;
                productOrders.add(products);
                /* Create a JSON Object and store it in String ArrayList */
                lOrderItems.add(products.getJsonObject().toString());
            }
        }

        /* Convert String ArrayList into JSON Array */
        JSONArray jsonArray = new JSONArray(lOrderItems);
        /* Open Summary with JSONArray String */
        openSummary(jsonArray.toString());
    }

    public void showMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    public void openSummary(String orderItems)
    {
        Intent summaryIntent = new Intent(this,Summary.class);
        summaryIntent.putExtra("orderItems",orderItems);
        startActivity(summaryIntent);
    }

    public void getProduct() {
        products.add(new Product("Tayir Sadam",50d,R.drawable.tayirsadam));
        products.add(new Product("Samosa",10d,R.drawable.samosa1));
        products.add(new Product("Gulab Jamun",12d,R.drawable.gulabjamun));
        products.add(new Product("Panneer Tikka",13d,R.drawable.paneertikka));
        products.add(new Product("Rasmalai",14d,R.drawable.rasmalai));
        products.add(new Product("Cheesy Pizza",16d,R.drawable.pizza));
        products.add(new Product("Bhel Puri",11d,R.drawable.bhelpuri1));
        products.add(new Product("Chocolate Cake",15d,R.drawable.cake));
        products.add(new Product("Sandwich",11d,R.drawable.sandwich));
        products.add(new Product("Belgian Waffle",17d,R.drawable.waffle));
        products.add(new Product("Spring Rolls",6d,R.drawable.springrolls));
    }
}