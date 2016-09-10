package edu.feicui.contactsupdate.adapter;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.base.adapter.BaseDataAdapter;
import edu.feicui.contactsupdate.entity.TelclassInfo;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** ͨѶ��ȫ�绰���������� */
public class TelclassAdapter extends BaseDataAdapter<TelclassInfo>{
	
	public TelclassAdapter(Context context) {
		super(context);
	}

	// ��ǰ�������ڵ����ݼ��� (��ǰ���������乤��ֻ�ϴ˼���)
	private ArrayList<TelclassInfo> adapterDatas = new ArrayList<TelclassInfo>();

	// ������ݵ���ǰ����������
	public void addDataToAdapter(TelclassInfo e) {
		if (e != null) {
			adapterDatas.add(e);
		}
	}

	// ������ݵ���ǰ����������
	public void addDataToAdapter(List<TelclassInfo> e) {
		if (e != null) {
			adapterDatas.addAll(e);
		}
	}
	// ������ݵ���ǰ����������
	public void clearDataTOAdapter() {
		adapterDatas.clear();
	}
	public ArrayList<TelclassInfo> getDataFromAdapter() {
		return adapterDatas;
	}

	@Override
	public int getCount() {
		return adapterDatas.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.inflate_telmgr_listitem, null);
		}
		TextView tv_text = (TextView) convertView.findViewById(R.id.textview);
		tv_text.setText(getItem(position).name);
		return convertView;
	}

	@Override
	public TelclassInfo getItem(int position) {
		return adapterDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
