package com.buka.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil {
	public static DBUtil mInstance;
	private Context mContext;
	private DBHelper mDBHelper;
	private SQLiteDatabase mSQLiteDatabase;
	private static String table;
	
	public DBUtil(Context mContext) {
		this.mContext = mContext;
		mDBHelper = new DBHelper(mContext);
		mSQLiteDatabase = mDBHelper.getWritableDatabase();
	}

	/**
	 * 初始化数据库操作DBUtil类
	 */
	public static DBUtil getInstance(Context context,String tb) {
		table = tb;
		if (mInstance == null) {
			mInstance = new DBUtil(context);
		}
		return mInstance;
	}
	
	/**
	 * 关闭数据库
	 */
	public void close() {
		mDBHelper.close();
		mDBHelper = null;
		mSQLiteDatabase.close();
		mSQLiteDatabase = null;
		mInstance = null;
	}

	/**
	 * 添加数据
	 */
	public void insertData(ContentValues values) {
		mSQLiteDatabase.insert(table, null, values);
	}

	/**
	 * 更新数据
	 * 
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 */
	public void updateData(ContentValues values, String whereClause,
			String[] whereArgs) {
		mSQLiteDatabase.update(table, values, whereClause,
				whereArgs);
	}

	/**
	 * 删除数据
	 * 
	 * @param whereClause
	 * @param whereArgs
	 */
	public void deleteData(String whereClause, String[] whereArgs) {
		mSQLiteDatabase
				.delete(table, whereClause, whereArgs);
	}

	/**
	 * 查询数据
	 * 
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 */
	public Cursor selectData(String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		Cursor cursor = mSQLiteDatabase.query(table,columns, selection, selectionArgs, groupBy, having, orderBy);
		return cursor;
	}
}