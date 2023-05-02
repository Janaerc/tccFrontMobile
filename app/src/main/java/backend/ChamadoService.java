package backend;

import model.ChamadoDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChamadoService {

    @POST("chamado")
    Call<ChamadoDTO> cadastrarChamado(@Body ChamadoDTO chamadoDTO);
}
