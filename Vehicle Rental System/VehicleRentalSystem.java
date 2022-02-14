import java.time.LocalDate;
import java.time.Period;
import java.util.*;

class user {
    String userName,userPassword;
    int userID,balance;
    ArrayList<Borrow> his;

    user(String userName,String userPassword,int balance,int userID,ArrayList<Borrow> his){
        this.userName=userName;
        this.userPassword=userPassword;
        this.balance=balance;
        this.userID=userID;
        this.his=his;
    }
}
class vehicle {
    String vehicleName,vehicleID,vehicleType;
    int vehicleModel;
    int vehicleRentPrice,vehicleTotalKM;
    int vehicleTripKM,vehiclePrice;


    vehicle(String vehicleType,String vehicleID,String vehicleName,int vehicleModel,int vehicleRentPrice,int vehiclePrice,int vehicleTotalKM,int vehicleTripKM){
        this.vehicleType=vehicleType;
        this.vehicleID=vehicleID;
        this.vehicleName=vehicleName;
        this.vehicleModel=vehicleModel;
        this.vehicleRentPrice=vehicleRentPrice;
        this.vehiclePrice=vehiclePrice;
        this.vehicleTotalKM=vehicleTotalKM;
        this.vehicleTripKM=vehicleTripKM;
    }
}
class Borrow {
    String vehicleType,vehicleName,userName,vehicleID;
    int userID,vehicleRentPrice;
    LocalDate borrowDate,returnDate;

    Borrow(int userID,String userName,String vehicleType,String vehicleID,String vehicleName,int vehicleRentPrice,LocalDate borrowDate,LocalDate returnDate){
        this.userName=userName;
        this.userID=userID;
        this.vehicleType=vehicleType;
        this.vehicleID=vehicleID;
        this.vehicleName=vehicleName;
        this.vehicleRentPrice=vehicleRentPrice;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
    }
}

