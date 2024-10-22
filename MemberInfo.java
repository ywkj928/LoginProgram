package com.login.domain;

import com.login.register.MemberRegister;

public class MemberInfo {
    private String num; // 회원 번호
    private String name; // 회원 이름
    private String phone; // 회원 전화번호
    private String address; // 회원 주소
    private String id; // 회원 ID
    private String password; // 회원 비밀번호

    // 생성자: 회원 정보를 초기화
    public MemberInfo(String num, String name, String phone, String address, String id, String password) {
        this.num = num; // 회원 번호 설정
        this.name = name; // 회원 이름 설정
        this.phone = phone; // 회원 전화번호 설정
        this.address = address; // 회원 주소 설정
        this.id = id; // 회원 ID 설정
        this.password = password; // 회원 비밀번호 설정
    }

    // ID 반환
	public String getId() {
        return id; // 회원 ID 반환
    }

    // ID 설정
    public void setId(String id) {
        this.id = id; // 회원 ID 설정
    }

    // 비밀번호 반환
    public String getPassword() {
        return password; // 회원 비밀번호 반환
    }

    // 비밀번호 설정
    public void setPassword(String password) {
        this.password = password; // 회원 비밀번호 설정
    }

    // 회원 번호 반환
    public String getNum() {
        return num; // 회원 번호 반환
    }

    // 회원 번호 설정
    public void setNum(String num) {
        this.num = num; // 회원 번호 설정
    }

    // 이름 반환
    public String getName() {
        return name; // 회원 이름 반환
    }

    // 이름 설정
    public void setName(String name) {
        this.name = name; // 회원 이름 설정
    }

    // 전화번호 반환
    public String getPhone() {
        return phone; // 회원 전화번호 반환
    }

    // 전화번호 설정
    public void setPhone(String phone) {
        this.phone = phone; // 회원 전화번호 설정
    }

    // 주소 반환
    public String getAddress() {
        return address; // 회원 주소 반환
    }

    // 주소 설정
    public void setAddress(String address) {
        this.address = address; // 회원 주소 설정
    }

    // 메뉴 표시 메서드 (현재는 구현되지 않음)
	public void displayMenu() {
		// TODO Auto-generated method stub
	}
}
