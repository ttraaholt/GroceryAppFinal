package ttraaholt.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    Button buttonSubmit;
    Button buttonSignOut;
    Spinner spinnerGender;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        etFirstName = (EditText) findViewById(R.id.editTextFirstName);
        etLastName = (EditText) findViewById(R.id.editTextLastName);

        userAuthentication();
        buttonSubmit();
        buttonSignOut();

    }

    private void userAuthentication() {

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Log.d("CSS3334","onAuthStateChanged - User NOT is signed in");
                    Intent signInIntent = new Intent(getBaseContext(), SecondActivity.class);
                    startActivity(signInIntent);

                }
            }
        };

    }

    private void buttonSubmit() {
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String spinGender = spinnerGender.getSelectedItem().toString();
                if (firstName.matches("")) {
                    Toast.makeText(MainActivity.this, "You did not enter a first name", Toast.LENGTH_SHORT).show();
                } else if (lastName.matches("")) {
                    Toast.makeText(MainActivity.this, "You did not enter a last name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent Intent = new Intent(MainActivity.this, FinalActivity.class);
                    //Attribute the first and last name into an extra and send it with the key "firstName" and "lastName".
                    Intent.putExtra("firstName",firstName);
                    Intent.putExtra("lastName",lastName);
                    Intent.putExtra("spinnerGender",spinGender);
                    //Start the Intent
                    startActivity(Intent);
                }
            }
        });
        };

    private void buttonSignOut() {
        buttonSignOut = (Button) findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signOut();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //signOut() method that signs out the user.
    private void signOut() {
        mAuth.signOut();

    }
}
