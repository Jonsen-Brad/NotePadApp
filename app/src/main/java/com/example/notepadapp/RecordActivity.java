package com.example.notepadapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notepadapp.utils.DBUtils;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView note_back;
    TextView note_time;
    EditText content;
    ImageView delete;
    ImageView note_save;
    TextView noteName;
    private SQLiteHelper mSQLiteHelper;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        note_back= findViewById(R.id.note_back);//后退键
        note_time=findViewById(R.id.tv_time);//保存记录的时间
        content= findViewById(R.id.note_content);//记录的内容
        delete=findViewById(R.id.delete);//清空的按钮
        note_save= findViewById(R.id.note_save);//保存的按钮
        noteName= findViewById(R.id.note_name);//标题的名称
        note_back.setOnClickListener(this);
        delete.setOnClickListener(this);
        note_save.setOnClickListener(this);
        initData();
        //insertPic1();
    }

    private void insertPic1() {
        SpannableString ss = new SpannableString("pic");
        Drawable d = getResources().getDrawable(R.drawable.save);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        ss.setSpan(span, 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        content.append(ss);
    }

    public void initData(){
        mSQLiteHelper=new SQLiteHelper(this);
        noteName.setText("添加记录");
        Intent intent=getIntent();
        if(intent!=null){
            id=intent.getStringExtra("id");
            if(id!=null){
                noteName.setText("修改记录");
                content.setText(intent.getStringExtra("content"));
                note_time.setText(intent.getStringExtra("time"));
                note_time.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.note_back:
                finish();
                break;
            case R.id.delete:
                content.setText(" ");
                break;
            case R.id.note_save:
                String noteContent =content.getText().toString().trim();
                if(id!=null){
                    //修改记录的功能
                    if(noteContent.length()>0){
                        if (mSQLiteHelper.updateData(id,noteContent, DBUtils.getTime())){
                            showToast("修改成功");
                            setResult(2);
                            finish();
                        }else{
                            showToast("修改失败");
                        }
                    } else{
                        showToast("修改的记录内容不能为空");
                    }
                }else{
                    //添加记录的功能
                    if(noteContent.length()>0){
                        if (mSQLiteHelper.insertData(noteContent,DBUtils.getTime())){
                            showToast("保存成功");
                            setResult(2);
                            finish();
                        }else{
                            showToast("保存失败");
                        }
                    } else{
                        showToast("保存的记录内容不能为空");
                    }
                }
                break;
        }
    }

    private void showToast(String message) {
        Toast.makeText(RecordActivity.this,message,Toast.LENGTH_LONG).show();
    }
}
