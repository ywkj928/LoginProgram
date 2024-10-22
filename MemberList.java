package com.login.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MemberList {
	private final static String DB = "memberList.txt"; // 회원 목록이 저장된 파일 경로
    private static Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체

    // 회원 정보를 출력하는 메서드
    public static void printMembersInfo() {
        System.out.print("이름을 입력하세요: "); // 이름 입력 요청
        String matchingName = scanner.nextLine(); // 사용자로부터 이름 입력 받기
        
        // 회원 정보 파일을 읽기 위한 BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            boolean userMatch = false; // 일치하는 회원 여부

            // 파일의 모든 라인 읽기
            while ((line = reader.readLine()) != null) {
                // 각 회원 정보를 로드하여 필요한 값만 출력
                String[] data = line.split(", "); // 각 회원 정보를 ", "로 분리
                
                // 원하는 정보만 추출 (예: 회원 번호, 이름, 전화번호, 주소)
                String num = data[0].split(": ")[1]; // 회원 번호
                String name = data[1].split(": ")[1]; // 이름 
                String phone = data[2].split(": ")[1]; // 전화번호
                String address = data[3].split(": ")[1]; // 주소

                // 입력한 이름과 일치하는지 확인
                if (name.equals(matchingName)) {
                    // 일치하는 회원 정보 출력
                    System.out.println("회원번호: " + num + "     이름: " + name + "     연락처: " + phone + "    주소: " + address);
                    userMatch = true; // 일치하는 회원이 발견됨
                }
            }

            // 일치하는 회원이 없는 경우 메시지 출력
            if (!userMatch) {
                System.out.println("회원 정보를 찾을 수 없습니다."); // 회원 정보 없음 메시지
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage()); // 파일 읽기 오류 처리
        }
    }
}
