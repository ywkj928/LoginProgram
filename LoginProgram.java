package com.login.controller;

import java.util.Scanner;
import com.login.domain.MemberInfo;
import com.login.register.MemberRegister;

public class LoginProgram {

	private static Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체
    private MemberRegister memberRegister; // 회원 등록 정보를 관리하는 MemberRegister 객체
    private String loggedInUserId; // 로그인한 사용자 ID 저장

    // 생성자: MemberRegister 객체를 초기화
    public LoginProgram(MemberRegister memberRegister) {
        this.memberRegister = memberRegister; // MemberRegister 저장
    }

    // 로그인 처리 메서드
    public String login() {
        System.out.println("*************************************************");
        System.out.println("                     로그인");
        System.out.println("*************************************************");

        // 사용자로부터 아이디와 비밀번호 입력 받기
        System.out.print("아이디를 입력하세요: ");
        String id = scanner.nextLine(); // 아이디 입력
        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine(); // 비밀번호 입력

        // MemberInfo 목록을 가져와서 로그인 시도
        for (MemberInfo memberInfo : memberRegister.getMembers()) {
            // ID가 일치하는지 확인
            if (memberInfo.getId().equals(id)) {
                // 비밀번호가 일치하는지 확인
                if (memberInfo.getPassword().equals(password)) {
                    loggedInUserId = id; // 로그인한 사용자 ID 저장
                    System.out.println(memberInfo.getName() + "님 로그인 성공"); // 로그인 성공 메시지 출력
                    
                    return "SUCCESS"; // 로그인 성공
                } else {
                    System.out.println("비밀번호가 틀렸습니다."); // 비밀번호 불일치 메시지 출력
                    return "PASSWORD_INCORRECT"; // 비밀번호 불일치 반환
                }
            }
        }

        // ID가 존재하지 않을 때
        System.out.println("일치하는 회원이 없습니다."); // 회원 없음 메시지 출력
        return "ID_NOT_FOUND"; // ID 미발견 반환
    }

    // 메뉴를 표시하는 메서드 (현재는 구현되지 않음)
	public void displayMenu() {
		// TODO Auto-generated method stub
	}

    // 로그인한 사용자 이름을 반환하는 메서드
	public String getName() {
		// 로그인한 사용자 ID 반환
		return this.loggedInUserId;
	}
}
