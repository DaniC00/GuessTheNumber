package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    //random
    public static final String EXTRA_MESSAGE = "com.example.guessthenumber.MESSAGE" ;

    private int nGuess, numberTries, n;
    private EditText text;
    private EditText textRanking;
    private Button halloffame;

    private AlertDialog adRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.editText);
        halloffame = (Button) findViewById(R.id.btnRanking);

        setRankingDialog();
        newGame();

        final Button validate = findViewById(R.id.btnCheck);

        //entrar en el validador al clickar el boton.
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
                text.setText("");
            }
        });

        halloffame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ranking("");
            }
        });


    }


    //valida si el numero es correcto o no y da pistas.
    private void validate(){
        n = Integer.parseInt(text.getText().toString());
        numberTries++;

        if (n==nGuess){
            Toast.makeText(this, "Correct! You found the number " + nGuess +" in "+numberTries + " tries. Let's play again!", Toast.LENGTH_SHORT).show();
            rankingDialog();
        }
        else if (n>nGuess){
            Toast.makeText(this, "Look for a smaller number.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Look for a bigger number.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setRankingDialog(){

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Go for the podium!");
        adb.setMessage("What's your name?");
        textRanking = new EditText(this);
        adb.setView(textRanking);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        adRanking = adb.create();

    }

    private void rankingDialog(){
        adRanking.show();
        adRanking.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = textRanking.getText().toString();
                adRanking.dismiss();

                ranking(userName);

                newGame();
            }
        });


    }


    //randomiza el numero que buscamos y resetea los intentos.
    private void newGame(){
        nGuess = (int)(Math.random()*100)+1;
        numberTries = 0;
    }

    private void ranking(String userName){
        Intent intent = new Intent(this, RankingActivity.class);

        if(userName != "") {
            String message = userName + ',' + numberTries;
            intent.putExtra(EXTRA_MESSAGE, message);
        }
        startActivity(intent);

    }

}