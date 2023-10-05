
/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: YYYY-MM-DD
 */

public class MyRawLinkedList {
    private static final long serialVersionUID = 1561306366555780559L;

    static class Node {
        private static final long serialVersionUID = -3505677833599614054L;
        String value;
        Node next = null;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        Node(String value) {
            this(value, null);
        }
    }

    /* This is intentionally left private so that you can't erroneously try to
     * instantiate a `new MyRawLinkedList()`
     */
    private MyRawLinkedList() {}

    /*
     * These methods included as examples for how to use Node as a linked list.
     */
    public static String listToString(Node head) {
        String ret = "";
        while (head != null) {
            ret += "\"" + head.value + (head.next == null ? "\" " : "\", ");
            head = head.next;
        }
        return "[ " + ret + "]";
    }

    public static void print(Node head) {
        System.out.println(listToString(head));
    }

    /*
     * Do not call this method in your code; it is not efficient. It is just for our
     * test cases.
     */
    public static String get(Node head, int index) {
        if (index < 0 || index >= size(head)) {
            throw new IndexOutOfBoundsException();
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
    }

    /* Do not call this method in your code. It is just for the test cases. */
    public static boolean contains(Node head, String value) {
        Node current = head;
        while (current != null) {
            if (current.value == value || current.value != null && current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /* Do not call this method in your code. It is just for the test cases. */
    public static int size(Node head) {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public static void main(String[] args) {
        Node list1 = new Node("One", new Node("Two", new Node("Three", null)));
        print(list1);

        Node args_as_list = null;
        for (int i = args.length - 1; i >= 0; i--)
            args_as_list = new Node(args[i], args_as_list);

        print(args_as_list);

        Node list2 = null;
        list2 = new Node("a", list2);
        list2 = new Node("b", list2);
        list2 = new Node("c", list2);
        print(list2);
    }

    /*
     * Implement the methods below. Please do not change their signatures!
     */

    public static Node reverse(Node head) {
        /* IMPLEMENT THIS METHOD! */
        /*This implementation uses three pointers: prev, current, and next.
         It starts with current pointing to the head of the list, and prev and next initially set to null */
            Node prev = null;
            Node current = head;
            Node next = null;
            while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
    }
    return prev;
   //return null;
    }

    /* IMPLEMENT THIS METHOD! */

/*This implementation first checks if N is less than or equal to zero and if so,
 it returns the original list without modification. Then, it creates a copy of the original list. 
 Using the copy, it finds the N largest values using a priority queue and reverse order comparator. 
 After that, it iterates through the original list and remove the N largest values using a previous pointer. 
 Finally, it returns the modified list. */


public static Node removeMaximumValues(Node head, int N) {
    
try {
    if (N <= 0) {
        return head;
    }
    // Create a copy of the original list
    Node current = head;
    Node copy = new Node(current.value);
    Node copyHead = copy;
    while (current.next != null) {
        current = current.next;
        copy.next = new Node(current.value);
        copy = copy.next;
    }
    // Print the copy of the original list
    System.out.println("Original list copy: " + listToString(copyHead));

    // Find the N largest values using a priority queue
    PriorityQueue<String> pq = new PriorityQueue<>(Comparator.reverseOrder());
    current = copyHead;
    while (current != null) {
        pq.offer(current.value);
        current = current.next;
    }
    // Print the N largest values
    System.out.print("N largest values: ");
    for (int i = 0; i < N; i++) {
        System.out.print(pq.poll() + " ");
    }
    System.out.println();

    // Iterate through the original list and remove the N largest values
    Node prev = null;
    current = head;
    while (current != null) {
        if (pq.contains(current.value)) {
            if (prev == null) {
                head = current.next;
            } else {
                prev.next = current.next;
            }
        } else {
            prev = current;
        }
        current = current.next;
    }

    // Print the modified list
    System.out.println("Modified list: " + listToString(head));
    return head;

    }
}
    catch (AssertionError e){
        System.out.println("caught: " + e);
    }
}

    public static boolean containsSubsequence(Node head, Node other) {
        /* IMPLEMENT THIS METHOD! */
        /*This implementation checks if the other list is null, and if it is, returns true. 
         Then it checks if the head list is null, if it is, return false. Then it iterates through
         the head list and compare each element of head list with the first element of other list. */
         if (other == null) {
        return true;
         }
    if (head == null) {
        return false;
    }
    Node current = head;
    while (current != null) {
        if (current.value.equals(other.value)) {
            Node curr = current;
            Node oth = other;
            while (curr != null && oth != null && curr.value.equals(oth.value)) {
                curr = curr.next;
                oth = oth.next;
            }
            if (oth == null) {
                return true;
            }
        }
        current = current.next;
    }
    return false;

        //return false;
    }



