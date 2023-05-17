package backend;

import java.util.List;

import model.ChamadoDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChamadoService {

    @POST("chamado")
    Call<ChamadoDTO> cadastrarChamado(@Body ChamadoDTO chamadoDTO);

    @GET("chamado/listaChamados/{idUsuario}")
    Call<List<ChamadoDTO>> listaDeChamados(@Path("idUsuario") int idUsuario);

    @GET("chamado/chamadoId/{id}")
    Call<ChamadoDTO> chamadoPorId (@Path("id") int id);

    @DELETE("chamado/{id}")
    Call<ChamadoDTO> excluirChamado(@Path("id") int id);
}
