package com.example.homework_m2_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private TextView resultTextView;
    private StringBuilder inputStringBuilder;
    private double firstOperand;
    private String pendingOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result_text_view);
        inputStringBuilder = new StringBuilder();
    }

    public void onDigitClick(View view) {
        String digit = ((TextView) view).getText().toString();
        inputStringBuilder.append(digit);
        updateResultTextView();
    }

    public void onOperationClick(View view) {
        String operation = ((TextView) view).getText().toString();
        if (inputStringBuilder.length() > 0) {
            firstOperand = Double.parseDouble(inputStringBuilder.toString());
            inputStringBuilder.setLength(0);
        }
        pendingOperation = operation;
    }

    public void onResultClick(View view) {
        if (pendingOperation != null && inputStringBuilder.length() > 0) {
            double secondOperand = Double.parseDouble(inputStringBuilder.toString());
            inputStringBuilder.setLength(0);

            switch (pendingOperation) {
                case "+":
                    firstOperand += secondOperand;
                    break;
                case "-":
                    firstOperand -= secondOperand;
                    break;
                case "*":
                    firstOperand *= secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        firstOperand /= secondOperand;
                    } else {
                        firstOperand = 0;
                    }
                    break;
            }

            pendingOperation = null;
            inputStringBuilder.append(firstOperand);
            updateResultTextView();
        }
    }


    public void onAC(View view) {
        if (inputStringBuilder.length() > 0) {
            inputStringBuilder.setLength(0);
            inputStringBuilder.append("0");
        }


        firstOperand = 0;
        pendingOperation = null;
        updateResultTextView();
    }


    private void updateResultTextView() {
        resultTextView.setText(inputStringBuilder.toString());
    }
}