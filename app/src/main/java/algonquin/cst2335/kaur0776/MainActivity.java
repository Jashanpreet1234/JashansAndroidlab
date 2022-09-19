package algonquin.cst2335.kaur0776;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        loads the XML file on screen

setContentView(R.layout.activity_main);


//loads thw first Textview

        Textview firstText = findViewById(R.id.firststring);
        firstText.setText("Java changed this");

    });
}
}





//Variables declarations
//        Button btn = findViewById(R.id.mybutton);
//        TextView mytext = findViewById(R.id.textview);
//      EditText myedit = findViewById(R.id.myedittext);
//
//
////
//String editString = myedit.getText().toString();
//
//mytext.setText("Your edit text has:" + editString);
//
//
//        final Button button = findViewById(R.id.my_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Code here executes on main thread after user presses button
//            }

