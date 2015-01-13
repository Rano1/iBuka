package com.buka;

import android.os.Bundle;
import android.widget.ListView;

import com.buka.adapter.RecentAdapter;
import com.buka.base.BaseActivity;
/**
 * 搜索活动
 */
public class SearchActivity extends BaseActivity {
	private ListView lv_search;
	private RecentAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_search);
		initView();
	}
	
	private void initView() {
		lv_search = (ListView) findViewById(R.id.lv_search);
	}
	
	public void setListView(){
//		mAdapter = new RecentAdapter(getApplicationContext(), imageUrls)
	}
	
}
