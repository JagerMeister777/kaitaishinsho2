package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// ゲッター、セッター、コンストラクタ、equals、toString、hashCode、メソッドが自動生成される
@Data
public class SignupForm {
	private String userId;
	private String password;
	private String userName;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	
	private Integer age;
	private Integer gender;
}
