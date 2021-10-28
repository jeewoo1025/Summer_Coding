import java.io.*;
import java.util.*;

public class bj_5639 {
    public static class Node {
        int data;
        Node leftNode, rightNode;  // 왼쪽/오른쪽 자식노드의 값

        public Node(int data) {
            this.data = data;
        }

        void insert(int val) {
            if(val < this.data) {
                if(this.leftNode == null)
                    this.leftNode = new Node(val);
                else
                    this.leftNode.insert(val);
            } else {
                if(this.rightNode == null)
                    this.rightNode = new Node(val);
                else
                    this.rightNode.insert(val);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // root node
        int val = Integer.parseInt(br.readLine());
        Node root = new Node(val);

        // 이진탐색트리 구현
        while(true) {
            String str = br.readLine();
            if(str == null || str.equals(""))
                break;
            root.insert(Integer.parseInt(str));
        }

        postOrder(root);
    }

    public static void postOrder(Node node) {
        if(node == null)
            return;

        postOrder(node.leftNode);
        postOrder(node.rightNode);
        System.out.println(node.data);
    }
}
