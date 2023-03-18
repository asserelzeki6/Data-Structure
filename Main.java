import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface IPolynomialSolver {
    /**
     * Set polynomial terms (coefficients & exponents)
     * @param poly: name of the polynomial
     * @param terms: array of [coefficients][exponents]
     */
    void setPolynomial(char poly, int[][] terms);

    /**
     * Print the polynomial in ordered human readable representation
     * @param poly: name of the polynomial
     * @return: polynomial in the form like 27x^2+x-1
     */
    String print(char poly);

    /**
     * Clear the polynomial
     * @param poly: name of the polynomial
     */
    void clearPolynomial(char poly);

    /**
     * Evaluate the polynomial
     * @param poly: name of the polynomial
     * @param value: the polynomial constant value
     * @return the value of the polynomial
     */
    float evaluatePolynomial(char poly, float value);

    /**
     * Add two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial
     */
    int[][] add(char poly1, char poly2);

    /**
     * Subtract two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);

    /**
     * Multiply two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return: the result polynomial
     */
    int[][] multiply(char poly1, char poly2);
}
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

public class Main implements IPolynomialSolver{
    static DoubleLinkedList A=new DoubleLinkedList();
    static DoubleLinkedList B=new DoubleLinkedList();
    static DoubleLinkedList C=new DoubleLinkedList();
    static DoubleLinkedList R=new DoubleLinkedList();
    public void setPolynomial(char poly, int[][] terms)
    {
        int[] coff=terms[0];
        for(int i=0;i<coff.length;i++){
            switch(poly)
            {
                case 'A':
                    A.add(coff[i]);
                    break;
                case 'B':
                    B.add(coff[i]);
                    break;
                case 'C':
                    C.add(coff[i]);
                    break;
                default:
                    System.out.println("Error");
                    System.exit(0);
            }
        }
    }
    public String print(char poly)
    {
        boolean ifirst=true;
        switch(poly)
        {
            case 'A':
                 ifirst=true;
                for(int i=0;i<A.size();i++){
                    if((int)A.get(i)==0)
                    { continue;}
                    String sign="+";
                    if((int)A.get(i)<0)
                        sign="-";
                    if(!ifirst)
                    {
                        if(((int)A.get(i)==1 )){System.out.print(sign);}
                        else{System.out.print(sign +Math.abs((int)A.get(i)));}
                    }
                    else{
                        if((int)A.get(i)!=1 || ((int)A.get(i)==1 && i==A.size()-1))
                        {System.out.print((int)A.get(i));}
                        ifirst=false;
                    }
                    if(i==A.size()-2)
                    {System.out.print("x");continue;}
                    else if(i!=A.size()-2 && i!=A.size()-1)
                    {System.out.print("x^"+((int)A.size()-1-i));continue;}
                }
                
                break;
            case 'B':
                ifirst=true;
                for(int i=0;i<B.size();i++){
                    if((int)B.get(i)==0)
                    { continue;}
                    String sign="+";
                    if((int)B.get(i)<0)
                        sign="-";
                    if(!ifirst)
                    {
                        if((int)B.get(i)==1 && i!=(int)B.size()-1){System.out.print(sign);}
                        else{System.out.print(sign +Math.abs((int)B.get(i)));}
                    }
                    else{
                        if((int)B.get(i)!=1 || ((int)B.get(i)==1 && i==B.size()-1))
                        {System.out.print((int)B.get(i));}
                        ifirst=false;
                    }
                    if(i==B.size()-2)
                    {System.out.print("x");continue;}
                    else if(i!=B.size()-2 && i!=B.size()-1)
                    {System.out.print("x^"+((int)B.size()-1-i));continue;}
                }
                break;
            case 'C':
                ifirst=true;
                for(int i=0;i<C.size();i++){
                    if((int)C.get(i)==0)
                    { continue;}
                    String sign="+";
                    if((int)C.get(i)<0)
                        sign="-";
                    if(!ifirst)
                    {
                        if((int)C.get(i)==1 && i!=(int)C.size()-1){System.out.print(sign);}
                        else{System.out.print(sign +Math.abs((int)C.get(i)));}
                    }
                    else{
                        if((int)C.get(i)!=1 || ((int)C.get(i)==1 && i==C.size()-1))
                        {System.out.print((int)C.get(i));}
                        ifirst=false;
                    }
                    if(i==C.size()-2)
                    {System.out.print("x");continue;}
                    else if(i!=C.size()-2 && i!=C.size()-1)
                    {System.out.print("x^"+((int)C.size()-1-i));continue;}
                }
                break;
            case 'R':
                ifirst=true;
                for(int i=0;i<R.size();i++){
                    if((int)R.get(i)==0)
                    { continue;}
                    String sign="+";
                    if((int)R.get(i)<0)
                        sign="-";
                    if(!ifirst)
                    {
                        if((int)R.get(i)==1 && i!=(int)R.size()-1){System.out.print(sign);}
                        else{System.out.print(sign +Math.abs((int)R.get(i)));}
                    }
                    else{
                        if((int)R.get(i)!=1 || ((int)R.get(i)==1 && i==R.size()-1))
                        {System.out.print((int)R.get(i));}
                        ifirst=false;
                    }
                    if(i==R.size()-2)
                    {System.out.print("x");continue;}
                    else if(i!=R.size()-2 && i!=R.size()-1)
                    {System.out.print("x^"+((int)R.size()-1-i));continue;}
                }
                break;
            default:
                System.out.println("Error");
                System.exit(0);
        }
        System.out.println();
        return "hh";
    }
    public void clearPolynomial(char poly)
    {

        switch(poly)
        {
            case 'A':
                A.clear();
                break;
            case 'B':
                B.clear();
                break;
            case 'C':
                C.clear();
                break;
            case 'R':
                R.clear();
                break;
            default:
                System.out.println("Error");
                System.exit(0);
        }
    }
    public float evaluatePolynomial(char poly, float value)
    {       
        float res=0;
        switch(poly)
        {
            case 'A':
                if(A.isEmpty())
                {System.out.println("Error");System.exit(0);}
                for(int i=0;i<A.size();i++){
                    res+=(int)A.get(i)*(Math.pow(value,A.size()-1-i));
                }
                break;
            case 'B':
                if(B.isEmpty())
                {System.out.println("Error");System.exit(0);}
                for(int i=0;i<B.size();i++){
                    res+=(int)B.get(i)*(Math.pow(value,i));
                }
                break;
            case 'C':
                if(C.isEmpty())
                {System.out.println("Error");System.exit(0);}
                for(int i=0;i<C.size();i++){
                    res+=(int)C.get(i)*(Math.pow(value,i));
                }
                break;
            case 'R':
                if(R.isEmpty())
                 {System.out.println("Error");System.exit(0);}
                for(int i=0;i<R.size();i++){
                    res+=(int)R.get(i)*(Math.pow(value,i));
                }
                break;
            default:
                System.out.println("Error");
                System.exit(0);
        }
        return (int)res;
    }
    public int[][] add(char poly1, char poly2)
    {
        int[][] arr={{0}};
        if((poly1=='A' && poly2=='B') || (poly1=='B' && poly2=='A')  )
        {
            if(A.isEmpty()||B.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            for(int i=0;i<Math.max(A.size(),B.size());i++)
            {
                if(i>=A.size())
                { R.add(B.get(i));continue;}
                if(i>=B.size())
                {R.add(A.get(i));continue;}
                R.add((int)B.get(i)+(int)A.get(i));
            }
        }
        else if((poly1=='A' && poly2=='C') || (poly1=='C' && poly2=='A') )
        {
            if(A.isEmpty()||C.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            for(int i=0;i<Math.max(A.size(),C.size());i++)
            {
                if(i>=A.size())
                { R.add(C.get(i));continue;}
                if(i>=C.size())
                {R.add(A.get(i));continue;}
                R.add((int)C.get(i)+(int)A.get(i));
            }
        }
        else
        {
            if(C.isEmpty()||B.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            for(int i=0;i<Math.max(C.size(),B.size());i++)
            {
                if(i>=B.size())
                { R.add(C.get(i));continue;}
                if(i>=C.size())
                {R.add(B.get(i));continue;}
                R.add((int)B.get(i)+(int)C.get(i));
            }
        }
        
        
        return arr;
    }
    public int[][] subtract(char poly1, char poly2)
    {
        int[][] arr={{0}};
        if((poly1=='A' && poly2=='B'))
        {
            if(A.isEmpty()||B.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            for(int i=0;i<Math.max(A.size(),B.size());i++)
            {
                if(i>=A.size())
                { R.add(0-(int)B.get(i));continue;}
                if(i>=B.size())
                {R.add(A.get(i));continue;}
                R.add((int)A.get(i)-(int)B.get(i));
            }
        }
        else if((poly1=='A' && poly2=='C'))
        {
            if(A.isEmpty()||C.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            for(int i=0;i<Math.max(A.size(),C.size());i++)
            {
                if(i>=A.size())
                { R.add(0-(int)C.get(i));continue;}
                if(i>=C.size())
                {R.add(A.get(i));continue;}
                R.add((int)A.get(i)-(int)C.get(i));
            }
        }
        else if((poly1=='B' && poly2=='A'))
        {
            if(A.isEmpty()||B.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            for(int i=0;i<Math.max(B.size(),A.size());i++)
            {
                if(i>=B.size())
                { R.add(0-(int)A.get(i));continue;}
                if(i>=A.size())
                {R.add(B.get(i));continue;}
                R.add((int)B.get(i)-(int)A.get(i));
            }
        }else if((poly1=='B' && poly2=='C'))
        {
            if(C.isEmpty()||B.isEmpty())
                 {System.out.println("Error");System.exit(0);}

            for(int i=0;i<Math.max(B.size(),C.size());i++)
            {
                if(i>=B.size())
                { R.add(0-(int)C.get(i));continue;}
                if(i>=C.size())
                {R.add(B.get(i));continue;}
                R.add((int)B.get(i)-(int)C.get(i));
            }
        }else if((poly1=='C' && poly2=='B'))
        {
            if(C.isEmpty()||B.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            for(int i=0;i<Math.max(C.size(),B.size());i++)
            {
                if(i>=C.size())
                { R.add(0-(int)B.get(i));continue;}
                if(i>=B.size())
                {R.add(C.get(i));continue;}
                R.add((int)C.get(i)-(int)B.get(i));
            }
        }else if((poly1=='C' && poly2=='A'))
        {
            if(A.isEmpty()||C.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            for(int i=0;i<Math.max(A.size(),C.size());i++)
            {
                if(i>=C.size())
                { R.add(0-(int)A.get(i));continue;}
                if(i>=A.size())
                {R.add(C.get(i));continue;}
                R.add((int)C.get(i)-(int)A.get(i));
            }
        }
        else
        {
        
                System.out.println("Error");
                System.exit(0);
        }
        return arr;
    }
    
    public int[][] multiply(char poly1, char poly2)
    {
        boolean ifirst=true;
        int[][] res=new int[2][1000];   
        int size=0;
        if((poly1=='A' && poly2=='B') || (poly1=='B' && poly2=='A')  )
        {
            if(A.isEmpty()||B.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            int[][] arr=new int[2][(Math.max(A.size(),B.size()))*(Math.min(A.size(),B.size()))];
            int c=0;
            for(int i=0;i<A.size();i++)
            {

            for(int j=0;j<B.size();j++)
            {
                arr[0][c]=(int)B.get(j)*(int)A.get(i);
                arr[1][c]=Math.max(B.size(),A.size())-1-i + Math.min(B.size(),A.size())-1-j;
                c++;
            }
            }
            for(int i=0;i<arr[0].length;i++)
            {
                for(int j=i+1;j<arr[0].length;j++)
                {
                    if(arr[1][j]==-1)
                    {continue;}
                    if(arr[1][j]==arr[1][i])
                    {arr[0][i]+=arr[0][j];arr[1][j]=-1;}
                }
            }
            for(int i=0;i<arr[0].length;i++)
            {
                if(arr[1][i]==-1)
                    {continue;}
                res[0][size]=arr[0][i];
                res[1][size]=arr[1][i];
                size++;
            }

        }
        else if((poly1=='A' && poly2=='C') || (poly1=='C' && poly2=='A') )
        {
            if(C.isEmpty()||A.isEmpty())
                 {System.out.println("Error");System.exit(0);}
           int[][] arr=new int[2][(Math.max(A.size(),C.size()))*(Math.min(A.size(),C.size()))];
            int c=0;
            for(int i=0;i<C.size();i++)
            {

            for(int j=0;j<A.size();j++)
            {
                arr[0][c]=(int)C.get(j)*(int)A.get(i);
                arr[1][c]=Math.max(C.size(),A.size())-1-i + Math.min(C.size(),A.size())-1-j;
                c++;
            }
            }
            for(int i=0;i<arr[0].length;i++)
            {
                for(int j=i+1;j<arr[0].length;j++)
                {
                    if(arr[1][j]==-1)
                    {continue;}
                    if(arr[1][j]==arr[1][i])
                    {arr[0][i]+=arr[0][j];arr[1][j]=-1;}
                }
            }
            for(int i=0;i<arr[0].length;i++)
            {
                if(arr[1][i]==-1)
                    {continue;}
                res[0][size]=arr[0][i];
                res[1][size]=arr[1][i];
                size++;
            }
                
        }
        else
        {
            if(C.isEmpty()||B.isEmpty())
                 {System.out.println("Error");System.exit(0);}
            int[][] arr=new int[2][(Math.max(C.size(),B.size()))*(Math.min(C.size(),B.size()))];
            int c=0;
            for(int i=0;i<C.size();i++)
            {

            for(int j=0;j<B.size();j++)
            {
                arr[0][c]=(int)B.get(j)*(int)C.get(i);
                arr[1][c]=Math.max(B.size(),C.size())-1-i + Math.min(B.size(),C.size())-1-j;
                c++;
            }
            }
            for(int i=0;i<arr[0].length;i++)
            {
                for(int j=i+1;j<arr[0].length;j++)
                {
                    if(arr[1][j]==-1)
                    {continue;}
                    if(arr[1][j]==arr[1][i])
                    {arr[0][i]+=arr[0][j];arr[1][j]=-1;}
                }
            }
            for(int i=0;i<arr[0].length;i++)
            {
                if(arr[1][i]==-1)
                    {continue;}
                res[0][size]=arr[0][i];
                res[1][size]=arr[1][i];
                size++;
            }
        }

               ifirst=true;
                for(int i=0;i<size;i++){
                    if((int)res[0][i]==0)
                    { continue;}
                    String sign="+";
                    if((int)res[0][i]<0)
                        sign="-";
                    if(!ifirst)
                    {
                        if((int)res[0][i]==1 && res[1][i]!=0){System.out.print(sign);}
                        else{System.out.print(sign +Math.abs((int)res[0][i]));}
                    }
                    else{
                        if((int)res[0][i]!=1 || ((int)res[0][i]==1 && res[1][i]==0))
                        {System.out.print((int)res[0][i]);}
                        ifirst=false;
                    }
                    if(res[1][i]==1)
                    {System.out.print("x");continue;}
                    else if(res[1][i]!=1 && res[1][i]!=0)
                    {System.out.print("x^"+(res[1][i]));continue;}
                }
   return res;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Main obj = new Main();
        char poly1;
        char poly2;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String operation = sc.nextLine();
            if(operation==null)
                break;
            switch (operation) {
                case ("set"):
                    poly1 = sc.nextLine().charAt(0);
                    String sin = sc.nextLine().replaceAll("\\[|\\]", "");
                    String[] s = sin.split(",");
                    int[][] arr = new int[2][s.length];
                    if (s.length == 1 && s[0].isEmpty()) {System.out.println("Error");System.exit(0);}
                    else {
                        for (int i = 0; i < s.length; ++i) {
                            arr[0][i] = (Integer.parseInt(s[i]));
                            arr[1][i]=s.length-1-i;
                        }
                    }
                    obj.setPolynomial(poly1,arr);
                    break;
                case ("print"):
                    poly1 = sc.nextLine().charAt(0);
                    obj.print(poly1);
                    break;
                case ("eval"):
                    poly1 = sc.nextLine().charAt(0);
                    float val = sc.nextFloat();
                    obj.evaluatePolynomial(poly1,val);
                    System.out.println((int)obj.evaluatePolynomial(poly1, val));
                    break;
     
                case ("mult"):
                    R.clear();
                    poly1 = sc.nextLine().charAt(0);
                    poly2 = sc.nextLine().charAt(0);
                    if ((poly1 == 'A' && poly2 == 'B') || (poly1 == 'B' && poly2 == 'A')) {
                        obj.multiply('A', 'B');
                    } else if ((poly1 == 'A' && poly2 == 'C') || (poly1 == 'C' && poly2 == 'A')) {
                        obj.multiply('A', 'C');
                    } else if ((poly1 == 'B' && poly2 == 'C') || (poly1 == 'C' && poly2 == 'B')){
                        obj.multiply('B', 'C');
                    }else
                    {
                        System.out.println("Error");System.exit(0);
                    }
            
                    break;
                case ("sub"):
                    poly1 = sc.nextLine().charAt(0);
                    poly2 = sc.nextLine().charAt(0);
                    R.clear();
                    if ((poly1 == 'A' && poly2 == 'B')) {
                        obj.subtract('A', 'B');
                    } else if ((poly1 == 'A' && poly2 == 'C')) {
                        obj.subtract('A', 'C');
                    } else if ((poly1 == 'B' && poly2 == 'A')) {
                        obj.subtract('B', 'A');
                    } else if ((poly1 == 'B' && poly2 == 'C')) {
                        obj.subtract('B', 'C');
                    } else if ((poly1 == 'C' && poly2 == 'B')) {
                        obj.subtract('C', 'B');
                    } else if ((poly1 == 'C' && poly2 == 'A')) {
                        obj.subtract('C', 'A');
                    }else
                    {
                        System.out.println("Error");System.exit(0);
                    }
                    obj.print('R');
                    break;
                case ("add"):
                    R.clear();
                    poly1 = sc.nextLine().charAt(0);
                    poly2 = sc.nextLine().charAt(0);
                    if ((poly1 == 'A' && poly2 == 'B') || (poly1 == 'B' && poly2 == 'A')) {
                        obj.add('A', 'B');
                    } else if ((poly1 == 'A' && poly2 == 'C') || (poly1 == 'C' && poly2 == 'A')) {
                        obj.add('A', 'C');
                    } else if ((poly1 == 'B' && poly2 == 'C') || (poly1 == 'C' && poly2 == 'B')){
                        obj.add('B', 'C');
                    }
                    else
                    {
                        System.out.println("Error");System.exit(0);
                    }
                    obj.print('R');
                    break;
                case ("clear"):
                    
                    poly1 = sc.nextLine().charAt(0);
                    if(poly1=='A')
                    {
                        if(A.isEmpty())
                        {
                        System.out.println("Error");
                        System.exit(0);
                        }
                    }
                    if(poly1=='B')
                    {
                        if(B.isEmpty())
                        {
                        System.out.println("Error");
                        System.exit(0);
                        }
                    }
                    if(poly1=='C')
                    {
                        if(C.isEmpty())
                        {
                        System.out.println("Error");
                        System.exit(0);
                        }
                    }
                    System.out.print("[]");
                    obj.clearPolynomial(poly1);
                    break;
                default:
                    System.out.println("Error");
                    System.exit(0);
            }
        }
    }
}
