package LinkedList;

public class RemoveNthNodeFromEnd {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null)
            return null;
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        if (size == n)
            return head.next;
        curr = head;
        for (int i = 1; i < (size - n); i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return head;
    }
}
