import java.sql.*;
import java.util.ArrayList;

class ConnectionP{
    Connection con;
    public ConnectionP(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bighitvideo","root","12345678");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}


public class Task{
    public static void main(String[] args){
        try{
            listOfRentedAndRental();
        }
        catch(Exception e){
            System.out.println(e);
        }
       
    }

    //return accountID for customers
    public static String[] customerName()throws Exception{
        
        ArrayList<String> array = new ArrayList<>();
        
            Connection con = new ConnectionP().con;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select accountID from customer");
           
            while (rs.next()) {
                array.add(rs.getString(1));
            }
            con.close();    

       
        
        
        String[] arr = new String[array.size()];
        for(int i = 0; i < array.size(); i++){
            arr[i] = array.get(i);
        } 
        return arr;
    }

    //method lists out account id and movie
    public static void listOfRentedAndRental() throws Exception{
        
            Connection con = new ConnectionP().con;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select accountId, videoId from rental union select accountId, videoId from previousrental order by accountID");
           
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
            con.close();    

       
    }
}