package c;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Score {
	
	private static final String JDBC_DRIVER = "org.h2.Driver";   
	private static final String DB_URL = "jdbc:h2:~/test"; 
	
	private static final String USER = "sa"; 
	private static final String PASS = ""; 
	
	private static Connection conn = null; 
	private static Statement stmt = null; 
	
	
	/*
	 *H2 table created as: 
	 * CREATE TABLE score (id int AUTO_INCREMENT, total INT, 
	 * correct INT, wrong INT,  PRIMARY KEY(id));
	 */
	private Score() { }
	private int correct = 0;
	private int incorrect = 0;
	private int total = 0;
	
	private static Score INSTANCE = null;
	
	public Score getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new Score();
		}
		return INSTANCE;
	}
	
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	
	public int getCorrect() {
		return this.correct;
	}
	
	public void setIncorrect(int inCorrect) {
		this.incorrect = inCorrect;
	}
	
	public int getIncorrect() {
		return this.incorrect;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	private static void connectToH2() {
		try {
	        // STEP 1: Register JDBC driver 
	        Class.forName(JDBC_DRIVER); 
	            
	        //STEP 2: Open a connection 
	        System.out.println("Connecting to database..."); 
	        conn = DriverManager.getConnection(DB_URL,USER,PASS); 
		} catch (Exception e) {
			System.err.println("Error! in connectToH2()");
		}
	}
	
	private static void disconnecFromH2() {
		try {
			stmt.close(); 
	        conn.close(); 
		} catch (Exception e) {
			System.err.println("Error! in disconnecFromH2()");
		}
	}
	
	private static void insertIntoH2(int id, int total, int correct, int wrong) {
		try {
			stmt = conn.createStatement(); 
		    String sql =  "INSERT INTO  scores " + 
		          "(total, correct, wrong) " +
		          "VALUES (" +
		          		total + 
		          		"," +
		          		correct +
		          		"," +
		          		wrong +
		          		" " +
		          		"); ";
		    System.out.println("SQL: "+ sql.toString());
		    
		       stmt.executeUpdate(sql);
		       stmt.close(); 
		} catch (Exception e) {
			e.printStackTrace(); 
			System.err.println("Error! in insertIntoH2");
		}
	}
	
	public static void main(String[] args) {
		try {
			
			connectToH2();
			
			int id=0;
			
			int total = 90;
			int correct = 10;
			int incorrect = 8;
			
			insertIntoH2(id,total, correct, incorrect);
			id += 1;
   
			
			disconnecFromH2();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
}


