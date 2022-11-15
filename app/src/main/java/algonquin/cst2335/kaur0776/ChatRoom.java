package algonquin.cst2335.kaur0776;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.kaur0776.databinding.ReceiveMessageBinding;
import algonquin.cst2335.kaur0776.databinding.SentMessageBinding;
import data.ChatRoomViewModel;

public class ChatRoom extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<ChatMessage> messages;
    ChatRoomViewModel chatModel;
    Button sendButton;
    TextView textInput;
    TextView sentMessage;

    Button recieveButton;
    ChatMessage chatMessage = null;


    private RecyclerView.Adapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);


        recyclerView = findViewById(R.id.recycleView);
        sendButton = findViewById(R.id.sendButton);
        textInput = findViewById(R.id.textInput);
        recieveButton = findViewById(R.id.recieveButton);

      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 5);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        if (messages == null) {
            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
                String currentDateandTime = sdf.format(new Date());
                chatMessage = new ChatMessage(textInput.getText().toString(), currentDateandTime, false);
                chatMessage.setTimeSent(currentDateandTime);
                chatMessage.setMessage(textInput.getText().toString());
                chatMessage.setSentButton(true);
                messages.add(chatMessage);
                myAdapter.notifyItemInserted(messages.size()-1);
                // messageText=chatMessage.getMessage();
                textInput.setText("");


            }
        });
        recieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
                String currentDateandTime = sdf.format(new Date());
                chatMessage = new ChatMessage(textInput.getText().toString(), currentDateandTime, false);
                chatMessage.setTimeSent(currentDateandTime);
                chatMessage.setMessage(textInput.getText().toString());
                chatMessage.setSentButton(false);
                messages.add(chatMessage);
                myAdapter.notifyItemInserted(messages.size()-1);
                // messageText=chatMessage.getMessage();
                textInput.setText("");


            }
        });

        recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {

            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//                SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(), parent, false);
//                return new MyRowHolder(binding.getRoot());
                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding.getRoot());
                } else {
                    ReceiveMessageBinding binding2 = ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding2.getRoot());
                }

            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
 chatMessage= messages.get(position);
                String obj = chatMessage.message;
                holder.messageText.setText(obj);
                String obj2 = chatMessage.timeSent;
                holder.timeText.setText(obj2);
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            public int getItemViewType(int position) {
                if (messages.get(position).isSentButton()==true){
                    return 0;
                }
                else
                {
                    return 1;
                }
            }
        });


    }

    private class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            timeText = itemView.findViewById(R.id.timeText);
        }

    }
}