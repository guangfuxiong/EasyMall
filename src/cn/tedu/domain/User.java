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
		//>>�ǿ�У��
		//�û���
		if(WebUtils.isNull(username)){//У��ʧ��
			//������ʾ��Ϣ
			throw new MsgException("�û�������Ϊ��");
		}
		
		if(WebUtils.isNull(password)){//У��ʧ��
			//������ʾ��Ϣ
			throw new MsgException("���벻��Ϊ��");
		}
		if(WebUtils.isNull(password2)){//У��ʧ��
			//������ʾ��Ϣ
			throw new MsgException("ȷ�����벻��Ϊ��");
		}
		//>>���������Ƿ�һ��У��
		if(!password.equals(password2)){
			throw new MsgException("�������벻һ��");
		}
		if(WebUtils.isNull(nickname)){//У��ʧ��
			throw new MsgException("�ǳƲ���Ϊ��");
		}
		if(WebUtils.isNull(email)){//У��ʧ��
			throw new MsgException("���䲻��Ϊ��");
		}
		//>>�����ʽ�Ƿ���ȷ
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
			throw new MsgException("�����ʽ����ȷ");
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
