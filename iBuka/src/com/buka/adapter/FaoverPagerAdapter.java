package com.buka.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

public class FaoverPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> frmList;
	FragmentManager fm;

	public FaoverPagerAdapter(FragmentManager fm, ArrayList<Fragment> frmList) {
		super(fm);
		this.fm = fm;
		this.frmList = frmList;
	}

	@Override
	public Fragment getItem(int position) {
		return frmList.get(position);
	}

	@Override
	public int getCount() {
		return frmList == null ? 0 : frmList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == object;
	}

}
