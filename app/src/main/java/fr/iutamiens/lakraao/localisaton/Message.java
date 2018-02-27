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
    public String getSender(){
        return sender;
    }
}
