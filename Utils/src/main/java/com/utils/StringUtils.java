package com.utils;

import java.util.Random;

public class StringUtils {
	/**
	 * 生成4/6位短信验证码
	 * @return
	 */
	public static String generateCheckCode(Digit digit){
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < digit.num; i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	static enum Digit{
		FOUR(4),SIX(6);
		private int num;

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		private Digit(int num) {
			this.num = num;
		}
	}
}
