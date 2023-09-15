package com.example.demo1;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author baiyu
 * @Date 2023/3/28 17:45
 * @Description
 */
class Node {
    //链表用于存储值
    private final int value;
    //指向下一个节点  理解为Node next更加恰当
    private Node node;

    public Node(int value) {
        this.value = value;
        this.node = null;
    }

    public int getValue() {
        return value;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

}

// 1 > 2 >3 > null


public class Solution {

    public static void main(String[] args) {
        Create create = new Create();
        Node node = create.creat(Arrays.asList(1, 2, 3, 4, 5));
        create.printList(node);
    }
}


class Create {

    public Node creat(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        Node node = new Node(list.get(0));
        Node nextNode = creat(list.subList(1, list.size()));
        node.setNode(nextNode);

        return node;
    }

    //测试方便的打印函数
    public void printList(Node node){
        while (node != null){
            System.out.print(node.getValue());
            System.out.print(" ");
            node = node.getNode();
        }
        System.out.println();
    }



}