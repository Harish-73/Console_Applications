import java.util.*;

class Merchant{
    String merchantName;
    int merchantPassword;
    String status;
    String merchantID;

    Merchant(String merchantName, int merchantPassword, String status, String merchantID){
        this.merchantName=merchantName;
        this.merchantPassword=merchantPassword;
        this.status=status;
        this.merchantID=merchantID;
    }
}

class User{
    String userMailId;
    int userPassword;
    int userBalance;
    String userId;


    User(String userMailId, int userPassword, int userBalance, String userId){
        this.userMailId=userMailId;
        this.userPassword=userPassword;
        this.userBalance=userBalance;
        this.userId=userId;
    }
}

class Products{
    int productNo;
    String productName;
    String productId;
    int noOfUnits;
    int productPrice;
    int disPrice;
    String merID;

    Products(int productNo,String productName,String productId,int noOfUnits,int productPrice,int disPrice,String merID) {
        this.productNo = productNo;
        this.productName = productName;
        this.productId = productId;
        this.noOfUnits = noOfUnits;
        this.productPrice = productPrice;
        this.disPrice = disPrice;
        this.merID = merID;
    }
}

class Approval{
    String merchantName;
    int merchantPassword;
    String status;


    Approval(String merchantName,int merchantPassword,String status){
        this.merchantName = merchantName;
        this.merchantPassword = merchantPassword;
        this.status=status;
    }
}
class Orders {
    String userId;
    String productId;
    String productName;
    int noUnits;
    int price;

    Orders(String userId,String productId,String productName,int noUnits,int price){
        this.userId=userId;
        this.productId=productId;
        this.productName=productName;
        this.noUnits=noUnits;
        this.price=price;
    }
}


public class ShoppingApplication {

    static Scanner sc = new Scanner(System.in);
    public static ArrayList<Merchant> merchantList = new ArrayList<>();
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Products> productList = new ArrayList<>();
    public static ArrayList<Approval> approvalList = new ArrayList<>();
    public static ArrayList<Orders> orderList = new ArrayList<>();
    static String adminEmail="admin";
    static int adminPassword=1;
    static int mi =0;
    static int ui =0;
    static int userBill=0;
    static int merchantId=0;
    static int merchantApprove=0;

