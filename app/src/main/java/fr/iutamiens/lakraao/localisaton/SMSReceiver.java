package fr.iutamiens.lakraao.localisaton;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/***
 * Class qui permet de détecter l'arrivé d'un message sur le telephone
 * Created by omer on 26/02/18.
 */
public class SMSReceiver extends BroadcastReceiver implements SMSReceiverListener{

    private final String ACTION_RECEIVE_SMS = "android.provider.Telephony.SMS_RECEIVED";
    private GPS gps;

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

                    if (message.getCode() != null){
                        Log.d("Message", message.getCode());

                    }else{
                        Log.e("Message", "Ce n'est pas un message codé");
                    }
                }
            }
        }
    }
}
