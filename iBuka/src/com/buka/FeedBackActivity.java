package com.buka;

import com.buka.base.BaseActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FeedBackActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_feedback);
		initView();
	}

	public void initView() {
		// TODO Auto-generated method stub
		TextView other_head_title = (TextView)findViewById(R.id.other_head_title);
		other_head_title.setText("意见反馈");
	}

}
