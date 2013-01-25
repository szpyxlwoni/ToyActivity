package toy.com.view;

import java.util.Random;

import toy.com.model.CommodityInfoDao;
import toy.com.model.ToyGoodsDao;
import toy.com.model.ToyInfoDao;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;

public class ShopView extends ExpandableListActivity{
	private ExpandableListAdapter adapter;
	private CommodityInfoDao cominfodao = new CommodityInfoDao(this);
	private ToyGoodsDao toygoodsdao = new ToyGoodsDao(this);
	private SQLiteDatabase sqlite;
	private Cursor cursor;
	private int InfoId;
	private int id[][] = new int[5][];
	private String[][] child = new String[5][];
	private String[][] price = new String[5][];
	private String[][] effic = new String[5][];
//private MyExpandableListAdapter MyAdapter=new MyExpandableListAdapter();

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("�����̳�");
		//�����ݿ��ж���������Ʒ��Ϣ�����ҷ���浽����child�С�
		//cominfodao.onCreate(cominfodao.getWritableDatabase());
		Bundle bundle = this.getIntent().getExtras();
		InfoId = bundle.getInt("InfoId");
		sqlite = cominfodao.getWritableDatabase();
		//�õ�ʳ��Function= 1
		cursor = sqlite.rawQuery("select count(*) from '"+cominfodao.TABLE_NAME+"' where "+ cominfodao.FUNCTION +" = 1", null);
		cursor.moveToFirst();
		int countnum = cursor.getInt(cursor.getColumnIndex("count(*)"));
		cursor.close();
		child[0] = new String[countnum];
		price[0] = new String[countnum];
		effic[0] = new String[countnum];
		id[0] = new int[countnum];
		int i = 0;
		cursor = sqlite.rawQuery("select * from '"+cominfodao.TABLE_NAME+"' where "+ cominfodao.FUNCTION +" = 1", null);
		Log.v("Food Number", "N:"+countnum + " " + "select * from '"+cominfodao.TABLE_NAME+"' where '"+ cominfodao.FUNCTION +"' = 1");
		cursor.moveToFirst();
		while (i < countnum&& !cursor.isAfterLast() && cursor.getString(cursor.getColumnIndex(cominfodao.NAME)) != null){
			child[0][i] = new String();
			price[0][i] = new String();
			effic[0][i] = new String();
			id[0][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.ID));
			child[0][i] = cursor.getString(cursor.getColumnIndex(cominfodao.NAME));
			price[0][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.PRICE)) + "";
			effic[0][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.EFFICIENCY)) + "";
			Log.v("FoodContent", child[0][i] + " " + price[0][i] + " " + effic[0][i]);
			cursor.moveToNext();
			i++;
		}
		cursor.close();
		//�õ������Function=2
		cursor = sqlite.rawQuery("select count(*) from "+cominfodao.TABLE_NAME+" where "+ cominfodao.FUNCTION +" = 2", null);
		cursor.moveToFirst();
		countnum = cursor.getInt(0);
		cursor.close();
		Log.v("Clean Number", "N:"+countnum);
		child[1] = new String[countnum];
		price[1] = new String[countnum];
		effic[1] = new String[countnum];
		id[1] = new int[countnum];
		i = 0;
		cursor = sqlite.rawQuery("select * from "+cominfodao.TABLE_NAME+" where "+ cominfodao.FUNCTION +" = 2", null);
		cursor.moveToFirst();
		while (i < countnum && !cursor.isAfterLast() && cursor.getString(cursor.getColumnIndex(cominfodao.NAME)) != null){
			child[1][i] = new String();
			price[1][i] = new String();
			effic[1][i] = new String();
			id[1][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.ID));
			child[1][i] = cursor.getString(cursor.getColumnIndex(cominfodao.NAME));
			price[1][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.PRICE)) + "";
			effic[1][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.EFFICIENCY)) + "";
			Log.v("CleanContent", child[1][i] + " " + price[1][i] + " " + effic[1][i]);
			cursor.moveToNext();
			i++;
		}
		cursor.close();
		//�õ���ˣ��Function = 3
		cursor = sqlite.rawQuery("select count(*) from "+cominfodao.TABLE_NAME+" where "+ cominfodao.FUNCTION +" = 3", null);
		cursor.moveToFirst();
		countnum = cursor.getInt(0);
		cursor.close();
		Log.v("Cloth Number", "N:"+countnum);
		child[2] = new String[countnum];
		price[2] = new String[countnum];
		effic[2] = new String[countnum];
		id[2] = new int[countnum];
		i = 0;
		cursor = sqlite.rawQuery("select * from "+cominfodao.TABLE_NAME+" where "+ cominfodao.FUNCTION +" = 3", null);
		cursor.moveToFirst();
		while (i < countnum&& !cursor.isAfterLast() && cursor.getString(cursor.getColumnIndex(cominfodao.NAME)) != null){
			child[2][i] = new String();
			price[2][i] = new String();
			effic[2][i] = new String();
			id[2][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.ID));
			child[2][i] = cursor.getString(cursor.getColumnIndex(cominfodao.NAME));
			price[2][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.PRICE)) + "";
			effic[2][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.EFFICIENCY)) + "";
			Log.v("WorkContent", child[2][i] + " " + price[2][i] + " " + effic[2][i]);
			cursor.moveToNext();
			i++;
		}
		cursor.close();
		//�õ�������Function=0
		cursor = sqlite.rawQuery("select count(*) from "+cominfodao.TABLE_NAME+" where "+ cominfodao.FUNCTION +" = 0", null);
		cursor.moveToFirst();
		countnum = cursor.getInt(0);
		cursor.close();
		Log.v("Work Number", "N:"+countnum);
		child[3] = new String[countnum];
		price[3] = new String[countnum];
		effic[3] = new String[countnum];
		id[3] = new int[countnum];
		i = 0;
		cursor = sqlite.rawQuery("select * from "+cominfodao.TABLE_NAME+" where "+ cominfodao.FUNCTION +" = 0", null);
		cursor.moveToFirst();
		while (i < countnum && !cursor.isAfterLast() && cursor.getString(cursor.getColumnIndex(cominfodao.NAME)) != null){
			child[3][i] = new String();
			price[3][i] = new String();
			effic[3][i] = new String();
			id[3][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.ID));
			child[3][i] = cursor.getString(cursor.getColumnIndex(cominfodao.NAME));
			price[3][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.PRICE)) + "";
			effic[3][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.EFFICIENCY)) + "";
			Log.v("ClothContent", child[3][i] + " " + price[3][i] + " " + effic[3][i]);
			cursor.moveToNext();
			i++;
		}
		cursor.close();
		//�õ�װ����Function = 4
		cursor = sqlite.rawQuery("select count(*) from "+cominfodao.TABLE_NAME+" where "+ cominfodao.FUNCTION +" = 4", null);
		cursor.moveToFirst();
		countnum = cursor.getInt(0);
		cursor.close();
		Log.v("Work Number", "N:"+countnum);
		child[4] = new String[countnum];
		price[4] = new String[countnum];
		effic[4] = new String[countnum];
		id[4] = new int[countnum];
		i = 0;
		cursor = sqlite.rawQuery("select * from "+cominfodao.TABLE_NAME+" where "+ cominfodao.FUNCTION +" = 4", null);
		cursor.moveToFirst();
		while (i < countnum && !cursor.isAfterLast() && cursor.getString(cursor.getColumnIndex(cominfodao.NAME)) != null){
			child[4][i] = new String();
			price[4][i] = new String();
			effic[4][i] = new String();
			id[4][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.ID));
			child[4][i] = cursor.getString(cursor.getColumnIndex(cominfodao.NAME));
			price[4][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.PRICE)) + "";
			effic[4][i] = cursor.getInt(cursor.getColumnIndex(cominfodao.EFFICIENCY)) + "";
			Log.v("ClothContent", child[4][i] + " " + price[4][i] + " " + effic[4][i]);	
			cursor.moveToNext();
			i++;
		}
		cursor.close();
		sqlite.close();
		
		adapter=new MyExpandableListAdapter(child);
		setListAdapter(adapter);
		registerForContextMenu(getExpandableListView());
		this.getWindow().setBackgroundDrawableResource(R.drawable.pic_shop);
	}
	
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			final int groupPosition, final int childPosition, long lid) {
		// TODO Auto-generated method stub
		this.setTitle("helloworld Group: " + groupPosition + "Child: " + childPosition );
		String chosen = new String();
		if (groupPosition == 0){
			chosen = "������";
		}
		else if(groupPosition == 1){
			chosen = "����";
		}
		else if(groupPosition == 2){
			chosen = "����ֵ";
		}
		else if (groupPosition == 4){
			chosen = "װ������";
		}
		if (groupPosition != 3){
			AlertDialog ad = new AlertDialog.Builder(ShopView.this)
			.setTitle("ȷ��Ҫ�������Ʒô��")
			.setMessage(chosen + "+" + effic[groupPosition][childPosition] + " ���� ��" + price[groupPosition][childPosition])
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int had_count = -1;
					ContentValues cv = new ContentValues();
					setTitle("�ɹ���������Ʒ!");
					sqlite = cominfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select count(*) from " + toygoodsdao.TABLE_NAME + " where " 
							+ toygoodsdao.INFOID + "=" + InfoId + " AND " + toygoodsdao.COMMODITYID + "=" + id[groupPosition][childPosition], null);
					cursor.moveToNext();
					if (cursor != null){
						had_count = cursor.getInt(cursor.getColumnIndex("count(*)"));
					}
					cursor.close();
					if (had_count == 0){
						cv.put(toygoodsdao.COMMODITYID, id[groupPosition][childPosition]);
						cv.put(toygoodsdao.INFOID, InfoId);
						cv.put(toygoodsdao.NUMBER, "1");
						sqlite.insert(toygoodsdao.TABLE_NAME, toygoodsdao.NUMBER + ", " + toygoodsdao.COMMODITYID + ", " + toygoodsdao.INFOID, cv);
					}
					else {
						sqlite.execSQL("update " + toygoodsdao.TABLE_NAME + " set " + toygoodsdao.NUMBER + "=" + toygoodsdao.NUMBER + "+1 where " 
								+ toygoodsdao.COMMODITYID + "=" + id[groupPosition][childPosition] + " AND " + toygoodsdao.INFOID + "=" + InfoId);
					}
					sqlite.execSQL("update " + ToyInfoDao.TABLE_NAME + " set " + ToyInfoDao.MONEY + "=" 
							+ ToyInfoDao.MONEY + "-" + price[groupPosition][childPosition]);
					sqlite.close();
				}
			})
			.setNegativeButton("����",null)
			.show();
		}
		else{
			AlertDialog ad = new AlertDialog.Builder(ShopView.this)
			.setTitle("ȷ��Ҫ��ʼ��ô��")
			.setMessage("�����ȡ����ȡ�����ֵ���ή��" + " ���� ��" + price[groupPosition][childPosition])
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int had_count = -1;
					Random rd = new Random();
					int down_hungry, down_clean, down_mood;
					
					down_hungry = rd.nextInt(Integer.parseInt(effic[groupPosition][childPosition]));
					down_clean = rd.nextInt(Integer.parseInt(effic[groupPosition][childPosition]));
					down_mood = rd.nextInt(Integer.parseInt(effic[groupPosition][childPosition]));
					setTitle("�ɹ�����ɴ�!");
					sqlite = cominfodao.getWritableDatabase();
					
					sqlite.execSQL("update " + ToyInfoDao.TABLE_NAME + " set " 
							+ ToyInfoDao.MONEY + "=" + ToyInfoDao.MONEY + "+" + price[groupPosition][childPosition] + ", " 
							+ ToyInfoDao.HUNGRY + "=" + ToyInfoDao.HUNGRY + "-" + down_hungry + ", "
							+ ToyInfoDao.CLEAN + "=" + ToyInfoDao.CLEAN + "-" + down_clean + ", "
							+ ToyInfoDao.MOOD + "= " + ToyInfoDao.MOOD + "-" + down_mood
					);
					sqlite.close();
				}
			})
			.setNegativeButton("����",null)
			.show();
		}
		return super.onChildClick(parent, v, groupPosition, childPosition, lid);
	}

	/* (non-Javadoc)
	 * @see android.app.ExpandableListActivity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("�˵�");
		menu.add(0, 0, 0, "Action");
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		boolean flag=false;
		// TODO Auto-generated method stub
		ExpandableListContextMenuInfo menuInfo=(ExpandableListContextMenuInfo)item.getMenuInfo();
		String title=((TextView)menuInfo.targetView).getText().toString();
		int type=ExpandableListView.getPackedPositionType(menuInfo.packedPosition);
		
		if (type==ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
		int	groupPos =ExpandableListView.getPackedPositionGroup(menuInfo.packedPosition);
		int	childPos =ExpandableListView.getPackedPositionChild(menuInfo.packedPosition);
		
		CharSequence str="��������"+title;
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		Log.i("tag", "Run Hereing...");
		
		flag= true;
		}
		
		else
			 if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
	            int groupPos = ExpandableListView.getPackedPositionGroup(menuInfo.packedPosition); 
	            CharSequence cs="��������"+title;
	            Toast.makeText(this, cs, Toast.LENGTH_SHORT).show();
	            Log.i("tag", "Run Here...");
	            flag= true;
	        }
		return flag;
	}
	
	 public class MyExpandableListAdapter extends BaseExpandableListAdapter {
	        // Sample data set.  children[i] contains the children (String[]) for groups[i].
		 	
	        public String[] groups = { "ʳƷ��", "�����", "��ˣ��", "������", "װ����" };
	        public String[][] children = {
	                { "11", "22", "33", "44" },
	                { "11", "22", "33", "44" },
	                { "11", "22" },
	                { "11", "11" }
	        };
	        
	        public MyExpandableListAdapter(String[][] children) {
				super();
				this.children = children;
			}

			public Object getChild(int groupPosition, int childPosition) {
	            return children[groupPosition][childPosition];
	        }

	        public long getChildId(int groupPosition, int childPosition) {
	            return childPosition;
	        }

	        public int getChildrenCount(int groupPosition) {
	            return children[groupPosition].length;
	        }

	        
	        public TextView getGenericView() {
	            // Layout parameters for the ExpandableListView
	            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
	                    ViewGroup.LayoutParams.MATCH_PARENT, 64);

	            TextView textView = new TextView(ShopView.this);
	            textView.setLayoutParams(lp);
	            // Center the text vertically
	            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
	            // Set the text starting position
	            textView.setPadding(36, 0, 0, 0);
	            textView.setTextColor(R.color.white);
	            return textView;
	        }
	        
	        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
	                View convertView, ViewGroup parent) {
	            TextView textView = getGenericView();
	            textView.setText(getChild(groupPosition, childPosition).toString());
	            //textView.setTextColor(R.color.crimson);
	            textView.setBackgroundResource(R.color.translucenceblue);
	            return textView;
	        }

	        public Object getGroup(int groupPosition) {
	            return groups[groupPosition];
	        }

	        public int getGroupCount() {
	            return groups.length;
	        }

	        public long getGroupId(int groupPosition) {
	            return groupPosition;
	        }

	        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
	                ViewGroup parent) {
	            TextView textView = getGenericView();
	            textView.setText(getGroup(groupPosition).toString());
	            textView.setTextSize(20);
//	            textView.setBackgroundResource(R.drawable.buttombackg);
	            //textView.setTextColor(R.color.white);
	            return textView;
	        }

	        public boolean isChildSelectable(int groupPosition, int childPosition) {
	            return true;
	        }

	        public boolean hasStableIds() {
	            return true;
	        }

	    }

}
