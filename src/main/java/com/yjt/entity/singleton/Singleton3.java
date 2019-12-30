package com.yjt.entity.singleton;

import lombok.Data;

@Data
public class Singleton3 {

	String name;
	
    private Singleton3() {}
    // 主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
    private static class Holder {
        private static Singleton3 instance = new Singleton3();
    }
    public static Singleton3 getInstance() {
        return Holder.instance;
    }
    
}