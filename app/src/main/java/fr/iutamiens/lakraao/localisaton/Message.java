package fr.iutamiens.lakraao.localisaton;

import android.telephony.SmsManager;
import android.util.Log;

/***
 * Represent a message
 * Created by omer on 27/02/18.
 */
public class Message {

    private String content;
    private String num;
    private String code;
    private final String head = "Code : ";

    /***
     * Message's construct
     * @param content message's content
     * @param num message's num
     */
    public Message(String num, String content){
        this.content = content;
        this.num = num;
        if (content.length() > head.length() && content.substring(0, head.length()).equals(head)){
            code = content.substring(head.length(), content.length());
        }else{
            code = null;
        }
    }

    /***
     * Return the message's content
     * @return message's content
     */
    public String getCode(){
        return code;
    }

    /***
     * Return the message's content
     * @return message's content
     */
    public String getnum(){
        return num;
    }

    /***
     * Envoie du message
     */
    public void send(){
        if(num.length()>= 4 && content.length() > 0){
            //GrâceToast.makeText(MainActivity.this, "Enter le numero et/ou le message", Toast.LENGTH_SHORT).show(); à l'objet de gestion de SMS (SmsManager) que l'on récupère via la méthode static getDefault()
            //On envoie le SMS à l'aide de la méthode sendTextMessage
            SmsManager.getDefault().sendTextMessage(num, null, content, null, null);
        }else{
            //On affiche un petit message d'erreur dans un Toast
            Log.d("SMS", "Envoie u message");
        }
    }
}
