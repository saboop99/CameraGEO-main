package com.facens.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


public class GPStracker implements LocationListener {
    Context context;
    //criação do método GPStracker com o "Context" como "c"
    public GPStracker(Context c){
        context = c;
    }

    //método criado para acessar a localização
    public Location getLocation(){
        //condicional para informar o usuário em caso da não permissão do acesso a localização
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
        PackageManager.PERMISSION_GRANTED){

            //mostrar ao usuário, em forma de texto, que o acesso não permitido
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //condicional para me dar as coordenadas caso o GPS esteja habilitado
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }
        else {
            //Texto pra mostrar ao usuário e informa-lo para habilitar o GPS, caso contrário o aplicativo não funcionará corretamente
            Toast.makeText(context, "Por favor, habitar o GPS!", Toast.LENGTH_LONG).show();
        }
        return null;
    }
    //Override(Sobrescrever), para um tipo de verificação caso o provedor esteja desabilitado
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //Override(Sobrescrever), para um tipo de verificação caso a localização tenha mudado
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //Override(Sobrescrever), para um tipo de verificação caso o Status do provedor tenha mudado
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }
}
