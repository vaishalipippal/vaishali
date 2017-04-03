package com.example.eventmanagement;

import java.util.ArrayList;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.eventmanagement.MainActivity.JSONAsyncTask;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Function  extends Activity {
	EditText people,price;
	String username;
	String newString;
	Button button2;
	String result;
	int flag=0;
	String return_emp_username,return_emp_password;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			 
			super.onCreate(savedInstanceState);
			setContentView(R.layout.acomodation);
			Intent intent = getIntent();
			username = intent.getStringExtra("username");
			 
			Button login=(Button)findViewById(R.id.button1);

		login.setOnClickListener(new OnClickListener() {
	 
				
				@Override
				public void onClick(View arg0) {
					
	 
						people=(EditText)findViewById(R.id.people);
					    
						price=(EditText)findViewById(R.id.price);
						
					new JSONAsyncTask().execute();
					
				}
	 
			});
		Button sms=(Button)findViewById(R.id.button2);
		sms.setOnClickListener(new View.OnClickListener() {                                            
		    public void onClick(View v) 
		                   {   
		    	//Toast.makeText(getApplicationContext(), "register", Toast.LENGTH_LONG).show();
		Intent launchactivity= new Intent(Function.this,Sendsms.class);                               
		startActivity(launchactivity);                          
		                     }
		                      });  
		button2 = (Button) findViewById(R.id.button3);

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				Intent moveIntent = 
                        new Intent(Function.this,Code.class);
				moveIntent.putExtra("username", username);
				
			//  Toast.makeText(getApplicationContext(), "Logged In user", Toast.LENGTH_SHORT).show();
			 
			    startActivity(moveIntent);
			    
				
				 

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
				dialog = new ProgressDialog(Function.this);
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
		          postParameters.add(new BasicNameValuePair("people",
		        		 people.getText().toString()));
		          
		          postParameters.add(new BasicNameValuePair("price",
		        		  price.getText().toString()));
		         
		         
		          String response = null;
		          
		          // call executeHttpPost method passing necessary parameters 
		          try {
		        	  response = CustomHttpClient.executeHttpPost(
		       //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
		    "http://vaiandro.5gbfree.com/pricebooking.php",  // in case of a remote server
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
	Function.this).create();

	// Setting Dialog Title
	alertDialog.setTitle("Hotel Management");

	// Setting Dialog Message
	alertDialog.setMessage("Your Hotel has been booked successfully");


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

 
	

