package com.study.calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String variable1 = "";
    private String operation;
    private String variable2 = "";
    private Boolean isOneVariable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        GridLayout gridLayout = findViewById(R.id.ButtonPanel);

        int buttonCount = gridLayout.getChildCount();
        for (int j = 0; j < buttonCount; j++) {
            View button = gridLayout.getChildAt(j);
            if (button instanceof Button) {
                Button btn = (Button) button;
                GridLayout.LayoutParams params = (GridLayout.LayoutParams) btn.getLayoutParams();
                GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
                GridLayout.Spec colSpec;
                if (btn.getText().toString().equals("0")) {
                    colSpec = GridLayout.spec(GridLayout.UNDEFINED, 2, 2f);
                } else
                    colSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
                params.rowSpec = rowSpec;
                params.columnSpec = colSpec;
                btn.setTextSize(25);

                btn.setLayoutParams(params);
            }
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
        TextView textView = findViewById(R.id.ResultPanel);
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
        TextView textView = findViewById(R.id.ResultPanel);
        textView.setText("");
    }

    public void onClickResult(View view) {
        setTextResult(result(), false);
        variable2 = "";
        operation = "";
        isOneVariable = true;
        TextView textView = findViewById(R.id.ResultPanel);

    }

    private String result() {
        try {
            if(isOneVariable){
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

