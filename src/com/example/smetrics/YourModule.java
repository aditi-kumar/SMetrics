package com.example.smetrics;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class YourModule extends Activity {
DBAdapter db;
TextView tv;
String s,m,texts;
int l,k;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_your_module);
		s=getIntent().getStringExtra("pname");
		m=getIntent().getStringExtra("mname");
		db= new DBAdapter(this);
		tv=(TextView) findViewById(R.id.textView1);
		db.open();
		Cursor c= db.getModule(s, m);
		c.moveToFirst();
		k=c.getInt(2)*c.getInt(3);
		l=c.getInt(5)*c.getInt(6);
		texts=" Parent project:"+c.getString(0)+"\n Project start date"+c.getString(9)+"\n Project end date"+c.getString(10)+"\n Project status"+c.getString(11)+"\n Module name:"+c.getString(1)+"\n Srs cost/paragraph:"+c.getInt(2)+"\n total no. of para: "+c.getInt(3)+"\n Srs file destination::"+c.getString(4)+"\n cost/loc:"+c.getInt(5)+"\n total no. of LOC: "+c.getInt(6)+"\n code file destination::"+c.getString(7)+"\n other costs:"+c.getString(8)+"\n Total Module Cost:"+(k+l+c.getInt(8));
		tv.setText(texts);
	}
}
