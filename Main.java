package com.login.main;

import java.util.Scanner;
import com.login.controller.MemberDelete;
import com.login.controller.MemberFix;
import com.login.controller.MemberList;
import com.login.register.MemberRegister;

public class Main {

	private MemberList MemderList;
    private MemberFix  Memberfix;
    private MemberDelete MemderDelete;
    private static Scanner scanner;

    // Initialization
    public Main(MemberRegister MemberRegister) {
        this.MemderList = new MemberList();
        this.Memberfix = new MemberFix();
        this.MemderDelete = new MemberDelete();
        this.scanner = new Scanner(System.in); // Initialize Scanner
    }

    public static void userDisplayMenu() {
    	
        while (true) {
            System.out.println("*************************************************");
            System.out.println("                 회원 정보 프로그램");
            System.out.println("*************************************************");
            System.out.println("1. 회원 정보 확인하기     " + "     2. 회원 정보 수정하기");
            System.out.println("3. 회원 탈퇴하기     " + "          4. 종료");
            System.out.println("*************************************************");
            System.out.print("메뉴 번호를 선택해주세요: ");

            int menu = scanner.nextInt();
            scanner.nextLine(); 
            switch (menu) {
                case 1:
                	MemberList.printMembersInfo(); 
                    break;
                case 2:
                	MemberFix.fixSearchMembersInfo(); 
                    break;
                    
                case 3:
                    MemberDelete.delSearchMembersInfo(); 
                    break;
                case 4:
                    System.out.println("종료합니다.");
                    System.exit(0); 
                default:
                    System.out.println("1 ~ 4 사이의 숫자를 입력 해주세요.");
            }
        }
    }
}
