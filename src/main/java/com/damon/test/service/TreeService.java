package com.damon.test.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeService {

    public static Node createTree() {
        Node d = new Node(null, null, "D");
        Node e = new Node(null, null, "E");
        Node f = new Node(null, null, "F");
        Node b = new Node(d, e, "B");
        Node c = new Node(f, null, "C");
        return new Node(b, c, "A");
    }

    public static void main(String[] args) {
        List<String> preList = preList(createTree());
        System.out.println("前序遍历：" + Arrays.toString(preList.toArray()));
        System.out.println("------------------------------");
        System.out.println("中序遍历：" + Arrays.toString(inList(createTree()).toArray()));
        System.out.println("------------------------------");
        System.out.println("后序遍历：" + Arrays.toString(lastList(createTree()).toArray()));
        System.out.println("------------------------------");
        System.out.println("层序遍历：" + Arrays.toString(layerList(createTree()).toArray()));
    }

    /**
     * 前序遍历二叉树
     * @param node
     * @return
     */
    public static List<String> preList(Node node) {
        if (null == node) {
            return null;
        }
        List<String> result = new ArrayList<>();
        Node cur = node;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || null != cur) {
            if (null != cur) {
                stack.push(cur);
                result.add(cur.data);
                cur = cur.left;
            } else {
                Node node1 = stack.pop();
                cur = node1.right;
            }
        }
        return result;
    }

    /**
     * 中序遍历二叉树
     * @param node
     * @return
     */
    public static List<String> inList(Node node) {
        if (null == node) {
            return null;
        }
        List<String> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node cur = node;
        while (!stack.isEmpty() || cur != null) {
            if (null != cur) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.data);
                cur = cur.right;
            }
        }
        return result;
    }

    /**
     * 后序遍历二叉树
     * @param node
     * @return
     */
    public static List<String> lastList(Node node) {
        if (null == node) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node cur = node;
        Node pre = null;
        while (null != cur) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (null == cur.right || pre == cur.right) {
                result.add(cur.data);
                pre = cur;
            } else {
                stack.push(cur);
                cur = cur.right;
                while (null != cur) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        return result;
    }

    /**
     * 层序遍历二叉树
     * @param node
     * @return
     */
    public static List<String> layerList(Node node) {
        if (null == node) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        Node cur = node;
        queue.add(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            result.add(cur.data);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return result;
    }

    /**
     * 二叉树
     */
    static class Node {
        private Node left;
        private Node right;
        String data;
        Node (Node left, Node right, String data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }

        public String getData() {
            return data;
        }

        void setData(String data) {
            this.data = data;
        }
    }


}
