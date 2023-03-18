/**
Provide a class SingleLinkedList that implements the interface ILinkedList.

Input Format

First line contains a comma-separated list of the linkedlist elements.
The following line contains a keyword of the operation required to perform on the list.
The number and the contents of the following lines depend on the keyword:

add: Followed by 1 line containing the element to insert to the end of the list.
addToIndex: Followed by 2 lines, the first contains the insertion index and the second contains the insertion value.
get: Followed by 1 line containing the index of the requried element.
set: Followed by 2 lines, the first contains the index and the second contains the value of the new value.
clear: Not followed by additional lines.
isEmpty: Not followed by additional lines.
remove: Followed by 1 line containig the index of the element.
sublist: Followed by 2 lines, the first has the starting index and the second has the ending index of the sublist.
contains: Followed by 1 line, containing the the element we test for exisitence.
Constraints

Assume the list contains only integer values for this problem.

Output Format

For keywords add, addToIndex, set, clear, and remove it is required to print the list elements after the operations.

For get: print the retrieved element.
For isEmpty, contains: print "True" or "False".
For sublist: print the elements of the sublist.

If any error occurs, print "Error".


Sample Input 0

[]
add
10
Sample Output 0

[10]
Sample Input 1

[40, 98, 36, 83, 25, 64, 36, 10, 31, 70]
addToIndex
3
67
Sample Output 1

[40, 98, 36, 67, 83, 25, 64, 36, 10, 31, 70]
Sample Input 2

[1, 2]
isEmpty
Sample Output 2

False
Sample Input 3

[6, 89, 85, 33, 18]
set
0
69
Sample Output 3

[69, 89, 85, 33, 18]
Sample Input 4

[26, 30, 16, 84, 24, 29, 80, 27, 17, 30, 20, 65, 41, 70, 25, 65, 67, 45, 82, 80]
get
16
Sample Output 4

67
Sample Input 5

[51, 60, 86, 13, 34, 90, 34, 94]
size
Sample Output 5

8
Sample Input 6

[58, 1, 26, 62, 27, 86, 71, 61, 99, 39, 10, 75, 93, 70, 77, 61, 82, 31]
contains
31
Sample Output 6

True
Sample Input 7

[13]
contains
69
Sample Output 7

False
Sample Input 8

[31, 87, 81, 100, 70, 26, 36, 28, 88, 66, 65, 8, 41]
sublist
6
8
Sample Output 8

[36, 28, 88]
Sample Input 9

[74, 59, 23, 89, 38, 73, 62, 22, 29]
clear
Sample Output 9

[]
Sample Input 10

[1, 5]
remove
2
Sample Output 10

Error
Sample Input 11

[1, 5, 4, 2, 1, 4]
get
6
Sample Output 11

Error
*/
import org.w3c.dom.Node;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}


