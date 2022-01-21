

import java.util.*;

class User{
    String userName;
    String userPassword;
    int phNo;
    String status;
    int balance;
    int totalBookings;
    int totalTicketsBooked;

    User(String userName,String userPassword,int phNo,String status,int balance,int totalBookings,int totalTicketsBooked){
        this.userName=userName;
        this.userPassword=userPassword;
        this.phNo=phNo;
        this.status=status;
        this.balance=balance;
        this.totalBookings=totalBookings;
        this.totalTicketsBooked=totalTicketsBooked;
    }
}
class ApprovalUser{
    String approvalUserName;
    String approvalUserPassword;
    int phNo;
    String status;
    int balance;

    ApprovalUser(String approvalUserName,String approvalUserPassword,int phNo,String status,int balance){
        this.approvalUserName=approvalUserName;
        this.approvalUserPassword=approvalUserPassword;
        this.phNo=phNo;
        this.status=status;
        this.balance=balance;
    }
}
class Train{
    String TrainName;
    String startPoint;
    String endPoint;
    int noOfStation;
    int noOfSeat;
    List<String> station;
    int noOfSeatAllotted;
    int[][] seatAllotted;
    List<Integer> cost;

    Train(String TrainName,String startPoint,String endPoint,int noOfStation,int noOfSeat,List<String> station,int noOfSeatAllotted,int[][] seatAllotted,List<Integer> cost){
        this.TrainName=TrainName;
        this.startPoint=startPoint;
        this.endPoint=endPoint;
        this.noOfStation=noOfStation;
        this.noOfSeat=noOfSeat;
        this.station=station;
        this.noOfSeatAllotted=noOfSeatAllotted;
        this.seatAllotted=seatAllotted;
        this.cost=cost;
    }
}

class RailwayReservation {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Train> trains = new ArrayList<>();
    static ArrayList<User> user = new ArrayList<>();
    static ArrayList<ApprovalUser> appUser = new ArrayList<>();
    static String adminName="a";
    static int adminPassword=1;
    static ArrayList<Integer> total = new ArrayList<Integer>();
    static int temp;
    static int tot=0;
    static ArrayList<Integer> refund = new ArrayList<Integer>();
    static int totCost=0;
    static int allottedTicket;
    static int trainCount=0;


