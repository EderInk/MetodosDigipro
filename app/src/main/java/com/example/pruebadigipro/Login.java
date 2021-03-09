package com.example.pruebadigipro;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digipro.fesdkcore.activity.BaseClass;
import com.digipro.fesdkcore.api.DGSDKLogin;
import com.digipro.fesdkcore.repositorios.LogErrorRepository;
import com.digipro.fesdkcore.repositorios.VariableRepository;
import com.digipro.fesdkcore.utils.CustomException;
import com.digipro.fesdkcore.utils.Extension;

public class Login extends AppCompatActivity {
    private TextView txtUsuario, txtContraseña;
    private Button btnConectar;
    boolean isCookie = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_login);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnConectar = findViewById(R.id.btnConectar);

        DGSDKLogin.handler = (loginResponse) -> {
            if (loginResponse.code == 200) {

                Intent siguiente = new Intent(this, Expedientes.class);
                startActivity(siguiente);
            } else {
                Toast.makeText(this, loginResponse.message, Toast.LENGTH_LONG).show();
            }


        };

    }

    public void conectarUsuario(View view){
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DGSDKLogin.invoke(txtUsuario.getText().toString(),txtContraseña.getText().toString(),isCookie,getApplicationContext());
            }
        });
    }

}