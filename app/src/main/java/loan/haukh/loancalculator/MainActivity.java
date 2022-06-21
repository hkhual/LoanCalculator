package loan.haukh.loancalculator;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

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
      int loan;
      double annualRate = 0.0;
      int termOfLoan;
      double monthlyRate;

      double Monthly_payment = 0.0;


    NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
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


//    @Override
//    public void onStart(){
//        super.onStart();
//        Toast.makeText(this, "In onStart", Toast.LENGTH_SHORT).show();
//        Log.i("Info", "On Start");
//    }
//
//    @Override
//    public void onResume(){
//        super.onResume();
//
//        Toast.makeText(this, "In Onresume", Toast.LENGTH_SHORT).show();
//        Log.i("Info", "On Resume");
//    }
//
//
//    @Override
//    public void onPause(){
//        super.onPause();
//
//        Toast.makeText(this, "On Pause", Toast.LENGTH_SHORT).show();
//        Log.i("info", "On Pause");
//    }
//
//
//    @Override
//    public void onStop(){
//        super.onStop();
//
//        Toast.makeText(this, "On Stop", Toast.LENGTH_SHORT).show();
//        Log.i("info", "On Stop");
//    }
//
//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//
//        Toast.makeText(this, "On Destroy", Toast.LENGTH_SHORT).show();
//        Log.i("info", "On Destroy");
//    }



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
        if( id== R.id.privacy_policy){
            Intent intent = new Intent(this, Privacy.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Handle navigation view item clicks here.
        int id= menuItem.getItemId();
        if(id == R.id.nav_home){
            Intent account = new Intent(this, MainActivity.class );
                    startActivity(account);
        } else if(id == R.id.nav_mortage){
            Intent intent = new Intent(this,    MortgageActivity.class );
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
