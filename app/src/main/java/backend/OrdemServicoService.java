package backend;

import java.util.List;

import model.OrdemServicoDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrdemServicoService {

    @GET("ordemServico/listarTodos/{id}")
    Call<List<OrdemServicoDTO>> listaOrdememAberto(@Path("id")Integer id);

    @GET("ordemServico/listarMinhasOrdens/{id}")
    Call<List<OrdemServicoDTO>> listaMinhasOrdens(@Path("id")Integer id);
}
