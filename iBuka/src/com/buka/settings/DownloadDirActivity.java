package com.buka.settings;

import java.io.File;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.buka.R;
import com.buka.base.BaseActivity;
import com.buka.tools.FileTools;
import com.buka.tools.Settings;

/**
 * 漫画下载路径选择
 */
public class DownloadDirActivity extends BaseActivity implements
		OnClickListener {
	private TextView tv_dirPath;
	private Button btn_dirManual;
	private Button btn_dirOk;
	private Button btn_dirParent;
	private ListView lv_file;
	/** 当前路径 */
	private String current_dir;
	private ArrayList<String> fileList;
	/** 手动填写 */
	AlertDialog custom_dir_dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_downloaddir);
		initView();
		initData();
		initFileList();
	}

	private void initData() {
		current_dir = Settings.getInstance(this).getDonwloadDir();
		tv_dirPath.setText(current_dir);
	}

	private void initView() {
		tv_dirPath = (TextView) findViewById(R.id.tv_dirPath);
		btn_dirManual = (Button) findViewById(R.id.btn_dirManual);
		btn_dirOk = (Button) findViewById(R.id.btn_dirOk);
		btn_dirParent = (Button) findViewById(R.id.btn_dirParent);
		lv_file = (ListView) findViewById(R.id.lv_file);
		btn_dirManual.setOnClickListener(this);
		btn_dirOk.setOnClickListener(this);
		btn_dirParent.setOnClickListener(this);
	}

	private void initFileList() {
		fileList = new ArrayList<String>();
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_dirManual:
			showDirManualDialog();
			break;
		case R.id.btn_dirOk:
			Settings.getInstance(getApplicationContext()).setDonwloadDir( current_dir);
			finish();
			Toast.makeText(getApplicationContext(), getString(R.string.setDlDirDone), Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btn_dirParent:
			File fire = new File(current_dir);
			if(fire.getParent() != null){
				
			}
			break;
		default:
			break;
		}
	}

	public void showDirManualDialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle(getString(R.string.dirManualTitle));
		LinearLayout ll_dialog = new LinearLayout(getApplicationContext());
		ll_dialog.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		final EditText edt_dir = new EditText(getApplicationContext());
		edt_dir.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		ll_dialog.addView(edt_dir);
		builder.setView(ll_dialog);
		edt_dir.setText(current_dir);
		builder.setNegativeButton(getString(R.string.dirOk),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String custom_dir = edt_dir.getText().toString();
						if (FileTools.isFileExist(custom_dir)) {
							Settings.getInstance(getApplicationContext()) .setDonwloadDir(custom_dir);
							finish();
							Toast.makeText(getApplicationContext(),
									getString(R.string.setDlDirDone),
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getApplicationContext(),
									getString(R.string.createDirErrManual),
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		builder.setPositiveButton(getString(R.string.btnCancel),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		custom_dir_dialog = builder.create();
		custom_dir_dialog.show();
	}
}
