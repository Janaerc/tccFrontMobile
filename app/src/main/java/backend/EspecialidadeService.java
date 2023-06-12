package backend;

import java.util.List;

import model.EspecialidadeDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EspecialidadeService {

    @GET("especialidade/lista")
    Call<List<EspecialidadeDTO>> listarEspecialidades();

}
