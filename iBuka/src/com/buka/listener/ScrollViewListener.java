package com.buka.listener;

import com.buka.view.ComicScrollView;

public interface ScrollViewListener {
	void onScrollChanged(ComicScrollView scrollView, int x, int y, int oldx,
			int oldy);
}