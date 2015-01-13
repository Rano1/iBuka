package com.buka.view;

import com.buka.ComicReadActivity;
import com.buka.ComicReadSettingActivity;
import com.buka.R;
import com.buka.entity.ComicContent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ReadMenuView extends RelativeLayout {
	Context context;
	LinearLayout layout_comic_read_menu_top_bar;
	LinearLayout layout_comic_read_menu_bottom_bar;
	LinearLayout layout_comic_read_menu_ButtonsParent;
	SeekBar comic_read_menu_pageSeekbar;
	TextView comic_read_menu_pageTips;
	setMenuButtonListener setMenuButtonListener;
	MenuSeekBarChangeListener menuSeekBarChangeListener;

	public ReadMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init(context);
	}

	private void init(Context context) {
		// TODO Auto-generated method stub
		LayoutInflater.from(context).inflate(R.layout.comic_read_menu, this,
				true);
		layout_comic_read_menu_top_bar = (LinearLayout) findViewById(R.id.layout_comic_read_menu_top_bar);
		layout_comic_read_menu_bottom_bar = (LinearLayout) findViewById(R.id.layout_comic_read_menu_bottom_bar);
		layout_comic_read_menu_ButtonsParent = (LinearLayout) findViewById(R.id.layout_comic_read_menu_ButtonsParent);
		comic_read_menu_pageTips = (TextView) findViewById(R.id.comic_read_menu_pageTips);
		comic_read_menu_pageSeekbar = (SeekBar) findViewById(R.id.comic_read_menu_pageSeekbar);
		initMenuBottomButton();
	}

	public void setMenuButtonClickListener(
			setMenuButtonListener setMenuButtonListener) {
		this.setMenuButtonListener = setMenuButtonListener;
	}

	public void setMenuSeekBarChangeListener(
			MenuSeekBarChangeListener menuSeekBarChangeListener) {
		this.menuSeekBarChangeListener = menuSeekBarChangeListener;
	}

	/** 给SeeKbar赋值 */
	public void setInfo(ComicContent comiccontent) {
		initSeeKbar(comiccontent.getTotalpages(), comiccontent.getPage());
	}

	/** 显示Menu */
	public void showMenu() {
		animation_show(layout_comic_read_menu_top_bar, "top");
		animation_show(layout_comic_read_menu_bottom_bar, "bottom");
	}

	/** 消失Menu */
	public void dissmissMenu() {
		animation_dismiss(layout_comic_read_menu_top_bar, "top");
		animation_dismiss(layout_comic_read_menu_bottom_bar, "bottom");
	}

	public int seeKbar_check_postion = 0;

	// 初始化SeeKbar
	private void initSeeKbar(final int secion_page, final int postion) {
		comic_read_menu_pageSeekbar.setMax(secion_page - 1);
		comic_read_menu_pageSeekbar.setProgress(postion);
		comic_read_menu_pageSeekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						comic_read_menu_pageTips.setVisibility(View.GONE);
						// comic_read_listview.setSelection(seeKbar_check_postion);
						menuSeekBarChangeListener.onStopTouch();
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						comic_read_menu_pageTips.setVisibility(View.VISIBLE);
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						// TODO Auto-generated method stub
						comic_read_menu_pageTips.setText((progress + 1) + "/"
								+ secion_page);
						seeKbar_check_postion = progress;
					}
				});
	}

	/**
	 * 动态添加BUTTON
	 */
	private void initMenuBottomButton() {
		int[] icon_id = new int[] { R.drawable.icon_rm_pic,
				R.drawable.icon_rm_bookmark, R.drawable.icon_rm_brightness,
				R.drawable.icon_rm_rotate, R.drawable.icon_rm_setting };
		String[] icon_label = new String[] { "截图", "书签", "亮度", "横屏", "设置" };
		for (int i = 0; i < icon_id.length; i++) {
			LinearLayout layout_button = new LinearLayout(context);
			LinearLayout.LayoutParams layout_lp = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT, 1);
			layout_button.setOrientation(LinearLayout.VERTICAL);
			layout_button.setBackgroundResource(R.drawable.readmenu_btn);
			layout_button.setClickable(true);
			layout_button.setGravity(Gravity.CENTER_HORIZONTAL);
			layout_button.setPadding(10, 10, 10, 10);
			ImageView img = new ImageView(context);
			img.setImageResource(icon_id[i]);
			layout_button.addView(img, new LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			TextView text = new TextView(context);
			text.setText(icon_label[i]);
			text.setTextSize(12);
			text.setTextColor(getResources().getColor(android.R.color.white));
			layout_button.addView(text, new LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			if (i == 2) {
				layout_button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						setMenuButtonListener.brightnessClick();
					}
				});
			}
			if (i == 3) {
				layout_button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						setMenuButtonListener.rotateClick();
					}
				});
			}
			if (i == 4) {
				layout_button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						setMenuButtonListener.settingClick();
					}
				});
			}
			layout_comic_read_menu_ButtonsParent.addView(layout_button,
					layout_lp);
		}
	}

	/**
	 * MENU展开动画
	 */
	private void animation_show(final LinearLayout r, String bar_type) {
		AnimationSet animationSet = new AnimationSet(true);
		// TranslateAnimation translateAnimation = new TranslateAnimation(
		// Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
		// Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, jl);
		// 没有指定fromXType toXType fromYType toYType 时候，默认是以自己为相对参照物
		TranslateAnimation translateAnimation;
		if (bar_type.equals("top")) {
			translateAnimation = new TranslateAnimation(0, 0,
					-layout_comic_read_menu_top_bar.getHeight(), 0);
		} else {
			translateAnimation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
					0f, Animation.RELATIVE_TO_PARENT, 1f,
					Animation.RELATIVE_TO_PARENT, 0f);
		}
		translateAnimation.setDuration(500);
		animationSet.addAnimation(translateAnimation);
		r.startAnimation(animationSet);
		translateAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				layout_comic_read_menu_top_bar.setVisibility(View.VISIBLE);
				layout_comic_read_menu_bottom_bar.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				layout_comic_read_menu_top_bar.setVisibility(View.VISIBLE);
				layout_comic_read_menu_bottom_bar.setVisibility(View.VISIBLE);
				r.clearAnimation();
			}
		});
	}

	/**
	 * MENU消失动画
	 */
	private void animation_dismiss(final LinearLayout r, String bar_type) {
		AnimationSet animationSet = new AnimationSet(true);
		// TranslateAnimation translateAnimation = new TranslateAnimation(
		// Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
		// Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, jl);
		// 没有指定fromXType toXType fromYType toYType 时候，默认是以自己为相对参照物
		TranslateAnimation translateAnimation;
		if (bar_type.equals("top")) {
			translateAnimation = new TranslateAnimation(0, 0, 0,
					-layout_comic_read_menu_top_bar.getHeight());
		} else {
			translateAnimation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
					0f, Animation.RELATIVE_TO_PARENT, 0f,
					Animation.RELATIVE_TO_PARENT, 1f);
		}
		translateAnimation.setDuration(500);
		animationSet.addAnimation(translateAnimation);
		r.startAnimation(animationSet);
		translateAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				layout_comic_read_menu_top_bar.setVisibility(View.GONE);
				layout_comic_read_menu_bottom_bar.setVisibility(View.GONE);
				r.clearAnimation();
			}
		});
	}

	/**
	 * 设置Menu上面的相关BUTTON点击事件监听
	 */
	public interface setMenuButtonListener {
		public void printscreenClick();

		public void bookmarkClick();

		public void brightnessClick();

		public void rotateClick();

		public void settingClick();
	}

	/**
	 * 设置Menu上面的SeekBar改变事件监听
	 */
	public interface MenuSeekBarChangeListener {
		public void onStopTouch();
	}
}
