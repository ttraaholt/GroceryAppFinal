package ttraaholt.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * This application brings the user through the authentication process and then gives the user the option to
 * set up a note system. Every time a note is added, it will add it to the list.
 * @author  Tommy Traaholt
 * @version 1.0
 * @since   5/7/2017
 */

//This class is mainly for redirecting to separate activities, as well as the creating a note section.
public class MainActivity extends AppCompatActivity {

    EditText etFirstNote;
    Button buttonSubmit;
    Button buttonSignOut;
    Spinner spinnerDay;
    private List<String> noteList;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * OnCreate() method that retrieves the spinnerDay and editTextFirstNote widget.
     * Next, it calls the userAuthentication(), the buttonSubmit(), and the buttonSignOut() methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        etFirstNote = (EditText) findViewById(R.id.editTextNote);

        userAuthentication();
        buttonSubmit();
        buttonSignOut();

    }
    /**
     * method that starts the login activity. In this case it is the SecondActivity.
     * starts the SignIn Intent
     */
    private void userAuthentication() {

        //creates a new instance of the Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //if the user is null declare a new intent and start the intent.
                if (user == null) {
                    Intent signInIntent = new Intent(getBaseContext(), SecondActivity.class);
                    startActivity(signInIntent);

                }
            }
        };

    }
    /**
     * method for the button submit, that takes the information from the form and sends them to a new activity.
     * param spinDay This is the first paramter to addNum method
     * param firstNote This is the second parameter to addNum method
     * param noteList This is the list array created for the notes and days
     * @return the intent with the noteList.
     */
    private void buttonSubmit() {
        //declare the button
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        //create a new listener that retrieves the data and converts them to strings.
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String spinDay = spinnerDay.getSelectedItem().toString();
                String firstNote = etFirstNote.getText().toString();
                //new array list called noteList. This list ads the spinDay and the firstNote variables.
                List<String> noteList = new ArrayList<String>();
                noteList.add(spinDay);
                noteList.add(firstNote);
                //if the firstNote variable is empty, pop up a toast message.
                if (firstNote.matches("")) {
                    Toast.makeText(MainActivity.this, "You did not enter a note", Toast.LENGTH_SHORT).show();
                //If firstNote is not empty, create a new intent and pass the noteList array through.
                } else {
                    Intent Intent = new Intent(MainActivity.this, FinalActivity.class);
                    //Attribute the noteList array.
                    Intent.putStringArrayListExtra("noteList", (ArrayList<String>) noteList);
                    //Start the Intent
                    startActivity(Intent);
                }
            }
        });
        };

    /**
     * method for the button sign out, which calls the signOut() method.
     * @return the signOut() method.
     */
    private void buttonSignOut() {
        buttonSignOut = (Button) findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signOut();
            }
        });
    }

    /**
     * onStart() method which calls the add auth state listener
     */
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * onStop() method which removes the auth state listener.
     */
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /**
     * signOut() method which signs out the user.
     */
    private void signOut() {
        mAuth.signOut();

    }
}
