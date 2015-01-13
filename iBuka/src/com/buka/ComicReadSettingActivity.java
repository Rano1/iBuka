package com.buka;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.buka.base.BaseActivity;
import com.buka.tools.Constants;

public class ComicReadSettingActivity extends BaseActivity {

	private RadioGroup setting_radiogroup;
	private RadioButton setting_rbtn_readmode;
	private RadioButton setting_rbtn_advance;
	private ImageView setting_rbtn_readmode_divide;
	private ImageView setting_rbtn_advance_divide;
	private LinearLayout layout_comic_read_setting_adv;
	private LinearLayout layout_comic_read_setting_read_mode;
	// read
	private RadioGroup read_mode_setting_radiogroup;
	private RadioButton read_set_mode_horz;
	private RadioButton read_set_mode_vert;
	private LinearLayout layout_comic_read_setting_vert;
	private LinearLayout layout_comic_read_setting_horz;
	private CheckBox set_vert_mode_slide_page_button;
	private CheckBox set_vert_mode_slide_page_clip_bord_button;
	private CheckBox set_vert_mode_flow_page_button;
	private SharedPreferences sp_setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comic_read_setting);
		sp_setting = getSharedPreferences(Constants.SETTING, MODE_PRIVATE);
		initView();
	}

	public void initView() {
		// TODO Auto-generated method stub
		setting_radiogroup = (RadioGroup) findViewById(R.id.setting_radiogroup);
		setting_rbtn_readmode = (RadioButton) findViewById(R.id.setting_rbtn_readmode);
		setting_rbtn_advance = (RadioButton) findViewById(R.id.setting_rbtn_advance);
		setting_rbtn_readmode_divide = (ImageView) findViewById(R.id.setting_rbtn_readmode_divide);
		setting_rbtn_advance_divide = (ImageView) findViewById(R.id.setting_rbtn_advance_divide);
		layout_comic_read_setting_read_mode = (LinearLayout) findViewById(R.id.layout_comic_read_setting_read_mode);
		layout_comic_read_setting_adv = (LinearLayout) findViewById(R.id.layout_comic_read_setting_adv);
		setting_radiogroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						switch (checkedId) {
						case R.id.setting_rbtn_readmode:
							setting_rbtn_readmode
									.setTextColor(getResources().getColor(
											R.drawable.bar_btn_text_color_emph));
							setting_rbtn_advance.setTextColor(getResources()
									.getColor(R.drawable.bar_btn_text_color));
							layout_comic_read_setting_read_mode
									.setVisibility(View.VISIBLE);
							layout_comic_read_setting_adv
									.setVisibility(View.INVISIBLE);
							setting_rbtn_readmode_divide
									.setVisibility(View.VISIBLE);
							setting_rbtn_advance_divide
									.setVisibility(View.INVISIBLE);
							break;
						case R.id.setting_rbtn_advance:
							setting_rbtn_readmode.setTextColor(getResources()
									.getColor(R.drawable.bar_btn_text_color));
							setting_rbtn_advance
									.setTextColor(getResources().getColor(
											R.drawable.bar_btn_text_color_emph));
							layout_comic_read_setting_read_mode
									.setVisibility(View.INVISIBLE);
							layout_comic_read_setting_adv
									.setVisibility(View.VISIBLE);
							setting_rbtn_readmode_divide
									.setVisibility(View.INVISIBLE);
							setting_rbtn_advance_divide
									.setVisibility(View.VISIBLE);
							break;

						default:
							break;
						}
					}
				});
		// read
		read_mode_setting_radiogroup = (RadioGroup) findViewById(R.id.read_mode_setting_radiogroup);
		read_set_mode_horz = (RadioButton) findViewById(R.id.read_set_mode_horz);
		read_set_mode_vert = (RadioButton) findViewById(R.id.read_set_mode_vert);
		layout_comic_read_setting_vert = (LinearLayout) findViewById(R.id.layout_comic_read_setting_vert);
		layout_comic_read_setting_horz = (LinearLayout) findViewById(R.id.layout_comic_read_setting_horz);
		read_mode_setting_radiogroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						switch (checkedId) {
						case R.id.read_set_mode_horz:
							read_set_mode_horz
									.setBackgroundColor(getResources()
											.getColor(
													R.color.read_mode_setting_split_bg_color));
							read_set_mode_vert
									.setBackgroundColor(getResources()
											.getColor(
													R.color.read_mode_setting_split_color));
							layout_comic_read_setting_vert
									.setVisibility(View.GONE);
							layout_comic_read_setting_horz
									.setVisibility(View.VISIBLE);
							break;
						case R.id.read_set_mode_vert:
							read_set_mode_horz
									.setBackgroundColor(getResources()
											.getColor(
													R.color.read_mode_setting_split_color));
							read_set_mode_vert
									.setBackgroundColor(getResources()
											.getColor(
													R.color.read_mode_setting_split_bg_color));
							layout_comic_read_setting_vert
									.setVisibility(View.VISIBLE);
							layout_comic_read_setting_horz
									.setVisibility(View.GONE);
							break;
						default:
							break;
						}
					}
				});
		read_set_mode_horz.setChecked(true);
		// read_vert
		set_vert_mode_slide_page_button = (CheckBox) findViewById(R.id.set_vert_mode_slide_page_button);
		set_vert_mode_slide_page_clip_bord_button = (CheckBox) findViewById(R.id.set_vert_mode_slide_page_clip_bord_button);
		set_vert_mode_flow_page_button = (CheckBox) findViewById(R.id.set_vert_mode_flow_page_button);
		if (Constants.MODE_VERT.equals(sp_setting.getString("read_mode", ""))) {
			if (Constants.MODE_VERT_SLIDE.equals(sp_setting.getString(
					"read_vert_mode", ""))) {
				set_vert_mode_slide_page_button.setChecked(true);
			} else if (Constants.MODE_VERT_FLOW.equals(sp_setting.getString(
					"read_vert_mode", ""))) {
				set_vert_mode_flow_page_button.setChecked(true);
			} else {
				set_vert_mode_flow_page_button.setChecked(true);
			}
		} else {
			set_vert_mode_flow_page_button.setChecked(true);
		}
		set_vert_mode_slide_page_button
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						set_vert_mode_slide_page_button.setChecked(true);
						set_vert_mode_slide_page_clip_bord_button
								.setChecked(false);
						set_vert_mode_flow_page_button.setChecked(false);
						Editor editor = sp_setting.edit();
						editor.putString("read_mode", "vert");
						editor.putString("read_vert_mode", "slide");
						editor.commit();
					}
				});
		set_vert_mode_slide_page_clip_bord_button
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						set_vert_mode_slide_page_button.setChecked(false);
						set_vert_mode_slide_page_clip_bord_button
								.setChecked(true);
						set_vert_mode_flow_page_button.setChecked(false);
						// Editor editor = sp_setting.edit();
						// editor.putString("read_mode", "horz");
						// editor.putString("read_vert_mode", "slide");
						// editor.commit();
					}
				});
		set_vert_mode_flow_page_button
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						set_vert_mode_slide_page_button.setChecked(false);
						set_vert_mode_slide_page_clip_bord_button
								.setChecked(false);
						set_vert_mode_flow_page_button.setChecked(true);
						Editor editor = sp_setting.edit();
						editor.putString("read_mode", "vert");
						editor.putString("read_vert_mode", "flow");
						editor.commit();
					}
				});

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Log.d("onBackPressed", "onBackPressed");
		Intent intent = new Intent(getApplicationContext(),
				ComicReadActivity.class);
		if (read_set_mode_vert.isChecked()) {
			if (set_vert_mode_slide_page_button.isChecked()) {
				setResult(ComicReadActivity.REQUEST_SETTING_VERT_SLIDE, intent);
				finish();
			} else if (set_vert_mode_flow_page_button.isChecked()) {
				setResult(ComicReadActivity.REQUEST_SETTING_VERT_FLOW, intent);
				finish();
			}
		} else {
			super.onBackPressed();
		}
	}
}
