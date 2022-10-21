package algonquin.cst2335.kaur0776;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent fromPrevious = getIntent(); // for getting previous intent?
        Intent call = new Intent(Intent.ACTION_DIAL); // for dialing
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // for taking image

        int age = fromPrevious.getIntExtra("Age", 0);
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        TextView welcome = (TextView) findViewById(R.id.textView3);
        EditText phoneNumber = (EditText) findViewById(R.id.editTextPhone);
        Button callButton = (Button) findViewById(R.id.button2);
        ImageView profileImage = (ImageView) findViewById(R.id.imageView);
        Button changePictureButton = (Button) findViewById(R.id.button3);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String phoneNumberPref  = prefs.getString("PhoneNumber", "");
        phoneNumber.setText(phoneNumberPref);

        welcome.setText("Welcome back " + emailAddress);

        callButton.setOnClickListener(click -> {

            call.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
            startActivity(call);

        });
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent data = result.getData(); // get data from result
                            Bitmap thumbnail = data.getParcelableExtra("data"); // store image under data as bitmap object
                            profileImage.setImageBitmap(thumbnail);


                            FileOutputStream fOut = null;

                            try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                                fOut.flush();

                                fOut.close();

                            }
                            catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        changePictureButton.setOnClickListener(click -> {
            cameraResult.launch(cameraIntent);
        });

        File file = new File( getFilesDir(), "Picture.png");

        if(file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(file.getPath());
            profileImage.setImageBitmap(theImage);
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        EditText phoneNumber = (EditText) findViewById(R.id.editTextPhone);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        String phoneNumberText = phoneNumber.getText().toString();

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("PhoneNumber", phoneNumberText);
        editor.apply();
    }
}
