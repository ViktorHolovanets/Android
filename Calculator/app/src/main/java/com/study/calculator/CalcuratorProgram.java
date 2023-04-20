package com.study.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

public class CalcuratorProgram extends AppCompatActivity {
    private String variable1 = "";
    private String operation;
    private String variable2 = "";
    private Boolean isOneVariable = true;
    private List<String> buttons = Arrays.asList(
            "C", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=");
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calcurator_program);
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setRowCount(6);
        gridLayout.setColumnCount(4);
        textView = new TextView(this);
        GridLayout.LayoutParams txtParams = new GridLayout.LayoutParams();
        GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
        GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, 4, 1f);
        txtParams.rowSpec = rowSpec;
        txtParams.columnSpec = colSpec;
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        textView.setBackgroundColor(ContextCompat.getColor(this,R.color.black));
        textView.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
        textView.setPadding(5, 5, 5, 5);
        textView.setLayoutParams(txtParams);
        gridLayout.addView(textView);
        for (String buttonContent : buttons) {
            Button btn = new Button(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            GridLayout.Spec rowSpecBtn = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
            GridLayout.Spec colSpecBtn = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.rowSpec = rowSpecBtn;
            params.columnSpec = colSpecBtn;
            btn.setTextSize(25);
            btn.setText(buttonContent);
            btn.setLayoutParams(params);
            listenerBtn(btn);
            gridLayout.addView(btn);
        }
        setContentView(gridLayout);
    }

    private void listenerBtn(Button button) {
        String content = button.getText().toString();
        switch (content) {
            case "C":
                button.setOnClickListener(this::clearResult);
                break;
            case "+/-":
                break;
            case "%":
            case "+":
            case "-":
            case "/":
            case "*":
                button.setOnClickListener(this::onClickOperation);
                break;
            case "=":
                button.setOnClickListener(this::onClickResult);
                break;
            default:
                button.setOnClickListener(this::onClickNumber);
                break;
        }
    }

    public void onClickNumber(View view) {
        Button btn = (Button) view;
        String num = btn.getText().toString();
        setTextResult(num, true);
        if (isOneVariable) {
            variable1 += num;
        } else {
            variable2 += num;
        }
    }

    public void onClickOperation(View view) {
        if (isOneVariable && !variable1.isEmpty()) {
            Button btn = (Button) view;
            operation = btn.getText().toString();
            setTextResult(operation, true);
            isOneVariable = !isOneVariable;
        }
    }

    private void setTextResult(String text, Boolean isAdd) {
        if (isAdd)
            textView.setText(textView.getText().toString() + text);
        else
            textView.setText(text);
    }

    public void clearResult(View view) {
        variable1 = "";
        variable2 = "";
        operation = "";
        isOneVariable = true;
        textView.setText("");
    }

    public void onClickResult(View view) {
        setTextResult(result(), false);
        variable2 = "";
        operation = "";
        isOneVariable = true;
    }

    private String result() {
        try {
            if (isOneVariable) {
                return variable1;
            }
            float v1 = Float.parseFloat(variable1);
            float v2 = Float.parseFloat(variable2);
            float result = 0;
            switch (operation) {
                case "+":
                    result = v1 + v2;
                    break;
                case "-":
                    result = v1 - v2;
                    break;
                case "*":
                    result = v1 * v2;
                    break;
                case "/":
                    if (v2 != 0)
                        result = v1 / v2;
                    break;
                case "%":
                    result = (v1 / 100) * v2;
                    break;
            }
            variable1 = String.valueOf(result);
            return variable1;
        } catch (Exception e) {
        }
        return "";
    }
}