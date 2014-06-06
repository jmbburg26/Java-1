package com.johnbrandenburg.myfirstapp.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Home extends ActionBarActivity {

    Button calcButton;
    TextView resultsView;
    EditText totalAdults;
    EditText totalTeens;
    EditText totalChildren;
    Boolean  goodDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layouts);

        //Find my view elements
        calcButton  =   (Button)    findViewById(R.id.calcButton);
        resultsView =   (TextView)  findViewById(R.id.resultView);
        totalAdults =   (EditText)  findViewById(R.id.totalAdults);
        totalTeens  =   (EditText)  findViewById(R.id.totalTeens);
        totalChildren = (EditText)  findViewById(R.id.totalChildren);
        goodDay = false;

        //Add functions to run when the button is pressed
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get values of people inputted
                String numAdults = totalAdults.getText().toString();
                int finalAdults = Integer.parseInt(numAdults);

                String numTeens = totalTeens.getText().toString();
                int finalTeens = Integer.parseInt(numTeens);

                String numChildren = totalChildren.getText().toString();
                int finalChildren = Integer.parseInt(numChildren);

                //Add all input values and calculate total number of people present
                int totalPresent = finalAdults + finalChildren + finalTeens;

                //Display the results of people enter into the counter
                //resultsView.setText("There are " + finalAdults + " adults, " + finalTeens + " teens, and " + finalChildren + " children" + " at church today.");
                //resultsView.setText("There are " + totalPresent + " people in church today.");

                //If statement to determine if you had a good day in church
                if (totalPresent >= 100)
                {
                    goodDay = true;
                    //Add alert to tell user that they had a great day
                    resultsView.setText("You had a great day!");

                }else{
                    //Add alert to tell user that they had a good day
                    resultsView.setText("You had a good day!");
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
