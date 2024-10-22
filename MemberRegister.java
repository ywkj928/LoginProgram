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

	private List<MemberInfo> memberList; // 회원 정보를 저장할 리스트
    private final String DB = "memberList.txt"; // 회원 정보를 저장하는 파일 경로

    // 생성자: 회원 정보를 로드하고 리스트 초기화
    public MemberRegister() {
    	memberList = new ArrayList<>(); // 회원 리스트 초기화
        loadMembersInfo(); // 파일에서 회원 정보 로드
    }

    // 회원 추가 메서드
    public void memberAdd() {
        String num, name, phone, address, id, password; // 회원 정보 변수 선언
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체

        // 사용자로부터 회원 정보 입력 받기
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
                System.out.println("이미 존재하는 ID입니다. 다른 ID를 입력하세요."); // 중복 ID 메시지
                return; // 메서드 종료
            }
        }
        
        System.out.print("Password: "); // 비밀번호 입력 요청
        password = scanner.nextLine();
        
        // 새 회원 추가
        memberList.add(new MemberInfo(num, name, phone, address, id, password)); // 회원 정보 추가
        System.out.println("가입 완료."); // 가입 완료 메시지
        
        // 회원 정보를 파일에 저장   
        saveMembersInfo(); // 파일에 회원 정보 저장
    }

    // 회원 정보 리스트 반환 메서드
    public List<MemberInfo> getMembers() {
        return memberList; // 회원 정보 리스트 반환
    }

    // 회원 정보를 파일에 저장하는 메서드
    public void saveMembersInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) {
            // 리스트의 모든 회원 정보를 파일에 기록
            for (MemberInfo member : memberList) {
                writer.write("회원 번호: " + member.getNum() + ", " +
                             "이름: " + member.getName() + ", " +
                             "전화번호: " + member.getPhone() + ", " +
                             "주소: " + member.getAddress() + ", " +
                             "ID: " + member.getId() + ", " +
                             "비밀번호: " + member.getPassword());
                writer.newLine(); // 다음 라인으로 이동
            }
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage()); // 파일 저장 오류 처리
        }
    }

    // 회원 정보를 파일에서 로드하는 메서드
    private void loadMembersInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            // 파일의 모든 라인 읽기
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", "); // 각 회원 정보를 ", "로 분리
                if (data.length == 6) { // 필요한 데이터 수 확인
                    // 각 필드에서 정보 추출
                    String num = data[0].split(": ")[1]; // "회원 번호: "
                    String name = data[1].split(": ")[1]; // "이름: "
                    String phone = data[2].split(": ")[1]; // "전화번호: "
                    String address = data[3].split(": ")[1]; // "주소: "
                    String id = data[4].split(": ")[1]; // "ID: "
                    String password = data[5].split(": ")[1]; // "비밀번호: "
                    
                    // 회원 정보를 리스트에 추가
                    memberList.add(new MemberInfo(num, name, phone, address, id, password));
                }
            }
        } catch (IOException e) {
            System.out.println("파일 로드 오류: " + e.getMessage()); // 파일 로드 오류 처리
        }
    }

    // ID로 회원 정보를 찾는 메서드 (현재는 구현되지 않음)
	public MemberInfo findMemberById(String loggedInUserId) {
		// TODO: ID로 회원 정보를 찾는 로직 추가
		return null; // 현재는 null 반환
	}
}
