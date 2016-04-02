package com.example.smetrics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
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

public class ModPro extends Activity {
LinearLayout ll;
TextView tvv;
String st,tvtext;
int i,k;

public void exec(View v)
{
    tvv=(TextView)v;
	Toast.makeText(getBaseContext(), "loading..", Toast.LENGTH_SHORT).show();
	 Intent intent= new Intent(getBaseContext(), EditFile.class);
     intent.putExtra("sname", (tvv.getText().toString()));
     startActivity(intent);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.tryin, R.anim.tryout);
		setContentView(R.layout.activity_mod_pro);
		ll= (LinearLayout) findViewById(R.id.linearLayout1);
		i=0;
		DBAdapter db=new DBAdapter(this);
		db.open();
		Cursor c=db.getAllCurrentProject();
		if(c.moveToFirst())
		{
			do{
				
			TextView tv= new TextView(this);
            tv.setText(c.getString(0));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
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
	        MenuItem mnu3 = menu.add(0, 2, 2, "mark completed");
	        {
	            mnu3.setAlphabeticShortcut('c');
	            mnu3.setIcon(R.drawable.ic_launcher);
	        }
	        MenuItem mnu4 = menu.add(0, 3, 3, "add checkpoints");
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
	       	 Intent intent= new Intent(getBaseContext(), EditFile.class);
	            intent.putExtra("sname", (tvtext));
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
	        
	            {
	            	new AlertDialog.Builder(this)
	                .setTitle("Mark Project Complete")
	                .setMessage("Are you sure you want to mark "+tvtext+" complete?")
	                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) { 
	                    	compl(tvtext);
	                    }
	                 })
	                .setNegativeButton("LATER MAYBE", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) { 
	                        // do nothing
	                    }
	                 })
	                .setIcon(android.R.drawable.ic_dialog_alert)
	                 .show();
	            }
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
	boolean n=db.deleteProject(r);
	db.close();
	Toast.makeText(getBaseContext(), "Project deleted", Toast.LENGTH_SHORT).show();
	Intent i= new Intent(this,MainMenu.class);
	startActivity(i);
	}
public void compl(String r)
{
	DBAdapter db=new DBAdapter(getBaseContext());
	db.open();
	db.update(r);
	db.close();
	Toast.makeText(getBaseContext(), "Project Updated", Toast.LENGTH_SHORT).show();
	Intent i= new Intent(this,MainMenu.class);
	startActivity(i);
	}
}








