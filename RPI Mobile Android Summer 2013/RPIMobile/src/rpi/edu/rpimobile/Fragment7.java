package rpi.edu.rpimobile;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import rpi.edu.rpimobile.model.Shuttle;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.AsyncTask.Status;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup; 
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
//Weather Fragment
public class Fragment7 extends SherlockFragment {
    
	private ArrayList<Shuttle> shuttles;
	private AsyncTask downloadtask;
	
	//Initial function
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//Inflate the layout into the parent view of container view of the parent class
		View rootView = inflater.inflate(R.layout.fragment7, container, false);
		
		//Allow this fragment to interact with the menu
		setHasOptionsMenu(true);
		
		//Point the shuttles variable to an arraylist of Shuttle objects
		shuttles = new ArrayList<Shuttle>();
		
		//download the shuttle data
		downloadtask = new Fragment7.Download().execute(5.0);
		
		return rootView;
	}
	
	private class Download extends AsyncTask<Double, Void, Boolean>{

		//before the thread is executed, set the action bar to show indetermine progress
		protected void onPreExecute(){
			getActivity().setProgressBarIndeterminateVisibility(Boolean.TRUE);
		}
		
		@Override
		protected Boolean doInBackground(Double... params) {
			
			logcat("Getting shuttle data...");
				
			Shuttle temp = new Shuttle();
			
			String data = "";
			
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet get = new HttpGet("http://shuttles.rpi.edu/vehicles/current.js");
				
				HttpResponse response = httpClient.execute(get);
				
				data = EntityUtils.toString(response.getEntity());
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			logcat("Shuttle Data: " + data);
			
			logcat("Finished getting shuttle data");
				
			return true;
		}
		
		protected void onPostExecute(Boolean results) {
			getActivity().setProgressBarIndeterminateVisibility(Boolean.FALSE);
		}
		
	}
	
	private void logcat(String logtext){
		if(PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("debugging", false))
			Log.d("RPI", logtext);
	}
	
}