package com.buka.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.buka.R;
import com.buka.adapter.FaoverPagerAdapter;
import com.buka.base.BaseFragment;
import com.buka.fragment.favor.CollectFragment;

public class FavorFragment extends BaseFragment implements OnClickListener{
	FaoverPagerAdapter mAdapter;
	private ViewPager viewpager_favor;
	private Button btn_favor_collect;
	private Button btn_favor_download;
	FragmentManager fm;
	private CollectFragment collectFragment;
	private CollectFragment downloadFragment;
	private ArrayList<Fragment> fragmentList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		fm = getChildFragmentManager(); 
		initFragment();
	}

	private void initFragment() {
		fragmentList = new ArrayList<Fragment>();
		collectFragment = new CollectFragment();
		downloadFragment = new CollectFragment();
		fragmentList.add(collectFragment);
		fragmentList.add(downloadFragment);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(activity).inflate(R.layout.frm_favor, null);
		viewpager_favor = (ViewPager) view.findViewById(R.id.viewpager_favor);
		btn_favor_collect = (Button) view.findViewById(R.id.btn_favor_collect);
		btn_favor_download = (Button) view.findViewById(R.id.btn_favor_download);
		btn_favor_collect.setOnClickListener(this);
		btn_favor_download.setOnClickListener(this);
		//≥ı ºªØViewPager
		mAdapter = new FaoverPagerAdapter(fm, fragmentList);
		viewpager_favor.setAdapter(mAdapter);
		viewpager_favor.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				viewpager_favor.setCurrentItem(position);
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
		viewpager_favor.setCurrentItem(0);
		return view;
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_favor_collect:
			viewpager_favor.setCurrentItem(0);
			break;
		case R.id.btn_favor_download:
			viewpager_favor.setCurrentItem(1);
			break;

		default:
			break;
		}
	}

}
