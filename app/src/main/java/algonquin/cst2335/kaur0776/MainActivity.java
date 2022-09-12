package algonquin.cst2335.kaur0776;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // loads the xml file on  the screen
        // r stands for resouces here
        setContentView(R.layout.activity_main);
    }
}