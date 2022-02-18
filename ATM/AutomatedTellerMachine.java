import java.util.*;

class UserDetail{
    String name;
    int password;
    int balance;
    String userID;
    public UserDetail(String name,int password,int balance,String userID){
        this.name=name;
        this.password=password;
        this.balance=balance;
        this.userID=userID;
    }
}
class AutomatedTellerMachine {

    static Scanner sc=new Scanner(System.in);
    static int[] noOfRs ={1,8,10,10};
    static int[]  rs = {2000,500,200,100};
    static int total=0;
    static String adminName = "a";
    static int adminPin = 1;
    static UserDetail u[]= new UserDetail[4];
    static int miniN,miniM=0;
    static int[][] miniSt = {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};
    static int w[]=new int[u.length];
    static int temp=0,k=0;

    // Admin Function------------------------------------------------

    static void addAmount(){
        System.out.println();
        System.out.println("Load Amount");
        for(int i=0;i<4;i++){
            System.out.print("No of "+rs[i]+" : ");
            noOfRs[i]=sc.nextInt();
        }
        System.out.println("Amount Loaded Successfully");
        System.out.println();
    }
    static void showAmount(){
        System.out.println();
        System.out.println("Available Amount");
        total=0;
        for(int i=0;i<4;i++) {
            System.out.println("No of " + rs[i] + "'s : " + noOfRs[i]);
            total += noOfRs[i] * rs[i];
        }
        System.out.println("Total Amount Left: "+total);
        System.out.println();
    }

    // User Function-----------------------------------------------------

    static int userd(String name2,int password2){
        int p=0;
        for(int i=0;i<u.length;i++){
            if (u[i].name.equals(name2) && u[i].password == password2){//(name2.equals(userName) && password2 == userPin)
                miniM=i;
                miniN=i;
                k=i;
                p++;
            }
        }
        return p;
    }
    static int remainder(int n){
        for (int i = 0; i < 4; i++) {
            int a=noOfRs[i];
            int k =rs[i];
            while (n >= k && a > 0) {
                n -= k;
                a--;
            }
        }
        return n;
    }
    static int getMul(int i){
        int max=i;
            if (noOfRs[i] < noOfRs[i+1]) {
                int n = noOfRs[i] * rs[i];
                int t = n / rs[i+1];
                if (t > noOfRs[i]) max++;
            }
            if(max==1){
                if (noOfRs[max] < noOfRs[max+1]) {
                    int n = noOfRs[max] * rs[max];
                    int t = n / rs[max+1];
                    if (t > noOfRs[max]) max++;
                }
            }
        if(max==2){
            if (noOfRs[max] < noOfRs[max+1]) {
                int n = noOfRs[max] * rs[max];
                int t = n / rs[max+1];
                if (t > noOfRs[max]) max++;
            }
        }
        return max;
    }
    static void withAmount() {
        System.out.println();
        System.out.print("Enter Amount to Withdraw : ");
        int n = sc.nextInt();
        int rem=remainder(n);
        if ( rem== 0) {
            if (u[k].balance > n) {
                int noOfNote[] = {0, 0, 0, 0};
                int t = n,c=0;
                for (int i = 0; i < 4; i++) {
                    if(i<3){
                        i=getMul(i);
                    }
                    while (noOfRs[i] > 0 && n >= rs[i]) {
                        n -= rs[i];
                        noOfRs[i]--;
                        noOfNote[i]++;
                    }
                }
                total -= t;
                u[k].balance -= t;
                w[miniN]++;
                if(w[miniN]>6) {
                    w[miniN]=1;
                }
                miniSt[miniM][w[miniN]]=t;
                System.out.println("Please Collect Your Amount ! " + t);
                for (int i = 0; i < 4; i++) {
                    System.out.println("With " + noOfNote[i] + " " + rs[i] + "'s");
                }
            } else System.out.println("Sorry!!! Your account is in insufficient funds \n  Please Enter Minimum Amount1");
        }
        else System.out.println("Sorry!!! ATM is in insufficient funds \n  Please Enter Minimum Amount");
        System.out.println();
    }
    static void balanceAmount(){
        System.out.println();
        System.out.println("Available Balance in your account is : "+u[k].balance);
        System.out.println();
    }
    static void changePin(){
        System.out.println();
        System.out.println("Change Your Pin");
        System.out.print("Enter Old Pin : ");
        int oldPin = sc.nextInt();
        if(oldPin==u[k].password) {
            System.out.print("Enter New Pin : ");
            int newPin = sc.nextInt();
            u[k].password=newPin;
        }
        else System.out.println("Please Enter your Old Pin correctly");
        System.out.println();
    }
    static void directDeposit(){
        System.out.println();
        System.out.println("Deposit Amount");
        int t=0;
        for(int i=0;i<4;i++){
            System.out.print("No of "+rs[i]+" : ");
            int n=sc.nextInt();
            t+=(rs[i]*n);
            noOfRs[i]+=n;
            total += rs[i]*n;
            u[k].balance+=rs[i]*n;
        }
        w[miniN]++;
        if(w[miniN]>6) {
            w[miniN]=1;
        }
        miniSt[miniM][w[miniN]]=t+1;
        System.out.println(t+" Deposited Successfully");
        System.out.println();
    }
    static void miniStatement(){
        System.out.println();
        System.out.println("-----Mini Statement-----");
        int s = 0;
        for (int i = 0; i <= 6; i++) {
            if (s == 0) {
                System.out.println();
                s++;
            } else System.out.println("Transaction " + i + " --> " + miniSt[k][i]);
        }
        s=0;
        System.out.println();
    }

