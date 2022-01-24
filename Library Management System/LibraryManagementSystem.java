
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
class Book {
    String bookTitle,bookAuthor,bookType;
    int bookID,bookCount,bookPrice;


    Book(String bookTitle,String bookAuthor,String bookType,int bookPrice,int bookID,int bookCount){
        this.bookTitle=bookTitle;
        this.bookAuthor=bookAuthor;
        this.bookType=bookType;
        this.bookPrice=bookPrice;
        this.bookID=bookID;
        this.bookCount=bookCount;
    }
}
class Borrow {
    String bookTitle,userName;
    int userID,bookID,bookPrice;
    LocalDate borrowDate,returnDate;

    Borrow(String userName,int userID,int bookID,String bookTitle,int bookPrice,LocalDate borrowDate,LocalDate returnDate){
        this.userName=userName;
        this.userID=userID;
        this.bookTitle=bookTitle;
        this.bookPrice=bookPrice;
        this.bookID=bookID;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
    }
}


public class LibraryManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<user> userList =new ArrayList<>();
    static ArrayList<Book> bookList =new ArrayList<>();
    static ArrayList<Borrow> borrowList =new ArrayList<>();
    static String adminEmail="a";
    static int adminPassword=1;
    static int historyi=0;
    static int usertaken=0;
    static int useri=0;
