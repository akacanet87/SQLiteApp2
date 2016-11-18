package com.sds.study.sqliteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by CANET on 2016-11-18.
 */

public class MemberItem extends LinearLayout{

    private Member member;
    TextView textMember_id, textName, textPassword;

    public MemberItem(Context context, Member member ){

        super(context);
        this.member = member;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.member_item, this);

        textMember_id = (TextView) this.findViewById(R.id.member_id);
        textName = (TextView) this.findViewById(R.id.name);
        textPassword = (TextView) this.findViewById(R.id.password);

        textMember_id.setText( Integer.toString(this.member.getMember_id()));
        textName.setText(this.member.getId());
        textPassword.setText(this.member.getPassword());

    }

    public Member getMember(){

        return member;

    }

    public void setMember(Member member){

        this.member = member;
        textMember_id.setText(member.getMember_id());
        textName.setText(member.getId());
        textPassword.setText(member.getPassword());

    }

}
