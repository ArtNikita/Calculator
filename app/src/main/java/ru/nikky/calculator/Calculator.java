package ru.nikky.calculator;

import android.content.Context;
import android.os.Vibrator;

import java.math.BigDecimal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

public class Calculator {

    private final AppCompatActivity mainActivity;
    //first row buttons
    private AppCompatButton changeTrigonometryModeButton;
    private AppCompatButton degRadSwitchButton;
    private AppCompatButton sinButton;
    private AppCompatButton cosButton;
    private AppCompatButton tanButton;
    //second row buttons
    private AppCompatButton powerButton;
    private AppCompatButton lgButton;
    private AppCompatButton lnButton;
    private AppCompatButton openParenthesisButton;
    private AppCompatButton closeParenthesisButton;
    //third row buttons
    private AppCompatButton sqrtButton;
    private AppCompatButton clearButton;
    private AppCompatButton backspaceButton;
    private AppCompatButton percentButton;
    private AppCompatButton divisionButton;
    //fourth row buttons
    private AppCompatButton factorialButton;
    private AppCompatButton digitSevenButton;
    private AppCompatButton digitEightButton;
    private AppCompatButton digitNineButton;
    private AppCompatButton multiplicationButton;
    //fifth row buttons
    private AppCompatButton reverseNumberButton;
    private AppCompatButton digitFourButton;
    private AppCompatButton digitFiveButton;
    private AppCompatButton digitSixButton;
    private AppCompatButton minusButton;
    //sixth row buttons
    private AppCompatButton piButton;
    private AppCompatButton digitOneButton;
    private AppCompatButton digitTwoButton;
    private AppCompatButton digitThreeButton;
    private AppCompatButton plusButton;
    //seventh row buttons
    private AppCompatButton changeLayoutBackButton;
    private AppCompatButton changeLayoutAndEButton;
    private AppCompatButton digitZeroButton;
    private AppCompatButton dotButton;
    private AppCompatButton equalsButton;

    private AppCompatTextView calculationsTextView;
    private AppCompatTextView calculationsHistoryTextView;

    private String currentExpression;

    private boolean numTypingMode;
    private boolean dotEnteredMode;
    private int notClosedOpenParenthesisCount;

    private static final char DOT = '.';
    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSE_PARENTHESIS = ')';
    private static final char ZERO = '0';
    private static final String INITIAL_EXPRESSION = "0";
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLICATION = '\u00d7';
    private static final char DIVISION = '/';

    private static final int VIBRATION_DURATION = 20;

    public Calculator(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;
        initViews();
        setOnClickListeners();
        initCurrentExpression();
        updateCalculationsTextView();
        initModesAndParams();
    }

