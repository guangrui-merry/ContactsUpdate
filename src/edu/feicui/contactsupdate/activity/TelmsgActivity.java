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

/** 通讯大全页面 */
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
		// 初始控件
		listView = (ListView) findViewById(R.id.listview);
		adapter = new TelclassAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		// 适配数据
		initAppDBFile();
		adapter.clearDataTOAdapter();
		adapter.addDataToAdapter(new TelclassInfo("本地电话", 0));// 本地电话 分类
		adapter.addDataToAdapter(DBRead.readTeldbClasslist());// db库内的电话分类
		adapter.notifyDataSetChanged();
	}

	private void initAppDBFile() {
		// 检测是否存在通讯大全DB文件
		if (!DBRead.isExistsTeldbFile()) {
			try {
				// 将本地项目中的Assets/db/commonnum.db文件复制写出到 DBRead.telFile文件中
				AssetsDBManager.copyAssetsFileToFile(getApplicationContext(),
						"db/commonnum.db", DBRead.telFile);
			} catch (IOException e) {
				ToastUtil.show(this, "初始通讯大全数据库文件异常", Toast.LENGTH_SHORT);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 本地通话
		if (position == 0) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			startActivity(intent);
			return;
		}
		// 取出当前选择的选项实体内容
		TelclassInfo classInfo = adapter.getItem(position);
		// 跳转至电话浏览页面,且传入idx
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
			ToastUtil.show(getApplicationContext(), "再接一次退出",Toast.LENGTH_SHORT);
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