    static void accountTransfer(int k){
        System.out.println("Enter Amount Transfer Account Number");
        String transferAc = sc.next();
        int pro=0,amt=0;
        for (int i = 0; i < u.length; i++) {
            if (u[i].userID.equals(transferAc)){
                System.out.println("Enter Transfer Amount");
                amt = sc.nextInt();
                if (u[k].balance <= amt) {
                    System.out.println("Enter Minimum Amount");
                } else {
                    u[k].balance-=amt;
                    u[i].balance+=amt;
                    pro++;
                    if(w[i]>6) {
                        w[i]=0;
                    }
                    w[i]++;
                    miniSt[i][w[i]]=amt+3;
                    System.out.println("Amount Transferred Successfully");
                }
            }
        }
        if(pro>0) {
            w[miniN]++;
            if(w[miniN]>6) {
                w[miniN]=1;
            }
            miniSt[miniM][w[miniN]]=amt+2;
        }
        else System.out.println("Check Transfer Account Number");
    }

    // Main Function--------------------------------------------
    public static void main(String[] args) {
        /*
        for (int i = 0; i < 2; i++) {
            u[i] = new UserDetails();
            u[i].name = sc.nextLine();
            u[i].password = sc.nextInt();
            u[i].balance=sc.nextInt();
            sc.nextLine();
            u[i].userID = sc.nextLine();
            System.out.println(u[i].name);
            System.out.println(u[i].password);
            System.out.println(u[i].balance);
            System.out.println(u[i].userID);
        }
        */
        u[0] = new UserDetail("u1",1,52300,"u1");
        u[1] = new UserDetail("u2",1,72300,"u2");
        u[2] = new UserDetail("u3",1,62300,"u3");
        u[3] = new UserDetail("u4",1,42300,"u4");
            int n = 0;
            do {
                System.out.println("-----ATM APPLICATION LOGIN-----");
                System.out.println("1.ADMIN");
                System.out.println("2.USER");
                System.out.println("3.EXIT");
                System.out.println("Enter Your Choice : ");
                n = sc.nextInt();
                switch (n) {
                    case 1:
                        try{
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println();
                            System.out.println("Admin Login Page");
                            System.out.println("Enter user name: ");
                            sc.nextLine();
                            String name1 = sc.nextLine();
                            System.out.println("Enter password: ");
                            int password1 = sc.nextInt();
                            if (name1.equals(adminName) && password1 == adminPin) {
                                System.out.println();
                                System.out.println("Admin Logged in Successfully");
                                int n1 = 0;
                                do {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("-----ADMIN PANEL-----");
                                    System.out.println("1.LOAD");
                                    System.out.println("2.SHOW");
                                    System.out.println("3.EXIT");
                                    System.out.println("Enter Your Choice : ");
                                    n1 = sc.nextInt();
                                    switch (n1) {
                                        case 1:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            addAmount();
                                            break;
                                        case 2:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            showAmount();
                                            break;
                                        case 3:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println("Admin Logged out Successfully");
                                            System.out.println();
                                            break;
                                        default:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println("Invalid");
                                            System.out.println();
                                            break;
                                    }
                                } while (n1 != 3);
                            } else {
                                System.out.println("Login name or Password Incorrect!!!");
                                System.out.println("!-----Please Retry Again-----!");
                                System.out.println();
                            }
                            break;
                        }catch(Exception e) {
                            System.out.println("Please Enter a valid Number");
                        }
                    case 2:


                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println();
                            System.out.println("User Login Page");
                            System.out.println("Enter user name: ");
                            sc.nextLine();
                            String name2 = sc.nextLine();
                            System.out.println("Enter password: ");
                            int password2 = sc.nextInt();
                            temp=userd(name2,password2);
                            if(temp!=0) {
                                System.out.println(u[k].name+" Logged in Successfully");
                                int n2 = 0;
                                do {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("-----USER PANEL-----");
                                    System.out.println("1.WITHDRAW");
                                    System.out.println("2.CHECK BALANCE");
                                    System.out.println("3.Change Pin");
                                    System.out.println("4.Direct Deposit");
                                    System.out.println("5.Mini Statement");
                                    System.out.println("6.Account Transfer");
                                    System.out.println("7.EXIT");
                                    System.out.println("Enter Your Choice : ");
                                    n2 = sc.nextInt();
                                    switch (n2) {
                                        case 1:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            withAmount();
                                            break;
                                        case 2:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            balanceAmount();
                                            break;
                                        case 3:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            changePin();
                                            break;
                                        case 4:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            directDeposit();
                                            break;
                                        case 5:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            miniStatement();
                                            break;
                                        case 6:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            accountTransfer(k);
                                            break;
                                        case 7:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println("User Logged out Successfully");
                                            System.out.println();
                                            break;
                                        default:
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println("Invalid");
                                            System.out.println();
                                            break;
                                    }
                                } while (n2 != 7);
                            } else {
                                System.out.println("Login name or Password Incorrect!!!");
                                System.out.println("!-----Please Retry Again-----!");
                                System.out.println();
                            }
                            break;

                    case 3:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Exit Successfully");
                        System.out.println();
                        break;
                    default:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Invalid");
                        System.out.println();
                        break;
                }
            } while (n != 3);

        System.out.println("Thanks for Using");
    }
}
