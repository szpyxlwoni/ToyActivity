package toy.com.tools;
import toy.com.model.CommodityInfoDao;
import toy.com.model.ToyExploitDao;
import toy.com.model.ToyGoodsDao;
import toy.com.model.ToyInfoDao;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class InitDataBase extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CommodityInfoDao cominfodao = new CommodityInfoDao(this);
		ToyExploitDao toyexploitdao = new ToyExploitDao(this);
		ToyGoodsDao toygoodsdao = new ToyGoodsDao(this);
		ToyInfoDao toyinfodao = new ToyInfoDao(this);
		SQLiteDatabase sqlite = cominfodao.getWritableDatabase();
		cominfodao.onCreate(sqlite);
		toyexploitdao.onCreate(sqlite);
		toygoodsdao.onCreate(sqlite);
		toyinfodao.onCreate(sqlite);
		sqlite.close();
	}

}
