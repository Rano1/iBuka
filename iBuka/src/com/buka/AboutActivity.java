package com.buka;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.buka.base.BaseActivity;
import com.buka.tools.BaseTools;

public class AboutActivity extends BaseActivity {
	public TextView tv_coop_about;
	public TextView tv_join_us_about;
	public TextView tv_weixin_about ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_about);
		initView();
	}

	public void initView() {
		// TODO Auto-generated method stub
		TextView tv_version_about = (TextView) findViewById(R.id.tv_version_about);
		TextView tv_site_about = (TextView) findViewById(R.id.tv_site_about);
		TextView tv_bbs_about = (TextView) findViewById(R.id.tv_bbs_about);
		TextView tv_weibo_about = (TextView) findViewById(R.id.tv_weibo_about);
		TextView tv_facebook_about = (TextView) findViewById(R.id.tv_facebook_about);
		// 赋值
		String version = String.format(getResources().getString(R.string.aboutVer),BaseTools.getAppVersionName(getApplicationContext()));
		tv_version_about.setText(version);
		tv_site_about.setText(Html.fromHtml(getResources().getString(R.string.aboutSite)));
		tv_bbs_about.setText(Html.fromHtml(getResources().getString(R.string.aboutBBS)));
		tv_weibo_about.setText(Html.fromHtml(getResources().getString(R.string.aboutWeibo)));
		tv_facebook_about.setText(Html.fromHtml(getResources().getString(R.string.aboutFacebook)));
	}

}
