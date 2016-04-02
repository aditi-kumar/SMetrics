package com.example.smetrics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity {
	public void mcontinue(View v)
	{
		Intent i= new Intent(this,MainMenu.class);
        startActivity(i);	
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
}
}
