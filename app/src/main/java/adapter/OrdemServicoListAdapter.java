package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tccfrontmobileusuario.R;

import java.util.List;

import model.CampusDTO;
import model.OrdemServicoDTO;
import model.PredioDTO;

public class OrdemServicoListAdapter extends RecyclerView.Adapter<OrdemServicoListAdapter.MyViewHolder>{


    private final List<OrdemServicoDTO> list;



    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView numeroChamado, nomePredio, descricaoLocal, nomeCampus, statusChamado;


        public MyViewHolder(@NonNull View view){

            super(view);

            numeroChamado = view.findViewById(R.id.txtNumeroChamado);
            nomeCampus = view.findViewById(R.id.txtNomeCampus);
            nomePredio = view.findViewById(R.id.txtNomePredio);
            descricaoLocal = view.findViewById(R.id.txtDescricaoLocal);
            statusChamado = view.findViewById(R.id.txtStatusChamado);
        }
    }

    public OrdemServicoListAdapter(List<OrdemServicoDTO> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chamadoItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.chamado_item, parent, false);
        return new MyViewHolder(chamadoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdemServicoListAdapter.MyViewHolder holder, int position) {
        System.out.println("entrou no on bind view holder");
        System.out.println(list);
        OrdemServicoDTO ordemServicoDTO = list.get(position);

        holder.statusChamado.setText(ordemServicoDTO.getChamado().getStatusId().getNome());


        CampusDTO campusDTO;
        campusDTO = ordemServicoDTO.getChamado().getPredioId().getCampusId();
        holder.nomeCampus.setText(campusDTO.getNome());

        PredioDTO predioDTO;
        predioDTO = ordemServicoDTO.getChamado().getPredioId();
        holder.nomePredio.setText(predioDTO.getNome());

        holder.descricaoLocal.setText(ordemServicoDTO.getChamado().getDescricaoLocal());
        System.out.println(holder.descricaoLocal);

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
