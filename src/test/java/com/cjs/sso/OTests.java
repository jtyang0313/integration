package com.cjs.sso;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Stack;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yjt.entity.iterator.MenuIterator;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class OTests {

    @Test
    public void contextLoads() {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String encode = encoder.encode("123456");
    	if(encoder.matches("123456", encode)){
    		System.out.println(true);
    	}else{
    		System.out.println(false);
    	}
    	//$2a$10$t8KCFULk0DuWAGuU2MNkVecOPXcKiCI.wFGjfzXnoqFYR8HvxuwK6
    }
    //观察者模式
    @Test
    public void observer(){
    	Observable o = new Observable();
    	//o.addObserver(o);
    	o.notifyObservers();
    	new Observer() {
			@Override
			public void update(Observable o, Object arg) {
			}
		};
    }
    
	@Test
    public void formats() throws IOException{
    	FileInputStream in = new FileInputStream("d://item.txt");
    	int size = in.available();
    	char[] text = new char[size];
    	for (int i = 0; i < text.length; i++) {
    		text[i] = (char)in.read();
    		System.out.print(text[i]);
    	}
    	in.close();
    	
    	InputStream in2 = new BufferedInputStream(new FileInputStream("d:/hello.txt"));
    	byte[] b = new byte[20];
    	int read2 = in2.read(b);
    	in2.close();
    	System.out.println(read2);
    	String string = new String(b);
    	System.out.println(string);
    	
    }
    
    @Test
    public void ss(){
    	String[] items = new String[6];
    	for(int i=0;i<items.length;i++){
    		if(i == 3){
    			continue;
    		}
    		items[i] = "a";
    	}
    	for(int i=0;i<items.length;i++){
    		System.out.print(items[i]);
    		
    	}
    	System.out.println();
    	String[] strings = new String[]{"a","b","c","d","e","f"};
    	Iterator menuIterator = new MenuIterator(strings);
    	while(menuIterator.hasNext()){
    		String next = (String)menuIterator.next();
    		//menuIterator.remove();
    		System.out.print(next);
    	}
    	System.out.print("\nhh");
    	List<String> list = new ArrayList<String>();
    	list.iterator();
    	Hashtable<String, String> table = new Hashtable<String, String>();
    	table.values().iterator();
    	Stack stack = new Stack<Object>();
    }
    
    @Test
    public void stateMode(){
    	Random random = new Random();
    	int nextInt = random.nextInt(10);//10%概率
    	
    	double random2 = Math.random();
    	int i  = (int)random2*10;
    	System.out.println(nextInt);
    	System.out.println(random2);
    }
    
}

