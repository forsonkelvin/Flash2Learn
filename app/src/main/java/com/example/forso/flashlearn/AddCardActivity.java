package com.example.forso.flashlearn;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        // Takes the user back to the main page
        findViewById(R.id.return_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
                finish();
            }
        });

        // Saves the data by the user and returns to Main activity with the new data being passed to main screen.
        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();//
                data.putExtra("string1", ((EditText) findViewById(R.id.question)).getText().toString()); // gets the string inputted in the Edit View and passes it onActivity Result in Main ACtivity.
                data.putExtra("string2", ((EditText) findViewById(R.id.answer)).getText().toString());
                data.putExtra("string3", ((EditText) findViewById(R.id.user_option_1)).getText().toString());
                data.putExtra("string4", ((EditText) findViewById(R.id.user_option_2)).getText().toString());
                setResult(RESULT_OK, data);
                Snackbar.make(findViewById(R.id.question), // Used to show toast message after user saves data.
                        "Saving Question",
                        Snackbar.LENGTH_SHORT)
                        .show();
                finish();

            }
        });

        String s1 = getIntent().getStringExtra("question1");
        String s2 = getIntent().getStringExtra("answer1");
        ((EditText) findViewById(R.id.question)).setText(s1);
        ((EditText) findViewById(R.id.answer)).setText(s2);
    }
}
