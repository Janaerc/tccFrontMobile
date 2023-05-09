package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tccfrontmobileusuario.R;

import java.util.List;

import kotlin.jvm.internal.markers.KMutableList;
import model.CampusDTO;
import model.ChamadoDTO;
import model.PredioDTO;

public class ChamadoListAdapter extends RecyclerView.Adapter<ChamadoListAdapter.MyViewHolder>{
    private final List<ChamadoDTO> list;



    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView numeroChamado, nomePredio, descricaoLocal, nomeCampus;


        public MyViewHolder(@NonNull View view){

            super(view);

            numeroChamado = view.findViewById(R.id.txtNumeroChamado);
            nomeCampus = view.findViewById(R.id.txtNomeCampus);
            nomePredio = view.findViewById(R.id.txtNomePredio);
            descricaoLocal = view.findViewById(R.id.txtDescricaoLocal);

        }
    }

    public ChamadoListAdapter(List<ChamadoDTO> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pokemonItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.chamado_item, parent, false);
        return new MyViewHolder(pokemonItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChamadoDTO chamadoDTO = list.get(position);
        holder.numeroChamado.setText(chamadoDTO.getId().toString());
        CampusDTO campusDTO;
        campusDTO = chamadoDTO.getPredioId().getCampusId();
        holder.nomeCampus.setText(campusDTO.getNome());
        PredioDTO predioDTO;
        predioDTO = chamadoDTO.getPredioId();
        holder.nomePredio.setText(predioDTO.getNome());
        holder.descricaoLocal.setText(chamadoDTO.getDescricaoLocal());

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

}
