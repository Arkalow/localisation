package fr.iutamiens.lakraao.localisaton;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    private TextView log;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = findViewById(R.id.log);
    }
    @Override
    public void onResume() {
        super.onResume();
        log("onResume");
        //Obtention de la référence du service
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        //Si le GPS est disponible, on s'y abonne
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            abonnementGPS();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        log("onPause");
        //On appelle la méthode pour se désabonner
        desabonnementGPS();
    }

    /**
     * Méthode permettant de s'abonner à la localisation par GPS.
     */
    public void abonnementGPS() {
        //On s'abonne
        int permission = PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permission == PermissionChecker.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        log("abonnement");
    }

    /**
     * Méthode permettant de se désabonner de la localisation par GPS.
     */
    public void desabonnementGPS() {
        //Si le GPS est disponible, on s'y abonne
        locationManager.removeUpdates(this);
        log("desabonnement");
    }

    @Override
    public void onLocationChanged(final Location location) {
        //On affiche dans un Toat la nouvelle Localisation
        final StringBuilder msg = new StringBuilder("lat : ");
        msg.append(location.getLatitude());
        msg.append( "; lng : ");
        msg.append(location.getLongitude());
        log(msg.toString());
    }

    @Override
    public void onProviderDisabled(final String provider) {
        log("onProviderDisabled");
        //Si le GPS est désactivé on se désabonne
        if("gps".equals(provider)) {
            log("GPS désactivé");
            desabonnementGPS();
        }
    }

    @Override
    public void onProviderEnabled(final String provider) {
        log("onProviderEnabled");
        //Si le GPS est activé on s'abonne
        if("gps".equals(provider)) {
            log("GPS activé");
            abonnementGPS();
        }
    }

    @Override
    public void onStatusChanged(final String provider, final int status, final Bundle extras) { }

    private void log(String str){
        String msg = log.getText().toString() + "\n" + str;

        log.setText(msg);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        Log.d("Localisation", str);
    }
}
