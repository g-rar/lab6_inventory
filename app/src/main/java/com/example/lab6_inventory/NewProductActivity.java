package com.example.lab6_inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewProductActivity extends AppCompatActivity {

    EditText nombreET;
    EditText descripcionET;
    EditText precioET;
    EditText cantidadET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        nombreET = findViewById(R.id.editText_nombre);
        descripcionET = findViewById(R.id.editText_descripcion);
        precioET = findViewById(R.id.editText_precio);
        cantidadET = findViewById(R.id.editText_cantidad);
    }

    public void createProductOnClick(View view){
        String nombre = nombreET.getText().toString();
        String descripcion = descripcionET.getText().toString();
        String precioStr = precioET.getText().toString();
        String cantidadStr = cantidadET.getText().toString();
        if(nombre.equals("") || descripcion.equals("") || precioStr.equals("") || cantidadStr.equals("")){
            Toast.makeText(this, R.string.str_fillAll, Toast.LENGTH_LONG);
            return;
        }
        double precio = Double.parseDouble(precioStr);
        int cantidad = Integer.parseInt(cantidadStr);
        Intent intent = getIntent();
        intent.putExtra("nombre",nombre);
        intent.putExtra("descripcion",descripcion);
        intent.putExtra("precio",precio);
        intent.putExtra("cantidad",cantidad);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
