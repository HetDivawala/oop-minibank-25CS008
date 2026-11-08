package minibank;
public class TransactionBuffer {
    private String[] items=new String[5]; private int count=0;
    public synchronized void add(String item) throws InterruptedException {while(count==items.length)wait();items[count++]=item;notify();}
    public synchronized String remove() throws InterruptedException {while(count==0)wait();String s=items[0];for(int i=1;i<count;i++)items[i-1]=items[i];count--;notify();return s;}
}
