package com.buka;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.buka.adapter.ComicReadAdapter;
import com.buka.base.BaseActivity;
import com.buka.entity.ComicContent;
import com.buka.gallery.GalleryViewPager;
import com.buka.gallery.UrlPagerAdapter;
import com.buka.tools.BaseTools;
import com.buka.tools.Constants;
import com.buka.view.ReadMenuView;
import com.buka.view.ReadMenuView.MenuSeekBarChangeListener;
import com.buka.view.ReadMenuView.setMenuButtonListener;

/**
 * 漫画阅读
 */
public class ComicReadActivity extends BaseActivity {
	private RelativeLayout layout_comic_loading;
//	private ImageView comic_loading_img;
	private ProgressBar comic_loading_progressBar;
	private RelativeLayout layout_comic_read;
	private ListView comic_read_listview;
	private int index = 0;
	private ComicReadAdapter mAdapter;
	private ArrayList<ComicContent> comiccontent;
	private Boolean menu_bar_isshow = false;
	private int width;

	private UrlPagerAdapter mGalleryAdapter;
	private GalleryViewPager comic_read_gallery;

	private ReadMenuView readMenuView;

	private SharedPreferences sp_setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comic_read);
		Log.d("onCreate", "onCreate");
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		width = BaseTools.getwidthPixels(getApplicationContext());
		sp_setting = getSharedPreferences(Constants.SETTING, MODE_PRIVATE);
		initView();
		initReadInfo();// 初始化READINFO控件
		initMenu();// 初始化MENU控件
	}

	// * 是不是刚显示,true表示是*/
	private Boolean now_show = false;

	public void initView() {
		// TODO Auto-generated method stub
		layout_comic_loading = (RelativeLayout) findViewById(R.id.layout_comic_loading);
//		comic_loading_img = (ImageView) findViewById(R.id.comic_loading_img);
		comic_loading_progressBar = (ProgressBar) findViewById(R.id.comic_loading_progressBar);
		layout_comic_read = (RelativeLayout) findViewById(R.id.layout_comic_read);
		comic_read_listview = (ListView) findViewById(R.id.comic_read_listview);
		comic_read_gallery = (GalleryViewPager) findViewById(R.id.comic_read_gallery);
//		comic_loading_img.setBackgroundResource(R.drawable.l1001);
		ShowLoadingImage();
	}

	TextView readTime;
	TextView readNetwork;
	TextView readPage;
	ProgressBar battery;
	ImageView readInfo_settings;

	// 初始化readinfo
	private void initReadInfo() {
		// TODO Auto-generated method stub
		readNetwork = (TextView) findViewById(R.id.readNetwork);
		readTime = (TextView) findViewById(R.id.readTime);
		readPage = (TextView) findViewById(R.id.readPage);
		battery = (ProgressBar) findViewById(R.id.battery);
		readInfo_settings = (ImageView) findViewById(R.id.readInfo_settings);
		// readNetwork.setText(BaseTool.getAPNType_String(BaseTool.getAPNType(getApplicationContext())));
		initBatterReceiver();
		initDateReceiver();
		initConnectReceiver();
		readTime.setText(BaseTools.getNowDate());// 设置当前时间
		readInfo_settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),ComicReadSettingActivity.class);
				startActivityForResult(intent, REQUEST_SETTING);
			}
		});
	}

	// 初始化menu
	private void initMenu() {
		readMenuView = (ReadMenuView) findViewById(R.id.readMenuView);
		readMenuView
				.setMenuSeekBarChangeListener(new MenuSeekBarChangeListener() {

					@Override
					public void onStopTouch() {
						// TODO Auto-generated method stub
						comic_read_listview
								.setSelection(readMenuView.seeKbar_check_postion);
					}
				});
		initMenuWeight();
		initMenuBottomButton();
	}

	// 调整亮度对应布局
	LinearLayout comic_read_menu_brightnessControl;
	LinearLayout comic_read_menu_brightnessBox;
	SeekBar comic_read_menu_brightness;
	CheckBox comic_read_menu_brightnessSys;

	// 初始化menu里面按钮的对应布局
	private void initMenuWeight() {
		comic_read_menu_brightnessControl = (LinearLayout) findViewById(R.id.comic_read_menu_brightnessControl);
		comic_read_menu_brightnessBox = (LinearLayout) findViewById(R.id.comic_read_menu_brightnessBox);
		comic_read_menu_brightness = (SeekBar) findViewById(R.id.comic_read_menu_brightness);
		comic_read_menu_brightnessSys = (CheckBox) findViewById(R.id.comic_read_menu_brightnessSys);
		comic_read_menu_brightnessControl
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							float x_down = event.getRawX();
							float y_down = event.getRawY();
							if (x_down > comic_read_menu_brightnessBox
									.getRight()
									|| x_down < comic_read_menu_brightnessBox
											.getLeft()
									|| y_down > comic_read_menu_brightnessBox
											.getBottom()
									|| y_down < comic_read_menu_brightnessBox
											.getTop()) {
								// 关闭其他按钮的界面
								comic_read_menu_brightnessControl
										.setVisibility(View.GONE);
							}
							break;

						default:
							break;
						}
						return true;
					}
				});
	}

	public void ShowLoadingImage() {// 自动播放图片 通过Thread.sleep(2000) 每两秒刷新次图片
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (index == 100) {
						break;
					}
					Message msg = new Message();
					msg.obj = index;
					handler.sendMessage(msg);
					index++;
					if (index >= Constants.COMIC_LOADING_IMG.length) {
						index = 0;
					}
					try {
						Thread.sleep(250);// 具体应用时可以通过传参数的方法
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 这里之所以没有用switch 来判断传回的msg。what 来控制 是应为这里的handler
			// 只处理更新图片，传回来的信息就是图片的编号，所以不需要
			if ((Integer) msg.obj * 4 <= 100) {
//				comic_loading_img.setBackgroundResource(Constants.COMIC_LOADING_IMG[(Integer) msg.obj]);
				comic_loading_progressBar.setProgress((Integer) msg.obj * 4);
			} else {
				comic_loading_progressBar.setProgress(100);
				index = 100;
				layout_comic_loading.setVisibility(View.GONE);
				layout_comic_read.setVisibility(View.VISIBLE);

				comiccontent = new ArrayList<ComicContent>();
				comiccontent = Constants.getComicContent(getApplicationContext(), 1, 1);
//				for (int i = 0; i < IMAGES.length; i++) {
//					ComicContent comic = new ComicContent();
//					comic.setId(i);
//					comic.setPage(i + 1);
//					comic.setSection_page(IMAGES.length);
//					comic.setSection(1);
//					comic.setImg_url(IMAGES[i]);
//					comiccontent.add(comic);
//				}
				if (Constants.MODE_VERT.equals(sp_setting.getString(
						"read_mode", ""))) {
					if (Constants.MODE_VERT_SLIDE.equals(sp_setting.getString(
							"read_vert_mode", ""))) {
						initGallery();
					} else if ((Constants.MODE_VERT_FLOW.equals(sp_setting
							.getString("read_vert_mode", "")))) {
						initListView();
					}
				} else {
					initListView();
				}
			}
		};
	};

	public void initListView() {
		if (comiccontent != null && comiccontent.size() != 0) {
			comic_read_gallery.setVisibility(View.GONE);
			comic_read_listview.setVisibility(View.VISIBLE);
			mAdapter = new ComicReadAdapter(ComicReadActivity.this,
					comiccontent);
			comic_read_listview.setAdapter(mAdapter);
			comic_read_listview.setOnScrollListener(new OnScrollListener() {

				@Override
				public void onScrollStateChanged(AbsListView view,
						int scrollState) {
					// TODO Auto-generated method stub
					// if(scrollState ==
					// OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
					// int lastposition =
					// view.getLastVisiblePosition();//当前界面最下方那个页面
					// }
					if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
						if (view.getLastVisiblePosition() == (view.getCount() - 1)) {

						}
					}
				}

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					// TODO Auto-generated method stub
					// int nowposition = view.getFirstVisiblePosition();//
					// 当前界面POSTION
					readPage.setText(comiccontent.get(firstVisibleItem)
							.getPage()
							+ "/"
							+ comiccontent.get(firstVisibleItem).getTotalpages());
				}
			});
			comic_read_listview.setOnTouchListener(new OnTouchListener() {
				float x_up = 0, x_down = 0;
				float x_move = 0;
				float y_up = 0, y_down = 0, y_move = 0;

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						x_down = event.getRawX();
						y_down = event.getRawY();
						if (menu_bar_isshow && now_show) {
							readMenuView.dissmissMenu();
							menu_bar_isshow = !menu_bar_isshow;
						}
						Log.d("DOWN", "x_down=" + x_down + "y_down" + y_down);
						break;
					case MotionEvent.ACTION_MOVE:
						x_move = event.getRawX() - x_down;
						y_move = event.getRawY() - y_down;
						Log.d("MOVE", "x_move=" + x_move + "y_move" + y_move);
						break;
					case MotionEvent.ACTION_UP:
						x_up = event.getRawX();
						y_up = event.getRawY();
						Log.d("UP", "x_up=" + x_up + "y_up" + y_up);
						if (Math.abs(y_down - y_up) >= 10
								|| Math.abs(x_down - x_up) >= 10) {
							Log.d("tag", "break");
							break;
						}
						if (x_up < (width / 3)) {// 点击中间区域才触发menu
							comic_read_listview.smoothScrollBy(-500, 1);// 向上移动的像素
							break;
						} else if (x_up > (width / 3 * 2)) {
							comic_read_listview.smoothScrollBy(500, 1);
							break;
						}
						if (!now_show) {
							if (!menu_bar_isshow) {
								readMenuView.setInfo(comiccontent
										.get(comic_read_listview
												.getFirstVisiblePosition()));
								readMenuView.showMenu();
								now_show = true;
							}
							menu_bar_isshow = !menu_bar_isshow;
						} else {
							now_show = !now_show;
						}
						break;
					default:
						break;
					}
					return false;
				}
			});
		}
	}

	public void initGallery() {
		mGalleryAdapter = new UrlPagerAdapter(ComicReadActivity.this,
				comiccontent);
		comic_read_gallery.setAdapter(mGalleryAdapter);
		comic_read_gallery.setVisibility(View.VISIBLE);
		comic_read_listview.setVisibility(View.GONE);
	}

	public void initBrightness() {
		comic_read_menu_brightnessControl.setVisibility(View.VISIBLE);
		// 进度条绑定最大亮度，255是最大亮度
		comic_read_menu_brightness.setMax(255);
		// 取得当前亮度
		int normal = Settings.System.getInt(getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS, 255);
		// 进度条绑定当前亮度
		comic_read_menu_brightness.setProgress(normal);
		comic_read_menu_brightness
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// 取得当前进度
						int tmpInt = seekBar.getProgress();

						// 当进度小于80时，设置成80，防止太黑看不见的后果。
						if (tmpInt < 80) {
							tmpInt = 80;
						}
						// 根据当前进度改变亮度
						// Settings.System.putInt(getContentResolver(),
						// Settings.System.SCREEN_BRIGHTNESS, tmpInt);
						// tmpInt = Settings.System.getInt(getContentResolver(),
						// Settings.System.SCREEN_BRIGHTNESS, -1);
						WindowManager.LayoutParams wl = getWindow()
								.getAttributes();
						float tmpFloat = (float) tmpInt / 255;
						if (tmpFloat > 0 && tmpFloat <= 1) {
							wl.screenBrightness = tmpFloat;
						}
						getWindow().setAttributes(wl);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						// TODO Auto-generated method stub
					}
				});
		comic_read_menu_brightnessSys
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							int tmpInt = Settings.System.getInt(
									getContentResolver(),
									Settings.System.SCREEN_BRIGHTNESS, -1);
							WindowManager.LayoutParams wl = getWindow()
									.getAttributes();
							float tmpFloat = (float) tmpInt / 255;
							if (tmpFloat > 0 && tmpFloat <= 1) {
								wl.screenBrightness = tmpFloat;
							}
							getWindow().setAttributes(wl);
						}
					}
				});
	}

	public static final String[] IMAGES = new String[] {
			"http://imgsrc.baidu.com/forum/pic/item/a4a4c83d70cf3bc7870f1bacd300baa1cc112a94.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/35ae728da9773912d31b5321fa198618377ae2db.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/1ef9574e9258d1094d217ef2d358ccbf6e814dda.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/0b38d043ad4bd11336f0617258afa40f4afb05da.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/0f126d81800a19d8b80c524531fa828ba71e4694.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/cc8943a7d933c89593714e3bd31373f083020094.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/7ba37aec54e736d1f61c5a4899504fc2d4626995.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/4126a71ea8d3fd1f4a07747a324e251f94ca5f95.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/51e8d788d43f87948cf19e6ed01b0ef41ad53a95.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/c9f8b219ebc4b74516de6156cdfc1e178b821551.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/6a418b13632762d0733c72ffa2ec08fa503dc69a.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/1e6950da81cb39db848ab116d2160924aa183051.jpg", };

	/**
	 * 初始化电池广播
	 */
	BatteryReceiver batteryReceiver;

	public void initBatterReceiver() {
		// 注册广播接受者java代码
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		// 创建广播接受者对象
		batteryReceiver = new BatteryReceiver();
		// 注册receiver
		registerReceiver(batteryReceiver, intentFilter);
	}

	/**
	 * 电池-广播接受者
	 */
	class BatteryReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// 判断它是否是为电量变化的Broadcast Action
			if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
				// 获取当前电量
				int level = intent.getIntExtra("level", 0);
				// 电量的总刻度
				int scale = intent.getIntExtra("scale", 100);
				// 把它转成百分比
				battery.setProgress(level);
			}
		}
	}

	/**
	 * 初始化电池广播
	 */
	DateReceiver dateReceiver;

	public void initDateReceiver() {
		// 注册广播接受者java代码
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
		// 创建广播接受者对象
		dateReceiver = new DateReceiver();
		// 注册receiver
		registerReceiver(dateReceiver, intentFilter);
	}

	/**
	 * 系统时间-广播接受者
	 */
	class DateReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
				readTime.setText(BaseTools.getNowDate());
				Log.d("1", "DateReceiver");
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 当摧毁的时候删除广播
		unregisterReceiver(batteryReceiver);
		unregisterReceiver(dateReceiver);
		unregisterReceiver(connectReceiver);
	}

	/**
	 * 初始化广播 --网络状态
	 */
	ConnectReceiver connectReceiver;

	public void initConnectReceiver() {
		// 注册广播接受者java代码
		IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		// 创建广播接受者对象
		connectReceiver = new ConnectReceiver();
		// 注册receiver
		registerReceiver(connectReceiver, intentFilter);
	}

	/**
	 * 系统时间-广播接受者
	 */
	class ConnectReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			readNetwork.setText(BaseTools.getAPNType_String(BaseTools.getAPNType(getApplicationContext())));
		}
	}

	/**
	 * 动态添加BUTTON
	 */
	private void initMenuBottomButton() {
		readMenuView.setMenuButtonClickListener(new setMenuButtonListener() {

			@Override
			public void settingClick() {
				// TODO Auto-generated method stub
				if (menu_bar_isshow) {
					readMenuView.dissmissMenu();
					menu_bar_isshow = !menu_bar_isshow;
				}
				Intent intent = new Intent(getApplicationContext(),
						ComicReadSettingActivity.class);
				startActivityForResult(intent, REQUEST_SETTING);
			}

			@Override
			public void rotateClick() {
				// TODO Auto-generated method stub
				readMenuView.dissmissMenu();
				if (ComicReadActivity.this.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
					ComicReadActivity.this
							.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置为置屏幕为竖屏
				} else {
					ComicReadActivity.this
							.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 设置屏幕为横屏
				}
			}

			@Override
			public void printscreenClick() {
				// TODO Auto-generated method stub

			}

			@Override
			public void brightnessClick() {
				// TODO Auto-generated method stub
				initBrightness();
				if (menu_bar_isshow) {
					readMenuView.dissmissMenu();
					menu_bar_isshow = !menu_bar_isshow;
				}
			}

			@Override
			public void bookmarkClick() {
				// TODO Auto-generated method stub

			}
		});
	}

	public static int REQUEST_SETTING = 1;
	public final static int REQUEST_SETTING_VERT_SLIDE = 1;
	public final static int REQUEST_SETTING_VERT_FLOW = 3;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_SETTING) {
			switch (resultCode) {
			case REQUEST_SETTING_VERT_SLIDE:
				// initGallery();
				finish();
				Intent intent_slide = new Intent(getApplicationContext(),
						ComicReadActivity.class);
				startActivity(intent_slide);
				break;
			case REQUEST_SETTING_VERT_FLOW:
				// initListView();
				finish();
				Intent intent_flow = new Intent(getApplicationContext(),
						ComicReadActivity.class);
				startActivity(intent_flow);
				break;

			default:
				break;
			}
		}
	}

	// Configuration n. 配置；结构；外形
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// 当前屏幕为横屏
		} else {
			// 当前屏幕为竖屏
		}
	}
}
