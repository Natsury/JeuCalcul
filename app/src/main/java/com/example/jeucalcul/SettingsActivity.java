package com.example.jeucalcul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    /**********************************************************************************************/
    /**                                                                                          **/
    /**                                       Difficulté                                         **/
    /**                                                                                          **/
    /**********************************************************************************************/
    private RadioGroup difficulteGroup;



    private Button valider;


    /**********************************************************************************************/
    /**                                                                                          **/
    /**                                       Choix Vies                                         **/
    /**                                                                                          **/
    /**********************************************************************************************/

    private TextView textViewNumberSlideBar;
    private TextView textViewTimer;
    private SeekBar seekBarNbVies;

    /**********************************************************************************************/
    /**                                                                                          **/
    /**                                       Choix Temps                                        **/
    /**                                                                                          **/
    /**********************************************************************************************/

    private TextView textViewNumberSlideBarTimer;
    private SeekBar seekBarNbMin;

    /**********************************************************************************************/
    /**                                                                                          **/
    /**                                        Données                                           **/
    /**                                                                                          **/
    /**********************************************************************************************/

    private String difficulte = "";
    private int nbVies;
    private int nbMin;
    private String radioDifficulteChecked = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        difficulteGroup = findViewById(R.id.radioGroupDifficulte);
        difficulteGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton radioButtonCheck = findViewById(id);
                radioDifficulteChecked = radioButtonCheck.getText().toString();
            }
        });

        valider = findViewById(R.id.buttonValideParam);
        valider.setOnClickListener(view ->  envoieData());



        textViewNumberSlideBar = findViewById(R.id.textViewNumberSlideBar);
        seekBarNbVies = findViewById(R.id.seekBarNbVies);

        textViewNumberSlideBar.setText(String.valueOf(this.seekBarNbVies.getProgress()) );

        seekBarNbVies.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewNumberSlideBar.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        textViewNumberSlideBarTimer = findViewById(R.id.textViewNumberSlideBarTimer);
        textViewTimer = findViewById(R.id.textViewMinutesSettings);
        seekBarNbMin = findViewById(R.id.seekBarNbMin);

        textViewNumberSlideBarTimer.setText(String.valueOf(this.seekBarNbMin.getProgress()) );

        seekBarNbMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i != 0) {
                    textViewNumberSlideBarTimer.setText(String.valueOf(i));
                    textViewTimer.setText(getString(R.string.textViewMinutesSettings));
                }
                else {
                    textViewNumberSlideBarTimer.setText(getString(R.string.textViewNumberSlideBarTimer));
                    textViewTimer.setText("");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    /**
     * Compile toutes les données de la page pour l'envoyer au jeu
     */
    private void envoieData() {
        this.difficulte = radioDifficulteChecked.toLowerCase();
        this.nbVies = getNbVies();
        this.nbMin = getNbMin();

        if (!difficulte.isEmpty()){
            Intent toGameActivity = new Intent(this, GameActivity.class);
            toGameActivity.putExtra("difficulte", difficulte);
            toGameActivity.putExtra("nbVies", nbVies);
            toGameActivity.putExtra("nbMin", nbMin);
            startActivity(toGameActivity);
        } else {
            Toast.makeText(this, getString(R.string.ERROR_CHECKED_RADIO), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Regarde la textView pour le nombre de vies et retourne la valeur
     * @return Le nombre de vie que l'utilisateur choisi - INT
     */
    private int getNbVies() {
        int res;

        res = Integer.parseInt(textViewNumberSlideBar.getText().toString());
        return res;
    }

    private int getNbMin() {
        String text = textViewNumberSlideBarTimer.getText().toString();
        if (text == getString(R.string.textViewNumberSlideBarTimer))
            return 0;
        else
            return Integer.parseInt(text);
    }
}