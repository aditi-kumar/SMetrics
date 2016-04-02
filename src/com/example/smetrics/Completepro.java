package com.example.smetrics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Completepro extends Activity {
LinearLayout ll;
int i;
String tvtext;
TextView tvv;
public void exec(View v)
{
	tvv=(TextView) v;
	tvtext=tvv.getText().toString();
	new AlertDialog.Builder(this)
    .setTitle("Commit entry")
    .setMessage("Are you sure you want to Commit "+tvtext+"to PDF ?")
    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) { 
        	execc(tvtext);
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
public void execc(String s)
{
	Intent i=new Intent(this , CommitPDF.class);
	i.putExtra("sname", s);
	startActivity(i);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_completepro);
		ll= (LinearLayout) findViewById(R.id.linearLayout1);
		i=0;
		DBAdapter db=new DBAdapter(this);
		db.open();
		Cursor c=db.getAllCompleteProject();
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
		    ll.addView(tv);
			
		    i++;}while(c.moveToNext());
		}
		
		db.close();
	}
}