public class VehicleRentalSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<user> userList = new ArrayList<>();
    static ArrayList<vehicle> vehicleList = new ArrayList<>();
    static ArrayList<Borrow> borrowList = new ArrayList<>();
    static String adminEmail = "admin";
    static int adminPassword = 1;
    static int historyI = 0;
    static int userTaken = 0;
    static int userI = 0;

    // User Function-------------------------------------------------------
    
    static int amount(int b) {
        while (b < 30000) {
            System.out.println("Your Initial Balance Must be Equal to or Greater Than 30000.\nPlease Make your Initial Payment According to it.");
            b = sc.nextInt();
        }
        return b;
    }
    private static void newUser() {
        System.out.println("Enter User Name : ");
        sc.nextLine();
        String uName = sc.nextLine();
        System.out.println("Enter Your Password : ");
        String uPassword = sc.nextLine();
        System.out.println("Enter your Balance : ");
        int bal = sc.nextInt();
        int ba = amount(bal);
        int id = userList.size() + 1;
        ArrayList<Borrow> his = new ArrayList<>();
        user u = new user(uName, uPassword, ba, id, his);
        userList.add(u);
        System.out.println("New User Added Successfully");
        System.out.println("Your User Id is " + id + " Please Remember for Login");
        System.out.println();
    }

    static void borrowVehicle() {
        viewVehicles();
        int c = 0;
        if (userTaken <= 2) {
            if (userList.get(userI).balance > 10000) {
                if(userList.get(userI).balance > 3000) {
                    System.out.print("Enter the Vehicle ID to place your order :");
                    sc.nextLine();
                    String n = sc.nextLine();
                    boolean flag = true;
                    for (int i = 0; i < vehicleList.size(); i++) {
                        if (flag) {
                            if (vehicleList.get(i).vehicleID.equals(n)) {
                                System.out.println("Enter Date of Booking(yyyy-mm-dd) : ");
                                LocalDate b = LocalDate.parse(sc.nextLine());
                                LocalDate r = b;
                                Borrow a = new Borrow(userList.get(userI).userID, userList.get(userI).userName, vehicleList.get(i).vehicleType, vehicleList.get(i).vehicleID, vehicleList.get(i).vehicleName, vehicleList.get(i).vehicleRentPrice, b, r);
                                borrowList.add(a);
                                c++;
                                userList.get(userI).his.add((borrowList.get(borrowList.size() - 1)));
                                userTaken++;
                                System.out.println("Vehicle Booked successfully!!!\nAccording to the Kilometers you will be Charged...\nNow the Kilometers runned by the Vehicle is " + vehicleList.get(i).vehicleTotalKM);
                            }
                        }
                    }
                    if (c == 0) System.out.println("Vehicles not available...");
                }else System.out.println("Your Available Deposit is too low.....");
            } else System.out.println("Your Available Deposit is too low to Borrow a Car.....\n You can Borrow a Bike...!");
        } else System.out.println("You only can Book 2 Vehicles at a time!!!");
        System.out.println();
    }
    static void vehicleReturn() {
        System.out.println("Enter 1 if Vehicle is Damaged : ");
        int damaged=sc.nextInt();
        if(damaged!=1) {
            System.out.print("Enter the Vehicle ID:");
            sc.nextLine();
            String temp_isbn = sc.nextLine();
            for (int i = 0; i < borrowList.size(); i++) {
                if (userList.get(userI).userID == borrowList.get(i).userID && borrowList.get(i).vehicleID.equals(temp_isbn)) {
                    LocalDate day = LocalDate.now();
                    Period days = Period.between(userList.get(userI).his.get(historyI).borrowDate, day);
                    System.out.println("Enter the Kilometers : ");
                    int tripKM = sc.nextInt();
                    if (userList.get(userI).his.get(historyI).borrowDate == day) {
                        double cost = costfunction(tripKM);
                        System.out.println("Your total trip cost is : " + cost + "0");
                        finePayment(cost);
                        borrowList.remove(i);
                        System.out.println("vehicle Returned Successfully....:)");
                        vehicleList.get(i).vehicleTripKM+=tripKM;
                    } else {
                        double cost = costfunction(tripKM) + (1000 * days.getDays());
                        System.out.println("Your total trip cost is : " + cost + "0");
                        finePayment(cost);
                        System.out.println("vehicle Returned Successfully....:)");
                        vehicleList.get(i).vehicleTripKM+=tripKM;
                        borrowList.remove(i);
                    }
                } else {
                    System.out.println("Entered ID is not in your Borrowed History please Try again..");
                }
            }
            System.out.println();
        }else{
            vehicleDamage();
        }
    }
    static double costfunction(int tripKM){
        double cost=0;
        cost = (tripKM * userList.get(userI).his.get(historyI).vehicleRentPrice);
        if(tripKM>500) {
            cost = (tripKM * userList.get(userI).his.get(historyI).vehicleRentPrice);
            cost = cost + (cost * 0.15);
        }
        return cost;
    }
    static void finePayment(double n){
        System.out.println("Pay By Cash--->(y/n)");
        sc.nextLine();
        String s=sc.nextLine();
        if(s.equals("y")) {
            System.out.println("Payment Completed!");
        }
        else if(s.equals("n")){
            System.out.println("Your Fine Amount will be Reduced in your Security Deposit!!!");
            userList.get(userI).balance -= n;
        }
        else System.out.println("Invalid Number");
    }
    static void vehicleDamage() {
        System.out.print("Enter the vehicle ID :");
        sc.nextLine();
        String temp_isbn= sc.nextLine();
        for(int i=0;i<borrowList.size();i++){
            if(userList.get(userI).userID==borrowList.get(i).userID && borrowList.get(i).vehicleID.equals(temp_isbn)){
                LocalDate day=LocalDate.now();
                Period days= Period.between(userList.get(userI).his.get(historyI).borrowDate,day);
                System.out.println("Enter the Kilometers : ");
                int tripKM=sc.nextInt();
                System.out.println("Damage label : \n1.Low\n2.Medium\n3.High");
                int damage =sc.nextInt();
                double temp_amt=0;
                switch (damage){
                    case 1:
                        temp_amt=temp_amt+costfunction(tripKM)+(costfunction(tripKM)*0.20);
                        System.out.println("You have Fined 20% of the Trip Amount of Rs."+temp_amt+"0 due to Lost of the vehicle...");
                        break;
                    case 2:
                        temp_amt=temp_amt+costfunction(tripKM)+(costfunction(tripKM)*0.50);
                        System.out.println("You have Fined 50% of the Trip Amount of Rs."+temp_amt+"0 due to Lost of the vehicle...");
                        break;
                    case 3:
                        temp_amt=temp_amt+costfunction(tripKM)+(costfunction(tripKM)*0.75);
                        System.out.println("You have Fined 75% of the Trip Amount of Rs."+temp_amt+"0 due to Lost of the vehicle...");
                        break;
                    default:
                        System.out.println("Invalid Number");
                        break;
                }
                finePayment(temp_amt);
                vehicleList.get(i).vehicleTripKM+=tripKM;
                borrowList.remove(i);
            }
            else{
                System.out.println("Entered ID is not in your Borrowed History Please Try again...");
            }
        }
        System.out.println();
    }
    static void borrowHistory() {
        System.out.println("------------- Your Borrow History ------------- ");
        System.out.println();
        System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-18s %-26s %-26s", "SNo", "Vehicle ID", "Vehicle Type", "Vehicle Name", "Vehicle Model", "vehicle Price", "Vehicle Rent Price", "Vehicle Total Kilometer", "Vehicle Trip Kilometer"));
        int f = 0;
        for (int i = 0; i < userList.get(userI).his.size(); i++) {
            if (userList.get(userI).userID == userList.get(userI).his.get(i).userID) {
                System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-18s %-26s %-26s", (i + 1), vehicleList.get(i).vehicleID, vehicleList.get(i).vehicleType, vehicleList.get(i).vehicleName, vehicleList.get(i).vehicleModel, vehicleList.get(i).vehiclePrice, vehicleList.get(i).vehicleRentPrice, vehicleList.get(i).vehicleTotalKM, vehicleList.get(i).vehicleTripKM));
                f++;
            }
        }
        if (f == 0) {
            System.out.println("No Borrow History left....");
        }
        System.out.println();
    }
    static void userSearchVehicle() {
        int c = 0;
        System.out.println("Enter Vehicle Name : ");
        sc.nextLine();
        String uName = sc.nextLine();
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).vehicleName.equals(uName)) {
                c++;
                System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-18s %-26s %-26s", "SNo", "Vehicle ID", "Vehicle Type", "Vehicle Name", "Vehicle Model", "vehicle Price", "Vehicle Rent Price", "Vehicle Total Kilometer", "Vehicle Trip Kilometer"));
                System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-18s %-26s %-26s", (i + 1), vehicleList.get(i).vehicleID, vehicleList.get(i).vehicleType, vehicleList.get(i).vehicleName, vehicleList.get(i).vehicleModel, vehicleList.get(i).vehiclePrice, vehicleList.get(i).vehicleRentPrice, vehicleList.get(i).vehicleTotalKM, vehicleList.get(i).vehicleTripKM));
            }
        }
        if (c == 0) System.out.println("No Vehicle Found! or Vehicle is Under Service!");
        else System.out.println("These are the Results!");
        System.out.println();
    }

    private static void borrower(){
        System.out.println("User Login Page");
        System.out.println("Enter User ID : ");
        sc.nextLine();
        int password =sc.nextInt();
        System.out.println("Enter Password : ");
        sc.nextLine();
        String n= sc.nextLine();
        int c=0;
        for(int i=0;i<userList.size();i++) {
            if (userList.get(i).userID==password && userList.get(i).userPassword .equals(n)) {
                userTaken=0;
                userI=i;
                c++;
                System.out.println("User Logged In Successfully");
            }
        }
        if(c>0) {
            int n2=0;
            do{
                System.out.println("-----WELCOME USER-----");
                System.out.println("1.View all vehicles");
                System.out.println("2.Borrow vehicle");
                System.out.println("3.Return vehicle");
                System.out.println("4.Check Balance");
                System.out.println("5.Search vehicle");
                System.out.println("6.Borrowed History");
                System.out.println("0.Exit");
                System.out.println();
                n2=sc.nextInt();
                switch (n2){
                    case 0:
                        System.out.println("User Logged out successfully");
                        break;
                    case 1:
                        viewVehicles();
                        break;
                    case 2:
                        borrowVehicle();
                        break;
                    case 3:
                        vehicleReturn();
                        break;
                    case 4:
                        System.out.println("Your Balance is : "+userList.get(userI).balance+".00");
                        System.out.println();
                        break;
                    case 5:
                        userSearchVehicle();
                        break;
                    case 6:
                        borrowHistory();
                        break;
                    default:
                        System.out.println("Invalid Number");
                        break;
                }
            }while(n2!=0);
        }
        else System.out.println("Please Check ID or Password!!!");
    }

    // Admin Function---------------------------------------

    static void addVehicle() {
        System.out.println("1.Car \t 2.Bike \nEnter Vehicle Type : ");
        int t = sc.nextInt();
        String id = "", type = "";
        if (t == 1) {
            id += "C";
            type = "Car";
        } else if (t == 2) {
            id += "B";
            type = "Bile";
        } else System.out.println("Invalid Number");
        System.out.println("Enter " + type + " Name : ");
        sc.nextLine();
        String n = sc.nextLine();
        System.out.println("Enter " + type + " Model : ");
        int pno = sc.nextInt();
        System.out.println("Enter " + type + " Price : ");
        int tp = sc.nextInt();
        System.out.println("Enter " + type + "'s Rent Price(1 km) : ");
        int rp = sc.nextInt();
        System.out.println("Enter " + type + "'s Total Kilometer : ");
        int tkm = sc.nextInt();
        System.out.println("Enter " + type + "'s Trip Kilometer : ");
        int km = sc.nextInt();
        vehicle b = new vehicle(type, (id + (vehicleList.size()+1)), n, pno, rp, tp, tkm, km);
        vehicleList.add(b);
        System.out.println("New "+type+" Added Successfully");
        System.out.println();
    }
    static void removeVehicle() {
        int c = 0;
        System.out.println("1.Car \t 2.Bike \nEnter Vehicle Type : ");
        int t = sc.nextInt();
        String type = "";
        if (t == 1) type = "Car";
        else if (t == 2) type = "Bile";
        System.out.println("Enter " + type + " ID : ");
        String pno = sc.nextLine();
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).vehicleID.equals(pno)) {
                vehicleList.remove(i);
                c++;
            }
        }
        if (c == 0) System.out.println("The " + type + " does not Exists!!!\nEnter " + type + " & ID Correctly!!!");
        else System.out.println(type + " Removed Successfully");
        System.out.println();
    }
    //--------------------------- Vehicle Modify Function-----------------------------------------------
    static void mod_tit(int i) {
        System.out.print("Enter New Vehicle Name :");
        String vehicle_tit = sc.next();
        vehicleList.get(i).vehicleName = vehicle_tit;
        System.out.println("Vehicle Name Changed Successfully....");
        System.out.println();
    }
    static void mod_auth(int i) {
        System.out.print("Enter New Model :");
        int vehicle_auth = sc.nextInt();
        vehicleList.get(i).vehicleModel = vehicle_auth;
        System.out.println("Vehicle Model Changed Successfully....");
        System.out.println();
    }
    static void mod_type(int i) {
        System.out.print("Enter New Rent Price :");
        int vehicle_type = sc.nextInt();
        vehicleList.get(i).vehicleRentPrice = vehicle_type;
        System.out.println("vehicle Type Changed Successfully....");
        System.out.println();
    }
    static void mod_isbn(int i) {
        System.out.print("Enter New ID number :");
        String vehicle_isbn = sc.nextLine();
        vehicleList.get(i).vehicleID = vehicle_isbn;
        System.out.println("vehicle ISBN Changed Successfully....");
        System.out.println();
    }
    static void mod_price(int i) {
        System.out.print("Enter New vehicle Price :");
        int vehicle_price = sc.nextInt();
        vehicleList.get(i).vehiclePrice = vehicle_price;
        System.out.println("vehicle Title Changed Successfully....");
        System.out.println();
    }
    static void mod_count(int i) {
        System.out.print("Enter New Kilometer :");
        int vehicle_count = sc.nextInt();
        vehicleList.get(i).vehicleTotalKM = vehicle_count;
        System.out.println("vehicle Title Changed Successfully....");
        System.out.println();
    }
    static void modifyVehicle() {
        System.out.print("Enter the Vehicle ID to modify : ");
        int mod_count = 0;
        String temp_isbn = sc.nextLine();
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).vehicleID.equals(temp_isbn)) {
                mod_count++;
                int n6 = 0;
                do {
                    System.out.println("1.Modify Vehicle Name");
                    System.out.println("2.Modify Vehicle Model");
                    System.out.println("3.Modify Vehicle Rent Price");
                    System.out.println("4.Modify Vehicle ID");
                    System.out.println("5.Modify Vehicle Price");
                    System.out.println("6.Modify Vehicle Total Kilometer");
                    System.out.println("0.Exit");
                    n6 = sc.nextInt();
                    switch (n6) {
                        case 0:
                            System.out.println("All Changer Modified Successfully!");
                            break;
                        case 1:
                            mod_tit(i);
                            break;
                        case 2:
                            mod_auth(i);
                            break;
                        case 3:
                            mod_type(i);
                            break;
                        case 4:
                            mod_isbn(i);
                            break;
                        case 5:
                            mod_price(i);
                            break;
                        case 6:
                            mod_count(i);
                            break;
                        default:
                            System.out.println("Invalid Number");
                            break;
                    }
                } while (n6 != 0);
            }
        }
        if (mod_count == 0) {
            System.out.println("Incorrect ISBN or vehicle not Found....");
        }
    }
