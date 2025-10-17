package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/** セキュリティの対象外の設定 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティを適用しない
		web
			// WebSecurityクラスのignoringメソッドを使うことでセキュリティ管理外に適用できる。
			.ignoring()
				// WebSecurityクラスのantMatchersメソッドでパスを指定して、該当するものを適用する
				.antMatchers("/webjars/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/h2-console/**");
	}
	
	/** セキュリティの各種設定 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// ログイン不要ページの設定
		http
			// HttpSecurityクラスのauthorizeRequestsメソッドで直リンクOKかNGかを設定できる
			.authorizeRequests()
				.antMatchers("/login").permitAll() // 直リンクOK
				.antMatchers("/user/signup").permitAll() // 直リンクOK
				.anyRequest().authenticated(); // それ以外は直リンクNG
		
		http
			// HttpSecurityクラスのformLoginメソッドからメソッドチェーンで条件を追加する。
			.formLogin()
				.loginProcessingUrl("/login") // ログイン処理のパス
				.loginPage("/login") //ログインページの設定
				.failureUrl("/login?error") // ログイン失敗時の遷移先
				.usernameParameter("userId") // ログインページのユーザーID
				.passwordParameter("password") // ログインページのパスワード
				.defaultSuccessUrl("/user/list", true); // 成功時の遷移先
		
		// CSRF対策を無効に設定（一時的）
		// csrfメソッドはリクエストにトークンがないとアクセスできないようにするための設定のON・OFFができる
		http.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = passwordEncoder();
		
		// インメモリ認証
		// AuthenticationManagerBuilderクラスのinMemoryAuthenticationメソッドでインメモリ認証のユーザーを追加できる
		auth
			.inMemoryAuthentication()
				.withUser("user") // userを追加
					.password(encoder.encode("user"))
					.roles("GENERAL")
				.and()
				.withUser("admin") // adminを追加
					.password(encoder.encode("admin"))
					.roles("ADMIN");
	}
}
