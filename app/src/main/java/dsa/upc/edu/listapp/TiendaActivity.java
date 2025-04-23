package dsa.upc.edu.listapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TiendaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        recyclerView = findViewById(R.id.recyclerProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(List.of()); // empezamos con lista vacía
        recyclerView.setAdapter(adapter);

        api = ApiClient.getClient().create(ApiService.class);

        cargarProductos();
    }

    private void cargarProductos() {
        api.getAllProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new MyAdapter(response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(TiendaActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show();
                    Log.e("TIENDA", "Código error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(TiendaActivity.this, "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TIENDA", "Error: " + t.getMessage());
            }
        });
    }
}
