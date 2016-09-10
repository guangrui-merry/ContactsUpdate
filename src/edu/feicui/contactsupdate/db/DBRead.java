package edu.feicui.contactsupdate.db;

import java.io.File;
import java.util.ArrayList;

import edu.feicui.contactsupdate.entity.TelclassInfo;
import edu.feicui.contactsupdate.entity.TelnumberInfo;
import edu.feicui.contactsupdate.utils.LogUtil;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/** ���������ݿ��ļ��Ķ�ȡ���� */
public class DBRead{
	/** ͨѶ��ȫFile */
	public static File telFile;
	static {
		// Ĭ��λ��
		String dbFileDir = "data/data/edu.feicui.contactsupdate/";
		// �洢����
		// String sdcardState = Environment.getExternalStorageState();
		// if (sdcardState.equals(Environment.MEDIA_MOUNTED)) {
		// dbFileDir = Environment.getExternalStorageDirectory() +
		// "/azyphone/cache";
		// }
		File fileDir = new File(dbFileDir);
		fileDir.mkdirs(); // �ļ�Ŀ¼�Ĵ���
		telFile = new File(dbFileDir, "commonnum.db");
		LogUtil.d("DBRead", "telFile dir path: " + dbFileDir);
	}

	/** ����Ƿ����ͨѶ��ȫDB�ļ� */
	public static boolean isExistsTeldbFile() {
		// û��ͨѶ��ȫFile
		File toFile = DBRead.telFile;
		if (!toFile.exists() || toFile.length() <= 0) {
			return false;
		}
		return true;
	}

	/** ��ȡtelFile������ݿ��ļ��е�classlist������ڵ����� */
	public static ArrayList<TelclassInfo> readTeldbClasslist() {
		ArrayList<TelclassInfo> classListInfos = new ArrayList<TelclassInfo>();
		// ��DB�ļ�
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
		// ִ�в�ѯ��SQL��� select * from classlist
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

	/** ��ȡtelFile������ݿ��ļ��е�tableN������ڵ�����(�̼����ƺ͵绰����) */
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
