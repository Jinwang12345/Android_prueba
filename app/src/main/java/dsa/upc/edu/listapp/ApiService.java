package dsa.upc.edu.listapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @POST("usuarios")
    Call<Void> registerUser(@Body Usuario usuario);

    @POST("usuarios/login")
    Call<TokenResponse> loginUser(@Body Usuario usuario);

    @GET("tienda/productos")
    Call<List<Producto>> getAllProductos();

}
