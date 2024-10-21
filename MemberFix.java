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
    private final static String DB = "memberList.txt"; // 파일 경로
    private static Scanner scanner = new Scanner(System.in);
    private static List<MemberInfo> members = new ArrayList<>();

    public static void fixSearchMembersInfo() {
        System.out.print("수정할 회원의 ID를 입력하세요: ");
        String fixSearchingId = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            boolean match = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");

                // 데이터 수 확인
                if (data.length == 6) {
                    String num = data[0].split(": ")[1]; // 회원 번호
                    String name = data[1].split(": ")[1]; // 이름
                    String phone = data[2].split(": ")[1]; // 전화번호
                    String address = data[3].split(": ")[1]; // 주소
                    String id = data[4].split(": ")[1]; // ID
                    String password = data[5].split(": ")[1]; // 비밀번호

                    if (id.equals(fixSearchingId)) {
                        match = true;

                        // 이름 수정 요청
                        String newName;
                        while (true) {
                            System.out.print(name + " 회원의 이름을 수정하세요: ");
                            newName = scanner.nextLine();
                            
                            // 중복 이름 체크
                            if (isNameExists(newName, fixSearchingId)) {
                                System.out.println("이미 존재하는 이름입니다. 다른 이름으로 수정해주세요.");
                            } else if (newName.isEmpty()) {
                                newName = name; // 기존 이름 유지
                                break;
                            } else {
                                break;
                            }
                        }

                        System.out.print(name + " 회원의 전화번호를 수정하세요: ");
                        String newPhone = scanner.nextLine();
                        if (newPhone.isEmpty()) {
                            newPhone = phone; // 기존 전화번호 유지
                        }

                        System.out.print(name + " 회원의 주소를 수정하세요: ");
                        String newAddress = scanner.nextLine();
                        if (newAddress.isEmpty()) {
                            newAddress = address; // 기존 주소 유지
                        }

                        System.out.print(name + " 회원의 비밀번호를 수정하세요: ");
                        String newPassword = scanner.nextLine();
                        if (newPassword.isEmpty()) {
                            newPassword = password; // 기존 비밀번호 유지
                        }

                        // 수정된 정보를 리스트에 저장
                        members.add(new MemberInfo(num, newName, newPhone, newAddress, id, newPassword)); 
                    } else {
                        // 일치하지 않는 경우 원본 데이터를 그대로 추가
                        members.add(new MemberInfo(num, name, phone, address, id, password));
                    }
                }
            }

            if (match) {
                // 수정된 내용을 파일에 저장
                saveMembersInfo();
                System.out.println("수정 완료되었습니다.");
            } else {
                System.out.println("회원 정보를 찾을 수 없습니다.");
            }

        } catch (IOException e) {
            System.out.println("파일 처리 오류: " + e.getMessage());
        }
    }

    private static boolean isNameExists(String name, String currentId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
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
            System.out.println("파일 처리 오류: " + e.getMessage());
        }
        return false; // 중복 이름 없음
    }

    public static void saveMembersInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) { // 덮어쓰기 모드
            for (MemberInfo member : members) {
                writer.write("회원 번호: " + member.getNum() + ", " +
                             "이름: " + member.getName() + ", " +  // 이름은 수정된 이름이 아닌 기존 이름 유지
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
}

