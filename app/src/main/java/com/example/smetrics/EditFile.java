package com.example.smetrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditFile extends Activity {
int i;
LinearLayout ll;
DBAdapter db;
String sname;
TextView tvv;
String st,tvtext;
int k;

public void exec(View v)
{
    tvv=(TextView)v;
	Toast.makeText(getBaseContext(), "loading..", Toast.LENGTH_SHORT).show();
	 Intent intent= new Intent(getBaseContext(), YourModule.class);
     intent.putExtra("mname", (tvv.getText().toString()));
     intent.putExtra("pname", sname);
     startActivity(intent);

	}
public void addt(View v)
{
	Intent i=new Intent(this,NewModule.class);
 i.putExtra("sname", sname);
  startActivity(i);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		setContentView(R.layout.activity_edit_file);
		sname=  getIntent().getStringExtra("sname");
		ll= (LinearLayout) findViewById(R.id.linearLayout1);
		
		 db=new DBAdapter(this);
		
		 db.open();
		 Cursor c= db.getProject(sname);

		 i=0;
		 if(c.moveToFirst())
		 {
		 	
		 	do{
		 		
		 	TextView tv= new TextView(this);
		     tv.setText(c.getString(0));
		     tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
		     tv.setId(i + 5);
		     tv.setOnClickListener(new OnClickListener() {

	                @Override
	                public void onClick(View v) {
	                	
	                  exec(v);
	                }
	            });
			    tv.setOnCreateContextMenuListener(this);
		     ll.addView(tv);
		     i++;}while(c.moveToNext());
		 }		
		 
		 db.close();
		
	}
		
	@Override
    public void onCreateContextMenu(ContextMenu menu, View view, 
    ContextMenuInfo menuInfo) 
    {
    
         super.onCreateContextMenu(menu, view, menuInfo);
         tvv=(TextView) view;
         tvtext=tvv.getText().toString();
         CreateMenu(menu);
    }
	public boolean onContextItemSelected(MenuItem item)
    {    
         return MenuChoice(item);    
    } 
	 private void CreateMenu(Menu menu)
	    {
	    	menu.setQwertyMode(true);
	        MenuItem mnu1 = menu.add(0, 0, 0, "view");
	        {
	            mnu1.setAlphabeticShortcut('a');
	            mnu1.setIcon(R.drawable.ic_launcher);            
	        }
	        MenuItem mnu2 = menu.add(0, 1, 1, "delete");
	        {
	            mnu2.setAlphabeticShortcut('b');
	            mnu2.setIcon(R.drawable.ic_launcher);            
	        }
	        MenuItem mnu3 = menu.add(0, 2, 2, "open SRS document");
	        {
	            mnu3.setAlphabeticShortcut('c');
	            mnu3.setIcon(R.drawable.ic_launcher);
	        }
	        MenuItem mnu4 = menu.add(0, 3, 3, "Open Code");
	        {
	            mnu4.setAlphabeticShortcut('d');                    
	        }
	       
	    }
	 
	    private boolean MenuChoice(MenuItem item)
	    {        
	        switch (item.getItemId()) {
	        case 0:
	        {
	        	Toast.makeText(getBaseContext(), "loading..", Toast.LENGTH_SHORT).show();
	       	 Intent intent= new Intent(getBaseContext(), YourModule.class);
	            intent.putExtra("mname", (tvtext));
	            intent.putExtra("pname", sname);
	            startActivity(intent);
	        }
	            return true;
	        case 1:
	            {
	            	new AlertDialog.Builder(this)
	                .setTitle("Delete entry")
	                .setMessage("Are you sure you want to delete "+tvtext+" ?")
	                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) { 
	                    	del(tvtext);
	                    }
	                 })
	                .setNegativeButton("NOPE", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) { 
	                        // do nothing
	                    }
	                 })
	                .setIcon(android.R.drawable.ic_dialog_alert)
	                 .show();
	            }
	            return true;
	        case 2:
	        	Toast.makeText(this, "You clicked on Item 3", 
		                Toast.LENGTH_LONG).show();
		            return true;
	        
	           
	        case 3:
	            Toast.makeText(this, "You clicked on Item 4", 
	                Toast.LENGTH_LONG).show();
	            return true;
	      
	        }
	        return false;
	    }    
public void del(String r)
{
	DBAdapter db=new DBAdapter(getBaseContext());
	db.open();
	boolean n=db.deleteModule(sname, r);
	db.close();
	Toast.makeText(getBaseContext(), "Module deleted", Toast.LENGTH_SHORT).show();
	Intent i= new Intent(this,MainMenu.class);
	startActivity(i);
	}



}
	
	

