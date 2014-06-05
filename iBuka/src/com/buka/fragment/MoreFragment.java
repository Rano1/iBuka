package com.buka.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.buka.AboutActivity;
import com.buka.R;
import com.buka.base.BaseFragment;

public class MoreFragment extends BaseFragment implements OnClickListener{
	View view;
	LinearLayout layout_about_more;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(activity).inflate(R.layout.frm_more,null);
		initView();
		return view;
	}

	private void initView() {
		layout_about_more = (LinearLayout) view.findViewById(R.id.layout_about_more);
		layout_about_more.setOnClickListener(this);
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
		case R.id.layout_about_more:
			activity.startActivity(new Intent(activity,AboutActivity.class));
			break;

		default:
			break;
		}
	}

}
