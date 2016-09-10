package edu.feicui.contactsupdate.base;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.utils.LogUtil;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * ��ActiooonBar�ؼ����ݳ�ʼ��������onClick�����Ļ���Activity
 * 
 * @author yuanc
 */
public abstract class BaseActionbarActivity extends BaseActivity{
	public static final int NULL_ID = -1;

	/**
	 * ����ActionBar�ؼ��ϵ�����
	 * 
	 * @param resIdTitle
	 *            �м��������ı�id,û�б���ʱ��ʹ��{@link #NULL_ID}
	 * @param resIdLeft
	 *            ���ͼ����Դid,û��ͼ��ʱ��ʹ��{@link #NULL_ID}
	 * @param resIdRight
	 *            �Ҳ�ͼ����Դid,û��ͼ��ʱ��ʹ��{@link #NULL_ID}
	 */
	public void setActionBar(int resIdTitle, int resIdLeft, int resIdRight) {
		try {
			TextView tv_action_title = (TextView) findViewById(R.id.tv_actionbar_title);
			ImageView iv_action_left = (ImageView) findViewById(R.id.iv_action_left);
			ImageView iv_action_right = (ImageView) findViewById(R.id.iv_action_right);
			if (resIdLeft != NULL_ID) {
				iv_action_left.setImageResource(resIdLeft);
			}
			else {
				iv_action_left.setVisibility(View.INVISIBLE);
			}
			if (resIdRight != NULL_ID) {
				iv_action_right.setImageResource(resIdRight);
			}
			else {
				iv_action_right.setVisibility(View.INVISIBLE);
			}
			if (resIdTitle != NULL_ID) {
				tv_action_title.setText(resIdTitle);
			}
		}
		catch (Exception e) {
			LogUtil.w("BaseActionbarActivity", "ActionBar���쳣,�Ƿ�ǰҳ�沢û��includle==> include_actionbar.xml����?");
		}
	}
}
