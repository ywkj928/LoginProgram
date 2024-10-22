package com.login.controller;

import com.login.domain.MemberInfo;
import com.login.main.Main;
import com.login.register.Choice;
import com.login.register.MemberRegister;

public class LoginHandle {
	private MemberRegister memberRegister; // 회원 등록 정보를 관리하는 객체

    // 생성자: MemberRegister 객체를 초기화
    public LoginHandle(MemberRegister memberRegister) {
        this.memberRegister = memberRegister;
    }

    // 로그인 처리 메서드
    public boolean handleLogin(Choice choice) {
        int attemptCount = 0; // 로그인 시도 횟수
        final int maxAttempts = 3; // 최대 로그인 시도 횟수

        // 로그인 시도
        while (attemptCount < maxAttempts) {
        	LoginProgram loginProgram = new LoginProgram(memberRegister); // 로그인 프로그램 객체 생성
            String loginResult = loginProgram.login(); // 로그인 시도

            // 로그인 성공
            if (loginResult.equals("SUCCESS")) {
                navigateToMenu(loginProgram.getName()); // 성공 시 메뉴로 이동
                return true; // 로그인 성공 시 true 반환
            } 
            // ID가 존재하지 않는 경우
            else if (loginResult.equals("ID_NOT_FOUND")) {
                choice.showMenu(); // 메인 메뉴로 돌아감
                return false; // false 반환
            } 
            // 비밀번호가 틀린 경우
            else {
                attemptCount++; // 시도 횟수 증가
                if (loginResult.equals("PASSWORD_INCORRECT")) {
                    // 비밀번호가 틀린 경우 추가 로직이 필요할 수 있음
                }

                // 최대 시도 횟수 초과 시
                if (attemptCount >= maxAttempts) {
                    System.out.println("로그인횟수 초과."); // 오류 메시지 출력
                    System.exit(0); // 프로그램 종료
                }
            }
        }

        return false; // 로그인 실패 시 false 반환
    }

    // 사용자에 따라 메뉴로 이동하는 메서드
	private void navigateToMenu(String username) {
        // 관리자 여부 확인
        if (isAdmin(username)) {
        	 LoginProgram adminMenu = new LoginProgram(memberRegister); // 관리자 메뉴 객체 생성
            adminMenu.displayMenu(); // 관리자 메뉴 표시
        } else {
            Main main1 = new Main(memberRegister); // 일반 사용자 메뉴 객체 생성
            Main.userDisplayMenu(); // 일반 사용자 메뉴 표시
        }
    }

    // 사용자가 관리자인지 확인하는 메서드
    private boolean isAdmin(String username) {
        return username != null && username.toLowerCase().contains("admin"); // username이 null이 아니고 'admin'이 포함되어 있는지 확인
    }
}
