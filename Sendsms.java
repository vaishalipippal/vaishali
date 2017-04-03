package com.example.eventmanagement;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Sendsms extends Activity {
	 
		Button buttonSend;
		EditText textPhoneNo;
		
	 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.sms);
	 
			buttonSend = (Button) findViewById(R.id.buttonSend);
			textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
			
	 
			buttonSend.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View v) {
	 
				  String phoneNo = textPhoneNo.getText().toString();
				  String sms = "Your Hotel Booking is Done...Thanks For Booking";
	 
				  try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					 AlertDialog alertDialog = new AlertDialog.Builder(
							  Sendsms.this).create();

							   // Setting Dialog Title
							   alertDialog.setTitle("Event Management");

							   // Setting Dialog Message
							   alertDialog.setMessage("A Confirmation SMS Sent!");


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
				//	Toast.makeText(getApplicationContext(), "SMS Sent!",Toast.LENGTH_LONG).show();
				  } catch (Exception e) {
					Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				  }
	 
				}
			});
		}
	}