//    static int totalUsersBorrowed=0;
//    static int totalTimesBorrowed=0;

    static int amount(int b){
        while (b<1500){
            System.out.println("Your Initial Balance Must be Equal to or Greater Than 1500.\nPlease Make your Initial Payment According to it.");
            b=sc.nextInt();
        }
        return b;
    }
    static void newUser() {
        System.out.println("Enter User Name : ");
        sc.nextLine();
        String uName = sc.nextLine();
        System.out.println("Enter Your Password : ");
        String uPassword = sc.nextLine();
        System.out.println("Enter your Balance : ");
        int bal=sc.nextInt();
        int ba=amount(bal);
        int id=userList.get(userList.size()-1).userID+1;
        ArrayList<Borrow> his =new ArrayList<>();
        user u = new user(uName,uPassword,ba,id,his);
        userList.add(u);
        System.out.println("New User Added Successfully");
        System.out.println("Your User Id is "+id+" Please Remember for Login");
        System.out.println();
    }
    public static boolean checkExistingInUser(String mailId, int pno){
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).userName.equals(mailId) && userList.get(i).userID==pno){
                return false;
            }
        }
        return true;
    }
    static void addUser() {
        System.out.println("Enter User Name : ");
        sc.nextLine();
        String uName = sc.nextLine();
        System.out.println("Enter User ID : ");
        int pno = sc.nextInt();
        boolean flag =checkExistingInUser(uName,pno);
        if(flag) {
            System.out.println("Enter Your Password : ");
            sc.nextLine();
            String uPassword = sc.nextLine();
            System.out.println("Enter your Balance : ");
            int bal=sc.nextInt();
            int ba=amount(bal);
            ArrayList<Borrow> his =new ArrayList<>();
            user u = new user(uName,uPassword,ba,pno,his);
            userList.add(u);
            System.out.println("New User Added Successfully");
        }else System.out.println("The User Name or ID is Already Exists \nPlease Try another Name or ID!");
        System.out.println();
    }
    public static boolean checkExistingInBook(String mailId, int pno){
        for(int i=0;i<bookList.size();i++){
            if(bookList.get(i).bookTitle.equals(mailId) && bookList.get(i).bookID==pno){
                return false;
            }
        }
        return true;
    }
    static void addBook(){
        System.out.println("Enter Book Title : ");
        sc.nextLine();
        String uName = sc.nextLine();
        System.out.println("Enter ISBN : ");
        int pno = sc.nextInt();
        boolean flag =checkExistingInBook(uName,pno);
        if(flag) {
            System.out.println("Enter Author Name : ");
            sc.nextLine();
            String author = sc.nextLine();
            System.out.println("Enter Book Type : ");
            String type = sc.nextLine();
            System.out.println("Enter Book Count : ");
            int count = sc.nextInt();
            System.out.println("Enter your Price : ");
            int bal=sc.nextInt();
            Book b = new Book(uName,author,type,bal,pno,count);
            bookList.add(b);
            System.out.println("New Book Added Successfully");
        }else System.out.println("The Book Name or ID is Already Exists \nPlease Try another Name or ID!");
        System.out.println();
    }
    static void removeBook(){
        int c=0;
        System.out.println("Enter Book Title : ");
        sc.nextLine();
        String uName = sc.nextLine();
        System.out.println("Enter ISBN : ");
        int pno = sc.nextInt();
        for(int i=0;i<bookList.size();i++){
            if(bookList.get(i).bookTitle.equals(uName) && bookList.get(i).bookID==pno){
                bookList.remove(i);
                c++;
            }
        }
        if(c==0) System.out.println("The Book does not Exists!!!\nEnter Book Name & ID Correctly!!!");
        else System.out.println("Book Removed Successfully");
        System.out.println();
    }
    static void removeUser(){
        int c=0;
        System.out.println("Enter User ID : ");
        int pno = sc.nextInt();
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).userID==pno){
                userList.remove(i);
                c++;
            }
        }
        if(c>0) System.out.println("User Removed Successfully");
        else System.out.println("Please Enter User ID Correctly!!!");
        System.out.println();
    }
    static void mod_tit(int i) {
        System.out.print("Enter New Book Title :");
        String book_tit=sc.next();
        bookList.get(i).bookTitle=book_tit;
        System.out.println("Book Title Changed Successfully....");
        System.out.println();
    }
    static void mod_auth(int i) {
        System.out.print("Enter Author Name :");
        String book_auth=sc.next();
        bookList.get(i).bookAuthor=book_auth;
        System.out.println("Book Author Changed Successfully....");
        System.out.println();
    }
    static void mod_type(int i) {
        System.out.print("Enter New Book Type :");
        String book_type=sc.next();
        bookList.get(i).bookType=book_type;
        System.out.println("Book Type Changed Successfully....");
        System.out.println();
    }
    static void mod_isbn(int i) {
        System.out.print("Enter New ISBN number :");
        int book_isbn=sc.nextInt();
        bookList.get(i).bookID=book_isbn;
        System.out.println("Book ISBN Changed Successfully....");
        System.out.println();
    }
    static void mod_price(int i) {
        System.out.print("Enter New Book Price :");
        int book_price=sc.nextInt();
        bookList.get(i).bookPrice=book_price;
        System.out.println("Book Title Changed Successfully....");
        System.out.println();
    }
    static void mod_count(int i) {
        System.out.print("Enter New Book Count :");
        int book_count=sc.nextInt();
        bookList.get(i).bookCount=book_count;
        System.out.println("Book Title Changed Successfully....");
        System.out.println();
    }
    static void modifyBook() {
        System.out.print("Enter the ISBN to modify Book :");
        int mod_count = 0;
        int temp_isbn = sc.nextInt();
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).bookID == temp_isbn) {
                mod_count++;
                int n6=0;
                do {
                    System.out.println("1.Modify Book Title");
                    System.out.println("2.Modify Book Author");
                    System.out.println("3.Modify Book Type");
                    System.out.println("4.Modify Book ISBN");
                    System.out.println("5.Modify Book Price");
                    System.out.println("6.Modify Book Count");
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
                }while(n6!=0);
            }
        }
        if (mod_count == 0) {
            System.out.println("Incorrect ISBN or Book not Found....");
        }
    }
    static void viewBooks() {
        System.out.println("-----Book List-----");
        if(bookList.size()>0) {
            System.out.println(String.format("%-4s %-12s %-30s %-24s %-16s %-16s %-16s ", "SNo", "ISBN_Number", "Book_Name", "Author_Name", "Book_Type", "Book_Price", "Book_Count"));
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println(String.format("%-4s %-12s %-30s %-24s %-16s %-16s %-16s ", (i + 1), bookList.get(i).bookID, bookList.get(i).bookTitle, bookList.get(i).bookAuthor, bookList.get(i).bookType, bookList.get(i).bookPrice, bookList.get(i).bookCount));
            }
        }
        else System.out.println("No Books Available!");
        System.out.println();
    }
    static void viewUsers() {
        System.out.println("-----User List-----");
        if(userList.size()>0) {
            System.out.println(String.format("%-4s %-8s %-25s %-12s", "SNo", "User_ID", "User_Name", "Balance_Amount"));
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(String.format("%-4s %-8s %-25s %-12s", (i + 1), userList.get(i).userID, userList.get(i).userName, userList.get(i).balance));
            }
        }else System.out.println("No Books Available!");
        System.out.println();
    }
    static void userSearchBook(){
        int c=0;
        System.out.println("Enter Book Title : ");
        sc.nextLine();
        String uName = sc.nextLine();
        for(int i=0;i<bookList.size();i++){
            if(bookList.get(i).bookTitle.equals(uName)){
                c++;
                System.out.println(String.format("%-4s %-12s %-30s %-24s %-16s %-16s %-16s ", "SNo", "ISBN_Number", "Book_Name", "Author_Name", "Book_Type", "Book_Price", "Book_Count"));
                System.out.println(String.format("%-4s %-18s %-24s %-24s %-20s %-16s %-16s ",c,bookList.get(i).bookID,bookList.get(i).bookTitle,bookList.get(i).bookAuthor,bookList.get(i).bookType,bookList.get(i).bookPrice,bookList.get(i).bookCount));
            }
        }
        if(c==0) System.out.println("Invalid Book Title or Book is not Presented!!!");
        else System.out.println("These are the Results!");
        System.out.println();
    }
    static void adminSearchBook(){
        int c=0;
        System.out.println("Enter ISBN Number : ");
        int n = sc.nextInt();
        for(int i=0;i<bookList.size();i++){
            if(bookList.get(i).bookID==n){
                c++;
                System.out.println(String.format("%-4s %-12s %-30s %-24s %-16s %-16s %-16s ", "SNo", "ISBN_Number", "Book_Name", "Author_Name", "Book_Type", "Book_Price", "Book_Count"));
                System.out.println(String.format("%-4s %-18s %-24s %-24s %-20s %-16s %-16s ",c,bookList.get(i).bookID,bookList.get(i).bookTitle,bookList.get(i).bookAuthor,bookList.get(i).bookType,bookList.get(i).bookPrice,bookList.get(i).bookCount));
            }
        }
        if(c==0) System.out.println("Invalid ISBN Number or Book is not Presented!!!");
        else System.out.println("These are the Results!");
        System.out.println();
    }
    static void manageFine(){
        System.out.println("8");
        System.out.println();
    }

    public static boolean checkHistory(int pno){
        if(borrowList.size()>0) {
            for (int i = 0; i < borrowList.size(); i++) {
                if (borrowList.get(i).bookID == pno) {
                    historyi=i;
                    return false;
                }
            }
        }
        return true;
    }
    static void borrowBook() {
        viewBooks();
        int c = 0, d = 0;
        if (usertaken<=3) {
            if (userList.get(useri).balance > 500) {
                System.out.print("Enter the book ISBN to place your order :");
                int n = sc.nextInt();
                boolean flag = checkHistory(n);
                for (int i = 0; i < bookList.size(); i++) {
                    if (flag) {
                        d++;
                        if (bookList.get(i).bookID == n && bookList.get(i).bookCount > 0) {
                            LocalDate b =LocalDate.now();
                            System.out.println("Enter Date of Returning : ");
                            sc.nextLine();
                            LocalDate r =LocalDate.parse(sc.nextLine());
                            Borrow a = new Borrow(userList.get(useri).userName, userList.get(useri).userID, bookList.get(i).bookID, bookList.get(i).bookTitle, bookList.get(i).bookPrice,b,r);
                            borrowList.add(a);
                            bookList.get(i).bookCount -= 1;
                            c++;
                            userList.get(useri).his.add((borrowList.get(borrowList.size()-1)));
                            usertaken++;
                            System.out.println("Book Borrowed successfully!!! You must return the book within 15 days otherwise you will be fined...");
                        }
                    }
                }
                if (d == 0) {
                    c++;
                    System.out.println("You Already Borrowed this Book!!! Yet to Return...");
                }
                if (c == 0) System.out.println("Book not available..");
            } else {
                System.out.println("Your Available Library balance is too low.....");
            }
        }else System.out.println("You only can Borrow 3 Books at a time!!!");
            System.out.println();
    }
    private static void book_return() {
        System.out.println("---------- Book Return Section -------");
        System.out.println("1.Book Return");
        System.out.println("2.Book Miss");
        System.out.print("Enter Your Choice :");
        int ch = sc.nextInt();
        switch (ch){
            case 0:
                break;
            case 1:
                book_ret();
                break;
            case 2:
                book_miss();
                break;
            default:
                System.out.println("Invalid Number");
                break;
        }
        System.out.println();
    }
    static void book_ret() {
        System.out.print("Enter the Book ISBN number:");
        int temp_isbn= sc.nextInt();
        sc.nextLine();
        for(int i=0;i<borrowList.size();i++){
            if(userList.get(useri).userID==borrowList.get(i).userID && borrowList.get(i).bookID==temp_isbn){
                LocalDate day=LocalDate.now();
                Period days= Period.between(userList.get(useri).his.get(historyi).borrowDate,day);
                if(days.getDays()<=15){
                    borrowList.remove(i);
                    System.out.println("Book Returned Successfully....:)");
                    bookList.get(i).bookCount+=1;
                }
                else if(days.getDays()>=16){
                    int f = 2;
                    double amt = 0;
                    if(days.getDays()%10==0) {
                        int x = (int) Math.floor(days.getDays() / 10);
                        for (int j = 1; j <= x; j++) {
                            amt += fine(f);
                            f += 2;
                        }
                    }
                    else {
                        int x = (int) Math.floor(days.getDays() / 10);
                        for (int j = 1; j <= x; j++) {
                            amt += fine(f);
                            f += 2;
                        }
                        amt+=((days.getDays())%10)*f;
                    }
                    double bAmount = bookList.get(i).bookPrice * 0.8;
                    int fineAmt= (int) Math.min(amt, bAmount);
                    System.out.println("You have Fined Amount Rs." + Math.min(amt, bAmount) + ".00 for Your Delayed Return...");
                    finePayment(fineAmt);
                    System.out.println("Book Returned Successfully....:)");
                    bookList.get(i).bookCount+=1;
                    borrowList.remove(i);
                }
            }
            else {
                System.out.println("Entered ISBN is not in your Borrowed History please Try again..");
            }
        }
        System.out.println();
    }
    static double fine(int n){
        double s=10*n;
        return s;
    }
    static void finePayment(int n){
        System.out.println("Pay By Cash--->(y/n)");
        sc.nextLine();
        String s=sc.nextLine();
        if(s.equals("y")) {
            userList.get(useri).balance -= n;
            System.out.println("Payment Completed!");
        }
        else if(s.equals("n")){
            System.out.println("Your Fine Amount will be Reduced in your Security Deposit!!!");
            userList.get(useri).balance -= n;
        }
        else System.out.println("Invalid Number");
    }
    static void book_miss() {
        System.out.print("Enter the Book ISBN number:");
        int temp_isbn= sc.nextInt();
        for(int i=0;i<borrowList.size();i++){
            if(userList.get(useri).userID==borrowList.get(i).userID && borrowList.get(i).bookID==temp_isbn){
                int temp_amt= (int)(borrowList.get(i).bookPrice*0.5);
                System.out.println("You have Fined 50% of the Book Amount of Rs."+temp_amt+"0 due to Lost of the Book...");
                finePayment(temp_amt);
                bookList.get(i).bookCount=0;
                borrowList.remove(i);
            }
            else{
                System.out.println("Entered ISBN is not in your Borrowed History Please Try again...");
            }
        }
        System.out.println();
    }

    static void borrowHistory() {
        System.out.println("------------- Your Borrow History ------------- ");
        System.out.println();
        System.out.println(String.format("%-4s %-8s %-30s %-10s %-15s %-15s %-15s","SNO","User ID","Book Title","ISBN","Book Price","Borrow date","Return date"));
        int f = 0;
        for (int i = 0; i < userList.get(useri).his.size(); i++) {
            if (userList.get(useri).userID == userList.get(useri).his.get(i).userID) {
                System.out.println(String.format("%-4s %-8s %-30s %-10s %-15s %-15s %-15s",(i+1),userList.get(useri).his.get(i).userID,userList.get(useri).his.get(i).bookTitle,userList.get(useri).his.get(i).bookID,userList.get(useri).his.get(i).bookPrice,userList.get(useri).his.get(i).borrowDate,userList.get(useri).his.get(i).returnDate));
                f++;
            }
        }
        if (f == 0) {
            System.out.println("No Borrow History left....");
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
        if (name1.equals(adminEmail) && password1 == adminPassword) {
            System.out.println();
            System.out.println("Admin Logged in Successfully");
        int n1=0;
        do{
            System.out.println("-----WELCOME ADMIN-----");
            System.out.println("1.Manage Users");
            System.out.println("2.Manage Books");
            System.out.println("0.Exit");
            System.out.println();
            n1=sc.nextInt();
            switch (n1){
                case 0:
                    System.out.println("Admin Logged out successfully");
                    break;
                case 1:
                    int n3=0;
                    do{
                        System.out.println("-----USER MANAGEMENT-----");
                        System.out.println("1.Add User");
                        System.out.println("2.Remove User");
                        System.out.println("3.View Users List");
//                        System.out.println("4.Manage Borrowers Fine Limit");
                        System.out.println("0.Exit");
                        System.out.println();
                        n3=sc.nextInt();
                        switch (n3){
                            case 0:
                                System.out.println("Exited from User Management");
                                break;
                            case 1:
                                addUser();
                                break;
                            case 2:
                                removeUser();
                                break;
                            case 3:
                                viewUsers();
                                break;
                            default:
                                System.out.println("Invalid Number");
                                break;
                        }
                    }while(n3!=0);
                    break;
                case 2:
                    int n4=0;
                    do{
                        System.out.println("-----BOOK MANAGEMENT-----");
                        System.out.println("1.Add Books");
                        System.out.println("2.Modify Books");
                        System.out.println("3.Remove Books");
                        System.out.println("4.View Books List");
                        System.out.println("5.Search Book");
                        System.out.println("0.Exit");
                        System.out.println();
                        n4=sc.nextInt();
                        switch (n4){
                            case 0:
                                System.out.println("Exited from Book Management");
                                break;
                            case 1:
                                addBook();
                                break;
                            case 2:
                                modifyBook();
                                break;
                            case 3:
                                removeBook();
                                break;
                            case 4:
                                viewBooks();
                                break;
                            case 5:
                                adminSearchBook();
                                break;
                            default:
                                System.out.println("Invalid Number");
                                break;
                        }
                    }while(n4!=0);
                    break;
                default:
                    System.out.println("Invalid Number");
                    break;
            }
        }while(n1!=0);
        }else {
            System.out.println("Login Id or Password Incorrect!!!");
            System.out.println("!-----Please Retry Again-----!");
            System.out.println();
        }
    }
    static void borrower(){
        System.out.println("Merchant Login Page");
        System.out.println("Enter User ID : ");
        sc.nextLine();
        int password =sc.nextInt();
        System.out.println("Enter Password : ");
        sc.nextLine();
        String n= sc.nextLine();
        int c=0;
        for(int i=0;i<userList.size();i++) {
            if (userList.get(i).userID==password && userList.get(i).userPassword .equals(n)) {
                usertaken=0;
                useri=i;
                c++;
                System.out.println("User Logged In Successfully");
            }
        }
        if(c>0) {
            int n2=0;
            do{
                System.out.println("-----WELCOME BORROWER-----");
                System.out.println("1.View all books");
                System.out.println("2.Borrow Book");
                System.out.println("3.Return Book");
                System.out.println("4.Check Balance");
                System.out.println("5.Search Book");
                System.out.println("6.Borrowed History");
//            System.out.println("5.Extend Tenure");
//            System.out.println("6.Exchange Book");
                System.out.println("0.Exit");
                System.out.println();
                n2=sc.nextInt();
                switch (n2){
                    case 0:
                        System.out.println("User Logged out successfully");
                        break;
                    case 1:
                        viewBooks();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        book_return();
                        break;
                    case 4:
                        System.out.println("Your Balance is : "+userList.get(useri).balance+".00");
                        System.out.println();
                        break;
                    case 5:
                        userSearchBook();
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
    public static void main(String[] args) {
        ArrayList<Borrow> his =new ArrayList<>();
        user u1 = new user("a","1",1000,1,his);
        userList.add(u1);
        bookList.add(new Book("The Greatness of Guide","Robin Sharma","General",300,100,3));
        bookList.add(new Book("The Adventures of Tow Sawyer","Mark Twain","Novel",200,101,3));
        bookList.add(new Book("My Earl Life","C.S.Lewis","Novel",150,102,3));
        bookList.add(new Book("Daily Inspiration","Robin Sharma","Motivation",270,103,0));
        bookList.add(new Book("Savrola","Winston Churchill","Political",300,104,0));
        int n=0;
        do{
            System.out.println("-----LIBRARY MANAGEMENT SYSTEM-----");
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
