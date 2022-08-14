package com.amaromerovic.countryflaggame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.amaromerovic.countryflaggame.data.Country;
import com.amaromerovic.countryflaggame.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private Random random;
    private int index;
    private int randomButton;
    private final int COUNTRY_COUNT = 48;
    private ArrayList<Button> buttons;
    private int globalCount;
    private int points;
    private final int QUESTION_LIMIT = 10;
    private Snackbar snackbar;
    private int correctAnswer;

    private final Country[] countries = new Country[]{
            new Country(R.drawable.albania, R.string.Albania),
            new Country(R.drawable.andora, R.string.Andorra),
            new Country(R.drawable.austria, R.string.Austria),
            new Country(R.drawable.belarus, R.string.Belarus),
            new Country(R.drawable.belgium, R.string.Belgium),
            new Country(R.drawable.bosnia, R.string.BosniaAndHerzegovina),
            new Country(R.drawable.bulgaria, R.string.Bulgaria),
            new Country(R.drawable.croatia, R.string.Croatia),
            new Country(R.drawable.cyprus, R.string.Cyprus),
            new Country(R.drawable.czech_republic, R.string.CzechRepublic),
            new Country(R.drawable.denmark, R.string.Denmark),
            new Country(R.drawable.estonia, R.string.Estonia),
            new Country(R.drawable.faroe_islands, R.string.FaroeIslands),
            new Country(R.drawable.finland, R.string.Finland),
            new Country(R.drawable.france, R.string.France),
            new Country(R.drawable.germany, R.string.Germany),
            new Country(R.drawable.gibraltar, R.string.Gibraltar),
            new Country(R.drawable.greece, R.string.Greece),
            new Country(R.drawable.greenland, R.string.Greenland),
            new Country(R.drawable.hungary, R.string.Hungary),
            new Country(R.drawable.iceland, R.string.Iceland),
            new Country(R.drawable.ireland, R.string.Ireland),
            new Country(R.drawable.italy, R.string.Italy),
            new Country(R.drawable.latvia, R.string.Latvia),
            new Country(R.drawable.liechtenstein, R.string.Liechtenstein),
            new Country(R.drawable.lithuania, R.string.Lithuania),
            new Country(R.drawable.luxembourg, R.string.Luxembourg),
            new Country(R.drawable.macedonia, R.string.Macedonia),
            new Country(R.drawable.malta, R.string.Malta),
            new Country(R.drawable.moldova, R.string.Moldova),
            new Country(R.drawable.monaco, R.string.Monaco),
            new Country(R.drawable.montenegro, R.string.Montenegro),
            new Country(R.drawable.netherlands, R.string.Netherlands),
            new Country(R.drawable.norway, R.string.Norway),
            new Country(R.drawable.poland, R.string.Poland),
            new Country(R.drawable.portugal, R.string.Portugal),
            new Country(R.drawable.romania, R.string.Romania),
            new Country(R.drawable.russia, R.string.Russia),
            new Country(R.drawable.san_marino, R.string.SanMarino),
            new Country(R.drawable.serbia, R.string.Serbia),
            new Country(R.drawable.slovakia, R.string.Slovakia),
            new Country(R.drawable.slovenia, R.string.Slovenia),
            new Country(R.drawable.spain, R.string.Spain),
            new Country(R.drawable.sweden, R.string.Sweden),
            new Country(R.drawable.switzerland, R.string.Switzerland),
            new Country(R.drawable.turkey, R.string.Turkey),
            new Country(R.drawable.ukraine, R.string.Ukraine),
            new Country(R.drawable.united_kingdom, R.string.UnitedKingdom),
            new Country(R.drawable.vatican, R.string.VaticanCity),
    };


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        globalCount = 1;
        points = 0;
        buttons = new ArrayList<>();
        random = new Random();
        buttons.add(mainBinding.userOption1);
        buttons.add(mainBinding.userOption2);
        buttons.add(mainBinding.userOption3);
        buttons.add(mainBinding.userOption4);


        mainBinding.questionNumber.setText(globalCount + "/" + QUESTION_LIMIT);
        generateQuestions();


        mainBinding.nextButton.setOnClickListener(view -> {
            globalCount++;
            if (globalCount <= 10){
                generateQuestions();
                mainBinding.questionNumber.setText(globalCount + "/" + QUESTION_LIMIT);
                mainBinding.userOption1.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                mainBinding.userOption2.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                mainBinding.userOption3.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                mainBinding.userOption4.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                mainBinding.userOption1.setClickable(true);
                mainBinding.userOption2.setClickable(true);
                mainBinding.userOption3.setClickable(true);
                mainBinding.userOption4.setClickable(true);
            } else {
                Intent intent = new Intent(getApplicationContext(), endScreen.class);
                intent.putExtra("Points", points);
                startActivity(intent);
                finish();
            }
        });

        mainBinding.userOption1.setOnClickListener(view -> {
            checkAnswer(mainBinding.userOption1.getText().toString().trim(), mainBinding.userOption1);
            mainBinding.userOption1.setClickable(false);
            mainBinding.userOption2.setClickable(false);
            mainBinding.userOption3.setClickable(false);
            mainBinding.userOption4.setClickable(false);
        });

        mainBinding.userOption2.setOnClickListener(view -> {
            checkAnswer(mainBinding.userOption2.getText().toString().trim(), mainBinding.userOption2);
            mainBinding.userOption1.setClickable(false);
            mainBinding.userOption2.setClickable(false);
            mainBinding.userOption3.setClickable(false);
            mainBinding.userOption4.setClickable(false);
        });

        mainBinding.userOption3.setOnClickListener(view -> {
            checkAnswer(mainBinding.userOption3.getText().toString().trim(), mainBinding.userOption3);
            mainBinding.userOption1.setClickable(false);
            mainBinding.userOption2.setClickable(false);
            mainBinding.userOption3.setClickable(false);
            mainBinding.userOption4.setClickable(false);
        });

        mainBinding.userOption4.setOnClickListener(view -> {
            checkAnswer(mainBinding.userOption4.getText().toString().trim(), mainBinding.userOption4);
            mainBinding.userOption1.setClickable(false);
            mainBinding.userOption2.setClickable(false);
            mainBinding.userOption3.setClickable(false);
            mainBinding.userOption4.setClickable(false);
        });




    }

    private void generateQuestions(){
        index = random.nextInt(COUNTRY_COUNT);
        mainBinding.imageView.setImageResource(countries[index].getPicture());
        randomButton = random.nextInt(buttons.size());
        buttons.get(randomButton).setText(countries[index].getName());
        correctAnswer = index;
        buttons.remove(randomButton);

        while (buttons.size() != 0){
            index = random.nextInt(COUNTRY_COUNT);
            randomButton = random.nextInt(buttons.size());
            String helpButtonOne = mainBinding.userOption1.getText().toString().trim();
            String helpButtonTwo = mainBinding.userOption2.getText().toString().trim();
            String helpButtonThree = mainBinding.userOption3.getText().toString().trim();
            String helpButtonFour = mainBinding.userOption4.getText().toString().trim();
            String countryName = getString(countries[index].getName());
            if (helpButtonOne.equals(countryName) || helpButtonTwo.equals(countryName) || helpButtonThree.equals(countryName) || helpButtonFour.equals(countryName)){
                continue;
            } else {
                buttons.get(randomButton).setText(countries[index].getName());
                buttons.remove(randomButton);
            }
        }

        buttons.add(mainBinding.userOption1);
        buttons.add(mainBinding.userOption2);
        buttons.add(mainBinding.userOption3);
        buttons.add(mainBinding.userOption4);
    }

    private void checkAnswer(String answer, Button button){
        int message = R.string.incorrect;
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        if (answer.equals(getString(countries[correctAnswer].getName()))){
            message = R.string.correct;
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
            points++;
        }
        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(100);
        snackbar = Snackbar.make(this.mainBinding.getRoot(), message, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.black));
        snackbar.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
        View view = snackbar.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.CENTER | Gravity.TOP;
        params.width=FrameLayout.LayoutParams.WRAP_CONTENT;
        TextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
        if(tv!=null) {
            tv.setGravity(Gravity.CENTER);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        }
        view.setLayoutParams(params);
        snackbar.show();
    }


}