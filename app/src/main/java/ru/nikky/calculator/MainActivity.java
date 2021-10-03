package ru.nikky.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private AppCompatImageView settingsButtonImageView;

    private static final String EXPRESSION_KEY = "EXPRESSION_KEY";
    private static final String CALCULATOR_ACTION = "ru.nikky.calculator.calculate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCalculator(savedInstanceState, getIntent());

        setupSettingsButton();
    }

    private void setupSettingsButton() {
        settingsButtonImageView = findViewById(R.id.settings_button_image_view);
        settingsButtonImageView.setOnClickListener(v -> {
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        });
    }

    private void createCalculator(Bundle savedInstanceState, Intent inputIntent) {
        if (inputIntent != null && inputIntent.getAction().equals(CALCULATOR_ACTION)){
            String currentExpression = inputIntent.getStringExtra(EXPRESSION_KEY);
            calculator = new Calculator(this, currentExpression, null, false, true);
        } else if (savedInstanceState != null){
            String currentExpression = savedInstanceState.containsKey(Calculator.KEY_CURRENT_EXPRESSION) ?
                    savedInstanceState.getString(Calculator.KEY_CURRENT_EXPRESSION) : null;
            String calculationsHistory = savedInstanceState.containsKey(Calculator.KEY_CALCULATIONS_HISTORY) ?
                    savedInstanceState.getString(Calculator.KEY_CALCULATIONS_HISTORY) : null;
            boolean allButtonsMode = savedInstanceState.containsKey(Calculator.KEY_IS_ALL_BUTTONS_MODE) &&
                    savedInstanceState.getBoolean(Calculator.KEY_IS_ALL_BUTTONS_MODE);
            calculator = new Calculator(this, currentExpression, calculationsHistory, allButtonsMode, false);
        } else calculator = new Calculator(this, null, null, false, false);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Calculator.KEY_CURRENT_EXPRESSION, calculator.getCurrentExpression());
        outState.putString(Calculator.KEY_CALCULATIONS_HISTORY, calculator.getCalculationsHistory());
        outState.putBoolean(Calculator.KEY_IS_ALL_BUTTONS_MODE, calculator.isAllButtonsMode());
    }
}