    static void approveUser(){
        for(int i=0;i<appUser.size();i++){
            System.out.println("User Name : "+appUser.get(i).approvalUserName);
            System.out.println("1.Approve or 2.Reject");
            int approval = sc.nextInt();
            if(approval==1){
                appUser.get(i).status="y";
                User us2 = new User(appUser.get(i).approvalUserName,appUser.get(i).approvalUserPassword,appUser.get(i).phNo,"y",appUser.get(i).balance,0,0);
                total.add(0);
                user.add(us2);
                System.out.println("User Approved Successfully");
            }else if(approval==2){
                appUser.remove(i);
                System.out.println("User Rejected Successfully");
            }
            else System.out.println("Invalid Input");
        }
        System.out.println("No more Pending Approvals");
        System.out.println();
    }
    static void addTrain() {
        System.out.println("Enter Train Name : ");
        sc.nextLine();String tName = sc.nextLine();
        System.out.println("Enter No Of Station : ");
        int noOfStation = sc.nextInt();
        System.out.println("Enter Train Boarding Station Name : ");
        sc.nextLine();String bName = sc.nextLine();
        System.out.println("Enter Train Destination Station Name : ");
        String dName = sc.nextLine();
        System.out.println("Enter No Of Seat Available : ");
        int noOfSeat = sc.nextInt();
        int[][] seatAllotted = new int[noOfSeat][noOfStation];
        List<String> st = new ArrayList<>();
        List<Integer> co = new ArrayList<>();
        System.out.println("Enter Stations Names : ");
        for (int i = 0; i < noOfStation; i++) {
            sc.nextLine();
            System.out.println("Enter Station "+(i+1)+" Name: " );
            String sn=sc.nextLine();
            st.add(sn);
            System.out.println("Enter Station "+(i+1)+" Cost: " );
            int c=sc.nextInt();
            co.add(c);
        }
        Train newTrain = new Train(tName,bName,dName,noOfStation,noOfSeat,st,0,seatAllotted,co);
        trains.add(newTrain);
        System.out.println("Train Added Successfully");
        System.out.println();
    }
    static void declareSeat() {
        System.out.println("Train Name");
        for(int i = 0;i<trains.size();i++){
            System.out.println(i+1+"."+trains.get(i).TrainName);
        }
        System.out.println("Enter Choice : ");
        int n = sc.nextInt();
        if(n<=trains.size()){
//            System.out.println("No of Seat Allotted : "+trains.get(n-1).noOfSeatAllotted);
            System.out.println("Enter No Of Available Seats");
            int noOfS = sc.nextInt();
            trains.get(n - 1).noOfSeat = noOfS;
            trains.get(n-1).seatAllotted = new int[noOfS][trains.get(n-1).noOfStation];
        }else System.out.println("Invalid Input!");
        System.out.println("Availability of Seats Updated successfully");
        System.out.println();
    }
    static void listUser(){
        System.out.println("-----User List-----");
        System.out.println("SNo  User_Name              User_Ph.No       Total_Bookings       Total_Tickets_Booked        Balance");
        for (int i = 0; i < user.size();i++) {
            System.out.println(String.format("%-4s %-22s %-16s %-20s %-26s %-12s", (i + 1), user.get(i).userName, user.get(i).phNo, user.get(i).totalBookings,user.get(i).totalTicketsBooked, user.get(i).balance));
        }
        System.out.println();
    }
    static void admin(){
        System.out.println("Admin Login Page");
        System.out.println("Enter user name: ");
        sc.nextLine();
        String name1 = sc.nextLine();
        System.out.println("Enter password: ");
        int password1 = sc.nextInt();
        if (name1.equals(adminName) && password1 == adminPassword) {
            System.out.println();
            System.out.println("Admin Logged in Successfully");
            int n1 = 0;
            do {
                System.out.println("1.Approve User");
                System.out.println("2.Add Trains-Routes-Stations");
                System.out.println("3.View User List");
                System.out.println("4.View Train List");
                System.out.println("5.Declare Seats Availability");
                System.out.println("0.Exit");
                System.out.println("Enter Choice : ");
                n1 = sc.nextInt();
                switch (n1) {
                    case 0:
                        System.out.println("Admin Logged Out Successfully");
                        break;
                    case 1:
                        approveUser();
                        break;
                    case 2:
                        addTrain();
                        break;
                    case 3:
                        listUser();
                        break;
                    case 4:
                        viewTrains();
                        break;
                    case 5:
                        declareSeat();
                        break;
                    default:
                        System.out.println("Invalid Input");
                }

            } while (n1 != 0);
        }
        else {
            System.out.println("Login Id or Password Incorrect!!!");
            System.out.println("!-----Please Retry Again-----!");
            System.out.println();
        }
    }

