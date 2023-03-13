package backend;

import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioService {

    @POST("/UsuarioCadastro")
    Call<UsuarioDTO> cadastrarUsuario(@Body UsuarioDTO usuarioDTO);



}
