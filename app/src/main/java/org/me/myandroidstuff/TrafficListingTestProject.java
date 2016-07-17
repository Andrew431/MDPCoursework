package org.me.myandroidstuff;

//Andrew Muir
//Matric number - S1511342

import org.me.myandroidstuff.Data.Item;
import org.me.myandroidstuff.Listeners.ListListener;
import org.me.myandroidstuff.Util.Reader;
import java.util.List;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;



public class TrafficListingTestProject extends Activity implements View.OnClickListener {

	private TrafficListingTestProject local;

	TextView Displaytext;
	TextView day;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		Displaytext = (TextView)findViewById(R.id.Displaytext);
		Displaytext.setText("Parsed data will appear below");

		day = (TextView)findViewById(R.id.Day);

		Button button = (Button)findViewById(R.id.Button);
		button.setOnClickListener(this);


	} // end of onCreate

	public void onClick(View v) {




		}


	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();


		// Check which radio button was clicked
		switch(view.getId()) {
			case R.id.Roadworks:
				if (checked) {
					local = this;
					GetRSSDataTask task = new GetRSSDataTask();
					task.execute("http://www.trafficscotland.org/rss/feeds/roadworks.aspx");
					// Debug the thread name
					Log.d("RssReader", Thread.currentThread().getName());

					Displaytext.setText("Roadworks");
					break;
				}


			case R.id.Planned:
				if (checked) {
					local = this;
					GetRSSDataTask task = new GetRSSDataTask();
					task.execute("http://www.trafficscotland.org/rss/feeds/plannedroadworks.aspx");
					// Debug the thread name
					Log.d("RssReader", Thread.currentThread().getName());

					Displaytext.setText("Planned roadworks");
					break;
				}
		}
	} // end of onRadioButtonClicked



	private class GetRSSDataTask extends AsyncTask<String, Void, List<Item>> {
		@Override
		protected List<Item> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("RssReader", Thread.currentThread().getName());

			try {
				// Create RSS reader
				Reader rssReader = new Reader(urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("RssReader", e.getMessage());
			}

			return null;
		}

		protected void onPostExecute(List<Item> result) {

			// Get a ListView from main view
			ListView itcItems = (ListView) findViewById(R.id.ListView);

			// Create a list adapter
			ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(local, android.R.layout.simple_list_item_1, result);
			// Set list adapter for the ListView
			itcItems.setAdapter(adapter);

			// Set list view item click listener
			itcItems.setOnItemClickListener(new ListListener(result, local));
		}

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);//Menu Resource, Menu
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.close:
				Toast.makeText(getApplicationContext(), "Closing app", Toast.LENGTH_LONG).show();
				System.exit(0);
				return true;

			case R.id.item2:
				Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
				return true;

			case R.id.item3:
				Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
























