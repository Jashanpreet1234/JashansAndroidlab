package algonquin.cst2335.kaur0776;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id" )
    public int id;
    @ColumnInfo(name = "message")
    protected String message;
    @ColumnInfo(name = "TimeSent")
    protected String timeSent;
    @ColumnInfo(name = "SendOrRecieve")
    protected boolean isSentButton;
    ChatMessage(String m, String t, boolean sent)
    {
        message =m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }
}
