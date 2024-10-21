package com.login.register;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.login.controller.LoginHandle;
import com.login.main.Display;

public class Choice {

    private MemberRegister memberRegister;
    private Scanner scanner; 

    // Constructor
    public Choice(MemberRegister memberRegister) {
        this.memberRegister = memberRegister;
        this.scanner = new Scanner(System.in); 
    }

    private void printLoginFailureMessage() {
    }

    public void showLogin() {
        // 로그인 시도
        while (true) {
            LoginHandle loginHandle = new LoginHandle(memberRegister);
            if (loginHandle.handleLogin(this)) {
                break; // 로그인 성공하면 반복문 종료
            } else {
                printLoginFailureMessage(); // 로그인 실패 시 메시지 출력
            }
        }
    }

    public void showMenu() {
        while (true) {
            System.out.println("------- 메뉴 선택 -------");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 프로그램 종료");

            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = scanner.nextInt();
                    validInput = true; // 유효한 입력을 받음
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
                    scanner.nextLine(); // 잘못된 입력 초기화
                }
            }

            scanner.nextLine(); // 줄바꿈 문자 소비

            switch (choice) {
                case 1:
                    memberRegister.memberAdd(); // 회원가입
                    System.out.println("회원가입이 완료되었습니다.");
                    break;
                case 2:
                	showLogin();
                	break;
                case 3:
                    System.out.println("프로그램 종료");
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }
}
