package com.example.andrew.androiddevclassday1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener{

        //set the number formats to be used for the $ amounts , and % amounts
        private static final NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance();
        private static final NumberFormat percentFormat =
                NumberFormat.getPercentInstance();
        //declare your variables for the widgets
        private EditText editTextBillAmount;
        private TextView textViewBillAmount;
        private TextView textView;
        private TextView tipAmount;
        private TextView totalAmount;
        private TextView tip;
        private TextView total;
        private SeekBar sb;
        //declare the variables for the calculations
        private double billAmount = 0.0;
        private double percent = .15;

        @Override
        protected void onCreate(android.os.Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(com.example.andrew.androiddevclassday1.R.layout.activity_main);
            //add Listeners to Widgets
            editTextBillAmount = (android.widget.EditText)findViewById(com.example.andrew.androiddevclassday1.R.id.editText_BillAmount);//uncomment this line
            editTextBillAmount.addTextChangedListener((android.text.TextWatcher) this);//uncomment this line
            textViewBillAmount = (android.widget.TextView)findViewById(com.example.andrew.androiddevclassday1.R.id.textView_BillAmount);

            textView = (android.widget.TextView)findViewById(com.example.andrew.androiddevclassday1.R.id.textView);
            tipAmount = (android.widget.TextView)findViewById(com.example.andrew.androiddevclassday1.R.id.TipAmount);

            totalAmount = findViewById(com.example.andrew.androiddevclassday1.R.id.TotalAmount);
            tip = findViewById(com.example.andrew.androiddevclassday1.R.id.Tip);
            total = findViewById(com.example.andrew.androiddevclassday1.R.id.Total);
            sb = findViewById(com.example.andrew.androiddevclassday1.R.id.seekBar);
            sb.setOnSeekBarChangeListener((android.widget.SeekBar.OnSeekBarChangeListener)this);
            textView.setText(percentFormat.format(percent));

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        /*
        Note:   int i, int i1, and int i2
                represent start, before, count respectively
                The charSequence is converted to a String and parsed to a double for you
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                Log.d("MainActivity", "inside onTextChanged method: charSequence= " + charSequence);
                //surround risky calculations with try catch (what if billAmount is 0 ?
                //charSequence is converted to a String and parsed to a double for you
                billAmount = Double.parseDouble(charSequence.toString()) * .01;
                Log.d("MainActivity", "Bill Amount = " + billAmount);
                //setText on the textView
               textViewBillAmount.setText(currencyFormat.format(billAmount));
                //perform tip and total calculation and update UI by calling calculate
                calculate();//uncomment this line
            } catch(Exception e){
                textViewBillAmount.setText(currencyFormat.format(0));
                totalAmount.setText(currencyFormat.format(0));
                tipAmount.setText(currencyFormat.format(0));
                billAmount = 0;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {



        }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        percent = progress * .01; //calculate percent based on seeker value
        textView.setText(percentFormat.format(progress * .01));
        calculate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        percent = seekBar.getProgress() / 100;
        calculate();
    }



        // calculate and display tip and total amounts
        private void calculate() {
            try {
                Log.d("MainActivity", "inside calculate method");
                //uncomment below

                // format percent and display in percentTextView
                //textView.setText(percentFormat.format(percent));

                // calculate the tip and total
                double tip1 = billAmount * percent;
                //use the tip example to do the same for the Total

                // display tip and total formatted as currency
                //user currencyFormat instead of percentFormat to set the textViewTip
                tipAmount.setText(currencyFormat.format(tip1));
                //use the tip example to do the same for the Total
                double total1 = billAmount + tip1;

                totalAmount.setText(currencyFormat.format(total1));
            } catch(Exception e) {
                android.util.Log.d("MainActivity", "calculate: Cannot divide by 0");
            }

        }

}

