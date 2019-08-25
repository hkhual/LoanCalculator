package loan.haukh.loancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

     private TextView totalPrincipal;
     private TextView totalInterest;

    //TextView to display payment and result.
    private TextView mDisplay_Payment;
    private TextView monthly_result;


     //Data types to hold user input
      private int loan;
      private double annualRate = 0.0;
      private int termOfLoan;
      private double monthlyRate;

      private double Monthly_payment = 0.0;


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


        //total loan and total interest paid
        totalPrincipal = findViewById(R.id.total_principal_paid);
        totalInterest = findViewById(R.id.total_interest_paid);


        //make them invisible
        monthly_result.setVisibility(View.INVISIBLE);
        mDisplay_Payment.setVisibility(View.INVISIBLE);

        totalPrincipal.setVisibility(View.INVISIBLE);
        totalInterest.setVisibility(View.INVISIBLE);


    }


    public void Calculate(View view){

        if(TextUtils.isEmpty(mLoanAmount.getText().toString().trim())){
            mLoanAmount.setError("Invalid input");
            //Stop the function execution
            return;
        }
        else if(TextUtils.isEmpty(mRate.getText().toString().trim())){
            mRate.setError("Invalid input");
            //Stop the function execution
            return;
        }
        else if(TextUtils.isEmpty(mTerm.getText().toString().trim())){
            mTerm.setError("Invalid input");
            //Stop the function execution
            return;
        }

        //Convert user input loanAmount into integer
        //Convert user input rate into double
        //Convert user input loan term into integer
        loan = Integer.parseInt(mLoanAmount.getText().toString().trim());
        annualRate = Double.parseDouble(mRate.getText().toString().trim());
        termOfLoan = Integer.parseInt(mTerm.getText().toString().trim());

        monthlyRate = (annualRate/100.0) / 12.0;

        //Calculate monthly payment
         Monthly_payment = loan * monthlyRate / (1-Math.pow(1 + monthlyRate,-termOfLoan));

        //display_payment
        //Send monthly payment into display_payment editText field
        mDisplay_Payment.setText(String.valueOf(currencyFormat.format(Monthly_payment)));



        double totalInterestPayment = Monthly_payment * termOfLoan - loan;

        totalPrincipal.setText(String.valueOf("Total Principal Paid: "+ currencyFormat.format(loan)));
        totalInterest.setText(String.valueOf("Total Interest Paid: "+ currencyFormat.format(totalInterestPayment)));


        monthly_result.setVisibility(View.VISIBLE);
        mDisplay_Payment.setVisibility(View.VISIBLE);

        totalPrincipal.setVisibility(View.VISIBLE);
        totalInterest.setVisibility(View.VISIBLE);

    }


    @Override
    public void onStart(){
        super.onStart();
        Toast.makeText(this, "In onStart", Toast.LENGTH_SHORT).show();
        Log.i("Info", "On Start");
    }

    @Override
    public void onResume(){
        super.onResume();

        Toast.makeText(this, "In Onresume", Toast.LENGTH_SHORT).show();
        Log.i("Info", "On Resume");
    }


    @Override
    public void onPause(){
        super.onPause();

        Toast.makeText(this, "On Pause", Toast.LENGTH_SHORT).show();
        Log.i("info", "On Pause");
    }


    @Override
    public void onStop(){
        super.onStop();

        Toast.makeText(this, "On Stop", Toast.LENGTH_SHORT).show();
        Log.i("info", "On Stop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        Toast.makeText(this, "On Destroy", Toast.LENGTH_SHORT).show();
        Log.i("info", "On Destroy");
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
        if( id== R.id.action_about){
            Intent intent = new Intent(this, AboutPage.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
