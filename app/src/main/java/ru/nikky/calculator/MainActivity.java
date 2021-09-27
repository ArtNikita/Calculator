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

        if (savedInstanceState != null &&
                savedInstanceState.containsKey(Calculator.KEY_CURRENT_EXPRESSION)){
            String currentExpression = savedInstanceState.containsKey(Calculator.KEY_CURRENT_EXPRESSION) ?
                    savedInstanceState.getString(Calculator.KEY_CURRENT_EXPRESSION) : null;
            String calculationsHistory = savedInstanceState.containsKey(Calculator.KEY_CALCULATIONS_HISTORY) ?
                    savedInstanceState.getString(Calculator.KEY_CALCULATIONS_HISTORY) : null;
            calculator = new Calculator(this, currentExpression, calculationsHistory);
        } else calculator = new Calculator(this, null, null);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Calculator.KEY_CURRENT_EXPRESSION, calculator.getCurrentExpression());
        outState.putString(Calculator.KEY_CALCULATIONS_HISTORY, calculator.getCalculationsHistory());
    }
}