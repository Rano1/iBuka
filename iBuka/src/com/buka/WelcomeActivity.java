package com.buka;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.buka.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_welcome);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				toMain();
			}
		}, 2000L);
	}

	public void toMain() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
}
