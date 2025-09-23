package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ServiceアノテーションはModel層に属し、ロジックを記述する。
@Service
public class HelloService {

	// Autowiredは依存性注入(DI)をするときに使用されるアノテーション
	@Autowired
	private HelloRepository repository;
	
	/** 既に登録されている従業員(Employee)情報を取得するためのメソッド
	 * 
	 * @param id 従業員ID
	 * @return Employeeクラス形式の従業員情報
	 */
	public Employee getEmployee(String id) {
		
		// HelloRepositoryに定義されているfindByIdメソッドで特定のIDの従業員情報を検索します。
		// 検索した情報はMap型で取得できるので、変数mapに代入
		Map<String, Object> map = repository.findById(id);
		
		// String型の変数employeeIdに取得したMap型の従業員情報からidを取得し、String型にキャストし代入
		String employeeId = (String)map.get("id");
		// String型の変数nameに取得したMap型の従業員情報からnameを取得し、String型にキャストし代入
		String name = (String)map.get("name");
		// int型の変数ageに取得したMap型の従業員情報からageを取得し、Integer型にキャストし代入
		int age = (Integer)map.get("age");
		
		// Employee型の変数employeeにインスタンスを生成
		Employee employee = new Employee();
		// 変数employeeのemployeeIdフィールドに取得したemployeeIdを代入
		employee.setEmployeeId(employeeId);
		// 変数employeeのemployeeNameフィールドに取得したnameを代入
		employee.setEmployeeName(name);
		// 変数employeeのemployeeAgeフィールドに取得したageを代入
		employee.setEmployeeAge(age);
		
		// 値がsetされたemployeeを呼び出し元へ返す
		return employee;
	}
}
