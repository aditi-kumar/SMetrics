package com.example.smetrics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Demo extends Activity {
	EditText mEdit;
	TextView tv;
	String s;
	Button b;
	int i,loc, pre,statements;
	public void butt2(View v)
	{
		Intent i=new Intent(this,ListFileActivity.class);
		startActivity(i);
	}
	public void gloc(View v)
	{
		
		mEdit   = (EditText)findViewById(R.id.editText1);
		tv   = (TextView)findViewById(R.id.textView2);
		b= (Button)findViewById(R.id.button1);
		s= mEdit.getText().toString();
		for(i=0;i<s.length();)
		{
			if(s.charAt(i)=='"')
			{	
				do{
					i++;
				}while(s.charAt(i)!='"');
 
			}
			if(s.charAt(i)==';')
			{	statements++;
			}
			if(s.charAt(i)=='#')
			{	pre++;
			}
			i++;
			
		}
		loc=statements+pre;
		tv.setText("the number of semicolon statements ="+statements+"\n"+"the number of predecessors="+pre+"\n the total number of loc will become"+loc);
	b.setClickable(false);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);

		setContentView(R.layout.activity_demo);
		pre=statements=0;
	}
}
