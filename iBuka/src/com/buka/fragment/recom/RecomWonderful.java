package com.buka.fragment.recom;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.buka.R;
import com.buka.adapter.WaterfallAdapter;
import com.buka.base.BaseFragment;
import com.buka.entity.RecomComic;
import com.buka.tools.Constants;
import com.buka.view.grid.StaggeredGridView;

/**
 * 精彩推荐
 */
public class RecomWonderful extends BaseFragment {
	public final static String TAG = "RecomWonderful";
	View view;
	public StaggeredGridView grid_view;
	public WaterfallAdapter mAdapter;
	ArrayList<RecomComic> comicList;
	/** 判断是否在请求刷新更多 */
    private boolean mHasRequestedMore = false;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		comicList = new ArrayList<RecomComic>();
		for (int i = 0; i < Constants.IMAGES_COMIC_RECOM.length; i++) {
			RecomComic recomcomic = new RecomComic();
			recomcomic.setId(i);
			recomcomic.setTitle("火影忍者" + i);
			recomcomic.setSection(i + "卷");
			recomcomic.setImg_url(Constants.IMAGES_COMIC_RECOM[i]);
			recomcomic.setImg_width(300);
			recomcomic.setImg_height(200 + 10 * i);
			comicList.add(recomcomic);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(activity).inflate(R.layout.frm_recom_wonderful, null);
		grid_view = (StaggeredGridView)view.findViewById(R.id.grid_view);
		View headerView = getActivity().getLayoutInflater().inflate(R.layout.view_grid_header, null);
		View footerView = getActivity().getLayoutInflater().inflate(R.layout.view_grid_footer, null);
		grid_view.addHeaderView(headerView);
		grid_view.addFooterView(footerView);
		mAdapter = new WaterfallAdapter(comicList, activity);
		grid_view.setAdapter(mAdapter);
		//设置滑动监听
		grid_view.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				//判断滑倒底部，加载更多数据
				if (!mHasRequestedMore) {
		            int lastInScreen = firstVisibleItem + visibleItemCount;
		            if (lastInScreen >= totalItemCount) {
		                mHasRequestedMore = true;
		                loadMore();
		                Log.d(TAG, "last");
		            }
		        }
			}
		});
		//设置点击监听
		grid_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 Toast.makeText(activity, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	/** */
	public void loadMore(){
		mAdapter.notifyDataSetChanged();
		mHasRequestedMore = false;
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
