package QuestionNo4;

public class QuestionNo4b {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int sortList(ListNode head) {
        if (head == null || head.next == null)
            return 0;

        int count = 0;
        ListNode current = head;
        while (current.next != null) {
            if (current.val > current.next.val) {
                current.next = current.next.next;
                count++;
            } else {
                current = current.next;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        QuestionNo4b obj = new QuestionNo4b();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);


        System.out.println("Number of steps required to sort the linked list: " + obj.sortList(head));
    }
}
