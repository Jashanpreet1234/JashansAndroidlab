package algonquin.cst2335.kaur0776;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import algonquin.cst2335.kaur0776.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
         //This loads the toolbar
        setSupportActionBar(variableBinding.myToolbar);
        setContentView(R.layout.activity_main);


    }
}