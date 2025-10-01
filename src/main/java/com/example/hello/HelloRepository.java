package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Reposotoryアノテーションを付与することでデータベースからデータを取得するためのAPIになる
// RepositoryはデータベースのCURD操作を実現できる
@Repository
public class HelloRepository {

	// Autowiredは依存性注入をするときに使用されるアノテーション
	// フィールドインジェクションとコンストラクタインジェクションで使用される
	// コンストラクタインジェクションの方がコードを簡素化できる
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 従業員情報をid検索するメソッド
	 * @param id 従業員ID
	 * @return Map型の従業員情報
	 */
	public Map<String, Object> findById(String id) {
		
		// SELECT文
		// String型の変数queryにSQL文を文字列として代入
		String query = "SELECT * FROM employee WHERE id = ?";
		
		// 検索実行
		// ?にidが代入される
		// JdbcTemplateクラスのqueryForMapメソッドで第一引数にはSQL文の文字列、第二引数には条件のIDを渡すことで、特定の従業員情報を取得できる
		Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);
		
		// 取得した従業員情報を呼び出し元に返す
		return employee;
	}
}
