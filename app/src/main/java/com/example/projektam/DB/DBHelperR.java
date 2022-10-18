package com.example.projektam.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelperR extends SQLiteOpenHelper {
    public DBHelperR(Context context){
        super(context,"projektAM.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE WYNIKI (ID INTEGER PRIMARY KEY AUTOINCREMENT, WYNIK INTEGER, NICK TEXT )");
        myDB.execSQL("CREATE TABLE UZYTKOWNICY (NICK TEXT PRIMARY KEY, PASSWD TEXT, CWYNIK INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oV, int nV) {
        myDB.execSQL("DROP TABLE IF EXISTS WYNIKI");
        myDB.execSQL("DROP TABLE IF EXISTS UZYTKOWNICY");
    }



    public void XD(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        myDB.execSQL("DROP TABLE IF EXISTS WYNIKI");
        myDB.execSQL("DROP TABLE IF EXISTS UZYTKOWNICY");
        myDB.execSQL("CREATE TABLE WYNIKI (ID INTEGER PRIMARY KEY AUTOINCREMENT, WYNIK INTEGER, NICK TEXT )");
        myDB.execSQL("CREATE TABLE UZYTKOWNICY (NICK TEXT PRIMARY KEY, PASSWD TEXT, CWYNIK INTEGER)");
    }


    
    public Boolean addUser (String nick, String pass){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NICK",nick);
        cv.put("PASSWD", pass);
        cv.put("CWYNIK", 0);
        long res = myDB.insert("UZYTKOWNICY",null,cv);
        return res != -1;
    }

    public  Boolean loginCheck(String nick, String pass){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cr = myDB.rawQuery("SELECT * FROM UZYTKOWNICY WHERE NICK = ? AND PASSWD = ? ", new String[] {nick, pass});
        return cr.getCount() > 0;
    }

    public  Boolean nickCheck(String nick){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cr = myDB.rawQuery("SELECT * FROM UZYTKOWNICY WHERE NICK = ?", new String[] {nick});
        return cr.getCount() > 0;
    }

    public Integer newGame(String nick){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NICK",nick);
        cv.put("WYNIK", 0);
        long res = myDB.insert("WYNIKI",null,cv);
        int idd = 0;
        if (res != -1) {
            Cursor cr = myDB.rawQuery("SELECT ID FROM WYNIKI WHERE NICK = ? ORDER BY ID DESC", new String[]{nick});
            cr.moveToFirst();
            if( cr != null && cr.moveToFirst() ){
                idd = cr.getInt(cr.getColumnIndex("ID"));
                cr.close();
            }
            return idd;
        }
        else{
            return -1;
        }
    }
}
