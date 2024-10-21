package com.login.register;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.login.domain.MemberInfo;

public class MemberRegister {

	private List<MemberInfo> memberList; 
    private final String DB = "memberList.txt"; // 파일 경로

    public MemberRegister() {
    	memberList = new ArrayList<>(); 
        loadMembersInfo(); // 파일에서 회원 정보 로드
    }

    public void memberAdd() {
        String num, name, phone, address, id, password;
        Scanner scanner = new Scanner(System.in);

        System.out.print("회원 번호: "); 
        num = scanner.nextLine();
        System.out.print("이름: "); 
        name = scanner.nextLine();
        System.out.print("전화번호: "); 
        phone = scanner.nextLine();
        System.out.print("주소: "); 
        address = scanner.nextLine();
        System.out.print("ID: "); 
        id = scanner.nextLine();
        
        // ID 중복 체크
        for (MemberInfo member : memberList) {
            if (member.getId().equals(id)) {
                System.out.println("이미 존재하는 ID입니다. 다른 ID를 입력하세요.");
                return; // 메서드 종료
            }
        }
        
        System.out.print("Password: "); 
        password = scanner.nextLine();
        
        // 새 회원 추가
        memberList.add(new MemberInfo(num, name, phone, address, id, password));
        System.out.println("가입 완료.");
        
        // 회원 정보를 파일에 저장   
        saveMembersInfo(); 
    }

    public List<MemberInfo> getMembers() {
        return memberList; // 회원 정보 리스트 반환
    }

    public void saveMembersInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) {
            for (MemberInfo member : memberList) {
                writer.write("회원 번호: " + member.getNum() + ", " +
                             "이름: " + member.getName() + ", " +
                             "전화번호: " + member.getPhone() + ", " +
                             "주소: " + member.getAddress() + ", " +
                             "ID: " + member.getId() + ", " +
                             "비밀번호: " + member.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }

    private void loadMembersInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", "); // ", "로 분리
                if (data.length == 6) { // 필요한 데이터 수 확인
                    String num = data[0].split(": ")[1]; // "회원 번호: "
                    String name = data[1].split(": ")[1]; // "이름: "
                    String phone = data[2].split(": ")[1]; // "전화번호: "
                    String address = data[3].split(": ")[1]; // "주소: "
                    String id = data[4].split(": ")[1]; // "ID: "
                    String password = data[5].split(": ")[1]; // "비밀번호: "
                    
                    memberList.add(new MemberInfo(num, name, phone, address, id, password));
                
                }
            }
        } catch (IOException e) {
            System.out.println("파일 로드 오류: " + e.getMessage());
        }
    }

	public MemberInfo findMemberById(String loggedInUserId) {
		// TODO Auto-generated method stub
		return null;
	}
}

