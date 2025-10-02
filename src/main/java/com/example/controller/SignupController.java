package com.example.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
// ＠RequestMappingはクラスに付与することで、URLの接頭辞として利用できる
@RequestMapping("/user")
@Slf4j
public class SignupController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	// クライアントが"/user/signup"でGETリクエストを送信してきた際に、実行されるメソッド
	@GetMapping("/signup")
	// @ModelAttributeを使うことでSignupFormをテンプレートのフォームを紐付けることができ、POSTした際に、バインドされてリクエストが送られる
	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
		// UserApplicationServiceクラスのgetGenderMapメソッドを使用して、性別を取得
		// 第三引数はクライアントの利用言語をLocaleで取得し、渡す。
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		// htmlでgenderMapを利用するために、ModelクラスのaddAttributeメソッドを使用して、genderMapというキーでmodelに追加
		model.addAttribute("genderMap", genderMap);
		
		// ユーザー登録画面に遷移
		return "user/signup";
	}
	
	// クライアントが"/user/signup"でPOSTリクエストを送信してきた際に、実行されるメソッド
	@PostMapping("/signup")
	public String postSignup(Model model, Locale locale, @ModelAttribute SignupForm form, BindingResult bindingResult) {
		
		// 入力チェックの結果
		// BindingResultクラスのhasErrorsメソッドでエラーのあるフィールドがあればtrue
		if(bindingResult.hasErrors()) {
			// NG:ユーザー管理画面に戻ります
			return getSignup(model, locale, form);
		}
		
		// Slf4jインターフェースのinfoメソッドを呼び出すと簡単にコンソール画面にログを出すことができる。
		log.info(form.toString());
		
		// ログイン画面にリダイレクト
		// リダイレクトをすることで、謝って再度データをリクエストしないようにする（PRGパターン）
		// 特に登録や更新、削除の際に利用することでユーザーの誤った操作を行わないようにする
		return "redirect:/login";
	}
	
}
