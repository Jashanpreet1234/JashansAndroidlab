package algonquin.cst2335.kaur0776;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import algonquin.cst2335.kaur0776.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.kaur0776.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ArrayList<String> messages = new ArrayList<>();
    private RecycleView.Adapter(myAdapter) = new RecyclerView.Adapter<MyRowHolder>(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendButton.setOnClickListener(click -> {
            messages.add(binding.textInput.getText().toString());
            myAdapter.notifyItemInserted(messages.size()-1);


            binding.textInput.setText("");
        });


        binding.recycleView.setAdapter(new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                return new MyRowHolder( binding.getRoot());
                getItemViewType(int position){
                    return 0;
                }

            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.messageText.setText("obj");
                holder.timeText.setText("");
                String obj = messages.get(position);

            }

            @Override
            public int getItemCount() {
                return messages.size();
            }
        });


             class  MyRowHolder extends RecyclerView.ViewHolder{
              TextView messageText;
              TextView timeText;


            public MyRowHolder(@NonNull View itemView) {
                super(itemView);

                messageText = itemView.findViewById(R.id.messageText);
                timeText = itemView.findViewById(R.id.timeText);

            }
             }

    }
}