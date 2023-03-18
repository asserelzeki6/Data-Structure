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
    public  void Display();
}


class DoubleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/

    class Node {
        Object data;
        Node next = null, prev = null;

        Node(Object data) {
            this.data = data;
        }
    }

    public  Node head;

    public void add(int index, Object element) {
        Node node = new Node(element);
        if (this.isEmpty()) {
            this.head = node;
            return;
        } else if (index == 0) {
            Node tmp = this.head;
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        } else {
            Node tmp = head;
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
            node.next = tmp.next;
            tmp.next = node;
            node.prev = tmp;
            if (node.next != null)
                node.next.prev = node;
        }
    }

    public void add(Object element) {
        Node node = new Node(element);

        if (this.isEmpty()) {
            this.head = node;
            return;
        }
        Node tmp = this.head;

        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
        node.prev = tmp;
    }

    public Object get(int index) {
        Node tmp = this.head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    public void set(int index, Object element) {
        Node tmp = this.head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        tmp.data = element;
    }

    public void clear() {
        Node tmp = this.head;
        while (this.head != null) {
            tmp = this.head;
            this.head = this.head.next;
            tmp = null;
        }
    }

    public boolean isEmpty() {
        if (this.head == null)
            return true;
        return false;
    }

    public void remove(int index) {
        Node tmp = this.head;
        if (index == 0) {
            this.head = tmp.next;
            return;
        }

        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        if (tmp.next != null) {
            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;
            tmp = null;
        } else {
            tmp.prev.next = null;
            tmp = null;
        }
    }

    public int size() {
        int s = 0;
        if (this.isEmpty())
            return 0;
        else {
            Node tmp = this.head;
            while (tmp.next != null) {
                s++;
                tmp = tmp.next;
            }
        }

        return s + 1;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        ILinkedList obj = new DoubleLinkedList();
        while (fromIndex <= toIndex) {
            if (fromIndex != toIndex)
                obj.add(this.get(fromIndex));
            fromIndex++;
        }
        obj.add(this.get(toIndex));
        return obj;
    }

    public boolean contains(Object o) {
        Node tmp = this.head;
        if (this.head == null)
            return false;
        while (tmp.next != null) {
            if (tmp.data == o)
                return true;
            tmp = tmp.next;
        }
        if (tmp.data == o)
            return true;
        return false;
    }

    public  void Display() {
        Node tmp = this.head;
        if (tmp == null) {
            System.out.println("[]");
            return;
        }
        System.out.print('[');
        while (tmp.next != null) {
            System.out.print(tmp.data + ", ");
            tmp = tmp.next;
        }
        System.out.print(tmp.data);
        System.out.println(']');
    }
}

public class Main  {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        ILinkedList obj=new DoubleLinkedList();

        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");

        String[] s = sin.split(", ");
        if (s.length == 1 && s[0].isEmpty())
        {}
        else {
            for(int i = 0; i < s.length; ++i)
            {
                obj.add(Integer.parseInt(s[i]));
            }}
        String operation=sc.nextLine();
        int element,index;
        switch(operation) {
            case "add" :
                element=sc.nextInt();
                obj.add(element);
                obj.Display();
                break;
            case "addToIndex" :
                index=sc.nextInt();
                element=sc.nextInt();

                if(index>obj.size()-1 || index<0){
                    System.out.println("Error");
                    break;
                }
                obj.add(index,element);
                obj.Display();
                break;
            case "get" :
                index=sc.nextInt();
                if(index>obj.size()-1 || index<0){
                    System.out.println("Error");
                    break;
                }
                System.out.println(obj.get(index));
                break;
            case "set" :
                index=sc.nextInt();
                element=sc.nextInt();
                if(index>obj.size()-1 || index<0){
                    System.out.println("Error");
                    break;
                }

                obj.set(index,element);
                obj.Display();
                break;
            case "clear" :
                System.out.println("[]");
                break;
            case "isEmpty" :
                if(obj.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "remove" :
                index=sc.nextInt();
                if(index>obj.size()-1 || index<0){
                    System.out.println("Error");
                    break;
                }
                obj.remove(index);
                obj.Display();
                break;
            case "sublist" :
                int index1=sc.nextInt();
                int index2=sc.nextInt();
                if(index1>obj.size()-1 || index1<0 || index2<index1){
                    System.out.println("Error");
                    break;
                }
                if(index2>obj.size()-1 || index2<0){
                    System.out.println("Error");
                    break;
                }
                ILinkedList obj2=obj.sublist(index1,index2);
                obj2.Display();
                break;
            case "contains" :
                element= sc.nextInt();
                if(obj.contains(element))
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "size" :
                System.out.println(obj.size());
                break;
            default :

        }
    }
}
