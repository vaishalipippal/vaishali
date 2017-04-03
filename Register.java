package com.example.eventmanagement;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;




import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class Register extends Activity {
EditText name,password,phone,email;
Bundle username;
String newString;
String result;
int flag=0;
String return_emp_username,return_emp_password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
		  .penaltyLog().build());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		 
		Button login=(Button)findViewById(R.id.button1);

	login.setOnClickListener(new OnClickListener() {
 
			
			@Override
			public void onClick(View arg0) {
				ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
				 
				Boolean isInternetPresent = cd.isConnectingToInternet();
				if(isInternetPresent)
				{
 
					name=(EditText)findViewById(R.id.name);
				    
					password=(EditText)findViewById(R.id.password);
					phone=(EditText)findViewById(R.id.phone);
					email=(EditText)findViewById(R.id.email);
					
				new JSONAsyncTask().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please Check Your Networkd Connectivity", Toast.LENGTH_LONG).show();	 

		                

				}
			}
 
		});
 
	}
@SuppressLint("NewApi")
class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			dialog = new ProgressDialog(Register.this);
			dialog.setMessage("Loading, please wait");
			dialog.setTitle("Connecting server");
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
	          postParameters.add(new BasicNameValuePair("name",
	        		 name.getText().toString()));
	          
	          postParameters.add(new BasicNameValuePair("password",
	        		  password.getText().toString()));
	          postParameters.add(new BasicNameValuePair("phone",
	        		  phone.getText().toString()));
	          postParameters.add(new BasicNameValuePair("email",
		        		 email.getText().toString()));
	         
	          String response = null;
	          
	          // call executeHttpPost method passing necessary parameters 
	          try {
	        	  response = CustomHttpClient.executeHttpPost(
	       //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
	    "http://vaiandro.5gbfree.com/signup.php",  // in case of a remote server
	       postParameters);
	     
	     // store the result returned by PHP script that runs MySQL query
	    result = response.toString();  
	              
	      //parse json data
	        
	     
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
			
			

			
			
			

		}

		         
	}
	
private void loginfailed() {
	// TODO Auto-generated method stub
	AlertDialog alertDialog = new AlertDialog.Builder(
Register.this).create();

// Setting Dialog Title
alertDialog.setTitle("Event Management");

// Setting Dialog Message
alertDialog.setMessage("Details Entered successfully");


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

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    /** Create an option menu from res/menu/items.xml */
    getMenuInflater().inflate(R.menu.activity_main, menu);

    /** Get the action view of the menu item whose id is search */
   

    /** Setting an action listener */
    
   
    return super.onCreateOptionsMenu(menu);
}




	// Showing Alert Message
	
}


