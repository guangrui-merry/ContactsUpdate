package edu.feicui.contactsupdate.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;

/**
 * @author 耿佼龙
 * Dialog工具类
 */
public class DialogUtil {
	Context context;
	public DialogUtil(Context context) {
		this.context = context;
	}
	
	/**
	 * @param name 姓名
	 * @param number 电话
	 */
	public void showDialog(String name,final String number){
		AlertDialog.Builder dialog = new Builder(context);
		
		dialog.setTitle("警告");
		dialog.setMessage("是否开始拨打" + name + "电话 ? \n\nTEL：" + number);
		dialog.setCancelable(true);
		dialog.setNegativeButton("取消", null);
		dialog.setPositiveButton("拨号",new OnClickListener() {
			
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
