package edu.feicui.contactsupdate.activity;

import java.io.IOException;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.adapter.TelclassAdapter;
import edu.feicui.contactsupdate.base.BaseActionbarActivity;
import edu.feicui.contactsupdate.db.AssetsDBManager;
import edu.feicui.contactsupdate.db.DBRead;
import edu.feicui.contactsupdate.entity.TelclassInfo;
import edu.feicui.contactsupdate.utils.ToastUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/** ͨѶ��ȫҳ�� */
public class TelmsgActivity extends BaseActionbarActivity implements OnItemClickListener,OnClickListener{
	private ListView listView;
	private TelclassAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_telmsg);
		setActionBar(R.string.title_activity_telmsg, R.drawable.btn_homeasup_default, NULL_ID);
		
		initView();
	}
	
	@Override
	protected void initView() {
		// ��ʼ�ؼ�
		listView = (ListView) findViewById(R.id.listview);
		adapter = new TelclassAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		// ��������
		initAppDBFile();
		adapter.clearDataTOAdapter();
		adapter.addDataToAdapter(new TelclassInfo("���ص绰", 0));// ���ص绰 ����
		adapter.addDataToAdapter(DBRead.readTeldbClasslist());// db���ڵĵ绰����
		adapter.notifyDataSetChanged();
	}

	private void initAppDBFile() {
		// ����Ƿ����ͨѶ��ȫDB�ļ�
		if (!DBRead.isExistsTeldbFile()) {
			try {
				// ��������Ŀ�е�Assets/db/commonnum.db�ļ�����д���� DBRead.telFile�ļ���
				AssetsDBManager.copyAssetsFileToFile(getApplicationContext(),
						"db/commonnum.db", DBRead.telFile);
			} catch (IOException e) {
				ToastUtil.show(this, "��ʼͨѶ��ȫ���ݿ��ļ��쳣", Toast.LENGTH_SHORT);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// ����ͨ��
		if (position == 0) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			startActivity(intent);
			return;
		}
		// ȡ����ǰѡ���ѡ��ʵ������
		TelclassInfo classInfo = adapter.getItem(position);
		// ��ת���绰���ҳ��,�Ҵ���idx
//		Intent intent = new Intent(this, TellistActivity.class);
//		intent.putExtra("idx", classInfo.idx);
//		startActivity(intent);
		Bundle bundle = new Bundle();
		bundle.putInt("idx", classInfo.idx);
		startActivity(TellistActivity.class, bundle);
	}
	
	private long preTime = 0;
	private long curTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			curTime = System.currentTimeMillis();
			if (curTime - preTime <= 800) {
				finish();
				System.exit(0);
			}
			ToastUtil.show(getApplicationContext(), "�ٽ�һ���˳�",Toast.LENGTH_SHORT);
			preTime = curTime;
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
