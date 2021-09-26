package ru.nikky.calculator;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.button.MaterialButton;

import java.math.BigDecimal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

public class Calculator {

    private final AppCompatActivity mainActivity;
    //first row buttons
    private MaterialButton changeTrigonometryModeButton;
    private MaterialButton degRadSwitchButton;
    private MaterialButton sinButton;
    private MaterialButton cosButton;
    private MaterialButton tanButton;
    //second row buttons
    private MaterialButton powerButton;
    private MaterialButton lgButton;
    private MaterialButton lnButton;
    private MaterialButton openParenthesisButton;
    private MaterialButton closeParenthesisButton;
    //third row buttons
    private MaterialButton sqrtButton;
    private MaterialButton clearButton;
    private MaterialButton backspaceButton;
    private MaterialButton percentButton;
    private MaterialButton divisionButton;
    //fourth row buttons
    private MaterialButton factorialButton;
    private MaterialButton digitSevenButton;
    private MaterialButton digitEightButton;
    private MaterialButton digitNineButton;
    private MaterialButton multiplicationButton;
    //fifth row buttons
    private MaterialButton reverseNumberButton;
    private MaterialButton digitFourButton;
    private MaterialButton digitFiveButton;
    private MaterialButton digitSixButton;
    private MaterialButton minusButton;
    //sixth row buttons
    private MaterialButton piButton;
    private MaterialButton digitOneButton;
    private MaterialButton digitTwoButton;
    private MaterialButton digitThreeButton;
    private MaterialButton plusButton;
    //seventh row buttons
    private MaterialButton eButton;
    private MaterialButton changeLayoutButton;
    private MaterialButton digitZeroButton;
    private MaterialButton dotButton;
    private MaterialButton equalsButton;

    private FrameLayout changeTrigonometryModeButtonFrameLayout;
    private FrameLayout degRadSwitchButtonFrameLayout;
    private FrameLayout sinButtonFrameLayout;
    private FrameLayout cosButtonFrameLayout;
    private FrameLayout tanButtonFrameLayout;
    private FrameLayout powerButtonFrameLayout;
    private FrameLayout lgButtonFrameLayout;
    private FrameLayout lnButtonFrameLayout;
    private FrameLayout openParenthesisButtonFrameLayout;
    private FrameLayout closeParenthesisButtonFrameLayout;
    private FrameLayout sqrtButtonFrameLayout;
    private FrameLayout factorialButtonFrameLayout;
    private FrameLayout reverseNumberButtonFrameLayout;
    private FrameLayout piButtonFrameLayout;
    private FrameLayout eButtonFrameLayout;

    private AppCompatTextView calculationsTextView;
    private AppCompatTextView calculationsHistoryTextView;

    private String currentExpression;

    private boolean numTypingMode;
    private boolean dotEnteredMode;
    private int notClosedOpenParenthesisCount;
    private boolean allButtonsMode;

    private FrameLayout[] additionalButtonsFrameLayouts;

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
        initAdditionalButtonsFrameLayouts();
        initModesAndParams();
        initButtonsMode();
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
        eButton = mainActivity.findViewById(R.id.e_button);
        changeLayoutButton = mainActivity.findViewById(R.id.change_layout_button);
        digitZeroButton = mainActivity.findViewById(R.id.digit_zero_button);
        dotButton = mainActivity.findViewById(R.id.dot_button);
        equalsButton = mainActivity.findViewById(R.id.equals_button);

        changeTrigonometryModeButtonFrameLayout = mainActivity.findViewById(R.id.change_trigonometry_mode_button_frame_layout);
        degRadSwitchButtonFrameLayout = mainActivity.findViewById(R.id.deg_rad_switch_button_frame_layout);
        sinButtonFrameLayout = mainActivity.findViewById(R.id.sin_button_frame_layout);
        cosButtonFrameLayout = mainActivity.findViewById(R.id.cos_button_frame_layout);
        tanButtonFrameLayout = mainActivity.findViewById(R.id.tan_button_frame_layout);
        powerButtonFrameLayout = mainActivity.findViewById(R.id.power_button_frame_layout);
        lgButtonFrameLayout = mainActivity.findViewById(R.id.lg_button_frame_layout);
        lnButtonFrameLayout = mainActivity.findViewById(R.id.ln_button_frame_layout);
        openParenthesisButtonFrameLayout = mainActivity.findViewById(R.id.open_parenthesis_button_frame_layout);
        closeParenthesisButtonFrameLayout = mainActivity.findViewById(R.id.close_parenthesis_button_frame_layout);
        sqrtButtonFrameLayout = mainActivity.findViewById(R.id.sqrt_button_frame_layout);
        factorialButtonFrameLayout = mainActivity.findViewById(R.id.factorial_button_frame_layout);
        reverseNumberButtonFrameLayout = mainActivity.findViewById(R.id.reverse_number_button_frame_layout);
        piButtonFrameLayout = mainActivity.findViewById(R.id.pi_button_frame_layout);
        eButtonFrameLayout = mainActivity.findViewById(R.id.e_button_frame_layout);

        calculationsTextView = mainActivity.findViewById(R.id.calculations_text_view);
        calculationsHistoryTextView = mainActivity.findViewById(R.id.calculation_history_text_view);
    }

    private void initAdditionalButtonsFrameLayouts() {
        additionalButtonsFrameLayouts = new FrameLayout[]{
                changeTrigonometryModeButtonFrameLayout,
                degRadSwitchButtonFrameLayout,
                sinButtonFrameLayout,
                cosButtonFrameLayout,
                tanButtonFrameLayout,
                powerButtonFrameLayout,
                lgButtonFrameLayout,
                lnButtonFrameLayout,
                openParenthesisButtonFrameLayout,
                closeParenthesisButtonFrameLayout,
                sqrtButtonFrameLayout,
                factorialButtonFrameLayout,
                reverseNumberButtonFrameLayout,
                piButtonFrameLayout,
                eButtonFrameLayout};
    }

    private void setOnClickListeners() {
        setDigitButtonsOnClickListeners();
        setDotButtonOnClickListener();
        setOperationButtonsOnClickListeners();
        setClearButtonOnClickListener();
        setBackspaceButtonOnClickListener();
        setEqualsButtonOnClickListener();
        setChangeLayoutAndEButtonOnClickListener();
    }

    private void setChangeLayoutAndEButtonOnClickListener() {
        changeLayoutButton.setOnClickListener(v -> {
            vibrate();
            if (allButtonsMode) {
                allButtonsMode = false;
                hideAdditionalButtonsFrameLayouts();
            } else {
                allButtonsMode = true;
                showAdditionalButtonsFrameLayouts();
            }
        });
    }

    private void showAdditionalButtonsFrameLayouts() {
        for (FrameLayout frameLayout : additionalButtonsFrameLayouts) {
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

    private void hideAdditionalButtonsFrameLayouts() {
        for (FrameLayout frameLayout : additionalButtonsFrameLayouts) {
            frameLayout.setVisibility(View.GONE);
        }
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
                currentExpression += ZERO;
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

    private void initButtonsMode() {
        allButtonsMode = false;
        hideAdditionalButtonsFrameLayouts();
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