    private void initViews() {
        //first row buttons
        changeTrigonometryModeButton = mainActivity.findViewById(R.id.change_trigonometry_mode_button);
        degRadSwitchButton = mainActivity.findViewById(R.id.deg_rad_switch_button);
        sinButton = mainActivity.findViewById(R.id.sin_button);
        cosButton = mainActivity.findViewById(R.id.cos_button);
        tanButton = mainActivity.findViewById(R.id.tan_button);
        //second row buttons
        powerButton = mainActivity.findViewById(R.id.power_button);
        lgButton = mainActivity.findViewById(R.id.lg_button);
        lnButton = mainActivity.findViewById(R.id.ln_button);
        openParenthesisButton = mainActivity.findViewById(R.id.open_parenthesis_button);
        closeParenthesisButton = mainActivity.findViewById(R.id.close_parenthesis_button);
        //third row buttons
        sqrtButton = mainActivity.findViewById(R.id.sqrt_button);
        clearButton = mainActivity.findViewById(R.id.clear_button);
        backspaceButton = mainActivity.findViewById(R.id.backspace_button);
        percentButton = mainActivity.findViewById(R.id.percent_button);
        divisionButton = mainActivity.findViewById(R.id.division_button);
        //fourth row buttons
        factorialButton = mainActivity.findViewById(R.id.factorial_button);
        digitSevenButton = mainActivity.findViewById(R.id.digit_seven_button);
        digitEightButton = mainActivity.findViewById(R.id.digit_eight_button);
        digitNineButton = mainActivity.findViewById(R.id.digit_nine_button);
        multiplicationButton = mainActivity.findViewById(R.id.multiplication_button);
        //fifth row buttons
        reverseNumberButton = mainActivity.findViewById(R.id.reverse_number_button);
        digitFourButton = mainActivity.findViewById(R.id.digit_four_button);
        digitFiveButton = mainActivity.findViewById(R.id.digit_five_button);
        digitSixButton = mainActivity.findViewById(R.id.digit_six_button);
        minusButton = mainActivity.findViewById(R.id.minus_button);
        //sixth row buttons
        piButton = mainActivity.findViewById(R.id.pi_button);
        digitOneButton = mainActivity.findViewById(R.id.digit_one_button);
        digitTwoButton = mainActivity.findViewById(R.id.digit_two_button);
        digitThreeButton = mainActivity.findViewById(R.id.digit_three_button);
        plusButton = mainActivity.findViewById(R.id.plus_button);
        //seventh row buttons
        changeLayoutBackButton = mainActivity.findViewById(R.id.change_layout_back_button);
        changeLayoutAndEButton = mainActivity.findViewById(R.id.change_layout_and_e_button);
        digitZeroButton = mainActivity.findViewById(R.id.digit_zero_button);
        dotButton = mainActivity.findViewById(R.id.dot_button);
        equalsButton = mainActivity.findViewById(R.id.equals_button);

        calculationsTextView = mainActivity.findViewById(R.id.calculations_text_view);
        calculationsHistoryTextView = mainActivity.findViewById(R.id.calculation_history_text_view);
    }

    private void setOnClickListeners() {
        setDigitButtonsOnClickListeners();
        setDotButtonOnClickListener();
        setOperationButtonsOnClickListeners();
        setClearButtonOnClickListener();
        setBackspaceButtonOnClickListener();
        setEqualsButtonOnClickListener();
    }

    private void setDigitButtonsOnClickListeners() {
        digitZeroButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitOneButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitTwoButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitThreeButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitFourButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitFiveButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitSixButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitSevenButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitEightButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
        digitNineButton.setOnClickListener(v -> digitButtonPressed((AppCompatButton) v));
    }

    private void setOperationButtonsOnClickListeners() {
        plusButton.setOnClickListener(v -> operationButtonPressed((AppCompatButton) v));
        minusButton.setOnClickListener(v -> operationButtonPressed((AppCompatButton) v));
        multiplicationButton.setOnClickListener(v -> operationButtonPressed((AppCompatButton) v));
        divisionButton.setOnClickListener(v -> operationButtonPressed((AppCompatButton) v));
    }

    private void setDotButtonOnClickListener() {
        dotButton.setOnClickListener(v -> {
            vibrate();
            if (dotEnteredMode) return;
            if (!numTypingMode) {
                currentExpression += "0";
            }
            currentExpression += DOT;
            dotEnteredMode = true;
            numTypingMode = true;
            updateCalculationsTextView();
        });
    }

    private void setEqualsButtonOnClickListener() {
        addAllClosingParenthesis();
        equalsButton.setOnClickListener(v -> equalsButtonPressed());
    }

    private void setBackspaceButtonOnClickListener() {
        backspaceButton.setOnClickListener(v -> {
            vibrate();
            if (currentExpression.length() == 1) {
                currentExpression = INITIAL_EXPRESSION;
                updateCalculationsTextView();
                return;
            }
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            initModesAndParams();
            updateCalculationsTextView();
        });
    }

    private void setClearButtonOnClickListener() {
        clearButton.setOnClickListener(v -> {
            vibrate();
            calculationsHistoryTextView.setText("");
            initCurrentExpression();
            updateCalculationsTextView();
            initModesAndParams();
        });
    }

