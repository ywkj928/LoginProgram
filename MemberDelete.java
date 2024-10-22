package com.login.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberDelete {
    private final static String DB = "memberList.txt"; // 회원 목록이 저장된 파일 경로
    private static Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체
    private static List<String> membersList = new ArrayList<>(); // 회원 정보를 저장할 리스트

    // 회원 삭제 및 정보 검색 메서드
    public static void delSearchMembersInfo() {
        System.out.print("회원의 이름을 입력하세요: "); // 삭제할 회원의 이름 입력 요청
        String delSearchingName = scanner.nextLine(); // 사용자 입력 받기

        boolean match = false; // 이름 일치 여부
        boolean deleted = false; // 삭제 여부

        // 회원 정보 파일을 읽기 위한 BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            // 파일의 모든 라인 읽기
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", "); // 각 회원 정보를 쉼표로 분리

                if (data.length == 6) { // 회원 정보가 6개 필드인지 확인
                    String name = data[1].split(": ")[1]; // 회원 이름 추출
                    String password = data[5].split(": ")[1]; // 회원 비밀번호 추출

                    // 입력한 이름과 일치하는 경우
                    if (name.equals(delSearchingName)) {
                        match = true; // 이름 일치
                        System.out.print("비밀번호를 입력하세요: "); // 비밀번호 입력 요청
                        String inputPassword = scanner.nextLine(); // 사용자 입력 받기

                        // 비밀번호가 일치하는 경우
                        if (inputPassword.equals(password)) {
                            System.out.println("탈퇴되었습니다."); // 탈퇴 성공 메시지
                            deleted = true; // 삭제 상태 업데이트
                            // 이 회원 정보는 membersList에 추가하지 않음 (삭제)
                        } else {
                            System.out.println("비밀번호가 일치하지 않습니다."); // 비밀번호 불일치 메시지
                            membersList.add(line); // 비밀번호가 틀리면 그대로 유지
                        }
                    } else {
                        membersList.add(line); // 다른 회원 정보는 그대로 유지
                    }
                }
            }

            // 회원을 찾지 못한 경우
            if (!match) {
                System.out.println("해당 회원을 찾을 수 없습니다."); // 회원 없음 메시지
            } else if (deleted) {
                saveUpdatedMembers(); // 파일에 업데이트된 회원 목록 저장
                System.out.println("로그인 프로그램을 종료합니다."); // 종료 메시지
                System.exit(0); // 프로그램 종료
            }

        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage()); // 파일 읽기 오류 처리
        }
    }

    // 업데이트된 회원 정보를 파일에 저장하는 메서드
    private static void saveUpdatedMembers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) {
            for (String member : membersList) {
                writer.write(member); // 각 회원 정보를 파일에 작성
                writer.newLine(); // 다음 라인으로 이동
            }
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage()); // 파일 저장 오류 처리
        }
    }

    // 프로그램 실행 진입점
    public static void main(String[] args) {
        delSearchMembersInfo(); // 회원 삭제 및 정보 검색 메서드 호출
    }
}
