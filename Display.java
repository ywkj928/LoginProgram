package com.login.main;

import com.login.register.Choice;
import com.login.register.MemberRegister;


public class Display {

    public static void main(String[] args) {
        MemberRegister memberRegister = new MemberRegister();
        Choice choice = new Choice(memberRegister); // Choice 인스턴스 생성

        // 로그인 화면으로 바로 이동
        choice.showLogin(); // 로그인 처리
    }
}

