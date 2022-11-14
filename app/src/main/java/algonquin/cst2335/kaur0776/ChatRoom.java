package algonquin.cst2335.kaur0776;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.kaur0776.data.ChatMessage;
import algonquin.cst2335.kaur0776.data.ChatRoomViewModel;
import algonquin.cst2335.kaur0776.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.kaur0776.databinding.ReceiveMessageBinding;
import algonquin.cst2335.kaur0776.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

        ArrayList<ChatMessage> messages = new ArrayList<>();


        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView timeText;

            public MyRowHolder(@NonNull View itemView) {
                super(itemView);
                messageText = itemView.findViewById(R.id.messageText);
                timeText = itemView.findViewById(R.id.timeText);
            }
        }


        private RecyclerView.Adapter myAdapter;
        ActivityChatRoomBinding binding;

        ChatRoomViewModel chatModel ;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
            messages = chatModel.messages.getValue();
            if(messages == null)
            {
                chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
            }


            binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding.recycleView.setLayoutManager(new LinearLayoutManager(this));


            binding.sendButton.setOnClickListener(click -> {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
                String currentDateandTime = sdf.format(new Date());
                String messageText = binding.textInput.getText().toString();
                messages.add (new ChatMessage(messageText, currentDateandTime, true));
                myAdapter.notifyItemInserted(messages.size()-1);

                binding.textInput.setText("");
            });
            binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
                @NonNull
                @Override
                public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    if(viewType == 0){
                        SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(),parent,false);
                        return new MyRowHolder(binding.getRoot());
                    }
                    else{
                        ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater(),parent,false);
                        return new MyRowHolder(binding.getRoot());
                    }
                }



                @Override
                public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                    String text = messages.get(position).getMessage();
                    holder.messageText.setText(text);

                    String time = messages.get(position).getTimeSent();
                    holder.timeText.setText(time);
                }

                @Override
                public int getItemCount() {
                    return messages.size();
                }
                @Override
                public int getItemViewType(int position){
                    if (messages.get(position).getIsSentButton() == true){
                        return 0 ;

                    }else return 1;
                }

            } );

            binding.receivebutton.setOnClickListener(click -> {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
                String currentDateandTime = sdf.format(new Date());
                String messageText = binding.textInput.getText().toString();
                messages.add (new ChatMessage(messageText, currentDateandTime, false));
                myAdapter.notifyItemInserted(messages.size()-1);

                binding.textInput.setText("");
            });

        }
    }