package backend;

import java.util.List;

import model.ComentarioOperarioDTO;
import model.OrdemServicoDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ComentarioService {

    @GET("comentariooperario/listarComentarioPorIdOS/{ordemServicoId}")
    Call<List<ComentarioOperarioDTO>> listarComentarios(@Path("ordemServicoId") int ordemServicoId);
    @POST("comentariooperario")
    Call<OrdemServicoDTO> inserirComentario(@Body ComentarioOperarioDTO comentarioOperarioDTO);
}
