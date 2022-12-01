package algonquin.cst2335.kaur0776;


import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;
import algonquin.cst2335.kaur0776.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.kaur0776.databinding.ReceiveMessageBinding;
import algonquin.cst2335.kaur0776.databinding.SentMessageBinding;
import data.ChatRoomViewModel;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import data.ChatRoomViewModel;

public class ChatRoom extends AppCompatActivity {
    RecyclerView recyclerView;
    MessageDatabase db;
    ArrayList<ChatMessage> messages;
    ChatRoomViewModel chatModel;
    Button sendButton;
    TextView textInput;
    TextView sentMessage;
    ChatMessageDAO mDAO;
    Button recieveButton;
    ChatMessage chatMessage = null;
    Toolbar myToolbar;

    ActivityChatRoomBinding binding;
    private RecyclerView.Adapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);



        recyclerView = findViewById(R.id.recycleView);
        sendButton = findViewById(R.id.sendButton);
        textInput = findViewById(R.id.textInput);
        recieveButton = findViewById(R.id.recieveButton);
        myToolbar=findViewById(R.id.myToolbar);
       // setSupportActionBar(myToolbar);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 5);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        if (messages == null) {
            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());

        }
        chatModel.selectedMessage.observe(this, (newMessageValue) -> {
            MessageDetailsFragment chatFragment = new MessageDetailsFragment(newMessageValue );  //newValue is the newly set ChatMessage
            FragmentManager fMgr = getSupportFragmentManager();
            FragmentTransaction tx = fMgr.beginTransaction();
            tx.add(R.id.fragementLocation, chatFragment);
            tx.addToBackStack("");
            tx.commit();
        });


        boolean sent;
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
                myAdapter.notifyItemInserted(messages.size() - 1);

                Executor thread = Executors.newSingleThreadExecutor();
                thread.execute(() -> {
                    mDAO.insertMessage(chatMessage);
                });

                textInput.setText("");


            }
        });
        recieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
                String currentDateandTime = sdf.format(new Date());
                chatMessage = new ChatMessage(textInput.getText().toString(), currentDateandTime, true);
                chatMessage.setTimeSent(currentDateandTime);
                chatMessage.setMessage(textInput.getText().toString());
                chatMessage.setSentButton(false);
                messages.add(chatMessage);
                myAdapter.notifyItemInserted(messages.size() - 1);

                Executor thread = Executors.newSingleThreadExecutor();
                thread.execute(() -> {
                    mDAO.insertMessage(chatMessage);
                });
                textInput.setText("");


            }
        });
        db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        mDAO = db.cmDAO();
        if (messages == null) {
            chatModel.messages.setValue(messages = new ArrayList<>());

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() ->
            {
                messages.addAll(mDAO.getAllMessages()); //Once you get the data from database

                runOnUiThread(() -> recyclerView.setAdapter(myAdapter)); //You can then load the RecyclerView
            });
        }
        recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {

            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding.getRoot());
                } else {
                    ReceiveMessageBinding b2 = ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);

                    return new MyRowHolder(b2.getRoot());
                }

            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

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
                if (messages.get(position).isSentButton() == true) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });


    }

    private void run() {
                   messages.addAll( mDAO.getAllMessages() ); //Once you get the data from database
        mDAO.deleteAll();
        for (int i = 0; i < messages.size(); i++) {
            messages.remove(i);

        }

//You can then load the RecyclerView
    }


    private class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
            ChatMessageDAO mDAO = db.cmDAO();
            itemView.setOnClickListener(clk -> {
                int position = getAbsoluteAdapterPosition();
                ChatMessage selected=messages.get(position);
                chatModel.selectedMessage.postValue(selected);
                ChatMessage thisMessage = messages.get(position);
                 MyRowHolder newRow = adt.OnCreateViewHolder(null, adt.getItemViewType(position));
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);
                builder.setMessage("Do you want to delete the message: " + messageText.getText())
                        .setTitle("Question: ")
                        .setNegativeButton("no", (dialog, cl) -> {
                        })
                        .setPositiveButton("yes", (dialog, cl) -> {
                            ChatMessage m = messages.get(position);
                            Executor thread = Executors.newSingleThreadExecutor();
                            thread.execute(() ->
                            {
                                       messages.addAll( mDAO.getAllMessages() ); //Once you get the data from database
                                mDAO.deleteMessage(thisMessage);

                                //You can then load the RecyclerView
                            });
                            messages.remove(position);
                            myAdapter.notifyItemRemoved(position);
                             mDAO.deleteMessage(m);

                            // adt.notifyItemRemoved(position)


                            Snackbar.make(messageText, "You deleted message #" + position, Snackbar.LENGTH_LONG).setAction("Undo", clkk -> {
                                messages.add(position, thisMessage);

                                Executor thread1 = Executors.newSingleThreadExecutor();
                                thread1.execute(() -> {
                                    mDAO.insertMessage(thisMessage);
                                });
                                myAdapter.notifyItemInserted(position);

                            }).show();
                        })
                        .create().show();


            });
            messageText = itemView.findViewById(R.id.messageText);
            timeText = itemView.findViewById(R.id.timeText);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch( item.getItemId() )
        {
            case R.id.item_1:
                Executor thread = Executors.newSingleThreadExecutor();
                thread.execute(this::run);
                //put your ChatMessage deletion code here. If you select this item, you should show the alert dialog
                //asking if the user wants to delete this message.
                break;
            case R.id.item_2:
                Context context = getApplicationContext();
                CharSequence text = "Version 1.0, created by Jashanpreet kaur";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;



            default:
                super.onOptionsItemSelected((MenuItem) item);
                break;
        }

        return true;
    }

}