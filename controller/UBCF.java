package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

import model.BookRateModel;

public class UBCF {
	
	private static int user_id;
	public static HashMap<Integer, ArrayList<BookRateModel>> users = new HashMap<Integer, ArrayList<BookRateModel>>();
	public static HashMap<String, Integer> bookList = new HashMap<String, Integer>();
	
	public static Set<Entry<Integer, ArrayList<BookRateModel>>> set;
	public static Iterator<Entry<Integer, ArrayList<BookRateModel>>> iterate;
	
	public static int getUser_id() {
		return user_id;
	}
	
	public static void setUser_id(int id) {
		user_id = id;
	}
	
	
	
	public static void getMapData() {
		Database.getMapData(users, bookList);
	}
	
	public static double calculate(int id) {
		//user_id -> id karsýlastýr
		/// up	  / down
		/// d1.d2 / ||d1|| * ||d2||
		int up = 0, down = 0;
		ArrayList<BookRateModel> list1 = users.get(user_id);
		ArrayList<BookRateModel> list2 = users.get(id);

		//up
		for(int i=0; i<list1.size(); i++) {
			for(int j=0; j<list2.size(); j++) {
				if(list1.get(i).getIsbn().equals(list2.get(j).getIsbn())) {
					up += list1.get(i).getRate() * list2.get(j).getRate();
					break;
				}
			}
		}
		if( up == 0) return 0;
		
		//down
		for(int i = 0; i<list1.size(); i++) down += Math.pow(list1.get(i).getRate(), 2);
		int temp = 0;
		for(int i = 0; i<list2.size(); i++) temp += Math.pow(list2.get(i).getRate(), 2);
		down = down * temp;
		
		System.out.println("Up: " + up + " Down: " + down );
		System.out.println("Up/down: " + up / (double) down);
		
		if(down == 0) return 0;
		return up/ (double) down;
	}

	public static void rating() {
		System.out.println("start");
		user_id = 278859;
		
		set = users.entrySet();
		iterate = set.iterator();
		
		int enYakin = 1;
		double yakinlik = calculate(2);
		
		while(iterate.hasNext()) {
			Map.Entry<Integer, ArrayList<BookRateModel>> me = (Map.Entry<Integer, ArrayList<BookRateModel>>)iterate.next();
			//10 kitap oylamamis kisilerle karsilastirma ve kendinle karsilastýrma
			//System.out.println(users.get(me.getKey()));
			if(users.get(me.getKey()).size() < 10 || user_id == me.getKey())
				continue;
			
			//arttýkca yakýnlýk artar
			if(calculate(me.getKey()) > yakinlik ) {
				yakinlik = calculate(me.getKey());
				enYakin = me.getKey();
			}
			
		}
		
		//kullaniciya gore kitap dondur
		System.out.println("kitaplar");
		for(int i=0; i<users.get(enYakin).size(); i++) {
			System.out.println(users.get(enYakin).get(i).getIsbn());
		}
		
		
		/*
			if(me.getKey() == 278859) {
				System.out.print(me.getKey() + ": ");
				System.out.println(me.getValue());	
			}
			
		}*/
	}
	
}
