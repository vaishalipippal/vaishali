package com.example.eventmanagement;

import java.util.ArrayList;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



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

public class Hotel  extends Activity implements OnItemSelectedListener {
	 Spinner spinnerOsversions;
	 
	 String selState;
	 TextView selVersion;
	 EditText date,time;
	 Button button,button2,button1;
	 String response,hotel;
	 String username;
	 String returnString,return_date,return_hotel;
	
		int flag;
	
	 private String[] state = { "Anant residancy","Swati","Gokul garden","Rambha Puri" };
	 private String[] eventype={"Birthday","Farewell","Aniversary","Marriage"};

	  @SuppressLint("NewApi")
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.hotel);
	  Intent intent = getIntent();
		username = intent.getStringExtra("username");
	  System.out.println(state.length);
	  System.out.println(eventype.length);
	  selVersion = (TextView) findViewById(R.id.selVersion);
	  spinnerOsversions = (Spinner) findViewById(R.id.osversions);
	  Spinner spinnerevent = (Spinner) findViewById(R.id.oevent);
	  ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
	    android.R.layout.simple_spinner_item, state);
	  ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
              android.R.layout.simple_list_item_1,eventype);
adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinnerevent.setAdapter(adp1);
	  adapter_state
	    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  spinnerOsversions.setAdapter(adapter_state);
	  spinnerOsversions.setOnItemSelectedListener(this);

		button = (Button) findViewById(R.id.button1);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				 date=(EditText)findViewById(R.id.date);
				time=(EditText)findViewById(R.id.time);
			    
				 new JSONAsyncTask().execute( );
				 

			}

		});
		spinnerevent.setOnItemSelectedListener(new OnItemSelectedListener()
        {

          @Override
          public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) {
              // TODO Auto-generated method stub
  Toast.makeText(getBaseContext(),eventype[position], Toast.LENGTH_SHORT).show();

              }

             @Override
             public void onNothingSelected(AdapterView<?> arg0) {
             // TODO Auto-generated method stub

             }

        });
		
	  }

	  public void onItemSelected(AdapterView<?> parent, View view, int position,
	   long id) {
	  spinnerOsversions.setSelection(position);
	  selState = (String) spinnerOsversions.getSelectedItem();
	  selVersion.setText("Selected Function:" + selState);
	 }
	 
	  class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
			
			ProgressDialog dialog;
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(Hotel.this);
				dialog.setMessage("Loading, please wait");
				dialog.setTitle("Connecting server");
				dialog.show();
				dialog.setCancelable(false);
			}

			@Override
			protected Boolean doInBackground(String... urls) {
			
				 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		          // define the parameter
				 postParameters.add(new BasicNameValuePair("date",
						 date.getText().toString()));
				
				 postParameters.add(new BasicNameValuePair("time",
						 time.getText().toString()));
				
				 postParameters.add(new BasicNameValuePair("hotel",
						 selState));
				
				
				try {
					
					response = CustomHttpClient.executeHttpPost(
						       //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
						       "http://vaiandro.5gbfree.com/datebook.php",  // in case of a remote server
						       postParameters);
					Log.i("",response);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   
					     // store the result returned by PHP script that runs MySQL query
					     String result = response.toString();  
					    
					     
					
						 
					     
					      //parse json data
					         try{
					        	 
					            returnString = "";
					           JSONArray jArray = new JSONArray(result);
					           
					      
					           
					                 for(int i=0;i<jArray.length();i++){
					                         JSONObject json_data = jArray.getJSONObject(i);
					            return_date=json_data.getString("dates").trim();
					              return_hotel=json_data.getString("hotel").trim();
					           //   Toast.makeText(getApplicationContext(), "happy", Toast.LENGTH_LONG).show(); 
					                  if((date.getText().toString().equals(return_date))&&(selState.equals(return_hotel)))
					                         {
					                        	flag=1;
					                        	
					                	//  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show(); 
					                         }
					                         //Get an output to the screen
					                       
					                        
					                    
					                         
					                         
					                        	 
					                 }
					         }
					         catch(JSONException e){
					                 Log.e("log_tag", "Error parsing data "+e.toString());
					         }
					     
					         try{
					        //  tv.setText(returnString);
					         }
					         catch(Exception e){
					          Log.e("log_tag","Error in Display!" + e.toString());;          
					         }   
					    
					     return false;     
				
				
					
			}
			
			protected void onPostExecute(Boolean result) {
				dialog.cancel();
				
			   if(flag==1)
			    {
				   AlertDialog alertDialog = new AlertDialog.Builder(
						   Hotel.this).create();

						   // Setting Dialog Title
						   alertDialog.setTitle("Event Management");

						   // Setting Dialog Message
						   alertDialog.setMessage("Hotel Already Booked");


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
			//	Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG).show(); 
			    }
				///Intent moveIntent = new Intent(Hotel.this,Function.class);
			  
			//  Toast.makeText(getApplicationContext(), "Logged In user", Toast.LENGTH_SHORT).show();
			  
			  //  startActivity(moveIntent);
			  //  }
				
				else
				{
					 new JSONAsyncTask2().execute( );

					//perform code for insserting tht date hotel ect to database here
					//Toast.makeText(getApplicationContext(), "HOtel  booked", Toast.LENGTH_LONG).show(); 
					//Intent moveIntent = 
	                        // new Intent(Hotel.this,Function.class);
				//  
				//  Toast.makeText(getApplicationContext(), "Logged In user", Toast.LENGTH_SHORT).show();
				  
				   // startActivity(moveIntent);
				}
			    	
	       	
	       	
			}

			
	  }
	  class JSONAsyncTask2 extends AsyncTask<String, Void, Boolean> {
			
			ProgressDialog dialog;
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(Hotel.this);
				dialog.setMessage("Loading, please wait");
				dialog.setTitle("Connecting server");
				dialog.show();
				dialog.setCancelable(false);
			}

			@Override
			protected Boolean doInBackground(String... urls) {
				
				 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		          // define the parameter
				 postParameters.add(new BasicNameValuePair("date",
						 date.getText().toString()));
				
				 postParameters.add(new BasicNameValuePair("time",
						 time.getText().toString()));
				
				 postParameters.add(new BasicNameValuePair("hotel",
						 selState));
				 postParameters.add(new BasicNameValuePair("username",
						 username));
				 
				try {
					
					response = CustomHttpClient.executeHttpPost(
						       //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
						       "http://vaiandro.5gbfree.com/date.php",  // in case of a remote server
						       postParameters);
					Log.i("",response);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   
					     // store the result returned by PHP script that runs MySQL query
					     String result = response.toString();  
					    
					     
					
						 
					     
					      //parse json data
					       
					    
					     return false;     
				
				
					
			}
			
			protected void onPostExecute(Boolean result) {
				dialog.cancel();
				
				
				 AlertDialog alertDialog = new AlertDialog.Builder(
						   Hotel.this).create();

						   // Setting Dialog Title
						   alertDialog.setTitle("Event Management");

						   // Setting Dialog Message
						   alertDialog.setMessage("Hotel and Date Booked... Proceed with other details");


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
					//perform code for insserting tht date hotel ect to database here
			//		Toast.makeText(getApplicationContext(), "HOtel  booked", Toast.LENGTH_LONG).show(); 
						
					Intent moveIntent = 
	                         new Intent(Hotel.this,Function.class);
			 
				//  Toast.makeText(getApplicationContext(), "Logged In user", Toast.LENGTH_SHORT).show();
					moveIntent.putExtra("username", username);
				    startActivity(moveIntent);
	       	
	       	
			}

			
	  }
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	  
}

		
		


	 
