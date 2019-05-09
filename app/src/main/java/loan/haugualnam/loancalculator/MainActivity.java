package loan.haugualnam.loancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //User input editText
     private EditText mLoanAmount;
     private EditText mRate;
     private EditText mTerm;

     //Data types to hold user input
      private int loan;
     private double annualRate;
     private int termOfLoan;

     //TextView to display payment and result.
    private TextView mDisplay_Payment;
    private TextView monthly_result;


    NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get user input
        mLoanAmount = findViewById(R.id.loan_userInput);
        mRate = findViewById(R.id.rate_userInput);
        mTerm = findViewById(R.id.term_userInput);




        //Display monthly payment
        mDisplay_Payment = findViewById(R.id.display_payment);
        monthly_result = findViewById(R.id.monthly_payment_textView);
        monthly_result.setVisibility(View.INVISIBLE);
        mDisplay_Payment.setVisibility(View.INVISIBLE);




        findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //Convert user input loanAmount into integer
                    //Convert user input rate into double
                    //Convert user input loan term into integer
                    loan = Integer.parseInt(mLoanAmount.getText().toString());
                    annualRate = Double.parseDouble(mRate.getText().toString());
                    termOfLoan = Integer.parseInt(mTerm.getText().toString());

                    //Convert interest rate into a decimal
                    //eg. 4.5% = 0.045
                    annualRate /= 100.0;

                   //Monthly interest rate
                    //is the yearly rate divided by 12
                    double monthlyRate = annualRate / 12.0;

                    //if(TextUtils.isEmpty())



                if(!isValidLoan(loan)){
                    mLoanAmount.setError("Invalid input");
                    //Stop the function execution
                    return;
                }
                else if(!(isValidRate(annualRate))){
                    mRate.setError("Invalid input");
                    //Stop the function execution
                    return;
                }
                else if(!(isValidTerm(termOfLoan))){
                    mTerm.setError("Invalid input");
                    //Stop the function execution
                    return;

                } else {
                    //Calculate monthly payment
                    double Monthly_payment = loan * monthlyRate / (1-Math.pow(1 + monthlyRate,-termOfLoan));

                    //display_payment
                    //Send monthly payment into display_payment editText field
                    mDisplay_Payment.setText(String.valueOf(currencyFormat.format(Monthly_payment)));

                    monthly_result.setVisibility(View.VISIBLE);
                    mDisplay_Payment.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public boolean isValidLoan(int loan){

        if(loan > 0){
            return true;
        } else {
            return false;
        }


    }


    public boolean isValidRate(double rate){

        if(rate >= 0 && rate <= 1){
            return true;
        } else {
            return false;
        }
    }



    public boolean isValidTerm(int term){

        if (term > 0) {
            return true;
        }else {
            return false;
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           Toast toast = Toast.makeText(getApplicationContext(), "Coming soon!",Toast.LENGTH_SHORT);
           toast.show();



        }else if( id== R.id.action_about){
            Intent intent = new Intent(this, AboutPage.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



}
