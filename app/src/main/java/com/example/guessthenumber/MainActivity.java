package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int nGuess, numberTries, n;
    private Button validate;
    private Button ranking;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.editText);
        validate = (Button) findViewById(R.id.touchButton);
        validate.setOnClickListener(this);
        ranking = (Button) findViewById(R.id.touchButtonRanking);
        ranking.setOnClickListener(this);

        newGame();
    }

    //entra en el validador al clicar el boton.
    @Override
    public void onClick(View view){
        if (view == validate){
            validate();
        }
        if (view == ranking){
            ranking();
        }
    }


    private void ranking(){
        setContentView(R.layout.activity_ranking_activiry);

    }
    //valida si el numero es correcto o no y da pistas.
    private void validate(){
        n = Integer.parseInt(text.getText().toString());
        numberTries++;

        if (n==nGuess){
            Toast.makeText(this, "Correct! You found the number " + nGuess +" in "+numberTries + " tries. Let's play again!", Toast.LENGTH_SHORT).show();
            newGame();
        }
        else if (n>nGuess){
            Toast.makeText(this, "Look for a smaller number.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Look for a bigger number.", Toast.LENGTH_SHORT).show();
        }
    }

    //randomiza el numero que buscamos y resetea los intentos.
    private void newGame(){
        nGuess = (int)(Math.random()*100)+1;
        numberTries = 0;

    }
}