package com.buka.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class CategoryView extends GridView {
	public CategoryView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CategoryView(Context context) {
		super(context);
	}

	public CategoryView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
