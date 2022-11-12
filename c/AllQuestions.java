package c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AllQuestions {

	static final String JDBC_DRIVER = "org.h2.Driver";   
	static final String DB_URL = "jdbc:h2:~/test"; 
	
	static final String USER = "sa"; 
	static final String PASS = ""; 
	
	ArrayList<QuestionAnswer> questionArrayList = new ArrayList<QuestionAnswer>();
		

	public ArrayList<QuestionAnswer> getAllQuestionsAnswers() {	
		Connection conn = null; 
	    Statement stmt = null; 
	    try { 
	       Class.forName(JDBC_DRIVER); 
	     
	       conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	    
	    
	       String sql_select =  "SELECT * FROM QUESTIONS;";
	       stmt = conn.createStatement();  
	       
	       ResultSet rs = stmt.executeQuery(sql_select);
	       while (rs.next()) {
	    	   int id = rs.getInt("id");
	    	   String actualQuestion = rs.getString("question");
	    	   String actualAnswer = rs.getString("answer");
	    	   QuestionAnswer question = new QuestionAnswer(id,actualQuestion, actualAnswer);
	    	   questionArrayList.add(question);
	       }
	           
	       stmt.close(); 
	       conn.close();
	    } catch(SQLException se) { 
	        //Handle errors for JDBC 
	        se.printStackTrace(); 
	     } catch(Exception e) { 
	        //Handle errors for Class.forName 
	        e.printStackTrace(); 
	     } finally { 
	        //finally block used to close resources 
	        try{ 
	           if(stmt!=null) stmt.close(); 
	        } catch(SQLException se2) { 
	        } // nothing we can do 
	        try { 
	           if(conn!=null) conn.close(); 
	        } catch(SQLException se){ 
	           se.printStackTrace(); 
	        } //end finally try 
	     } //end try 
	    
		return questionArrayList;
	}
	
}
