package com.example.assignment4androidaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_preferences);
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);


        //add the data from the preferences file to the ArrayList
        ArrayList<String> list = new ArrayList<>();
        if (preferences.contains("input"))
            list.add(preferences.getString("input", ""));
        if (preferences.contains("input2"))
            list.add(preferences.getString("input2", ""));
        if (preferences.contains("input3"))
            list.add(preferences.getString("input3", ""));
        if (preferences.contains("input4"))
            list.add(preferences.getString("input4", ""));
        //create the array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, list);

        //set the list to the adapter
        final ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        //set the empty view
        TextView emptyView = (TextView) findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);

        //need to set the On Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) listView.getItemAtPosition(position);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), selectedItem, duration);
                toast.show();
            }



        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_screens, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuactivity1) {
            Intent intent = new Intent(getApplicationContext(), UserPreferences.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menuactivity3) {

            //clear the preferences file
            SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            //empty out the array and view now that you deleted the file data
            ArrayList<String> list = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row_item, R.id.listItem, list);
            final ListView listView = (ListView) findViewById(android.R.id.list);
            listView.setAdapter(adapter);
            TextView emptyView = (TextView) findViewById(android.R.id.empty);
            listView.setEmptyView(emptyView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
