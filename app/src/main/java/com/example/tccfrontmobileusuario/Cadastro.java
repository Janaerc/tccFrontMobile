package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import backend.RetrofitConfig;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity {
    UsuarioDTO usuarioDTO;

    EditText nomeUsuario, telefoneUsuario, cpfUsuario, senhaUsuario, emailUsuario, senhaUsuario2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");


        nomeUsuario = findViewById(R.id.editTextName);
        telefoneUsuario = findViewById(R.id.editTextTelefone);
        cpfUsuario = findViewById(R.id.editTextCpf);
        emailUsuario = findViewById(R.id.editTextEmailAddress);
        senhaUsuario = findViewById(R.id.editTextPassword);
        senhaUsuario2 = findViewById(R.id.editTextPassword2);

        nomeUsuario.setText(usuarioDTO.getNome());
        telefoneUsuario.setText(usuarioDTO.getTelefone());
        cpfUsuario.setText(usuarioDTO.getCpf());
        emailUsuario.setText(usuarioDTO.getEmail());

    }

    public void atualizarCadastro(View view){

        UsuarioDTO usuario = new UsuarioDTO();

        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setEmail(usuarioDTO.getEmail());
        String senha = senhaUsuario.getText().toString();
        String senha2 = senhaUsuario2.getText().toString();

        // VALIDAÇÃO DOS CAMPOS
        if (usuario.getNome().isEmpty()){
            Toast.makeText(this, "Preencha um Nome Válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (usuario.getTelefone().isEmpty()){
            Toast.makeText(this, "Preencha um Telefone Válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (usuario.getEmail().isEmpty()){
            Toast.makeText(this, "Preencha um E-mail Válido", Toast.LENGTH_SHORT).show();
            return;
        }





        //CASO: NÃO ATUALIZAR A SENHA

        if (senha.isEmpty()|| senha.isEmpty()) {

            Call<UsuarioDTO> call1 = new RetrofitConfig().getUsuarioService().atualizaUsuario(usuario.getId(),usuario);
            call1.enqueue(new Callback<UsuarioDTO>() {
                @Override
                public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(Cadastro.this, "Cadastro Atualizado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Cadastro.this, HomepageUsuario.class);
                        intent.putExtra("usuario", usuarioDTO);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Cadastro.this, "Erro ao Atualizar Cadastro ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Cadastro.this, Cadastro.class);
                        intent.putExtra("usuario", usuarioDTO);
                        startActivity(intent);

                    }
                }

                @Override
                public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                        Toast.makeText(Cadastro.this, "Cadastro não atualizado ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Cadastro.this, Cadastro.class);
                        intent.putExtra("usuario", usuarioDTO);
                        startActivity(intent);
                }
            });
        }
        else {
            //CASO: ATUALIZAR A SENHA
            if(senha.equals(senha2)) {
                usuario.setSenha(usuarioDTO.getSenha());
                Call<UsuarioDTO> call1 = new RetrofitConfig().getUsuarioService().atualizaUsuario(usuario.getId(),usuario);
                call1.enqueue(new Callback<UsuarioDTO>() {
                    @Override
                    public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(Cadastro.this, "Cadastro Atualizado2", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Cadastro.this, HomepageUsuario.class);
                            intent.putExtra("usuario", usuarioDTO);
                            startActivity(intent);

                        } else {
                            Toast.makeText(Cadastro.this, "Erro ao Atualizar Cadastro2 ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Cadastro.this, Cadastro.class);
                            intent.putExtra("usuario", usuarioDTO);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                        Toast.makeText(Cadastro.this, "Cadastro não atualizado2 ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Cadastro.this, Cadastro.class);
                        intent.putExtra("usuario", usuarioDTO);
                        startActivity(intent);
                    }
                });



            }
            else {
                Toast.makeText(this, "As senhas não são iguais", Toast.LENGTH_SHORT).show();
                return;

                }
            }
        }













// ***********************************MENU DE OPÇÕES ***********************************
    public void menuUsuario(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this::onOptionsItemSelected);
        popup.inflate(R.menu.menu_usuario);
        popup.show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobreAppUsuarioLogado:
                Intent intent = new Intent(Cadastro.this, SobreAppLogadoUsuario.class);
                intent.putExtra("usuario", usuarioDTO);
                startActivity(intent);
                finish();
                return true;
            case R.id.cadastro:
                Intent intent2 = new Intent(Cadastro.this, Cadastro.class);
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


    public void closeActivity (View view) {
        Intent intent = new Intent(Cadastro.this, HomepageUsuario.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
        finish();

    }




}