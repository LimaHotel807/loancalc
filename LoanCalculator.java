/*
    Written by Lucas Holl.
    Last modified: 1/11/2018
    Student#: 3280916
*/

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class LoanCalculator 
{
    static Scanner console = new Scanner(System.in);
    static LoanCalculator calc = new LoanCalculator();
    private Client[] client = new Client[5];
    private int clientNumber = 0;
    private Account account = new Account();
    static DecimalFormat df = new DecimalFormat("0.00");

    public void run()
    {
        int choice, position;
        showOption();
        choice = console.nextInt();
        while(choice != 8)
        {
            switch(choice) //Switch used for the main menu
            {
                case 1: createClient(); //Starts process of creating client
                        break;
                case 2: deleteClient(); //Starts process of deleting client
                        break;
                case 3: showClient(); //Starts process of showing client
                        break;
                case 4: createAccount(); //Starts process of creating account
                        break;
                case 5: deleteAccount(); //Starts process of deleting account
                        break;
                case 6: showAccount(); //Starts process of showing account
                        break;
                case 7: saveInFile(); //Starts process of exporting information to a .txt file
                        break;
                case 8: System.exit(0); //Exits the program
                default: System.out.println("Invalid selection!");
            }

            showOption();
            choice = console.nextInt();
        }
    }
    
  
    public static void main(String[] args)
    {
        calc.run(); //Runs menu
    }

    public static void showOption() //Shows options for input
    {
        System.out.println("Please select an option");
        System.out.println("1 - Add a client");
        System.out.println("2 - Delete a client");
        System.out.println("3 - Show a client");
        System.out.println("4 - Add an account");
        System.out.println("5 - Delete an account");
        System.out.println("6 - Show account details");
        System.out.println("7 - Save in a file");
        System.out.println("8 - Exit");
    }

    public void createClient() //This method creates a client
    {
        String name = "", tempName;
        while(name.equals(""))
        {    
            if(clientNumber < 5)
            {
                client[clientNumber] = new Client();   

                //This section sets the name of the client.

                System.out.println("Please enter your name.");
                tempName = console.nextLine();
                if(tempName.equals("")) tempName = console.nextLine();

                for (int i = 0; i < client.length; i++)
                {
                    if (client[i].getName().equals(tempName))
                    {
                        System.out.println("Client already exists" + "\n");
                        return;
                    }

                    else
                    {
                        name = tempName;
                        client[clientNumber].setName(name);
                        break;
                    }
                }

                System.out.println("Name: " + client[clientNumber].getName() + "\n");
            
                //This section sets the age of the client.
                int age;
            
                System.out.println("Please enter your age.");
                age = console.nextInt();

                while(age < 0) //Checks for a negative value entered when asked for age.
                {
                    System.out.println("Invalid input! Your age cannot be negative!");
                    age = console.nextInt();
                }

                client[clientNumber].setAge(age);
                System.out.println("Age: " + client[clientNumber].getAge() + "\n");

                //This section sets the income of the client.
                double income;
                System.out.println("Please enter your total annual income.");
                income = console.nextDouble();

                while(income < 0) //Checks for a invalid value when asked for income.
                {
                    System.out.println("Invalid input! You cannot have a negative salary!");
                    income = console.nextDouble();
                }

                client[clientNumber].setIncome(income);
                System.out.println("Income: $" + df.format(client[clientNumber].getIncome()) + "\n");

                clientNumber++;
            }

            else
            {
                System.out.println("Maxmimum number of clients reached." + "\n");
                break;
            }
        }
    }

    public void deleteClient() //This method deletes a client
    {
        if (clientNumber == 0)
        {
            System.out.println("There are no clients." + "\n");
            return;
        }

        System.out.println("Enter client name");
        String nameCheck = console.nextLine();
        if(nameCheck.equals("")) nameCheck = console.nextLine();
        int clientExists = clientExists(nameCheck);
        if (clientExists == -1)
        {
            System.out.println("Client doesn't exist" + "\n");         
        }
        else
        {
            System.out.println("Client No: " + clientExists + "\n");

            Client[] temp = new Client[5];
            client[clientExists] = null;
            for (int i = 0; i < client.length; i++)
            {
                if(client[i] == null && i != (client.length - 1)) 
                {
                    temp[i] = client[i + 1];
                    client[i + 1] = null;
                }
                else
                {
                    temp[i] = client[i];
                }
            }
            client = temp;
        }
        clientNumber--;
    }

    public void showClient() //This method shows a client
    {
        try
        {
            String name = "";
            while(name.equals(""))
            {
                System.out.println("Please enter the name of a client.");
                name = console.nextLine();
                if (name.equals("")) name = console.nextLine();

                for (int i = 0; i < client.length; i++)
                {
                    if (client[i].getName().equals(name))
                    {
                        System.out.println("Name: " + client[i].getName());
                        System.out.println("Age: " + client[i].getAge());
                        System.out.println("Income: $" + df.format(client[i].getIncome()));
                        System.out.println("Number of accounts: " + client[i].getNumOfAccounts() + "\n");
                        break;
                    }
                    else
                    {
                        System.out.println("Client does not exist." + "\n");
                        break;
                    }

                }
            }
        }

        catch(Exception e)
        {
            System.out.println("Exception!" + "\n");
        }
    }

    public void createAccount() //this method creates an account
    {
        if(clientNumber == 0)
        {
            System.out.println("There are no clients." + "\n");
            return;
        }
        
        System.out.println("Enter client name.");
        String nameCheck = console.nextLine();

        if(nameCheck.equals("")) nameCheck = console.nextLine();
        System.out.println("Name: " + client[clientNumber].getName() + "\n");

        
        int clientExists = clientExists(nameCheck);
        if (clientExists == -1)
        {
            System.out.println("Client doesn't exist." + "\n");         
        }

        else
        {
            client[clientExists].createAccount();
        }
    }

    public int clientExists(String tempNameCheck) //This method checks to see if the client exists using their name
    {
        boolean clientExists = false;
        for (int i = 0; i < clientNumber; i++)
        {
            if(tempNameCheck.equals(client[i].getName()))
            {
                clientExists = true;
                return i;
            }
            else
            {
                clientExists = false;
                System.out.println(i);
            }
        }
        return -1;
    }

    public int accountExists(int tempAccountCheck) //This method checks to see how many accounts exist per client
    {
        boolean accountExists = false;
        for (int i = 0; i < client[i].getAccountNumber(); i++)
        {
            if(client[i].getNumOfAccounts() > 0)
            {
                accountExists = true;
                return i;
            }
            else
            {
                accountExists = false;
                System.out.println(i);
            }
        }
        return -1;
    }

    public void deleteAccount() //This method deletes an account
    {
        if (clientNumber < 1)
        {
            System.out.println("There are no clients." + "\n");
            return;
        }

        System.out.println("Enter client name.");
        String nameCheck = console.nextLine();
        if(nameCheck.equals("")) nameCheck = console.nextLine();
        int clientExists = clientExists(nameCheck);
        if (clientExists == -1)
        {
            System.out.println("Client doesn't exist." + "\n");         
        }
        else
        {
            System.out.println("Please choose an account to delete.");
            int accountChoice = console.nextInt();

            int accountExists = accountExists(accountChoice);
            if (accountExists == -1)
            {
                System.out.println("Account does't exist." + "\n");    
            }
            else
            {
                System.out.println("Deleting account..." + "\n");
                client[accountChoice].deleteAccount(accountChoice);
            }
        }
    }

    public void showAccount() //This method shows an account
    {
        try
        {
            if (clientNumber < 1)
            {
                System.out.println("There are no clients." + "\n");
                return;
            }

            System.out.println("Enter client name.");
            String nameCheck = console.nextLine();
            if(nameCheck.equals("")) nameCheck = console.nextLine();
            int clientExists = clientExists(nameCheck);
            if (clientExists == -1)
            {
                System.out.println("Client doesn't exist." + "\n");         
            }

            else
            {
        	    System.out.println("Please choose an account.");
                int accountChoice = console.nextInt();

                int accountExists = accountExists(accountChoice);
                if (accountExists == -1)
                {
                    System.out.println("Account does't exist." + "\n");    
                }
                else
                {
                    client[accountChoice].getAccount(accountChoice);
                }
            }
        }

        catch(Exception e)
        {
            System.out.println("Exception! Please contact the administrator of this software." + "\n");
        }
    }

    public void saveInFile() //This method exports information to a .txt file
    {
        String fileName = "Account Details.txt";

        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(fileName);
        }

        catch(FileNotFoundException e)
        {
            System.out.println("File not found." + "\n");
        }


        String name = "";
        if (clientNumber < 1)
        {
            System.out.println("There are no clients." + "\n");
            return;
        }

        else
        {
            while(name.equals(""))
            {
                System.out.println("Please enter the name of a client.");
                name = console.nextLine();
                if (name.equals("")) name = console.nextLine();
                for (int i = 0; i < client.length; i++)
                {
                    if (client[i].getName().equals(name))
                    {
                        outputStream.println("Name: " + client[i].getName());
                        outputStream.println("Age: " + client[i].getAge());
                        outputStream.println("Income: $" + df.format(client[i].getIncome()));
                        outputStream.println("Number of accounts: " + client[i].getNumOfAccounts());
                        break;
                    }
                    else
                    {
                        System.out.println("Client does not exist." + "\n");
                        break;
                    }
                }
            }

            outputStream.close();
            System.out.println("File written." + "\n");
        }
    }
}