package model;
import java.sql.*;
public class Bill
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "madhi99#");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertBill(String userid,String unitUsage, String month, String year, String amount)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into bill(`id`,`userid`, `unitUsage`,`month`,`year`,`amount`)"+ " values (?, ?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, userid);
 preparedStmt.setString(3, unitUsage);
 preparedStmt.setString(4, month);
 preparedStmt.setString(5,year);
 preparedStmt.setString(6, amount);
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the item.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readBill()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>User Id</th><th>Unit Usage</th>"+"<th>Month</th>" + "<th>Year</th>" + "<th>Amount</th>" +"<th>Update</th><th>Remove</th></tr>";
 //output = "<table border='1'><tr><th>User Name</th><th>Contact Number</th>" + "<th>Email</th>" + "<th>Password</th>" +"<th>Update</th><th>Remove</th></tr>";

 String query = "select * from bill";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String id = Integer.toString(rs.getInt("id"));
 String userid = rs.getString("userid");
 String unitUsage = rs.getString("unitUsage");
 String month = rs.getString("month");
 String year = rs.getString("year");
 String amount = rs.getString("amount");
 // Add into the html table
 output += "<tr><td>" + userid + "</td>";
 output += "<td>" + unitUsage + "</td>";
 output += "<td>" + month + "</td>";
 output += "<td>" + year + "</td>";
 output += "<td>" + amount + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='bill.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"+ "<input name='id' type='hidden' value='" + id+ "'>" + "</form></td></tr>";}
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the items.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateBill(String id, String userid , String unitUsage, String month, String year, String amount)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE bill SET userid=?, unitUsage=?, month=?,year=?,amount=?WHERE id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, userid);
preparedStmt.setString(2, unitUsage);
preparedStmt.setString(3, month);
preparedStmt.setString(4, year);
preparedStmt.setString(5, amount);
preparedStmt.setInt(6, Integer.parseInt(id));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the item.";
System.err.println(e.getMessage());
}
return output;
}
public String deleteBill(String id)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from bill where id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(id));
// execute the statement
preparedStmt.execute();
con.close();
output = "Deleted successfully";
}
catch (Exception e)
{
output = "Error while deleting the item.";
System.err.println(e.getMessage());
}
return output;
}
} 

