package cn.tedu.domain;

import java.io.Serializable;

import cn.tedu.exception.MsgException;
import cn.tedu.utils.WebUtils;

public class User extends Object implements Serializable{
	private int id;
	private String username;
	private String password;
	private String password2;
	private String nickname;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void checkData() throws MsgException{
		//>>非空校验
		//用户名
		if(WebUtils.isNull(username)){//校验失败
			//设置提示消息
			throw new MsgException("用户名不能为空");
		}
		
		if(WebUtils.isNull(password)){//校验失败
			//设置提示消息
			throw new MsgException("密码不能为空");
		}
		if(WebUtils.isNull(password2)){//校验失败
			//设置提示消息
			throw new MsgException("确认密码不能为空");
		}
		//>>两次密码是否一致校验
		if(!password.equals(password2)){
			throw new MsgException("两次密码不一致");
		}
		if(WebUtils.isNull(nickname)){//校验失败
			throw new MsgException("昵称不能为空");
		}
		if(WebUtils.isNull(email)){//校验失败
			throw new MsgException("邮箱不能为空");
		}
		//>>邮箱格式是否正确
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
			throw new MsgException("邮箱格式不正确");
		}
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username="
				+ username + ", password=" + password
				+ ", password2=" + password2
				+ ", nickname=" + nickname
				+ ", email=" + email + "]";
	}
}
