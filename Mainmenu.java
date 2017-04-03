package com.example.eventmanagement;

import java.util.ArrayList;

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
public class Mainmenu  extends Activity {
	Button button;
	
	 String username;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);
		 Intent intent = getIntent();
			username = intent.getStringExtra("username");
		 
		Button bookhotel =(Button)findViewById(R.id.bookhotel);
		bookhotel.setOnClickListener(new OnClickListener() {
 
			
			@Override
			public void onClick(View arg0) {
				 AlertDialog alertDialog = new AlertDialog.Builder(
						  Mainmenu.this).create();

						   // Setting Dialog Title
						   alertDialog.setTitle("Event Management");

						   // Setting Dialog Message
						   alertDialog.setMessage("Welcome To Booking Hotel!");


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
				Intent launchactivity= new Intent(Mainmenu.this,Hotel.class); 
				//Toast.makeText(getApplicationContext(), "Welcome To Booking Hotel", Toast.LENGTH_LONG).show();
				launchactivity.putExtra("username",username );
				startActivity(launchactivity);  
				 
			}
 
		});

		Button delete=(Button)findViewById(R.id.delete);
		delete.setOnClickListener(new View.OnClickListener() {                                            
		    public void onClick(View v) 
		                   {   
		    	 AlertDialog alertDialog = new AlertDialog.Builder(
						  Mainmenu.this).create();

						   // Setting Dialog Title
						   alertDialog.setTitle("Event Management");

						   // Setting Dialog Message
						   alertDialog.setMessage("Welcome To Delete Booked Hotel!");


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
		    	//Toast.makeText(getApplicationContext(), "register", Toast.LENGTH_LONG).show();
		Intent launchactivity= new Intent(Mainmenu.this,DeleteBooking.class); 
	//	Toast.makeText(getApplicationContext(), "Welcome To Delete Booking", Toast.LENGTH_LONG).show();
		launchactivity.putExtra("username",username );
		startActivity(launchactivity);                          
		                     }
		                      }); 
 
	
	Button nearbyhotel=(Button)findViewById(R.id.search);
	nearbyhotel.setOnClickListener(new View.OnClickListener() {                                            
	    public void onClick(View v) 
	                   {   
	    	 AlertDialog alertDialog = new AlertDialog.Builder(
					  Mainmenu.this).create();

					   // Setting Dialog Title
					   alertDialog.setTitle("Event Management");

					   // Setting Dialog Message
					   alertDialog.setMessage("Welcome To Search Near By Hotel!");


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
	    //	Toast.makeText(getApplicationContext(), "Welcome To Search Near By Hotel", Toast.LENGTH_LONG).show();
	Intent launchactivity= new Intent(Mainmenu.this,SearchHotel.class);
	
	startActivity(launchactivity);                          
	                     }
	                      }); 
	}

 
	}
 
	