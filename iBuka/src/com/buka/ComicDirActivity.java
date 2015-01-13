package com.buka;

import com.buka.base.BaseActivity;
import com.buka.view.ComicDirView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 漫画目录页面
 */
public class ComicDirActivity extends BaseActivity {
	Integer comic_id;
	TextView comic_dir_head_title;
	Button button_comic_dir_read;
	// Desc
	ImageView comic_detail_info_DescSwitcher;
	LinearLayout layout_comic_detail_info_Desc;
	TextView comic_detail_info_Desc;
	Boolean detail_intr_close_status = true;
	ComicDirView view_comicdirview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comic_dir);
		comic_id = getIntent().getIntExtra("comic_id", 0);
		initView();
		initData();
	}

	public void initView() {
		comic_dir_head_title = (TextView) findViewById(R.id.comic_dir_head_title);
		// Desc
		comic_detail_info_DescSwitcher = (ImageView) findViewById(R.id.comic_detail_info_DescSwitcher);
		layout_comic_detail_info_Desc = (LinearLayout) findViewById(R.id.layout_comic_detail_info_Desc);
		comic_detail_info_Desc = (TextView) findViewById(R.id.comic_detail_info_Desc);
		comic_dir_head_title.setText(String.valueOf(comic_id));
		// dir
		view_comicdirview = (ComicDirView) findViewById(R.id.view_comicdirview);
	}
	
	private void initData() {
		view_comicdirview.setInfo(comic_id);
	}
}
