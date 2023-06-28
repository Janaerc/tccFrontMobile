package backend;

import android.util.Log;

import model.LoginDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {


    @POST("usuario")
    Call<UsuarioDTO> cadastrarUsuario(@Body UsuarioDTO usuarioDTO);


    @POST("usuario/login")
    Call<UsuarioDTO> login(@Body LoginDTO loginDTO);

    @PUT("usuario/alterar/{id}")
    Call<UsuarioDTO> atualizaUsuario(@Path("id") int id, @Body UsuarioDTO usuario);
    @GET("usuario/recuperarSenha/{cpf}")
    Call<UsuarioDTO> recuperarSenha(@Path("cpf") int cpf);
    @PUT("usuario/{id}")
    Call<UsuarioDTO> atualizaUsuarioComSenha(@Path("id") int id, @Body UsuarioDTO usuario);
}
