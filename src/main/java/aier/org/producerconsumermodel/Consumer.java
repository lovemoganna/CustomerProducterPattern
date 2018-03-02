package aier.org.producerconsumermodel;

/**
 * @author: ligang
 * date: 2018/3/2
 * time: 9:25
 */
public class Consumer implements Runnable{
    private Storage<Phone> storage;

    public Consumer(Storage<Phone> storage) {
        this.storage = storage;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            storage.consume();
            try {
                Thread.sleep(100);//每隔10s消耗一个.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
