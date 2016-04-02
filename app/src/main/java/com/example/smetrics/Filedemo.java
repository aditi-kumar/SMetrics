package com.example.smetrics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Filedemo extends Activity {
TextView tv;
TextView tvs;
int sc;
String str;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filedemo);
		String filename=getIntent().getStringExtra("filename");
		 tv= (TextView) findViewById(R.id.textView1) ;
		 tvs= (TextView) findViewById(R.id.textView2) ;
       
		String line = null;
		str=null;

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
  	      alert.showDialog(this, "the number of loc is:"+sc);
            tv.setText(str);
tvs.setText("the loc is-"+sc);
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
	}
}
