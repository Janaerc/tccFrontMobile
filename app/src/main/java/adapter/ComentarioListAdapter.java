package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tccfrontmobileusuario.R;

import java.util.List;

import model.ComentarioOperarioDTO;

public class ComentarioListAdapter extends RecyclerView.Adapter<ComentarioListAdapter.MyViewHolder> {

private final List<ComentarioOperarioDTO> list;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView data, comentario;

    public MyViewHolder (@NonNull View view) {
        super(view);
        data = view.findViewById(R.id.txtDataeHora);
        comentario = view.findViewById(R.id.txtDescricaoStatus);
    }
}
public ComentarioListAdapter(List<ComentarioOperarioDTO> list) {this.list = list;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    View comentariosHistorio = LayoutInflater.from(parent.getContext()).inflate(R.layout.statushistorico,parent, false);
    return new MyViewHolder(comentariosHistorio);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    ComentarioOperarioDTO comentarioDTO = list.get(position);
    holder.data.setText(comentarioDTO.getDataHora().toString());
    holder.comentario.setText(comentarioDTO.getDescricao());
    }

    @Override
    public int getItemCount(){return this.list.size();}
}
