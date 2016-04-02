package com.example.smetrics;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPro extends Activity {
	DBAdapter db;
	EditText ed1, ed2,ed3;
	String name,date;
	int cost;
	public void butt(View v)
	{
		 db.open();
		 name= ed1.getText().toString();
		date=ed3.getText().toString();
	long id=db.insertProject(name, date);
	db.close();
	
	Toast.makeText(getBaseContext(), "new project added", Toast.LENGTH_LONG).show();
	Intent inte=new Intent(getBaseContext(),MainMenu.class);
	startActivity(inte);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		setContentView(R.layout.activity_new_pro);
		
		ed1= (EditText) findViewById(R.id.editText1);
		ed3=(EditText) findViewById(R.id.editText3);
		db=new DBAdapter(this);
		 try {
	            String destPath = "/data/data/com.example.smetrics/databases";
	            File f = new File(destPath);
	            if (!f.exists()) {            	
	            	f.mkdirs();
	                f.createNewFile();
	            	
	            	//---copy the db from the assets folder into 
	            	// the databases folder---
	                CopyDB(getBaseContext().getAssets().open("ProjectDBs"),
	                    new FileOutputStream(destPath + "/ProjectDBs"));
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	            
	        }}
		 public void CopyDB(InputStream inputStream,OutputStream outputStream) throws IOException {
				        //---copy 1K bytes at a time---
				        byte[] buffer = new byte[1024];
				        int length;
				        while ((length = inputStream.read(buffer)) > 0) {
				            outputStream.write(buffer, 0, length);
				        }
				        inputStream.close();
				        outputStream.close();
				        
				    }
		
		
		
}
