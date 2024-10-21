package com.login.controller;

import com.login.domain.MemberInfo;
import com.login.main.Main1;
import com.login.register.Choice;
import com.login.register.MemberRegister;

public class LoginHandle {
	private MemberRegister memberRegister;

    public LoginHandle(MemberRegister memberRegister) {
        this.memberRegister = memberRegister;
    }

    public boolean handleLogin(Choice choice) {
        int attemptCount = 0;
        final int maxAttempts = 3;

        while (attemptCount < maxAttempts) {
        	LoginProgram loginProgram = new LoginProgram(memberRegister);
            String loginResult = loginProgram.login();

            if (loginResult.equals("SUCCESS")) {
                navigateToMenu(loginProgram.getName());
                return true; // 로그인 성공 시 true 반환
            } else if (loginResult.equals("ID_NOT_FOUND")) {
                // ID가 존재하지 않는 경우
                choice.showMenu(); // 메인 메뉴로 돌아감
                return false; // false 반환
            } else {
                attemptCount++;
                if (loginResult.equals("PASSWORD_INCORRECT")) {
                }

                if (attemptCount >= maxAttempts) {
                    System.out.println("로그인횟수 초과.");
                    System.exit(0);
                }
            }
        }

        return false; // 실패 시 false 반환
    }


	private void navigateToMenu(String username) {
        if (isAdmin(username)) {
        	 LoginProgram adminMenu = new  LoginProgram(memberRegister);
            adminMenu.displayMenu();	
        } else {
            Main1 main1 = new Main1(memberRegister);
            Main1.userDisplayMenu();
        }
    }

    private boolean isAdmin(String username) {
        return username != null && username.toLowerCase().contains("admin");
    }
}
