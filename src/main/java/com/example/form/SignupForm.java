package com.example.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// ゲッター、セッター、コンストラクタ、equals、toString、hashCode、メソッドが自動生成される
@Data
public class SignupForm {
	
	// NotBlank Nullや空白、空文字のみだとNG
	// NotNull NullだとNG
	// Email 文字列がメールアドレス形式かどうかチェックする
	// Length 文字列の長さが指定した範囲内かどうかチェックする
	// Perttren 指定した正規表現に一致するかチェックする
	// Min Max 最大値最小値を指定し、範囲内かどうかチェックする
	
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String userId;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 4, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;
	
	@NotBlank(groups = ValidGroup1.class)
	private String userName;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull(groups = ValidGroup1.class)
	private Date birthday;
	
	@Min(value = 20, groups = ValidGroup2.class)
	@Max(value = 100, groups = ValidGroup2.class)
	private Integer age;
	
	@NotNull(groups = ValidGroup1.class)
	private Integer gender;
}
