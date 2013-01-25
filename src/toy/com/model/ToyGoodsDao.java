package toy.com.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class ToyGoodsDao extends SQLiteOpenHelper {
	public final static int VERSION=1;//°æ±¾ºÅ
	public final static String TABLE_NAME="toy_goods";//±íÃû
	public final static String DATABASE_NAME="toy_android.db";
	public final static String ID="N_GOODS_ID";
	public final static String NUMBER="N_GOODS_NUMBER";
	public final static String INFOID="N_INFO_ID";
	public final static String COMMODITYID="N_COMMODITY_ID";

	public ToyGoodsDao(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}


	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ NUMBER + " int(11), " + INFOID + " int(11), " + COMMODITYID + " int(11));";
		db.execSQL(sql);
//		String insert="INSERT INTO " + TABLE_NAME + " VALUES(1, 3, 1, 1);"
//					+ "INSERT INTO " + TABLE_NAME + " VALUES(2, 4, 1, 2);"
//					+ "INSERT INTO " + TABLE_NAME + " VALUES(3, 1, 1, 3);"
//					+ "INSERT INTO " + TABLE_NAME + " VALUES(4, 0, 1, 4);";
//		db.execSQL(insert);
		
		ContentValues cv = new ContentValues();
		cv.put(ID, "1");
		cv.put(NUMBER, "3");
		cv.put(INFOID, "1");
		cv.put(COMMODITYID, "1");
		db.insertOrThrow(TABLE_NAME, NUMBER + ", " + INFOID + ", " + COMMODITYID, cv);
		
		cv = new ContentValues();
		cv.put(ID, "2");
		cv.put(NUMBER, "4");
		cv.put(INFOID, "1");
		cv.put(COMMODITYID, "2");
		db.insertOrThrow(TABLE_NAME, NUMBER + ", " + INFOID + ", " + COMMODITYID, cv);
		
		cv = new ContentValues();
		cv.put(ID, "3");
		cv.put(NUMBER, "1");
		cv.put(INFOID, "1");
		cv.put(COMMODITYID, "3");
		db.insertOrThrow(TABLE_NAME, NUMBER + ", " + INFOID + ", " + COMMODITYID, cv);
		
//		cv = new ContentValues();
//		cv.put(ID, "4");
//		cv.put(NUMBER, "2");
//		cv.put(INFOID, "1");
//		cv.put(COMMODITYID, "4");
		
//		db.insertOrThrow(TABLE_NAME, NUMBER + ", " + INFOID + ", " + COMMODITYID, cv);
		Log.v("Creat", "Table " + TABLE_NAME + " insert: ");
		db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.v("TOY_GOODS", "OnUpgrade");
	}

}
