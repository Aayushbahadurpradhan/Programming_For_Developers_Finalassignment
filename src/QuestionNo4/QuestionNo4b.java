package QuestionNo4;

public class QuestionNo4b {
    // ListNode class to represent the nodes of the linked list
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    // sortList function that takes the head node of the linked list and modifies the list in place to sort it
// It counts the number of swaps it makes during the sorting process and returns this count
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

    // main function to test the sortList function
    public static void main(String[] args) {
        QuestionNo4b obj = new QuestionNo4b();

        // Create a linked list with nodes 3 -> 2 -> 1 -> 4
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);

        // Call the sortList function on the linked list and print the result
        System.out.println("Number of steps required to sort the linked list: " + obj.sortList(head));
    }

}
