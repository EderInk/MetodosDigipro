package com.example.pruebadigipro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digipro.fesdkcore.activity.BaseClass;
import com.digipro.fesdkcore.api.DGSDKDownloadSkin;
import com.digipro.fesdkcore.api.DGSDKLogin;
import com.digipro.fesdkcore.utils.Extension;
import com.digipro.fesdkcore.utils.NotifyBar;

import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private TextView txtCodigo;
    private Button btnValidar;
    boolean isCookie = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCodigo = findViewById(R.id.txtCodigo);
        btnValidar = findViewById(R.id.btnValidarCodigo);

        //Respuesta del servicio

        DGSDKLogin.handler = (loginResponse) -> {
            if (loginResponse.code == 200) {

                Intent siguiente = new Intent(this, Login.class);
                startActivity(siguiente);
            } else {
                Toast.makeText(this, loginResponse.message, Toast.LENGTH_LONG).show();
            }


        };

        }







    public void pasarActivity(View view) {
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DGSDKLogin.invoke(txtCodigo.getText().toString(), isCookie, getApplicationContext());
                Toast.makeText(getApplicationContext(),"Código válido",Toast.LENGTH_LONG).show();
            }
        });

    }
}
