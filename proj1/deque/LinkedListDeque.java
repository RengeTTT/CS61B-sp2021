package deque;

public class LinkedListDeque<T> {

    private Node<T> sentinel;
    private int size;

    /*
    *  嵌套内部类定义Node节点，prev指向上一个节点，next指向下一个节点，构造单哨兵循环链表
    * */
    public class Node<T> {
        Node<T> prev;
        T val;
        Node<T> next;
        public Node() {}
        public Node(T val) {
            this.val = val;
        }
        public Node(Node<T> prev, T val, Node<T> next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }
    public LinkedListDeque() {
        sentinel = new Node<>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node<>();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
        size = other.size();
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        Node<T> oldNode = sentinel.next;

        sentinel.next = newNode;    // sentinel的下一个节点是新的节点
        newNode.prev = sentinel;    // 新节点的上一个节点是sentinel
        newNode.next = oldNode;     // 新节点的下一个节点是旧节点
        oldNode.prev = newNode;     // 旧节点的上一个节点是新节点

        size++;
    }
    public void addLast(T item) {
        Node<T> lastNode = new Node<>(item);
        Node<T> prevNode = sentinel.prev;

        prevNode.next = lastNode;   // 倒数第二个节点的下一个节点是最后一个节点
        lastNode.prev = prevNode;   // 最后一个节点的上一个节点是倒数第二个节点
        sentinel.prev = lastNode;   // 哨兵节点的上一个节点是最后一个节点
        lastNode.next = sentinel;   // 最后一个节点的下一个节点是哨兵节点
        size++;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public T removeFirst() {
        Node<T> removeItem = sentinel.next;
        if (removeItem == sentinel) {
            return null;
        }
        sentinel.next = removeItem.next;    // 哨兵节点的下一个节点是被移除节点的下一个节点
        removeItem.next.prev = sentinel;    // 被移除节点的下一个节点的前一个节点是哨兵节点
        size--;

        return removeItem.val;
    }
    public T removeLast() {
        Node<T> removeItem = sentinel.prev;
        if (removeItem == sentinel) {
            return null;
        }
        sentinel.prev = removeItem.prev;    // 哨兵节点的上一个节点（最后一个节点）是被移除节点的上一个节点
        removeItem.prev.next = sentinel;    // 最新的最后一个节点（倒数第二个节点）的下一个节点是哨兵节点
        removeItem.next = null;             // 处理没有更新的残存指向
        removeItem.prev = null;
        size--;

        return removeItem.val;
    }
    public T get(int index) {
        Node<T> curNode = sentinel.next;
        if (index > size - 1 || index < 0) {
            return null;
        }
        while (curNode != sentinel) {
            if (index == 0) {
                return curNode.val;
            }
            curNode = curNode.next;
            index--;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node<T> targetNode = recursion(index);
        return targetNode.val;
    }

    private Node recursion(int index) {
        if (index == 0) {
            return sentinel.next;
        }
        Node<T> curNode = recursion(index - 1);
        return curNode.next;
    }

    public void printDeque() {
        Node<T> curNode = sentinel.next;
        while (curNode != sentinel) {
            System.out.print(curNode.val + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }
}
