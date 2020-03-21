package com.example.forso.flashlearn;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        allFlashcards = flashcardDatabase.getAllCards();



        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.question_holder)).setText(allFlashcards.get(allFlashcards.size() -1).getQuestion());
            ((TextView) findViewById(R.id.answer_holder)).setText(allFlashcards.get(allFlashcards.size() -1).getAnswer());
            ((TextView) findViewById(R.id.Option_2)).setText(allFlashcards.get(allFlashcards.size() -1).getAnswer());
            ((TextView) findViewById(R.id.Option_1)).setText(allFlashcards.get(allFlashcards.size() -1).getWrongAnswer1());
            ((TextView) findViewById(R.id.Option_3)).setText(allFlashcards.get(allFlashcards.size() -1).getWrongAnswer2());
        }

        // Changes the Background color of the question field
        //((TextView) findViewById(R.id.question_holder)).setBackgroundColor(getResources().getColor(R.color.colorSeaBlue));

        //Sets Question invisible whenever question widget is clicked.
        findViewById(R.id.question_holder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.question_holder).setVisibility(View.GONE);
                findViewById(R.id.Option_1).setVisibility(View.GONE);
                findViewById(R.id.Option_2).setVisibility(View.GONE);
                findViewById(R.id.Option_3).setVisibility(View.GONE);
                findViewById(R.id.toggle_icon_visibility).setVisibility(View.GONE);
                findViewById(R.id.answer_holder).setVisibility(View.VISIBLE);
                findViewById(R.id.next_button).setVisibility(View.INVISIBLE);

            }
        });
        // Changes the Background Color of the answer widget.
        //((TextView) findViewById(R.id.answer_holder)).setBackgroundColor(getResources().getColor(R.color.colorYellow));

        // Sets the visibility of the answer widget to invisible when clicked.
        findViewById(R.id.answer_holder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.answer_holder).setVisibility(View.GONE);
                findViewById(R.id.question_holder).setVisibility(View.VISIBLE);
                findViewById(R.id.Option_1).setVisibility(View.VISIBLE);
                findViewById(R.id.Option_2).setVisibility(View.VISIBLE);
                findViewById(R.id.Option_3).setVisibility(View.VISIBLE);
                findViewById(R.id.toggle_icon_visibility).setVisibility(View.VISIBLE);
                findViewById(R.id.next_button).setVisibility(View.VISIBLE);

            }
        });
        // Changes the color to red when clicked to signify wrong answer.
        findViewById(R.id.Option_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.Option_1).setBackgroundResource(R.drawable.answer_incorrect);
                findViewById(R.id.Option_2).setBackgroundResource(R.drawable.answer_correct);
            }
        });

        // Changes the color to Green when clicked to signify correct answer.
        findViewById(R.id.Option_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.Option_2).setBackgroundResource(R.drawable.answer_correct);
            }
        });

        // Changes the color to Red when clicked to signify wrong answer.
        findViewById(R.id.Option_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.Option_3).setBackgroundResource(R.drawable.answer_incorrect);
                findViewById(R.id.Option_2).setBackgroundResource(R.drawable.answer_correct);
            }
        });


        // Setting Boolean logic to toggle the visibility of the answer choices

            findViewById(R.id.toggle_icon_visibility).setOnClickListener(new View.OnClickListener() {
                boolean is_showing_icon = false;
                @Override
                public void onClick(View v) {
                    if (is_showing_icon){
                    ((ImageView) findViewById(R.id.toggle_icon_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_8);
                    findViewById(R.id.Option_1).setVisibility(View.VISIBLE);
                    findViewById(R.id.Option_2).setVisibility(View.VISIBLE);
                    findViewById(R.id.Option_3).setVisibility(View.VISIBLE);
                    findViewById(R.id.Option_1).setBackgroundResource(R.drawable.options_shape);
                    findViewById(R.id.Option_2).setBackgroundResource(R.drawable.options_shape);
                    findViewById(R.id.Option_3).setBackgroundResource(R.drawable.options_shape);
                    is_showing_icon = false;}
                    else {
                        ((ImageView) findViewById(R.id.toggle_icon_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_5);
                    findViewById(R.id.Option_1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.Option_2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.Option_3).setVisibility(View.INVISIBLE);
                    findViewById(R.id.Option_1).setBackgroundResource(R.drawable.options_shape);
                    findViewById(R.id.Option_2).setBackgroundResource(R.drawable.options_shape);
                    findViewById(R.id.Option_3).setBackgroundResource(R.drawable.options_shape);
                    is_showing_icon = true;}



                }});
            findViewById(R.id.root_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.Option_1).setBackgroundResource(R.drawable.options_shape);
                    findViewById(R.id.Option_2).setBackgroundResource(R.drawable.options_shape);
                    findViewById(R.id.Option_3).setBackgroundResource(R.drawable.options_shape);

                }
            });
            findViewById(R.id.plus_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                    startActivityForResult(intent,100);
                }
            });

            findViewById(R.id.edit_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                    intent.putExtra("question1",((TextView)findViewById(R.id.question_holder)).getText().toString());
                    intent.putExtra("answer1", ((TextView)findViewById(R.id.answer_holder)).getText().toString());
                    startActivityForResult(intent, 100);
                }
            });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.question_holder)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.answer_holder)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                ((TextView) findViewById(R.id.Option_1)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                ((TextView) findViewById(R.id.Option_3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                ((TextView) findViewById(R.id.Option_2)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });

       findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               flashcardDatabase.deleteCard(((TextView) findViewById(R.id.question_holder)).getText().toString());
               flashcardDatabase.deleteCard(((TextView) findViewById(R.id.answer_holder)).getText().toString());
               flashcardDatabase.deleteCard(((TextView) findViewById(R.id.Option_1)).getText().toString());
               flashcardDatabase.deleteCard(((TextView) findViewById(R.id.Option_2)).getText().toString());
               flashcardDatabase.deleteCard(((TextView) findViewById(R.id.Option_3)).getText().toString());

           }
       });




        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            if (data != null) {// checks for empty data passed by the user to prevent app from crashing.
                String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
                String string2 = data.getExtras().getString("string2");
                String string3 = data.getExtras().getString("string3");
                String string4 = data.getExtras().getString("string4");
                ((TextView) findViewById(R.id.question_holder)).setText(string1);
                ((TextView) findViewById(R.id.answer_holder)).setText(string2);
                ((TextView) findViewById(R.id.Option_1)).setText(string3);
                ((TextView) findViewById(R.id.Option_2)).setText(string2);
                ((TextView) findViewById(R.id.Option_3)).setText(string4);
                flashcardDatabase.insertCard(new Flashcard(string1, string2, string3, string4));
                allFlashcards = flashcardDatabase.getAllCards();


            }

        }

    }

    int currentCardDisplayedIndex = 0;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;


}




