package com.example.smetrics;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainMenu extends Activity {
public void mcontinue6(View v)
{
	Intent i=new Intent(this,Demo.class);
	startActivity(i);
	}
public void newp(View v)
{
	Intent i=new Intent(this,NewPro.class);
	startActivity(i);
	}
public void inst(View v)
{
	Intent i=new Intent(this,Instructions.class);
	startActivity(i);
	}public void auth(View v)
	{
		Intent i=new Intent(this,AboutAuthor.class);
		startActivity(i);
		}
public void modp(View v)
{
	Intent i=new Intent(this,ModPro.class);
	startActivity(i);
	}
public void comp(View v)
{
	Intent i=new Intent(this,Completepro.class);
	startActivity(i);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.tryin, R.anim.tryout);

		setContentView(R.layout.activity_main_menu);
	}
}
