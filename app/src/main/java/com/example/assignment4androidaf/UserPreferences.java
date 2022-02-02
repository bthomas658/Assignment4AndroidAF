package com.example.assignment4androidaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserPreferences extends AppCompatActivity {
    EditText input, input2, input3, input4;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        // A few input fields and a button is what we need
        input = (EditText) findViewById(R.id.userInput);
        input2 = (EditText) findViewById(R.id.userInput2);
        input3 = (EditText) findViewById(R.id.userInput3);
        input4 = (EditText) findViewById(R.id.userInput4);
        save = (Button) findViewById(R.id.saveButton);

        //When we click the button we store the input fields into the preferences file

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (input.getText().toString().trim().length() > 0)
                    editor.putString("input", input.getText().toString());
                if (input2.getText().toString().trim().length() > 0)
                    editor.putString("input2", input2.getText().toString());
                if (input3.getText().toString().trim().length() > 0)
                    editor.putString("input3", input3.getText().toString());
                if (input4.getText().toString().trim().length() > 0)
                    editor.putString("input4", input4.getText().toString());

                // editor.commit();
                editor.apply();

                //display the toast
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), "Data Saved", duration);
                toast.show();

                //clear the input fields
                input.setText("");
                input2.setText("");
                input3.setText("");
                input4.setText("");
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

        //Start display activity
        if (id == R.id.menuactivity2) {
            Intent intent = new Intent(getApplicationContext(), DisplayPreferences.class);
            startActivity(intent);
            return true;
        }

        //Clear the preferences file
        if (id == R.id.menuactivity3) {
            SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
