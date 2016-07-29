package com.base.app;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	public static BaseActivity getForegroundActivity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** 初始化数据 */
		init();
		/** 初始化view */
		initView();
		/** 初始化actionBar */
	}

	/** Activity界面获得焦点的时候，调用该方法 */
	@Override
	protected void onResume() {
		getForegroundActivity = this;
		super.onResume();
	}

	// 获取到前台的activity
	public static BaseActivity getForegroundActivity() {
		return getForegroundActivity();
	}


	public abstract void initView();

	public abstract void init();
}
