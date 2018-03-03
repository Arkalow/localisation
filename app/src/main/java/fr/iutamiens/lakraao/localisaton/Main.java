package fr.iutamiens.lakraao.localisaton;

import android.content.Context;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main implements GPSListener, SMSReceiverListener{

    private GPS gps; //gps du téléphone
    private String num; //numéro de l'expéditeur
    private static Main self; //Instance unique de main

    /***
     * récupère l'instance unique de Main
     * @param context
     * @return
     */
    public static Main getSelf(Context context){
        if (self == null){
            self = new Main(context);
        }
        return self;
    }

    /***
     * Constructeur
     * @param context
     */
    private Main(Context context) {

        gps = new GPS(context);
        gps.addListener(this);
        SMSReceiver.addListener(this);
    }

    /***
     * Détection de la position GPS
     * @param location nouvelle position
     */
    @Override
    public void positionChanged(Location location) {
        StringBuilder str = new StringBuilder();
        str.append(location.getLatitude());
        str.append(", ");
        str.append(location.getLongitude());
        Log.d("Main", "Position changé");
        Log.d("Main", str.toString());
        gps.desabonnementGPS();
        Message message = new Message(num, str.toString());
        message.send();
    }

    /***
     * Détection d'un message
     * @param message message reçu
     */
    @Override
    public void receiveMessage(Message message) {
        Log.d("Main", "Code reçu");
        if (message.getCode().equals("location")){
            gps.abonnementGPS();
            num = message.getnum();
        }else{
            Log.e("Main", "Erreur code");
        }
    }
}
