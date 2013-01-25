package toy.com.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class ToyInfoDao extends SQLiteOpenHelper{
	public final static int VERSION=1;//°æ±¾ºÅ
	public final static String TABLE_NAME="toy_info";//±íÃû
	public final static String DATABASE_NAME="toy_android.db";
	public final static String ID="N_INFO_ID";
	public final static String NAME="VC_INFO_NAME";
	public final static String BIRTH="TS_INFO_BIRTH";
	public final static String HUNGRY="N_INFO_HUNGRY";
	public final static String CLEAN="N_INFO_CLEAN";
	public final static String MOOD="N_INFO_MOOD";
	public final static String POWER="N_INFO_POWER";
	public final static String INTELL="N_INFO_INTELLIGENCE";
	public final static String AGLIE="N_INFO_AGLIE";
	public final static String MONEY="N_INFO_MONEY";
	public final static String USERID="N_USER_ID";
	
	
	public ToyInfoDao(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
		Log.v("Come on toy_info", "Into table " + TABLE_NAME);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */

	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NAME + " int(11), " + BIRTH + " DATA, " + HUNGRY + " int(11), " + CLEAN + " int(11), " + MOOD + " int(11), " 
			+ POWER + " int(11), " + INTELL + " int(11), " + AGLIE + " int(11), " + MONEY + " int(11), " + USERID + " int(11));";
		db.execSQL(sql);
		ContentValues cv = new ContentValues();
		cv.put(ID, "1");
        cv.put(NAME, "LittleQ");
        cv.put(BIRTH, "2012-06-06 08:29:41");
        cv.put(HUNGRY, "35");
        cv.put(CLEAN, "56");
        cv.put(MOOD, "80");
        cv.put(POWER, "20");
        cv.put(INTELL, "15");
        cv.put(AGLIE, "5");
        cv.put(MONEY, "10000");
        cv.put(USERID, "1");
        db.insertOrThrow(TABLE_NAME, null, cv);
		Log.v("Creat table", "table toy_info");
	}


	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.v("TOY_INFO", "OnUpgrade");
	}

}
