package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	/** ログイン画面を表示 **/
	@GetMapping("/login")
	public String getLogin() {
		// templatesフォルダのログインフォルダのlogin.htmlを返す
		return "login/login";
	}
	
	/** ログイン画面にリダイレクト **/
	@PostMapping("/login")
	public String postLogin() {
		return "redirect:/user/list";
	}
}
