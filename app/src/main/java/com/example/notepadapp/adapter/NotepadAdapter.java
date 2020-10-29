package com.example.notepadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.notepadapp.Bean.NotepadBean;
import com.example.notepadapp.R;

import java.util.List;

public class NotepadAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;//这个对象用于加载item的布局文件
    private List<NotepadBean>list;//list集合是列表中需要显示的集合
    public NotepadAdapter(Context context, List<NotepadBean> list){
        layoutInflater= LayoutInflater.from(context);
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.activity_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        NotepadBean notepadBean=(NotepadBean)getItem(position);
        viewHolder.tvNotepadContent.setText(notepadBean.getNotepadContent());
        viewHolder.tvNotepadTime.setText(notepadBean.getNotepadTime());
        return convertView;
    }
    class ViewHolder {
        TextView tvNotepadContent;
        TextView tvNotepadTime;

        public ViewHolder(View view) {
            tvNotepadContent = view.findViewById(R.id.item_content);//记录的内容
            tvNotepadTime = view.findViewById(R.id.item_time);//保存记录的时间

        }
    }
}
