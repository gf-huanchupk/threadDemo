package com.gf.conn013;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserConcurrentMap {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String , Object>();
		chm.put("k1", "v1");
		chm.put("k2", "v2");
		chm.put("k3", "v3");
		chm.putIfAbsent("k3", "vvvv"); //如果没有k3, 就添加
		
		for(Map.Entry<String, Object> me: chm.entrySet()){
			System.out.println("key:" + me.getKey() + ",value:" + me.getValue() );
		}
	}
	
}
