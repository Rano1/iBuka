package com.buka.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.buka.R;
import com.buka.adapter.PagerAdapter;
import com.buka.base.BaseFragment;
import com.buka.fragment.recom.RecomGamescenter;
import com.buka.fragment.recom.RecomHot;
import com.buka.fragment.recom.RecomRecent;
import com.buka.fragment.recom.RecomWonderful;

public class RecomFragment extends BaseFragment {
	private RadioGroup rgroup_recom;
	private RadioButton rbtn_recom_recommend;
	private RadioButton rbtn_recom_hot;
	private RadioButton rbtn_recom_gamescenter;
	private RadioButton rbtn_recom_recent;
	private ImageView view_recom_recommend_divide;
	private ImageView view_recom_hot_divide;
	private ImageView view_recom_gamescenter_divide;
	private ImageView view_recom_recent_divide;
	private ViewPager vpage_recom;
	private ArrayList<Fragment> recom_fragments = new ArrayList<Fragment>();
	private RecomWonderful mRecomWonderful;
	private RecomHot mRecomHot;
	private RecomGamescenter mRecomGamescenter;
	private RecomRecent mRecomRecent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initFragment();
	}

	private void initFragment() {
		recom_fragments = new ArrayList<Fragment>();
		mRecomWonderful = new RecomWonderful();
		mRecomHot = new RecomHot();
		mRecomGamescenter = new RecomGamescenter();
		mRecomRecent = new RecomRecent();
		recom_fragments.add(mRecomWonderful);
		recom_fragments.add(mRecomHot);
		recom_fragments.add(mRecomGamescenter);
		recom_fragments.add(mRecomRecent);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(activity).inflate(R.layout.frm_recom,null);
		view_recom_recommend_divide = (ImageView) view.findViewById(R.id.view_recom_recommend_divide);
		view_recom_hot_divide = (ImageView) view.findViewById(R.id.view_recom_hot_divide);
		view_recom_gamescenter_divide = (ImageView) view.findViewById(R.id.view_recom_gamescenter_divide);
		view_recom_recent_divide = (ImageView) view.findViewById(R.id.view_recom_recent_divide);
		rgroup_recom = (RadioGroup) view.findViewById(R.id.rgroup_recom);
		rbtn_recom_recommend = (RadioButton) view.findViewById(R.id.rbtn_recom_recommend);
		rbtn_recom_hot = (RadioButton) view.findViewById(R.id.rbtn_recom_hot);
		rbtn_recom_gamescenter = (RadioButton) view.findViewById(R.id.rbtn_recom_gamescenter);
		rbtn_recom_recent = (RadioButton) view.findViewById(R.id.rbtn_recom_recent);
		rgroup_recom.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rbtn_recom_recommend:
					vpage_recom.setCurrentItem(0);
					check_change(rbtn_recom_recommend,
							view_recom_recommend_divide);
					break;
				case R.id.rbtn_recom_hot:
					vpage_recom.setCurrentItem(1);
					check_change(rbtn_recom_hot, view_recom_hot_divide);
					break;
				case R.id.rbtn_recom_gamescenter:
					vpage_recom.setCurrentItem(2);
					check_change(rbtn_recom_gamescenter,
							view_recom_gamescenter_divide);
					break;
				case R.id.rbtn_recom_recent:
					vpage_recom.setCurrentItem(3);
					check_change(rbtn_recom_recent, view_recom_recent_divide);
					break;
				default:
					break;
				}
			}
		});
		vpage_recom = (ViewPager) view.findViewById(R.id.vpage_recom);
		vpage_recom.setOffscreenPageLimit(0);
		PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),recom_fragments);
		vpage_recom.setAdapter(adapter);
		vpage_recom.setCurrentItem(0);
		vpage_recom.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				switch (arg0) {
				case 0:
					rgroup_recom.check(R.id.rbtn_recom_recommend);
					break;
				case 1:
					rgroup_recom.check(R.id.rbtn_recom_hot);
					break;
				case 2:
					rgroup_recom.check(R.id.rbtn_recom_gamescenter);
					break;
				case 3:
					rgroup_recom.check(R.id.rbtn_recom_recent);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		check_change(rbtn_recom_recommend, view_recom_recommend_divide);
		return view;
	}

	private void check_change(RadioButton rbtn, ImageView img) {
		// 初始化
		rbtn_recom_recommend.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color));
		rbtn_recom_hot.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color));
		rbtn_recom_gamescenter.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color));
		rbtn_recom_recent.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color));
		view_recom_recommend_divide.setVisibility(View.INVISIBLE);
		view_recom_hot_divide.setVisibility(View.INVISIBLE);
		view_recom_gamescenter_divide.setVisibility(View.INVISIBLE);
		view_recom_recent_divide.setVisibility(View.INVISIBLE);
		// 点击的
		rbtn.setTextColor(getResources().getColor(R.drawable.bar_btn_text_color_emph));
		img.setVisibility(View.VISIBLE);
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
