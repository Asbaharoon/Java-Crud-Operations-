

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import com.mysql.cj.xdevapi.Statement;

public class JDBC_CRUD {
public static void main(String args[]) throws NumberFormatException, IOException,SQLException, ClassNotFoundException
{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int StudentNumber;
	int no;
	while(true)
	{
		System.out.println("Enter 1 to insert student data \nEnter 2 to Update student data \nEnter 3 to Delete student data\nEnter 4 to get a list of Students \nEnter 5 to get stuent information using student id filter\nEnter 6 to Exit");
		 no=Integer.parseInt(br.readLine());
	

	switch(no)
	{
	case 1:
				
				String DOB="";
				String DOJ="";             
				System.out.println("Enter a three digit student Number  ");
			    StudentNumber=Integer.parseInt(br.readLine());
			    System.out.println("Enter Name");
			    String StudentName=br.readLine();
			    System.out.println("Enter Date of Birth of Student in the format date/month/year");
			    DOB=br.readLine();

			    System.out.println("Enter Date of Joining for Student in the format date/month/year");
			    DOJ=br.readLine();
	
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","6756");
				    PreparedStatement ps=(PreparedStatement) con.prepareStatement("INSERT INTO student VALUES(?,?,?,?)");
				    ps.setInt(1,StudentNumber);
				    ps.setString(2,StudentName);
				    ps.setString(3,DOB);
				    ps.setString(4, DOJ);
				  int  i= ps.executeUpdate();
				  if(i>0)
				  {
					  System.out.println("Record Added Successfully\n\n");
					  con.close();
				  }
				  else
					  System.out.println("Record is not Added\\n\\n");
				  	
				}
				catch(SQLException e)
				{
					System.out.println(e);
				}
				
				break;
	case 2:
				String DOOB="";
				String DOOJ="";             
				System.out.println("Enter a student no whose record you want to update ");
			    StudentNumber=Integer.parseInt(br.readLine());
			    System.out.println("Enter Name");
			    String UpdateName=br.readLine();
			    System.out.println("Enter Date of Birth of Student in the format date/month/year");
			    DOOB=br.readLine();

			    System.out.println("Enter Date of Joining for Student in the format date/month/year");
			    DOOJ=br.readLine();
			    
			    try
				{
			    
			    
			    	Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","6756");
			    	PreparedStatement st=(PreparedStatement) con.prepareStatement("update student set STUDENT_NAME=?,STUDENT_DOB=?,STUDENT_DOJ=? where STUDENT_NO=?");
			    	st.setString(1,UpdateName);
				    st.setString(2,DOOB);
				    st.setString(3, DOOJ);
				    st.setInt(4, StudentNumber);
				    int  i= st.executeUpdate();
					  if(i>0)
					  {
						  System.out.println("Record Updated Successfully\n\n");
						  con.close();
					  }
					  else
						  System.out.println("Record is not Updated\n\n");
					  	
					}
				
				
				
				catch(SQLException e)
				{
					System.out.println(e);
				}
			    	break;    
	
	case 3:
		        
		       System.out.println("Enter name of Student  whose name is to be deleted");
		       String nm=br.readLine();
		       try
		       {
		    	   Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","6756");
			       PreparedStatement st=(PreparedStatement) con.prepareStatement("delete from student where STUDENT_NAME=?");
			       st.setString(1, nm);
				    int  i= st.executeUpdate();
				    if(i>0)
					  {
						  System.out.println("Record Deleted Successfully\\n");
						  con.close();
					  }
					  else
						  System.out.println("Record is not Deleted\\n");
					  	
					}
				
		       
		       catch(SQLException e)
				{
					System.out.println(e);
				}
		       
		       break;
	case 4:
	       		try
	       		{
	       			Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","6756");
	       			java.sql.Statement stmt =  con.createStatement();
	       			ResultSet rs =  stmt.executeQuery("select * from student");
	       			
	       			System.out.println("List of Students is :\n ");
	       	      while(rs.next()) {
	       	         System.out.println(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4));
	       	         System.out.println();
	       	      }
	       		}
				
			       
			       catch(SQLException e)
					{
						System.out.println(e);
					}
			       
		      break;
		      
	case 5:   
		      System.out.println("Enter id of Student whose information you want");
		      String sid =br.readLine();
		  	try
       		{
		  		Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","6756");
				 Statement st=(Statement) con.createStatement();

				 String qry="select * from student where STUDENT_NO like '"+sid+"%'";
				 
				 ResultSet rss=((java.sql.Statement) st).executeQuery(qry);

				 System.out.println("STUDENT_NO"+"     "+"STUDENT_NAME"+"     "+"STUDENT_DOB"+"     "+"STUDENT_DOJ");

				 while(rss.next())
				 {

					 System.out.print(rss.getString(1));		
					 System.out.print("     "+rss.getString(2));
					 System.out.print("     "+rss.getString(3));
					 System.out.print("     "+rss.getString(4));

					
				 }
		     
//			    if(i>0)
//				  {
//					  System.out.println("Record Deleted Successfully\n");
//					  con.close();
//				  }
//				  else
//					  System.out.println("Record is not Deleted\n");
//				  	
//				}
       		}
		  	catch(SQLException e)
			{
				System.out.println(e);
			}
		  	break;
	case 6:
		   System.exit(0);

	 default:
					System.out.println("Please Enter a valid choice");
	       
	}
	
	
	
}

}
}
