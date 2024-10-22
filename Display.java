package com.login.main;

import com.login.register.Choice;
import com.login.register.MemberRegister;

public class Display {

    // 프로그램의 진입점
    public static void main(String[] args) {
        MemberRegister memberRegister = new MemberRegister(); // 회원 등록을 관리하는 MemberRegister 객체 생성
        Choice choice = new Choice(memberRegister); // Choice 인스턴스 생성, 회원 등록 정보를 전달

        // 로그인 화면으로 바로 이동
        choice.showLogin(); // 로그인 화면 표시 및 로그인 처리
    }
}
