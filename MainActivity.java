package com.example.eventmanagement;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;












import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends Activity {
EditText emp_username,emp_password;
		Boolean isInternetPresent = false;

String result;
int flag=0;
String return_emp_username,return_emp_password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
		  .penaltyLog().build());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button login=(Button)findViewById(R.id.loginbutton);

	login.setOnClickListener(new OnClickListener() {
 
			
			@Override
			public void onClick(View arg0) {
				 ConnectionDetector cd;
				 cd = new ConnectionDetector(getApplicationContext());
				 isInternetPresent = cd.isConnectingToInternet();
				 
	                // check for Internet status
	                if (isInternetPresent) {
	                    // Internet Connection is Present
	                    // make HTTP requests
	                	emp_username=(EditText)findViewById(R.id.username);
	   				 emp_password=(EditText)findViewById(R.id.password);
	   				new JSONAsyncTask().execute();
	                    
	                } else {
	                    // Internet connection is not present
	                    // Ask user to connect to Internet
	                	Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                    	
	                }
 
				 
			}
 
		});
 
	Button register=(Button)findViewById(R.id.register);
	register.setOnClickListener(new View.OnClickListener() {                                            
	    public void onClick(View v) 
	                   {   
	    	//Toast.makeText(getApplicationContext(), "register", Toast.LENGTH_LONG).show();
	Intent launchactivity= new Intent(MainActivity.this,Register.class);                               
	startActivity(launchactivity);                          
	                     }
	                      });  
 
	}

	
@SuppressLint("NewApi")
class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			dialog = new ProgressDialog(MainActivity.this);
			
			dialog.setTitle("Connecting server");
			dialog.setMessage("Loading, please wait");
			dialog.show();
			dialog.setCancelable(false);
		}
		
		@TargetApi(Build.VERSION_CODES.GINGERBREAD)
		@SuppressLint("NewApi")
		@Override
		protected Boolean doInBackground(String... urls) {
			boolean res=false;
			// declare parameters that are passed to PHP script 
	          ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	          
	          // define the parameter
	          postParameters.add(new BasicNameValuePair("username",
	        		  emp_username.getText().toString()));
	          postParameters.add(new BasicNameValuePair("password",
	        		  emp_password.getText().toString()));
	          String response = null;
	          
	          // call executeHttpPost method passing necessary parameters 
	          try {
	     response = CustomHttpClient.executeHttpPost(
	       //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
	    		 "http://vaiandro.5gbfree.com/login.php",  // in case of a remote server
	       postParameters);
	     
	     // store the result returned by PHP script that runs MySQL query
	    result = response.toString();  
	              
	      //parse json data
	         try{
	           String      returnString = "";
	           JSONArray jArray = new JSONArray(result);
	                 for(int i=0;i<jArray.length();i++){
	                         JSONObject json_data = jArray.getJSONObject(i);
	                         return_emp_username=json_data.getString("username").trim();
	                         return_emp_password=json_data.getString("password").trim();
	                         if((emp_username.getText().toString().equals(return_emp_username))&&(emp_password.getText().toString().equals(return_emp_password)))
	                         {
	                        	flag=1;
	                        	
	                        	
	                         }
	                        
	                         
	                       
	                 }
	         }
	         catch(JSONException e){
	                 Log.e("log_tag", "Error parsing data "+e.toString());
	         }
	     
	    }
	          catch (Exception e) {
	     Log.e("log_tag","Error in http connection!!" + e.toString());     
	    }
	          
	 	     return res;		
		}
		
		protected void onPostExecute(Boolean results) {
			dialog.cancel();
			
			if((flag!=1)&&(results==false))
			{
				loginfailed();
			}
			if(flag==1)
			{
				Toast toast = Toast.makeText(MainActivity.this,"Welcome To MainMenu", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			//	Toast.makeText(getApplicationContext(), "Welcome To MainMenu", Toast.LENGTH_LONG).show();
				flag=0;
			Intent i=new Intent(MainActivity.this,Mainmenu.class);
			i.putExtra("username", emp_username.getText().toString());
			startActivity(i);
			}
			

			
			
			

		}

		         
	}
	
private void loginfailed() {
	// TODO Auto-generated method stub
	AlertDialog alertDialog = new AlertDialog.Builder(
            MainActivity.this).create();

// Setting Dialog Title
alertDialog.setTitle("Event Management");

// Setting Dialog Message
alertDialog.setMessage("User Not Registered");


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
	
