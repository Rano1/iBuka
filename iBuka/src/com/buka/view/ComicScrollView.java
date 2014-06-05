package com.buka.view;


import com.buka.listener.ScrollViewListener;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ComicScrollView extends ScrollView {

	public ComicScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ComicScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ComicScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private ScrollViewListener scrollViewListener = null;

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		// TODO Auto-generated method stub
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}
	}
}
