package com.example.lab6_inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomProductAdapter extends BaseAdapter {

    private List<ProductContainer> productsListData;
    private LayoutInflater layoutInflater;

    public CustomProductAdapter(Context context, List<ProductContainer> products) {
        this.productsListData = products;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.listrow_item, null);
            holder = new ViewHolder();
            holder.cantidad = convertView.findViewById(R.id.SwItemTextView_cantidad);
            holder.descripcion = convertView.findViewById(R.id.SwItemTextView_description);
            holder.nombre = convertView.findViewById(R.id.SwItemTextView_productName);
            holder.precio = convertView.findViewById(R.id.SwItemTextView_precio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.precio.setText(Double.toString(productsListData.get(position).getPrecio()));
        holder.nombre.setText(productsListData.get(position).getNombre());
        String descripcion = productsListData.get(position).getDescripcion();
        holder.descripcion.setText(descripcion.length() > 30 ? descripcion.substring(0, 27) + "..." : descripcion);
        holder.cantidad.setText(Integer.toString(productsListData.get(position).getCantidad()));
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return productsListData.get(position);
    }

    @Override
    public int getCount() {
        return productsListData.size();
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
