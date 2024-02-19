package com.example.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(@Nullable Context context) {
        super(context, "Login_Credentials", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table login_details (name text primary key, email text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists login_details");
    }

    public Boolean insertdata (String name, String pass,String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("password",pass);
        long result = DB.insert("login_details",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
//        return result != -1;
    }
    public boolean checkemail(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from login_details where email=?",new String[]{email});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkemailpassword(String email,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from login_details where email=? and  password=?",new String[]{email,password});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
