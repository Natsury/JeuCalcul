package com.example.jeucalcul;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private int lives;
    private int time;
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
    private Button buttonPause;
    private Button buttonSkip;
    private MenuItem toolbarLives;
    private MenuItem toolbarScore;
    private String difficulte;
    private CountDownTimer countDownTimer;
    private TextView textViewTimer;
    private boolean enPause = false;
    private long timeLeft = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();

        lives = intent.getIntExtra("nbVies", 3);
        time = intent.getIntExtra("nbMin", 0);
        difficulte = intent.getStringExtra("difficulte");
        textViewTimer = findViewById(R.id.textViewMinutesSettings);
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
        buttonPause = findViewById(R.id.buttonPause);
        buttonSkip = findViewById(R.id.buttonSkip);

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

        buttonPause.setOnClickListener(view -> pauseGame());
        buttonSkip.setOnClickListener(view -> nextCalcul());

        resetButtons();
        randomCalcul();

        if (time != 0){
            timeLeft = (60000*(long)time+1000);
            startTimer();
        }else {
            buttonPause.setVisibility(View.INVISIBLE);
            buttonSkip.setVisibility(View.INVISIBLE);
            textViewTimer.setText("");
        }
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
            buttonConfirm.setEnabled(false);
        } else {
            boolean point = answer.endsWith(".");
            boolean minus = answer.endsWith("-");
            buttonMinus.setEnabled(false);
            if (point)
                buttonPoint.setEnabled(false);
            else
                buttonPoint.setEnabled(true);
            if (point || minus)
                buttonConfirm.setEnabled(false);
            else
                buttonConfirm.setEnabled(true);
        }
    }

    private void resetButtons() {
        buttonMinus.setEnabled(true);
        buttonPoint.setEnabled(false);
        buttonConfirm.setEnabled(false);
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

        operand1 = randomNumber();

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
            operand2 = randomNumber();

        textViewCalcul.setText(operand1 + " " + operator + " " + operand2 + " = ?");
    }

    private int randomNumber(){
        Random rand = new Random();
        int res;

        switch (difficulte){
            case "facile":
            case "easy":
                res = rand.nextInt(10);
                break;
            case "difficile":
            case "hard":
                res = rand.nextInt(100);
                break;
            case "impossible":
                res = rand.nextInt(999);
                break;
            default:
                res = 0;
        }
        return res;
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

    private void toGameOver(){
        Intent intent = new Intent(this, SaveScoreActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("difficulte", difficulte);
        startActivity(intent);
    }

    private void checkAnswer() {
        if (Float.parseFloat(answer) == trueAnswer()){
            score++;
            updateToolbar();
            Toast.makeText(this, getString(R.string.GoodAnserGameActivity), Toast.LENGTH_SHORT).show();
            if (enPause)
                enablePause();
            else
                nextCalcul();
        }else{
            lives--;
            Toast.makeText(this, getString(R.string.BadAnserGameActivity), Toast.LENGTH_SHORT).show();
            if (lives > 0) {
                updateToolbar();
                if (enPause)
                    enablePause();
                else
                    nextCalcul();
            }else{
                toGameOver();
            }
        }
    }

    private void pauseGame(){
        if (enPause){
            enPause = false;
            disablePause();
            nextCalcul();
            startTimer();
        }else {
            enPause = true;
            buttonPause.setEnabled(false);
            Toast.makeText(this, getString(R.string.PauseTimer), Toast.LENGTH_SHORT).show();
        }
    }

    private void enablePause(){
        pauseTimer();
        buttonOne.setEnabled(false);
        buttonTwo.setEnabled(false);
        buttonThree.setEnabled(false);
        buttonFour.setEnabled(false);
        buttonFive.setEnabled(false);
        buttonSix.setEnabled(false);
        buttonSeven.setEnabled(false);
        buttonEight.setEnabled(false);
        buttonNine.setEnabled(false);
        buttonZero.setEnabled(false);
        buttonMinus.setEnabled(false);
        buttonPoint.setEnabled(false);
        buttonConfirm.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonSkip.setEnabled(false);
        buttonPause.setText(getString(R.string.TextPlay));
        Toast.makeText(this, getString(R.string.PausedTimer), Toast.LENGTH_SHORT).show();
        buttonPause.setEnabled(true);
    }

    private void disablePause(){
        startTimer();
        buttonOne.setEnabled(true);
        buttonTwo.setEnabled(true);
        buttonThree.setEnabled(true);
        buttonFour.setEnabled(true);
        buttonFive.setEnabled(true);
        buttonSix.setEnabled(true);
        buttonSeven.setEnabled(true);
        buttonEight.setEnabled(true);
        buttonNine.setEnabled(true);
        buttonZero.setEnabled(true);
        buttonMinus.setEnabled(true);
        buttonPoint.setEnabled(true);
        buttonConfirm.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonSkip.setEnabled(true);
        buttonPause.setText(getString(R.string.TextPause));
    }

    private void nextCalcul() {
        resetAnswer();
        randomCalcul();
    }

    private void pauseTimer() {
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long tempsRestant) {
                timeLeft = tempsRestant;
                textViewTimer.setText(getString(R.string.textViewTimer) + " " + tempsRestant / 1000);
            }

            @Override
            public void onFinish() {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                    toGameOver();
                }
            }
        };
        countDownTimer.start();
    }
}


