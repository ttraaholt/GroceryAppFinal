package ttraaholt.finalproject;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.os.IResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
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
    Button buttonNewItem;
    Button buttonCoupon;
    ListView lvItems;

    /**
     * OnCreate() method that calls the showResult(), the buttonNewItem(), the buttonSignOut() method, and the buttonCoupon() method.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        showResult();
        ButtonNewItem();
        buttonSignOut();
        buttonCoupon();
    }

    /**
     * showResult() method that ties the list view to the variable and retrieves the information from the itemList in the Main Activity
     * Next, it creates a new array adapter and sets the lvItems variable to the adapter
     */
    private void showResult() {

        lvItems = (ListView) findViewById(R.id.ListViewItems);
        //Stores the noteList from MainActivity to a new itemList that is an Array List.
        ArrayList<String> ItemList = getIntent().getStringArrayListExtra("ItemList");
        //Creates a new array adapter with the itemList
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                ItemList );

        //ties the list view and the array adapter with the list together.
        lvItems.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

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
     * method for the button new Item. It returns the user to the Main Activity.
     * when startActivity is called, the user is sent back to the form.
     */
    private void ButtonNewItem() {
        buttonNewItem = (Button) findViewById(R.id.buttonNewItem);
        buttonNewItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(FinalActivity.this, MainActivity.class));
            }
        });
    }
    /**
     * method for the coupon intent. It opens the browser for groupon.com.
     */
    private void buttonCoupon() {
        buttonCoupon = (Button) findViewById(R.id.buttonCoupon);

        buttonCoupon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String url = "http://www.groupon.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }


    //signOut() method starts a new activity that sends the user to the SecondActivity class.
    private void signOut() {
        startActivity(new Intent(FinalActivity.this, SecondActivity.class));
    }
}
