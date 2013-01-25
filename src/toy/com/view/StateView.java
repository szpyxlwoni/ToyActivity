package toy.com.view;

import toy.com.model.CommodityInfoDao;
import toy.com.model.ToyExploitDao;
import toy.com.model.ToyGoodsDao;
import toy.com.model.ToyInfo;
import toy.com.model.ToyInfoDao;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StateView extends Activity{
    /** Called when the activity is first created. */
	private Button gobackButton;
	private ToyInfo toyinfo;
	private ToyInfoDao toyinfodao = new ToyInfoDao(this);
	private ToyExploitDao toyexploitdao = new ToyExploitDao(this);
	private ToyGoodsDao toygoodsdao = new ToyGoodsDao(this);
	private CommodityInfoDao cominfodao = new CommodityInfoDao(this); 
	private TextView nickname;
	private TextView birthday;
	private TextView money;
	private TextView hungry;
	private TextView clean;
	private TextView mood;
	private TextView power;
	private TextView intelligence;
	private TextView aglie;
	private TextView fightnum;
	private TextView fightwin;
	private TextView fightrate;
	private TextView defense;
	private TextView dodge;
	private TextView point;
	private SQLiteDatabase sqlite;
	private Cursor cursor;
	private int InfoId;
	private final float c_defense = (float) 1.0;
	private final float c_dodge = (float) 0.2;
	private final float c_point = (float) 0.1;
	
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        setTitle("宠物详细信息");
        //toyexploitdao.onCreate(toyexploitdao.getWritableDatabase());
        Bundle bundle = this.getIntent().getExtras();
        InfoId = bundle.getInt("InfoId");
        
        gobackButton = (Button)findViewById(R.id.state_gobackbutton);
        gobackButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        nickname = (TextView)findViewById(R.id.info_nickname);
        birthday = (TextView)findViewById(R.id.info_birthday);
        money = (TextView)findViewById(R.id.info_money);
        hungry = (TextView)findViewById(R.id.info_hungry);
        clean = (TextView)findViewById(R.id.info_clean);
        mood = (TextView)findViewById(R.id.info_mood);
        power = (TextView)findViewById(R.id.info_power);
        intelligence = (TextView)findViewById(R.id.info_intelligence);
        aglie = (TextView)findViewById(R.id.info_aglie);
        fightnum = (TextView)findViewById(R.id.info_fightnum);
        fightwin = (TextView)findViewById(R.id.info_fightwin);
        fightrate = (TextView)findViewById(R.id.info_fightrate);
        defense = (TextView)findViewById(R.id.info_defennse);
        dodge = (TextView)findViewById(R.id.info_dodge);
        point = (TextView)findViewById(R.id.info_point);
        
        // 显示宠物基本信息
        sqlite = toyinfodao.getReadableDatabase();
        cursor = sqlite.rawQuery("select * from " + toyinfodao.TABLE_NAME + " where " + toyinfodao.ID + "=" + InfoId, null);
        cursor.moveToFirst();
        if (cursor.getString(cursor.getColumnIndex(toyinfodao.NAME)) != null && !cursor.isAfterLast()){
        	hungry.setText("饥饿度：" + cursor.getInt(cursor.getColumnIndex(toyinfodao.HUNGRY))+"%");
    		clean.setText("清洁度：" + cursor.getInt(cursor.getColumnIndex(toyinfodao.CLEAN))+"%");
    		mood.setText("心情值：" + cursor.getInt(cursor.getColumnIndex(toyinfodao.MOOD))+"%");
    		nickname.setText("昵称：" + cursor.getString(cursor.getColumnIndex(toyinfodao.NAME)));
    		birthday.setText("生日：" + cursor.getString(cursor.getColumnIndex(toyinfodao.BIRTH)).substring(0,10));
    		money.setText("金币值：" + cursor.getInt(cursor.getColumnIndex(toyinfodao.MONEY)) + "");
    		power.setText("力量：" + cursor.getInt(cursor.getColumnIndex(toyinfodao.POWER)) + "");
    		intelligence.setText("智力：" + cursor.getInt(cursor.getColumnIndex(toyinfodao.INTELL)) + "");
    		aglie.setText("敏捷：" + cursor.getInt(cursor.getColumnIndex(toyinfodao.AGLIE)) + "");
        }
        cursor.close();
        sqlite.close();
        //显示宠物战绩信息
        sqlite = toyexploitdao.getWritableDatabase();
        cursor = sqlite.rawQuery("select * from " + toyexploitdao.TABLE_NAME + " where " + toyexploitdao.INFOID + "=" + InfoId, null);
        cursor.moveToFirst();
        if (cursor != null){
        	int sum, win;
        	float rate;
        	
        	sum = cursor.getInt(cursor.getColumnIndex(toyexploitdao.NUMBER));
        	win = cursor.getInt(cursor.getColumnIndex(toyexploitdao.WINNUM));
        	rate = (float)win / (float)sum * 100;
        	fightnum.setText("总计：" + sum + "次");
        	fightwin.setText("胜利：" + win + "次");
        	fightrate.setText("胜率：" + rate + "%");	
        }
        cursor.close();
        sqlite.close();
        //显示宠物装备属性
        sqlite = toygoodsdao.getWritableDatabase();
        /*
         * 
         * select count(*) from toy_goods, toy_commodity where toy_goods.N_COMMODITY_ID=toy
         * commodity.N_COMMODITY_ID AND toy_goods.N_INFO_ID=1 AND toy_commodity.N_COMMODIT
         * Y_FUNCTION=0 AND toy_goods.N_GOODS_NUMBER > 0; 
        _*/
        cursor = sqlite.rawQuery("select count(*) from " 
				+ toygoodsdao.TABLE_NAME + ", " + cominfodao.TABLE_NAME 
				+ " where " + toygoodsdao.INFOID + "=" + InfoId //设置用户ID
				+ " AND " + toygoodsdao.TABLE_NAME + "." + toygoodsdao.COMMODITYID 
				+ "=" + cominfodao.TABLE_NAME + "." + cominfodao.ID
				+ " AND " + cominfodao.FUNCTION + "=4 "
				+ " AND " + toygoodsdao.TABLE_NAME + "." + toygoodsdao.NUMBER + ">0", null);
        Log.v("Calcu Cloth", "select count(*) from " 
				+ toygoodsdao.TABLE_NAME + ", " + cominfodao.TABLE_NAME 
				+ " where " + toygoodsdao.INFOID + "=" + InfoId //设置用户ID
				+ " AND " + toygoodsdao.TABLE_NAME + "." + toygoodsdao.COMMODITYID 
				+ "=" + cominfodao.TABLE_NAME + "." + cominfodao.ID
				+ " AND " + cominfodao.FUNCTION + "=4 "
				+ " AND " + toygoodsdao.TABLE_NAME + "." + toygoodsdao.NUMBER + ">0");
        cursor.moveToFirst();
        if (cursor != null){
        	int num = 0;
        	int sumnum = 0;
        	int tmpnum = 0, tmpeffic;
        	
        	num = cursor.getInt(cursor.getColumnIndex("count(*)"));
        	Log.v("Cloth Number: ", "Number: " + num);
        	cursor.close();
        	cursor = sqlite.rawQuery("select * from " 
    				+ toygoodsdao.TABLE_NAME + ", " + cominfodao.TABLE_NAME 
    				+ " where " + toygoodsdao.INFOID + "=" + InfoId //设置用户ID
    				+ " AND " + toygoodsdao.TABLE_NAME + "." + toygoodsdao.COMMODITYID 
    				+ "=" + cominfodao.TABLE_NAME + "." + cominfodao.ID
    				+ " AND " + cominfodao.FUNCTION + "=4 "
    				+ " AND " + toygoodsdao.TABLE_NAME + "." + toygoodsdao.NUMBER + ">0", null);
        	cursor.moveToFirst();
        	for (int i = 0; i < num; i++, cursor.moveToNext()){
        		tmpnum = cursor.getInt(cursor.getColumnIndex(toygoodsdao.NUMBER));
        		tmpeffic = cursor.getInt(cursor.getColumnIndex(cominfodao.EFFICIENCY));
        		sumnum += tmpnum * tmpeffic;
        		Log.v("Cloth Num", "tempnum:" + tmpnum + "; tempeffic:" + tmpeffic);
        	}
        	Log.v("Cloth Sum", sumnum +"");
        	
        	defense.setText("护甲："+ c_defense * sumnum);
        	dodge.setText("闪避："+ c_dodge * sumnum);
        	point.setText("命中："+ c_point * sumnum);
        }
        cursor.close();
        sqlite.close();
    }
}
