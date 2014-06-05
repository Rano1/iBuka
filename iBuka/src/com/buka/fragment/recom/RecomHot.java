package com.buka.fragment.recom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ScrollView;

import com.buka.R;
import com.buka.adapter.RecomAdapter;
import com.buka.base.BaseFragment;
import com.buka.tools.Constants;
import com.buka.view.CategoryView;
/**
 * 热门连载
 */
public class RecomHot extends BaseFragment {
	private View parentView;
	String[] imageUrls;
	private RecomAdapter mAdapter;
	private CategoryView recom_hot_cate;
	private ScrollView scrolview_recom_hot;
	private static String TAG = "RecomHot";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		imageUrls = Constants.IMAGES_COMIC_HOT;
		mAdapter = new RecomAdapter(getActivity(), imageUrls);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = LayoutInflater.from(activity).inflate(R.layout.frm_recom_hot, null);
		initView();
		return parentView;
	}

	private void initView() {
		recom_hot_cate = (CategoryView) parentView
				.findViewById(R.id.recom_hot_cate);
		scrolview_recom_hot = (ScrollView) parentView
				.findViewById(R.id.scrolview_recom_hot);
		recom_hot_cate.setAdapter(mAdapter);
		recom_hot_cate.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// startImagePagerActivity(position);
//				Intent intent = new Intent(getActivity(),ComicDirActivity.class);
//				intent.putExtra("comic_id", position);
//				startActivity(intent);
			}
		});
		scrolview_recom_hot.scrollTo(0, 0);
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
