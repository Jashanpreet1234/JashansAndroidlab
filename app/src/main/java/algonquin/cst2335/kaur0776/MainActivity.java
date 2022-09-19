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
TextView mytext = findViewById(R.id.textview);


  Button btn = findViewById(R.id.mybutton);
  EditText myedit = findViewById(R.id.myedittext);
btn.setOnClickListener(new View.OnClick(){
    public void onClick(View v) {
    String editString = myedit.getText().toString();
    mytext.setText("Your edit text has:" + editString);
    }

});
    }
}


//
////loads thw first Textview
//
//        TextView firstText = findViewById( R.id.firstString );
//        firstText.setText("Java changed this");
//        String value = firstText.getText().tostring();
//
//
//        EditText second = findViewById( R.id.secondString );
//        second.setText("I'm an edit Text");

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

