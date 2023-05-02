package backend;

import java.util.List;

import model.PredioDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PredioService {

    @GET("predio/lista/{selectedCampusId}")
    Call<List<PredioDTO>> listarPredios(@Path("selectedCampusId") int selectedCampusId);
}
