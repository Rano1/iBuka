package com.buka.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

public class PagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> frmList;

	public PagerAdapter(FragmentManager fm, ArrayList<Fragment> frmList) {
		super(fm);
		this.frmList = frmList;
	}

	@Override
	public Fragment getItem(int position) {
		if(frmList != null && frmList.size() !=0){
			return frmList.get(position);
		}
		return null;
	}

	@Override
	public int getCount() {
		return frmList == null ? 0 : frmList.size();
	}

}
