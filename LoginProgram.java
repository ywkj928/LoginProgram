package com.login.controller;

import java.util.Scanner;
import com.login.domain.MemberInfo;
import com.login.register.MemberRegister;
public class LoginProgram {

	private static Scanner scanner = new Scanner(System.in);
    private MemberRegister memberRegister; // MemberRegister 객체 추가
    private String loggedInUserId; // 로그인한 사용자 ID 저장

    public LoginProgram(MemberRegister memberRegister) {
        this.memberRegister = memberRegister; // MemberRegister를 저장
    }

    public String login() {
        System.out.println("***************************************************");
        System.out.println("                     로그인");
        System.out.println("***************************************************");

        System.out.print("아이디를 입력하세요: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        // MemberInfo 목록을 가져와서 로그인 시도
        for (MemberInfo memberInfo : memberRegister.getMembers()) {
            // ID가 일치하는지 확인
            if (memberInfo.getId().equals(id)) {
                // 비밀번호가 일치하는지 확인
                if (memberInfo.getPassword().equals(password)) {
                    loggedInUserId = id; // 로그인한 사용자 ID 저장
                    System.out.println(memberInfo.getName() + "님 로그인 성공");
                    
                    return "SUCCESS"; // 로그인 성공
                } else {
                    System.out.println("비밀번호가 틀렸습니다.");
                    return "PASSWORD_INCORRECT"; // 비밀번호 불일치
                }
            }
        }

        // ID가 존재하지 않을 때
        System.out.println("일치하는 회원이 없습니다.");
        return "ID_NOT_FOUND"; 
    }

	public void displayMenu() {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.loggedInUserId;
	}
}

