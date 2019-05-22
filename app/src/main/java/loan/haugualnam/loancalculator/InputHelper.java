package loan.haugualnam.loancalculator;

/**
 *  Input Helper Class
 */

public class InputHelper {

    private int loanAmount;
    private double rateAmount;
    private int loanTerm;


    public void setLoanAmount(int loan){
        loanAmount = loan;
    }

    public int getLoanAmount(){
        return loanAmount;
    }


    public void setRateAmount(double rate){
        rateAmount = rate;
    }

    public double getRateAmount(){
        return rateAmount;
    }


    public void setLoanTerm(int term){
        loanTerm = term;

    }

    public int getLoanTerm(){
        return loanTerm;
    }

}
