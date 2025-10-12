package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.user.model.MUser;

@Mapper
// ここにメソッドを定義して、UserMapper.xmlでキーとしてメソッド名で指定することで、メソッドが呼ばれたら、指定されているxmlのSQL文が実行される
public interface UserMapper {

	/** ユーザー登録 */
	public int insertOne(MUser user);
	
	/** ユーザー取得 */
	public List<MUser> findMany();
}
