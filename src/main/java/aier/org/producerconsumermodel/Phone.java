package aier.org.producerconsumermodel;

/**
 * @author: ligang
 * date: 2018/3/2
 * time: 9:18
 */
public class Phone {
    private int id;// 手机编号

    public Phone(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "手机编号：" + id;
    }
}

