package com.example.smetrics;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class NewModule extends Activity {
EditText ed1,ed2,ed3,ed4; 
DBAdapter db;
String s1,s5,s6,sname;
int s2,s3,s4;

public void butt(View v)
{
	db.open();
	Cursor c=db.find(sname);
s1=ed1.getText().toString();
s2=Integer.parseInt(ed2.getText().toString());
s3=Integer.parseInt( ed3.getText().toString());
s4=Integer.parseInt( ed4.getText().toString());
if(c.moveToFirst())
{
	do{
s5=c.getString(0);
s6=c.getString(1);
	}while(c.moveToNext());
}
	long id=db.insertall(sname,s1,s6,s5,s2 ,s3,s4);
	Intent i=new Intent(this,AddSRS.class);
	i.putExtra("pname", sname);
	i.putExtra("mname",s1);
	startActivity(i);	
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_module);
		sname=  getIntent().getStringExtra("sname");
		db=new DBAdapter(this);
		ed1=(EditText) findViewById(R.id.editText1);
		ed2=(EditText) findViewById(R.id.editText2);
		ed3=(EditText) findViewById(R.id.editText3);
		ed4=(EditText) findViewById(R.id.editText4);
		
	}
}
