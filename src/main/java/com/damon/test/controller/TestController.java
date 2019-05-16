package com.damon.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("请输入字符串:");
//        String input = scanner.next();
//        char[] chars = input.toCharArray();
//        int sum = 0;
//        for(int i = 0,j = chars.length - 1; i <= j; i++) {
//            System.out.println(chars[i] - '0');
//            sum += (chars[i] - '0') * Math.pow(10,(j - i));
//            System.out.println("sum=" + sum);
//        }
//        System.out.println("last sum : " + sum);

//        int input = scanner.nextInt();
//        int[] a = {4,7,12,31,52};
//        System.out.println("结果：" + binarySearch(a, 0, a.length - 1, input));

          testSumTime();
    }

    public static int binarySearch(int[] arr, int start, int end, int val) {
        int temp = -1;
        while (start < end && temp != start) {
            temp  = (start + end) / 2;
            if (arr[temp] == val)
                return temp;
            if (arr[temp] > val)
                end = temp;
            else
                start = temp;
        }
        return arr[temp] == val ? temp : -1;
    }

    public static void testSumTime() {
        int[] a = {1,2,5,10};
        System.out.println("最短时间为：" + sumTime(a));
        System.out.println("非递归方式 " + sumTime1(a));
    }

    public static int sumTime(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int[] result = new int[arr.length + 1];
        for (int i = 0; i <= arr.length; i++) {
            result[i] = -1;
        }
        return sumTime(arr, result, arr.length);
    }

    public static int sumTime(int[] arr, int[] result, int cur) {
        if (result[cur] != -1) {
            return result[cur];
        }
        if (cur == 0) {
            return 0;
        }
        if (cur == 1 || cur == 2) {
            return arr[cur - 1];
        }
        return Math.min(sumTime(arr, result, cur - 1) + arr[0] + arr[cur - 1],
                sumTime(arr, result, cur - 2) + arr[0] + 2 * arr[1] + arr[cur-1]);
    }

    public static int sumTime1(int[] arr) {
        int ti = 0;
        int t1 = arr[0];
        int t2 = arr[1];
        for (int i = 3, len = arr.length; i <= len; i++) {
            ti = Math.min(t1 + arr[0] + arr[i - 1],
                    t2 + arr[0] + 2 * arr[1] + arr[i - 1]);
            t2 = t1;
            t1 = ti;
        }
        return ti;
    }
}
