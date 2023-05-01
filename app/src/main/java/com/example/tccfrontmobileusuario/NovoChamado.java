package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import backend.RetrofitConfig;
import model.CampusDTO;
import model.PredioDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class NovoChamado extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    private Spinner campusSpinner;
    private Spinner predioSpinner;

    private List<Campus> campusList;
    private List<Predio> predioList;

    private ArrayAdapter<Campus> campusAdapter;
    private ArrayAdapter<Predio> predioAdapter;

    private int selectedCampusId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");

        campusSpinner = findViewById(R.id.campusSpinner);
        predioSpinner = findViewById(R.id.predioSpinner);

        campusList = new ArrayList<>();
        predioList = new ArrayList<>();

        campusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, campusList);
        campusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campusSpinner.setAdapter(campusAdapter);

        predioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, predioList);
        predioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        predioSpinner.setAdapter(predioAdapter);

        campusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Campus campus = (Campus) parent.getItemAtPosition(position);
                selectedCampusId = campus.getId();
                loadPredios();
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
                System.out.println(campusDTO);
                //verificar como colocar no spinner
                //campusList.clear();
                //campusList.addAll(response.body());
                campusAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CampusDTO>> call, Throwable t) {
                Toast.makeText(NovoChamado.this, "Erro ao buscar os campus", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPredios() {
        Call<List<PredioDTO>> call = new RetrofitConfig().getPredioService().listarPredios(selectedCampusId); //fazer o service e arrumar a consulta
        call.enqueue(new Callback<List<PredioDTO>>() {
            @Override
            public void onResponse(Call<List<PredioDTO>> call, Response<List<PredioDTO>> response) {
                List<PredioDTO> predioDTO = response.body();
                System.out.println(predioDTO);
                //predioList.clear();
                //predioList.addAll(response.body());
                predioAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PredioDTO>> call, Throwable t) {
                Toast.makeText(NovoChamado.this, "Erro ao buscar os prédios", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobreApp:
                Intent intent = new Intent(NovoChamado.this, SobreApp.class);
                startActivity(intent);
                finish();
            case R.id.cadastro:
                Intent intent2 = new Intent(NovoChamado.this, Cadastro.class);
                startActivity(intent2);
                finish();
            case R.id.logout:
                Toast.makeText(NovoChamado.this, "Implementar logout", Toast.LENGTH_SHORT).show();



        }


        return super.onOptionsItemSelected(item);
    }









}