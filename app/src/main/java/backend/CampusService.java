package backend;



import java.util.List;

import model.CampusDTO;
import retrofit2.Call;
import retrofit2.http.GET;


public interface CampusService {

    @GET("campus/lista")
    Call<List<CampusDTO>> listarCampos();
}
