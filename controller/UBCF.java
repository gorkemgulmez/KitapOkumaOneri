package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jdk.incubator.http.MultiMapResult;

import java.util.Set;

import model.BookRateModel;

public class UBCF {
	
	private static int user_id;
	public static HashMap<Integer, ArrayList<BookRateModel>> users = new HashMap<Integer, ArrayList<BookRateModel>>();
	public static Set<Entry<Integer, ArrayList<BookRateModel>>> set;
	public static Iterator<Entry<Integer, ArrayList<BookRateModel>>> i;
	
	public static int getUser_id() {
		return user_id;
	}
	
	public static void setUser_id(int id) {
		user_id = id;
	}
	
	public static void calculate() {
		
	}
	
	public static void setMap() {
		Database.getMapData(users);
	}
	
	public static void rating() {
		//var mainUser = users.get(278859);
		//System.out.println(user_id);
		//System.out.println(mainUser.toString());
		System.out.println("start");
		set = users.entrySet();
		i = set.iterator();
		
		/*
		while(i.hasNext()) {
			Map.Entry<Integer, Integer> me = (Map.Entry<Integer, Integer>)i.next();
			if(me.getKey() == 278859) {
				System.out.print(me.getKey() + ": ");
				System.out.println(me.getValue());	
			}
			
		}*/
		//return -1;
	}
	
}
