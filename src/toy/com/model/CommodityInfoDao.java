package toy.com.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CommodityInfoDao extends SQLiteOpenHelper {
	public final static int VERSION=1;//∞Ê±æ∫≈
	public final static String TABLE_NAME="toy_commodity";//toy_commodity±Ì√˚
	public final static String DATABASE_NAME="toy_android.db";
	public final static String ID="N_COMMODITY_ID";
	public final static String NAME="VC_COMMODITY_NAME";
	public final static String PRICE="N_COMMODITY_PRICE";
	public final static String FUNCTION="N_COMMODITY_FUNCTION";
	public final static String EFFICIENCY="N_COMMODITY_EFFICIENCY";
	
	
	public CommodityInfoDao(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		Log.v("Come on Commodity", "Into Table " + TABLE_NAME );
		// TODO Auto-generated constructor stub
	}


	
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ NAME + " varcher(50), "+ PRICE + " int(11), " + FUNCTION + " int(11), " + EFFICIENCY + " int(11));";
		db.execSQL(sql);
		ContentValues cv = new ContentValues();
		cv.put(ID, "1");
		cv.put(NAME, "œ„Ω∂");
		cv.put(PRICE, "10");
		cv.put(FUNCTION, "1");
		cv.put(EFFICIENCY, "30");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);
		
		cv = new ContentValues();
		cv.put(ID, "2");
		cv.put(NAME, "œ„‘Ì");
		cv.put(PRICE, "10");
		cv.put(FUNCTION, "2");
		cv.put(EFFICIENCY, "30");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);
		
		cv = new ContentValues();
		cv.put(ID, "3");
		cv.put(NAME, "∆§«Ú");
		cv.put(PRICE, "10");
		cv.put(FUNCTION, "3");
		cv.put(EFFICIENCY, "30");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);
		
		cv = new ContentValues();
		cv.put(ID, "4");
		cv.put(NAME, "º“ΩÃ");
		cv.put(PRICE, "10");
		cv.put(FUNCTION, "0");
		cv.put(EFFICIENCY, "30");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);

		cv = new ContentValues();
		cv.put(ID, "5");
		cv.put(NAME, "Õ∑ø¯");
		cv.put(PRICE, "100");
		cv.put(FUNCTION, "4");
		cv.put(EFFICIENCY, "1");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);
		
		cv = new ContentValues();
		cv.put(ID, "6");
		cv.put(NAME, "Ó¯º◊");
		cv.put(PRICE, "100");
		cv.put(FUNCTION, "4");
		cv.put(EFFICIENCY, "3");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);

		Log.v("Creat", "Table " + TABLE_NAME);
		db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String dsql = "DROP TABLE "+ TABLE_NAME;
		db.execSQL(dsql);
		String sql ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ NAME + " varcher(50), "+ PRICE + " int(11), " + FUNCTION + " int(11), " + EFFICIENCY + " int(11));";
		db.execSQL(sql);
//		String insert = "INSERT INTO "+ TABLE_NAME + " VALUES(1,'œ„Ω∂',10,1,30);"
//			+ "INSERT INTO " + TABLE_NAME + " VALUES(2,'œ„‘Ì',10,2,30);" 
//			+ "INSERT INTO " + TABLE_NAME + " VALUES(3,'∆§«Ú',10,3,30);"
//			+ "INSERT INTO " + TABLE_NAME + " VALUES(4,'º“ΩÃ',-100,0,30);";
//		db.execSQL(insert);
		ContentValues cv = new ContentValues();
		cv.put(NAME, "œ„Ω∂");
		cv.put(PRICE, "10");
		cv.put(FUNCTION, "1");
		cv.put(EFFICIENCY, "30");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);
		
		cv = new ContentValues();
		cv.put(NAME, "œ„‘Ì");
		cv.put(PRICE, "10");
		cv.put(FUNCTION, "2");
		cv.put(EFFICIENCY, "30");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);
		
		cv = new ContentValues();
		cv.put(NAME, "∆§«Ú");
		cv.put(PRICE, "10");
		cv.put(FUNCTION, "3");
		cv.put(EFFICIENCY, "30");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);
		
		cv = new ContentValues();
		cv.put(NAME, "º“ΩÃ");
		cv.put(PRICE, "10");
		cv.put(FUNCTION, "0");
		cv.put(EFFICIENCY, "30");
		db.insert(TABLE_NAME, NAME + ", " + PRICE + ", " + FUNCTION + ", " + EFFICIENCY, cv);
		
		Log.v("TOY_COMMODITY", "OnUpgrade");
		db.close();
	}

}
