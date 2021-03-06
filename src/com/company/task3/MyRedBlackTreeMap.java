package com.company.task3;

import java.util.LinkedList;
import java.util.Queue;

import static com.company.task3.MyRedBlackTreeMap.Color.BLACK;
import static com.company.task3.MyRedBlackTreeMap.Color.RED;


public class MyRedBlackTreeMap<K extends Comparable<K>, V> implements IRedBlackTreeMap<K, V> {

    private Node<K, V> root;
    private int size;

    enum Color {
        RED, BLACK
    }


    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        boolean isLeftChild = false;
        Color color = RED;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.parent = null;

        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        if (root == null) {
            root = newNode;
            root.color = BLACK;
            size++;
            return;
        }
        put(root, newNode);
    }

    public V get(K key) {
        Node<K, V> curr = root;
        if (root.key.equals(key)) {
            System.out.println("root " + curr.color);
            return curr.value;
        } else {
            while(true) {
                if(key.compareTo(curr.key) > 0) {
                    curr = curr.right;
                    if(curr == null) break;
                }

                if(key.compareTo(curr.key) < 0) {
                    curr = curr.left;
                    if(curr == null) break;
                }
                if(curr.key.equals(key)) {
                    System.out.println((curr.isLeftChild ? "left" : "right") + " " + curr.color);
                    return curr.value;
                }
            }
        }
        return null;
    }


    private void put(Node<K, V> parent, Node<K, V> newNode) {
        if (newNode.key.compareTo(parent.key) > 0) {
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = false;
                size++;
                return;
            }
            put(parent.right, newNode);
        } else if (newNode.key.compareTo(parent.key) < 0) {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = true;
                size++;
                return;
            }
            put(parent.left, newNode);
        }
        checkColor(newNode);
    }

    private void checkColor(Node<K, V> node) {
        if (node == root) return;

        if (node.color == RED && node.parent.color == RED) {
            correctTree(node);
            return;
        }
        checkColor(node.parent);
    }

    private void correctTree(Node<K, V> node) {
        if (node.parent.isLeftChild) {
            if (node.parent.parent.right == null || node.parent.parent.right.color == BLACK) {
                rotate(node);
                return;
            }
            node.parent.parent.right.color = BLACK;
            node.parent.parent.color = RED;
            node.parent.color = BLACK;
            root.color = BLACK;
        } else {
            if (node.parent.parent.left == null || node.parent.parent.left.color == BLACK) {
                rotate(node);
                return;
            }
            node.parent.parent.left.color = BLACK;
            node.parent.parent.color = RED;
            node.parent.color = BLACK;
            root.color = BLACK;
        }

    }

    private void rotate(Node<K, V> node) {
        if (node.isLeftChild) {
            if (node.parent.isLeftChild) {
                rightRotation(node.parent.parent);
                node.color = RED;
                node.parent.color = BLACK;
                if (node.parent.right != null) {
                    node.parent.right.color = RED;
                }
            } else {
                rightLeftRotation(node.parent.parent);
                node.color = BLACK;
                node.right.color = RED;
                node.left.color = RED;
            }
        } else {
            if (!node.parent.isLeftChild) {
                leftRotation(node.parent.parent);
                //System.out.println(root.right.left);
                node.color = RED;
                node.parent.color = BLACK;
                if (node.parent.left != null) {
                    node.parent.left.color = RED;
                }
            } else {
                leftRightRotation(node.parent.parent);
                node.color = BLACK;
                node.left.color = RED;
                node.right.color = RED;
            }
        }
    }

    private void leftRotation(Node<K, V> node) {
        Node<K, V> temp = node.right;
        node.right = temp.left;

        if (node.right != null) {
            node.right.parent = node;
            node.right.isLeftChild = false;
        }

        if (node.parent == null) {
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            if (node.isLeftChild) {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }
        temp.left = node;
        node.isLeftChild = true;
        node.parent = temp;
        //System.out.println(temp.left);
    }


    private void rightRotation(Node<K, V> node) {
        Node<K, V> temp = node.left;
        node.left = temp.right;

        if (node.left != null) {
            node.left.parent = node;
            node.left.isLeftChild = true;
        }

        if (node.parent == null) {
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            if (node.isLeftChild) {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }
        temp.right = node;
        node.isLeftChild = false;
        node.parent = temp;
    }

    private void rightLeftRotation(Node<K, V> node) {
        rightRotation(node.right);
        leftRotation(node);
    }

    private void leftRightRotation(Node<K, V> node) {
        leftRotation(node.left);
        rightRotation(node);
    }

    @Override
    public boolean remove(K key) {
        throw new UnsupportedOperationException();
    }

    public void printTreeQueue() {
        Queue<Node<K,V>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<K,V> curr = queue.remove();
            System.out.println(curr);
            if(curr.left != null){
                queue.add(curr.left);
            }
            if(curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    public void printTree() {
        Node<K,V> current = root;
        printTree(current,0);
    }

    private void printTree(Node<K,V> node, int level) {
        if(node != null) {
            for(int i = 0; i < level; i++) {
                System.out.print(" ");
            }
            System.out.println(node.key+ "(" + node.color + ")");
            level++;
            printTree(node.left,level);
            printTree(node.right,level);
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

}
