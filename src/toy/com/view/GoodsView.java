package toy.com.view;

import java.util.ArrayList;
import java.util.HashMap;

import toy.com.model.CommodityInfoDao;
import toy.com.model.ToyGoods;
import toy.com.model.ToyGoodsDao;
import toy.com.tools.GoodsUserListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class GoodsView extends Activity {
	private Button gobackButton;
	private ToyGoodsDao toygoodsdao = new ToyGoodsDao(this);
	private CommodityInfoDao cominfodao = new CommodityInfoDao(this);
	private Cursor cursor;
	private SQLiteDatabase sqlite;
	private ContentValues cv = new ContentValues();
	private ToyGoods[] ar_toygoods;
	private SimpleAdapter myadapter;
	private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	private HashMap<String, Object> map;
	private String c_title[];
	private String c_info[];
	private String c_number[];
	private int c_function[];
	private int countnum;
	private int InfoId;
	
	public void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.bag);
		setTitle("���ﱳ������");
		//toygoodsdao.onCreate(toygoodsdao.getWritableDatabase());
		Bundle bundle = this.getIntent().getExtras();
		InfoId = bundle.getInt("InfoId");
		Log.v("Get InfoId", "ID:" + InfoId);
		gobackButton = (Button)findViewById(R.id.bag_gobackbutton);
		gobackButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		ArrayList<HashMap<String, Object>> mData;
		ListView listv = (ListView)findViewById(R.id.BagList);
		
		mData = getData();
		myadapter = new SimpleAdapter(this, mData, R.layout.bagitem, 
				new String[] {"title", "info", "number"}, 
				new int[] {R.id.GoodsTitle, R.id.GoodsItem, R.id.GoodsNumber});
		listv.setAdapter(myadapter);
		
		listv.setOnItemClickListener(new OnItemClickListener(){
			int id;
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				setTitle("������"+ arg2+"����Ʒ");
				id = arg2;
				
				AlertDialog ad = new AlertDialog.Builder(GoodsView.this)
				.setTitle("ȷ��ʹ��"+(arg2 + 1))
				.setMessage("��ȷ��Ҫʹ��No."+ (arg2 + 1) + "��Ʒô��")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (c_function[id] == 4){
							AlertDialog ad = new AlertDialog.Builder(GoodsView.this)
							.setTitle("ʹ��ʧ��")
							.setMessage("װ���ǲ��ܱ�ʹ�õ�Ŷ����")
							.setPositiveButton("ȷ��", null)
							.show();
							return;
						}
						setTitle(countnum + "��ʹ����"+(id + 1)+"��Ʒ,������"+ ar_toygoods[id].getGoodsNum());
						sqlite = toygoodsdao.getWritableDatabase();
						//map = new HashMap<String, Object>();
						if (ar_toygoods[id].getGoodsNum() == 1){
							sqlite.delete(toygoodsdao.TABLE_NAME, toygoodsdao.ID + "=" + ar_toygoods[id].getGoodsID(), null);
							list.remove(id);
						}
						else{
							ContentValues cv = new ContentValues();
							cv.put(toygoodsdao.NUMBER, (ar_toygoods[id].getGoodsNum() - 1) + "");
							sqlite.update(toygoodsdao.TABLE_NAME, cv, toygoodsdao.ID + "=" + ar_toygoods[id].getGoodsID(),  null);
							map = list.get(id);
							map.put("number", (ar_toygoods[id].getGoodsNum() - 1) + "��");
							ar_toygoods[id].setGoodsNum(ar_toygoods[id].getGoodsNum() - 1);
							list.set(id, map);				
							
							
						}
						myadapter.notifyDataSetChanged();
						GoodsUserListener gul = new GoodsUserListener();
						gul.UpGoodsUse(InfoId, ar_toygoods[id].getGoodsID());
						sqlite.close();
					}
				})
				.setNegativeButton("����",null)
				.show();
			}
			
			
		});
		
		//��ӳ������  
        listv.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
              
            public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
                menu.setHeaderTitle("�����˵�-ContextMenu");     
                menu.add(0, 0, 0, "���������˵�0");  
                menu.add(0, 1, 0, "���������˵�1");     
            }  
        });  
	}

	@Override  
    public boolean onContextItemSelected(MenuItem item) {  
        setTitle("����˳����˵�����ĵ�"+item.getItemId()+"����Ŀ");   
        return super.onContextItemSelected(item);  
    } 
	
	private ArrayList<HashMap<String, Object>> getData(){
		//map = new HashMap<String, Object>()
		int i;
		int classes, efficiency;
		String info = null;
		countnum = 0;
		//�����ݿ����������
		sqlite = toygoodsdao.getWritableDatabase();
		cursor = sqlite.rawQuery("select count(*) from " 
				+ toygoodsdao.TABLE_NAME + ", " + cominfodao.TABLE_NAME 
				+ " where " + toygoodsdao.INFOID + "=" + InfoId //�����û�ID
				+ " AND " + toygoodsdao.TABLE_NAME + "." + toygoodsdao.COMMODITYID 
				+ "=" + cominfodao.TABLE_NAME + "." + cominfodao.ID
				+ " AND " + cominfodao.FUNCTION + "<>0", null);
		cursor.moveToFirst();
		//Log.v("Goods Number", "select count(*) from " + toygoodsdao.TABLE_NAME + " where " + toygoodsdao.INFOID + "=" + InfoId);
		if (cursor != null){
			countnum = cursor.getInt(cursor.getColumnIndex("count(*)"));
		}
		c_title = new String[countnum];
		c_info = new String[countnum];
		c_number = new String[countnum];
		ar_toygoods = new ToyGoods[countnum];
		c_function = new int[countnum];
		cursor.close();
		cursor = sqlite.rawQuery("select * from " 
				+ toygoodsdao.TABLE_NAME + ", " + cominfodao.TABLE_NAME 
				+ " where " + toygoodsdao.INFOID + "=" + InfoId //�����û�ID
				+ " AND " + toygoodsdao.TABLE_NAME + "." + toygoodsdao.COMMODITYID 
				+ "=" + cominfodao.TABLE_NAME + "." + cominfodao.ID
				+ " AND " + cominfodao.FUNCTION + "<>0", null);
		cursor.moveToFirst();
		for (i = 0; i < countnum; i++, cursor.moveToNext()){
			map = new HashMap<String, Object>();
			c_title[i] = new String();
			c_info[i] = new String();
			c_number[i] = new String();
			ar_toygoods[i] = new ToyGoods();
			classes =cursor.getInt(cursor.getColumnIndex(cominfodao.FUNCTION));
			c_function[i] = classes;
			efficiency = cursor.getInt(cursor.getColumnIndex(cominfodao.EFFICIENCY));
			ar_toygoods[i].setGoodsID(cursor.getInt(cursor.getColumnIndex(toygoodsdao.ID)));
			ar_toygoods[i].setGoodsNum(cursor.getInt(cursor.getColumnIndex(toygoodsdao.NUMBER)));
			
			map.put("title", cursor.getString(cursor.getColumnIndex(cominfodao.NAME)));
			c_title[i] = (String)map.get("title");
			if (classes == 0){
				continue;
			} 
			else if (classes == 1){
				info = "������ + " + efficiency;
			}
			else if (classes == 2){
				info = "���� + " + efficiency;
			}
			else if (classes == 3){
				info = "����ֵ + " + efficiency;
			}
			else if (classes == 4){
				info = "װ������ + " + efficiency;
			}
			map.put("info", info);
			c_info[i] = (String)map.get("info");
			map.put("number", cursor.getInt(cursor.getColumnIndex(toygoodsdao.NUMBER)) + "��");
			c_number[i] = (String)map.get("number");
			list.add(map);	
			
		}
		cursor.close();
		sqlite.close();
		return list;
	}
}
