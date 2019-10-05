package com.example.lab6_inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    TextView nombreTV;
    TextView descripcionTV;
    TextView cantidadTV;
    TextView precioTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        nombreTV = findViewById(R.id.productATextView_nombre);
        descripcionTV = findViewById(R.id.productATextView_description);
        cantidadTV = findViewById(R.id.productATextView_cantidad);
        precioTV = findViewById(R.id.productATextView_precio);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        String precio = Double.toString(intent.getDoubleExtra("precio", 0));
        String cantidad = Integer.toString(intent.getIntExtra("cantidad",0));

        nombreTV.setText(nombre);
        descripcionTV.setText(descripcion);
        cantidadTV.setText(cantidad);
        precioTV.setText(precio);

    }
}
