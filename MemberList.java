package com.login.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MemberList {
	private final static String DB = "memberList.txt"; // 파일 경로
    private static Scanner scanner = new Scanner(System.in);

    public static void printMembersInfo() {
        System.out.print("이름을 입력하세요: ");
        String matchingName = scanner.nextLine();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            boolean userMatch = false;

            while ((line = reader.readLine()) != null) {
                // 각 회원 정보를 로드하여 필요한 값만 출력
                String[] data = line.split(", "); // ", "로 분리
                
                // 원하는 정보만 추출 (예: 이름과 전화번호)
                String num = data[0].split(": ")[1]; // 회원 번호
                String name = data[1].split(": ")[1]; // 이름 
                String phone = data[2].split(": ")[1]; // 전화번호
                String address = data[3].split(": ")[1]; // 주소

                // 입력한 이름과 일치하는지 확인
                if (name.equals(matchingName)) {
                    System.out.println("회원번호: " + num + "     이름: " + name + "     연락처: " + phone +"    주소: " + address);
                    userMatch = true; // 일치하는 회원이 발견됨
                }
            }

            if (!userMatch) {
                System.out.println("회원 정보를 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }
}

