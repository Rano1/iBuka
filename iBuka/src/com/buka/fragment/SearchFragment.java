package com.buka.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.buka.R;
import com.buka.adapter.CateAdapter;
import com.buka.base.BaseFragment;
import com.buka.entity.CateEntity;
import com.buka.tools.Constants;

public class SearchFragment extends BaseFragment implements OnClickListener{
	private final static String TAG = "SearchFragment";
	public GridView grid_cate;
	public EditText edt_search;
	public ImageView iv_searchBtn;
	public ImageView iv_searchClearBtn;
	private CateAdapter mCateAdapter;
	private ArrayList<CateEntity> cateList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		View view = LayoutInflater.from(activity).inflate(R.layout.frm_search,null);
		grid_cate = (GridView)view.findViewById(R.id.grid_cate);
		edt_search = (EditText)view.findViewById(R.id.edt_search);
		iv_searchBtn = (ImageView)view.findViewById(R.id.iv_searchBtn);
		iv_searchClearBtn = (ImageView)view.findViewById(R.id.iv_searchClearBtn);
		iv_searchBtn.setOnClickListener(this);
		iv_searchClearBtn.setOnClickListener(this);
		new Thread(){

			@Override
			public void run() {
				
				super.run();
			}
		}.start();
		setGridView();
		//设置输入框监听
		edt_search.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() > 0) {
					iv_searchClearBtn.setVisibility(View.VISIBLE);
				} else {
					iv_searchClearBtn.setVisibility(View.GONE);
				}

			}
		});
		return view;
	}

	public void setGridView() {
		cateList = Constants.getCateList();
		mCateAdapter = new CateAdapter(activity, cateList);
		grid_cate.setAdapter(mCateAdapter);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_searchBtn:
			
			break;
		case R.id.iv_searchClearBtn:
			edt_search.setText("");
			break;

		default:
			break;
		}
	}

}
