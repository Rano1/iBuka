package com.buka.fragment;

import java.util.ArrayList;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.buka.LoginActivity;
import com.buka.R;
import com.buka.adapter.PagerAdapter;
import com.buka.base.BaseFragment;
import com.buka.fragment.favor.FavorCollect;
import com.buka.tools.Constants;

public class FavorFragment extends BaseFragment implements OnClickListener{
	PagerAdapter mAdapter;
	private ViewPager viewpager_favor;
	private RadioGroup rgroup_favor;
	private RadioButton rbtn_favor_collect;
	private RadioButton rbtn_favor_history;
	private ImageView view_favor_collect_divide;
	private ImageView view_favor_history_divide;
	FragmentManager fm;
	private FavorCollect collectFragment;
	private FavorCollect historyFragment;
	private ArrayList<Fragment> fragmentList;
	private LinearLayout ll_login;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		fm = getChildFragmentManager(); 
		initFragment();
	}

	private void initFragment() {
		fragmentList = new ArrayList<Fragment>();
		collectFragment = new FavorCollect();
		Bundle data_collect = new Bundle();
		data_collect.putInt("favor_type", Constants.FAVOR_COLLECT);
    	collectFragment.setArguments(data_collect);
		historyFragment = new FavorCollect();
		Bundle data_history = new Bundle();
		data_history.putInt("favor_type", Constants.FAVOR_HISTORY);
    	historyFragment.setArguments(data_history);
		fragmentList.add(collectFragment);
		fragmentList.add(historyFragment);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(activity).inflate(R.layout.frm_favor, null);
		ll_login = (LinearLayout) view.findViewById(R.id.ll_login);
		ll_login.setOnClickListener(this);
		viewpager_favor = (ViewPager) view.findViewById(R.id.viewpager_favor);
		rgroup_favor = (RadioGroup) view.findViewById(R.id.rgroup_favor);
		rbtn_favor_collect = (RadioButton) view.findViewById(R.id.rbtn_favor_collect);
		rbtn_favor_history = (RadioButton) view.findViewById(R.id.rbtn_favor_history);
		view_favor_collect_divide = (ImageView) view.findViewById(R.id.view_favor_collect_divide);
		view_favor_history_divide = (ImageView) view.findViewById(R.id.view_favor_history_divide);
		rgroup_favor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rbtn_favor_collect:
					viewpager_favor.setCurrentItem(0);
					rbtn_favor_collect.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color_emph));
					rbtn_favor_history.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color));
					view_favor_collect_divide.setVisibility(View.VISIBLE);
					view_favor_history_divide.setVisibility(View.INVISIBLE);
					break;
				case R.id.rbtn_favor_history:
					viewpager_favor.setCurrentItem(1);
					rbtn_favor_collect.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color));
					rbtn_favor_history.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color_emph));
					view_favor_collect_divide.setVisibility(View.INVISIBLE);
					view_favor_history_divide.setVisibility(View.VISIBLE);
					break;

				default:
					break;
				}
			}
		});
		//初始化ViewPager
		mAdapter = new PagerAdapter(fm, fragmentList);
		viewpager_favor.setAdapter(mAdapter);
		viewpager_favor.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					rgroup_favor.check(R.id.rbtn_favor_collect);
					break;
				case 1:
					rgroup_favor.check(R.id.rbtn_favor_history);
					break;

				default:
					break;
				}
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
		rgroup_favor.check(R.id.rbtn_favor_collect);
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
		case R.id.ll_login:
			startActivity(new Intent(activity, LoginActivity.class));
			break;
		default:
			break;
		}
	}

}