package com.example.domain.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;

@Service
// UserDetailsServiceインターフェースを実装し、ログイン認証で使用するユーザー情報を設定
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	// ユーザー情報を検索したのちに、情報が存在しなければUsernameNotFoundExceptionを発生させる。
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// ユーザー情報取得
		// UserServiceクラスのgetLoginUserメソッドでログインするユーザー情報を取得する
		MUser loginUser = service.getLoginUser(username);
		
		// ユーザーが存在しない場合
		if(loginUser == null) {
			// 例外を発生させる
			throw new UsernameNotFoundException("user not found");
		}
		
		// ログインユーザーの権限List作成
		GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		
		// UserDetails生成
		UserDetails userDetails = (UserDetails) new User(
				loginUser.getUserId(),
				loginUser.getPassword(),
				authorities);
		
		return userDetails;
	}
	
	
}
