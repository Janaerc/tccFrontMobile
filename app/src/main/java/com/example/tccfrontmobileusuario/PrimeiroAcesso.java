package com.example.tccfrontmobileusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PrimeiroAcesso extends AppCompatActivity {

    EditText nomeUsuario,telefoneUsuario, cpfUsuario, senhaUsuario, emailUsuario, senhaUsuario2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiro_acesso);


        nomeUsuario = findViewById(R.id.editTextName);
        telefoneUsuario = findViewById(R.id.editTextTelefone);
        cpfUsuario = findViewById(R.id.editTextCpf);
        emailUsuario = findViewById(R.id.editTextTextEmailAddress);
        senhaUsuario = findViewById(R.id.editTextPassword);
        senhaUsuario2 = findViewById(R.id.editTextPassword2);
    }

    public void salvarCadastro(View view){

        String senha = senhaUsuario.getText().toString();
        String senha2 = senhaUsuario2.getText().toString();



        if(senha != senha2){
            Toast.makeText(this, "As senhas não são iguais", Toast.LENGTH_SHORT).show();
            return;

        }



    }






}