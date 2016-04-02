package com.example.smetrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FileList extends ListActivity {

	  private String path;
	  DBAdapter db;
	  int call,sc;
	  String p,m,line,str;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_file_list);
	    db=new DBAdapter(this);
	    sc=1;
	    call=getIntent().getIntExtra("callno", 3);
	    p=getIntent().getStringExtra("pname");
	    m=getIntent().getStringExtra("mname");
	    
	    // Use the current directory as title
	    path = "/";
	    if (getIntent().hasExtra("path")) {
	      path = getIntent().getStringExtra("path");
	    }
	    setTitle(path);

	    // Read all files sorted into the values-array
	    List values = new ArrayList();
	    File dir = new File(path);
	    if (!dir.canRead()) {
	      setTitle(getTitle() + " (inaccessible)");
	    }
	    String[] list = dir.list();
	    if (list != null) {
	      for (String file : list) {
	        if (!file.startsWith(".")) {
	          values.add(file);
	        }
	      }
	    }
	    Collections.sort(values);

	    // Put the data into the list
	    ArrayAdapter adapter = new ArrayAdapter(this,
	        android.R.layout.simple_list_item_2, android.R.id.text1, values);
	    setListAdapter(adapter);
	  }

	  @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	    String filename = (String) getListAdapter().getItem(position);
	    if (path.endsWith(File.separator)) {
	      filename = path + filename;
	    } else {
	      filename = path + File.separator + filename;
	    }
	    if (new File(filename).isDirectory()) {
	      Intent intent = new Intent(this, FileList.class);
	      intent.putExtra("callno",call);
	      intent.putExtra("mname", m);
	      intent.putExtra("pname", p);
	      intent.putExtra("path", filename);
	      startActivity(intent);
	    } 
	    else {
	    	if(call==1)
	    	{

	    		if((filename.endsWith(".txt"))||(filename.endsWith(".TXT"))||(filename.endsWith(".doc"))||(filename.endsWith(".DOC"))||(filename.endsWith(".docx"))||(filename.endsWith(".DOCX")))
	    		{
		    		

	    			try {
	    				// FileReader reads text files in the default encoding.
	    				FileReader fileReader = 
	    				new FileReader(filename);

	    				// Always wrap FileReader in BufferedReader.
	    				BufferedReader bufferedReader = 
	    				new BufferedReader(fileReader);

	    				while((line=bufferedReader.readLine()) != null) {
	    							
	    					if(line.startsWith(" "))
	    						sc++;
	    					
	    									}   
	    				ViewDialog alert = new ViewDialog();
	    				alert.showDialog(this, "the number of paragraph is:"+sc);
	              
	    				// Always close files.
	    				bufferedReader.close();         
	    				}	
	    			catch(FileNotFoundException ex) {
	    				Toast.makeText(this,"Unable to open file '" + filename + "'", Toast.LENGTH_LONG).show();                
	    				}
	    			catch(IOException ex) {
	    				Toast.makeText(this, "Error reading file " + filename + "'", Toast.LENGTH_LONG).show();                 
	    				// Or we could just do this: 
	    				// ex.printStackTrace();
	    				}
	    			db.open();
	    	  db.updatesrs(p,m,filename,sc);
	    	  db.close();
	      Intent i=new Intent(this,AddCode.class);
	      try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      i.putExtra("pname", p);
	      i.putExtra("mname", m);
	      startActivity(i);
	      
	      }
	    		else
	    		{
	    			Toast.makeText(this, "can't open this file extension", Toast.LENGTH_LONG).show();
	    		}}
	   else{
	    	  if(call==2)
		    	{

		    		if((filename.endsWith(".cpp"))||(filename.endsWith(".CPP"))||(filename.endsWith(".java"))||(filename.endsWith(".JAVA")))
		    		{

		    			try {
		    				// FileReader reads text files in the default encoding.
		    				FileReader fileReader = 
		    				new FileReader(filename);

		    				// Always wrap FileReader in BufferedReader.
		    				BufferedReader bufferedReader = 
		    				new BufferedReader(fileReader);

		    				while((line = bufferedReader.readLine()) != null) {
		    							sc++;
		    							str= str+ line+"\n";
		    									}   
		    				ViewDialog alert = new ViewDialog();
		    				alert.showDialog(this, "the number of paragraph is:"+sc);
		              
		    				// Always close files.
		    				bufferedReader.close();         
		    				}	
		    			catch(FileNotFoundException ex) {
		    				Toast.makeText(this,"Unable to open file '" + filename + "'", Toast.LENGTH_LONG).show();                
		    				}
		    			catch(IOException ex) {
		    				Toast.makeText(this, "Error reading file " + filename + "'", Toast.LENGTH_LONG).show();                 
		    				// Or we could just do this: 
		    				// ex.printStackTrace();
		    				}
		    			db.open();
		    	  db.updatecode(p,m,path,sc);
		    	  db.close();
		      Intent i=new Intent(this,MainMenu.class);
		      try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      startActivity(i);
		      
		      }
	      }
	    	
	    	
	      
	    }
	
	  }
	}}