//    ----------------------------------------------------------------------------------------------------------------------
    static void viewVehicles() {
        System.out.println("-----Vehicle List-----");
        if (vehicleList.size() > 0) {
            System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-20s %-26s %-26s", "SNo", "Vehicle ID", "Vehicle Type", "Vehicle Name", "Vehicle Model", "vehicle Price", "Vehicle Rent Price", "Vehicle Total Kilometer", "Vehicle Trip Kilometer"));
            for (int i = 0; i < vehicleList.size(); i++) {
                int n = i;
                if (vehicleList.get(i).vehicleType.equals("Car") && vehicleList.get(i).vehicleTripKM < 3000) {
                    System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-20s %-26s %-26s", (n + 1), vehicleList.get(i).vehicleID, vehicleList.get(i).vehicleType, vehicleList.get(i).vehicleName, vehicleList.get(i).vehicleModel, vehicleList.get(i).vehiclePrice, vehicleList.get(i).vehicleRentPrice, vehicleList.get(i).vehicleTotalKM, vehicleList.get(i).vehicleTripKM));
                } else if (vehicleList.get(i).vehicleType.equals("Bike") && vehicleList.get(i).vehicleTripKM < 1500) {
                    System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-20s %-26s %-26s", (n + 1), vehicleList.get(i).vehicleID, vehicleList.get(i).vehicleType, vehicleList.get(i).vehicleName, vehicleList.get(i).vehicleModel, vehicleList.get(i).vehiclePrice, vehicleList.get(i).vehicleRentPrice, vehicleList.get(i).vehicleTotalKM, vehicleList.get(i).vehicleTripKM));
                } else n--;
            }
        } else System.out.println("No vehicles Available!");
        System.out.println();
    }
    static void viewUsers() {
        System.out.println("-----User List-----");
        if (userList.size() > 0) {
            System.out.println(String.format("%-4s %-8s %-25s %-12s", "SNo", "User_ID", "User_Name", "Balance_Amount"));
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(String.format("%-4s %-8s %-25s %-12s", (i + 1), userList.get(i).userID, userList.get(i).userName, userList.get(i).balance));
            }
        } else System.out.println("No vehicles Available!");
        System.out.println();
    }
    static void adminSearchVehicle() {
        int c = 0;
        System.out.println("1.Car \t 2.Bike \nEnter Vehicle Type : ");
        int t = sc.nextInt();
        String type = "";
        if (t == 1) type = "Car";
        else if (t == 2) type = "Bile";
        System.out.println("Enter Vehicle ID  : ");
        sc.nextLine();
        String n = sc.nextLine();
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).vehicleID.equals(n)) {
                c++;
                System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-18s %-26s %-26s", "SNo", "Vehicle ID", "Vehicle Type", "Vehicle Name", "Vehicle Model", "vehicle Price", "Vehicle Rent Price", "Vehicle Total Kilometer", "Vehicle Trip Kilometer"));
                if (t == 1 && vehicleList.get(i).vehicleTripKM < 3000) {
                    System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-18s %-26s %-26s", (i + 1), vehicleList.get(i).vehicleID, vehicleList.get(i).vehicleType, vehicleList.get(i).vehicleName, vehicleList.get(i).vehicleModel, vehicleList.get(i).vehiclePrice, vehicleList.get(i).vehicleRentPrice, vehicleList.get(i).vehicleTotalKM, vehicleList.get(i).vehicleTripKM));
                } else if (t == 2 && vehicleList.get(i).vehicleTripKM < 1500) {
                    System.out.println(String.format("%-4s %-18s %-18s %-18s %-18s %-22s %-18s %-26s %-26s", (i + 1), vehicleList.get(i).vehicleID, vehicleList.get(i).vehicleType, vehicleList.get(i).vehicleName, vehicleList.get(i).vehicleModel, vehicleList.get(i).vehiclePrice, vehicleList.get(i).vehicleRentPrice, vehicleList.get(i).vehicleTotalKM, vehicleList.get(i).vehicleTripKM));
                }
            }
            if (c == 0) System.out.println("Invalid ID or Vehicle is Under Service!!!");
            else System.out.println("These are the Results!");
            System.out.println();
        }
    }

    private static void admin(){
        System.out.println("Admin Login Page");
        System.out.println("Enter admin name: ");
        sc.nextLine();
        String name1 = sc.nextLine();
        System.out.println("Enter password: ");
        int password1 = sc.nextInt();
        if (name1.equals(adminEmail) && password1 == adminPassword) {
            System.out.println();
            System.out.println("Admin Logged in Successfully");
            int n1 = 0;
            do {
                System.out.println("-----WELCOME ADMIN-----");
                System.out.println("1.Add vehicles");
                System.out.println("2.Remove vehicles");
                System.out.println("3.View All vehicles");
                System.out.println("4.Modify vehicles");
                System.out.println("5.View Renters List");
                System.out.println("6.Search vehicle");
                System.out.println("0.Exit");
                System.out.println();
                n1 = sc.nextInt();
                switch (n1) {
                    case 0:
                        System.out.println("Admin Logged out successfully");
                        break;
                    case 1:
                        addVehicle();
                        break;
                    case 2:
                        removeVehicle();
                        break;
                    case 3:
                        viewVehicles();
                        break;
                    case 4:
                        modifyVehicle();
                        break;
                    case 5:
                        viewUsers();
                        break;
                    case 6:
                        adminSearchVehicle();
                        break;
                    default:
                        System.out.println("Invalid Number");
                        break;
                }
            } while (n1 != 0);
        }
        else {
            System.out.println("Login Id or Password Incorrect!!!");
            System.out.println("!-----Please Retry Again-----!");
            System.out.println();
        }
    }

    // Main Function-------------------------------------------------------

    public static void main(String[] args) {
        ArrayList<Borrow> his =new ArrayList<>();
        user u = new user("user1","1",35000,1,his);
        userList.add(u);
        vehicle v = new vehicle("Car","c1","Polo",2015,15,500000,35680,0);
        vehicleList.add(v);
        int n=0;
        do{
            System.out.println("-----VEHICLE RENTAL SYSTEM-----");
            System.out.println("1.Admin");
            System.out.println("2.New User");
            System.out.println("3.Existing User");
            System.out.println("0.Exit");
            System.out.println();
            n=sc.nextInt();
            switch (n){
                case 0:
                    System.out.println("Exit Successfully");
                    break;
                case 1:
                    admin();
                    break;
                case 2:
                    newUser();
                    break;
                case 3:
                    borrower();
                default:
                    System.out.println("Invalid Number");
                    break;
            }
        }while(n!=0);
    }
}
