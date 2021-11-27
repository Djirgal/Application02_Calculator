package ru.learningandroid.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String KEY = "CALCULATOR";
    private Calculator calculator;
    private TextView textResult;

    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    private Button btnPlus, btnMinus, btnDivide, btnMultiply, btnPoint, btnDelete, btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeToast("onCreate()");
        if(savedInstanceState==null){
            makeToast("первый запуск onCreate()");
        }else{
            makeToast("повторный запуск onCreate()");
        }

        calculator = new Calculator();
        initView();
    }

    private void initView(){

        textResult = findViewById(R.id.textRes);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);

        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);

        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);

        button0 = findViewById(R.id.button0);
        button0.setOnClickListener(this);

        btnPoint = findViewById(R.id.buttonPoint);
        btnPoint.setOnClickListener(this);

        btnDelete = findViewById(R.id.del);
        btnDelete.setOnClickListener(this);

        btnPlus = findViewById(R.id.plus);
        btnPlus.setOnClickListener(this);

        btnMinus = findViewById(R.id.minus);
        btnMinus.setOnClickListener(this);

        btnMultiply = findViewById(R.id.multiply);
        btnMultiply.setOnClickListener(this);

        btnDivide = findViewById(R.id.divide);
        btnDivide.setOnClickListener(this);

        btnCalc = findViewById(R.id.calculate);
        btnCalc.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                calculator.addNumber("1");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button2:
                calculator.addNumber("2");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button3:
                calculator.addNumber("3");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button4:
                calculator.addNumber("4");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button5:
                calculator.addNumber("5");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button6:
                calculator.addNumber("6");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button7:
                calculator.addNumber("7");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button8:
                calculator.addNumber("8");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button9:
                calculator.addNumber("9");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.button0:
                calculator.addNumber("0");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.buttonPoint:
                calculator.addOperation(".");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.del:
                calculator.deleteLast();
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.plus:
                calculator.addOperation("+");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.minus:
                calculator.addOperation("-");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.multiply:
                calculator.addOperation("*");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.divide:
                calculator.addOperation("/");
                setTextResult(textResult, calculator.getMathExpression());
                break;
            case R.id.calculate:
                setTextResult(textResult, calculator.getResult());
                break;
        }
    }

    private void setTextResult(TextView textResult, String mathExpression){
        textResult.setText(mathExpression);
    }

    @Override
    protected void onStart(){
        super.onStart();
        makeToast("onStart()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState){
        super.onSaveInstanceState(instanceState);
        makeToast("onSaveInstanceState()");
        instanceState.putSerializable(KEY, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState){
        super.onRestoreInstanceState(instanceState);
        makeToast("onRestoreInstanceState()");
        calculator = (Calculator) instanceState.getSerializable(KEY);
//To think about: should setTextResult() always gets 2nd param = calculator.getMathExpression()???
        setTextResult(textResult, calculator.getMathExpression());
    }

    @Override
    protected void onResume(){
        super.onResume();
        makeToast("onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        makeToast("onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        makeToast("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        makeToast("onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        makeToast("onDestroy()");
    }

    private void makeToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
        Log.e("mylogs: ", toast);
    }
}