package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import backend.RetrofitConfig;
import bean.Campus;
import bean.Predio;
import model.CampusDTO;
import model.ChamadoDTO;
import model.PredioDTO;
import model.StatusDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NovoChamado extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    private Spinner campusSpinner;
    private Spinner predioSpinner;

    private List<Campus> campusList = new ArrayList<>();
    private List<Predio> predioList = new ArrayList<>();

    private ArrayAdapter<Campus> adapter;

    private ArrayAdapter<Predio> adapter1;

    private int selectedCampusId;

    private int campusId, predioId;

    private List<PredioDTO> predioDTO;


    TextView localizacao = null, problema = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");

        campusSpinner = findViewById(R.id.campusSpinner);
        predioSpinner = findViewById(R.id.predioSpinner);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, campusList);
        adapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        campusSpinner.setAdapter(adapter);

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, predioList);
        adapter1.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        predioSpinner.setAdapter(adapter1);

        localizacao = findViewById(R.id.descricao_localizacao_editText);
        problema = findViewById(R.id.descricao_problema_editText);


        campusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                campusId = position + 1;
                System.out.println("aqui está o id do campus");
                System.out.println(campusId);
                Campus campus = (Campus) parent.getItemAtPosition(position);
                selectedCampusId = campus.getId();
                loadPredios(selectedCampusId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        predioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                predioId = position + 1;
                System.out.println("aqui está o id do predio");
                System.out.println(predioId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadCampus();
    }

    private void loadCampus() {
        Call<List<CampusDTO>> call = new RetrofitConfig().getCampusService().listarCampos(); //fazer o service e arrumar a consulta
        call.enqueue(new Callback<List<CampusDTO>>() {
            @Override
            public void onResponse(Call<List<CampusDTO>> call, Response<List<CampusDTO>> response) {
                List<CampusDTO> campusDTO = response.body();
                for (CampusDTO c : campusDTO) {
                    Campus campus = new Campus();
                    campus.setId(c.getId());
                    campus.setNome(c.getNome());
                    campusList.add(campus);
                }

                //verificar como colocar no spinner
                //campusList.clear();
                //campusList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CampusDTO>> call, Throwable t) {
                Toast.makeText(NovoChamado.this, "Erro ao buscar os campus", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPredios(int selectedCampusId) {
        Call<List<PredioDTO>> call = new RetrofitConfig().getPredioService().listarPredios((selectedCampusId)); //fazer o service e arrumar a consulta
        call.enqueue(new Callback<List<PredioDTO>>() {
            @Override
            public void onResponse(Call<List<PredioDTO>> call, Response<List<PredioDTO>> response) {
                predioDTO = response.body();
                predioList.clear();
                if (predioDTO != null) {
                    for (PredioDTO c : predioDTO) {
                        System.out.println(c.getNome());
                        Predio predio = new Predio();
                        predio.setId(c.getId());
                        predio.setNome(c.getNome());
                        predioList.add(predio);
                    }
                }

                //predioList.addAll(response.body());
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PredioDTO>> call, Throwable t) {
                Toast.makeText(NovoChamado.this, "Erro ao buscar os prédios", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cadastrarChamado(View view) throws ParseException {

        ChamadoDTO chamado = new ChamadoDTO();
        chamado.setDescricaoLocal(localizacao.getText().toString());
        chamado.setDescricaoProblema(problema.getText().toString());
        PredioDTO preaux = new PredioDTO();
        preaux.setId(predioId);
        chamado.setPredioId(preaux);
        chamado.setUsuarioId(usuarioDTO);
        StatusDTO status = new StatusDTO();
        status.setId(2);
        chamado.setStatusId(status);
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = agora.format(formato);
        SimpleDateFormat formatoTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatoTimestamp.parse(timestamp);
        //chamado.setDataHora(date);
        //verificar o problema do status

        if (chamado.getDescricaoLocal().isEmpty() || chamado.getDescricaoProblema().isEmpty()) {
            Toast.makeText(NovoChamado.this, "Local e problema devem ser preenchidos", Toast.LENGTH_SHORT).show();
        } else {
            Call<ChamadoDTO> call = new RetrofitConfig().getChamadoService().cadastrarChamado(chamado);
            call.enqueue(new Callback<ChamadoDTO>() {
                @Override
                public void onResponse(Call<ChamadoDTO> call, Response<ChamadoDTO> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(NovoChamado.this, "Chamado Cadastrado", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(NovoChamado.this, HomepageUsuario.class);
                        it.putExtra("usuario", usuarioDTO);
                        startActivity(it);
                    }
                }

                @Override
                public void onFailure(Call<ChamadoDTO> call, Throwable t) {

                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sobreAppUsuarioLogado:
                Intent intent = new Intent(NovoChamado.this, SobreAppLogadoUsuario.class);
                intent.putExtra("usuario", usuarioDTO);
                startActivity(intent);
                finish();
                return true;
            case R.id.cadastro:
                Intent intent2 = new Intent(NovoChamado.this, Cadastro.class);
                intent2.putExtra("usuario", usuarioDTO);
                startActivity(intent2);
                finish();
                return true;
            case R.id.logout:
                Logout logout = new Logout(this);
                logout.logout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void voltarHome(View view) {
        Intent intent = new Intent(NovoChamado.this, HomepageUsuario.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
    }

}