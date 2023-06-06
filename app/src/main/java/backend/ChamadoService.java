package backend;

import java.util.List;

import bean.Chamado;
import model.ChamadoDTO;
import model.OrdemServicoDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChamadoService {

    @POST("chamado")
    Call<ChamadoDTO> cadastrarChamado(@Body ChamadoDTO chamadoDTO);

    @GET("chamado/listaChamados/{idUsuario}")
    Call<List<ChamadoDTO>> listaDeChamados(@Path("idUsuario") int idUsuario);

    @GET("chamado/listaChamadosEmAberto/{idUsuario}")
    Call<List<ChamadoDTO>> listaChamadosEmAberto(@Path("idUsuario") int idUsuario);

    @GET("chamado/listaMeusChamados/{idUsuario}")
    Call<List<ChamadoDTO>> listaMeusChamados(@Path("idUsuario") int idUsuario);

    @GET("chamado/chamadoId/{id}")
    Call<ChamadoDTO> chamadoPorId (@Path("id") int id);

    @DELETE("chamado/{id}")
    Call<ChamadoDTO> excluirChamado(@Path("id") int id);

    @PUT("chamado/associar/{idChamado}")
    Call<ChamadoDTO> associarChamado(@Path("idChamado") int idChamado,@Body ChamadoDTO chamado);
}
