package com.facens.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView ImageViewFoto;
    private Button btnGeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        btnGeo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();

                if(l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE:" +lat+ "/nLONGITUDE:" +lon, Toast.LENGTH_LONG).show();
                }
            }

        });
        //Condicional para checagem da permissão do Android para o uso da Camera
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 0);
        }
        //utilizando o ImageView para adicionar imagem no app, setando para achar a imagem por Id
        ImageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener(){
            //Sobrescrever o código, acontece toda vez que se chama um método que está dentro de alguma outra classe, como forma de verificação após mudanças
            @Override
            public void onClick(View view){ tirarFoto();}
        });

    }
    //Criação do método para tirar a foto, e embaixo os comandos necessários para a captura da imagem
    private void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //Mais uma sobrescrição antes do método
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        //Condicional para "setar" a imagem como um bitmap, que define a imagem com base em pixels
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            ImageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}