package edu.feicui.contactsupdate.base;


import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import edu.feicui.contactsupdate.utils.LogUtil;

/**
 * ����Activity������������������ڼ̳�
 *
 */
public abstract class BaseActivity extends Activity {
	private static final String TAG = "ActivityLife";
	// --------------------------------------------------------------------------------------
	/** �����������ڴ��ڵ�Activity */
	private static ArrayList<BaseActivity> onlineActivityList = new ArrayList<BaseActivity>();

	/** �����˳���ǰ���ڵ�����Activity */
	public static void finishAll() {
		Iterator<BaseActivity> iterator = onlineActivityList.iterator();
		while (iterator.hasNext()) {
			iterator.next().finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	protected void startActivity(Class<?> targetClass) {
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);
	}

	protected void startActivity(Class<?> targetClass, Bundle bundle) {
		Intent intent = new Intent(this, targetClass);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	protected void startActivity(Class<?> targetClass, int inAnimID,
			int outAnimID) {
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);
		overridePendingTransition(inAnimID, outAnimID);
	}

	protected void startActivity(Class<?> targetClass, int inAnimID,
			int outAnimID, Bundle bundle) {
		Intent intent = new Intent(this, targetClass);
		intent.putExtras(bundle);
		startActivity(intent);
		overridePendingTransition(inAnimID, outAnimID);
	}

	@Override
	public void finish() {
		super.finish();
	}

	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.d(TAG, getClass().getSimpleName() + " onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.d(TAG, getClass().getSimpleName() + " onResume()");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		LogUtil.d(TAG, getClass().getSimpleName() + " onPause()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		LogUtil.d(TAG, getClass().getSimpleName() + " onStop()");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		LogUtil.d(TAG, getClass().getSimpleName() + " onDestroy()");
		if (onlineActivityList.contains(this)) {
			onlineActivityList.remove(this);
		}
	}
	
	protected abstract void initView();
	
 }
