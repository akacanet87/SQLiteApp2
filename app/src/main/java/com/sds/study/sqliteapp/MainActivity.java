package com.sds.study.sqliteapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    MyHelper myHelper;  //  db 구축
    static SQLiteDatabase db;  //  db 쿼리문 제어
    EditText textId, textPw;
    ListView listView;
    MyListAdapter myListAdapter;

    String TAG;
    static final int CALL_DETAIL_RESULTCODE=1220;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName()+"/byCanet";

        setContentView(R.layout.activity_main);

        init();

        textId = (EditText) findViewById(R.id.textId);
        textPw = (EditText) findViewById(R.id.textPw);
        listView = (ListView) findViewById(R.id.listView);

        myListAdapter = new MyListAdapter(this);

        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(this);

    }

    //  db 초기화
    public void init(){

        myHelper = new MyHelper(this, "iot.sqlite", null, 2);

        db = myHelper.getWritableDatabase();

    }

    //  이 앱이 보유한 sqlite 데이터베이스에 insert
    public void regist(){

        String sql = "insert into member(id, password)";
        sql += " values(?,?)";

        db.execSQL(sql, new String[]{

                textId.getText().toString(),
                textPw.getText().toString()

        });

        Log.d( TAG, "등록 완료" );

        //  ListView 갱신
        myListAdapter.getList();
        myListAdapter.notifyDataSetChanged();

    }

    public void btnClick(View view){

        if(view.getId()==R.id.btnInsert){

            //  ListView에게 어댑터의 정보를 다시 가져가서 갱신하게 하기
            myListAdapter.notifyDataSetChanged();

            regist();
            textId.setText("");
            textPw.setText("");

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){

        Intent intent = new Intent(this, DetailActivity.class);

        MemberItem item = (MemberItem) view;

        Member member = item.getMember();

        /*int member_id = member.getMember_id();
        String id = member.getId();
        String password = member.getPassword();*/

        Toast.makeText(this, i+"번째 "+member.getMember_id()+"이다", Toast.LENGTH_SHORT).show();

        //member.setMember_id();
        intent.putExtra("member", member);

        //startActivity(intent);

        startActivityForResult(intent, CALL_DETAIL_RESULTCODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode==CALL_DETAIL_RESULTCODE ){

            if( resultCode==RESULT_OK ){

                /*if( data.hasExtra("update") ){

                    Member updatedMember = data.getParcelableExtra("member");

                    String updatedMember_id = Integer.toString(updatedMember.getMember_id());

                    String sql;
                    sql = "delete from member where member_id=?";

                    db.execSQL(sql, new String[]{updatedMember.getId(),updatedMember.getPassword(), updatedMember_id});


                }else if( data.hasExtra("delete") ){

                    String sql;
                    sql = "delete from member where member_id=?";

                    String deletedMember_id = data.getStringExtra("delete");

                    db.execSQL(sql, new String[]{deletedMember_id});

                }*/

                myListAdapter.getList();
                myListAdapter.notifyDataSetChanged();

            }

        }

    }

}
