package com.makhabatusen.lesson_2_hw;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstNum, etSecondNum, etThirdNum;
    private TextView tv_result;
    private Button btnSum;

    private int firstNum, secondNum, thirdNum, sum;

    private final String SAVED_INT1 = "SAVED_INT1";
    private final String SAVED_INT2 = "SAVED_INT2";
    private final String SAVED_INT3 = "SAVED_INT3";
    private final String SAVED_SUM = "SAVED_SUM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setTextWatcher();

        btnSum.setOnClickListener(view -> {
            if (secondNum != 0 && thirdNum != 0) {
                sum = firstNum + secondNum / thirdNum;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Toast.makeText(MainActivity.this, "To see the result, please, rotate the screen to landscape", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setTextWatcher() {
        TextWatcher numTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }


            @Override
            public void afterTextChanged(Editable editable) {
                if (etFirstNum.getText().hashCode() == editable.hashCode()) {
                    firstNum = Integer.parseInt(editable.toString());
                } else if (etSecondNum.getText().hashCode() == editable.hashCode()) {
                    secondNum = Integer.parseInt(editable.toString());
                } else if (etThirdNum.getText().hashCode() == editable.hashCode()) {
                    thirdNum = Integer.parseInt(editable.toString());
                }
            }
        };
        etFirstNum.addTextChangedListener(numTextWatcher);
        etSecondNum.addTextChangedListener(numTextWatcher);
        etThirdNum.addTextChangedListener(numTextWatcher);
    }

    private void init() {
        etFirstNum = findViewById(R.id.et_firstNum);
        etSecondNum = findViewById(R.id.et_secondNum);
        etThirdNum = findViewById(R.id.et_thirdNum);
        btnSum = findViewById(R.id.btn_sum);
        tv_result = findViewById(R.id.tv_result);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INT1, firstNum);
        outState.putInt(SAVED_INT2, secondNum);
        outState.putInt(SAVED_INT3, thirdNum);
        outState.putInt(SAVED_SUM, sum);
        Log.d("ololo", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int firstNumRestored = savedInstanceState.getInt(SAVED_INT1);
        int secondNumRestored = savedInstanceState.getInt(SAVED_INT2);
        int thirdNumRestored = savedInstanceState.getInt(SAVED_INT3);
        int sumRestored = savedInstanceState.getInt(SAVED_SUM);
        Log.d("ololo", "onRestoreInstanceState");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            tv_result.setText(String.valueOf(sumRestored));
        }

    }
}