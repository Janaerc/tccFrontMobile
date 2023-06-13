package backend;

import model.ComentarioOperarioDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ComentarioOperarioService {

    @PUT("comentariooperario/{id}")
    Call<ComentarioOperarioDTO> redirecionar(@Path("id") Integer id, @Body ComentarioOperarioDTO comentarioOperarioDTO);
}
