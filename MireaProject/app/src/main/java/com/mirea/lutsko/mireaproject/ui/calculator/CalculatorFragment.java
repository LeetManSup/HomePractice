package com.mirea.lutsko.mireaproject.ui.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mirea.lutsko.mireaproject.R;

import java.util.Objects;

public class CalculatorFragment extends Fragment {
    private TextView resultTextView;

    private String number1 = "";
    private String number2 = "";
    private boolean operationMark = false;

    Operation operation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        resultTextView = (TextView) view.findViewById(R.id.resultTextView);

        //Числа
        view.findViewById(R.id.buttonDot).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum1).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum2).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum3).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum4).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum5).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum6).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum7).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum8).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum9).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum0).setOnClickListener(this::onClickNumber);
        view.findViewById(R.id.buttonNum000).setOnClickListener(this::onClickNumber);

        //Операции
        view.findViewById(R.id.buttonPercent).setOnClickListener(this::onClickOperation);
        view.findViewById(R.id.buttonPlus).setOnClickListener(this::onClickOperation);
        view.findViewById(R.id.buttonMinus).setOnClickListener(this::onClickOperation);
        view.findViewById(R.id.buttonMultiply).setOnClickListener(this::onClickOperation);
        view.findViewById(R.id.buttonDivide).setOnClickListener(this::onClickOperation);

        //Управление
        view.findViewById(R.id.buttonAC).setOnClickListener(this::onClickAC);
        view.findViewById(R.id.buttonDel).setOnClickListener(this::onClickDel);
        view.findViewById(R.id.buttonEquals).setOnClickListener(this::onClickEquals);

        return view;
    }

    private void onClickNumber(View view) {
        Button button = (Button) view;

        if (!operationMark) {
            number1 += button.getText().toString();
            resultTextView.setText(number1);
        }
        else {
            number2 += button.getText().toString();
            resultTextView.setText(number2);
        }
    }

    private void onClickOperation(View view) {
        Button button = (Button) view;

        if (!number1.equals("")) {
            switch (button.getText().toString()) {
                case ("%"):
                    number1 = String.valueOf(Double.parseDouble(number1) / 100);
                    resultTextView.setText(number1);
                    break;

                case ("+"):
                    operationMark = true;
                    operation = Operation.ADDITION;
                    resultTextView.setText("");
                    break;

                case ("-"):
                    operationMark = true;
                    operation = Operation.SUBTRACTION;
                    resultTextView.setText("");
                    break;

                case ("×"):
                    operationMark = true;
                    operation = Operation.MULTIPLICATION;
                    resultTextView.setText("");
                    break;

                case ("÷"):
                    operationMark = true;
                    operation = Operation.DIVISION;
                    resultTextView.setText("");
                    break;
            }
        }
    }

    private void onClickAC(View view) {
        resultTextView.setText("");
        number1 = "";
        number2 = "";
        operationMark = false;
    }

    private void onClickDel(View view) {
        String string = resultTextView.getText().toString();

        if (string.length() > 0) {
            string = string.substring(0, string.length() - 1);
        }

        if (!operationMark) {
            number1 = string;
        }
        else {
            number2 = string;
        }

        resultTextView.setText(string);
    }

    @SuppressLint("SetTextI18n")
    private void onClickEquals(View view) {
        if (operationMark && !Objects.equals(number1, "") && !Objects.equals(number2, "")) {
            double result;
            switch (operation) {
                case ADDITION:
                    result = Double.parseDouble(number1) + Double.parseDouble(number2);
                    resultTextView.setText(Double.toString(result));
                    operationMark = false;
                    number1 = Double.toString(result);
                    number2 = "";
                    break;

                case SUBTRACTION:
                    result = Double.parseDouble(number1) - Double.parseDouble(number2);
                    resultTextView.setText(Double.toString(result));
                    operationMark = false;
                    number1 = Double.toString(result);
                    number2 = "";
                    break;

                case MULTIPLICATION:
                    result = Double.parseDouble(number1) * Double.parseDouble(number2);
                    resultTextView.setText(Double.toString(result));
                    operationMark = false;
                    number1 = Double.toString(result);
                    number2 = "";
                    break;

                case DIVISION:
                    if (Double.parseDouble(number2) != 0) {
                        result = Double.parseDouble(number1) / Double.parseDouble(number2);
                        resultTextView.setText(Double.toString(result));
                        operationMark = false;
                        number1 = Double.toString(result);
                        number2 = "";
                    }
                    else {
                        number1 = "";
                        number2 = "";
                        operationMark = false;
                        resultTextView.setText("");
                    }
                    break;
            }
        }
    }
}