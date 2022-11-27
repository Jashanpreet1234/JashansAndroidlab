package algonquin.cst2335.kaur0776;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ChatMessage")
public class ChatMessage {

    @ColumnInfo(name = "message")
    public String message;

    @ColumnInfo(name = "timeSent")
    public String timeSent;

    @ColumnInfo(name = "isSentButton")
    protected boolean isSentButton;


    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;


    public ChatMessage(String m, String t, boolean sent) {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }


    public ChatMessage() {


    }


    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }
}

