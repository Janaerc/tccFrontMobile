package backend;

import java.util.List;

import model.ComentarioOperarioDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComentarioService {

    @GET("alterar o nome/{ordemServicoId}")
    Call<List<ComentarioOperarioDTO>> listarComentarios(@Path("ordemServicoId") int ordemServicoId);
}
