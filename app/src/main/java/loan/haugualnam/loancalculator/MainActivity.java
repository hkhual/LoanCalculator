package loan.haugualnam.loancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mLoanAmount;
    private EditText mRate;
    private EditText mTerm;

    private TextView mDisplay_Payment;

    private TextView monthly_result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get text input from user
        mLoanAmount = findViewById(R.id.loan_userInput);
        mRate = findViewById(R.id.rate_userInput);
        mTerm = findViewById(R.id.term_userInput);

        //Display monthly payment
        mDisplay_Payment = findViewById(R.id.display_payment);
        mDisplay_Payment.setVisibility(View.INVISIBLE);




//
//
//        String loanAmount = mLoanAmount.getText().toString();
//        int loan = Integer.parseInt(loanAmount);
//
//        String rate = mRate.getText().toString();
//        double annualRate = Double.parseDouble(rate);
//
//        String loanTerm = mTerm.getText().toString();
//        int termOfLoan = Integer.parseInt(loanTerm);
//
//
//        //Payment = principle * monthly interest/(1-1(1+MonthlyInterest) * months));
//
//        //Montly Payment
//        double payment = loan * annualRate / (1- (Math.pow(1/(1 + annualRate), termOfLoan)));



        monthly_result = findViewById(R.id.monthly_payment_textView);
        monthly_result.setVisibility(View.INVISIBLE);





    }



    public void loanCalculate(View v) {

        monthly_result.setVisibility(View.VISIBLE);
        mDisplay_Payment.setVisibility(View.VISIBLE);




    }


    public boolean isValidate(String str){
            return !str.isEmpty();

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


        }
        return super.onOptionsItemSelected(item);
    }
}
