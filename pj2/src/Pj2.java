import java.util.*;
import pj2.Login;

public class Pj2{

    public static void main(String args[]) {

        // instance of Runnable implementation for threads
        ParallelTask task = new ParallelTask();
     
     
        // This will only create instance of Thread class
        // it will not start until you call start() method
        Thread T1 = new Thread(task);
        Thread T2 = new Thread(task);
     
        // Starting T1 and T2 thread
        T1.start();
        T2.start();  
   
    }
 
}

/*
 * Always use Runnable to put code which you want to execute parallel
 * Using multiple threads.
 */
class ParallelTask implements Runnable{

    @Override
    public void run() {
       new Login();
     
    }
 
}
