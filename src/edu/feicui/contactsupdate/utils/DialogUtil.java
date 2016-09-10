package edu.feicui.contactsupdate.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;

/**
 * @author ��ٮ��
 * Dialog������
 */
public class DialogUtil {
	Context context;
	public DialogUtil(Context context) {
		this.context = context;
	}
	
	/**
	 * @param name ����
	 * @param number �绰
	 */
	public void showDialog(String name,final String number){
		AlertDialog.Builder dialog = new Builder(context);
		
		dialog.setTitle("����");
		dialog.setMessage("�Ƿ�ʼ����" + name + "�绰 ? \n\nTEL��" + number);
		dialog.setCancelable(true);
		dialog.setNegativeButton("ȡ��", null);
		dialog.setPositiveButton("����",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel://" + number));
				context.startActivity(intent);
			}
		});
		dialog.show();
	}
}
