/*
    Written by Lucas Holl.
    Last modified: 1/11/2018
    Student#: 3280916
*/

import java.util.*;
import java.lang.Math; //Required to use powers in equation.
import java.text.DecimalFormat; //Required to set decimal format.

public class Account
{
    Scanner console = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("0.00"); //This sets the decimal format when using df.format().
    private String name, accountTypeDisplay, accountType;
    private double age, amount, interestRate, annualIncome, monthlyPayment, interestPercent, initBalance, interestPaid, principalPaid, finalBalance, addTen, totalPayment, totalInterestPaid;
    private int numberOfMonths;
    
    public Account()
    {
        name = "NO NAME";
        age = 0;
        annualIncome = 0;
    }
     
    public void setInterestRate(double newInterestRate) //This method sets the interest rate.
    {
        if(accountType.equals("1")) //accountType 1 is the dynamic interest account.
        {
            if(numberOfMonths < 50)
            {
                interestRate = 0.065; //Actual interest rate used in equation.
                interestPercent = 6.5; //Representative number to be printed in order to make the output more user-friendly.
            }
            else if(50 <= numberOfMonths && numberOfMonths <= 100)
            {
                interestRate = 0.075;
                interestPercent = 7.5;
            }
            else
            {
                interestRate = 0.085;
                interestPercent = 8.5;
            }
        }
        else //accountType 0 is the fixed interest account with a monthly fee of $10.
        {
            interestRate = 0.06;
            interestPercent = 6;
        }
    }

    public double getInterestRate() //This method retrieves the interest rate.
    {
        return interestPercent;
    }

    public void setNumberOfMonths(int newNumberOfMonths) //This method sets the numberOfMonths of the loan.
    {
        numberOfMonths = newNumberOfMonths;
    }

    public int getNumberOfMonths() //This method retrieves the numberOfMonths of the loan.
    {
        return numberOfMonths;
    }
    
    public void setAmount(double newAmount) //This method sets the amount borrowed.
    {
        amount = newAmount;
    }

    public double getAmount() //This method retrieves the amount borrowed.
    {
        return amount;
    }
    
    public void setAccountType(String newAccountType) //This method sets the account type; fixed or dynamic interest.
    {
        accountType = newAccountType;
    }

    public String getAccountType() //This method retrieves the account type.
    {
        return accountType;
    }

    public void setAccountTypeDisplay(String newAccountTypeDisplay)
    {
        if (accountType.equals("0"))
        {
            accountTypeDisplay = "fees";
        }
        else
        {
            accountTypeDisplay = "no fees";
        }
    }

    public String getAccountTypeDisplay()
    {
        return accountTypeDisplay;
    }

    public void calculateMonthlyPayment(double newMonthlyPayment) //This method calculates the monthly payment.
    {
        double a = (1 + interestRate / 12); //This line declares and initialises the variablea which has a unique value only required in this method.
        monthlyPayment = (amount * interestRate * Math.pow(a, numberOfMonths)) / (12 * (Math.pow(a, numberOfMonths) - 1) + getAddTen()); //This line calculates the monthly payment.
    }

    public double getMonthlyPayment() //This method retrieves the monthly payment.
    {
        return monthlyPayment;
    }

    public void setInterestPaid(double newInterestPaid) //This method sets the interest paid.
    {
        interestPaid = initBalance * interestRate / 12; //This line calculates the value for interestPaid.
    }

    public double getInterestPaid() //this method retrieves the interest paid.
    {
        return interestPaid;
    }

    public void setPrincipalPaid(double newPrincipalPaid) //This method sets the principal paid.
    {
        principalPaid = monthlyPayment - interestPaid; //This line calculates the value for principalPaid.
    }

    public double getPrincipalPaid() //This method retrieves the principal paid.
    {
        return principalPaid;
    }

    public void setFinalBalance(double newFinalBalance) //This method sets the final balance.
    {
        finalBalance = initBalance - monthlyPayment + interestPaid; //This line calculates the value for finalBalance.
    }

    public double getFinalBalance() //This method retrieves the final balance.
    {
        return finalBalance;
    }

    public void setInitBalance(double newInitBalance) //This method sets the initial balance.
    {
        initBalance = amount;
    }

    public double getInitBalance() //This method retrieves the initial balance.
    {
        return initBalance;
    }

    public void addTen(double newAddTen) //This method adds $10 to the fixed interest monthly repayment.
    {
        if(accountType.equals("0")) //If statement determines the account type in use.
        {
            monthlyPayment = monthlyPayment + 10; //If the statement is true, it adds $10 to the monthly repayment.
        }
    }

    public double getAddTen()
    {
        return monthlyPayment;
    }

    public void setTotalPayment(double newTotalPayment) //This method calculates the total payment.
    {
        totalPayment = monthlyPayment * numberOfMonths;
    }

    public double getTotalPayment() //THis method retrieves the total payment.
    {
        return totalPayment;
    }

    public void setTotalInterestPaid(double newTotalInterestPaid) //This method sets the total interest paid.
    {
        totalInterestPaid = interestPaid * numberOfMonths;
    }

    public double getTotalInterestPaid() //This method retrieves the total interest paid.
    {
        return totalInterestPaid;
    }

    public String setAmortizationTable() //This method gets the amortization table.
    {
        String table = "";
        table = table + String.format("%5s%20s%20s%20s%20s%20s%n", "Month", "Initial Balance", "Monthly Payment", "Interest Paid", "Principal Paid", "Final Balance"); //This is outputting the table headings.

        for (int i = 1; i <= numberOfMonths; i++) //This loop sets manner in which the table is handled.
        {
            table = table + String.format("%5d%20s%20s%20s%20s%20s%n", i, "$" + df.format(initBalance), "$" + df.format(monthlyPayment), "$" + df.format(interestPaid), "$" + df.format(principalPaid), "$" + df.format(finalBalance)); //This is outputting the calculations to the table for every month.

            initBalance = finalBalance; //This sets the initial balance in the next line as the previous final balance.

            setInterestPaid(interestPaid); //This calls the setInterestPaid method above.
            setPrincipalPaid(principalPaid); //This calls the setPrincipalPaid method above.
            setFinalBalance(finalBalance); //This calls the setFinalBalance method above.
        }

        return(table);
    }
}