package com.example.smetrics;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 

  public class CommitPDF extends Activity {
String s,x;
DBAdapter db;
int k,l,o;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commit_pdf);
		DBAdapter db=new DBAdapter(this);
		s=getIntent().getStringExtra("sname");
		o=1;
		Document document = new Document();
	      try
	      {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/sdcard/SMETRICS/"+s+".pdf"));
	         document.open();
	         db.open();
	         Cursor c=(Cursor) db.getProjectdetails(s);
	         document.add(new Paragraph("---------------------"+s+"--------------------"));
	         if(((android.database.Cursor) c).moveToFirst())
	 		{
	 			
	 			do{
	 				k=c.getInt(2)*c.getInt(3);
	 				l=c.getInt(5)*c.getInt(6);
	 				if(o==1)
	 				{
	 					x="\n Project start date"+c.getString(9)+"\n Project end date"+c.getString(10)+"\n Project status"+c.getString(11)+"\n\n\n Module name:"+c.getString(1)+"\n Srs cost/paragraph:"+c.getInt(2)+"\n total no. of para: "+c.getInt(3)+"\n Srs file destination::"+c.getString(4)+"\n cost/loc:"+c.getInt(5)+"\n total no. of LOC: "+c.getInt(6)+"\n code file destination::"+c.getString(7)+"\n other costs:"+c.getString(8)+"\n Total Module Cost:"+(k+l+c.getInt(8));
	 				o++;
	 				}
	 				else{
	 					x="\n\n Module name:"+c.getString(1)+"\n Srs cost/paragraph:"+c.getInt(2)+"\n total no. of para: "+c.getInt(3)+"\n Srs file destination::"+c.getString(4)+"\n cost/loc:"+c.getInt(5)+"\n total no. of LOC: "+c.getInt(6)+"\n code file destination::"+c.getString(7)+"\n other costs:"+c.getString(8)+"\n Total Module Cost:"+(k+l+c.getInt(8));
	 				}
	               document.add(new Paragraph(x));
	 			}while(((android.database.Cursor) c).moveToNext());
			}
	         document.addAuthor("Aditi Kumar");
	         document.addCreationDate();
	         document.addCreator("SMETRICS");
	         document.addTitle(s);
	         document.addSubject("SMETRICS pdf file for "+s+" project.");
	         			
			db.close();
	         document.close();
	         writer.close();
	      } catch (DocumentException e)
	      {
	         e.printStackTrace();
	      } catch (FileNotFoundException e)
	      {
	         e.printStackTrace();
	      }
	 Toast.makeText(this, "pdf generates", Toast.LENGTH_LONG).show();
	      Intent i=new Intent(this,MainMenu.class);
	      startActivity(i);
	      }

}
