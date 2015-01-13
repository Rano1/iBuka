package com.buka.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

/**
 * 配置文件
 */
public class Settings {
	public static Settings mSettings;
	public SharedPreferences sp_settings;
	public static String SETTINGS = "settings";
	/** KEY:下载路径 */
	public final static String KEY_DOWNLOAD_DIR = "download_dir";
	/** 默认下载路径 */
	public final static String DEFAULT_DOWNLOAD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ibuka";
	
	public static Settings getInstance(Context context){
		if(mSettings ==null ){
			mSettings = new Settings(context);
		}
		return mSettings;
	}
	
	public Settings(Context context) {
		sp_settings = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
	}

	/**
	 * 设置下载路径
	 */
	public void setDonwloadDir(String dir){
		Editor editor = sp_settings.edit();
		editor.putString(KEY_DOWNLOAD_DIR, dir);
		editor.commit();
	}
	
	/**
	 * 获取下载路径
	 */
	public String getDonwloadDir(){
		return sp_settings.getString(KEY_DOWNLOAD_DIR, DEFAULT_DOWNLOAD_DIR);
	}
}
