package com.buptsse.tj;

import java.io.FileNotFoundException;

@SuppressWarnings("rawtypes")
public class User_info implements Comparable, Cloneable{
	private boolean flag;     //用于标志该用户是否被选为最终推送对象,默认为true
	public String user_id;    //用户ID
	private double fans_num;     //用户好友数
	private double unit_comment;     //单位状态被评论数
	private double freq_news;     //发状态频率
	private double user_friendly_value;     //用户友善值，由友善值公式计算得到
	private String[] user_friends;     //用户好友列表，以数组形式存储
	
	
	public User_info() throws FileNotFoundException {
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public double getUser_friendly_value() {
		return user_friendly_value;
	}
	
	public void calUser_friendly_value() {
		this.user_friendly_value = Math.log(0.7641*this.freq_news + 0.1149*Math.sqrt(this.fans_num) + 0.121*this.unit_comment);
	}
	
	public String[] getUser_friends() {
		return user_friends;
	}
	
	public void setUser_friends(String[] user_friends) {
		this.user_friends = user_friends;
	}

	public double getUnit_comment() {
		return unit_comment;
	}

	public void setUnit_comment(double unit_comment) {
		this.unit_comment = unit_comment;
	}

	public double getFans_num() {
		return fans_num;
	}

	public void setFans_num(double fans_num) {
		this.fans_num = fans_num;
	}

	public double getFreq_news() {
		return freq_news;
	}

	public void setFreq_news(double freq_news) {
		this.freq_news = freq_news;
	}

	
	@Override
	public int compareTo(Object o) {
		User_info user = (User_info)o;
		return user_friendly_value > user.user_friendly_value ? 1 : (user_friendly_value == user.user_friendly_value ? 0 : -1);
	}
	
	@Override
	public Object clone() {
		User_info user = null;
		try{
			user = (User_info)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return user;
	}
}
