import java.util.*;
import java.lang.*;
class User{
    String name;
    String pin;
    ArrayList<User> friends = new ArrayList<>();
    ArrayList<User> requests = new ArrayList<>();
    ArrayList<Expense> expenses = new ArrayList<>();
    ArrayList<Group> groups = new ArrayList<>();
    int wallet;
}
class Expense{
    String name;
    User from;
    User to;
    int amt;
}
class GroupExpense{
    String name;
    User from;
    ArrayList<User> to = new ArrayList<>();
    ArrayList<User> done = new ArrayList<>();
    int amt;
}
class Group{
    String name;
    ArrayList<User> groupmembers = new ArrayList<>();
    ArrayList<GroupExpense> groupexpenses = new ArrayList<>();
}
public class SplitDemo{
    public static User createuser(){
        Scanner sc = new Scanner(System.in);
        User u = new User();
        System.out.println("Enter Name : ");
        u.name = sc.next();
        System.out.println("Enter Pin : ");
        u.pin = sc.next();
        System.out.println("Enter Amount to add in wallet : ");
        u.wallet = sc.nextInt();
        if(u.wallet<0){
            u.wallet = 0;
            System.out.println("Invalid Amount");
        }
        return u;
    }
    public static ArrayList<Integer> addmember(User x){
        ArrayList<Integer> res = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<x.groups.size();i++){
            System.out.println(i+" - Group Name : "+x.groups.get(i).name);
        }
        System.out.println("Enter the Group : ");
        int gind = sc.nextInt();
        res.add(gind);
        for(int i=0;i<x.friends.size();i++){
            Boolean b  = true;
            for(int j=0;j<x.groups.get(gind).groupmembers.size();j++){
                if(x.friends.get(i).name.equals(x.groups.get(gind).groupmembers.get(j).name)){
                    b=false;
                }
            }
            if(b){
                System.out.println(i+" - "+x.friends.get(i).name);
            }
        }
        System.out.println("Enter the Numer of Friends : ");
        int nos = sc.nextInt();
        res.add(nos);
        for(int i=0;i<nos;i++){
            int k = sc.nextInt();
            for(int j=0;j<x.groups.get(gind).groupmembers.size();j++){
                if(!x.friends.get(k).name.equals(x.groups.get(gind).groupmembers.get(j).name)){
                    res.add(k);
                }
                else{
                    System.out.println("User Already in group");
                }
            }
        }
        return res;
    }
    public static int valid_user(ArrayList<User> x){
        int res = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Name : ");
        String name = sc.nextLine();
        System.out.println("Enter Your PIN : ");
        String pin = sc.nextLine();
        for(int i=0;i<x.size();i++){
            if(x.get(i).name.equals(name)&&x.get(i).pin.equals(pin)){
                res=i;
            }
        }
        return res;
    }
    public static int addfriends(ArrayList<User> x,ArrayList<User> y,int ul){
        int res = -1;
        Scanner sc = new Scanner(System.in);
        Boolean p = false;
        if(y.size()>0){
            for(int i=0;i<x.size();i++){
                Boolean b= true;
                for(int j=0;j<y.size();j++){
                    if((x.get(i).name.equals(y.get(j).name))||(x.get(i).name.equals(x.get(ul).name))){
                        b = false;
                    }
                }
                if(b){
                    System.out.println(i+"-"+x.get(i).name);
                    p=true;
                }
            }
        }
        else{
            for(int i=0;i<x.size();i++){
                if(!x.get(i).name.equals(x.get(ul).name)){
                    p = true;
                    System.out.println(i+"-"+x.get(i).name);
                }
            }
        }
        if(p){
            System.out.println("Choose from the above  : ");
            int ch = sc.nextInt();
            if(ch>=0&&ch<x.size()){
                res = ch;
            }
        }
        return res;
    }
    public static int addwallet(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount You Want to add : ");
        int amt =sc.nextInt();
        return amt;
    }
    public static int acceptfriends(ArrayList<User> x){
        int res = -1;
        if(x.size()>0){
            for(int i=0;i<x.size();i++){
                System.out.println(i+"-"+x.get(i).name);
            }
            System.out.println("Choose from the above  : ");
            Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            if(ch>=0&&ch<x.size()){
                res=ch;
            }
        }
        return res;
    }
    public static Expense addExpense(ArrayList<User> x,User u){
        Scanner sc = new Scanner(System.in);
        Expense e = new Expense();
        if(x.size()>0){
            System.out.println("Enter the name of Expense");
            e.name = sc.nextLine();
            for(int i=0;i<x.size();i++){
                System.out.println(i+"-"+x.get(i).name);
            }
            System.out.println("Select the user : ");
            int ch = sc.nextInt();
            System.out.println("Enter the expense amount : ");
            int amt = sc.nextInt();
            if(ch>=0&&ch<x.size()&&amt>0){
                e.from = u;
                e.to = x.get(ch);
                e.amt = amt;
            }
            else{
                e.amt=-1;
            }
        }
        else{
            e.amt = -2;
        }
        return e;
    }
    public static Expense expensepayup(ArrayList<Expense> expenses,User u){
        Scanner sc = new Scanner(System.in);
        Expense e = new Expense();
        Boolean b= false;
        for(int i=0;i<expenses.size();i++){
            if(expenses.get(i).to.name.equals(u.name)){
                b=true;
            }
        }
        if(b){
            for(int i=0;i<expenses.size();i++){
                if(expenses.get(i).to.name.equals(u.name)){
                    System.out.println("Expense ID : "+i);
                    System.out.println("Expense name : "+expenses.get(i).name);
                    System.out.println("Expense Amount : "+expenses.get(i).amt);
                    System.out.println("Payed By : "+expenses.get(i).from.name);
                    System.out.println("Owed By : "+expenses.get(i).to.name);
                    System.out.println("--------------------------------------------");
                }
            }
            System.out.println("Enter the ID of the Expense : ");
            int id = sc.nextInt();
            if(id>=0&&id<expenses.size()){
                e=expenses.get(id);
            }
            else{
                e.amt=-1;
            }
        }
        else{
            e.amt=-2;
        }
        return e;
    }
    public static Group creategroup(ArrayList<User> x,User y){
        Group g = new Group();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Group name : ");
        String name = sc.nextLine();
        g.name = name;
        g.groupmembers.add(y);
        for(int i=0;i<x.size();i++){
            System.out.println(i+"-"+x.get(i).name);
        }
        System.out.println("Enter the Number of friends You want to add : ");
        int nos = sc.nextInt();
        if(nos<=x.size()&&nos>0){
            System.out.println("Enter the users You Want to add : ");
            for(int i=0;i<nos;i++){
                int k = sc.nextInt();
                for(int j=0;j<g.groupmembers.size();j++){
                    if(!x.get(k).name.equals(g.groupmembers.get(j).name)){
                        g.groupmembers.add(x.get(k));
                    }
                }
            }
            for(int i=0;i<g.groupmembers.size();i++){
                int c=0;
                for(int j=0;j<g.groupmembers.size();j++){
                    if(g.groupmembers.get(i).name.equals(g.groupmembers.get(j).name)){
                        c+=1;
                    }
                }
                if(c>1){
                    g.groupmembers.remove(i);
                }
            }
            for(int i=0;i<g.groupmembers.size();i++){
                System.out.println(g.groupmembers.get(i).name);
            }
        }
        else{
            g.name="inv";
        }
        return g;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        u1.name = "user1";
        u1.pin ="1";
        u1.wallet = 1000;
        u2.name = "user2";
        u2.pin ="1";
        u2.wallet = 1000;
        u3.name = "user3";
        u3.pin ="1";
        u3.wallet = 1000;
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.get(0).friends.add(users.get(1));
        users.get(0).friends.add(users.get(2));
        users.get(1).friends.add(users.get(0));
        users.get(2).friends.add(users.get(0));
        Boolean whole_exit = false;
        while(!whole_exit){
            System.out.println("1-New User");
            System.out.println("2-Existing User");
            System.out.println("3-Exit");
            int ch = sc.nextInt();
            switch(ch){
                case 1:users.add(createuser());
                    break;
                case 2 : int ul = valid_user(users);
                    if(ul!=-1){
                        Boolean login_status=true;
                        while(login_status){
                            System.out.println("1-Add friends");
                            System.out.println("2-Create Expense");
                            System.out.println("3-Show Expense");
                            System.out.println("4-Pay Up");
                            System.out.println("5-Create Group");
                            System.out.println("6-Add Members to Group");
                            System.out.println("7-Add Expense To Group");
                            System.out.println("8-Show Group Stats");
                            System.out.println("9-Payup in Group");
                            System.out.println("10-Show Friends");
                            System.out.println("11-Accept Friend Request");
                            System.out.println("12-Show Wallet Balance");
                            System.out.println("13-Add amount to Wallet");
                            System.out.println("14-LogOut");
                            int uch = sc.nextInt();
                            switch(uch){
                                case 1:int af = addfriends(users,users.get(ul).friends,ul);
                                    if(af!=-1){
                                        users.get(af).requests.add(users.get(ul));
                                        System.out.println("Friend Request Sent");
                                    }
                                    else{
                                        System.out.println("Choose a valid option");
                                    }
                                    break;
                                case 2:Expense e = addExpense(users.get(ul).friends,users.get(ul));
                                    if(e.amt>0){
                                        users.get(ul).expenses.add(e);
                                        users.get(users.indexOf(e.to)).expenses.add(e);
                                    }
                                    else if(e.amt==-2){
                                        System.out.println("You have no friends , Add Some Friends To Create an expense ");
                                    }
                                    else{
                                        System.out.println("Invalid Options Entered");
                                    }
                                    break;
                                case 3:for(int i=0;i<users.get(ul).expenses.size();i++){
                                    System.out.println("Expense name : "+users.get(ul).expenses.get(i).name);
                                    System.out.println("Expense Amount : "+users.get(ul).expenses.get(i).amt);
                                    System.out.println("Payed By : "+users.get(ul).expenses.get(i).from.name);
                                    System.out.println("Owed By : "+users.get(ul).expenses.get(i).to.name);
                                    System.out.println("--------------------------------------------");
                                }
                                    break;
                                case 4:Expense payup = expensepayup(users.get(ul).expenses,users.get(ul));
                                    if(payup.amt>0){
                                        users.get(users.indexOf(payup.from)).wallet+=payup.amt/2;
                                        users.get(users.indexOf(payup.to)).wallet-=payup.amt/2;
                                        users.get(users.indexOf(payup.from)).expenses.remove(payup);
                                        users.get(users.indexOf(payup.to)).expenses.remove(payup);
                                        System.out.println("Payed Up Successfully");
                                    }
                                    else if(payup.amt==-1){
                                        System.out.println("Enter a valid ID");
                                    }
                                    else if(payup.amt==-2){
                                        System.out.println("Nothing to Pay up");
                                    }
                                    break;
                                case 5:Group g = creategroup(users.get(ul).friends,users.get(ul));
                                    if(!g.name.equals("inv")){
                                        users.get(ul).groups.add(g);
                                        for(int i=0;i<g.groupmembers.size();i++){
                                            if(!g.groupmembers.get(i).name.equals(users.get(ul).name)){
                                                users.get(users.indexOf(g.groupmembers.get(i))).groups.add(g);
                                            }
                                        }
                                        System.out.println("Group Created Successfully");
                                    }
                                    break;
                                case 6 : ArrayList<Integer> am = addmember(users.get(ul));
                                    if(am.get(0)!=-1){
                                        for(int i=0;i<am.get(1);i++){
                                            users.get(ul).groups.get(am.get(0)).groupmembers.add(users.get(ul).friends.get(am.get(i+2)));
                                        }
                                        int gind=-1;
                                        for(int i=0;i<am.get(1);i++){
                                            for(int j=0;j<users.size();j++){
                                                if(users.get(j).name.equals(users.get(ul).friends.get(am.get(i+2)).name)){
                                                    gind = j;
                                                }
                                            }
                                            users.get(gind).groups.add(users.get(ul).groups.get(am.get(0)));
                                            System.out.println(users.get(gind).name);
                                        }
                                    }
                                    break;
                                case 7:
                                    GroupExpense age = new GroupExpense();
                                    for(int i=0;i<users.get(ul).groups.size();i++){
                                        System.out.println(i+"-"+users.get(ul).groups.get(i).name);
                                    }
                                    System.out.println("Choose the Group : ");
                                    int ageo = sc.nextInt();
                                    System.out.println("Enter the expense name : ");
                                    age.name = sc.next();
                                    System.out.println("Enter the expense amount : ");
                                    age.amt = sc.nextInt();
                                    age.from = users.get(ul);
                                    for(int i=0;i<users.get(ul).groups.get(ageo).groupmembers.size();i++){
                                        age.to.add(users.get(ul).groups.get(ageo).groupmembers.get(i));
                                    }
                                    age.to.remove(users.get(ul));
                                    if(age.amt!=-1){
                                        users.get(ul).groups.get(ageo).groupexpenses.add(age);
                                    }
                                    break;
                                case 8:
                                    for(int i=0;i<users.get(ul).groups.size();i++){
                                        System.out.println("Group name : "+users.get(ul).groups.get(i).name);
                                        for(int j=0;j<users.get(ul).groups.get(i).groupexpenses.size();j++){
                                            System.out.println("Expense name : "+users.get(ul).groups.get(i).groupexpenses.get(j).name);
                                            System.out.println("Expense Amount : "+users.get(ul).groups.get(i).groupexpenses.get(j).amt);
                                            System.out.println("Payed By : "+users.get(ul).groups.get(i).groupexpenses.get(j).from.name);
                                            for(int k=0;k<users.get(ul).groups.get(i).groupexpenses.get(j).to.size();k++){
                                                System.out.println("Owed By : "+users.get(ul).groups.get(i).groupexpenses.get(j).to.get(k).name);
                                            }
                                            for(int k=0;k<users.get(ul).groups.get(i).groupexpenses.get(j).done.size();k++){
                                                System.out.println("Done Owing : "+users.get(ul).groups.get(i).groupexpenses.get(j).done.get(k).name);
                                            }
                                            System.out.println("--------------------------------------------");
                                        }
                                        System.out.print("Group Members : ");
                                        for(int j=0;j<users.get(ul).groups.get(i).groupmembers.size();j++){
                                            System.out.print(users.get(ul).groups.get(i).groupmembers.get(j).name);
                                        }
                                        System.out.println();
                                    }
                                    break;
                                case 9:for(int i=0;i<users.get(ul).groups.size();i++){
                                    System.out.println(i+"-"+users.get(ul).groups.get(i).name);
                                }
                                    float pa=0;
                                    int pg = sc.nextInt();
                                    for(int i=0;i<users.get(ul).groups.get(pg).groupexpenses.size();i++){
                                        if(!users.get(ul).groups.get(pg).groupexpenses.get(i).from.equals(users.get(ul))){
                                            Boolean b = true;
                                            for(int j=0;j<users.get(ul).groups.get(pg).groupexpenses.get(i).done.size();j++){
                                                if(users.get(ul).groups.get(pg).groupexpenses.get(i).done.get(j).equals(users.get(ul))){
                                                    b=false;
                                                }
                                            }
                                            if(b){
                                                System.out.println(i+"-"+users.get(ul).groups.get(pg).groupexpenses.get(i).name);
                                            }
                                        }
                                    }
                                    System.out.println("Choose the expense : ");
                                    int pe  = sc.nextInt();
                                    pa=(float)users.get(ul).groups.get(pg).groupexpenses.get(pe).amt/(users.get(ul).groups.get(pg).groupexpenses.get(pe).to.size()+1);
                                    if(pa>0){
                                        System.out.println("0-Pay from Wallet ");
                                        System.out.println("1-Not Now");
                                        int uuc = sc.nextInt();
                                        if(uuc==0){
                                            if(users.get(ul).wallet>pa){
                                                System.out.println(users.get(ul).wallet);
                                                System.out.println(pa);
                                                users.get(ul).wallet-=pa;
                                                users.get(ul).groups.get(pg).groupexpenses.get(pe).done.add(users.get(ul));
                                                users.get(ul).groups.get(pg).groupexpenses.get(pe).from.wallet+=pa;
                                            }
                                            else{
                                                System.out.println("Insuffiecient Balance");
                                            }
                                        }
                                    }
                                    break;
                                case 10:for(int i = 0;i<users.get(ul).friends.size();i++){
                                    System.out.println(users.get(ul).friends.get(i).name);
                                }
                                    break;
                                case 11:int ar = acceptfriends(users.get(ul).requests);
                                    if(ar!=-1){
                                        int en = -1;
                                        users.get(ul).friends.add(users.get(ul).requests.get(ar));
                                        for(int i=0;i<users.size();i++){
                                            if(users.get(ul).requests.get(ar).name.equals(users.get(i).name)){
                                                en = i;
                                            }
                                        }
                                        users.get(en).friends.add(users.get(ul));
                                        users.get(ul).requests.remove(users.get(en));
                                        System.out.println("Accepted Friend Request");
                                    }
                                    else{
                                        System.out.println("Enter a valid number");
                                    }
                                    break;
                                case 12:System.out.println("Available Balance is : "+users.get(ul).wallet);
                                    break;
                                case 13:int amt = addwallet();
                                    if(amt>0){
                                        users.get(ul).wallet+=amt;
                                    }
                                    break;
                                case 14:login_status = false;
                                    break;
                                default : System.out.println("Enter a valid Option");
                            }
                        }
                    }
                    break;
                case 3 : whole_exit = true;
                    break;
                default : System.out.println("Enter a valid option");
            }
        }
    }
}
