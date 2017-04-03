package com.example.eventmanagement;



import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Code extends Activity{
	 String username;

		String returnString;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.random);
	        Intent intent = getIntent();
			username = intent.getStringExtra("username");
			getInfo();
}
	 @SuppressLint("NewApi")
	 private void getInfo() {
	 		// TODO Auto-generated method stub
	 		AlertDialog alertDialog = new AlertDialog.Builder(
	 	            Code.this).create();

	 	// Setting Dialog Title
	 	alertDialog.setTitle("Booking Code");

	 	
	 	
	 	// Setting Dialog Message
	 	 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    
	    // define the parameter
	    postParameters.add(new BasicNameValuePair("username",
	 username));
	    String response = null;
	    
	    
	 // call executeHttpPost method passing necessary parameters 
	    try {
	 response = CustomHttpClient.executeHttpPost(
	 //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
	 "http://vaiandro.5gbfree.com/details.php",  // in case of a remote server
	 postParameters);

	 //store the result returned by PHP script that runs MySQL query
	 String results = response.toString();  
	        
	 //parse json data
	   try{
	           returnString = "";
	     JSONArray jArray = new JSONArray(results);
	           for(int i=0;i<jArray.length();i++){
	                   JSONObject json_data = jArray.getJSONObject(i);
	                  
	                   //Get an output to the screen
	                  
	                   returnString += "\n" +"Date:\t\t"+ json_data.getString("dates") + "\n"+"Time:\t\t"+ json_data.getString("time")+ "\n"+"Hotel:\t\t"+ json_data.getString("hotel")+ "\n"+"Username:\t\t"+ json_data.getString("username")+ "\n"+"BookingCode:\t\t"+ json_data.getString("code") ;
	           
	           }
	   }
	   catch(JSONException e){
	           Log.e("log_tag", "Error parsing data "+e.toString());
	   }

	   
	 }
	    catch (Exception e) {
	 Log.e("log_tag","Error in http connection!!" + e.toString());     
	 }
	 	alertDialog.setMessage(returnString
	 			
	 			);

	 	// Setting Icon to Dialog
	 	//alertDialog.setIcon(R.drawable.tick);

	 	// Setting OK Button
	 	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	 	    public void onClick(DialogInterface dialog, int which) {
	 	    // Write your code here to execute after dialog closed
	 	    	
	 	    	
	 	   
	 	    }
	 	});

	 	// Showing Alert Message
	 	alertDialog.show();
	 	}
}