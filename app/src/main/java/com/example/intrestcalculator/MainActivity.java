package com.example.intrestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText loan_input, interest_input, term_input, upfront_input;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsetup();
    }
    public void buttonsetup() {
        loan_input = (EditText) findViewById(R.id.loan_input);
        interest_input = (EditText) findViewById(R.id.interest_input);
        term_input = (EditText) findViewById(R.id.term_input);
        upfront_input = (EditText) findViewById(R.id.upfront_input);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                button.setBackgroundColor(Color.BLUE);
                double loan = Double.parseDouble(loan_input.getText().toString());
                double interest = Double.parseDouble(interest_input.getText().toString())/100.0;
                double term = Double.parseDouble(term_input.getText().toString());
                double upfront = Double.parseDouble(upfront_input.getText().toString());
                double payment = (interest*loan)/(1-Math.pow(1+interest,-term*12));
                double total_interest = payment*12*term - loan;
                payment = Math.round(payment * 100)/100.0;
                total_interest = Math.round(total_interest * 100)/100.0;
                upfront = total_interest - upfront;
                upfront = Math.round(upfront * 100)/100.0;


                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
                Toast.makeText(getApplicationContext(),"Monthly Payment $" + payment + " for " + term + " years\n total interest $" + total_interest + "\n You pay $" + upfront, Toast.LENGTH_LONG).show();
            }

        });
    }


}