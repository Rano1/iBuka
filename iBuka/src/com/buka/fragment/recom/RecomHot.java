package com.buka.fragment.recom;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.buka.R;
import com.buka.adapter.RecomAdapter;
import com.buka.base.BaseFragment;
import com.buka.tools.Constants;
import com.buka.view.grid.StaggeredGridView;
/**
 * 热门连载
 */
public class RecomHot extends BaseFragment {
	private View parentView;
	String[] imageUrls;
	private RecomAdapter mAdapter;
	private static String TAG = "RecomHot";
	private TextView tv_recomHeaderTitle;
	private TextView tv_recomItemHeaderText;
	StaggeredGridView grid_view;
	ImageView recom_hot_head_img;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		imageUrls = Constants.IMAGES_COMIC_HOT;
		mAdapter = new RecomAdapter(getActivity(), imageUrls , Constants.RECOM_HOT);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = LayoutInflater.from(activity).inflate(R.layout.frm_recom_hot, null);
		initView();
		initData();
		return parentView;
	}

	private void initData() {
		recom_hot_head_img.setImageResource(R.drawable.hot_head_img);
		tv_recomHeaderTitle.setText("美食的俘获");
		tv_recomItemHeaderText.setText("烹饪美食");
	}

	private void initView() {
//		recom_hot_cate = (CategoryView) parentView.findViewById(R.id.recom_hot_cate);
//		scrolview_recom_hot = (ScrollView) parentView.findViewById(R.id.scrolview_recom_hot);
		View view_head = LayoutInflater.from(activity).inflate(R.layout.view_grid_header, null);
		recom_hot_head_img = (ImageView) view_head.findViewById(R.id.recom_hot_head_img);
		tv_recomHeaderTitle = (TextView) view_head.findViewById(R.id.tv_recomHeaderTitle);
		tv_recomItemHeaderText = (TextView) view_head.findViewById(R.id.tv_recomItemHeaderText);
		grid_view = (StaggeredGridView)parentView.findViewById(R.id.grid_view);
		grid_view.addHeaderView(view_head);
		grid_view.setAdapter(mAdapter);
		grid_view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// startImagePagerActivity(position);
//				Intent intent = new Intent(getActivity(),ComicDirActivity.class);
//				intent.putExtra("comic_id", position);
//				startActivity(intent);
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
