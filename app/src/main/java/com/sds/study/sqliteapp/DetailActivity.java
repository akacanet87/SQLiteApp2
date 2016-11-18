package com.sds.study.sqliteapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.sds.study.sqliteapp.MainActivity.db;

/**
 * Created by CANET on 2016-11-18.
 */

public class DetailActivity extends AppCompatActivity {

    Intent intent;
    Member member;
    AnimationDrawable imgDrawable, imgLayout;
    ImageView img;
    LinearLayout layout;
    EditText detailMember_id, detailName, detailPassword;

    int member_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_layout);

        intent = getIntent();

        member = intent.getParcelableExtra("member");

        img = (ImageView) findViewById(R.id.imgWarior);
        layout = (LinearLayout) findViewById(R.id.layout);

        detailMember_id = (EditText) findViewById(R.id.detailMember_id);
        detailName = (EditText) findViewById(R.id.detailName);
        detailPassword = (EditText) findViewById(R.id.detailPassword);

        detailMember_id.setFocusable(false);
        detailMember_id.setClickable(false);

        member_id = member.getMember_id();

        detailMember_id.setText(Integer.toString(member_id));
        detailName.setText(member.getId());
        detailPassword.setText(member.getPassword());

        imgDrawable = (AnimationDrawable) img.getDrawable();
        imgLayout = (AnimationDrawable) layout.getBackground();

        imgDrawable.start();
        imgLayout.start();

    }

    public void update(){

        String sql;
        sql = "update member set id=?, password=? where member_id=?";

        db.execSQL(sql, new Object[]{

                detailName.getText().toString(), detailPassword.getText().toString(), detailMember_id.getText().toString()

        });

        setResult(RESULT_OK,intent);
        finish();

    }

    public void delete(){

        String sql;
        sql = "delete from member where member_id=?";

        db.execSQL(sql, new String[]{detailMember_id.getText().toString()});

        /*intent.putExtra("delete", detailMember_id.getText());*/

        setResult(RESULT_OK,intent);
        finish();

    }

    public void btnDetailClick(View view){

        if( view.getId()== R.id.btnUpdate){

            update();

        } else if( view.getId()== R.id.btnDelete){

            delete();

        }

    }

}
