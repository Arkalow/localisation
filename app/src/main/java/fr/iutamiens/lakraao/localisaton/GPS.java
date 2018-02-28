package fr.iutamiens.lakraao.localisaton;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.PermissionChecker;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class GPS implements LocationListener {
    private LocationManager locationManager;
    private Context context;
    private List<GPSListener> listeners;

    public GPS(Context context){
        this.context = context;
        listeners = new ArrayList<>();
        //Obtention de la référence du service
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
    }

    /**
     * Méthode permettant de s'abonner à la localisation par GPS.
     */
    public void abonnementGPS() {
        //On s'abonne
        int permission = PermissionChecker.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permission == PermissionChecker.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        Log.d("GPS", "abonnement");
    }

    /**
     * Méthode permettant de se désabonner de la localisation par GPS.
     */
    public void desabonnementGPS() {
        //Si le GPS est disponible, on s'y abonne
        locationManager.removeUpdates(this);
        Log.d("GPS", "desabonnement");
    }

    @Override
    public void onLocationChanged(final Location location) {
        for (GPSListener listener : listeners){
            listener.positionChanged(location);
        }
        /*final StringBuilder msg = new StringBuilder("lat : ");
        msg.append(location.getLatitude());
        msg.append( "; lng : ");
        msg.append(location.getLongitude());
        Log.d("GPS", msg.toString());*/
    }

    @Override
    public void onProviderDisabled(final String provider) {
        Log.d("GPS", "onProviderDisabled");
        //Si le GPS est désactivé on se désabonne
        if("gps".equals(provider)) {
            Log.d("GPS", "GPS désactivé");
            desabonnementGPS();
        }
    }

    @Override
    public void onProviderEnabled(final String provider) {
        Log.d("GPS", "onProviderEnabled");
        //Si le GPS est activé on s'abonne
        if("gps".equals(provider)) {
            Log.d("GPS", "GPS activé");
            abonnementGPS();
        }
    }

    @Override
    public void onStatusChanged(final String provider, final int status, final Bundle extras) { }


    public void addListener(GPSListener listener){
        listeners.add(listener);
    }
    public void remove(GPSListener listener){
        listeners.remove(listener);
    }
}
