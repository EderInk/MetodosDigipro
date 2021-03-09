package com.example.pruebadigipro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.digipro.fesdkcore.FeSdkCore;
import com.digipro.fesdkcore.activity.BaseClass;
import com.digipro.fesdkcore.api.DGSDKDownloadFormat;
import com.digipro.fesdkcore.api.DGSDKDownloadTemplate;
import com.digipro.fesdkcore.api.DGSDKDownloadVariable;
import com.digipro.fesdkcore.api.DGSDKLoadTemplate;
import com.digipro.fesdkcore.api.responses.DGSDKDownloadFormatResponse;
import com.digipro.fesdkcore.dto.FEFormatoData;
import com.digipro.fesdkcore.dto.FEPlantillaData;
import com.digipro.fesdkcore.repositorios.PlantillaRepository;

import java.util.ArrayList;
import java.util.List;

public class Expedientes extends AppCompatActivity {
    private Button btnDesVariables, btnDesFormatos, btnDesPlantillas;
    private ListView lvExpedientes;
    private EditText txtDatos;
    private Activity activity;
    private Context contexto;

    private List<String> items = new ArrayList<>();
    private List<FEPlantillaData> itemsPlantillaData = new ArrayList<>();
    private ArrayAdapter mAdapter;


    boolean isCookie = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expedientes);
        activity = this;
        contexto = this;
        btnDesVariables = findViewById(R.id.btnDesVariables);
        btnDesFormatos = findViewById(R.id.btnDesFormatos);
        btnDesPlantillas = findViewById(R.id.btnDesPlantillas);


        lvExpedientes = findViewById(R.id.lvExpedientes);
        DGSDKLoadTemplate.handler = new DGSDKLoadTemplate.LoadFormatoOnFinishAllFCallback() {
            @Override
            public void onFinishFormat_Cancelado(String guid, String mensaje) {
                Toast.makeText(contexto,"Se cancela el formato" + "-" + guid, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinishFormat_Borrador(String guid, String mensaje) {
                Toast.makeText(contexto,"Se manda a borrador" + "-" + guid, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinishFormat_Publicar(String guid, String mensaje) {
                Toast.makeText(contexto,"Se publica el formato" + "-" + guid, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinishFormat_WaitUIFromUser(String guid, String mensaje) {

            }

        };



        DGSDKDownloadFormat.handler = (downloadFormatResponse) -> {
            if (downloadFormatResponse.code == 200) {
                Toast.makeText(this, "Carga correcta de formatos", Toast.LENGTH_LONG).show();
                return;
            }

        };

        DGSDKDownloadVariable.handler = (variableResponse) -> {
            if (variableResponse.code == 200) {
                Toast.makeText(this, "Carga correcta de Variables", Toast.LENGTH_LONG).show();
                return;

            }

        };

        DGSDKDownloadTemplate.handler = (downloadTemplateResponse) -> {
            if (downloadTemplateResponse.code == 200) {
                Toast.makeText(this, "Carga de plantillas correctas", Toast.LENGTH_LONG).show();
                try {
                    BaseClass.listPlantillas = PlantillaRepository.GetPlantillasData(getApplicationContext());
                    BaseClass.plantillaSaved = PlantillaRepository.GetPlantilla(getApplicationContext());
                    BaseClass.listConsultas = FeSdkCore.usuarioSaved.Consultas;
                    itemsPlantillaData = BaseClass.listPlantillas;

                    for (FEPlantillaData plantillaData : BaseClass.listPlantillas) {

                        items.add(plantillaData.NombreExpediente + " - " + plantillaData.NombreTipoDoc);
                    }


                    mAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, items);

                    lvExpedientes.setAdapter(mAdapter);
                    lvExpedientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(Expedientes.this, itemsPlantillaData.get(position) + " - " + position, Toast.LENGTH_LONG).show();
                            FEPlantillaData plantillaSeleccionada;
                            plantillaSeleccionada = itemsPlantillaData.get(position);
                            DGSDKLoadTemplate.invoke(plantillaSeleccionada,activity);
                            //DGSDKLoadTemplate.invoke(plantillaSeleccionada,(Activity) contexto);

                        }
                    });
                    lvExpedientes.setBackgroundColor(Color.WHITE);
                    lvExpedientes.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

                } catch (Exception ex) {

                }

                return;
            }

        };
    }

    public void descargarFormatos(View view) {
        btnDesFormatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DGSDKDownloadFormat.invoke(isCookie, getBaseContext());
                Toast.makeText(getApplicationContext(), "Descargando formatos", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void descargarVariables(View view) {
        btnDesVariables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DGSDKDownloadVariable.invoke(getBaseContext());
                Toast.makeText(getApplicationContext(), "Descargando Variables", Toast.LENGTH_LONG).show();


            }
        });
    }

    public void descargarPlantillas(View view) {
        btnDesPlantillas.setOnClickListener(v -> {
            DGSDKDownloadTemplate.invoke(isCookie, getBaseContext());
            Toast.makeText(getApplicationContext(), "Descargando Plantillas", Toast.LENGTH_LONG).show();


        });
    }


}