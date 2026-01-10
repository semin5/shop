package com.apple.shop.other;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class nai {

    private Long id;
    private String name;
    private Integer age;

    public void 한살더하기(){
        this.age = this.age + 1;
    }

    public void 나이설정(Integer a){
        if(a > 0 && a < 100) {
            this.age = a;
        }
    }

    public static void main(String[] args) {
        var object = new nai();
        object.setName("홍길동");
        object.setAge(20);
        object.한살더하기();
        System.out.println(object.getAge());
        object.나이설정(12);
        System.out.println(object.getAge());
        object.나이설정(101);
        System.out.println(object.getAge());
    }
}


