package ru.nikky.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCalculator(savedInstanceState);
    }

    private void createCalculator(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            String currentExpression = savedInstanceState.containsKey(Calculator.KEY_CURRENT_EXPRESSION) ?
                    savedInstanceState.getString(Calculator.KEY_CURRENT_EXPRESSION) : null;
            String calculationsHistory = savedInstanceState.containsKey(Calculator.KEY_CALCULATIONS_HISTORY) ?
                    savedInstanceState.getString(Calculator.KEY_CALCULATIONS_HISTORY) : null;
            boolean allButtonsMode = savedInstanceState.containsKey(Calculator.KEY_IS_ALL_BUTTONS_MODE) &&
                    savedInstanceState.getBoolean(Calculator.KEY_IS_ALL_BUTTONS_MODE);
            calculator = new Calculator(this, currentExpression, calculationsHistory, allButtonsMode);
        } else calculator = new Calculator(this, null, null, false);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Calculator.KEY_CURRENT_EXPRESSION, calculator.getCurrentExpression());
        outState.putString(Calculator.KEY_CALCULATIONS_HISTORY, calculator.getCalculationsHistory());
        outState.putBoolean(Calculator.KEY_IS_ALL_BUTTONS_MODE, calculator.isAllButtonsMode());
    }
}