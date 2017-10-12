package com.iflytek.clientdemo.com.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.iflytek.clientdemo.R;

import java.util.List;

public class DictionaryViewUtil {
	private int mWidth, mHeight, mMargin, rounding, remainder;
	private LayoutInflater inflater;
	private Context mContext;
	private DictionaryBean mdictionaryBean;

	public DictionaryViewUtil(Context context, int width, int height, int margin) {
		this.mWidth = width;
		this.mHeight = height;
		this.mMargin = margin;
		this.mContext = context;

		inflater = LayoutInflater.from(mContext);
	}

	/**
	 * 显示数据字典视图
	 * 
	 * @param view
	 * @param dictionaryBeans
	 */
	public void showDictionaryView(final RelativeLayout view,
                                   List<DictionaryBean> dictionaryBeans, int type) {
		if (dictionaryBeans != null) {
			LayoutParams params = null;

			if (type == 1) {
				mHeight = (int) (mHeight * 1.2);
			}
			for (int i = 0; i < dictionaryBeans.size(); i++) {
				final DictionaryBean dictionaryBean = dictionaryBeans.get(i);
				final View convertView = inflater.inflate(
						R.layout.dictionary_tv, null);
				final TextView tv = (TextView) convertView
						.findViewById(R.id.tv);

				if (type == 1) {
					tv.setSingleLine(false);
					tv.setMaxLines(2);
				}
				tv.setText(dictionaryBean.getValue());
				rounding = i % 4;
				remainder = i / 4;
				// 设置view的显示区域
				params = new LayoutParams(mWidth, mHeight);
				params.setMargins(rounding * (mMargin + mWidth), remainder
						* (mMargin + mHeight), 0, 0);
				convertView.setLayoutParams(params);

				view.addView(convertView);

				tv.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if ("0".equals(dictionaryBean.getIsSelect())) {
							dictionaryBean.setIsSelect("1");
							tv.setSelected(true);
						} else if ("1".equals(dictionaryBean.getIsSelect())) {
							dictionaryBean.setIsSelect("0");
							tv.setSelected(false);
						}
					}
				});
			}
		}
	}

	/**
	 * 显示数据字典视图
	 * 
	 * @param view
	 * @param dictionaryBeans
	 */
	public void showDictionaryView1(final RelativeLayout view,
			List<DictionaryBean> dictionaryBeans) {
		if (dictionaryBeans != null) {
			LayoutParams params = null;
			for (int i = 0; i < dictionaryBeans.size(); i++) {
				final DictionaryBean dictionaryBean = dictionaryBeans.get(i);
				final View convertView = inflater.inflate(
						R.layout.dictionary1_tv, null);
				final TextView tv = (TextView) convertView
						.findViewById(R.id.tv1);
				tv.setText(dictionaryBean.getValue());
				rounding = i % 3;
				remainder = i / 3;
				// 设置view的显示区域
				params = new LayoutParams(mWidth, mHeight);
				params.setMargins(rounding * (mMargin + mWidth), remainder
						* (mMargin + mHeight), 0, 0);
				convertView.setLayoutParams(params);



				tv.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if ("0".equals(dictionaryBean.getIsSelect())) {
							dictionaryBean.setIsSelect("1");
							tv.setSelected(true);
						} else if ("1".equals(dictionaryBean.getIsSelect())) {
							dictionaryBean.setIsSelect("0");
							tv.setSelected(false);
						}
					}
				});
				view.addView(convertView);
			}
		}
	}

	/**
	 * 获取被选中视图的id（数据字典视图）
	 */
	public void getDictionaryInfo(List<DictionaryBean> dictionaryBeans,
			DictionaryBean dictionaryBean) {
		if (dictionaryBeans != null) {
			StringBuffer idsBuffer = new StringBuffer();
			StringBuffer namesBuffer = new StringBuffer();
			for (int i = 0; i < dictionaryBeans.size(); i++) {
				mdictionaryBean = dictionaryBeans.get(i);
				if ("1".equals(mdictionaryBean.getIsSelect())) {
					idsBuffer.append(mdictionaryBean.getId() + ",");
					namesBuffer.append(mdictionaryBean.getValue() + ",");
				}
			}

			if (idsBuffer.length() > 0) {
				dictionaryBean.setId(idsBuffer.toString().substring(0,
						idsBuffer.length() - 1));
				dictionaryBean.setValue(namesBuffer.toString().substring(0,
						namesBuffer.length() - 1));
			} else {
				dictionaryBean.setId("");
				dictionaryBean.setValue("");
			}
		}
	}

	/**
	 * 重置数据字典中的数据使其状态变为初始未选中状态
	 * 
	 * @param dictionaryBeans
	 */
	public void resetDictionaryInfo(List<DictionaryBean> dictionaryBeans) {
		if (dictionaryBeans != null) {
			for (int i = 0; i < dictionaryBeans.size(); i++) {
				mdictionaryBean = dictionaryBeans.get(i);
				mdictionaryBean.setIsSelect("0");
			}
		}
	}
}
