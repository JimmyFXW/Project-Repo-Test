/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyekparkir.v2.pkg0;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import static proyekparkir.v2.pkg0.EMoneyApp.adminMenuEMoney;
import static proyekparkir.v2.pkg0.EMoneyApp.emoneyMenu;
import static proyekparkir.v2.pkg0.EMoneyApp.getIndex;
import static proyekparkir.v2.pkg0.EMoneyApp.getUser;
import static proyekparkir.v2.pkg0.EMoneyApp.loginEMoney;
import static proyekparkir.v2.pkg0.EMoneyApp.registerEMoney;

class Branch{
    String name;
    ParkingSlot[] slots;
    Gate[] gates;
    int totalIncome;
    public Branch(){
    }
    public Branch(String name, int slots, int gates){
        this.slots = new ParkingSlot[slots];
        this.gates= new Gate[gates];
        this.name = name;
    }
}
class ParkingSlot{
    boolean availability; //availability of slot
    LocalDateTime entranceTime;//kalau program beneran local time
    int totalTime;
    int totalIncome;
    String plateNo;
    public ParkingSlot(){
        
    }
    public ParkingSlot(boolean availability){
        this.availability = false;
    }
}
class Gate{
    String location;
    int frequency;
    boolean entryOr;
     public Gate(){
    }
    public Gate(String lcoation){
        this.location = location;
    }
}
class Member{
    String username;
    String password;
    int eMoney;
    int points;
    public Member(){
    }
    public Member(String username, String password, int eMoney){
        this.username = username;
        this.password = password;
        this.eMoney = eMoney;
    }
}
public class ProyekParkirV20 {
    public static void main(String[] args) {
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
	//Bernard membuat yang eMoneyApp, Jimmy yang membuat proyek parkirnya Ko

        ArrayList<Branch> branches = new ArrayList<Branch>();
        ArrayList<Member> members = new ArrayList<Member>();
        ArrayList<eMoneyAccount> accounts = new ArrayList<eMoneyAccount>();
        
         //=========================================================DUMMY DATA===========================================================================
        eMoneyAccount member1 = new eMoneyAccount("James Khan", "jamesBoeing778", "jamB007!", "082346519723", "Sudirman 65", "1326458021351345", "male", "Jawa Barat", "jamesBoeing778@gmail.com", "Bandung", 100000);
        eMoneyAccount member2 = new eMoneyAccount("Kay Boyle", "Kboyle", "Hiya2*", "08136452946", "Ir H Juanda 215", "4931657254651302", "male", "Jawa Barat", "kboyle@gmail.com", "Bandung", 250000);
        eMoneyAccount member3 = new eMoneyAccount("Ted Beneke", "Ted7", "kayL4$", "081264539215", "Braga 35", "32059461316259421", "male", "Jawa Barat", "ted7@gmail.com", "Bandung", 350000);
        
        LocalDate date = LocalDate.now();
        Deposit uno = new Deposit(100000, date, "Wire Trasfer");
        Deposit dos = new Deposit(250000, date, "Wire Trasfer");
        Deposit tres = new Deposit(350000, date, "Wire Trasfer");
        
        member1.deposits.add(uno);
        member2.deposits.add(dos);
        member3.deposits.add(tres);
        
        Branch newBranch = new Branch("Paskal23", 30, 4);
        for (int i = 0; i < newBranch.gates.length; i++) {
            newBranch.gates[i] = new Gate();
        }
        for (int i = 0; i < newBranch.slots.length; i++) {
            newBranch.slots[i] = new ParkingSlot();
        }
        for (int i = 0; i < newBranch.slots.length; i++) {
            newBranch.slots[i].availability = true;
            newBranch.slots[i].plateNo = "";
        }
        newBranch.gates[0].location = "west1";
        newBranch.gates[0].entryOr = true;
        newBranch.gates[1].location = "west2";
        newBranch.gates[1].entryOr = true;
        newBranch.gates[2].location = "north1";
        newBranch.gates[2].entryOr = false;
        newBranch.gates[3].location = "north2";
        newBranch.gates[3].entryOr = false;
        
        branches.add(newBranch);
        accounts.add(member1);
        accounts.add(member2);
        accounts.add(member3);
        //=========================================================DUMMY DATA===========================================================================
        
        
        //=========================================================DUMMY DATA===========================================================================
        Member Member = new Member("bryanJ", "password", 200000);
        Member MMember = new Member("kiara698", "lovechristies", 300000);
        Member MMMember = new Member("Kevin382", "12345", 150000);
        
        members.add(Member);
        members.add(MMember);
        members.add(MMMember);
        //=========================================================DUMMY DATA===========================================================================
        
        Queue<eMoneyAccount> registerQueue = new LinkedList<>();
        int app = 0;
        int counter = 0;
        while(app != 3){
            System.out.println("                             Master Menu");
            System.out.println("===========================================================================");
            System.out.println("Apps: \n1. Parking App\n2. E-Money App\n3. Exit");
            app = inputn.nextInt();
            if(app == 1){
                System.out.println("                            The Parking App");
                System.out.println("===========================================================================");
                if(counter < 1){
                    System.out.println("Hi, this is the first time this program is initiated");
                }
                counter++;
                System.out.println("would you like to use the created branch/branches, or create a new one [created/new]");
                String yesNo = inputl.nextLine();
                if(yesNo.equals("new")){
                    createBranch(branches);
                }
                else{
                    String menu = "";
                    while(!menu.equals("exit")){
                        System.out.println("Are you a user or an admin? [type exit to exit]");
                        menu = inputl.nextLine();
                        if(menu.equals("user")){
                            userMenu(members, branches, accounts, registerQueue);
                        }
                        else if(menu.equals("admin")){
                            adminMenu(members, branches, accounts, registerQueue);
                        }
                        else if(menu.equals("exit")){
                        }
                        else{
                            System.out.println("I apologize, currently the menu still revolves around user, admin and exit, please try again");
                        }
                    }
                }

            }
            else if(app == 2){
                int menu = 0;
                while(menu != 3){
                    System.out.println("Menu: ");
                    System.out.println("1. User");
                    System.out.println("2. Admin");
                    System.out.println("3. Exit");
                    menu = inputn.nextInt();
                    if(menu == 1){
                        emoneyMenu(accounts, registerQueue);
                    }
                    else if(menu == 2){
                        adminMenuEMoney(accounts, registerQueue);
                    }
                    else if(menu == 3){
                    }
                    else{
                        System.out.println("sorry, options are now just the three");
                    }
                }
            }
            else{
                System.out.println("sorry, options are only between 1-3");
            }
        }
        
    }
    static int selectGate(Branch branch){
        Scanner inputn = new Scanner(System.in);
        
        int index = -1;
        System.out.println("Gates: ");
        System.out.println("-----------------------------------------------------------------");
        for (int i = 0; i < branch.gates.length; i++) {
            if(branch.gates[i].entryOr){
                System.out.println((i+1) + ". Entry Gate " + branch.gates[i].location);
            }
            else{
                System.out.println((i+1) + ". Exit Gate " + branch.gates[i].location);
            }
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Please select gate number");
        index = inputn.nextInt();
        while(index < 1 && index > branch.gates.length-1){
            System.out.println("incorrect number, pelase try and input again");
            index = inputn.nextInt();
        }
        index--;
        return index;
    }
    static void createBranch(ArrayList<Branch> branches){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
            System.out.println("                            Branch Creation");
            System.out.println("===========================================================================");
            System.out.println("You are creating the 1st branch...");
            System.out.println("");
            System.out.println("Please input the name of your first branch: [case sensitive]");
            String name = inputl.nextLine();
            System.out.println("You have created the " + name + " branch!");
            System.out.println("");
            System.out.println("Please input the number of parking slots you would like to have in the " + name +" branch: ");
            int slots = inputn.nextInt();
            System.out.println("You have created " + slots + "slots in the " + name + " branch");
            System.out.println("");
            System.out.println("You are now creating gates");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("For each branch that you have, you need a minimum of two gates");
            System.out.println("1 for entry and 1 for exit");
            System.out.println("So bearing that in mind....");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Please input the number of gates you would like to have in that branch: ");
            int gates = inputn.nextInt();
            System.out.println("You have created " + gates + " gates...");
            System.out.println("");
            while(gates < 2){
                System.out.println("");
                System.out.println("I do apologize, we need a minimum of 2 gates...");
                System.out.println("Please reinput the number of gates you would like to have in that branch: ");
                gates = inputn.nextInt();
            }
            Branch newBranch = new Branch(name, slots, gates);
            for (int i = 0; i < newBranch.gates.length; i++) {
                newBranch.gates[i] = new Gate();
            }
            for (int i = 0; i < newBranch.slots.length; i++) {
               newBranch.slots[i] = new ParkingSlot();
            }
            for (int i = 0; i < newBranch.slots.length; i++) {
                newBranch.slots[i].availability = true;
                newBranch.slots[i].plateNo = "";
            }
            System.out.println("");
            System.out.println("               Gate Creation");
            for (int i = 0; i < newBranch.gates.length; i++) {
                System.out.println("Please enter " + (i+1) + ordinal(i+1) + " gate's name [anything will do]: ");
                System.out.println("[north, west, or lobby name, etc]");
                String location = inputl.nextLine();
                newBranch.gates[i].location = location;
                System.out.println("is this gate an entry or an exit?");
                System.out.println("1. Entry\n2. Exit");
                int inOut = inputn.nextInt();
                while(inOut != 1 && inOut != 2){
                    System.out.println("input is only 1 or 2");
                    System.out.println("please try again");
                    inOut = inputn.nextInt();
                }
                if(inOut == 1){
                    newBranch.gates[i].entryOr = true;
                }
                else if(inOut == 2){
                    newBranch.gates[i].entryOr = false;
                }
                else{
                    System.out.println("input is only 1 or 2");
                }
            }
            branches.add(newBranch);

            System.out.println("Well Done, you have just created your first branch, congratulations!");
            System.out.println("Would you like to create another branch? [Y/N]");
            char createAgain = inputl.next().charAt(0);
            createAgain = Character.toLowerCase(createAgain);

            while(createAgain == 'y'){
                int BranchNumber = branches.size();
                System.out.println("Creating the " + BranchNumber + ordinal(BranchNumber) + " branch");
                System.out.println("Please input name: [case sensitive]");
                name = inputl.nextLine().toLowerCase();
                if(!checkBranch(branches, name)){
                    System.out.println("Please input the number of parking slots you would like in that branch: ");
                    slots = inputn.nextInt();
                    System.out.println("Please input the number of gates you would like in that branch: ");
                    gates = inputn.nextInt();
                    Branch newwBranch = new Branch(name, slots, gates);
                    for (int i = 0; i < newwBranch.gates.length; i++) {
                        newwBranch.gates[i] = new Gate();
                    }
                    for (int i = 0; i < newwBranch.slots.length; i++) {
                       newwBranch.slots[i] = new ParkingSlot();
                    }
                    for (int i = 0; i < newBranch.slots.length; i++) {
                        newBranch.slots[i].availability = true;
                        newBranch.slots[i].plateNo = "";
                    }
                    for (int i = 0; i < newwBranch.gates.length; i++) {
                        System.out.println("Please enter " + (i+1) + ordinal(i+1) + " gate location: ");
                        System.out.println("[north, west, or lobby name, etc");
                        String location = inputl.nextLine();
                        newwBranch.gates[i].location = location;
                        System.out.println("is this gate an entry or an exit?");
                        System.out.println("1. Entry\n2. Exit");
                        int inOut = inputn.nextInt();
                        while(inOut != 1 || inOut != 2){
                            inOut = inputn.nextInt();
                            if(inOut == 1){
                                newBranch.gates[i].entryOr = true;
                                System.out.println("true");
                            }
                            else if(inOut == 2){
                                newBranch.gates[i].entryOr = false;
                                System.out.println("false");
                            }
                            else{
                                System.out.println("input is only 1 or 2");
                            }
                        }
                    }
                    branches.add(newwBranch);
                    System.out.println("Would you like to create another branch? [Y/N]");
                    createAgain = inputl.next().charAt(0);
                    createAgain = Character.toLowerCase(createAgain);
                }
                else{
                    while(checkBranch(branches, name)){
                        System.out.println("Creating the " + BranchNumber + ordinal(BranchNumber) + " branch");
                        System.out.println("Please input name: [case sensitive]");
                    }
                    System.out.println("Please input the number of parking slots you would like in that branch: ");
                    slots = inputn.nextInt();
                    System.out.println("Please input the number of gates you would like in that branch: ");
                    gates = inputn.nextInt();
                    Branch newwBranch = new Branch(name, slots, gates);
                    for (int i = 0; i < newwBranch.gates.length; i++) {
                        newwBranch.gates[i] = new Gate();
                    }
                    for (int i = 0; i < newwBranch.slots.length; i++) {
                       newwBranch.slots[i] = new ParkingSlot();
                    }
                    for (int i = 0; i < newBranch.slots.length; i++) {
                        newBranch.slots[i].availability = true;
                        newBranch.slots[i].plateNo = "";
                    }
                    for (int i = 0; i < newwBranch.gates.length; i++) {
                        System.out.println("Please enter " + (i+1) + ordinal(i+1) + " gate location: ");
                        System.out.println("[north, west, or lobby name, etc");
                        String location = inputl.nextLine();
                        newwBranch.gates[i].location = location;
                        System.out.println("is this gate an entry or an exit?");
                        System.out.println("1. Entry\n2. Exit");
                        int inOut = inputn.nextInt();
                        while(inOut != 1 || inOut != 2){
                            inOut = inputn.nextInt();
                            if(inOut == 1){
                                newBranch.gates[i].entryOr = true;
                                System.out.println("true");
                            }
                            else if(inOut == 2){
                                newBranch.gates[i].entryOr = false;
                                System.out.println("false");
                            }
                            else{
                                System.out.println("input is only 1 or 2");
                            }
                        }
                    }
                    branches.add(newwBranch);
                    System.out.println("Would you like to create another branch? [Y/N]");
                    createAgain = inputl.next().charAt(0);
                    createAgain = Character.toLowerCase(createAgain);
                }
            }
    }
    static void adminMenu(ArrayList<Member> members, ArrayList<Branch> branches, ArrayList<eMoneyAccount> accounts,Queue<eMoneyAccount> registerQueue){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        System.out.println("=========================================================================");
        System.out.println("                              Admin Menu ");
        System.out.println("-------------------------------------------------------------------------");
        Branch x = new Branch();
        for (int i = 0; i < branches.size(); i++) {
            x = branches.get(i);
            System.out.println("Branch      : " + x.name + "-" + (i+1));
            System.out.println("Total Income: Rp." + x.totalIncome);
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Please input the number *beside* the branch you would like to select: ");
        int index = inputn.nextInt();
        while(index < 0 && index > branches.size()){
            System.out.println("Index is only between 1 and " + branches.size());
            index = inputn.nextInt();
        }
        index--;
        Branch sessionBranch = branches.get(index);
        System.out.println("===========================================");
        System.out.println("Branch " + sessionBranch.name + "'s Menu");
        System.out.println("-------------------------------------------");
        System.out.println("Would you like to display: ");
        System.out.println("1. Slot base data");
        System.out.println("2. Gate based data");
        int displayOption = inputn.nextInt();
        if(displayOption == 1){
            System.out.println("============================================================================================================");
            System.out.println("Slot no.     Availability     Total Time Parked     Total Income     Plate No Parked(now)");
            for (int i = 0; i < sessionBranch.slots.length; i++) {
                System.out.println(" "+(i+1)+ "              "+    sessionBranch.slots[i].availability + "                 " + sessionBranch.slots[i].totalTime + "                   " +sessionBranch.slots[i].totalIncome +"                  -"+ sessionBranch.slots[i].plateNo);
            }
        }
        else if(displayOption == 2){
            System.out.println("============================================================================================================");
            System.out.println("Gate Location       Frequency");
            for (int i = 0; i < sessionBranch.gates.length; i++) {
                System.out.println("   " + sessionBranch.gates[i].location + "                " + sessionBranch.gates[i].frequency);
            }
            System.out.println("------------------------------------------------------------------------------------------------------------");
        }
        else{
            System.out.println("sorry, the option currently available is between 1 and 2");
        }
    }
    static void userMenu(ArrayList<Member> members, ArrayList<Branch> branches, ArrayList<eMoneyAccount> accounts,Queue<eMoneyAccount> registerQueue){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.println("                       User Menu ");
        System.out.println("----------------------------------------------------------");
        Branch sessionBranch = selectBranch(branches);
        int branchIndex = branchIndex(branches, sessionBranch);
        
        int gate = selectGate(sessionBranch);
        sessionBranch.gates[gate].frequency++;
        
        branches.set(branchIndex, sessionBranch);
        
        if(sessionBranch.gates[gate].entryOr){//entrance gate
            entryMenu(branches, sessionBranch);
        }
        else{//exit gate
            exitMenu(branches, members, accounts, registerQueue, sessionBranch);
        }
        
    }
    static int branchIndex(ArrayList<Branch> branches, Branch branch){
        int index = -1;
        Branch x = new Branch();
        for (int i = 0; i < branches.size(); i++) {
            x = branches.get(i);
            if(x.equals(branch)){
                index = i;
            }
        }
        return index;
    }
    static Branch selectBranch(ArrayList<Branch> branches){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
        Branch sessionBranch = new Branch();
        System.out.println("Please enter the name of the branch you are at");
        System.out.println("----------------------------------------------");
        displayBranches(branches);
        System.out.println("----------------------------------------------");
        String branch = inputl.nextLine();
        if(checkBranch(branches, branch)){//if branch is typed right
            Branch selectedBranch = getBranch(branches, branch);
            sessionBranch = selectedBranch;
        }
        else{
            if(similarExist(branches, branch)){
                ArrayList<String> similars = similarPerhaps(branches, branch);
                displaySimilars(similars);
                System.out.println("----------------------------------------");
                if(similars.size() > 1){
                    System.out.println("Is one of these the branches you are at?");
                }
                else{
                    System.out.println("Is this the branch you meant?");
                }
                System.out.println("[y/n]");
                char yesNo = inputl.next().charAt(0);
                if(yesNo == 'y'){
                    if(similars.size() > 1){
                        System.out.println("Please key in the number beside the branch name you would like to select");
                        int number = inputn.nextInt();
                        number--;
                        String branchName = similars.get(number);
                        Branch selectedBranch = getBranch(branches, branchName);
                        sessionBranch = selectedBranch;
                    }
                    else{
                        String branchName = similars.get(0);
                        Branch selectedBranch = getBranch(branches, branchName);
                        sessionBranch = selectedBranch;
                    }
                }
                else if(yesNo == 'n'){
                    System.out.println("Please retype the branch name");
                    String branchName = inputl.nextLine();
                    while(!checkBranch(branches, branchName)){
                        System.out.println("Please retype the branch name");
                        branchName = inputl.nextLine();
                    }
                    Branch selectedBranch = getBranch(branches, branchName);
                    sessionBranch = selectedBranch;
                }
            }
            else{
                System.out.println("Please retype the branch name");
                String branchName = inputl.nextLine();
                while(!checkBranch(branches, branchName)){
                    System.out.println("Please retype the branch name");
                    branchName = inputl.nextLine();
                }
                Branch selectedBranch = getBranch(branches, branchName);
                sessionBranch = selectedBranch;
            }
        }
        return sessionBranch;
    }
    static void entryMenu(ArrayList<Branch> branches, Branch sessionBranch){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        System.out.println("=============================================================================");
        System.out.println("                           "+sessionBranch+ " menu");
        System.out.println("=============================================================================");
        displayParkingSpace(branches, sessionBranch);

        System.out.println("Please enter one of the available slots you would like to park at");
        int slotNumber = inputn.nextInt();
        slotNumber--;

        while(slotNumber > sessionBranch.slots.length || slotNumber < 0){
            System.out.println("Slot number is incorrect");
            System.out.println("Please input slot number again");
            slotNumber = inputn.nextInt();
            slotNumber--;
        }

        while(!checkSlot(branches, sessionBranch, slotNumber)){
            System.out.println("Slot is occupied!");
            System.out.println("Please input slot number again");
            slotNumber = inputn.nextInt();
            slotNumber--;
        }
        
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Enter Plate Number [scanned in reality]");
        String plateNo = inputl.nextLine();
        System.out.println("slot number: " + (slotNumber+1));
        amendParkingSpace(branches, sessionBranch, slotNumber, now, plateNo, 0);

    }
    static void exitMenu(ArrayList<Branch> branches, ArrayList<Member> members, ArrayList<eMoneyAccount> accounts, Queue<eMoneyAccount> regQueue, Branch sessionBranch){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
        Member sessionMember = new Member();
        int index = indexSearch(branches, sessionBranch);
        long timeParked = 0;
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Exiting parking space...");
        System.out.println("Please enter plate number");
        String plateNo = inputl.nextLine();
        while(!checkPlate(sessionBranch, plateNo)){
            System.out.println("there is no such plate, please enter again [again, this is done by scanners irl]");
            plateNo = inputl.nextLine();
        }
        System.out.println("Do you have a membership? [yes/no]");
        String yesNo = inputl.nextLine().toLowerCase();
        if(yesNo.equals("yes")){//=====================================logged in=====================================================
            sessionMember = loginMenu(members);
            System.out.println("real time or just input hours?");
            System.out.println("1. Real time [not recommended, we need a test demo of 1+ hour");
            System.out.println("2. Manual input");
            int time = inputn.nextInt();
            while(time != 1 && time != 2){
                System.out.println("1. Real time [not recommended, we need a test demo of 1+ hour");
                System.out.println("2. Manual input");
                time = inputn.nextInt();
            }
            if(time == 1){
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime entry = entranceTime(sessionBranch, plateNo);

                long diff = ChronoUnit.SECONDS.between(entry, now);
                diff = diff/60/60;

                timeParked = diff;
            }
            else if(time == 2){
                System.out.println("please enter hours manually: ");
                timeParked = inputn.nextInt();
            }
            int parkingTime = Math.toIntExact(timeParked);
            int price = priceCalc(parkingTime);
            int slotIndex = getSlotIndex(sessionBranch.slots, plateNo);

            if(sessionMember.points > price){
                System.out.println("it seems you have enough points to pay with your membership");
                System.out.println("would you like to pay with your points?");
                System.out.println("[yes/no]");
                String point = inputl.nextLine();
                if(point.equals("yes")){
                    System.out.println("points are deducted by " + price);
                    sessionMember.points -= price;
                }
                else if(point.equals("no")){
                    if(sessionMember.eMoney > price){
                        System.out.println("it seems you have enough emoney to pay with your membership");
                        System.out.println("would you like to pay with your emoney?");
                        System.out.println("[yes/no]");
                        String input = inputl.nextLine();
                        if(input.equals("yes")){
                            sessionMember.eMoney -= price;
                        }
                        else if(input.equals("no")){
                            System.out.println("Payed by cash");
                        }
                        else{
                            System.out.println("sorry input is just between yes and no");
                        }
                    }
                    else{
                        System.out.println("sorry, it seems the balance isn't enough");
                        System.out.println("Cash Balance: " + sessionMember.eMoney);
                    }
                }
                else{
                    System.out.println("sorry input is just between yes and no");
                }
            }
            else{
                if(sessionMember.eMoney > price){
                    System.out.println("it seems you have enough emoney to pay with your membership");
                    System.out.println("would you like to pay with your emoney?");
                    System.out.println("[yes/no]");
                    String input = inputl.nextLine();
                    if(input.equals("yes")){
                        sessionMember.eMoney -= price;
                    }
                    else if(input.equals("no")){
                        System.out.println("Payed by cash");
                    }
                    else{
                        System.out.println("sorry input is just between yes and no");
                    }
                }
                else{
                    System.out.println("sorry, it seems the balance isn't enough");
                    System.out.println("Cash Balance: " + sessionMember.eMoney);
                }
            }
            displayReceipt(plateNo, parkingTime, sessionBranch.slots[slotIndex].entranceTime, price);
            sessionBranch.slots[slotIndex].availability = true;
            sessionBranch.slots[slotIndex].entranceTime = null;
            sessionBranch.slots[slotIndex].plateNo = "";
            sessionBranch.slots[slotIndex].totalIncome += price;
            sessionBranch.totalIncome += price;
            sessionBranch.slots[slotIndex].totalTime += parkingTime;
        }
        else if(yesNo.equals("no")){
            System.out.println("would you like to register? [yes/no]");
            String question = inputl.nextLine();
            
            if(question.equals("yes")){
                sessionMember = registerMenu(members, accounts);//===========================just registered========================================
                System.out.println("Thank you for registering with us");
                System.out.println("real time or just input hours?");
                System.out.println("1. Real time");
                System.out.println("2. just input");
                int time = inputn.nextInt();
                if(time == 1){
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime entry = entranceTime(sessionBranch, plateNo);
                
                    long diff = ChronoUnit.SECONDS.between(entry, now);
                    diff = diff/60/60;
                    
                    timeParked = diff;
                }
                else if(time == 2){
                    System.out.println("manual mode:");
                    System.out.println("enter hours");
                    timeParked = inputn.nextInt();
                }
                else{
                    System.out.println("sorry, the input is either 1 or 2");
                }
                int parkingTime = Math.toIntExact(timeParked);
                int price = priceCalc(parkingTime);
                int slotIndex = getSlotIndex(sessionBranch.slots, plateNo);
                
                if(sessionMember.points > price){
                    System.out.println("it seems you have enough points to pay with your membership");
                    System.out.println("would you like to pay with your points?");
                    System.out.println("[yes/no]");
                    String point = inputl.nextLine();
                    if(point.equals("yes")){
                        System.out.println("points are deducted by " + price);
                        sessionMember.points -= price;
                    }
                    else if(point.equals("no")){
                        if(sessionMember.eMoney > price){
                            System.out.println("it seems you have enough emoney to pay with your membership");
                            System.out.println("would you like to pay with your emoney?");
                            System.out.println("[yes/no]");
                            String input = inputl.nextLine();
                            if(input.equals("yes")){
                                sessionMember.eMoney -= price;
                            }
                            else if(input.equals("no")){
                                System.out.println("Payed by cash");
                            }
                            else{
                                System.out.println("sorry input is just between yes and no");
                            }
                        }
                        else{
                            System.out.println("sorry, it seems the balance isn't enough");
                            System.out.println("Cash Balance: " + sessionMember.eMoney);
                        }
                    }
                    else{
                        System.out.println("sorry input is just between yes and no");
                    }
                }
                else{
                    if(sessionMember.eMoney > price){
                        System.out.println("it seems you have enough emoney to pay with your membership");
                        System.out.println("would you like to pay with your emoney?");
                        System.out.println("[yes/no]");
                        String input = inputl.nextLine();
                        if(input.equals("yes")){
                            sessionMember.eMoney -= price;
                        }
                        else if(input.equals("no")){
                            System.out.println("Payed by cash");
                        }
                        else{
                            System.out.println("sorry input is just between yes and no");
                        }
                    }
                    else{
                        System.out.println("sorry, it seems the balance isn't enough");
                        System.out.println("Cash Balance: " + sessionMember.eMoney);
                    }
                }
                displayReceipt(plateNo, parkingTime, sessionBranch.slots[slotIndex].entranceTime, price);
                sessionBranch.slots[slotIndex].availability = true;
                sessionBranch.slots[slotIndex].entranceTime = null;
                sessionBranch.slots[slotIndex].plateNo = "";
                sessionBranch.slots[slotIndex].totalIncome += price;
                sessionBranch.totalIncome += price;
                sessionBranch.slots[slotIndex].totalTime += parkingTime;
            }
            else if(question.equals("no")){//==============================================naaa====================================================
                System.out.println("Payed by cash");
                System.out.println("real time or just input hours?");
                System.out.println("1. Real time");
                System.out.println("2. just input");
                int time = inputn.nextInt();
                if(time == 1){
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime entry = entranceTime(sessionBranch, plateNo);
                
                    long diff = ChronoUnit.SECONDS.between(entry, now);
                    diff = diff/60/60;
                    
                    timeParked = diff;
                }
                else if(time == 2){
                    System.out.println("manual mode:");
                    System.out.println("enter hours");
                    timeParked = inputn.nextInt();
                }
                else{
                    System.out.println("sorry, the input is either 1 or 2");
                }
                int parkingTime = Math.toIntExact(timeParked);
                int price = priceCalc(parkingTime);
                int slotIndex = getSlotIndex(sessionBranch.slots, plateNo);
                displayReceipt(plateNo, parkingTime, sessionBranch.slots[slotIndex].entranceTime, price);
                sessionBranch.slots[slotIndex].availability = true;
                sessionBranch.slots[slotIndex].entranceTime = null;
                sessionBranch.slots[slotIndex].plateNo = "";
                sessionBranch.slots[slotIndex].totalIncome += price;
                sessionBranch.totalIncome += price;
                sessionBranch.slots[slotIndex].totalTime += parkingTime;
            }
            else{
                System.out.println("sorry, the input is either yes or no");
            }
        }
        else{
            System.out.println("sorry, the input is either yes or no");
        }
        
        branches.set(index, sessionBranch);
    }
    static int pointSystem(int hours){
        int points = 0;
        if(hours > 5){
            points += 1000;
        }
        if(hours > 10){
            points += 2000;
        }
        if(hours > 24){
            points += 3000;
        }
        return points;
    }
    static void displayReceipt(String plateNo, int hoursParked, LocalDateTime now, int price){
        System.out.println("==========================Receipt===========================");
        System.out.println("         Number Plate: " + plateNo);
        System.out.println("         Hours Parked: " + hoursParked);
        System.out.println("         Date Time   : " + now);
        System.out.println("         Price       :" + "Rp. " + price);
        System.out.println("");
        System.out.println("-------------------------------------------------------------");
    }
    static int getSlotIndex(ParkingSlot[] slots, String plateNo){
        int index = -1;
        ParkingSlot slot = new ParkingSlot();
        for (int i = 0; i < slots.length; i++) {
            if(slots[i].plateNo.equals(plateNo)){
                index = i;
            }
        }
        return index;
    }
    static int priceCalc(int hours){
        int[] carFee = new int[3];
        carFee[0] = 3000;//first hour
        carFee[1] = 2000;//proceeding hours
        carFee[2] = 15000;
        
        int price = 0;
        while(hours > 24){
            price += carFee[2];
            hours -= 24;
        }
        if(hours > 0){
            price += carFee[0];
        }
        if(hours > 0){
            price += hours*carFee[1];
        }
        return price;
    }
    static boolean checkPlate(Branch sessionBranch, String plateNo){
        boolean check = false;
        System.out.println(sessionBranch.name);
        System.out.println(sessionBranch.slots[0].plateNo);
        for (int i = 0; i < sessionBranch.slots.length; i++) {
            if(sessionBranch.slots[i].plateNo.equals(plateNo)){
                check = true;
            }
        }
        return check;
    }
    static LocalDateTime entranceTime(Branch sessionBranch, String plateNo){
        int index = -1;
        for (int i = 0; i < sessionBranch.slots.length; i++) {
            if(sessionBranch.slots[i].plateNo.equals(plateNo)){
                index = i;
            }
        }
        LocalDateTime time = sessionBranch.slots[index].entranceTime;
        return time;
    }
    static Member registerMenu(ArrayList<Member> members, ArrayList<eMoneyAccount> accounts){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        System.out.println("=======================================================================");
        System.out.println("Welcome to the Parking App Registry Menu");
        System.out.println("");
        
        int deposit = 0;
        
        System.out.println("Please input your username");
        String username = inputl.nextLine();
        
        while(usernameCheck(members, username)){
                System.out.println("Sorry, than username already exists, please try another one");
                username = inputl.nextLine();
        }
        
        System.out.println("Please input your password");
        String password = inputl.nextLine();
        
        while(!validPassword(password)){
            password = inputl.nextLine();
        }
        
        System.out.println("Fantastic!, would you like to deposit some cash?");
        System.out.println("1. Yes, I would like to");
        System.out.println("2. No");
        int answer = inputn.nextInt();
        if(answer == 1){
            System.out.println("Would you like to pay by cash or deposit via eMoney?");
            System.out.println("1. Cash");
            System.out.println("2. Emoney");
            int option = inputn.nextInt();
            if(option == 1){
                System.out.println("how much would you like to deposit?");
                deposit = inputn.nextInt();
            }
            else if(option == 2){
                eMoneyAccount user = loginEMoney(accounts);
                System.out.println("how much would you like to deposit?");
                int amount = inputn.nextInt();
                if(user.cash < deposit){
                    System.out.println("I do apologize but it seems your account is insufficient, please refill");
                    System.out.println("Cash Balance: Rp." + user.cash);
                }
                else{
                    deposit = amount;
                    int index = getIndex(accounts, user.username);
                    user.cash += deposit;
                    accounts.set(index, user);
                    System.out.println("Thank you");
                }
            }
            else{
                System.out.println("answer is just between 1 and 2");
            }
        }
        else if(answer == 2){
        }
        else{
            System.out.println("answer is just between 1 and 2");
        }
        Member newMember = new Member(username, password, deposit);
        members.add(newMember);
        return newMember;
    }
    static boolean checkSlot(ArrayList<Branch> branches, Branch selectedBranch, int slotNumber){
        boolean check = false;
        Branch branch = new Branch();
        int index = -1;
        for (int i = 0; i < branches.size(); i++) {
            branch = branches.get(i);
            if(branch.equals(selectedBranch)){
                index = i;
            }
        }
        branch = branches.get(index);
        ParkingSlot[] slots = branch.slots;
        ParkingSlot slot = new ParkingSlot();
        for (int i = 0; i < slots.length; i++) {
            slot = slots[i];
            if(slot.availability == true){
                check = true;
            };
        }
        return check;
    }
    static void amendParkingSpace(ArrayList<Branch> branches, Branch selectedBranch, int slotNumber, LocalDateTime now, String plateNo, int mode){
        if(mode == 0){//changing to occupied
            Branch toBeEdited = selectedBranch;
            int index = -1;
            toBeEdited.slots[slotNumber].availability = false;
            toBeEdited.slots[slotNumber].entranceTime = now;
            toBeEdited.slots[slotNumber].plateNo = plateNo;
            Branch x = new Branch();
            for (int i = 0; i < branches.size(); i++) {
                x = branches.get(i);
                if(x.equals(selectedBranch)){
                    index = i;
                }
            }
            branches.set(index, toBeEdited);
        }
        else{//changing to available
            
        }
    }
    static void displayParkingSpace(ArrayList<Branch> branches, Branch selectedBranch){
        Branch branch = new Branch();
        int index = -1;
        for (int i = 0; i < branches.size(); i++) {
            branch = branches.get(i);
            if(branch.equals(selectedBranch)){
                index = i;
            }
        }
        branch = branches.get(index);
        ParkingSlot[] slots = branch.slots;
        System.out.println("O = AVAILABLE");
        System.out.println("X = UNAVAILABLE");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < slots.length; i++) {
            if(slots[i].availability){
                System.out.print((i+1) + "[O] ");
            }
            else{
                System.out.print((i+1) + "[X] ");
            }
            if(i%5 == 0 && i != 0){
                System.out.println("");
            }
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("");
    }
    
    static void displaySimilars(ArrayList<String> similars){
        System.out.println("did you mean?");
        for (int i = 0; i < similars.size(); i++) {
            String name = similars.get(i);
            System.out.println("Branch " + name + "-"+ (i+1));
        }
    }
    
    static boolean similarExist(ArrayList<Branch> branches, String branch){
        boolean check = false;
        branch = branch.toLowerCase();
        System.out.println(branch);
        char firstLetterBranch = branch.charAt(0);
        System.out.println(firstLetterBranch);
        Branch x = new Branch();
        for (int i = 0; i < branches.size(); i++) {
            x = branches.get(i);
            String name = x.name;
            char firstLetter = name.charAt(0);
            firstLetter = Character.toLowerCase(firstLetter);
            if(firstLetter == firstLetterBranch){
                check = true;
            }
        }
        return check;
    }
    static ArrayList<String> similarPerhaps(ArrayList<Branch> branches, String branch){
        branch = branch.toLowerCase();
        char firstLetterBranch = branch.charAt(0);
        
        ArrayList<String> similars = new ArrayList<String>();
        
        Branch x = new Branch();
        for (int i = 0; i < branches.size(); i++) {
            x = branches.get(i);
            String name = x.name;
            char firstLetter = name.charAt(0);
            firstLetter = Character.toLowerCase(firstLetter);
            if(firstLetter == firstLetterBranch){
                similars.add(name);
            }
        }
        return similars;
    }
    static Branch getBranch(ArrayList<Branch> branches, String branch){
        Branch x = new Branch();
        int index = -1;
        for (int i = 0; i < branches.size(); i++) {
            x = branches.get(i);
            if(x.name.equals(branch)){
                index = i;
            }
        }
        x = branches.get(index);
        return x;
    }
    static boolean checkBranch(ArrayList<Branch> branches, String branch){
        boolean exist = false;
        Branch x = new Branch();
        for (int i = 0; i < branches.size(); i++) {
            x = branches.get(i);
            if(x.name.equals(branch)){
                exist = true;
            }
        }
        return exist;
    }
    static void displayBranches(ArrayList<Branch> branches){
        Branch newBranch = new Branch();
        System.out.println("");
        for (int i = 0; i < branches.size(); i++) {
            newBranch = branches.get(i);
            System.out.println(newBranch.name);
        }
        System.out.println("");
    }
    static Member loginMenu(ArrayList<Member> members){
        Scanner inputn = new Scanner(System.in);
        Scanner inputl = new Scanner(System.in);
        
        Member toBeReturned = new Member();
        int index = 0;
        System.out.println("......................................logging in");
        System.out.println("Please input your username");
        String username = inputl.nextLine();
        while(!usernameCheck(members, username)){
            System.out.println("Sorry username does not exits");
            username = inputl.nextLine();
        }
        System.out.println("Please input your password");
        String password = inputl.nextLine();
        while(!passwordCheck(members, username, password)){
            System.out.println("sorry, password is incorrect");
            System.out.println("Please input your password again");
            password = inputl.nextLine();
        }
        Member x = new Member();
        for (int i = 0; i < members.size(); i++) {
            x = members.get(i);
            if(x.username.equals(username)){
                index = i;
            }
        }
        toBeReturned = members.get(index);
        return toBeReturned;
    }
    static boolean validPassword(String password){
        boolean check = false;
        final int minLength = 8;
        int uppercase = 0;
        int lowercase = 0;
        int specialCharacter = 0;
        int digit = 0;
        
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if(Character.isUpperCase(c)){
                uppercase++;
            }
            else if(Character.isLowerCase(c)){
                lowercase++;
            }
            else if(Character.isDigit(c)){
                digit++;
            }
            if(c >= 33 && c <= 46 || c == 64){
                specialCharacter++;
            }
        }
        if(uppercase < 1){
            System.out.println("Lacks Upper Case letter/s");
        }
        if(lowercase < 1){
            System.out.println("Lacks Lower Case letter/s");
        }
        if(specialCharacter < 1){
            System.out.println("Lacks Special character/s");
        }
        if(digit < 1){
            System.out.println("Lacks number character/s");
        }
        if(password.length() < 8){
            System.out.println("Minimum of 8 characters");
        }
        if(uppercase > 0){
            if(lowercase > 0){
                if(digit > 0){
                    if(specialCharacter > 0){
                        if(password.length() >= 8){
                            check = true;
                        }
                    }
                }
            }
        }
        if(!check){
            System.out.println("please try again");
        }
        return check;
    }
    static boolean usernameCheck(ArrayList<Member> members, String username){
        boolean check = false;
        Member x = new Member();
        for (int i = 0; i < members.size(); i++) {
            x = members.get(i);
            if(username.equals(x.username)){
                check = true;
            }
        }
        return check;
    }
    static boolean passwordCheck(ArrayList<Member> members, String username, String password){
        boolean check = false;
        Member x = new Member();
        for (int i = 0; i < members.size(); i++) {
            x = members.get(i);
            if(x.username.equals(username)){
                if(x.password.equals(password)){
                    check = true;
                }
            }
        }
        return check;
    }
    static String ordinal(int number){
        String ordinal = "";
        if(number % 10 == 1){
            ordinal = "st";
        }
        else if(number % 10 == 2){
            ordinal = "nd";
        }
        else if(number % 10 == 3){
            ordinal = "rd";
        }
        else{
            ordinal = "th";
        }
        return ordinal;
    }

    static int indexSearch(ArrayList<Branch> branches, Branch branch){
        int index = -1;
        Branch x = new Branch();
        for (int i = 0; i < branches.size(); i++) {
            x = branches.get(i);
            if(x.equals(branch)){
                index = i;
            }
        }
        return index;
    }
}
