package backend;

import java.util.List;

import model.OrdemServicoDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrdemServicoService {

    @GET("ordemServico/listarTodos/{id}")
    Call<List<OrdemServicoDTO>> listaOrdememAberto(@Path("id")Integer id);

    @GET("ordemServico/listarMinhasOrdens/{id}")
    Call<List<OrdemServicoDTO>> listaMinhasOrdens(@Path("id")Integer id);

    @PUT("ordemservico/associarOS/{id}/{idUsuario}/{idChamado}")
    Call<OrdemServicoDTO> associarOS(@Path("id")Integer id, @Path ("idUsuario") Integer idUsuario);

    @PUT("ordemservico/redirecionarOS/{id}/{idEspecialidade}")
    Call<OrdemServicoDTO> redirecionarOS(@Path("id")Integer id, @Path("idEspecialidade") Integer idEspecialidade);

    @PUT("/ordemservico/VERIFICAR O QUE COLOCAR AQUI")
    Call<OrdemServicoDTO> redirecionar(OrdemServicoDTO ordemServicoId);
}
