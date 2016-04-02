package com.example.smetrics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AddSRS extends Activity {
String p,m;
DBAdapter db;
int o;
public void butt1(View v){
	o=1;

	Intent i=new Intent(this,FileList.class);
	i.putExtra("callno", o);
	i.putExtra("pname", p);
	i.putExtra("mname", m);
	startActivity(i);
}
public void butt2(View v){
	Intent i=new Intent(this,AddCode.class);
	startActivity(i);
}
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_srs);
		p=  getIntent().getStringExtra("pname");
		m=  getIntent().getStringExtra("mname");
		db=new DBAdapter(this);
	}
}
