package com.example.forso.flashlearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
        startActivityForResult(intent,100);
        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((EditText) findViewById(R.id.question)).getText().toString();
                ((EditText) findViewById(R.id.answer)).getText().toString();
                Intent data = new Intent();
                data.putExtra("string1", "question");
                data.putExtra("string2", "answer");
                setResult(RESULT_OK, data);
                finish();

            }
        });
    }
}
