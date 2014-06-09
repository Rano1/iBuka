package com.buka.view;

import com.buka.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
/** 
 * 漫画详情目录列表View
 * */
public class ComicDirView extends LinearLayout {
	
	public ComicDirView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ComicDirView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		View view_dir = LayoutInflater.from(context).inflate(R.layout.view_comic_detail_dir, null);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		addView(view_dir , params);
	}

}