    private void digitButtonPressed(AppCompatButton button) {
        vibrate();
        numTypingMode = true;
        if (currentExpression.equals(INITIAL_EXPRESSION)) {
            currentExpression = button.getText().toString();
            updateCalculationsTextView();
            return;
        }
        if (currentExpression.charAt(currentExpression.length() - 1) == ZERO &&
                !Character.isDigit(currentExpression.charAt(currentExpression.length() - 2)) &&
                currentExpression.charAt(currentExpression.length() - 2) != DOT) {
            return;
        }
        currentExpression += button.getText().toString();
        updateCalculationsTextView();
    }

    private void operationButtonPressed(AppCompatButton button) {
        vibrate();
        if (!checkIfCanPressOperationButton()) return;
        if (currentExpression.charAt(currentExpression.length() - 1) == DOT)
            currentExpression += ZERO;
        numTypingMode = false;
        dotEnteredMode = false;
        currentExpression += button.getText().toString();
        updateCalculationsTextView();
    }

    private void initCurrentExpression() {
        currentExpression = INITIAL_EXPRESSION;
    }

    private void updateCalculationsTextView() {
        calculationsTextView.setText(currentExpression);
    }

    private void initModesAndParams() {
        numTypingMode = isNumTypingMode();
        dotEnteredMode = isDotEnteredMode();
        notClosedOpenParenthesisCount = countNotClosedOpenParenthesis();
    }

    private boolean isNumTypingMode() {
        char lastChar = currentExpression.charAt(currentExpression.length() - 1);
        return Character.isDigit(lastChar) || lastChar == DOT;
    }

    private boolean isDotEnteredMode() {
        if (!numTypingMode) return false;
        for (int i = currentExpression.length() - 1; i >= 0; i--) {
            if (currentExpression.charAt(i) == DOT) return true;
            if (!Character.isDigit(currentExpression.charAt(i))) return false;
        }
        return false;
    }

    private int countNotClosedOpenParenthesis() {
        int counter = 0;
        for (int i = 0; i < currentExpression.length(); i++) {
            if (currentExpression.charAt(i) == OPEN_PARENTHESIS) counter++;
            else if (currentExpression.charAt(i) == CLOSE_PARENTHESIS) counter--;
        }
        return counter;
    }

    private void addAllClosingParenthesis() {
        if (notClosedOpenParenthesisCount != 0) {
            StringBuilder closeParenthesisSB = new StringBuilder(notClosedOpenParenthesisCount);
            for (int i = 0; i < notClosedOpenParenthesisCount; i++) {
                closeParenthesisSB.append(CLOSE_PARENTHESIS);
            }
            currentExpression += closeParenthesisSB.toString();
            updateCalculationsTextView();
            notClosedOpenParenthesisCount = 0;
        }
    }

    private boolean checkIfCanPressOperationButton() {
        char currentExpressionLastChar = currentExpression.charAt(currentExpression.length() - 1);
        return currentExpressionLastChar != PLUS &&
                currentExpressionLastChar != MINUS &&
                currentExpressionLastChar != MULTIPLICATION &&
                currentExpressionLastChar != DIVISION;
    }

    private void equalsButtonPressed() {
        vibrate();
        BigDecimal result = evaluateExpression();
        String stringResult = String.valueOf(result);
        if (stringResult.length() > 2 &&
                stringResult.charAt(stringResult.length() - 1) == ZERO &&
                stringResult.charAt(stringResult.length() - 2) == DOT) {
            stringResult = stringResult.substring(0, stringResult.length() - 2);
        }
        calculationsHistoryTextView.append(currentExpression + "=" + stringResult + "\n");
        currentExpression = stringResult;
        updateCalculationsTextView();
        initModesAndParams();
    }

    private BigDecimal evaluateExpression() {
        return new EvaluateExpression(currentExpression).evaluate();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) mainActivity.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(VIBRATION_DURATION);
    }
}
