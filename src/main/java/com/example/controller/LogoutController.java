package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LogoutController {

	/** ログイン画面にリダイレクト **/
	@PostMapping("/logout")
	public String postLogout() {
		// Slf4jクラスのinfoメソッドでコンソール画面に簡単に出力できる
		log.info("ログアウト");
		return "redirect:/login";
	}
}
