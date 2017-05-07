package ttraaholt.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class FinalActivity extends AppCompatActivity {

    TextView tvDay;
    TextView tvNote1;
    Button buttonSignOut;
    Button buttonNewNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        showResult();
        buttonNewNote();
        buttonSignOut();
    }


    private void showResult() {

        Intent Intent = getIntent();
        //Place the Strings passed into three new variables first, last, and spinnerGender.
        String first = Intent.getStringExtra("firstNote");
        String spinnerDay = Intent.getStringExtra("spinDay");

        tvDay = (TextView) findViewById(R.id.textViewDay);
        tvNote1 = (TextView) findViewById(R.id.textViewNote1);

        tvDay.setText("Day: " + spinnerDay);
        tvNote1.setText("Note: " + first);
    }

    private void buttonSignOut() {
        buttonSignOut = (Button) findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void buttonNewNote() {
        buttonNewNote = (Button) findViewById(R.id.buttonNewNote);
        buttonNewNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }


    //signOut() method that signs out the user.
    private void signOut() {
        startActivity(new Intent(FinalActivity.this, SecondActivity.class));

    }
}
