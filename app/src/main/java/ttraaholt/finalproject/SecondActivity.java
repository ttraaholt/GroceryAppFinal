package ttraaholt.finalproject;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This class is for the authentication process and display that is called when the app starts up.
 * @author  Tommy Traaholt
 * @version 1.0
 * @since   5/7/2017
 */
//This class is mainly for the user authentication process
public class SecondActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogin;
    Button buttonCreateUser;
    FloatingActionButton fab;

    private FirebaseAuth mAuth;

    /**
     * OnCreate() method that ties the widgets together and creates the buttons for logging in and creating a new user.
     * Next, it calls the userAuthentication(), the buttonSubmit(), and the buttonSignOut() methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fab = (FloatingActionButton) findViewById(R.id.fabMail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:ttraaholt@css.edu")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "I Can't Sign In!");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCreateUser = (Button) findViewById(R.id.buttonCreateUser);

        //button onclick listener for the login button. Calls the signIn() method and converts the editText widgets to strings.
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }
        });

        //onClick listener added to buttonCreateLogin that contains log message when it is clicked, and uses createAccount method
        //gets the editTextEmail and converts it to a string, and gets the editTextPassword and converts it to a string.
        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAccount(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    /**
     * signIn() method that signs the user in if the email and password match. If it fails, it will display a message.
     * @params email The users email
     * @param password The users password
     * When it is done, it finishes and goes back to the Main Activity.
     */
    private void signIn(String email, String password){
        //sign in the recurrent user with email and password previously created.
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithEmail:success");
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithEmail:failure", task.getException());
                            Toast.makeText(SecondActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * createAccount() method that creates a new account with Firebase.
     * @params email The users email
     * @param password The users password
     * When it is done, it finishes and goes back to the Main Activity.
     */
    private void createAccount(String email, String password) {
        //create account for new users
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "createUserWithEmail:success");
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SecondActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}