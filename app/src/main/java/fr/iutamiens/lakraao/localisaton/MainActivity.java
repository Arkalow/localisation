package fr.iutamiens.lakraao.localisaton;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements GPSListener, SMSReceiverListener{

    private GPS gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gps = new GPS(this);
        gps.addListener(this);
        SMSReceiver.addListener(this);
    }

    @Override
    public void positionChanged(Location location) {
        Log.d("Main", "Position changé");
        Log.d("Main", "Latitude" + location.getLatitude());
        Log.d("Main", "Longitude" + location.getLongitude());
        gps.desabonnementGPS();
    }

    @Override
    public void receiveMessage(Message message) {
        Log.d("Main", "Code reçu");
        if (message.getCode().equals("location")){
            gps.abonnementGPS();
        }else{
            Log.e("Main", "Erreur code");
        }
    }
}
