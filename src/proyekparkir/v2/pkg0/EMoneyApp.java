/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyekparkir.v2.pkg0;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import static proyekparkir.v2.pkg0.ProyekParkirV20.validPassword;

/**
 *
 * @author USER
 */
class Deposit{
    int cash;
    String method;
    LocalDate date;
    public Deposit(){
    }
    public Deposit(int cash, LocalDate date, String method){
        this.cash = cash;
        this.date = date;
        this.method = method;
    }
}
class eMoneyAccount{
    String fullName;
    String username;
    String password;
    String telephone;
    String address;
    String NIK;
    String gender;
    String province;
    String email;
    int cash;
    String city;
    int age;
    ArrayList<Deposit> deposits= new ArrayList<Deposit>();
    public eMoneyAccount(){
        
    }
    public eMoneyAccount(String fullName, String username, String password, String telephone, String address, String NIK, String gender, String province, String email, String city, int cash){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.address = address;
        this.NIK = NIK;
        this.gender = gender;
        this.province = province;
        this.email = email;
        this.cash = cash;
        this.city = city;
    }
}
public class EMoneyApp {
    static void adminMenuEMoney(ArrayList<eMoneyAccount> accounts, Queue<eMoneyAccount> regQueue){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
        int menu = 0;
        while(menu != 4){
            System.out.println("===================================================================================");
            System.out.println("                               E-Money Admin Menu");
            System.out.println("===================================================================================");
            System.out.println("1. See Pending List");
            System.out.println("2. Start Approval");
            System.out.println("3. See Account List(would not be allowed in reality, just for displaying accounts)");
            System.out.println("4. Exit");
            menu = inputn.nextInt();
            if(menu == 1){
                for(eMoneyAccount account : regQueue){
                    System.out.println("Username: " + account.username);
                }
            }
            else if(menu == 2){
                if(regQueue.isEmpty()){
                    System.out.println("registry queue is empty!");
                }
                else{
                    boolean pressOn = true;
                    while(pressOn){
                        eMoneyAccount peek = (eMoneyAccount) regQueue.peek(); 
                        System.out.println("Full Name: " + peek.fullName);
                        System.out.println("Username: " + peek.username);
                        System.out.println("Address: " + peek.address);
                        System.out.println("NIK: " + peek.NIK);
                        System.out.println("Gender: " + peek.gender);
                        System.out.println("City: " + peek.city);
                        System.out.println("");
                        System.out.println("To Approve enter 1");
                        System.out.println("To Deny enter 2");
                        System.out.println("To Cancel processing enter 3");
                        System.out.println("");
                        int check = inputn.nextInt();
                        if(check == 1){
                            accounts.add(peek);
                            regQueue.remove(peek);
                            pressOn = false;
                        }
                        else if(check == 2){
                            regQueue.remove(peek);
                            pressOn = false;
                        }
                        else{
                           pressOn = false;
                        }
                    }
                }
                
            }
            else if(menu == 3){
                eMoneyAccount x = new eMoneyAccount();
                System.out.println("==========================================");
                System.out.println("Accounts: ");
                System.out.println("------------------------------------------");
                for (int i = 0; i < accounts.size(); i++) {
                    x = accounts.get(i);
                    System.out.println("Name: " + x.fullName);
                    System.out.println("Email: " + x.email);
                    System.out.println("Username: " + x.username);
                    System.out.println("Password: " + x.password);
                    System.out.println("NIK: " + x.NIK);
                    System.out.println("Gender: " + x.gender);
                    System.out.println("Cash: " + x.cash);
                    System.out.println("");
                }
                System.out.println("------------------------------------------");
            }
            else if(menu == 4){
                
            }
            else{
                System.out.println("sorry, there are only 3 options here");
            }
        }
    }
    static void emoneyMenu(ArrayList<eMoneyAccount> accounts, Queue<eMoneyAccount> regQueue){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
        int menu = -1;
        while(menu != 3){
            System.out.println("=================================================");
            System.out.println("E-Money Menu");
            System.out.println("-------------------------------------------------");
            System.out.println("1. Register");
            System.out.println("2. User Account");
            System.out.println("3. Exit");
            menu = inputn.nextInt();
            if(menu == 1){
                registerEMoney(accounts, regQueue);
            }
            else if(menu == 2){
                eMoneyAccount user = loginEMoney(accounts);
                String username = user.username;
                userAccount(accounts, username);
            }
            else{
                System.out.println("Sorry, currently the menu is only between 1 and 2");
            }   
        }
    }
    static int getIndex(ArrayList<eMoneyAccount> accounts, String username){
        int index = -1;
        eMoneyAccount x = new eMoneyAccount();
        for (int i = 0; i < accounts.size(); i++) {
            x = accounts.get(i);
            if(username.equals(x.username)){
                index = i;
            }
        }
        return index;
    }
    static eMoneyAccount loginEMoney(ArrayList<eMoneyAccount> accounts){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
        System.out.println("...................................logging in");
        System.out.println("Please enter username: ");
        String username = inputl.nextLine();
        while(!usernameCheckEMoney(accounts, username)){
                System.out.println("sorry, username doesn't seem to exists, please input username again");
                username = inputl.nextLine();
        }
        eMoneyAccount sessionUser = getUser(accounts, username);
        System.out.println("Please input password");
        String password = inputl.nextLine();
        int counter = 0;
        while(!passwordCheck(accounts, username, password)){
            System.out.println("sorry, password is incorrect");
            System.out.println("key in f if you have forgotten your password");
            password = inputl.nextLine();
            if(password == "f"){
                System.out.println("please input your email, where your current password will be sent");
                String email = inputl.nextLine();
                if(sessionUser.email.equals(email)){
                    System.out.println("Your Password is " + sessionUser.password);
                }
            }
        }
        return sessionUser;
    }
    static void registerEMoney(ArrayList<eMoneyAccount> accounts, Queue<eMoneyAccount> regQueue){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
        System.out.println("===================================================");
        System.out.println("          This is the Register Menu");
        System.out.println("---------------------------------------------------");
        System.out.println("Please input the username");
        String username = inputl.nextLine();
        if(!usernameCheckEMoney(accounts, username)){
            System.out.println("Please input a password");
            String password = inputl.nextLine();
            while(!validPassword(password)){
                System.out.println("Please input password");
                password = inputl.nextLine();
            }
            if(validPassword(password)){
                System.out.println("please input your full name");
                String fullName = inputl.nextLine();
                System.out.println("please input your telephone number");
                String telephone = inputl.nextLine();
                System.out.println("please input your address");
                String address = inputl.nextLine().toLowerCase();
                System.out.println("please input city");
                String city = inputl.nextLine().toLowerCase();
                System.out.println("please input province");
                String province = inputl.nextLine().toLowerCase();
                System.out.println("please input your NIK");
                String NIK = inputl.nextLine();
                System.out.println("please input gender");
                String gender = inputl.nextLine().toLowerCase();
                System.out.println("please input email");
                String email = inputl.nextLine();
                System.out.println("please input the amount of money you would like to deposit in your emoney account");
                System.out.println("[numbers only please, so Rp. 200,000 -> 200000");
                int money = inputn.nextInt();
                System.out.println("please input the method by which you will deposit");
                System.out.println("1. Wire Transter");
                System.out.println("2. Cash at our nearest branches");
                String method = inputl.nextLine();
                eMoneyAccount newAccount = new eMoneyAccount(fullName, username, password, telephone, address, NIK, gender, province, email, city, money);
                regQueue.add(newAccount);
                System.out.println("");
                System.out.println("Thank you for registering with E-money, you account will be created shortly");
            }
        }
        else{
            while(usernameCheckEMoney(accounts, username)){
                System.out.println("sorry, useraname already exists, please try another one");
                System.out.println("Please input username");
                username = inputl.nextLine();
            }
        }
    }
    static eMoneyAccount getUser(ArrayList<eMoneyAccount> accounts, String username){
        int index = -1;
        eMoneyAccount x = new eMoneyAccount();
        for (int i = 0; i < accounts.size(); i++) {
            x = accounts.get(i);
            if(username.equals(x.username)){
                index = i;
            }
        }
        x = accounts.get(index);
        return x;
    }
    static void userAccount(ArrayList<eMoneyAccount> accounts, String username){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
        
        int menu = -1;
        while(menu != 4){
            System.out.println("=============================================");
            System.out.println("                 User Account");
            System.out.println("--------------------------------------------");
            System.out.println("1. See Cash Balance");
            System.out.println("2. See Deposit History");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit Menu");
            menu = inputn.nextInt();
            switch(menu){
                case 1:
                    System.out.println("------------------------------------");
                    eMoneyAccount x = new eMoneyAccount();
                    for (int i = 0; i < accounts.size(); i++) {
                        x = accounts.get(i);
                        if(username.equals(x.username)){
                            System.out.println("Cash Balance: " + x.cash);
                        }
                    }
                    System.out.println("------------------------------------");
                    break;
                case 2:
                    System.out.println("------------------------------------");
                    printDepositHistory(accounts, username);
                    System.out.println("------------------------------------");
                    break;
                case 3:
                    System.out.println("---------------------Deposit Menu----------------------");
                    System.out.println("How much would you like to deposit?");
                    int depositAmount = inputn.nextInt();
                    System.out.println("By what method: ");
                    System.out.println("1. Wire Transter");
                    System.out.println("2. Cash at our nearest branches");
                    int method = inputn.nextInt();
                    String depositMethod = "";
                    if(method == 1){
                        depositMethod = "Wire Transfer";
                    }
                    else if(method == 2){
                        depositMethod = "Cash";
                    }
                    else{
                        System.out.println("sorry, there are currently only the two methods");
                    }
                    LocalDate date = LocalDate.now();
                    Deposit newDeposit = new Deposit(depositAmount, date, depositMethod);
                    System.out.println(newDeposit.date);
                    System.out.println(newDeposit.cash);
                    System.out.println(newDeposit.method);
                    eMoneyAccount account = getUser(accounts, username);
                    account.deposits.add(newDeposit);
                    account.cash += depositAmount;
                    int index = getIndexEMoney(accounts, username);
                    accounts.set(index, account);
                    ArrayList<Deposit> deposit = account.deposits;
                    Deposit depo = deposit.get(0);
                    System.out.println(depo.method);
                    Deposit depo1 = deposit.get(1);
                    System.out.println(depo1.method);
                    break;
                default:
                    break;
            }
        }
    }
    static int getIndexEMoney(ArrayList<eMoneyAccount> accounts, String username){
        int index = -1;
        eMoneyAccount x = new eMoneyAccount();
        for (int i = 0; i < accounts.size(); i++) {
            x = accounts.get(i);
            if(x.username.equals(username)){
                index = i;
            }
        }
        return index;
    }
    static void printDepositHistory(ArrayList<eMoneyAccount> accounts, String username){
        eMoneyAccount x = new eMoneyAccount();
        for (int i = 0; i < accounts.size(); i++) {
            x = accounts.get(i);
            if(username.equals(x.username)){
                if(x.deposits.size() == 0){
                    System.out.println("Deposit History is Empty");
                }
                else{
                    Deposit y = new Deposit();
                    for (int j = 0; j < x.deposits.size(); j++) {
                        y = x.deposits.get(i);
                        System.out.println("Deposit " + y.date);
                        System.out.println("Amount: " + y.cash);
                        System.out.println("By method of: " + y.method);
                        System.out.println("");
                    }
                }
            }
        }
    }
    static boolean usernameCheckEMoney(ArrayList<eMoneyAccount> accounts, String username){
        boolean check = false;
        eMoneyAccount x = new eMoneyAccount();
        for (int i = 0; i < accounts.size(); i++) {
            x = accounts.get(i);
            if(username.equals(x.username)){
                check = true;
            }
        }
        return check;
    }
    static boolean passwordCheck(ArrayList<eMoneyAccount> accounts, String username, String password){
        boolean check = false;
        eMoneyAccount x = new eMoneyAccount();
        for (int i = 0; i < accounts.size(); i++) {
            x = accounts.get(i);
            if(x.username.equals(username)){
                if(x.password.equals(password)){
                    check = true;
                }
            }
        }
        return check;
    }

}
