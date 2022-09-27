package algonquin.cst2335.kaur0776.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import algonquin.cst2335.kaur0776.R;
import algonquin.cst2335.kaur0776.data.MainViewModel;
import algonquin.cst2335.kaur0776.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());


        model = new ViewModelProvider(this).get(MainViewModel.class);
        setContentView(variableBinding.getRoot());


        variableBinding.mybutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.editstring.postValue(variableBinding.edittext.getText().toString());

            }

        });

        model.editstring.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                variableBinding.textview.setText("Your edit text has : " + s);
            }
        });


        model.isselected.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
        });


        variableBinding.checkBox.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isselected.postValue(variableBinding.checkBox.isChecked());
//          variableBinding.textview.setText("The value is  now:" + isChecked);
            Toast.makeText(this, "The value is now" + isChecked, Toast.LENGTH_SHORT).show();
        });
        variableBinding.radioButton.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isselected.postValue(variableBinding.radioButton.isChecked());
            Toast.makeText(this, "The value is now:" + isChecked, Toast.LENGTH_SHORT).show();
        });
        variableBinding.switch1.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isselected.postValue(variableBinding.switch1.isChecked());
            Toast.makeText(this, "The value is now" + isChecked, Toast.LENGTH_SHORT).show();

        });

        variableBinding.myimagebutton.setImageResource(R.drawable.logo_algonquin);
        variableBinding.myimagebutton.setOnClickListener(click ->
        {
//            Toast.makeText(this, "The width =" + click.getMeasuredWidth() + " and height =" + click.getMeasuredHeight(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "this is an ImageButton", Toast.LENGTH_SHORT).show();

        });

    }
}



