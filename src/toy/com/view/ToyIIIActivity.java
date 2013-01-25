package toy.com.view;


import java.util.Timer;
import java.util.TimerTask;

import toy.com.model.CommodityInfoDao;
import toy.com.model.ToyExploitDao;
import toy.com.model.ToyGoodsDao;
import toy.com.model.ToyInfoDao;
import toy.com.tools.GoodsUserListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ToyIIIActivity extends Activity implements OnClickListener{
	private Button testbutton;
	private Button freshbutton;
	private Button bagbutton;
	private Button shopButton;
	private Button feedButton;
	private Button showerButton;
	private Button workButton;
	private ImageView clockImageView;
	private ImageView toyfaceImageView;
	private TextView Hungry;
	private TextView Clean;
	private TextView Mood;
	private TextView Exercise;
	private ToyInfoDao toyinfodao = new ToyInfoDao(this);
	private CommodityInfoDao cominfodao = new CommodityInfoDao(this);
	private ToyExploitDao toyexploitdao = new ToyExploitDao(this);
	private ToyGoodsDao toygoodsdao = new ToyGoodsDao(this);
	
	private SQLiteDatabase sqlite;
	private Cursor cursor;
	private int InfoId = 1;
	
	Timer timer = new Timer();   
    Handler handler = new Handler(){   
    	public void handleMessage(Message msg) {   
    		switch (msg.what) {   
		     	case 1:   
			     	setContentView(R.layout.main);  
			     	break;   
		    }   
		    super.handleMessage(msg);   
	    }   
    };   
    TimerTask task = new TimerTask(){   
	    public void run() {   
		    Message message = new Message();   
		    message.what = 1;   
		    handler.sendMessage(message);   
	    }   
    }; 
    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        setTitle("宠物大作战");
        //timer.schedule(task, 10000);
        /*
         * 初始化数据库，仅第一次运行使用，之后需要注释掉这句 
         */
        this.Initdata();
        
        setContentView(R.layout.main);
        testbutton = (Button)findViewById(R.id.gobackbutton);
        testbutton.setOnClickListener(this);
        freshbutton = (Button)findViewById(R.id.freshbutton);
        freshbutton.setOnClickListener(this);
        bagbutton = (Button)findViewById(R.id.im_main_bag);
        bagbutton.setOnClickListener(this);
        shopButton = (Button)findViewById(R.id.im_main_shopping);
        shopButton.setOnClickListener(this);
        feedButton = (Button)findViewById(R.id.im_main_feed);
        feedButton.setOnClickListener(this);
        showerButton = (Button)findViewById(R.id.im_main_shower);
        showerButton.setOnClickListener(this);
        workButton = (Button)findViewById(R.id.im_main_work);
        workButton.setOnClickListener(this);
        clockImageView = (ImageView)findViewById(R.id.im_main_clock);
        clockImageView.setOnClickListener(this);
        toyfaceImageView = (ImageView)findViewById(R.id.im_main_toyface);
        Exercise = (TextView)findViewById(R.id.ct_main_exercise);
        Exercise.setOnClickListener(this);
        sqlite = toyinfodao.getWritableDatabase();
//       sqlite.execSQL("INSERT INTO toy_info VALUES(,'LittleQ','2012-06-06 08:29:41',85,35,50,10,10,10,10000,1);");
        
        Hungry = (TextView)findViewById(R.id.vt_main_hungry);
        Clean = (TextView)findViewById(R.id.vt_main_clean);
        Mood = (TextView)findViewById(R.id.vt_main_mood);
        cursor = sqlite.rawQuery("select * from "+ toyinfodao.TABLE_NAME, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast() && cursor.getString(cursor.getColumnIndex(toyinfodao.NAME)) != null){
        		Hungry.setText(cursor.getInt(cursor.getColumnIndex(toyinfodao.HUNGRY))+"%");
        		Clean.setText(cursor.getInt(cursor.getColumnIndex(toyinfodao.CLEAN))+"%");
        		Mood.setText(cursor.getInt(cursor.getColumnIndex(toyinfodao.MOOD))+"%");
        	//setTitle(cursor.getCount());
        	//Hungry.setText("200$");
        }
        else{
        	setTitle("找不到！");
        }
        cursor.close();
        sqlite.close();
        restartanim();
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.gobackbutton){
			finish();
		}
		else if (v.getId() == R.id.freshbutton){
//			Intent intent = new Intent(this, this.getClass());
//			startActivity(intent);
		}
		else if (v.getId() == R.id.im_main_shower){
			
			AlertDialog ad = new AlertDialog.Builder(ToyIIIActivity.this)
			.setTitle("洗澡确认")
			.setMessage("你确定要给宠物洗澡么？")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int countnum = -1;
					int goodsnum = -1;
					int comid = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select count(*) from " 
							+ ToyGoodsDao.TABLE_NAME + ", " + CommodityInfoDao.TABLE_NAME 
							+ " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
							+ " AND " + ToyGoodsDao.TABLE_NAME + "." + ToyGoodsDao.COMMODITYID 
							+ "=" + CommodityInfoDao.TABLE_NAME + "." + CommodityInfoDao.ID
							+ " AND " + CommodityInfoDao.FUNCTION + "=2", null);
					cursor.moveToFirst();
					//Log.v("Goods Number", "select count(*) from " + toygoodsdao.TABLE_NAME + " where " + toygoodsdao.INFOID + "=1");
					if (cursor != null){
						countnum = cursor.getInt(cursor.getColumnIndex("count(*)"));
					}
					cursor.close();
					if (countnum == 0){
						AlertDialog ad = new AlertDialog.Builder(ToyIIIActivity.this)
						.setTitle("洗澡失败")
						.setMessage("抱歉，你已经没有清洁用品了！")
						.setPositiveButton("确定", null)
						.show();
						return ;
					}
					else {
						cursor = sqlite.rawQuery("select * from " 
								+ ToyGoodsDao.TABLE_NAME + ", " + CommodityInfoDao.TABLE_NAME 
								+ " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
								+ " AND " + ToyGoodsDao.TABLE_NAME + "." + ToyGoodsDao.COMMODITYID 
								+ "=" + CommodityInfoDao.TABLE_NAME + "." + CommodityInfoDao.ID
								+ " AND " + CommodityInfoDao.FUNCTION + "=2", null);
						cursor.moveToFirst();
						if (cursor != null){
							comid = cursor.getInt(cursor.getColumnIndex(CommodityInfoDao.ID));
							GoodsUserListener gul = new GoodsUserListener();
							gul.UpGoodsUse(InfoId, comid);
						}
						cursor.close();
						cursor = sqlite.rawQuery("select * from " 
								+ ToyGoodsDao.TABLE_NAME + " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
								+ " AND " + CommodityInfoDao.ID + "=" + comid, null);
						cursor.moveToFirst();
						if (cursor != null){
							goodsnum = cursor.getInt(cursor.getColumnIndex(ToyGoodsDao.NUMBER)); 
						}
						if (countnum == 1){
							sqlite.delete(ToyGoodsDao.TABLE_NAME, CommodityInfoDao.ID + "=" + comid, null);
						}
						else{
							ContentValues cv = new ContentValues();
							cv.put(ToyGoodsDao.NUMBER, (goodsnum - 1) + "");
							sqlite.update(ToyGoodsDao.TABLE_NAME, cv, CommodityInfoDao.ID + "=" + comid,  null);		
						}
						
					}
					cursor.close();
					sqlite.close();
					
					//播放逐帧动画
					setTitle("清洁成功");
					toyfaceImageView.setBackgroundResource
						(R.drawable.cleanlist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("算了",null)
			.show();
			
			//Drawable last = toyfaceImageView.getBackground();
			
			//restartanim();
			//toyfaceImageView.setBackgroundDrawable(last);
		}
		else if (v.getId() == R.id.im_main_feed){
			AlertDialog ad = new AlertDialog.Builder(ToyIIIActivity.this)
			.setTitle("喂食确认")
			.setMessage("你确定要给宠物喂食么？")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int countnum = -1;
					int goodsnum = -1;
					int comid = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select count(*) from " 
							+ ToyGoodsDao.TABLE_NAME + ", " + CommodityInfoDao.TABLE_NAME 
							+ " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
							+ " AND " + ToyGoodsDao.TABLE_NAME + "." + ToyGoodsDao.COMMODITYID 
							+ "=" + CommodityInfoDao.TABLE_NAME + "." + CommodityInfoDao.ID
							+ " AND " + CommodityInfoDao.FUNCTION + "=1", null);
					cursor.moveToFirst();
					//Log.v("Goods Number", "select count(*) from " + toygoodsdao.TABLE_NAME + " where " + toygoodsdao.INFOID + "=1");
					if (cursor != null){
						countnum = cursor.getInt(cursor.getColumnIndex("count(*)"));
					}
					cursor.close();
					if (countnum == 0){
						AlertDialog ad = new AlertDialog.Builder(ToyIIIActivity.this)
						.setTitle("喂养失败")
						.setMessage("抱歉，你已经没有食物了！")
						.setPositiveButton("确定", null)
						.show();
						return ;
					}
					else {
						cursor = sqlite.rawQuery("select * from " 
								+ ToyGoodsDao.TABLE_NAME + ", " + CommodityInfoDao.TABLE_NAME 
								+ " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
								+ " AND " + ToyGoodsDao.TABLE_NAME + "." + ToyGoodsDao.COMMODITYID 
								+ "=" + CommodityInfoDao.TABLE_NAME + "." + CommodityInfoDao.ID
								+ " AND " + CommodityInfoDao.FUNCTION + "=1", null);
						Log.v("Get Feed Goods", "select * from " 
								+ ToyGoodsDao.TABLE_NAME + ", " + CommodityInfoDao.TABLE_NAME 
								+ " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
								+ " AND " + ToyGoodsDao.TABLE_NAME + "." + ToyGoodsDao.COMMODITYID 
								+ "=" + CommodityInfoDao.TABLE_NAME + "." + CommodityInfoDao.ID
								+ " AND " + CommodityInfoDao.FUNCTION + "=1");
						cursor.moveToFirst();
						if (cursor != null){
							comid = cursor.getInt(cursor.getColumnIndex(CommodityInfoDao.ID));
							GoodsUserListener gul = new GoodsUserListener();
							gul.UpGoodsUse(InfoId, comid);
						}
						cursor.close();
						cursor = sqlite.rawQuery("select * from " 
								+ ToyGoodsDao.TABLE_NAME + " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
								+ " AND " + CommodityInfoDao.ID + "=" + comid, null);
						cursor.moveToFirst();
						if (cursor != null){
							goodsnum = cursor.getInt(cursor.getColumnIndex(ToyGoodsDao.NUMBER)); 
						}
						if (goodsnum == 1){
							sqlite.delete(ToyGoodsDao.TABLE_NAME, CommodityInfoDao.ID + "=" + comid, null);
						}
						else{
							ContentValues cv = new ContentValues();
							cv.put(ToyGoodsDao.NUMBER, (goodsnum - 1) + "");
							sqlite.update(ToyGoodsDao.TABLE_NAME, cv, CommodityInfoDao.ID + "=" + comid,  null);		
						}
					}
					cursor.close();
					sqlite.close();
					
					//播放逐帧动画
					setTitle("喂食成功");
					toyfaceImageView.setBackgroundResource
					(R.drawable.eatlist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("算了",null)
			.show();
			
		}
		else if (v.getId() == R.id.im_main_work){
			AlertDialog ad = new AlertDialog.Builder(ToyIIIActivity.this)
			.setTitle("玩耍确认")
			.setMessage("你确定要陪宠物玩耍么？")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int countnum = -1;
					int goodsnum = -1;
					int comid = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select count(*) from " 
							+ ToyGoodsDao.TABLE_NAME + ", " + CommodityInfoDao.TABLE_NAME 
							+ " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
							+ " AND " + ToyGoodsDao.TABLE_NAME + "." + ToyGoodsDao.COMMODITYID 
							+ "=" + CommodityInfoDao.TABLE_NAME + "." + CommodityInfoDao.ID
							+ " AND " + CommodityInfoDao.FUNCTION + "=3", null);
					cursor.moveToFirst();
					//Log.v("Goods Number", "select count(*) from " + toygoodsdao.TABLE_NAME + " where " + toygoodsdao.INFOID + "=1");
					if (cursor != null){
						countnum = cursor.getInt(cursor.getColumnIndex("count(*)"));
					}
					cursor.close();
					if (countnum == 0){
						AlertDialog ad = new AlertDialog.Builder(ToyIIIActivity.this)
						.setTitle("玩耍失败")
						.setMessage("抱歉，你已经没有玩具了！")
						.setPositiveButton("确定", null)
						.show();
						return ;
					}
					else {
						cursor = sqlite.rawQuery("select * from " 
								+ ToyGoodsDao.TABLE_NAME + ", " + CommodityInfoDao.TABLE_NAME 
								+ " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
								+ " AND " + ToyGoodsDao.TABLE_NAME + "." + ToyGoodsDao.COMMODITYID 
								+ "=" + CommodityInfoDao.TABLE_NAME + "." + CommodityInfoDao.ID
								+ " AND " + CommodityInfoDao.FUNCTION + "=3", null);
						Log.v("Get Feed Goods", "select * from " 
								+ ToyGoodsDao.TABLE_NAME + ", " + CommodityInfoDao.TABLE_NAME 
								+ " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
								+ " AND " + ToyGoodsDao.TABLE_NAME + "." + ToyGoodsDao.COMMODITYID 
								+ "=" + CommodityInfoDao.TABLE_NAME + "." + CommodityInfoDao.ID
								+ " AND " + CommodityInfoDao.FUNCTION + "=3");
						cursor.moveToFirst();
						if (cursor != null){
							comid = cursor.getInt(cursor.getColumnIndex(CommodityInfoDao.ID));
							GoodsUserListener gul = new GoodsUserListener();
							gul.UpGoodsUse(InfoId, comid);
						}
						cursor.close();
						cursor = sqlite.rawQuery("select * from " 
								+ ToyGoodsDao.TABLE_NAME + " where " + ToyGoodsDao.INFOID + "=" + InfoId //设置用户ID
								+ " AND " + CommodityInfoDao.ID + "=" + comid, null);
						cursor.moveToFirst();
						if (cursor != null){
							goodsnum = cursor.getInt(cursor.getColumnIndex(ToyGoodsDao.NUMBER)); 
						}
						if (countnum == 1){
							sqlite.delete(ToyGoodsDao.TABLE_NAME, CommodityInfoDao.ID + "=" + comid, null);
						}
						else{
							ContentValues cv = new ContentValues();
							cv.put(ToyGoodsDao.NUMBER, (goodsnum - 1) + "");
							sqlite.update(ToyGoodsDao.TABLE_NAME, cv, CommodityInfoDao.ID + "=" + comid,  null);		
						}
					}
					cursor.close();
					sqlite.close();
					
					//播放逐帧动画
					setTitle("玩耍成功");
					toyfaceImageView.setBackgroundResource
					(R.drawable.worklist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("算了",null)
			.show();
					
		}
		else if (v.getId() == R.id.im_main_bag){
			Intent intent = new Intent(this, GoodsView.class);
			Bundle bundle = new Bundle();
			bundle.putInt("InfoId", InfoId);
			intent.putExtras(bundle);
			startActivity(intent);	
		}
		else if (v.getId() == R.id.im_main_shopping){
			Intent intent = new Intent(this, ShopView.class);
			Bundle bundle = new Bundle();
			bundle.putInt("InfoId", InfoId);
			intent.putExtras(bundle);
			startActivity(intent);	
			//startActivity(new Intent(this, ShopView.class));
		}
		else if (v.getId() == R.id.im_main_clock){
			Intent intent = new Intent(this, StateView.class);
			Bundle bundle = new Bundle();
			bundle.putInt("InfoId", InfoId);
			intent.putExtras(bundle);
			startActivity(intent);
			//startActivity(new Intent(this, StateView.class));
		}
		else if (v.getId() == R.id.ct_main_exercise){
			Intent intent = new Intent(this, ExerciseView.class);
			Bundle bundle = new Bundle();
			bundle.putInt("InfoId", InfoId);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}
	
	public void restartanim(){
		//this.setTitle("宠物大作战");
		toyfaceImageView.setBackgroundResource
		(R.drawable.greetlist);
	AnimationDrawable ad = 
		(AnimationDrawable)toyfaceImageView.getBackground();
		ad.start();
	}
	
	public void Initdata() {
		// TODO Auto-generated method stub
		
		SQLiteDatabase sqlite = toyinfodao.getWritableDatabase();
		if (!tabIsExist(sqlite, toyinfodao.TABLE_NAME)){
			toyinfodao.onCreate(sqlite);
		}
		sqlite.close();
		sqlite = cominfodao.getWritableDatabase();
		if (!tabIsExist(sqlite, cominfodao.TABLE_NAME)){
			cominfodao.onCreate(sqlite);
		}
		sqlite.close();
		
		sqlite = toygoodsdao.getWritableDatabase();
		if (!tabIsExist(sqlite, toygoodsdao.TABLE_NAME)){
			toygoodsdao.onCreate(sqlite);
		}
		sqlite.close();
		sqlite = toyexploitdao.getWritableDatabase();
		if (!tabIsExist(sqlite, toyexploitdao.TABLE_NAME)){
			toyexploitdao.onCreate(sqlite);
		}
		sqlite.close();
	}
	
	  public boolean tabIsExist(SQLiteDatabase sqlite,String tabName){
          boolean result = false;
          if(tabName == null){
                  return false;
          }
          Cursor cursor = null;
          try {
                 
                  String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"+tabName.trim()+"' ";
                  cursor = sqlite.rawQuery(sql, null);
                  if(cursor.moveToNext()){
                          int count = cursor.getInt(0);
                          if(count>0){
                                  result = true;
                          }
                  }
                  cursor.close();
          } catch (Exception e) {
                  // TODO: handle exception
          }                
          return result;
  }
}