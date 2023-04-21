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
class Node
{
    Object data;
    Node next;
    Node(Object data) {
        this.data = data;
    }
}
class LinkedListQueue implements IQueue {
    private int size =0;
    private Node Rear;
    private Node Front;
    public void enqueue(Object item){
        Node New= new Node(item);
        if (this.size==0)
        {
           this.Front=this.Rear=New ;
        }
        else
        {
            this.Rear.next=New;
            this.Rear=New;

        }
        this.size++;
    }
    public Object dequeue(){
        if(this.size==0)
        {
            System.out.println("Error");
            return null;
        }
        else
        {
          Node rtnNode= this.Front;
          Object rtnVal=rtnNode.data;
          this.Front=this.Front.next;
          this.size--;
          if(size==0)
          {
              this.Rear=null;
          }
          return rtnVal;
        }
    }
    public boolean isEmpty(){
        return this.size==0;
    }
    public int size(){
        return this.size;
    }
    private void reverse()
    {
        Node prev = null;
        Node current = this.Front;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.Front = prev;
    }
    public void print()
    {
        this.reverse();
        Node it=this.Front;
        System.out.print('[');
        for(int i=0;i<this.size;i++)
        {
            if(i!=this.size-1) {
                System.out.print(it.data+", ");
            }
            else {
                System.out.print(it.data);
            }

            it=it.next;
        }
        System.out.print("]");
        this.reverse();
    }
}
public class Main{
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        LinkedListQueue queue=new LinkedListQueue();
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        if (s.length == 1 && s[0].isEmpty()) {

        }
        else {
            for (int i = s.length-1; i >= 0; --i) {
                queue.enqueue(Integer.parseInt(s[i]));
            }
        }
        String operation=sc.nextLine();
        switch (operation){
            case "enqueue":
                int element=sc.nextInt();
                queue.enqueue(element);
                queue.print();
                break;
            case "dequeue":
                if(!queue.isEmpty())
                {queue.dequeue();
                queue.print();}
                else {
                    System.out.println("Error");
                }
                break;
            case "isEmpty":
                if(queue.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "size":
                System.out.println((int)queue.size());
                break;
            default:
                System.out.println("Error");


        }

    }
}
