package LinkedList;

import java.util.Stack;

public class ReorderList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer by 1
            fast = fast.next.next; // Move fast pointer by 2
        }

        // Step 2: Reverse the second half of the linked list
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode nextTemp = curr.next; // Store next node
            curr.next = prev; // Reverse current node's pointer
            prev = curr; // Move prev to current node
            curr = nextTemp; // Move to next node
        }

        // Step 3: Merge the two halves
        ListNode first = head;
        ListNode second = prev; // Start of reversed second half

        while (second != null) {
            ListNode temp1 = first.next; // Store next node of first half
            ListNode temp2 = second.next; // Store next node of second half

            first.next = second; // Link first half to second half
            second.next = temp1; // Link second half to next node of first half

            first = temp1; // Move to next node in first half
            second = temp2; // Move to next node in second half
        }
    }

    public void reorderList2(ListNode head) {
        if (head.next == null)
            return;
        Stack<ListNode> s = new Stack<>();
        int n = 0;
        ListNode curr = head;
        while (curr != null) {
            s.push(curr);
            n++;
            curr = curr.next;
        }
        curr = head;

        for (int i = 0; i < n / 2; i++) {
            ListNode t = s.pop();
            ListNode prev = curr;
            curr = curr.next;
            prev.next = t;
            t.next = curr;
        }
        if (n % 2 == 1) {
            ListNode t = s.pop();
            curr.next = t;
            curr = curr.next;
        }
        curr.next = null;

    }
}
