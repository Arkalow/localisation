package fr.iutamiens.lakraao.localisaton;

import android.util.Log;

/***
 * Represent a message
 * Created by omer on 27/02/18.
 */
public class Message {

    private String content;
    private String sender;
    private String code;
    private final String head = "Code : ";

    /***
     * Message's construct
     * @param content message's content
     * @param sender message's sender
     */
    public Message(String sender, String content){
        this.content = content;
        this.sender = sender;
        Log.d("Message", content.substring(0, head.length()));
        if (content.substring(0, head.length()) == head){
            code = content.substring(head.length() + 1, content.length());
        }else{
            code = null;
        }
    }

    /***
     * Return the message's content
     * @return message's content
     */
    public String getCode(){
        return content;
    }

    /***
     * Return the message's content
     * @return message's content
     */
    public String getSender(){
        return sender;
    }
}
