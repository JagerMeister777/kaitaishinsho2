package com.example.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;

@Controller
// ＠RequestMappingはクラスに付与することで、URLの接頭辞として利用できる
@RequestMapping("/user")
public class SignupController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	@GetMapping("/signup")
	public String getSignup(Model model) {
		// 性別を取得
		Map<String, Integer> genderMap = userApplicationService.getGenderMap();
		// htmlでgenderMapを利用するためにgenderMapというキーでmodelに追加
		model.addAttribute("genderMap", genderMap);
		
		// ユーザー登録画面に遷移
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup() {
		// ログイン画面にリダイレクト
		// リダイレクトをすることで、謝って再度データをリクエストしないようにする（PRGパターン）
		// 特に登録や更新、削除の際に利用することでユーザーの誤った操作を行わないようにする
		return "redirect:/login";
	}
	
}
