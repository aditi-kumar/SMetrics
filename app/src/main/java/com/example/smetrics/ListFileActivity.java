package com.example.smetrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class ListFileActivity extends ListActivity {

	  private String path;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_list_file);
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
	      Intent intent = new Intent(this, ListFileActivity.class);
	      intent.putExtra("path", filename);
	      startActivity(intent);
	    } else {
	      if((filename.endsWith(".cpp"))||(filename.endsWith(".CPP")))
	      {
	      Intent i=new Intent(this,Filedemo.class);
	      i.putExtra("filename", filename);
	      startActivity(i);
	      
	      }
	      else{
	    	  Toast.makeText(this, "Can't open this file extension", Toast.LENGTH_LONG).show();
	      }
	      
	      
	    }
	
	  }
	}