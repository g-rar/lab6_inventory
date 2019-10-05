package com.example.lab6_inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;

import java.util.List;

public class SwipeProductAdapter extends BaseSwipeAdapter {

    private Context context;
    private List<ProductContainer> productsListData;
    private LayoutInflater layoutInflater;

    public SwipeProductAdapter (Context context, List<ProductContainer> data){
        this.context = context;
        this.productsListData = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return layoutInflater.inflate(R.layout.swipe_listrow_item, null);
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView cantidad = convertView.findViewById(R.id.SwItemTextView_cantidad);
        TextView descripcion = convertView.findViewById(R.id.SwItemTextView_description);
        TextView nombre = convertView.findViewById(R.id.SwItemTextView_productName);
        TextView precio = convertView.findViewById(R.id.SwItemTextView_precio);
        precio.setText(Double.toString(productsListData.get(position).getPrecio()));
        nombre.setText(productsListData.get(position).getNombre());
        String descripcionStr = productsListData.get(position).getDescripcion();
        descripcion.setText(descripcionStr.length() > 30 ? descripcionStr.substring(0, 27) + "..." : descripcionStr);
        cantidad.setText(Integer.toString(productsListData.get(position).getCantidad()));
        Button button = convertView.findViewById(R.id.SwBtn_Delete);
        button.setTag(position);
    }

    @Override
    public int getCount() {
        return productsListData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        TextView nombre;
        TextView descripcion;
        TextView precio;
        TextView cantidad;
    }

}
