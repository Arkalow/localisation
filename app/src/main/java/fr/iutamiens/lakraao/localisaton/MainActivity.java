package fr.iutamiens.lakraao.localisaton;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements GPSListener{

    private GPS gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gps = new GPS(this);
        gps.addListener(this);
    }

    public void receive(String sender, String code){
        gps.abonnementGPS();
    }

    @Override
    public void positionChanged(Location location) {
        Log.d("Main", "Position changé");
        Log.d("Main", "Latitude" + location.getLatitude());
        Log.d("Main", "Longitude" + location.getLongitude());
        gps.desabonnementGPS();
    }
}
