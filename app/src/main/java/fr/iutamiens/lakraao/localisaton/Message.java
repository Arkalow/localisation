package fr.iutamiens.lakraao.localisaton;

/***
 * Represent a message
 * Created by omer on 27/02/18.
 */
public class Message {

    private String content;
    private String sender;
    private String code;
    private final String head = "code : ";

    /***
     * Message's construct
     * @param content message's content
     * @param sender message's sender
     */
    public Message(String content, String sender){
        this.content = content;
        this.sender = sender;

        if (content.substring(0, head.length()) == head){
            code = content.substring(head.length() + 1, content.length());
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
