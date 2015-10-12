package com.example.baseaapterdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity{

	private GridView gridView;
	private List<Map<String,Object>> list;
	MyAdapter myAdapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionbar = getActionBar();
		actionbar.hide();

		setContentView(R.layout.activity_main);
		initialView();
		
		list=getData();
	}
	
	private void initialView(){
		gridView=(GridView) findViewById(R.id.main_gridview);
		myAdapter=new MyAdapter(MainActivity.this);

		gridView.setAdapter(myAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,	long arg3) {
				if((myAdapter.getCount()-1)>position){
					//点击除第一项以外的item
				}else {
					//点击最后一项item
//					Intent intent=new Intent(this,AddItemActivity.class);  //这样会报错
					Intent intent=new Intent(MainActivity.this,AddItemActivity.class);
					startActivityForResult(intent, 68);
				}
			}
		});
	}
	
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		for(int i=0;i<15;i++){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			map.put("name", "chenlin"+String.valueOf(i));
			map.put("describe", "goodboy"+String.valueOf(i));
			list.add(map);
		}
		return list;
	}
	
	static class Holder{
		public ImageView image;
		public TextView name;
		public TextView describe;
	}
	
	class MyAdapter extends BaseAdapter{
//		private Holder myHolder=null;
		private LayoutInflater layoutInflater=null;
		
		private MyAdapter(Context context){
//			this.layoutInflater=context;
			this.layoutInflater=LayoutInflater.from(context);
		}
		
		@Override
		public View getView(int poisition, View convertView, ViewGroup arg2) {
			Holder holder=null;
			if(convertView==null){
				holder=new Holder();
				//差一个填充器
//				layoutInflater.inflate(R.layout.list_item, null);
//  (1)				
//				holder.image=(ImageView) list.get(poisition).get("image");
//				holder.name=(TextView) list.get(poisition).get("name");
//				holder.describe=(TextView) list.get(poisition).get("describe");
//	(2)
//				holder.image=(ImageView) findViewById(R.id.image);
//				holder.name=(TextView) findViewById(R.id.name_tv);
//				holder.describe=(TextView) findViewById(R.id.describe_tv);
		
				//原理 ：layoutInfater -> convertView -> holder
				convertView=layoutInflater.inflate(R.layout.list_item, null);
				holder.image=(ImageView) convertView.findViewById(R.id.image);
				holder.name=(TextView) convertView.findViewById(R.id.name_tv);
				holder.describe=(TextView) convertView.findViewById(R.id.describe_tv);
				convertView.setTag(holder);
				
			}else {
				holder=(Holder) convertView.getTag();			
			}
			
//			holder.image=(ImageView) list.get(poisition).get("image");
			holder.image.setBackgroundResource((Integer) list.get(poisition).get("image"));
//			holder.name=(TextView) list.get(poisition).get("name");
//			holder.describe=(TextView) list.get(poisition).get("describe");
			holder.name.setText((String) list.get(poisition).get("name"));
			holder.describe.setText((String) list.get(poisition).get("describe"));
			
			return convertView;
		}
		
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

	
	}
}
