package com.damon.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/failure")
    public String failure() {
        return "failure";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入字符串:");
        String input = scanner.next();
        char[] chars = input.toCharArray();
        int sum = 0;
        for(int i = 0,j = chars.length - 1; i <= j; i++) {
            System.out.println(chars[i] - '0');
            sum += (chars[i] - '0') * Math.pow(10,(j - i));
            System.out.println("sum=" + sum);
        }
        System.out.println("last sum : " + sum);
    }
}
