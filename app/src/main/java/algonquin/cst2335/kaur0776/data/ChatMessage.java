package algonquin.cst2335.kaur0776.data;

public class ChatMessage {
    String message;
    String timeSent;
    boolean isSentButton;

    public ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }
    public String getMessage() { return message;}
    public String getTimeSent() {return timeSent;}
    public boolean getIsSentButton() { return isSentButton;}

}