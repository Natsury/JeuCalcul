package com.example.jeucalcul;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private int lives = 3;
    private int score = 0;
    private int operand1;
    private int operand2;
    private String operator;
    private String answer = "";
    private TextView textViewCalcul;
    private TextView textViewAnswer;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonZero;
    private Button buttonMinus;
    private Button buttonPoint;
    private Button buttonConfirm;
    private Button buttonDelete;
    private MenuItem toolbarLives;
    private MenuItem toolbarScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textViewCalcul = findViewById(R.id.textViewCalcul);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonZero = findViewById(R.id.buttonZero);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonOne.setOnClickListener(view -> addChar("1"));
        buttonTwo.setOnClickListener(view -> addChar("2"));
        buttonThree.setOnClickListener(view -> addChar("3"));
        buttonFour.setOnClickListener(view -> addChar("4"));
        buttonFive.setOnClickListener(view -> addChar("5"));
        buttonSix.setOnClickListener(view -> addChar("6"));
        buttonSeven.setOnClickListener(view -> addChar("7"));
        buttonEight.setOnClickListener(view -> addChar("8"));
        buttonNine.setOnClickListener(view -> addChar("9"));
        buttonZero.setOnClickListener(view -> addChar("0"));
        buttonMinus.setOnClickListener(view -> addChar("-"));
        buttonPoint.setOnClickListener(view -> addChar("."));

        buttonDelete.setOnClickListener(view -> resetAnswer());
        buttonConfirm.setOnClickListener(view -> checkAnswer());

        resetButtons();
        randomCalcul();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_game, menu);

        toolbarLives = menu.findItem(R.id.toolbar_lives);
        toolbarScore = menu.findItem(R.id.toolbar_score);

        updateToolbar();

        return super.onCreateOptionsMenu(menu);
    }

    private void updateToolbar() {
        toolbarLives.setTitle(getString(R.string.text_lives) + " " + lives);
        toolbarScore.setTitle(getString(R.string.text_score) + " " + score);
    }

    private void updateButtons() {
        if (answer.length() == 0){
            buttonMinus.setEnabled(true);
            buttonPoint.setEnabled(false);
        } else {
            buttonMinus.setEnabled(false);
            if (answer.endsWith("."))
                buttonPoint.setEnabled(false);
            else
                buttonPoint.setEnabled(true);
        }
    }

    private void resetButtons() {
        buttonMinus.setEnabled(true);
        buttonPoint.setEnabled(false);
    }

    private void addChar(String character){
        answer+=character;
        textViewAnswer.setText(answer);
        updateButtons();
    }

    private void resetAnswer(){
        answer = "";
        textViewAnswer.setText("");
        resetButtons();
    }

    private void randomCalcul() {
        Random rand = new Random();

        operand1 = rand.nextInt(10);

        int randomOperator = rand.nextInt(4);
        switch (randomOperator) {
            case 0:
                operator = "+";
                break;
            case 1:
                operator = "-";
                break;
            case 2:
                operator = "*";
                break;
            case 3:
                operator = "/";
                break;
        }

        if (randomOperator == 3)
            operand2 = 2;
        else
            operand2 = rand.nextInt(10);

        textViewCalcul.setText(operand1 + " " + operator + " " + operand2 + " = ?");
    }

    private float trueAnswer() {
        switch (operator) {
            case "+":
                return (operand1 + operand2);
            case "-":
                return (operand1 - operand2);
            case "*":
                return (operand1 * operand2);
            case "/":
                return ((float) operand1 / operand2);
            default:
                return 0;
        }
    }

    private void checkAnswer() {
        if (Float.parseFloat(answer) == trueAnswer()){
            score++;
            updateToolbar();
            Toast.makeText(this, "Right answer", Toast.LENGTH_SHORT).show();
            resetAnswer();
            randomCalcul();
        }else{
            lives--;
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
            if (lives > 0) {
                updateToolbar();
                resetAnswer();
                randomCalcul();
            }else{
                Intent intent = new Intent(this, SaveScoreActivity.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }
        }
    }
}


