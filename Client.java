/*
    Written by Lucas Holl.
    Last modified: 1/11/2018
    Student#: 3280916
*/

import java.util.*;
import java.lang.Math; //Required to use powers in equation.
import java.text.DecimalFormat; //Required to set decimal format.

public class Client
{
    Scanner console = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("0.00"); //This sets the decimal format when using df.format().
    private String name;                    
    private int age;                        
    private double income;   
    private Account monthlyPayment;
    private Account[] loan = new Account[2];
    private int accountNumber = 0;
    private int numOfAccounts = 0;

    public Client()
    {
        name = "";
        age = 0;
        income = 0;
    }

    public void createAccount() //This method creates an account withing the client array
    {
        if (accountNumber < 2)
        {
            loan[accountNumber] = new Account();
            accountNumber++;

            //This section sets the account type.
            String accountType;
            String accountTypeDisplay = "";
            System.out.println("Please choose the account type you wish to open.");
            System.out.println("Fixed interest with a monthly fee of $10 (0) or dynamic interest with no fees (1).");
            accountType = console.next();

            while((!accountType.equals("0")) && (!accountType.equals("1"))) //Checks for an invalid value when asking what account type the user wants.
            {
                System.out.println("Invalid input! You must enter the corresponding number.");
                accountType = console.next();
            }

            loan[accountNumber - 1].setAccountType(accountType); //Sets the value of the variable accountType in the method setAccountType in the class Account.
            loan[accountNumber - 1].setAccountTypeDisplay(accountTypeDisplay);
            System.out.println("Account type = " + loan[accountNumber - 1].getAccountTypeDisplay() + "\n"); 

            //This section sets the repayment period.
            int numberOfMonths;
            System.out.println("Please enter the repayment period you want in number of months.");
            numberOfMonths = console.nextInt();

            while(numberOfMonths < 0) //Checks for an invalid value when asked for number of months.
            {
                System.out.println("Invalid input! You cannot have a negative repayment period!");
                numberOfMonths = console.nextInt();
            }

            loan[accountNumber - 1].setNumberOfMonths(numberOfMonths);
            System.out.println("Number of months = " + loan[accountNumber - 1].getNumberOfMonths() + "\n");

            //This section sets and displays the interest rate.
            double interestRate = 0, interestPercent = 0;
            loan[accountNumber - 1].setInterestRate(interestRate);
            System.out.println("Your interest rate is = " + loan[accountNumber - 1].getInterestRate() + "%" + "\n");

            //This section sets and displays the amount borrowed.
            double amount;
            System.out.println("Please enter the amount you wish to borrow.");
            amount = console.nextDouble();

            while(amount < 1) //Checks for an invalid value when asked for how much the user wants to borrow.
            {
                System.out.println("Invalid input! You cannot borrow a negative or zero value!");
                amount = console.nextDouble();
            }

            loan[accountNumber - 1].setAmount(amount);
            System.out.println("Your requested amount is = $" + df.format(loan[accountNumber - 1].getAmount()) + "\n");

            numOfAccounts++;
        }

        else
        {
            System.out.println("Maximum number of accounts reached.");
        }
    }

    public void getAccount(int accountChoice) //This method pulls information from the loan array for the client in question
    {
        //This section calculates the monthly payment.
        double monthlyPayment = 0;
        loan[accountChoice].calculateMonthlyPayment(monthlyPayment);

        //This sections sets the information in their respective methods and displays the summary and amortization table.
        double initBalance = 0, interestPaid = 0, principalPaid = 0, finalBalance = 0, addTen = 10, totalPayment = 0, totalInterestPaid = 0;
        loan[accountChoice].setInitBalance(initBalance);
        loan[accountChoice].setInterestPaid(interestPaid);
        loan[accountChoice].setPrincipalPaid(principalPaid);
        loan[accountChoice].setFinalBalance(finalBalance);
        loan[accountChoice].addTen(addTen);
        loan[accountChoice].setTotalPayment(totalPayment);
        loan[accountChoice].setTotalInterestPaid(totalInterestPaid);
        System.out.println("Summary");
        System.out.println(name + ", age " + age + ", income $" + df.format(income));
        System.out.println("Amount to borrow = $" + df.format(loan[accountChoice].getAmount()) + " to pay in " + loan[accountChoice].getNumberOfMonths() + " months in account type " + loan[accountChoice].getAccountTypeDisplay() + ".");
        System.out.println("The interest rate will be " + loan[accountChoice].getInterestRate() + "%.");
        System.out.println("The monthly payment will be = $" + df.format(loan[accountChoice].getMonthlyPayment()) + ".");
        System.out.println("The total payment is = $" + df.format(loan[accountChoice].getTotalPayment()));
        System.out.println("The total interest paid will be = $" + df.format(loan[accountChoice].getTotalInterestPaid()) + "\n");
        System.out.println("Amortization Table");
        System.out.println(loan[accountChoice].setAmortizationTable()); //Prints the table.
        System.out.println(loan[accountChoice].getAmount());
    }

    public void deleteAccount(int accountChoice) //This method deletes an account within the client array
    {
        Account[] temp = new Account[2];
        loan[accountChoice] = null;
        for (int i = 0; i < loan.length; i++)
        {
            if(loan[i] == null && i != (loan.length-1)) 
            {
                temp[i] = loan[i + 1];
                loan[i + 1] = null;
            }
            else
            {
                temp[i] = loan[i];
            }
        }
        loan = temp;
        accountNumber--;

        numOfAccounts--;
    }

    public int getNumOfAccounts() //This method gets the number of accounts
    {
        return numOfAccounts;
    }
    
    public int getAccountNumber() //This method gets the account index number from the array
    {
        return accountNumber;
    }

    public String getName() //This method returns the value for name.
    {
        return name;
    }

    public void setName(String newName) //This method sets the value for name.
    {
        name = newName;
    }
    
    public void setAge(int newAge) //This method sets the value for age.
    {
        age = newAge;
    }
    
    public int getAge() //This method returns the value for age.
    {
        return age;
    }
    
    public void setIncome(double newIncome) //This method sets the value for income.
    {
        income = newIncome;
    }

    public double getIncome() //This method returns the value for income.
    {
        return income;
    }

    public Account getAccountMonthlyPayment() //This method returns the value for monthlyPayment.
    {
        return monthlyPayment;
    }
}