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

 public class MyLinkedList {

    private static final long serialVersionUID = 1663679278942178557L;
    static class Node {
        private static final long serialVersionUID = -539394075146871892L;
        String value;
        Node next;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        Node(String value) {
            this(value, null);
        }
    }

    protected Node head = null;
    protected Node tail = null;
    protected int size = 0;

    public void addFirst(String value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
        size++;
    }

    public void addLast(String value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void add(int index, String value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node newNode = new Node(value);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            if (current.next == null) {
                tail = newNode;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
    }

    public boolean contains(String value) {
        Node current = head;
        while (current != null) {
            if (current.value == value || current.value != null && current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
        } else {
            return;
        }
        if (head == null) {
            tail = null;
        }
        if (size > 0)
            size--;
    }

    public void removeLast() {
        if (head == null) { // empty list
            return;
        } else if (head == tail) {
            // single element list
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            current.next = null;
        }
        size--;
    }

    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        else if (index == 0)
            removeFirst();
        else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
            }
            size--;
        }
    }

    /*
     * Implement the methods below. Please do not change their signatures!
     */

    public void reverse() {
        /* IMPLEMENT THIS METHOD! */

    /*The reverse method is implemented by swapping the head and tail pointers,
     and then iterating through the list, swapping the next pointers of each node. 
    The new head will be the current tail, and the new tail will be the current head. */
    Node prev = null;
    Node current = head;
    Node next = null;
    while (current != null) {
        next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }
    Node temp = head;
    head = tail;
    tail = temp;

    }

  
        /* IMPLEMENT THIS METHOD! */
        /*The removeMaximumValues method is implemented by iterating through the list,
         comparing each value to the maximum value found so far, and keeping track of the number of occurrences of the maximum value. Once the iteration is complete, 
        the method can remove the first N occurrences of the maximum value from the list. */
    
 public void removeMaximumValues(int N) {
    try {
        while (N > 0) {
            String maxValue = "";
            int count = 0;
            Node current = head;
            while (current != null) {
                if (current.value.compareTo(maxValue) > 0) {
                    maxValue = current.value;
                    count = 1;
                } else if (current.value.equals(maxValue)) {
                    count++;
                }
                current = current.next;
            }

            current = head;
            int index = 0;
            while (current != null) {
                if (current.value.equals(maxValue)) {
                    remove(index);
                    count--;
                    N--;
                } else {
                    index++;
                }
                current = current.next;
            }
        }
    }catch (AssertionError e){
        System.out.println("caught: " + e);
    }
 }
    public Boolean containsSubsequence(MyLinkedList other) {
        /* IMPLEMENT THIS METHOD! */
        /*The containsSubsequence method is implemented by iterating through the elements of the
         current list, and for each element, checking if the following elements in the current list 
         match the elements in the other list, in the same order. If a match is found, the method can
        return true. If the iteration is complete and no match is found, the method can return false. */
        if (other == null || other.size == 0) {
        return true;
    }
    if (size < other.size) {
        return false;
    }
    Node current = head;
    while (current != null) {
        if (current.value.equals(other.head.value)) {
            Node currentOther = other.head;
            Node currentThis = current;
            while (currentOther != null) {
                if (!currentThis.value.equals(currentOther.value)) {
                    break;
                }
                currentOther = currentOther.next;
                currentThis = currentThis.next;
            }
            if (currentOther == null) {
                return true;
            }
        }
        current = current.next;
    }
        return false;
    }

}
