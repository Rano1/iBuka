package com.buka.fragment.recom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.buka.R;
import com.buka.adapter.RecomAdapter;
import com.buka.base.BaseFragment;
import com.buka.listener.ScrollViewListener;
import com.buka.tools.Constants;
import com.buka.view.CategoryView;
import com.buka.view.ComicScrollView;
/**
 * 游戏中心
 */
public class RecomGamescenter extends BaseFragment {
	private View parentView;
	private CategoryView recom_gamescenter_cate;
	String[] imageUrls;
	private RecomAdapter mAdapter;
	private static String TAG = "RecomGamescenter";
	private ComicScrollView recom_gamescenter_scrollview;
	private int scroll_Y = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		imageUrls = Constants.IMAGES_GMAES;
		mAdapter = new RecomAdapter(getActivity(), imageUrls);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = LayoutInflater.from(activity).inflate(
				R.layout.frm_recom_gamescenter, null);
		initView();
		return parentView;
	}

	private void initView() {
		recom_gamescenter_scrollview = (ComicScrollView) parentView
				.findViewById(R.id.recom_gamescenter_scrollview);
		recom_gamescenter_cate = (CategoryView) parentView
				.findViewById(R.id.recom_gamescenter_cate);
		recom_gamescenter_cate.setAdapter(mAdapter);
		recom_gamescenter_cate
				.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// startImagePagerActivity(position);
					}
				});
		recom_gamescenter_scrollview.scrollTo(0, scroll_Y);
		Log.d(TAG, "scroll_Y= " + scroll_Y);
		recom_gamescenter_scrollview.setScrollViewListener(new ScrollViewListener() {

					@Override
					public void onScrollChanged(ComicScrollView scrollView,
							int x, int y, int oldx, int oldy) {
						// TODO Auto-generated method stub
						scroll_Y = y;
						if (scrollView == recom_gamescenter_scrollview) {
							// recom_gamescenter_scrollview.scrollTo(x, y);
						}
					}
				});
		recom_gamescenter_scrollview.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				recom_gamescenter_scrollview.scrollTo(0, scroll_Y);
			}
		});
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				recom_gamescenter_scrollview.scrollTo(0, scroll_Y);
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	
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
