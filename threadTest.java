import java.util.* ;

class starter{

    public static void main( String args[] ) {
        System.out.println("Hello World");
        ArrayList<Queue<String>> queues = new ArrayList<Queue<String>>();
        Queue<String> outputQueue = new LinkedList<String>();
        queues.add(new LinkedList<String>());
        queues.add(new LinkedList<String>());
        inputReader testThread = new inputReader(queues, "test");
        thingDoer thing1 = new thingDoer(queues.get(0), outputQueue, "doer one");
        thingDoer thing2 = new thingDoer(queues.get(1), outputQueue, "doer two");
        testThread.start();
        thing1.start();
        thing2.start();
        while(true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Thread ");
            }
        }
    }

}

class thingDoer extends Thread {
    Queue<String> inQueue;
    Queue<String> outQueue;
    String threadName;
    Thread t;

    thingDoer(Queue<String> in, Queue<String> out, String name) {
        inQueue = in;
        outQueue = out;
        threadName = name;
    }

    public void start () {
        System.out.println("Starting " +  this.threadName );
        if (t == null) {
            t = new Thread (this, this.threadName);
            t.start ();
            
        }
    }


    public void run() {
        while(true) {
            
            while(inQueue.size() > 0) {
                System.out.println(threadName);
                System.out.println(inQueue.poll() + " " + threadName);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Thread ");
            }
        }
    }


}

class inputReader extends Thread {
    private Thread t;
    ArrayList<Queue<String>> outQueues;
    String threadName;

    inputReader(ArrayList<Queue<String>> queues, String name) {
        this.outQueues = queues;
        this.threadName = name;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        String number;
        while(true) {
            number = input.next();
            System.out.println("input!");
            if (number.charAt(0) > 'm') {
                outQueues.get(0).add(number);
            } else {
                outQueues.get(1).add(number);
            }
        }
    }

    public void start () {
        System.out.println("Starting " +  this.threadName );
        if (t == null) {
            t = new Thread (this, this.threadName);
            t.start ();
        }
   }
}

class outputWriter extends Thread {
    Queue<String> inQueue; 
}