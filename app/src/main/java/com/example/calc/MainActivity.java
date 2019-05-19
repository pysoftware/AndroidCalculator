package com.example.calc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView screen;
    String tmp, tmp2, sign="", historyString, resultString = "";
    Double a,b = 0d,c=0d,f;
    TextView textView;
    TextView secondResult;
    TextView history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.result);
        tmp = "";
        tmp2 = "";
        historyString = "";
        textView =  findViewById(R.id.result);
        history = findViewById(R.id.history);
        history.setText("");
        secondResult = findViewById(R.id.second_result);
        secondResult.setText("");
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
// Need to done with it...
    public void onClickDigit(View view){
        NumberFormat nf = new DecimalFormat("#.######");
        Button button = (Button)view;
        if (screen.length() < 15)
            tmp += button.getText().toString();
        if (tmp.length() >= 6)
            screen.setTextSize(60-tmp.length());
        else
            screen.setTextSize(65f);
        if(!tmp.contains(".")) {
            if (tmp.length() == 4) {
                tmp = tmp.substring(0, 3) + "," + tmp.substring(3);
            } else if (tmp.length() == 7) {
                tmp = tmp.substring(0, 3) + "," + tmp.substring(4, 7);
            } else if (tmp.length() == 8) {
                tmp = tmp.substring(0, 3) + "," + tmp.substring(4, 7) + "," + tmp.substring(7);
            } else if (tmp.length() == 10) {
                tmp = tmp.substring(0, 3) + "," + tmp.substring(4, 7) + "," + tmp.substring(8);
            } else if (tmp.length() == 11) {
                tmp = tmp.substring(0, 3) + "," + tmp.substring(4, 7) + "," + tmp.substring(8, 11);
            } else if (tmp.length() == 12) {
                tmp = tmp.substring(0, 3) + "," + tmp.substring(4, 7) + "," + tmp.substring(8, 11) + "," + tmp.substring(11);
            }
        }
        screen.setText(tmp);
        if (sign.equals(""))
            secondResult.setText("=" + screen.getText().toString());
        else {
            tmp2 = screen.getText().toString();
            b = Double.parseDouble(tmp2);
            screen.setText(sign + " "+ nf.format(b));
//          ПРОБЛЕМА С ОТОБРАЖЕНИЕМ ИСТОРИИ)))
            if (screen.getText().toString().equals("0")) {
                historyString = (nf.format(f) + " " + sign + " " + nf.format(b));
                history.setText(historyString);
            } else {
                historyString = ("\n"+nf.format(f) + " " + sign + " " + nf.format(b));
                history.setText(historyString);
            }
            if (sign.equals("+")){
                c = f+b;
                secondResult.setText("=" + nf.format(c));
            } else if (sign.equals("×")){
                c = f*b;
                secondResult.setText("=" + nf.format(c));
            } else if (sign.equals("÷")){
                c = f/b;
                secondResult.setText("=" + nf.format(c));
            } else if (sign.equals("-")){
                c = f-b;
                secondResult.setText("=" + nf.format(c));
            }
        }
        a = Double.parseDouble(tmp.replace(",",""));
        Log.d("zaloopa",a.toString());
    }
    public void onClickSign(View view){
        Button button = (Button)view;
        sign = button.getText().toString();
        f = a;
        if (!screen.getText().toString().equals("0"))
            screen.setText(sign);
        resultString = sign;
        tmp = "";
    }
    public void onClickDot(View view){
        if (!screen.getText().toString().equals("0")) {
            tmp += ".";
            screen.setText(tmp);
        }

    }
    public void onClickResult(View view){
        NumberFormat nf = new DecimalFormat("#.######");
        tmp2 = screen.getText().toString().substring(2);
        b = Double.parseDouble(tmp2);
        if (sign.equals("+")){
            c = f+b;
            screen.setText(nf.format(c));
            secondResult.setText("=" + nf.format(c));
            history.setText(nf.format(f) +" "+ sign + " " + nf.format(b) + " =" + " "+nf.format(c));
            tmp ="";
            a = c;
            sign = "";
        } else if (sign.equals("×")){
            c = f*b;
            screen.setText(nf.format(c));
            secondResult.setText("=" + nf.format(c));
            history.setText(nf.format(f) +" "+ sign + " " + nf.format(b) + " =" + " "+nf.format(c));
            tmp ="";
            a = c;
            sign = "";
        } else if (sign.equals("÷")){
            c = f/b;
            screen.setText(nf.format(c));
            secondResult.setText("=" + nf.format(c));
            history.setText(nf.format(f) +" "+ sign + " " + nf.format(b) + " =" + " "+nf.format(c));
            tmp ="";
            a = c;
            sign = "";
        } else if (sign.equals("-")){
            c = f-b;
            screen.setText(nf.format(c));
            secondResult.setText("=" + nf.format(c));
            history.setText(nf.format(f) +" "+ sign + " " + nf.format(b) + " =" + " "+nf.format(c));
            tmp ="";
            a = c;
            sign = "";
        }
    }
    public void onClickFullDelete(View view){
        historyString = "";
        f = 0d;
        c = 0d;
        sign = "";
        history.setText("");
        screen.setText("0");
        a = 0d;
        b = 0d;
        tmp = "";
        tmp2 = "";
        screen.setTextSize(70f);
        secondResult.setText("");
    }
    public void onClickOneDelete(View view){
        if(screen.getText().toString().equals("0") || tmp.length() == 1){
            tmp = "";
            screen.setText("0");
        } else if ((""+(tmp.charAt(tmp.length()-2))).equals(",")){
            tmp = tmp.substring(0, tmp.length()-2);
            screen.setText(tmp);
        }
        else {
            if(!screen.getText().toString().equals("0")) {
//                tmp = tmp.substring(0, tmp.length() - 1);
                screen.setText(deleteChar(screen.getText().toString()));
            }
        }
    }
    public void onClickRoot(View view){
        Double tmp = Double.parseDouble(screen.getText().toString().replace(",",""));
        Double tmp2 = Math.sqrt(tmp);
        a = tmp2;
        NumberFormat nf = new DecimalFormat("#.######");
        screen.setText(nf.format(tmp2));
        secondResult.setText("=" + screen.getText().toString());
    }
    public void onClickSqr(View view){
        tmp = "";
        NumberFormat nf = new DecimalFormat("#.######");
        if(!screen.getText().toString().equals("∞")) {
            screen.setText(String.valueOf(nf.format(Math.pow(Double.parseDouble(screen.getText().toString().replace(",", "")), 2))));
            a = Double.parseDouble(screen.getText().toString());
            secondResult.setText("=" + screen.getText().toString());
        } else {
            screen.setText("0");
            tmp = "";
        }
    }
    private static String deleteChar(String str){
        return str.substring(0, str.length() - 1);
    }
}
