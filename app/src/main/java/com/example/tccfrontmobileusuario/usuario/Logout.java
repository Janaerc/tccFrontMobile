package com.example.tccfrontmobileusuario.usuario;

import android.app.Activity;
import android.content.Intent;


public class Logout {

    private Activity activity;

    public Logout(Activity activity) {

        this.activity = activity;
    }

    public void logout() {

        //finaliza o app
        Intent intent = new Intent(activity, MainActivity.class);
        activity.finishAffinity();



    }
}