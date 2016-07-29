package com.base.app;

import java.util.LinkedList;
import java.util.List;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

public class BaseApplication extends Application {

	// 获取全局的上下文
	private static Application mContext;
	// 获取主线程的Handler
	private static Handler mMainThreadHandler;
	// 获取主线程的Looper
	private static Looper mMainThreadLooper;
	// 获取主线程
	private static Thread mMainThread;
	// 获取主线程的id
	private static int mMainThreadId;

	/**
	 * Activity列表
	 */
	private List<BaseActivity> activityList = new LinkedList<BaseActivity>();

	/**
	 * 全局唯一实例
	 */
	private static BaseApplication instance;

	/**
	 * 该类采用单例模式，不能实例化
	 */
	private BaseApplication() {
	}

	/**
	 * 获取类实例对象
	 * 
	 * @return MyActivityManager
	 */
	public static BaseApplication getInstance() {
		if (null == instance) {
			instance = new BaseApplication();
		}
		return instance;
	}

	/**
	 * 保存Activity到现有列表中
	 * 
	 * @param activity
	 */
	public void addActivity(BaseActivity activity) {
		activityList.add(activity);
	}

	/**
	 * 关闭保存的Activity
	 */
	public void exit() {
		if (activityList != null) {
			BaseActivity activity;

			for (int i = 0; i < activityList.size(); i++) {
				activity = activityList.get(i);

				if (activity != null) {
					if (!activity.isFinishing()) {
						activity.finish();
					}

					activity = null;
				}

				activityList.remove(i);
				i--;
			}
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		mMainThreadHandler = new Handler();
		mMainThreadLooper = getMainLooper();
		mMainThread = Thread.currentThread();
		mMainThreadId = android.os.Process.myTid();
	}

	public static Application getApplication() {
		return mContext;
	}

	public static Handler getMainThreadHandler() {
		return mMainThreadHandler;
	}

	public static Looper getMainThreadLooper() {
		return mMainThreadLooper;
	}

	public static Thread getMainThread() {
		return mMainThread;
	}

	public static int getMainThreadId() {
		return mMainThreadId;
	}
}
