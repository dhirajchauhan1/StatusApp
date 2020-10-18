package com.shayari_jokesallinone.DatabasePackage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.shayari_jokesallinone.RecyclerPackage.DbModelClass;
import com.shayari_jokesallinone.ShayariActivity;

import java.util.ArrayList;

public class ShayariDatabaseClass extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "alldataassets.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    Cursor objCursor;
    String Cat;


    public ShayariDatabaseClass(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
        this.context = context;
    }


    public ArrayList<DbModelClass> getAllData(){

        //get data from shayari activity
        Cat=  ShayariActivity.getActivityInstance().get();

        try {
            ArrayList<DbModelClass> objDbModelClassesArrayList = new ArrayList<>();
            SQLiteDatabase objSqLiteDatabase = getReadableDatabase();
            if (objSqLiteDatabase != null){

                objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM "+Cat, null);


                if (objCursor.getCount() != 0) {
                    while (objCursor.moveToNext()) {
                        String shayari = objCursor.getString(0);

                        objDbModelClassesArrayList.add(
                                new DbModelClass(
                                        shayari
                                ));
                    }
                    return objDbModelClassesArrayList;
                } else {
                    Toast.makeText(context, "no data is retrieved...", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
            else {
                Toast.makeText(context, "data is null...", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context, "get all data:- "+e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}


