package dsa.upc.edu.listapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.*;

public class RetrofitActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        recyclerView = findViewById(R.id.recyclerProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = ApiClient.getClient().create(ApiService.class);
        cargarProductos();
    }

    private void cargarProductos() {
        apiService.getAllProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    adapter = new MyAdapter(response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(RetrofitActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                Log.e("RetrofitActivity", "Error: ", t);
            }
        });
    }
}
