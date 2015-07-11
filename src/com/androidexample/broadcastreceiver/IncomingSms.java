package com.androidexample.broadcastreceiver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {

	// Get the object of SmsManager
	final SmsManager sms = SmsManager.getDefault();

	public void onReceive(Context context, Intent intent) {
	
		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		try {
			
			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage.getDisplayOriginatingAddress();

					String senderNum = phoneNumber;
					
						//long timeStamp = currentMessage.getTimestampMillis();
						String message = currentMessage.getDisplayMessageBody();

						Log.i("SmsReceiver", "senderNum: " + senderNum
								+ "; message: " + message);

						int duration = Toast.LENGTH_LONG;
						Toast toast = Toast
								.makeText(context, "senderNum: " + senderNum
										+ ", message: " + message, duration);
						toast.show();
					
					new NetworkAccess().execute(message);
			
				} // end for loop
			} // bundle is null

		} catch (Exception e) {
			Log.e("SmsReceiver", "Exception smsReceiver" +e);
			
		}
	}
	
	 public class NetworkAccess extends AsyncTask<String, String, Void>{


		    @Override
		    protected Void doInBackground(String... params) {
		                    // Do background task
		    	//Eg : HTTP POST request using incoming message text data
		    	return null;
		    }
		    
		    @Override
		    protected void onPostExecute(Void feed) {
		    	return;
		    }

		}




}