package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/user")
public class UserDetailController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/** ユーザー詳細画面を表示 */
	@GetMapping("/detail/{userId:.+}")
	public String getUser(UserDetailForm form, Model model, @PathVariable("userId") String userId) {
		
		// ユーザーを1件取得
		MUser user = userService.getUserOne(userId);
		
		// ユーザーのパスワードをnullに設定
		user.setPassword(null);
		
		// MUserをformに変換
		//ModelMapperクラスのmapメソッドはフィールド名が同じフィールドに値を自動的にコピーすることができる
		form = modelMapper.map(user, UserDetailForm.class);
		
		// htmlでuserDetailFormを利用するために、ModelクラスのaddAttributeメソッドを使用して、userDetailFormというキーでmodelに追加
		model.addAttribute("userDetailForm", form);
		
		// user/detail.htmlに遷移
		return "user/detail";
	}
	
	/** ユーザー更新処理 */
	@PostMapping(value = "/detail", params = "update")
	public String updateUser(UserDetailForm form, Model model) {
		
		// ユーザーを更新
		userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
		
		// ユーザー一覧画面にリダイレクト
		return "redirect:/user/list";
	}
	
	@PostMapping(value = "/detail", params = "delete")
	public String deleteUser(UserDetailForm form, Model model) {
		
		// ユーザーを削除
		userService.deleteUserOne(form.getUserId());
		
		// ユーザー一覧画面にリダイレクト
		return "redirect:/user/list";
	}
}
