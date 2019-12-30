package com.yjt.entity.iterator;

import java.util.Iterator;

public class MenuIterator implements Iterator{

	String[] items;
	int position = 0;
	
	public MenuIterator(String[] items) {
		this.items = items;
	}
	
	@Override
	public boolean hasNext() {
		if(position >= items.length || items[position]==null){
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		String item = items[position];
		position ++;
		return item;
	}

}
