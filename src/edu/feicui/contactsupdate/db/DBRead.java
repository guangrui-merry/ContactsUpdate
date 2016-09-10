package edu.feicui.contactsupdate.db;

import java.io.File;
import java.util.ArrayList;

import edu.feicui.contactsupdate.entity.TelclassInfo;
import edu.feicui.contactsupdate.entity.TelnumberInfo;
import edu.feicui.contactsupdate.utils.LogUtil;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/** 用来做数据库文件的读取操作 */
public class DBRead{
	/** 通讯大全File */
	public static File telFile;
	static {
		// 默认位置
		String dbFileDir = "data/data/edu.feicui.contactsupdate/";
		// 存储卡上
		// String sdcardState = Environment.getExternalStorageState();
		// if (sdcardState.equals(Environment.MEDIA_MOUNTED)) {
		// dbFileDir = Environment.getExternalStorageDirectory() +
		// "/azyphone/cache";
		// }
		File fileDir = new File(dbFileDir);
		fileDir.mkdirs(); // 文件目录的创建
		telFile = new File(dbFileDir, "commonnum.db");
		LogUtil.d("DBRead", "telFile dir path: " + dbFileDir);
	}

	/** 检测是否存在通讯大全DB文件 */
	public static boolean isExistsTeldbFile() {
		// 没有通讯大全File
		File toFile = DBRead.telFile;
		if (!toFile.exists() || toFile.length() <= 0) {
			return false;
		}
		return true;
	}

	/** 读取telFile这个数据库文件中的classlist这个表内的数据 */
	public static ArrayList<TelclassInfo> readTeldbClasslist() {
		ArrayList<TelclassInfo> classListInfos = new ArrayList<TelclassInfo>();
		// 打开DB文件
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
		// 执行查询的SQL语句 select * from classlist
		Cursor cursor = db.rawQuery("select * from classlist", null);
		LogUtil.d("DBRead", "read teldb classlist size: " + cursor.getCount());
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				int idx = cursor.getInt(cursor.getColumnIndex("idx"));
				TelclassInfo classListInfo = new TelclassInfo(name, idx);
				classListInfos.add(classListInfo);
			}
			while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		LogUtil.d("DBRead", "read teldb classlist end [list size]: " + classListInfos.size());
		return classListInfos;
	}

	/** 读取telFile这个数据库文件中的tableN这个表内的数据(商家名称和电话号码) */
	public static ArrayList<TelnumberInfo> readTeldbTable(int idx) {
		ArrayList<TelnumberInfo> numberInfos = new ArrayList<TelnumberInfo>();
		String sql = "select * from table" + idx;
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
		Cursor cursor = db.rawQuery(sql, null);
		LogUtil.d("DBRead", "read teldb number table size: " + cursor.getCount());
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String number = cursor.getString(cursor.getColumnIndex("number"));
				TelnumberInfo numberInfo = new TelnumberInfo(name, number);
				numberInfos.add(numberInfo);
			}
			while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		LogUtil.d("DBRead", "read teldb number table end [list size]: " + numberInfos.size());
		return numberInfos;
	}
}
