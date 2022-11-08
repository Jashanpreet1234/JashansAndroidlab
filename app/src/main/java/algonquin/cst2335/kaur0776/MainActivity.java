package algonquin.cst2335.kaur0776;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This page is created for a password checker application
 * @author Jashanpreet Kaur
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This function is used to detect if a password contains
     * uppercase, lowercase, numeric, and special characters
     * @param pw The String object that we are checking
     * @return Returns true if password is complex enough
     */
    boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);

            if (Character.isDigit(c))
                foundNumber = true;
            if (Character.isUpperCase(pw.charAt(i)))
                foundUpperCase = true;
            if (Character.isLowerCase(pw.charAt(i)))
                foundLowerCase = true;
            if (isSpecialCharacter(pw.charAt(i)))
                foundSpecial = true;
        }

        if (!foundUpperCase) {
            Toast.makeText(this, "At least one uppercase character is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "At least one lowercase character is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundNumber) {
            Toast.makeText(this, "At least one number is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(this, "At least one special character is required", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    /**
     * This function is to check and ensure that a special character is used
     * @param c The character that the function is checking
     * @return Returns a boolean; true if it contains one of the special
     * characters and false if it does not
     */
    boolean isSpecialCharacter(char c) {
        switch (c) {
            case '!':
            case '@':
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '?':
                return true;
            default:
                return false;
        }
    }

    /** This holds the text at the centre of the screen*/
    private TextView tv = null;

    /**This holds an input of a password under the TextView*/
    private EditText et = null;

    /**This holds a login button under the password input*/
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener( clk -> {
            String password = et.getText().toString();

//            checkPasswordComplexity(password);

            if(checkPasswordComplexity(password) == true) {
                tv.setText("Your password meets the requirements. Whoohooooo!");
            } else
                tv.setText("You shall not pass!");
        });
    }
}











