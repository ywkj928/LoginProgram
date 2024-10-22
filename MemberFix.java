package com.login.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.login.domain.MemberInfo;

public class MemberFix {
    private final static String DB = "memberList.txt"; // 회원 목록이 저장된 파일 경로
    private static Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체
    private static List<MemberInfo> members = new ArrayList<>(); // 수정된 회원 정보를 저장할 리스트

    // 회원 정보 수정 및 검색 메서드
    public static void fixSearchMembersInfo() {
        System.out.print("수정할 회원의 ID를 입력하세요: "); // 수정할 회원의 ID 입력 요청
        String fixSearchingId = scanner.nextLine(); // 사용자 입력 받기

        // 회원 정보 파일을 읽기 위한 BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            boolean match = false; // 회원 정보 일치 여부

            // 파일의 모든 라인 읽기
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", "); // 각 회원 정보를 쉼표로 분리

                // 데이터 수 확인
                if (data.length == 6) {
                    String num = data[0].split(": ")[1]; // 회원 번호
                    String name = data[1].split(": ")[1]; // 이름
                    String phone = data[2].split(": ")[1]; // 전화번호
                    String address = data[3].split(": ")[1]; // 주소
                    String id = data[4].split(": ")[1]; // ID
                    String password = data[5].split(": ")[1]; // 비밀번호

                    // 입력한 ID와 일치하는 경우
                    if (id.equals(fixSearchingId)) {
                        match = true; // 일치 여부 업데이트

                        // 이름 수정 요청
                        String newName;
                        while (true) {
                            System.out.print(name + " 회원의 이름을 수정하세요: "); // 기존 이름 표시 후 수정 요청
                            newName = scanner.nextLine(); // 사용자 입력 받기
                            
                            // 중복 이름 체크
                            if (isNameExists(newName, fixSearchingId)) {
                                System.out.println("이미 존재하는 이름입니다. 다른 이름으로 수정해주세요."); // 중복 이름 메시지
                            } else if (newName.isEmpty()) {
                                newName = name; // 기존 이름 유지
                                break; // 수정 종료
                            } else {
                                break; // 유효한 이름 입력 시 종료
                            }
                        }

                        // 전화번호 수정 요청
                        System.out.print(name + " 회원의 전화번호를 수정하세요: ");
                        String newPhone = scanner.nextLine();
                        if (newPhone.isEmpty()) {
                            newPhone = phone; // 기존 전화번호 유지
                        }

                        // 주소 수정 요청
                        System.out.print(name + " 회원의 주소를 수정하세요: ");
                        String newAddress = scanner.nextLine();
                        if (newAddress.isEmpty()) {
                            newAddress = address; // 기존 주소 유지
                        }

                        // 비밀번호 수정 요청
                        System.out.print(name + " 회원의 비밀번호를 수정하세요: ");
                        String newPassword = scanner.nextLine();
                        if (newPassword.isEmpty()) {
                            newPassword = password; // 기존 비밀번호 유지
                        }

                        // 수정된 정보를 리스트에 저장
                        members.add(new MemberInfo(num, newName, newPhone, newAddress, id, newPassword)); 
                    } else {
                        // ID가 일치하지 않는 경우 원본 데이터를 그대로 추가
                        members.add(new MemberInfo(num, name, phone, address, id, password));
                    }
                }
            }

            // 회원을 찾지 못한 경우
            if (match) {
                // 수정된 내용을 파일에 저장
                saveMembersInfo(); 
                System.out.println("수정 완료되었습니다."); // 수정 완료 메시지
            } else {
                System.out.println("회원 정보를 찾을 수 없습니다."); // 회원 정보 없음 메시지
            }

        } catch (IOException e) {
            System.out.println("파일 처리 오류: " + e.getMessage()); // 파일 처리 오류 메시지
        }
    }

    // 중복 이름 체크 메서드
    private static boolean isNameExists(String name, String currentId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            // 파일의 모든 라인 읽기
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 6) {
                    String id = data[4].split(": ")[1]; // ID
                    String existingName = data[1].split(": ")[1]; // 기존 이름

                    // 현재 수정 중인 ID와 다른 회원의 이름 비교
                    if (existingName.equals(name) && !id.equals(currentId)) {
                        return true; // 중복 이름 존재
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("파일 처리 오류: " + e.getMessage()); // 파일 처리 오류 메시지
        }
        return false; // 중복 이름 없음
    }

    // 수정된 회원 정보를 파일에 저장하는 메서드
    public static void saveMembersInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) { // 덮어쓰기 모드
            for (MemberInfo member : members) {
                // 회원 정보를 파일에 작성
                writer.write("회원 번호: " + member.getNum() + ", " +
                             "이름: " + member.getName() + ", " +  // 이름은 수정된 이름으로 저장
                             "전화번호: " + member.getPhone() + ", " +
                             "주소: " + member.getAddress() + ", " +
                             "ID: " + member.getId() + ", " +
                             "비밀번호: " + member.getPassword());
                writer.newLine(); // 다음 라인으로 이동
            }
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage()); // 파일 저장 오류 메시지
        }
    }
}
