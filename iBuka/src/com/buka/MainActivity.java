package com.buka;

import com.buka.fragment.FavorFragment;
import com.buka.fragment.MoreFragment;
import com.buka.fragment.RecomFragment;
import com.buka.fragment.SearchFragment;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements OnClickListener{
	FragmentManager fm;
	FragmentTabHost mTabHost;
	private ImageView tab_btn_favor;
	private ImageView tab_btn_recom;
	private ImageView tab_btn_search;
	private ImageView tab_btn_more;
	private String TAG_FAVOR = "favor";
	private String TAG_RECOM = "recom";
	private String TAG_SEARCH = "search";
	private String TAG_MORE = "more";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		initView();
	}

	private void initView() {
		//初始化块管理
		fm = getSupportFragmentManager();
		//初始化TabHost
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, fm, android.R.id.tabcontent);
		mTabHost.addTab(mTabHost.newTabSpec(TAG_FAVOR).setIndicator(TAG_FAVOR),FavorFragment.class,null);
		mTabHost.addTab(mTabHost.newTabSpec(TAG_RECOM).setIndicator(TAG_RECOM),RecomFragment.class,null);
		mTabHost.addTab(mTabHost.newTabSpec(TAG_SEARCH).setIndicator(TAG_SEARCH),SearchFragment.class,null);
		mTabHost.addTab(mTabHost.newTabSpec(TAG_MORE).setIndicator(TAG_MORE),MoreFragment.class,null);
		//初始化其他控件
		tab_btn_favor = (ImageView)findViewById(R.id.tab_btn_favor);
		tab_btn_recom = (ImageView)findViewById(R.id.tab_btn_recom);
		tab_btn_search = (ImageView)findViewById(R.id.tab_btn_search);
		tab_btn_more = (ImageView)findViewById(R.id.tab_btn_more);
		tab_btn_favor.setOnClickListener(this);
		tab_btn_recom.setOnClickListener(this);
		tab_btn_search.setOnClickListener(this);
		tab_btn_more.setOnClickListener(this);
		//初始化选中状态
		checkTab(TAG_RECOM , tab_btn_recom);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab_btn_favor:
			checkTab(TAG_FAVOR , tab_btn_favor);
			break;
		case R.id.tab_btn_recom:
			checkTab(TAG_RECOM , tab_btn_recom);
			break;
		case R.id.tab_btn_search:
			checkTab(TAG_SEARCH , tab_btn_search);
			break;
		case R.id.tab_btn_more:
			checkTab(TAG_MORE , tab_btn_more);
			break;

		default:
			break;
		}
	}

	/** 改变说点击的按钮的状态 */
	public void checkTab(String tag , ImageView check){
//		mTabHost.onTabChanged(tag);
		mTabHost.setCurrentTabByTag(tag);
		tab_btn_favor.setEnabled(true);
		tab_btn_recom.setEnabled(true);
		tab_btn_search.setEnabled(true);
		tab_btn_more.setEnabled(true);
		check.setEnabled(false);
	}
}
