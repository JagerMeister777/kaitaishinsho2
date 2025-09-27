package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

	/** 性別のMapを生成する */
	public Map<String, Integer> getGenderMap() {
		// キーがString型でバリューがInteger型のMapを生成
		Map<String, Integer> genderMap = new LinkedHashMap<>();
		// 変数genderMapにキーが男性、バリューが1のデータを追加
		genderMap.put("男性", 1);
		// 変数genderMapにキーが女性、バリューが2のデータを追加
		genderMap.put("女性", 2);
		// 呼び出し元にgenderMapを返す
		return genderMap;
	}
}