    static boolean checkIfAlreadyExist(String name){
        for(int i=0;i<merchantList.size();i++) {
            if (merchantList.get(i).merchantName.equals(name)) return false;
        }
        return true;
    }
    public static boolean checkExistingInUser(String mailId){
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).userMailId.equals(mailId)){
                return false;
            }
        }
        return true;
    }

    // Admin Function--------------------------

    static void addMerchant(){
        System.out.println("Enter Merchant Name : ");
        sc.nextLine();
        String name =sc.nextLine();
        boolean flag = checkIfAlreadyExist(name);
        if(flag){
            System.out.println("Enter password");
            String id="u"+merchantList.size()+1;
            Merchant u=new Merchant(name,sc.nextInt(),"y",id);
            merchantList.add(u);
            System.out.println("Merchant Added Successfully");
        }
        else System.out.println("Merchant Name Already Exists \n  Please Try Some Other Name");
        System.out.println();
    }
    static void removeMerchant(){
        System.out.println("Enter Merchant ID to Remove: ");
        sc.nextLine();
        String n=sc.nextLine();
        int p=0;
        for (int i = 0; i < merchantList.size(); i++) {
            if (merchantList.get(i).merchantID.equals(n)) {
                merchantList.remove(i);
                System.out.println("Merchant Removed Successfully");
                p++;
                break;
            }
        }
        if(p==0) System.out.println("Merchant Name not Found!!!");
        System.out.println();
    }
    static void approveMerchant(){
        for(int i=0;i<approvalList.size();i++) {
            if (approvalList.get(i).status.equals("n")) {
                System.out.println("Merchant Name : " + approvalList.get(i).merchantName);
                System.out.println("1.Approve \n2.Reject");
                int sts = sc.nextInt();
                if (sts == 1) {
                    String id = "m" + (merchantList.size()+1);
                    approvalList.get(i).status="y";
                    Merchant u = new Merchant(approvalList.get(i).merchantName, approvalList.get(i).merchantPassword, "y", id);
                    merchantList.add(u);
                    System.out.println("Merchant Approved Successfully ");
                    merchantApprove=i;
                    merchantId++;
//                    System.out.println(merchantList.get(i).merchantName+"'s ID is " + id);
                } else if (sts == 2) {
                    approvalList.remove(i);
                    System.out.println("Merchant Rejected Successfully");
                } else System.out.println("Invalid Input");
            }
        }
        System.out.println("No more Approvals Pending!");
        System.out.println();
    }
    static void viewAllProducts(){
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).noOfUnits>0) {
                System.out.println(productList.get(i).productNo + " " + productList.get(i).productName
                        + " " + productList.get(i).productId + " " + productList.get(i).noOfUnits
                        + " " + productList.get(i).productPrice + " " + productList.get(i).disPrice
                        + " " + productList.get(i).merID);
            }
        }
        System.out.println();
    }
    static void viewMerchantList(){
        for(int i=0;i<merchantList.size();i++){
            System.out.println(merchantList.get(i).merchantName+" "+merchantList.get(i).merchantID);
        }
    }

    static void adminFunction(){
        System.out.println("Admin Login Page");
        System.out.println("Enter Admin name: ");
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
                System.out.println("1.Add Merchant");
                System.out.println("2.Remove Merchant");
                System.out.println("3.View All Products");
                System.out.println("4.Approve Merchant");
                System.out.println("5.View Merchant List");
                System.out.println("0.Exit");
                System.out.println();
                n1 = sc.nextInt();
                switch (n1) {
                    case 0:
                        System.out.println("Admin Logged out successfully");
                        break;
                    case 1:
                        addMerchant();
                        break;
                    case 2:
                        removeMerchant();
                        break;
                    case 3:
                        viewAllProducts();
                        break;
                    case 4:
                        approveMerchant();
                        break;
                    case 5:
                        viewMerchantList();
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

    // Merchant Function--------------------------

    static void newMerchant(){
        System.out.println("Enter Merchant name : ");
        sc.nextLine();
        String n=sc.nextLine();
        boolean flag = checkIfAlreadyExist(n);
        if(flag){
            System.out.println("Enter password : ");
            int password =sc.nextInt();
            Approval a1 = new Approval(n,password,"n");
            approvalList.add(a1);
            System.out.println("Please Wait for your Approval");
        }
        else System.out.println("Merchant Name is Already Exists!");
        System.out.println();
    }

    public static int noOfProduct(String k){
        int n=0;
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).merID.equals(k)){
                n++;
            }
        }
        return n;
    }
    static void addProducts(int k){
        int c=0;
        String pId="";
        if(productList.size()==0) {
            c++;
            pId= "p"+(merchantList.get(k).merchantID+(productList.size()+c));
        }
        else {
            pId= "p"+(merchantList.get(k).merchantID+productList.size());
        }
        int pNo = productList.size() + 101;
        System.out.println("Enter Product Name : ");
        sc.nextLine();String pName = sc.nextLine();
        System.out.println("Enter Available Units : ");
        int aUnit = sc.nextInt();
        System.out.println("Enter Product Price : ");
        int pPrice = sc.nextInt();
        System.out.println("Enter Discount Price : ");
        int dPrice = sc.nextInt();
        Products addProduct = new Products(pNo,pName,pId,aUnit,pPrice,dPrice,merchantList.get(k).merchantID);
        productList.add(addProduct);
        System.out.println("Product Added Successfully");
        System.out.println();
    }
    static void removeProducts(int k){
        System.out.println("Enter product ID : ");
        sc.nextLine();String pId = sc.nextLine();
//        System.out.println("Enter Product Name : ");
//        sc.nextLine();String pName = sc.nextLine();
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).productId.equals(pId) && productList.get(i).merID.equals(merchantList.get(k).merchantID)){
                productList.remove(i);
                break;
            }
        }
        System.out.println("product Removed Successfully");
    }
    public static void listMyProduct(int k){
        String a = merchantList.get(k).merchantID;
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).merID.equals(a)){
                System.out.println(productList.get(i).productNo+" "+productList.get(i).productName
                        +" "+productList.get(i).productId+" "+productList.get(i).noOfUnits
                        +" "+productList.get(i).productPrice+" "+productList.get(i).disPrice
                        +" " +productList.get(i).merID);
            }
        }
    }
    static void update(){
        System.out.println("Enter product ID : ");
        sc.nextLine();String pId = sc.nextLine();
        int up=0;int g=0;
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).productId.equals(pId)){
                g=i;
                break;
            }
        }
        do{
            System.out.println("1.Update Product Name");
            System.out.println("2.Update No Units");
            System.out.println("3.Update Product Price");
            System.out.println("4.Update Product Discount Price");
            System.out.println("0.Exit");
            up = sc.nextInt();
            switch (up) {
                case 0:
                    break;
                case 1:
                    System.out.println("Enter Product Name : ");
                    sc.nextLine();
                    String pName = sc.nextLine();
                    productList.get(g).productName = pName;
                    break;
                case 2:
                    System.out.println("Enter Available Units : ");
                    int aUnit = sc.nextInt();
                    productList.get(g).noOfUnits = aUnit;
                    break;
                case 3:
                    System.out.println("Enter Product Price : ");
                    int pPrice = sc.nextInt();
                    productList.get(g).productPrice = pPrice;
                    break;
                case 4:
                    System.out.println("Enter Discount Price : ");
                    int dPrice = sc.nextInt();
                    productList.get(g).disPrice = dPrice;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }while(up!=0);
        System.out.println();
    }

    static void merchantFunction(){
        System.out.println("Merchant Login Page");
        System.out.println("Enter Merchant ID : ");
        sc.nextLine();
        String n= sc.nextLine();
        System.out.println("Enter Password : ");
        int password =sc.nextInt();
        int c=0;
        for(int i=0;i<merchantList.size();i++) {
            if (merchantList.get(i).merchantID.equals(n) && merchantList.get(i).merchantPassword == password) {
                mi=i;
                c++;
                System.out.println("Merchant Logged In Successfully");
            }
        }
        if(c>0) {
            int n2 = 1;
            do {
                System.out.println();
                System.out.println("-----WELCOME MERCHANT-----");
                System.out.println("1.Add Products");
                System.out.println("2.Remove Products");
                System.out.println("3.Update Products");
                System.out.println("4.View My Products");
                System.out.println("0.Exit");
                System.out.println();
                n2 = sc.nextInt();
                switch (n2) {
                    case 0:
                        System.out.println("Merchant Logged out successfully");
                        break;
                    case 1:
                        addProducts(mi);
                        break;
                    case 2:
                        removeProducts(mi);
                        break;
                    case 3:
                        update();
                        break;
                    case 4:
                        listMyProduct(mi);
                        break;
                    default:
                        System.out.println("Invalid Number");
                        break;
                }

            } while (n2 != 0);
        }
        else System.out.println("Please Check ID or Password!!!");
    }

    // User Function-----------------------------

    static void newUser(){
        System.out.println("Enter User Mail ID : ");
        sc.nextLine();
        String usMail = sc.nextLine();
        boolean flag = checkExistingInUser(usMail);
        if(flag) {
            System.out.println("Enter User Password : ");
            int usPassword = sc.nextInt();
            System.out.println("Enter Deposit Amount : ");
            int usBalance = sc.nextInt();
            User user = new User(usMail, usPassword, usBalance, "u" + (userList.size() + 1));
            userList.add(user);
            System.out.println("Customer Registration Done Successfully");
        } else System.out.println("User Mail ID Already Exist!");
        System.out.println();
    }

    public static void removeProduct(int k){
        System.out.println("Remove product \n1.Yes  2.No");
        int n=sc.nextInt();
        if(n==1) {
            System.out.println("Enter product ID");
            sc.nextLine();
            String a = sc.nextLine();
            for (int i = 0; i < orderList.size(); i++) {
                if (userList.get(k).userId.equals(orderList.get(i).userId) && orderList.get(i).productId.equals(a)) {
                    orderList.remove(i);
                    System.out.println("Product Removed Successfully");
                    break;
                }
            }
        }
        else if(n==2) System.out.println();
        else System.out.println("Invalid Number");
        System.out.println();
    }
    public static int productId(String productId){
        int i;
        for(i=0;i<productList.size();i++){
            if(productId.equals(productList.get(i).productId)){
                return i;
            }
        }
        return i;
    }
    public static void checkOut(int k){
        int total = 0;
        if(orderList.size()==0){
            System.out.println("No Product Found on Your Cart! Add Product To Your Cart");
        }
        else {
            for (int i = 0; i < orderList.size(); i++) {
                if (userList.get(k).userId.equals(orderList.get(i).userId)) {
                    int a = productId(orderList.get(i).productId);
                    total += orderList.get(i).price * orderList.get(i).noUnits;
                    productList.get(a).noOfUnits -= orderList.get(i).noUnits;
                    orderList.remove(i);
                    userBill = total;
                }
            }
            userList.get(k).userBalance -= total;
            userBill = 0;
            System.out.println("Thanks for Shopping !");
            System.out.println();
        }
    }
    static void viweAvailableProductsInCart(int k){
        int total = 0;
        for(int i=0;i<orderList.size();i++){
            if(userList.get(k).userId.equals(orderList.get(i).userId)){
                System.out.println("Product ID : "+orderList.get(i).productId+" No of Units : "+orderList.get(i).noUnits);
                total+=orderList.get(i).price*orderList.get(i).noUnits;
            }
        }
        System.out.println("Total Amount : Rs."+total+".00");
        System.out.println();
    }
    public static void viewCart(int k) {
        int ch=0;
        do{
            System.out.println("1.View Products");
            System.out.println("2.Check Out");
            System.out.println("0.Continue Shopping");
            ch=sc.nextInt();
            switch (ch){
                case 0:
                    break;
                case 1:
                    if(userBill>0) {
                        for (int i = 0; i < orderList.size(); i++) {
                            if (userList.get(k).userId.equals(orderList.get(i).userId)) {
                                System.out.println("Product ID : " + orderList.get(i).productId + ";  Product Name : " + orderList.get(i).productName+ ";  No of Units : " + orderList.get(i).noUnits);
                            }
                        }
                        if(orderList.size()>0) removeProduct(k);
                        else System.out.println("No Product Found on Your Cart! \nAdd Product To Your Cart");
                    }else System.out.println("No Product Found on Your Cart! \nAdd Product To Your Cart");
                    break;
                case 2:
                    if(userBill>0) {
                        checkOut(k);
                    }else System.out.println("No Product Found on Your Cart!! \nAdd Product To Your Cart");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }while (ch!=0);
        System.out.println();
    }
    public static boolean checkProductID(String productId){
        int price=0;
        for(int i=0;i<productList.size();i++){
            if(productId.equals(productList.get(i).productId)){
//                return productList.get(i).productPrice;
                return true;
            }
        }
        if(price==0) System.out.println("Please Enter ProductID Correctly!");
        return false;
    }
    public static int totalPrice(int k){
        int total = 0;
        for(int i=0;i<orderList.size();i++){
            if(userList.get(k).userId.equals(orderList.get(i).userId)){
                total+=orderList.get(i).price*orderList.get(i).noUnits;
            }
        }
        return total;
    }
    static void addProductsToCart(){
        int no=0;
        System.out.println("Enter no of Items : ");
        no = sc.nextInt();
        for(int i=0;i<no;i++){
            System.out.println("Enter Product ID : ");
            sc.nextLine();String productId=sc.nextLine();
            System.out.println("Enter Product Name : ");
            String productName=sc.nextLine();
            System.out.println("Enter no of Units : ");
            int noUnits = sc.nextInt();
            if(checkProductID(productId)) {
                int proPrice = productList.get(i).productPrice;
                Orders addProductsToCart = new Orders(userList.get(ui).userId, productId, productList.get(i).productName, noUnits, proPrice);
                orderList.add(addProductsToCart);
            }

        }
        userBill = totalPrice(ui);
        if(userBill>0) {
            System.out.println("Product Added To Cart Successfully");
        }
        System.out.println();
    }

    static void userFunction(){
        int c=0;
        System.out.println("User Login Page");
        System.out.println("Enter User Mail ID : ");
        sc.nextLine();
        String usMail = sc.nextLine();
        System.out.println("Enter User Password : ");
        int usPassword = sc.nextInt();
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).userMailId.equals(usMail) && userList.get(i).userPassword==usPassword){
                ui=i;
                c++;
                System.out.println("User Logged In Successfully");
            }
        }
        if(c>0) {
            int n3 = 0;
            do {
                System.out.println();
                System.out.println("-----WELCOME USER-----");
                System.out.println("1.Add Products To Cart");
                System.out.println("2.View Available Products");
                System.out.println("3.View Cart");
                System.out.println("4.Check Balance");
                System.out.println("0.Exit");
                System.out.println();
                n3 = sc.nextInt();
                switch (n3) {
                    case 0:
                        System.out.println("User Logged out successfully");
                        break;
                    case 1:
                        addProductsToCart();
                        break;
                    case 2:
                        viewAllProducts();
                        break;
                    case 3:
                        if (userBill > 0) {
                            viweAvailableProductsInCart(ui);
                            viewCart(ui);
                        } else System.out.println("No Product Found on Your Cart!!! \nAdd Product To Your Cart");
                        break;
                    case 4:
                        System.out.println("Your Balance : " + userList.get(ui).userBalance + ".00");
                        break;
                    default:
                        System.out.println("Invalid Number");
                        break;
                }

            } while (n3 != 0);
        }else System.out.println("Please Check ID or Password!!!");
    }

    // Main Function--------------------------

    public static void main(String[] args) {
        Merchant m1 =new Merchant("b1",1,"y","m1");
        Merchant m2 =new Merchant("b2",1,"y","m2");
        merchantList.add(m1);
        merchantList.add(m2);
        User u1 =new User("u1",1,100000,"u1");
        userList.add(u1);
        Products p1 =new Products(101,"x","pm11",100, 10000,1000,"m1");
        productList.add(p1);
        int n=0;
        do{
            System.out.println("-----SHOPPING APPLICATION-----");
            System.out.println("1.Admin Login");
            System.out.println("2.New Merchant");
            System.out.println("3.Merchant Login");
            System.out.println("4.New Customer");
            System.out.println("5.Customer Login");
            System.out.println("0.Exit");
            System.out.println();
            n=sc.nextInt();
            switch (n){
                case 0:
                    System.out.println("Exit Successfully");
                    break;
                case 1:
                    adminFunction();
                    break;
                case 2:
                    newMerchant();
                    break;
                case 3:
                    System.out.println("Having Merchant ID : \n1.Yes  2.No");
                    int t=sc.nextInt();
                    if(t==1) merchantFunction();
                    else if(t==2) {
                        int c=0;
                        System.out.println("Enter Merchant Name : ");
                        sc.nextLine();
                        String s=sc.nextLine();
                        for(int i=0;i<merchantList.size();i++) {
                            if (merchantList.get(i).merchantName.equals(s)) {
                                System.out.println(merchantList.get(i).merchantName + "'s ID is " + "m" + (merchantList.size()));
                                c++;
                            }
                        }
                        if(c==0) System.out.println("Please Enter Your Name Correctly \n");
                    }else System.out.println("Invalid Number");
                    break;
                case 4:
                    newUser();
                    break;
                case 5:
                    userFunction();
                    break;
                default:
                    System.out.println("Invalid Number");
                    break;
            }
        }while(n!=0);
    }
}

