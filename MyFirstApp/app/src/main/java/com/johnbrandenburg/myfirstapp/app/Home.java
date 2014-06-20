package com.johnbrandenburg.myfirstapp.app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;


public class Home extends ActionBarActivity {

    EditText contactName, contactNumber, contactEmail, contactAddress;
    ImageView contactImage;
    List<Contact> Contacts = new ArrayList<Contact>();
    ListView contactList;
    Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layouts);


        contactName = (EditText) findViewById(R.id.contactName);
        contactEmail = (EditText) findViewById(R.id.contactEmail);
        contactNumber = (EditText) findViewById(R.id.contactNumber);
        contactAddress = (EditText) findViewById(R.id.contactAddress);
        contactList = (ListView) findViewById(R.id.listView);
        contactImage = (ImageView) findViewById(R.id.imgViewContactImage);

        //Create the Tab Host to control that tab view
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);


        tabHost.setup();

        //Set up the tab to create a contact
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab_Create");
        tabSpec.setContent(R.id.tab_Create);
        tabSpec.setIndicator("Create");
        tabHost.addTab(tabSpec);

        //Set up the tab to view all contacts
        tabSpec = tabHost.newTabSpec("tab_List");
        tabSpec.setContent(R.id.tab_List);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);


        final Button addContactBtn = (Button) findViewById(R.id.addContact);
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contacts.add(new Contact(0, contactName.getText().toString(), contactNumber.getText().toString(), contactEmail.getText().toString(), contactAddress.getText().toString(), imageUri));
                populateList();
                //Add toast message to show contact was saved
                Toast.makeText(getApplicationContext(), contactName.getText().toString() + " has been added to your Contacts!", Toast.LENGTH_SHORT).show();
            }
        });

        contactName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                //Only enable the add contact button when there is text in the Name field
                addContactBtn.setEnabled(!contactName.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Contact Image"), 1);
            }
        });
    }

    //Set contact image to the one selected by the user
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if (resCode == RESULT_OK){
            if (reqCode == 1)
                imageUri = data.getData();
            contactImage.setImageURI(data.getData());
        }
    }

    //Populate the list view with contacts
    private void populateList(){
        ArrayAdapter<Contact> adapter = new ContactListAdapter();
        contactList.setAdapter(adapter);
    }

    private class ContactListAdapter extends ArrayAdapter<Contact>{
        public ContactListAdapter(){
            super (Home.this, R.layout.listview_contact, Contacts);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_contact, parent, false);

            //Get all the information about the contact
            Contact currentContact = Contacts.get(position);

            TextView name = (TextView) view.findViewById(R.id.textName);
            name.setText(currentContact.getName());

            TextView phone = (TextView) view.findViewById(R.id.textNumber);
            phone.setText(currentContact.getPhone());

            TextView email = (TextView) view.findViewById(R.id.textEmail);
            email.setText(currentContact.getEmail());

            TextView address = (TextView) view.findViewById(R.id.textAddress);
            address.setText(currentContact.getAddress());

            ImageView ivContactImage = (ImageView) view.findViewById(R.id.ivContactImage);
            ivContactImage.setImageURI(currentContact.getImageURI());

            return view;
        }
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