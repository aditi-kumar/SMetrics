package com.example.smetrics;





import java.text.SimpleDateFormat;
import java.util.Date;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {
	static final String KEY_PNAME = "projectname";
    static final String KEY_MNAME = "modulename";
    static final String SRSCost = "srscost";
    static final String TSRS = "totalsrs";
    static final String sdest = "srsdestination";
    static final String CodeCost = "codecost";
    static final String tcode = "totalcode";
    static final String cdest = "codedestination";
    static final String OtherCost = "othercosts";
    static final String StartD = "startdate";
    static final String FinishD = "enddate";
    static final String Status = "projectstatus";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "smetricsdb";
    static final String DATABASE_TABLE = "mytable";
    static final int DATABASE_VERSION = 3;

    static final String DATABASE_CREATE ="CREATE TABLE `mytable` ("+
	"`projectname`	TEXT NOT NULL,"+
	"`modulename`	TEXT NOT NULL DEFAULT 'main',"+
	"`srscost`	INTEGER NOT NULL DEFAULT 0,"+
	"`totalsrs`	INTEGER NOT NULL DEFAULT 0,"+
	"`srsdestination`	TEXT,"+
	"`codecost`	INTEGER NOT NULL DEFAULT 0,"+
	"`totalcode`	INTEGER NOT NULL DEFAULT 0,"+
	"`codedestination`	TEXT,"+
	"`othercosts`	INTEGER NOT NULL DEFAULT 0,"+
	"`startdate`	TEXT NOT NULL,"+
	"`enddate`	TEXT NOT NULL,"+
	"`projectstatus`	TEXT DEFAULT 'intransit'"+
");";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        
        
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Project");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() 
    {
        DBHelper.close();
    }

    //---insert a project into the database---
    public long insertProject(String name, String finishd) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PNAME, name);
        initialValues.put(FinishD, finishd);
        initialValues.put(StartD,new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular project---
    public boolean deleteProject(String s) 
    {
        return db.delete(DATABASE_TABLE, KEY_PNAME + "='" + s+"'", null) > 0;
    }
    public boolean deleteModule(String s,String m) 
    {
        return db.delete(DATABASE_TABLE, KEY_PNAME + "='" + s+"' AND modulename ='"+m+"'", null) > 0;
    }

    //---retrieves all the projects---
    public Cursor getAllCurrentProject()
    {
        return (db.rawQuery("select DISTINCT projectname from mytable WHERE projectstatus='intransit'", null));
    }
    public Cursor getModule(String s, String m)
    {
        return (db.rawQuery("select * from mytable WHERE projectname ='"+s+"' AND modulename ='"+m+"'", null));
    }
    public Cursor getAllCompleteProject()
    {
        return (db.rawQuery("select DISTINCT projectname from mytable WHERE projectstatus='complete'", null));
    }

    //---retrieves a particular project---
    public Cursor getProject(String s) throws SQLException 
    {
        
        		
	    SQLiteCursor c = (SQLiteCursor) db.rawQuery("SELECT modulename FROM mytable" + " WHERE projectname= '" + s+"'" , null);
       
        return c;
    }
    public long insertall(String s,String m, String f, String st, int sc, int cc, int oc )
    {
    	ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PNAME, s);
        initialValues.put(KEY_MNAME, m);
        initialValues.put(FinishD, f);
        initialValues.put(StartD,st);
        initialValues.put(SRSCost, sc);
        initialValues.put(CodeCost, cc);
        initialValues.put(OtherCost, oc);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    public Cursor find(String s)
    {
		
	    SQLiteCursor c = (SQLiteCursor) db.rawQuery("SELECT  startdate, enddate FROM mytable" + " WHERE projectname= '" + s+"'" , null);
       
        return c;
    }
    public void update(String s)
    {
            
            
            db.execSQL("UPDATE mytable SET projectstatus='complete' WHERE projectname='"+s+"'"); 
        }
    public void updatesrs(String s,String m,String path,int sc)
    {
    	ContentValues value = new ContentValues();
            value.put(sdest, path);
            value.put(TSRS, sc);
    	db.update(DATABASE_TABLE,
    		    value,
    		    KEY_PNAME + " = ? AND " + KEY_MNAME + " = ?",
    		    new String[]{s, m});
                
    }
    public void updatecode(String s,String m,String path,int sc)
    {
    	ContentValues value = new ContentValues();
            value.put(cdest, path);
            value.put(tcode, sc);
    	db.update(DATABASE_TABLE,
    		    value,
    		    KEY_PNAME + " = ? AND " + KEY_MNAME + " = ?",
    		    new String[]{s, m});
                
    }
    //---updates a project---
    public boolean updateProject(long rowId, String name,int cost,int srscost, int codecost,int othercost,String status,Date finishd, Date startd) 
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String date = sdf.format(new Date());
    String date1 = sdf.format(finishd);
        ContentValues args = new ContentValues();
        args.put(KEY_PNAME, name);
        args.put(SRSCost, srscost);
        args.put(CodeCost, codecost);
        args.put(OtherCost, othercost);
        args.put(Status, status);
        args.put(FinishD, date1);
        args.put(StartD, date);
        return db.update(DATABASE_TABLE, args, KEY_PNAME + "=" + name, null) > 0;
    }

}