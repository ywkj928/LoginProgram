package com.login.domain;

import com.login.register.MemberRegister;

public class MemberInfo {
    private String num;
    private String name;
    private String phone;
    private String address;
    private String id;
    private String password;

    // 생성자
    public MemberInfo(String num, String name, String phone, String address, String id, String password) {
        this.num = num;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.id = id;
        this.password = password;
    }

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public void displayMenu() {
		// TODO Auto-generated method stub
		
	}
}


