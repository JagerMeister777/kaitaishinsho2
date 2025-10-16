package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	
	@Override
	public void signup(MUser user) {
		// MUserクラスに@Dataアノテーションが付与されているので、setterが自動生成されている
		user.setDepartmentId(1); //部署
		user.setRole("ROLE_GENERAL"); //ロール
		// UserMapperクラスのinsertOneメソッドでユーザー1件新規登録
		mapper.insertOne(user);
	}

	@Override
	public List<MUser> getUsers(MUser user) {
		// UserMapperクラスのfindManyメソッドでユーザー複数取得
		return mapper.findMany(user);
	}

	@Override
	public MUser getUserOne(String userId) {
		// UserMapperクラスのfindOneメソッドでユーザー1件検索
		return mapper.findOne(userId);
	}
	
	/** ユーザー更新（1件） */
	@Transactional
	// Transactionalアノテーションでトランザクションを設定でき、例外が発生したら自動的にロールバックできる。
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		mapper.updateOne(userId, password, userName);
		
		// 例外を発生させる
//		int i = 1 / 0;
	}
	
	/** ユーザー削除（1件） */
	@Override
	public void deleteUserOne(String userId) {
		int count = mapper.deleteOne(userId);
		
	}
	
	
}
