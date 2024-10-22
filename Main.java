package com.login.main;

import java.util.Scanner;
import com.login.controller.MemberDelete;
import com.login.controller.MemberFix;
import com.login.controller.MemberList;
import com.login.register.MemberRegister;

public class Main {

	private MemberList MemderList; // 회원 목록 관리 클래스 인스턴스
    private MemberFix Memberfix; // 회원 정보 수정 클래스 인스턴스
    private MemberDelete MemderDelete; // 회원 삭제 클래스 인스턴스
    private static Scanner scanner; // 사용자 입력을 위한 Scanner 객체

    // 생성자: MemberRegister 객체를 받아서 초기화
    public Main(MemberRegister MemberRegister) {
        this.MemderList = new MemberList(); // 회원 목록 객체 초기화
        this.Memberfix = new MemberFix(); // 회원 수정 객체 초기화
        this.MemderDelete = new MemberDelete(); // 회원 삭제 객체 초기화
        this.scanner = new Scanner(System.in); // Scanner 초기화
    }

    // 사용자 메뉴를 표시하는 메서드
    public static void userDisplayMenu() {
    	
        while (true) { // 무한 루프 시작
            System.out.println("*************************************************");
            System.out.println("                 님 안녕하세요?"); // 사용자 환영 메시지
            System.out.println("*************************************************");
            System.out.println("1. 회원 정보 확인하기     " + "     2. 회원 정보 수정하기"); // 메뉴 항목
            System.out.println("3. 회원 탈퇴하기     " + "         4. 종료"); // 메뉴 항목
            System.out.println("*************************************************");
            System.out.print("메뉴 번호를 선택해주세요: "); // 메뉴 선택 요청

            int menu = scanner.nextInt(); // 사용자로부터 메뉴 번호 입력 받기
            scanner.nextLine(); // 입력 버퍼 비우기
            switch (menu) { // 선택된 메뉴에 따라 분기
                case 1:
                	MemberList.printMembersInfo(); // 회원 정보 확인 메서드 호출
                    break;
                case 2:
                	MemberFix.fixSearchMembersInfo(); // 회원 정보 수정 메서드 호출
                    break;
                case 3:
                    MemberDelete.delSearchMembersInfo(); // 회원 탈퇴 메서드 호출
                    break;
                case 4:
                    System.out.println("종료합니다."); // 종료 메시지 출력
                    System.exit(0); // 프로그램 종료
                default:
                    System.out.println("1 ~ 4 사이의 숫자를 입력 해주세요."); // 잘못된 입력 처리
            }
        }
    }
}
