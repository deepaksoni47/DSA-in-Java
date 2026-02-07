package LinkedList;

public class CopyRandomList {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        for (Node curr = head; curr != null; curr = curr.next.next) {
            Node temp = curr.next;
            curr.next = new Node(curr.val);
            curr.next.next = temp;
        }
        for (Node curr = head; curr != null; curr = curr.next.next) {
            curr.next.random = (curr.random != null) ? curr.random.next : null;
        }
        Node newHead = head.next;
        for (Node curr = head; curr != null; curr = curr.next) {
            Node temp = curr.next;
            curr.next = temp.next;
            temp.next = (curr.next != null) ? curr.next.next : null;
        }
        return newHead;
    }
}
