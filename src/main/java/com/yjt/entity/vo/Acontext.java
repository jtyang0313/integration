package com.yjt.entity.vo;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Acontext<T extends Bcontext> {

	private String name;
	
	public String pname;
	
	public void apple(){
		System.out.println("apple");
	}
	
	public static void pear(){
		ArrayList<String> arrayList = new ArrayList<String>();
		
		System.out.println("pear");
	}
}
