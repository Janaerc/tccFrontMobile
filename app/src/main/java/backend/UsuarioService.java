package backend;

import android.util.Log;

import model.LoginDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioService {


    @POST("usuario")
    Call<UsuarioDTO> cadastrarUsuario(@Body UsuarioDTO usuarioDTO);


    @POST("usuario/login")


    Call<UsuarioDTO> login(@Body LoginDTO loginDTO);
}
