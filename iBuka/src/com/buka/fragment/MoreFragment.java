package com.buka.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.buka.AboutActivity;
import com.buka.DownloadActivity;
import com.buka.FeedBackActivity;
import com.buka.LocalActivity;
import com.buka.LoginActivity;
import com.buka.R;
import com.buka.SettingsActivity;
import com.buka.base.BaseFragment;

public class MoreFragment extends BaseFragment implements OnClickListener{
	View view;
	LinearLayout layout_setting_more;
	LinearLayout layout_feedback_more;
	LinearLayout layout_user_center_more;
	LinearLayout layout_about_more;
	LinearLayout layout_download_more;
	LinearLayout layout_local_manga_more;
	
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
		layout_setting_more = (LinearLayout) view.findViewById(R.id.layout_setting_more);
		layout_feedback_more = (LinearLayout) view.findViewById(R.id.layout_feedback_more);
		layout_user_center_more = (LinearLayout) view.findViewById(R.id.layout_user_center_more);
		layout_about_more = (LinearLayout) view.findViewById(R.id.layout_about_more);
		layout_download_more = (LinearLayout) view.findViewById(R.id.layout_download_more);
		layout_local_manga_more = (LinearLayout) view.findViewById(R.id.layout_local_manga_more);
		layout_setting_more.setOnClickListener(this);
		layout_feedback_more.setOnClickListener(this);
		layout_user_center_more.setOnClickListener(this);
		layout_about_more.setOnClickListener(this);
		layout_download_more.setOnClickListener(this);
		layout_local_manga_more.setOnClickListener(this);
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
		case R.id.layout_setting_more:
			activity.startActivity(new Intent(activity,SettingsActivity.class));
			break;
		case R.id.layout_feedback_more:
			activity.startActivity(new Intent(activity,FeedBackActivity.class));
			break;
		case R.id.layout_user_center_more:
			activity.startActivity(new Intent(activity,LoginActivity.class));
			break;
		case R.id.layout_about_more:
			activity.startActivity(new Intent(activity,AboutActivity.class));
			break;
		case R.id.layout_download_more:
			activity.startActivity(new Intent(activity,DownloadActivity.class));
			break;
		case R.id.layout_local_manga_more:
			activity.startActivity(new Intent(activity,LocalActivity.class));
			break;
			
		default:
			break;
		}
	}

}
