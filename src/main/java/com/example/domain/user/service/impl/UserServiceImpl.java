package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void signup(MUser user) {
		// MUserクラスに@Dataアノテーションが付与されているので、setterが自動生成されている
		user.setDepartmentId(1); //部署
		user.setRole("ROLE_GENERAL"); //ロール
		
		// PasswordEncoderクラスのencoderメソッドでパスワード暗号化（ハッシュ化）
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
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
		
		// PasswordEncoderクラスのencoderメソッドでパスワード暗号化（ハッシュ化）
		String encryptPassword = encoder.encode(password);
		
		mapper.updateOne(userId, encryptPassword, userName);
		
		// 例外を発生させる
//		int i = 1 / 0;
	}
	
	/** ユーザー削除（1件） */
	@Override
	public void deleteUserOne(String userId) {
		int count = mapper.deleteOne(userId);
		
	}

	/** ログインユーザー情報取得 */
	@Override
	public MUser getLoginUser(String userId) {
		// UserMapperクラスのfindLoginUserメソッドでユーザー1件検索
		return mapper.findLoginUser(userId);
	}
	
	
}
