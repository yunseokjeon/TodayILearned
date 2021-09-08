# Linked List

```Java
// 단방향 Linked List  https://youtu.be/C1SDkdPvQPA 
class Node {
    int data;
    Node next = null;

    Node(int d) {
        this.data = d;
    }

    void append(int d) {
        Node end = new Node(d);
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    void delete(int d) {
        Node n = this;
        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }

    void retrieve() {
        Node n = this;
        while (n.next != null) {
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data);
    }
}
```

```Java
// 발전된 Linked List https://youtu.be/IrXYr7T8u_s
class LinkedList {
    Node header;

    static class Node {
        int data;
        Node next = null;
    }

    LinkedList() {
        header = new Node();
    }

    void append(int d) {
        Node end = new Node();
        end.data = d;
        Node n = header;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    void delete(int d) {
        Node n = header;
        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }

    void retrieve() {
        Node n = header.next;
        while (n.next != null) {
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data);
    }

    // 버퍼를 사용하지 않고 중복된 데이터 삭제하기 https://youtu.be/Ce4baygLMz0
    void removeDups() {
        Node n = header;
        while (n != null && n.next != null) {
            Node r = n;
            while (r.next != null) {
                if (n.data == r.next.data) {
                    r.next = r.next.next;
                } else {
                    r = r.next;
                }
            }
            n = n.next;
        }
    }

}
```

