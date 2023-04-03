package backend;

import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioService {

    @POST("/usuario")
    Call<UsuarioDTO> cadastrarUsuario(@Body UsuarioDTO usuarioDTO);

}
