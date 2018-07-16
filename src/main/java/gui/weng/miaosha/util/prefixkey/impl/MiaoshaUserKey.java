package gui.weng.miaosha.util.prefixkey.impl;

import gui.weng.miaosha.util.prefixkey.BasePrefix;

public class MiaoshaUserKey extends BasePrefix {

	public static final int TOKEN_EXPIRE = 3600*24 * 2;
	private MiaoshaUserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "tk");
}
