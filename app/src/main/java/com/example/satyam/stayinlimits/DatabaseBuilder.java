package com.example.satyam.stayinlimits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseBuilder extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "limit.db";
    public static final String TABLE_NAME = "Limits";
    public static final String COLUMN_ID = "TID";
    public static final String COLUMN_NAME = "TRANSC";
    public static final String COLUMN_NAME1 = "CREDIT";
    public static final String COLUMN_NAME2 = "DEBIT";

    public DatabaseBuilder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseBuilder(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }

    @Override
    public void setWriteAheadLoggingEnabled(boolean enabled) {
        super.setWriteAheadLoggingEnabled(enabled);
    }

    @Override
    public void setLookasideConfig(int slotSize, int slotCount) {
        super.setLookasideConfig(slotSize, slotCount);
    }

    @Override
    public void setOpenParams(SQLiteDatabase.OpenParams openParams) {
        super.setOpenParams(openParams);
    }

    @Override
    public void setIdleConnectionTimeout(long idleConnectionTimeoutMs) {
        super.setIdleConnectionTimeout(idleConnectionTimeoutMs);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this.getWritableDatabase();
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY, " + COLUMN_NAME + " text, " + COLUMN_NAME1 + " REAL, " +
                COLUMN_NAME2 + " REAL)";
        db.execSQL(createTable);
        createUserTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String fetchData() {
        String result = "";
        String query = "SELECT * From " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            int pKey = c.getInt(0);
            String tName = c.getString(1);
            double credit = c.getDouble(2);
            double debit = c.getDouble(3);
            result += pKey + " " + tName + " " + credit + " " + debit
                    + System.getProperty("line.separator");
        }
        c.close();
        db.close();
        return result;
    }

    public void addRecord(Record r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(this.COLUMN_ID, r.getTransID());
        val.put(this.COLUMN_NAME, r.getTransName());
        val.put(this.COLUMN_NAME1, r.getCredit());
        val.put(this.COLUMN_NAME2, r.getDebit());
        db.insert(this.TABLE_NAME, null, val);
    }

    private void createUserTable(SQLiteDatabase db) {

        //SQLiteDatabase db = this.getWritableDatabase();
        String createTable = "CREATE TABLE  User" + " (" + "Name text, " + "Age" +
                " INTEGER, " + "AvailableMoney REAL," + "CreditLimit" + " REAL, " + "CreditBalance" + " REAL, PRIMARY KEY(Name, Age)" + ")";

        db.execSQL(createTable);
    }

    public void addUserProfile(String userName, int age, double aMoney, double cLimit, double cBalance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("Name", userName);
        val.put("Age", age);
        val.put("AvailableMoney", aMoney);
        val.put("CreditLimit", cLimit);
        val.put("CreditBalance", cBalance);
        db.insert("user", null, val);
    }

    public String fetchUserData() {
        String result = "";
        String query = "SELECT * From User";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {

            String tName = c.getString(0);
            result += tName
                    + System.getProperty("line.separator");
        }
        c.close();
        db.close();
        return result;
    }

    public static boolean isOpenDatabase(DatabaseBuilder db) {
        db.getReadableDatabase();
        String name = db.getDatabaseName();
        if (name.equalsIgnoreCase("Limit.db")) {
            return true;
        }
        return false;
    }
}
