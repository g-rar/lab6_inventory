package com.example.lab6_inventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TAG = "GERADEGUB";
    int ADD_PRODUCT = 1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbProductsRef = database.getReference("productos");
    List<ProductContainer> products;
//    CustomProductAdapter productsAdapter;
    SwipeProductAdapter productsAdapter;
    ListView productsLV;
    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsLV = findViewById(R.id.listView_products);
        products = new ArrayList<>();
//        productsAdapter = new CustomProductAdapter(this, products);
        productsAdapter = new SwipeProductAdapter(this, products);
        productsLV.setAdapter(productsAdapter);

        productsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                intent.putExtra("pos", position);
                intent.putExtra("nombre", products.get(position).getNombre());
                intent.putExtra("descripcion", products.get(position).getDescripcion());
                intent.putExtra("cantidad", products.get(position).getCantidad());
                intent.putExtra("precio", products.get(position).getPrecio());
                startActivity(intent);
            }
        });

        dbProductsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();
                List<ProductContainer> dbList = (dataSnapshot.getValue(new GenericTypeIndicator<List<ProductContainer>>() {}));
                for (int i = 0; i < dbList.size(); i++) {
                    Log.d(TAG, "Valor de la lista: " + dbList.get(i).toString());
                    products.add(dbList.get(i));
                }
                productsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "No se pudo leer el valor.", databaseError.toException());
            }
        });

        fba = FirebaseAuth.getInstance();
        FirebaseUser usr = fba.getCurrentUser();
        if(usr!=null){
            String usrMail = fba.getCurrentUser().getEmail();
            usrMail = usrMail.substring(0, usrMail.indexOf('@'));
            TextView tv = findViewById(R.id.textView_usrName);
            tv.setText(usrMail);
        }
    }

    public void logOut(View view){
        fba.signOut();
        finish();
    }

    public void addProduct(View view){
        Intent intent = new Intent(this, NewProductActivity.class);
        startActivityForResult(intent, ADD_PRODUCT);
    }

    public void dropBtnOnClick (View view){
        int pos = (int) view.getTag();
        products.remove(pos);
        dbProductsRef.setValue(products);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == ADD_PRODUCT){
            if(resultCode == Activity.RESULT_OK){
                String nombreRet = data.getStringExtra("nombre");
                String descRet = data.getStringExtra("descripcion");
                int cantidadRet = data.getIntExtra("cantidad", 0);
                double precioRet = data.getDoubleExtra("precio", 0);
                ProductContainer pRetrieved = new ProductContainer(nombreRet, descRet,  precioRet, cantidadRet);
                Log.d(TAG, "onActivityResult: product retrieved: " + pRetrieved.toString());
                products.add(pRetrieved);
                dbProductsRef.setValue(products);
            }
        }
    }
}
