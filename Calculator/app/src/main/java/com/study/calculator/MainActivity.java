package com.study.calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.GridLayout;
import android.widget.TextView;

import com.study.calculator.model.ParamsCalculatorModel;

public class MainActivity extends AppCompatActivity {

    private ParamsCalculatorModel paramsCalculatorModel = new ParamsCalculatorModel();

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
                GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
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
        if (paramsCalculatorModel.getOneVariable()) {
            paramsCalculatorModel.setVariable1(paramsCalculatorModel.getVariable1() + num);
        } else {
            paramsCalculatorModel.setVariable2(paramsCalculatorModel.getVariable2() + num);
        }
    }

    public void onClickOperation(View view) {
        if (paramsCalculatorModel.getOneVariable() && !paramsCalculatorModel.getVariable1().isEmpty()) {
            Button btn = (Button) view;
            paramsCalculatorModel.setOperation(btn.getText().toString());
            setTextResult(paramsCalculatorModel.getOperation(), true);
            paramsCalculatorModel.setOneVariable(!paramsCalculatorModel.getOneVariable());
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
        paramsCalculatorModel.clear();
        TextView textView = findViewById(R.id.ResultPanel);
        textView.setText("");
    }

    public void onClickResult(View view) {
        setTextResult(result(), false);
        paramsCalculatorModel.clear1();
        TextView textView = findViewById(R.id.ResultPanel);

    }

    private String result() {
        try {
            if (paramsCalculatorModel.getOneVariable()) {
                return paramsCalculatorModel.getVariable1();
            }
            float v1 = Float.parseFloat(paramsCalculatorModel.getVariable1());
            float v2 = Float.parseFloat(paramsCalculatorModel.getVariable2());
            float result = 0;
            switch (paramsCalculatorModel.getOperation()) {
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
            paramsCalculatorModel.setVariable1(String.valueOf(result));
            return paramsCalculatorModel.getVariable1();
        } catch (Exception e) {
        }
        return "";
    }

    public void onClickNav(View view) {
        Intent intent = new Intent(this, CalcuratorProgram.class);
        intent.putExtra(ParamsCalculatorModel.class.getSimpleName(), paramsCalculatorModel);
        startActivity(intent);
    }
}