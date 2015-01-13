package com.buka;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.buka.base.BaseActivity;
import com.buka.settings.DownloadDirActivity;

/**
 * 设置界面
 */
public class SettingsActivity extends BaseActivity implements OnClickListener{
	private TextView tv_setdownloaddir;
	private AlertDialog dirDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_settings);
		initDirDialog();
		initView();
	}

	private void initView() {
		tv_setdownloaddir = (TextView)findViewById(R.id.tv_setdownloaddir);
		tv_setdownloaddir.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_setdownloaddir:
			dirDialog.show();
			break;

		default:
			break;
		}
	}
	
	public void initDirDialog(){
		AlertDialog.Builder builder = new  AlertDialog.Builder(this);
		builder.setItems(new String[]{getString(R.string.sdMenuIntSdcard) ,getString(R.string.sdMenuSel)} , new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					
					break;
				case 1:
					startActivity(new Intent(getApplicationContext(),DownloadDirActivity.class));
					break;
				default:
					break;
				}
			}
		});
		builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dirDialog.dismiss();
			}
		});
		dirDialog = builder.create();
	}
}
