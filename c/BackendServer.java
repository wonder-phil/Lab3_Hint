package c;


import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.*;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.Resource;
import org.restlet.resource.ServerResource;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;  


public class BackendServer extends ServerResource {  
	
   private static int count = 0;
   private static String json_string;
   private static ArrayList<QuestionAnswer> allQuestionsAnswers = null;
   private static GsonBuilder builder = new GsonBuilder();     
   private static Gson gson = builder.create(); 
   
   public static void main(String[] args) throws Exception {  
	   
	   builder.setPrettyPrinting();
	   
	   AllQuestions allQuestions = new AllQuestions();
	   
	   allQuestionsAnswers = allQuestions.getAllQuestionsAnswers();
	   
       // Create the HTTP server and listen on port 8182 
	  new Server(Protocol.HTTP, 8182, BackendServer.class).start();    
   }
   
   /*
    * curl http://localhost:8182
    */
   @Get  
   public String toString() {  
	  count++;
	  count = count % allQuestionsAnswers.size();
	  QuestionAnswer questionAnswer = allQuestionsAnswers.get(count);
	  json_string = gson.toJson(questionAnswer);
      return  json_string;  // "Question " + Integer.valueOf(count).toString() + ": " +
   }
   
   /*
    * curl -v -d "Hello=YEAH" -X  POST "http://localhost:8182" -H "Content-type: text/html; charset=UTF-8"
    */
   @Post
   public Response addScore(Representation r) {
	   String s;
	   Request request = new Request();
	
	   try {
		s = r.getText();
		System.out.println("s: " + s);
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }

	   Response response = new Response(request);
	   return response;
   }
	
   /*
    * curl -v -d "Hello=YEAH" -X  PUT "http://localhost:8182" -H "Content-type: text/html; charset=UTF-8"
    */
   @Put
   public Response updateScore(Representation r) {
       String s;
	   
	try {
		s = r.getText();
		System.out.println("s: " + s);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Request request = new Request();
	Response response = new Response(request);
       
    return response;
   }
 
}
   
 
