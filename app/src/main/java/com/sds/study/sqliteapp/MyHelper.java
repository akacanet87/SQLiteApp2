package com.sds.study.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**

    안드로이드에서는 SQlite 데이터베이스의 위치가 이미
    /data/data/현재패키지/databases 로 정해져 있기 때문에
    오직 SqliteOpenHelper를 통해서만 접근 및 제어가 가능하다.
    (개발자가 임의로 디렉토리에 접근이 불가능)

    String name : 생성할 db명
    int version : 최초의 버전 숫자 // 임의로 가능하다. 엥간하면 1부터



 */
//  첫번째 생성자를 쓰고 implements도 가져야 한다.
public class MyHelper extends SQLiteOpenHelper{

    String TAG;

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        //  name은 db파일명이다. 이 시점에 db 생성
        super(context, name, factory, version);

        TAG = this.getClass().getName()+"/byCanet";

    }

    //  데이터베이스가 최초에 생성될 때 호출되는 메서드
    //  파일이 존재하지 않을 때 이 메서드가 호출.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        Log.d(TAG, "onCreate 호출");

        //  어플리케이션에 필요한 테이블이 있으면 이 시점에 구축한다.
        StringBuffer sql = new StringBuffer();
        sql.append("create table member(");
        sql.append("member_id integer primary key autoincrement");
        sql.append(", id varchar(20)");
        sql.append(", password varchar(20)");
        sql.append(");");

        sqLiteDatabase.execSQL(sql.toString());

        Log.d(TAG, "db 구축");

    }

    //  파일명은 동일하지만 version이 달라질 경우 이 메서드를 호출
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

        Log.d(TAG, "onUpgrade 호출");

    }

}
