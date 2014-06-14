package com.johnbrandenburg.myfirstapp.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;


public class Home extends ActionBarActivity {

EditText contactName, contactNumber, contactEmail, contactAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layouts);

        contactName = (EditText) findViewById(R.id.contactName);
        contactEmail = (EditText) findViewById(R.id.contactEmail);
        contactNumber = (EditText) findViewById(R.id.contactNumber);
        contactAddress = (EditText) findViewById(R.id.contactAddress);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        Button contactBtn = (Button) findViewById(R.id.addContact);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab_Create");
        tabSpec.setContent(R.id.tab_Create);
        tabSpec.setIndicator("Create");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab_List");
        tabSpec.setContent(R.id.tab_List);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);
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
