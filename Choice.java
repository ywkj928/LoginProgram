package com.login.register;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.login.controller.LoginHandle;

public class Choice {

    private MemberRegister memberRegister; // 회원 등록을 관리하는 객체
    private Scanner scanner; // 사용자 입력을 위한 Scanner 객체

    // 생성자: MemberRegister 객체를 받아서 초기화
    public Choice(MemberRegister memberRegister) {
        this.memberRegister = memberRegister; // 회원 등록 객체 저장
        this.scanner = new Scanner(System.in); // Scanner 초기화
    }

    // 로그인 실패 메시지를 출력하는 메서드 (현재는 구현되지 않음)
    private void printLoginFailureMessage() {
        // TODO: 로그인 실패 시 사용자에게 메시지를 출력하는 로직 추가
    }

    // 로그인 화면을 표시하고 로그인 시도
    public void showLogin() {
        // 로그인 시도
        while (true) {
            LoginHandle loginHandle = new LoginHandle(memberRegister); // 로그인 처리 객체 생성
            if (loginHandle.handleLogin(this)) {
                break; // 로그인 성공하면 반복문 종료
            } else {
                printLoginFailureMessage(); // 로그인 실패 시 메시지 출력
            }
        }
    }

    // 메인 메뉴를 표시하는 메서드
    public void showMenu() {
        while (true) { // 무한 루프 시작
            System.out.println("------- 메뉴 선택 -------");
            System.out.println("1. 회원가입"); // 회원가입 옵션
            System.out.println("2. 로그인"); // 로그인 옵션
            System.out.println("3. 프로그램 종료"); // 종료 옵션

            int choice = 0; // 사용자 선택 변수
            boolean validInput = false; // 유효한 입력 여부

            // 유효한 입력을 받을 때까지 반복
            while (!validInput) {
                try {
                    choice = scanner.nextInt(); // 사용자 입력 받기
                    validInput = true; // 유효한 입력을 받음
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 숫자를 입력하세요."); // 잘못된 입력 메시지
                    scanner.nextLine(); // 잘못된 입력 초기화
                }
            }

            scanner.nextLine(); // 줄바꿈 문자 소비

            // 사용자 선택에 따라 분기
            switch (choice) {
                case 1:
                    memberRegister.memberAdd(); // 회원가입 메서드 호출
                    System.out.println("회원가입이 완료되었습니다."); // 회원가입 완료 메시지 출력
                    break;
                case 2:
                	showLogin(); // 로그인 화면 호출
                	break;
                case 3:
                    System.out.println("프로그램 종료"); // 종료 메시지 출력
                    System.exit(0); // 프로그램 종료
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요."); // 잘못된 선택 처리
            }
        }
    }
}
