package ttraaholt.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent Intent = getIntent();
        //Place the Strings passed into three new variables first, last, and spinnerGender.
        String first = Intent.getStringExtra("firstName");
        String last = Intent.getStringExtra("lastName");
        String spinnerGender = Intent.getStringExtra("spinGender");


        tvResult = (TextView) findViewById(R.id.tvResult);

        tvResult.setText(first + last + spinnerGender);
    }
}
