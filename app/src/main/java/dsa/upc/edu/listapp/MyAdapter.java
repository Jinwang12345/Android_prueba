package dsa.upc.edu.listapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<Producto> productos;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, precio;

        public ViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.productName);
            precio = v.findViewById(R.id.productPrice);
        }
    }

    public MyAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_producto, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Producto p = productos.get(position);
        holder.nombre.setText(p.getNombre());
        holder.precio.setText("Precio: " + p.getPrecio() + "€");
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
}
