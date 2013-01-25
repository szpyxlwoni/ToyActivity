package toy.com.tools;

import toy.com.model.CommodityInfoDao;
import toy.com.model.ToyInfoDao;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GoodsUserListener {
	ToyInfoDao toyinfodao = new ToyInfoDao(null);
	CommodityInfoDao cominfodao = new CommodityInfoDao(null);
	SQLiteDatabase sqlite;
	Cursor cursor;
	
	public void UpGoodsUse(int InfoId, int CommodityId){
		int function = -1;
		int efficiency = -1;
		int temp = -1;
		String changed = null;
		sqlite = SQLiteDatabase.openOrCreateDatabase("/data/data/toy.com.view/databases/toy_android.db",null);
		
		cursor = sqlite.rawQuery("select * from " + cominfodao.TABLE_NAME 
				+ " where " + cominfodao.ID + "=" + CommodityId, null);
		cursor.moveToFirst();
		if(cursor != null){
			function  = cursor.getInt(cursor.getColumnIndex(cominfodao.FUNCTION));
			efficiency = cursor.getInt(cursor.getColumnIndex(cominfodao.EFFICIENCY));
		}
		cursor.close();
		if (function == 1){
			changed = toyinfodao.HUNGRY;
		}
		else if (function == 2){
			changed = toyinfodao.CLEAN;
		}
		else if (function == 3){
			changed = toyinfodao.MOOD;
		}
		Log.v("InfoId", InfoId + "");
		cursor = sqlite.rawQuery("select " + changed + " from " 
				+ toyinfodao.TABLE_NAME + " where " + toyinfodao.ID 
				+ "=" + InfoId, null);
		cursor.moveToFirst();
		if (cursor != null){
			temp = cursor.getInt(cursor.getColumnIndex(changed));
			temp += efficiency;
		}
		if (temp > 100){
			temp = 100;
		}
		Log.v("Use Goods", "update " + toyinfodao.TABLE_NAME + " set " + changed + "=" + temp + " where " + toyinfodao.ID + "=" + InfoId);
		sqlite.execSQL("update " + toyinfodao.TABLE_NAME + " set " + changed + "=" + temp + " where " + toyinfodao.ID + "=" + InfoId);
		cursor.close();
		sqlite.close();
		
	}
}
