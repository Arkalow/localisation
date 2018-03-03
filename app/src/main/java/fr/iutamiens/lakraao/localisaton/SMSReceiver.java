package fr.iutamiens.lakraao.localisaton;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/***
 * Class qui permet de détecter l'arrivé d'un message sur le telephone
 * Created by omer on 26/02/18.
 */
public class SMSReceiver extends BroadcastReceiver {

    private final String ACTION_RECEIVE_SMS = "android.provider.Telephony.SMS_RECEIVED";
    private GPS gps;
    private static List<SMSReceiverListener> listeners = listeners = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_RECEIVE_SMS))
        {
            Bundle bundle = intent.getExtras();
            if (bundle != null)
            {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];

                for (int i = 0; i < pdus.length; i++)  {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                if (messages.length > -1){

                    Message message = new Message(
                            messages[0].getDisplayOriginatingAddress(),
                            messages[0].getMessageBody()
                    );
                    //Toast.makeText(context, "Reception message", Toast.LENGTH_SHORT).show();

                    if (message.getCode() != null){
                        Log.d("Message", message.getCode());
                        for (SMSReceiverListener listener : listeners){
                            listener.receiveMessage(message);
                        }

                    }else{
                        Log.e("Message", "Ce n'est pas un message codé");
                    }
                }
            }
        }
    }
    public static void addListener(SMSReceiverListener listener){
        listeners.add(listener);
    }
    public static void remove(SMSReceiverListener listener){
        listeners.remove(listener);
    }
}
