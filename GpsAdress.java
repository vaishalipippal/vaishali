package com.example.eventmanagement;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GpsAdress extends Activity {

	private Button myLocation;
	private TextView myAddress;
	public String lats;
	public String longis;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		Intent i = getIntent();
		
		Bundle bundle =i.getExtras();
		
		lats=		i.getStringExtra("key");
		longis=	i.getStringExtra("pair");
		Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + lats + "\nLong: " + longis, Toast.LENGTH_LONG).show();
		//myLocation= (Button) findViewById(R.id.location);
	//	myAddress = (TextView)findViewById(R.id.address);
	
		myLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getMyLocationAddress();
			}
		});
		
	}
	

	public void getMyLocationAddress() {
		
		Geocoder geocoder= new Geocoder(this, Locale.ENGLISH);
		
		try {
        	  
			  //Place your latitude and longitude
			  List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(lats),Double.parseDouble(longis), 1);
        	 
        	  if(addresses != null) {
        	  
        		  Address fetchedAddress = addresses.get(0);
        		  StringBuilder strAddress = new StringBuilder();
        	   
        		  for(int i=0; i<fetchedAddress.getMaxAddressLineIndex(); i++) {
        			  	strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
        		  }
        	   
        		  myAddress.setText("I am at: " +strAddress.toString());
        	  
        	  }
        	  
        	  else
        		  myAddress.setText("No location found..!");
         
        } 
		catch (IOException e) {
        		 // TODO Auto-generated catch block
        		 e.printStackTrace();
        		 Toast.makeText(getApplicationContext(),"Could not get address..!", Toast.LENGTH_LONG).show();
		}
	}
	
}

