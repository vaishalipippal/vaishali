package com.example.eventmanagement;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class SearchHotel extends Activity{


	Button btnShowLocation;
	double latitude;
	double longitude;
	boolean enabled;
	// GPSTracker class
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
        
      //  btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        
        
		// show location button click event
       // btnShowLocation.setOnClickListener(new View.OnClickListener() {
      /*  if (!enabled) {
        AlertDialog.Builder builder = new AlertDialog.Builder(donationcenter.this);
        builder.setMessage("Need GPS enabled!!")
         .setCancelable(false)
         .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialogInterface, int i) {
                 Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                 startActivity(intent);
                 finish();
             }
         }).create().show();
        return;
        }*/
       
        	GPSTracker gps   = new GPSTracker(SearchHotel.this);
			//@Override
			//public void onClick(View arg0) {		
				// create class object
		      

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	 latitude = gps.getLatitude();
		        	longitude = gps.getLongitude();
		        	
		        	// \n is for new line
		        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
		        	
		        	//as.setAltitudes(latitude,longitude);
		        	Intent i = new Intent(SearchHotel.this, GpsAdress.class);
		        	i.putExtra("key", String.valueOf(latitude));i.putExtra("pair", String.valueOf(longitude));
		        
		        	startActivity(i);
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
				
			
				//}
		//});
		      Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse("http://maps.google.com/maps?&saddr="+ latitude + "," + longitude + "&daddr=nearby hotel"));
					startActivity(intent);
		      //  String uri = "geo:"+ latitude + "," + longitude +"&q=child hospitals";
			//	startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
    }
    
    
}