package toy.com.view;

import toy.com.model.CommodityInfoDao;
import toy.com.model.ToyGoodsDao;
import toy.com.model.ToyInfoDao;
import toy.com.tools.GoodsUserListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ExerciseView extends Activity implements OnClickListener {
	private Button gobackButton;
	private ImageView moningImageView;
	private ImageView trainImageView;
	private ImageView readImageView;
	private ImageView thinkImageView;
	private ImageView swinImageView;
	private ImageView longImageView;
	private ImageView toyfaceImageView;
	private int InfoId;
	private ToyInfoDao toyinfodao = new ToyInfoDao(this);
	private SQLiteDatabase sqlite;
	private Cursor cursor;

    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise);
		setTitle("����ѵ����");
		Bundle bundle = this.getIntent().getExtras();
		InfoId = bundle.getInt("InfoId");
		Log.v("Get InfoId", "ID:" + InfoId);
		gobackButton = (Button)findViewById(R.id.gobackbutton);
		gobackButton.setOnClickListener(this);
		moningImageView = (ImageView)findViewById(R.id.exe_moning);
		moningImageView.setOnClickListener(this);
		trainImageView = (ImageView)findViewById(R.id.exe_train);
		trainImageView.setOnClickListener(this);
		readImageView = (ImageView)findViewById(R.id.exe_read);
		readImageView.setOnClickListener(this);
		thinkImageView = (ImageView)findViewById(R.id.exe_think);
		thinkImageView.setOnClickListener(this);
		swinImageView = (ImageView)findViewById(R.id.exe_swim);
		swinImageView.setOnClickListener(this);
		longImageView = (ImageView)findViewById(R.id.exe_long);
		longImageView.setOnClickListener(this);
		toyfaceImageView = (ImageView)findViewById(R.id.init_img);
    }
    
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	if (v.getId() == R.id.gobackbutton){
			finish();
		}
        else if (v.getId() == R.id.exe_moning){
			AlertDialog ad = new AlertDialog.Builder(ExerciseView.this)
			.setTitle("���ȷ��")
			.setMessage("��ȷ��Ҫ�ó���������ô��")
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int power = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select " + ToyInfoDao.POWER + " from " 
							+ ToyInfoDao.TABLE_NAME + " where " + ToyInfoDao.ID + "=" + InfoId, null);
					cursor.moveToFirst();
					if (cursor != null){
						power = cursor.getInt(cursor.getColumnIndex(ToyInfoDao.POWER));
					}
					cursor.close();
					ContentValues cv = new ContentValues();
					cv.put(ToyInfoDao.POWER, power + 1);
					sqlite.update(ToyInfoDao.TABLE_NAME, cv, ToyInfoDao.ID + "=" + InfoId, null);
					sqlite.close();
					
					//������֡����
					setTitle("��ٳɹ�");
					toyfaceImageView.setBackgroundResource
						(R.drawable.moninglist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("����",null)
			.show();
			
			//Drawable last = toyfaceImageView.getBackground();
			
			//restartanim();
			//toyfaceImageView.setBackgroundDrawable(last);
		}
        else if (v.getId() == R.id.exe_train){
			AlertDialog ad = new AlertDialog.Builder(ExerciseView.this)
			.setTitle("�˶�ȷ��")
			.setMessage("��ȷ��Ҫ�ó�������˶�ô��")
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int power = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select " + ToyInfoDao.POWER + " from " 
							+ ToyInfoDao.TABLE_NAME + " where " + ToyInfoDao.ID + "=" + InfoId, null);
					cursor.moveToFirst();
					if (cursor != null){
						power = cursor.getInt(cursor.getColumnIndex(ToyInfoDao.POWER));
					}
					cursor.close();
					ContentValues cv = new ContentValues();
					cv.put(ToyInfoDao.POWER, power + 2);
					sqlite.update(ToyInfoDao.TABLE_NAME, cv, ToyInfoDao.ID + "=" + InfoId, null);
					sqlite.close();
					
					//������֡����
					setTitle("�˶��ɹ�");
					toyfaceImageView.setBackgroundResource
						(R.drawable.sportlist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("����",null)
			.show();
			
			//Drawable last = toyfaceImageView.getBackground();
			
			//restartanim();
			//toyfaceImageView.setBackgroundDrawable(last);
		}
        else if (v.getId() == R.id.exe_read){
			AlertDialog ad = new AlertDialog.Builder(ExerciseView.this)
			.setTitle("�Ķ�ȷ��")
			.setMessage("��ȷ��Ҫ�ó�������Ķ�ô��")
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int intell = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select " + ToyInfoDao.INTELL + " from " 
							+ ToyInfoDao.TABLE_NAME + " where " + ToyInfoDao.ID + "=" + InfoId, null);
					cursor.moveToFirst();
					if (cursor != null){
						intell = cursor.getInt(cursor.getColumnIndex(ToyInfoDao.INTELL));
					}
					cursor.close();
					ContentValues cv = new ContentValues();
					cv.put(ToyInfoDao.INTELL, intell + 1);
					sqlite.update(ToyInfoDao.TABLE_NAME, cv, ToyInfoDao.ID + "=" + InfoId, null);
					sqlite.close();
					
					//������֡����
					setTitle("�Ķ��ɹ�");
					toyfaceImageView.setBackgroundResource
						(R.drawable.readlist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("����",null)
			.show();
			
			//Drawable last = toyfaceImageView.getBackground();
			
			//restartanim();
			//toyfaceImageView.setBackgroundDrawable(last);
		}
        else if (v.getId() == R.id.exe_think){
			AlertDialog ad = new AlertDialog.Builder(ExerciseView.this)
			.setTitle("ڤ��ȷ��")
			.setMessage("��ȷ��Ҫ�ó������ڤ��ô��")
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int intell = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select " + ToyInfoDao.INTELL + " from " 
							+ ToyInfoDao.TABLE_NAME + " where " + ToyInfoDao.ID + "=" + InfoId, null);
					cursor.moveToFirst();
					if (cursor != null){
						intell = cursor.getInt(cursor.getColumnIndex(ToyInfoDao.INTELL));
					}
					cursor.close();
					ContentValues cv = new ContentValues();
					cv.put(ToyInfoDao.INTELL, intell + 2);
					sqlite.update(ToyInfoDao.TABLE_NAME, cv, ToyInfoDao.ID + "=" + InfoId, null);
					sqlite.close();
					
					//������֡����
					setTitle("ڤ��ɹ�");
					toyfaceImageView.setBackgroundResource
						(R.drawable.thinklist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("����",null)
			.show();
			
			//Drawable last = toyfaceImageView.getBackground();
			
			//restartanim();
			//toyfaceImageView.setBackgroundDrawable(last);
		}
        else if (v.getId() == R.id.exe_swim){
			AlertDialog ad = new AlertDialog.Builder(ExerciseView.this)
			.setTitle("��Ӿȷ��")
			.setMessage("��ȷ��Ҫ�ó��������Ӿô��")
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int aglie = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select " + ToyInfoDao.AGLIE + " from " 
							+ ToyInfoDao.TABLE_NAME + " where " + ToyInfoDao.ID + "=" + InfoId, null);
					cursor.moveToFirst();
					if (cursor != null){
						aglie = cursor.getInt(cursor.getColumnIndex(ToyInfoDao.AGLIE));
					}
					cursor.close();
					ContentValues cv = new ContentValues();
					cv.put(ToyInfoDao.AGLIE, aglie + 1);
					sqlite.update(ToyInfoDao.TABLE_NAME, cv, ToyInfoDao.ID + "=" + InfoId, null);
					sqlite.close();
					
					//������֡����
					setTitle("��Ӿ�ɹ�");
					toyfaceImageView.setBackgroundResource
						(R.drawable.swimlist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("����",null)
			.show();
			
			//Drawable last = toyfaceImageView.getBackground();
			
			//restartanim();
			//toyfaceImageView.setBackgroundDrawable(last);
		}
        else if (v.getId() == R.id.exe_long){
			AlertDialog ad = new AlertDialog.Builder(ExerciseView.this)
			.setTitle("����ȷ��")
			.setMessage("��ȷ��Ҫ�ó�����г���ô��")
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int aglie = -1;
					sqlite = toyinfodao.getWritableDatabase();
					cursor = sqlite.rawQuery("select " + ToyInfoDao.AGLIE + " from " 
							+ ToyInfoDao.TABLE_NAME + " where " + ToyInfoDao.ID + "=" + InfoId, null);
					cursor.moveToFirst();
					if (cursor != null){
						aglie = cursor.getInt(cursor.getColumnIndex(ToyInfoDao.AGLIE));
					}
					cursor.close();
					ContentValues cv = new ContentValues();
					cv.put(ToyInfoDao.AGLIE, aglie + 2);
					sqlite.update(ToyInfoDao.TABLE_NAME, cv, ToyInfoDao.ID + "=" + InfoId, null);
					sqlite.close();
					
					//������֡����
					setTitle("���ܳɹ�");
					toyfaceImageView.setBackgroundResource
						(R.drawable.longlist);
					AnimationDrawable ad = 
						(AnimationDrawable)toyfaceImageView.getBackground();
					ad.start();
				}
			})
			.setNegativeButton("����",null)
			.show();
			
			//Drawable last = toyfaceImageView.getBackground();
			
			//restartanim();
			//toyfaceImageView.setBackgroundDrawable(last);
		}
    }
}
