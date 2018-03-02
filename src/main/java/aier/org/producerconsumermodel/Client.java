package aier.org.producerconsumermodel;

/**
 * @author: ligang
 * date: 2018/3/2
 * time: 9:32
 */
public class Client {
    public static void main(String[] args) {
        Storage<Phone> storage = new Storage<>();

        new Thread(new Producer(storage)).start();
        new Thread(new Consumer(storage)).start();
    }
}
