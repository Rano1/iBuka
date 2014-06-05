package com.buka.fragment.recom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.buka.R;
import com.buka.adapter.RecomAdapter;
import com.buka.base.BaseFragment;
import com.buka.listener.ScrollViewListener;
import com.buka.tools.Constants;
import com.buka.view.CategoryView;
import com.buka.view.ComicScrollView;
import com.buka.view.grid.StaggeredGridView;
/**
 * 游戏中心
 */
public class RecomGamescenter extends BaseFragment {
	private View parentView;
	String[] imageUrls;
	private RecomAdapter mAdapter;
	private static String TAG = "RecomGamescenter";
	private int scroll_Y = 0;
	private TextView tv_recomHeaderTitle;
	private TextView tv_recomItemHeaderText;
	StaggeredGridView grid_view;
	ImageView recom_hot_head_img;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		imageUrls = Constants.IMAGES_GMAES;
		mAdapter = new RecomAdapter(getActivity(), imageUrls ,Constants.RECOM_GAME);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = LayoutInflater.from(activity).inflate(R.layout.frm_recom_gamescenter, null);
		initView();
		initData();
		return parentView;
	}

	private void initData() {
		recom_hot_head_img.setImageResource(R.drawable.gamescenter_head_img);
		tv_recomHeaderTitle.setText("火影忍者最强对决");
		tv_recomItemHeaderText.setText("第4次忍者大战");
	}
	
	private void initView() {
		View view_head = LayoutInflater.from(activity).inflate(R.layout.view_grid_header, null);
		recom_hot_head_img = (ImageView) view_head.findViewById(R.id.recom_hot_head_img);
		tv_recomHeaderTitle = (TextView) view_head.findViewById(R.id.tv_recomHeaderTitle);
		tv_recomItemHeaderText = (TextView) view_head.findViewById(R.id.tv_recomItemHeaderText);
		grid_view = (StaggeredGridView)parentView.findViewById(R.id.grid_view);
		grid_view.addHeaderView(view_head);
		grid_view.setAdapter(mAdapter);
		grid_view
				.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// startImagePagerActivity(position);
					}
				});
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
