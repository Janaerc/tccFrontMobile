package com.example.tccfrontmobileusuario.usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tccfrontmobileusuario.R;

public class SobreApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_app);
    }
//*********************************************************************************************************************************************
// fazer o código if (se usuario logado ir para o menu de usuario, se operario logado abrir menu de operario, se usuario deslocago abrir menu de usuario deslogado)
    //*******************************************************************************************************************************************
public void menuDeslogado(View view) {
    PopupMenu popup = new PopupMenu(this, view);
    popup.setOnMenuItemClickListener(this::onOptionsItemSelected);
    popup.inflate(R.menu.menu_deslogado);
    popup.show();
}


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.sobreApp:
                Intent intent = new Intent(SobreApp.this, SobreApp.class);
                startActivity(intent);
                finish();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }



    public void closeActivity (View view) {
        Intent intent = new Intent(SobreApp.this, MainActivity.class);
        startActivity(intent);
        finish();

    }















}