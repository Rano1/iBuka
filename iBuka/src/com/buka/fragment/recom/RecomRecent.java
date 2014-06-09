package com.buka.fragment.recom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.buka.R;
import com.buka.adapter.RecentAdapter;
import com.buka.base.BaseFragment;
import com.buka.tools.Constants;

/**
 * 最近更新
 */
public class RecomRecent extends BaseFragment {
	View view;
	private ListView recom_recent_listview;
	String[] imageUrls;
	private static String TAG = "RecomRecent";
	RecentAdapter mAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		imageUrls = Constants.IMAGES_COMIC_RECENT;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(activity).inflate(R.layout.frm_recom_recent,
				null);
		initView();
		return view;
	}

	private void initView() {
		mAdapter = new RecentAdapter(activity, imageUrls);
		recom_recent_listview = (ListView) view.findViewById(R.id.recom_recent_listview);
		recom_recent_listview.setAdapter(mAdapter);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
