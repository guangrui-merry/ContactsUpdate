package edu.feicui.contactsupdate.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.adapter.TellistAdapter;
import edu.feicui.contactsupdate.base.BaseActionbarActivity;
import edu.feicui.contactsupdate.db.DBRead;
import edu.feicui.contactsupdate.utils.DialogUtil;

public class TellistActivity extends BaseActionbarActivity implements OnItemClickListener,OnClickListener{
	private ListView listView;
	private TellistAdapter adapter;
	private int idx = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tellist);
		initView();
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		// 获取数据用来判断是显示哪一种分类的电话号码列表
		idx = getIntent().getIntExtra("idx", -1);
		// 初始控件
		listView = (ListView) findViewById(R.id.listview);
		adapter = new TellistAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String name = adapter.getItem(position).name;
		String number = adapter.getItem(position).number;
		DialogUtil dialog = new DialogUtil(TellistActivity.this);
		dialog.showDialog(name, number);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// 添加数据
		adapter.addDataToAdapter(DBRead.readTeldbTable(idx));
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
	}

}
