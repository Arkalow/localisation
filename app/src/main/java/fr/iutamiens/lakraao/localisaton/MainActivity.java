package fr.iutamiens.lakraao.localisaton;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria critere = new Criteria();

        // Pour indiquer la précision voulue
        // On peut mettre ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision
                critere.setAccuracy(Criteria.ACCURACY_FINE);

        // Est-ce que le fournisseur doit être capable de donner une altitude ?
                critere.setAltitudeRequired(false);

        // Est-ce que le fournisseur doit être capable de donner une direction ?
                critere.setBearingRequired(false);

        // Est-ce que le fournisseur peut être payant ?
                critere.setCostAllowed(false);

        // Pour indiquer la consommation d'énergie demandée
        // Criteria.POWER_HIGH pour une haute consommation, Criteria.POWER_MEDIUM pour une consommation moyenne et Criteria.POWER_LOW pour une basse consommation
                critere.setPowerRequirement(Criteria.POWER_HIGH);

        // Est-ce que le fournisseur doit être capable de donner une vitesse ?
                critere.setSpeedRequired(false);

        String provider = locationManager.getBestProvider(critere, true);
        Log.d("Location", provider);

        //locationManager.requestLocationUpdates(provider, 5000, 10000, new Loca)
        Location location = locationManager.getLastKnownLocation(provider);
        Log.d("location", location.getLatitude()+"");
        Log.d("location", location.getLongitude()+"");
    }
}
