package aier.org.producerconsumermodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 23:18
 * 仓库类(缓冲区): 用来管理产品的生产,消费者存储
 */
public class Storage<T>{
    private int index = 0;
    private static final int MAX = 10;//最大容量
    private List<T> storages = new ArrayList<T>(MAX);//存储集合

    /**
     * 生产方法
     * @param item
     */
     public synchronized void produce(T item){
        //仓库已经满的情况下
        while(index >= MAX ){
            try {
                System.out.println("仓库已满!等待中......");
                this.wait();
                System.out.println("仓库不满了,继续生产......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         System.out.println("生产>>>" + item.toString());
         storages.add(item);
         index++;
         notify();//唤醒在此对象监视器上等待的单个线程，即消费者线程.
    }

     public synchronized T consume(){
        //仓库为空的情况下,进入等待状态.
        while (index <= 0){
            try {
                System.out.println("仓库空了,等待中......");
                this.wait();
                System.out.println("仓库不为空,开始消费......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        index --;
        T item = storages.remove(index);
        System.out.println("消费>>"+item.toString());
        notify();
        return item;
    }
}
