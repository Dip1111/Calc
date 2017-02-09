package com.example.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mFirstNumberTv;
    private TextView mSecondNumberTv;
    private TextView mOperationTv;
    private boolean chosenTextView;
    private Button mDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstNumberTv = (TextView)findViewById(R.id.first_number_tv);
        mSecondNumberTv = (TextView)findViewById(R.id.second_number_tv);
        mOperationTv = (TextView)findViewById(R.id.operation_tv);
        mDelete = (Button)findViewById(R.id.delete);
        mDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!chosenTextView){
                    mFirstNumberTv.setText("");
                } else {
                    mSecondNumberTv.setText("");
                }
                return false;
            }
        });
    }

    public void onClickNumber(View v) {
        if (!chosenTextView) {
            /**
             * napomnit` Dime chtobu on menya vuebal
             */
            String clickValue = ((Button) v).getText().toString();
            mFirstNumberTv.setText(mFirstNumberTv.getText().toString() + clickValue);
        } else {
            String clickValue = ((Button) v).getText().toString();
            mSecondNumberTv.setText(mSecondNumberTv.getText().toString() + clickValue);
        }
    }

    public void onClickOperation(View v) {



        if (v.getId()== R.id.delete){

            String curValue;

            if(!chosenTextView) {
                curValue = mFirstNumberTv.getText().toString();
                if(curValue.length() != 0) {
                    mFirstNumberTv.setText(curValue.substring(0, curValue.length() - 1));
                } else {
                    Toast.makeText(this,"Idi NAHYI",Toast.LENGTH_SHORT).show();
                }
            } else {
                curValue = mSecondNumberTv.getText().toString();
                if(curValue.length() != 0) {
                    mSecondNumberTv.setText(curValue.substring(0, curValue.length() - 1));
                } else {
                    Toast.makeText(this, "Idi NAHYI", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            String clickValue = ((Button)v).getText().toString();
            mOperationTv.setText(clickValue);
            chosenTextView = true;
        }
    }

    public int operationResult (String firstNumber,String secondNumber, String operation){
        int first = Integer.valueOf(firstNumber);
        int second = Integer.valueOf(secondNumber);
        int result = 0;
        final String ADD_SYMBOL = "+";
        final String MIN_SYMBOL = "-";
        final String DIV_SYMBOL = "/";
        final String MUL_SYMBOL = "*";

        switch (operation){
            case ADD_SYMBOL:
                result = first + second;
                break;
            case MIN_SYMBOL:
                result = first - second;
                break;
            case DIV_SYMBOL:
                if(second == 0) {
                    Toast.makeText(this, "IDIOT CAN`T Divided by 0",Toast.LENGTH_LONG).show();
                    break;
                } else {
                    result = first / second;
                    break;
                }
            case MUL_SYMBOL:
                result = first * second;
                break;
        }
        return result;
    }

    public void onClickYaIndys(View view) {

        if(mFirstNumberTv.length() != 0){
            if(mSecondNumberTv.length() != 0){

            int result = operationResult(mFirstNumberTv.getText().toString(),
                    mSecondNumberTv.getText().toString(), mOperationTv.getText().toString());
            mFirstNumberTv.setText(String.valueOf(result));
            mSecondNumberTv.setText("");
            mOperationTv.setText("");
            chosenTextView = false;
            }
        } else {
            Toast.makeText(this, "EBALO NA NOL`", Toast.LENGTH_SHORT).show();
        }
    }
}
