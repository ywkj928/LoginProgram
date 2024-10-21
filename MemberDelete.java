package com.login.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberDelete {
    private final static String DB = "memberList.txt"; // 파일 경로
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> membersList = new ArrayList<>();

    public static void delSearchMembersInfo() {
        System.out.print("회원의 이름을 입력하세요: ");
        String delSearchingName = scanner.nextLine();

        boolean match = false;
        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");

                if (data.length == 6) {
                    String name = data[1].split(": ")[1]; // 이름
                    String password = data[5].split(": ")[1]; // 비밀번호

                    if (name.equals(delSearchingName)) {
                        match = true;
                        System.out.print("비밀번호를 입력하세요: ");
                        String inputPassword = scanner.nextLine();

                        if (inputPassword.equals(password)) {
                            System.out.println("탈퇴되었습니다.");
                            deleted = true;
                            // 이 회원 정보는 membersList에 추가하지 않음 (삭제)
                        } else {
                            System.out.println("비밀번호가 일치하지 않습니다.");
                            membersList.add(line); // 비밀번호가 틀리면 그대로 유지
                        }
                    } else {
                        membersList.add(line); // 다른 회원 정보는 그대로 유지
                    }
                }
            }

            if (!match) {
                System.out.println("해당 회원을 찾을 수 없습니다.");
            } else if (deleted) {
                saveUpdatedMembers(); // 파일에 업데이트된 회원 목록 저장
                System.out.println("로그인 프로그램을 종료합니다.");
                System.exit(0);
            }

        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }

    private static void saveUpdatedMembers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) {
            for (String member : membersList) {
                writer.write(member);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        delSearchMembersInfo();
    }
}

