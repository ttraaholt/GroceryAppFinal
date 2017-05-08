package ttraaholt.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for displaying the notes in a list view.
 * @author  Tommy Traaholt
 * @version 1.0
 * @since   5/7/2017
 */
public class FinalActivity extends AppCompatActivity {

    Button buttonSignOut;
    Button buttonNewNote;
    ListView lvNotes;

    /**
     * OnCreate() method that calls the showResult(), the buttonNewNote(), and the buttonSignOut() methods.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        showResult();
        buttonNewNote();
        buttonSignOut();
    }

    /**
     * showResult() method that ties the list view to the variable and retrieves the information from the noteList in the Main Activity
     * Next, it creates a new array adapter and sets the lvNotes variable to the adapter
     */
    private void showResult() {

        lvNotes = (ListView) findViewById(R.id.ListViewNote);
        //Stores the noteList from MainActivity to a new noteList that is an Array List.
        ArrayList<String> noteList = getIntent().getStringArrayListExtra("noteList");

        //Creates a new array adapter with the noteList
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                noteList );

        //ties the list view and the array adapter with the list together.
        lvNotes.setAdapter(arrayAdapter);

    }

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
     * method for the button new Note. It returns the user to the Main Activity.
     * when finish() is called, the user is sent back to the form.
     */
    private void buttonNewNote() {
        buttonNewNote = (Button) findViewById(R.id.buttonNewNote);
        buttonNewNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }


    //signOut() method starts a new activity that sends the user to the SecondActivity class.
    private void signOut() {
        startActivity(new Intent(FinalActivity.this, SecondActivity.class));
    }
}
