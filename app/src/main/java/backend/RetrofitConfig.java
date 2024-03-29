package backend;

import model.UsuarioDTO;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;


    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/manutencaoufpr/webresources/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UsuarioService getUsuarioService() {
        return this.retrofit.create(UsuarioService.class);
    }

    public CampusService getCampusService() {return this.retrofit.create(CampusService.class);}

    public PredioService getPredioService() {return this.retrofit.create(PredioService.class);}

    public ChamadoService getChamadoService() {return this.retrofit.create(ChamadoService.class);}

    public ComentarioService getComentarioService() {return this.retrofit.create(ComentarioService.class);}

    public OrdemServicoService getOdemServicoService() {return this.retrofit.create(OrdemServicoService.class);}

    public EspecialidadeService getEspecidadeService() {return this.retrofit.create(EspecialidadeService.class);}

    public ComentarioOperarioService getComentarioOperarioService() {return this.retrofit.create(ComentarioOperarioService.class);}

}
