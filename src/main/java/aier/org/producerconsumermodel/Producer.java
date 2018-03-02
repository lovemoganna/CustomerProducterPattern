package aier.org.producerconsumermodel;

/**
 * @author: ligang
 * date: 2018/3/2
 * time: 9:18
 */
public class Producer implements Runnable {
    private Storage<Phone> storage;

    public Producer(Storage<Phone> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                storage.produce(new Phone(i));
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
