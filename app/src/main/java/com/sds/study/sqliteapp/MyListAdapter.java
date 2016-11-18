package com.sds.study.sqliteapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**

    리스트뷰에 보여질 화면이 단일 위젯이 아닌, 2개 이상으로 구성된
    복합 위젯일 경우 개발자가 디자인을 정의하므로 어댑터를 재정의한다.

 */

public class MyListAdapter extends BaseAdapter{

    Context context;
    SQLiteDatabase db;
    ArrayList<Member> memberList = new ArrayList<>();

    String TAG;

    //  SqliteDatabase는 MainActivity에서 받아와야 하기 때문에
    //  생성자에 매개변수로 MainActivity인 context를 넘겨준다.
    public MyListAdapter(Context context){

        this.context = context;
        MainActivity mainActivity = (MainActivity) context;
        db = mainActivity.db;

        TAG = this.getClass().getName()+"/byCanet";

        getList();

    }

    //  데이터베이스로부터 레코드 가져오기
    public void getList(){

        String sql = "select * from member";

        Cursor rs = db.rawQuery(sql, null);     //  Cursor는 ResultSet과 비슷하다.
                                                //  rawQuery 에서 null은 바인드 변수 자리이다.
        //  기존 arrayList 모두 삭제
        memberList.removeAll(memberList);

        while(rs.moveToNext()){

            int member_id = rs.getInt(rs.getColumnIndex("member_id"));
            String id = rs.getString(rs.getColumnIndex("id"));
            String password = rs.getString(rs.getColumnIndex("password"));

            Log.d( TAG, "id="+id+", password="+password);

            Member dto = new Member();

            dto.setMember_id(member_id);
            dto.setId(id);
            dto.setPassword(password);

            memberList.add(dto);

        }

    }

    @Override
    public int getCount(){

        return memberList.size();

    }

    @Override
    public Object getItem(int i){

        return memberList.get(i);

    }

    @Override
    public long getItemId(int i){

        return memberList.get(i).getMember_id();

    }

    //  안드로이드 리스트 보여주기에서 가장 중요한 부분이다.
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup){

        View view=null;     //  반환되어질 item을 view라 명시함
        Member member = memberList.get(i);

        //  이미 생성된 아이템이라면 ( 해당 index에 아이템이 이미 채워져 있다면 )
        if( view != null ){

            view = convertView;
            MemberItem item = (MemberItem) view;     //  convertView는 이 클래스를 말한다. ( LinearLayout을 상속받은 item 한개 )
            item.setMember( member );

        }else{      //  생성되어지지 않은 아이템이라면 ( 해당 index에 아무것도 채워져 있지 않다면 )

            view = new MemberItem(context, member);

        }

        return view;

    }

}
