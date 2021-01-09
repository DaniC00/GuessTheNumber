package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import static com.example.guessthenumber.RankingActivity.players;


public class MainActivity extends AppCompatActivity {

    //random
    public static final String EXTRA_MESSAGE = "com.example.guessthenumber.MESSAGE" ;

    private int nGuess, numberTries, n;
    private EditText text;
    private EditText textRanking;
    private Button halloffame;
    private Chronometer simpleChronometer;
    private int segundos;

    private String imageName;

    private AlertDialog adRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.editText);
        halloffame = (Button) findViewById(R.id.btnRanking);
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);


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

            simpleChronometer.stop();
            segundos = (int) (SystemClock.elapsedRealtime() - simpleChronometer.getBase())/1000;

            imageName = generateImageName();

            System.out.println("imageName:"+imageName);
            takePhoto();
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
        segundos = 0;
        textRanking.setText("");
        nGuess = (int)(Math.random()*100)+1;
        System.out.println(nGuess);
        numberTries = 0;

        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.start();
    }

    private void ranking(String userName){
        Intent intent = new Intent(this, RankingActivity.class);

        String imageRoute = getFilesDir().getPath() +"/"+ imageName + ".png";
        System.out.println("ruta:"+imageRoute);


        if(userName != "") {
            RankingActivity.players.add(new Player(userName, numberTries, imageRoute, segundos));

        }
        startActivity(intent);

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            saveImage(imageBitmap);
        }
    }

    public void saveImage(Bitmap bitmap) {

        File imageFile = new File(getApplicationContext().getFilesDir(), imageName + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            Toast.makeText(getApplicationContext(), "Image saved as " + imageName , Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR: Can't save the image.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public String generateImageName(){

        Long tsLong = System.currentTimeMillis()/1000;
        String generatedImageName=tsLong.toString();

        return generatedImageName;
    }

}