    public static boolean checkExistingInUser(String mailId, int pno){
        for(int i=0;i<user.size();i++){
            if(user.get(i).userName.equals(mailId) && user.get(i).phNo==pno){
                return false;
            }
        }
        return true;
    }
    static void newUser() {
        System.out.println("Enter Your Name : ");
        sc.nextLine();
        String uName = sc.nextLine();
        System.out.println("Enter Your Phone Number : ");
        int pno = sc.nextInt();
        boolean flag =checkExistingInUser(uName,pno);
        if(flag) {
            System.out.println("Enter Your Password : ");
            sc.nextLine();
            String uPassword = sc.nextLine();
            System.out.println("Enter your Balance : ");
            int bal=sc.nextInt();
            ApprovalUser aU = new ApprovalUser(uName, uPassword,pno,"n",bal);
            appUser.add(aU);
            System.out.println("Please Wait for Admin's Approval!");
        }else System.out.println("The User Name or Phone Number is Already Exists \nPlease Try another Name or Number!");
        System.out.println();
    }
    static void viewTrains() {
        System.out.println("-----Train List-----");
        System.out.println("SNo  Train_Name          Boarding_Point      Destination_Point       Total_No._of_Seats      Seats_Allotted");
        for (int i = 0; i < trains.size();i++) {
            System.out.println(String.format("%-4s %-19s %-19s %-23s %-23s %-18s",(i+1),trains.get(i).TrainName,trains.get(i).startPoint,trains.get(i).endPoint,trains.get(i).noOfSeat,trains.get(i).noOfSeatAllotted));
        }
        System.out.println();
    }
    static void printSeat(int t){
        for (int k = 0; k < trains.get(t).noOfSeat; k++) {
            for (int i = 0; i < trains.get(t).noOfStation; i++) {
                System.out.print(trains.get(t).seatAllotted[k][i]+" ");
            }
            System.out.println();
        }
    }
    static void bookTicket(){
        System.out.println("----- Ticket Booking -----");
        if(user.get(temp).balance>0) {
            System.out.println("Enter Train Name : ");
            sc.nextLine();
            String tName = sc.nextLine();
            for (int l = 0; l < trains.size(); l++) {
                if (trains.get(l).TrainName.equals(tName)) {
                    trainCount = l;
                    System.out.println("Enter No Of Passengers : ");
                    int noOfPassengers = sc.nextInt();
                    for (int s = 0; s < trains.get(l).station.size(); s++) {
                        System.out.println(trains.get(l).station.get(s));
                    }
                    System.out.println("Enter Boarding Station No : ");
                    int st = sc.nextInt();
                    System.out.println("Enter Destination Station No : ");
                    int en = sc.nextInt();
                    if (en > st) {
                        int allotted = trains.get(l).noOfSeatAllotted;
                        user.get(temp).totalBookings++;
                        for (int k = 0; k < noOfPassengers; k++) {
                            int seat = 0;
                            for (int i = 0; i < trains.get(l).noOfSeat; i++) {
                                for (int i1 = st; i1 < en; i1++) {
                                    if (trains.get(l).seatAllotted[i][i1] != 0) {
                                        break;
                                    } else seat++;
                                }
                                if (seat == en - st) {
                                    tot = 0;
                                    for (int g = st - 1; g <= en - 1; g++) {
                                        tot += trains.get(l).cost.get(g);
                                    }
                                    refund.add(tot);
                                    totCost = tot;
                                    if (user.get(temp).balance > totCost) {
                                        user.get(temp).balance = user.get(temp).balance - totCost;
                                    } else {
                                        System.out.println("Your Wallet has Insufficient Fund!\nSo you cannot Book this Ticket!!! ");
                                        break;
                                    }
//                                user.get(temp).balance = user.get(temp).balance - totCost;
                                    user.get(temp).totalTicketsBooked++;
                                    System.out.println("Your Seat No is : " + (i + 1) + "\nYour Ticket No : " + ++allotted);
                                    allottedTicket = allotted;
                                    for (int i1 = st - 1; i1 < en; i1++) {
                                        trains.get(l).seatAllotted[i][i1] = allotted;
                                    }
                                    break;
                                }
                            }
                        }
                        printSeat(l);
                        trains.get(l).noOfSeatAllotted = allotted;
                        System.out.println("Ticket Booking Done Successfully");
                        System.out.println();
                        break;
                    } else System.out.println("Please Enter your Boarding & Destination Stations Correctly!");
                }else System.out.println("Please Enter Train Name Correctly");
            }
        }else {
            user.get(temp).balance=0;
            System.out.println("You cannot Book Tickets \nYour Balance is very Low");
        }
        System.out.println();
    }
    static void ticketCancel() {
        int c=0;
        System.out.println("Enter Train Name : ");
        sc.nextLine();String tName = sc.nextLine();
        for (int i = 0; i < trains.size(); i++) {
            if(trains.get(i).TrainName.equals(tName)){
                c++;
                System.out.println("Enter Ticket No : ");
                int ticNo = sc.nextInt();
                System.out.println("Enter Seat No : ");
                int seatNo = sc.nextInt();
                for (int j = 0; j < trains.get(i).noOfStation; j++) {
                    if (trains.get(i).seatAllotted[seatNo - 1][j] == ticNo) {
                        trains.get(i).seatAllotted[seatNo - 1][j] = 0;
                    }
                }
                user.get(temp).totalTicketsBooked--;
                trains.get(trainCount).noOfSeatAllotted--;
                int r = refund.get(allottedTicket - 1);
                user.get(temp).balance = user.get(temp).balance + (r / 2);
                printSeat(trainCount);
                System.out.println("Refund Amount : " + (r / 2));
                System.out.println("Ticket Canceled Successfully!");
                break;
            }
        }
        if(c==0) System.out.println("Please Enter Train Name Correctly");
        System.out.println();
    }
    static void user(){
        int c=0,d=0,e=0,p=0;
        System.out.println("User Login Page");
        System.out.println("Enter Your Name : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Enter User Password : ");
        String usPassword = sc.nextLine();
            for (int j = 0; j < user.size(); j++) {
                if (user.get(j).userName.equals(name) && user.get(j).userPassword.equals(usPassword)) {
                    c++;
                    e++;
                    temp=j;
                    System.out.println("User Logged In Successfully");
                    System.out.println();
                }
            }
                for(int i=0;i<appUser.size();i++){
                    if(appUser.get(i).approvalUserName.equals(name) && appUser.get(i).approvalUserPassword.equals(usPassword)){
                        d++;p=i;
                    }
                }
                if(d==0 && e==0) {
                    System.out.println("Your Account is Rejected by the Admin \nPlease Enter the Details Correctly!");
                    System.out.println();
                }else if(d>0 && appUser.get(p).status.equals("n")) {
                    System.out.println("Your Account is not Approved by the Admin \nPlease wait for your Approval!");
                    System.out.println();
                }
            if (c > 0) {
                int n3 = 0;
                do {
                    System.out.println("1.View Trains and Availability");
                    System.out.println("2.Book Tickets");
                    System.out.println("3.Ticket Cancellation");
                    System.out.println("4.Check Balance");
                    System.out.println("0.Exit");
                    System.out.println("Enter Choice : ");
                    n3 = sc.nextInt();
                    switch (n3) {
                        case 0:
                            System.out.println("User Logged Out Successfully");
                            break;
                        case 1:
                            viewTrains();
                            break;
                        case 2:
                            viewTrains();
                            bookTicket();
                            break;
                        case 3:
                            ticketCancel();
                            break;
                        case 4:
                            int x=0;
                            System.out.println("Your Balance is : "+user.get(temp).balance);
                            System.out.println();
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                } while (n3 != 0);
            }
        else {
            System.out.println("Login Id or Password Incorrect!!!");
            System.out.println("!-----Please Retry Again-----!");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        User u1 = new User("u1","1",1234567890,"y",10000,0,0);
        user.add(u1);
        int[][] seat=new int[5][8];
        ArrayList<String> s = new ArrayList<String>();
        s.add("1.Kovai");s.add("2.Tiruppur");s.add("3.Erode");s.add("4.Salem");s.add("5.Jolarpettai");s.add("6.Katpadi");s.add("7.Arakkonam");s.add("8.Chennai");
        ArrayList<Integer> cost = new ArrayList<Integer>();
        cost.add(0);cost.add(30);cost.add(30);cost.add(35);cost.add(85);cost.add(50);cost.add(35);cost.add(50);
        Train t1 = new Train("K","Kovai","Chennai",8,5,s,0,seat,cost);
        trains.add(t1);
        int n = 0;
        do {
            System.out.println("1.Admin Login");
            System.out.println("2.New User");
            System.out.println("3.Existing User");
            System.out.println("0.Exit");
            System.out.println("Enter Choice : ");
            n = sc.nextInt();
            switch (n) {
                case 0:
                    System.out.println("Thanks for Using!");
                    break;
                case 1:
                    admin();
                    break;
                case 2:
                    newUser();
                    break;
                case 3:
                    user();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (n != 0);
    }
}
