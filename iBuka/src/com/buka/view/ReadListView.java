package com.buka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.widget.ListView;

public class ReadListView extends ListView {
	public enum Mode {
		NONE, MOVING, MULTIMOVING, DOWN, SINGLE;
	}

	private Enum state;
	private float prLen;
	private float nowLen;
	int startX;
	int startY;
	/**
	 * 计算出来的 view高度，使用它的目的是 修正小数点对控件大小的影响
	 */
	private float calculateHeight;
	/**
	 * 计算出来的 view宽度，使用它的目的是 修正小数点对控件大小的影响
	 */
	private float calculatewidth;

	// float pregetHeight;
	// float nowheight;
	// private PointF point1=new PointF();
	// private PointF point2=new PointF();
	public ReadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) event.getX();
			startY = (int) event.getY();
			state = Mode.SINGLE;
			break;
		case MotionEvent.ACTION_MOVE:
			int tempX = (int) event.getX();
			int tempY = (int) event.getY();
			// point1.x=tempX;
			// point1.y=tempY;
			/*
			 * 拖拽
			 */
			if (state == Mode.SINGLE) {
				layout(getLeft() + tempX - startX, getTop() + tempY - startY,
						getRight() + tempX - startX, getBottom() + tempY
								- startY);
			}
			/*
			 * 缩放
			 */
			else if (state == Mode.MULTIMOVING) {
				nowLen = measureSpace(event);
				float scale;
				scale = nowLen / prLen;

				scaleLayout(scale,
						calculateCenter(event.getX(0), event.getX(1)), scale,
						calculateCenter(event.getY(0), event.getY(1)));
				prLen = nowLen;

			}
			break;
		case MotionEvent.ACTION_UP:

			state = Mode.NONE;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:

			prLen = measureSpace(event);
			state = Mode.MULTIMOVING;
			break;
		case MotionEvent.ACTION_POINTER_UP:

			state = Mode.NONE;

			break;

		}
		return true;
	}

	private float calculateCenter(float point1, float point2) {
		if (point2 > point1) {
			float num;
			num = (point2 - point1) / 2 + point1;
			if (num < 0)
				num = 0;
			return num;

		} else {
			float num;
			num = (point1 - point2) / 2 + point2;
			if (num < 0)
				num = 0;
			return num;

		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (getMeasuredHeight() > 0)
			calculateHeight = getHeight();
		if (getMeasuredWidth() > 0)
			calculatewidth = getWidth();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	private float measureSpace(MotionEvent event) {
		float xLen = event.getX(0) - event.getX(1);
		float yLen = event.getY(0) - event.getY(1);
		float len = FloatMath.sqrt(xLen * xLen + yLen * yLen);
		return len;
	}

	/**
	 * 按比例进行缩放图片 scaleNumX=scaleNumY 根据缩放比例，和两个手指中点进行分段缩放
	 * 
	 * @param scaleNumX
	 * @param pointX
	 *            两个手指x轴中点
	 * @param scaleNumY
	 * @param pointY
	 *            两个手指y轴中点
	 */
	private void scaleLayout(float scaleNumX, float pointX, float scaleNumY,
			float pointY) {
		int leftMarginX = 0;
		int rightMarginX = 0;
		int topMarginY = 0;
		int bottomMarginY = 0;
		// iniheight*=scaleNumY;

		calculateHeight *= scaleNumY;
		calculatewidth *= scaleNumX;
		// System.out.println("--------------"+iniheight);
		// System.out.println("-----前------"+getWidth()*scaleNumX+"    "+getHeight()*scaleNumY+"   "+scaleNumX+"  "+scaleNumY);
		if (scaleNumX >= 1) {
			// System.out.println(getLeft()+"  ");
			/**
			 * 手指中点x轴左边
			 */
			leftMarginX = (int) (getLeft() - (((pointX) * (scaleNumX - 1))));
			/**
			 * 手指中点x轴右边
			 */
			rightMarginX = (int) (getRight() + ((getWidth() - pointX) * (scaleNumX - 1)));
			// System.out.println("放大"+leftMarginX+"   "+rightMarginX+"   "+"   "+pointX+"   "+((
			// pointX)*(scaleNumX-1)));
		} else {
			leftMarginX = (int) (getLeft() + ((pointX) * (1 - scaleNumX)));
			rightMarginX = (int) (getRight() - ((getWidth() - pointX) * (1 - scaleNumX)));
			// System.out.println("缩小"+leftMarginX+"   "+rightMarginX+"   "+scaleNumX);
		}
		if (scaleNumY >= 1) {
			/**
			 * 手指中点y轴上边
			 */
			topMarginY = (int) (getTop() - pointY * (scaleNumY - 1));
			/**
			 * 手指中点y轴下边
			 */
			bottomMarginY = (int) (getBottom() + (getHeight() - pointY)
					* (scaleNumY - 1));
		} else {
			topMarginY = (int) (getTop() + (pointY * (1 - scaleNumY)));
			bottomMarginY = (int) (getBottom() - ((getHeight() - pointY) * (1 - scaleNumY)));
		}
		// System.out.println("leftMarginX"+leftMarginX+"topMarginY"+topMarginY+"rightMarginX"+rightMarginX+"bottomMarginY"+bottomMarginY);
		/*
		 * 因为有小数点的差异所以 要重新整所有坐标的值
		 * 不使用下面这些，也可以是图片正常显示，但是控件的大小会产生变化。可以通过设置控件的背景看出来。
		 */
		int realwidth = rightMarginX - leftMarginX;
		int realheight = bottomMarginY - topMarginY;

		topMarginY -= (calculateHeight - realheight) / 2;
		bottomMarginY += (calculateHeight - realheight) / 2;

		leftMarginX -= (calculatewidth - realwidth) / 2;
		rightMarginX += (calculatewidth - realwidth) / 2;
		/*
		 * 布局控件的显示
		 */
		layout(leftMarginX, topMarginY, rightMarginX, bottomMarginY);
		// System.out.println("------后--------"+getWidth()+"  "+getHeight());
	}

}
