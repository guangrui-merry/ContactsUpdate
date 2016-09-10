package edu.feicui.contactsupdate.activity;


import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.base.BaseActivity;
import edu.feicui.contactsupdate.utils.LogUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class LogoActivity extends BaseActivity {
	private Animation animation;
	private ImageView img_logo;

	private AnimationListener animationListener = new AnimationListener() {
		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			startActivity(TelmsgActivity.class);
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		
		initView();
		// logo图像控件开始动画
	    img_logo.startAnimation(animation);
		
	}
	@Override
	protected void initView() {
		// 初始控件及动画
		img_logo = (ImageView) findViewById(R.id.iv_logo);
		animation = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
		animation.setAnimationListener(animationListener);		
	}
}
