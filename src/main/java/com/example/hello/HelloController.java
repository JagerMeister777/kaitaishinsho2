package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controllerアノテーションはクライアントからのリクエストに対して、どんなデータを返すかを管理している
// Modelとの橋渡し
@Controller
public class HelloController {

	// Autowiredは依存性注入(DI)をするときに使用されるアノテーション
	@Autowired
	private HelloService service;
	
	// クライアントが"/hello"でGETリクエストを送信してきた際に、実行されるメソッド
	@GetMapping("/hello")
	public String getHello() {
		// hello.html に遷移
		return "hello";
	}
	
	// クライアントが"/hello/db"でPOSTリクエストを送信してきた際に、実行されるメソッド
	@PostMapping("/hello/db")
	public String postRequest(@RequestParam("text2") String id, Model model) {
		// 1件検索　HelloServiceクラスのgetEmployeeメソッドを利用して特定の従業員情報を取得
		Employee employee = service.getEmployee(id);
		
		// ModelクラスのaddAttributeメソッドを利用して、検索結果をemployeeというキーでemployeeを追加
		model.addAttribute("employee", employee);
		
		// db.htmlに画面遷移
		return "hello/db";
	}
	
}
