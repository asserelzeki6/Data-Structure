import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item);
    /*** Removes the object at the queue rear and returnsit.*/
    public Object dequeue();
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
    public int size();
}

public class ArrayQueue implements IQueue {
       private int size=0 , f=-1 , r =-1;
       private Object [] array = new Object [1000] ;

    public void enqueue(Object item){
        if(f==-1 && f==r)
        {
            f=0;
            r=1;

        } else if (f==r) {
            throw new RuntimeException();
        }
        array[r]=item;
        size++;
        r=(r+1)% array.length;
    }
    public Object dequeue() {
        if(isEmpty()) throw new RuntimeException();
        Object item=array[f];
        f=(f+1)% array.length;
        size--;
        return item;
    }
    public boolean isEmpty(){if(size==0) return true ; else return false;}

    public int size(){return size;}
    public void printQueue ()
    {
        int i = size,j=r;
        System.out.print("[");
        while (i>0) {
            System.out.print(array[(j-1)% array.length]);
            if(i>1)
                System.out.print(", ");
            i--;
            j--;
        }
        System.out.print("]");
    }
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String sin = sc.nextLine().replaceAll("\\[|\\]", "");
            String[] s = sin.split(", ");
            ArrayQueue obj = new ArrayQueue();
            if (!(s.length == 1 && s[0].isEmpty())) {
                for (int i = s.length - 1; i >= 0; i--) {
                    obj.enqueue(s[i]);
                }
            }
            String operation = sc.nextLine();
            switch (operation) {
                case "enqueue":
                    String e = sc.nextLine();
                    obj.enqueue(e);
                    obj.printQueue();
                    break;
                case "dequeue":
                    obj.dequeue();
                    obj.printQueue();
                    break;
                case "size":
                    System.out.println(obj.size());
                    break;
                case "isEmpty":
                    // obj.isEmpty() ? System.out.println("True") :  System.out.println("False");
                    boolean check = obj.isEmpty();
                    if (check)
                        System.out.println("True");
                    else
                        System.out.println("False");

                    break;
                default:
                    System.out.println("Error");
                    System.exit(0);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
            System.exit(0);
        }
    }

}