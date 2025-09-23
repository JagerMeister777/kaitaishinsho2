package com.example.hello;

import lombok.Data;

// @Dataを記述することで、ゲッター、セッター、toString、hashCode、equalsメソッドを自動生成する
@Data
public class Employee {
	private String employeeId;
	private String employeeName;
	private int employeeAge;
}