public class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/

    public  static  SingleLinkedList list = new SingleLinkedList();
    Node head;
    static class Node {
        Object data;
        Node next;

        Node(Object d) {
            data = d;
            next= null;
        }
    }
    public SingleLinkedList insert(Object data)
    {
        Node new_node = new Node(data);
        // making a new node if there wasn't any
        if(list.head==null)
            list.head= new_node;
        else {
            Node last = list.head;
            while(last.next != null)
            {
                last=last.next;
            }
            last.next= new_node;
        }
        return list;
    }
    public void printlist()
    {
        Node current_node = list.head;
        System.out.print("[");
        if(list.head==null)
            System.out.print("]");
        else {
            while (current_node != null) {
                System.out.print(current_node.data);
                if (current_node.next != null)
                    System.out.print(", ");
                current_node = current_node.next;
            }
            System.out.print("]");
        }
    }

    public void add(int index, Object element)
    {
        if(list.head==null)
        {
            if(index==0) {
                insert(element);
                printlist();
            }
            else System.out.print("Error");
            return;
        }
        Node new_node = new Node(element);
        Node current_node= list.head;
        for (int i=0;i<index-1;i++)
        {
            current_node=current_node.next;
            if (current_node == null){
                System.out.print("Error");
                return;
            }

        }
        if(index==0)
        {
            new_node.next=list.head;
            list.head=new_node;
        }
        else {
            new_node.next = current_node.next;
            current_node.next = new_node;
        }
        printlist();
    }
    public void add(Object element)
    {
        Node new_node = new Node(element);
        if (list.head==null)
            list.head=new_node;
        else {
            Node current_node = list.head;
            while (current_node.next != null) {
                current_node = current_node.next;
            }
            current_node.next = new_node;
        }
    }
    public Object get(int index)
    {
        if (list.head==null) {
            System.out.print("Error");
            return null;
        }
        else {
            Node current_node = list.head;
            for (int i = 0; i < index; i++) {
                if (current_node == null) {
                    System.out.print("Error");
                    return null;
                }
                else {
                    current_node = current_node.next;
                }
            }
            if(current_node!=null)
                 return current_node.data;
            System.out.print("Error");
            return null;
        }
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element)
    {
        if(list.head==null)
        {
            System.out.print("Error");
            return;
        }
        Node current_node= list.head;
        for (int i=0;i<index;i++)
        {
            current_node=current_node.next;
            if (current_node == null) {
                System.out.print("Error");
                return;
            }
        }
        current_node.data=element;
        printlist();
    }
    /**
     * Removes all of the elements from this list.
     */
    public void clear()
    {
        list.head=null;
    }
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty()
    {
        if(list.head==null)
            return true;
        return false;
    }
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index)
    {
        Node current_node= list.head;
        Node previous_node = list.head;

        if(index==0)
        {
            list.head=current_node.next;
        }
        else {
            for (int i = 0; i < index; i++) {
                previous_node = current_node;
                current_node = current_node.next;
                if (current_node == null) {
                    System.out.print("Error");
                    return;
                }
            }
            previous_node.next = current_node.next;
        }
        printlist();
    }
    /**
     * @return the number of elements in this list.
     */
    public int size()
    {
        int i=0;
        Node current_node=list.head;
        while (current_node!=null)
        {
            i++;
            current_node=current_node.next;
        }
        return i;
    }
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex)
    {
        if (list.head == null)
        {
            System.out.print("Error");
            return null;
        }
        Node from_node = list.head;
        for (int i=0;i<fromIndex;i++)
        {
            from_node=from_node.next;
            if (from_node == null)
            {
                System.out.print("Error");
                return null;
            }
        }
        list.head=from_node;
        Node to_node=list.head;
        for (int i=0;i<toIndex-fromIndex;i++)
        {
            to_node=to_node.next;
            if (to_node == null) {
            System.out.print("Error");
            return null;
        }
        }
        to_node.next=null;
        printlist();
        return null;
    }
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o)
    {
        if(list.head==null)
            return false;
        Node current_node=list.head;
        while (current_node != null)
        {
            if(current_node.data==o)
                return true;
            current_node=current_node.next;
        }
        return false;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        SingleLinkedList obj = new SingleLinkedList();
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty()) {
            arr = new int[]{};
        }
        else
            for(int i = 0; i < s.length; ++i)
            {
                arr[i] =Integer.parseInt(s[i]);
                obj.insert(arr[i]);

            }


        // implementing the insertion function to make the list

        String operation = sc.nextLine();
        switch (operation)
        {
            case "add":
                int addElement=sc.nextInt();
                obj.add(addElement);
                obj.printlist();
                break;
            case "addToIndex":
                int indexAdd = sc.nextInt();
                int IaddElement = sc.nextInt();
                if(indexAdd<0)
                    System.out.print("Error");
                else {
                    obj.add(indexAdd, IaddElement);
                }
                break;
            case "get":

                int indexGet = sc.nextInt();
                if(indexGet<0)
                    System.out.print("Error");
                else {
                    Object answer = obj.get(indexGet);
                    if (answer != null)
                        System.out.print(answer);
                }
                break;
            case "set":
                int indexSet=sc.nextInt();
                int setElement=sc.nextInt();
                if(indexSet<0)
                    System.out.print("Error");
                else
                    obj.set(indexSet,setElement);
                break;
            case "clear":
                obj.clear();
                obj.printlist();
                break;
            case "isEmpty":
                if(obj.isEmpty())
                    System.out.print("True");
                else
                    System.out.print("False");
                break;
            case "remove":

                int removeIndex=sc.nextInt();
                if(removeIndex<0 || list.head==null)
                    System.out.print("Error");
                else
                    obj.remove(removeIndex);
                break;
            case "sublist":
                int startingIndex=sc.nextInt();
                int endingIndex=sc.nextInt();
                if(startingIndex<0 || endingIndex<0 || startingIndex>endingIndex)
                    System.out.print("Error");
                else {
                    obj.sublist(startingIndex, endingIndex);
                }
                break;
            case "contains":
                int isContained=sc.nextInt();
                if(obj.contains(isContained))
                    System.out.print("True");
                else
                    System.out.print("False");
                break;
            case "size":
                System.out.print(obj.size());
                break;
            default: System.out.print("Error");
                break;
        }


    }
}