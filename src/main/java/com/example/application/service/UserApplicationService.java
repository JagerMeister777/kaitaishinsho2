package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

	@Autowired
	private MessageSource messageSource;
	
	/** 性別のMapを生成する */
	public Map<String, Integer> getGenderMap() {
		// キーがString型でバリューがInteger型のMapを生成
		Map<String, Integer> genderMap = new LinkedHashMap<>();
		// messagePropertiesのキーmaleのバリューを取得
		String male = messageSource.getMessage("male", null, Locale.JAPAN);
		// messagePropertiesのキーfemaleのバリューを取得
		String female = messageSource.getMessage("female", null, Locale.JAPAN);
		// 変数genderMapにキーがmale、バリューが1のデータを追加
		genderMap.put(male, 1);
		// 変数genderMapにキーがfemale、バリューが2のデータを追加
		genderMap.put(female, 2);
		// 呼び出し元にgenderMapを返す
		return genderMap;
	}
}
