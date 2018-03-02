生产者和消费者模式
## 逻辑以及逻辑图示

1. 生产者只在仓库未满时进行生产,仓库满时生产者进程被阻塞.

2. 消费者只在仓库非空时进行消费,仓库为空时消费者进程被阻塞.

3. 当消费者发现仓库为空时会通知生产者生产.

4. 当生产者发现仓库满时会通知消费者消费. 

图示:

![](http://upload-images.jianshu.io/upload_images/7505161-8af4cd967f790985.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 关键点

共享内存中的两个同步方法,及同步方法wait()方法的调用.

- synchronized 保证了对象只能被一个线程占用。
- wait 保证了当线程在等待过程中释放锁，使得其他对象有机会获得锁.

>Java中有一个同步模型-监视器，负责管理线程对对象中的同步方法的访问，它的原理是：赋予该对象唯一一把'钥匙'，当多个线程进入对象，只有取得该对象钥匙的线程才可以访问同步方法，其它线程在该对象中等待，直到该线程用wait()方法放弃这把钥匙，其它等待的线程抢占该钥匙，抢占到钥匙的线程后才可得以执行，而没有取得钥匙的线程仍被阻塞在该对象中等待。 总而言之，synchonized使得只有一个线程能进入临界代码区。
 
> 由于wait( )所等待的对象必须先锁住，因此，它只能用在同步化程序段或者同步化方法内，否则，会抛出异常java.lang.IllegalMonitorStateException.

## UML类图

![](http://upload-images.jianshu.io/upload_images/7505161-24f1e707f19011dc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

详细代码见工程.

## debug调试记录

### debug 界面
```androiddatabinding
如下是在IDEA中启动Debug模式，进入断点后的界面，我这里是Windows，可能和Mac的图标等会有些不一样。就简单说下图中标注的8个地方：

　　① 以Debug模式启动服务，左边的一个按钮则是以Run模式启动。在开发中，我一般会直接启动Debug模式，方便随时调试代码。

　　② 断点：在左边行号栏单击左键，或者快捷键Ctrl+F8 打上/取消断点，断点行的颜色可自己去设置。

　　③ Debug窗口：访问请求到达第一个断点后，会自动激活Debug窗口。如果没有自动激活，可以去设置里设置，如图1.2。

　　④ 调试按钮：一共有8个按钮，调试的主要功能就对应着这几个按钮，鼠标悬停在按钮上可以查看对应的快捷键。在菜单栏Run里可以找到同样的对应的功能，如图1.4。

　　⑤ 服务按钮：可以在这里关闭/启动服务，设置断点等。

　　⑥ 方法调用栈：这里显示了该线程调试所经过的所有方法，勾选右上角的[Show All Frames]按钮，就不会显示其它类库的方法了，否则这里会有一大堆的方法。

　　⑦ Variables：在变量区可以查看当前断点之前的当前方法内的变量。

　　⑧ Watches：查看变量，可以将Variables区中的变量拖到Watches中查看 

```
### 快捷键

![](http://upload-images.jianshu.io/upload_images/7505161-b931580e941930a6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/7505161-ebd801e305e74d0a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

如下解释:
```androiddatabinding
1. Show Execution Point (Alt + F10)：如果你的光标在其它行或其它页面，点击这个按钮可跳转到当前代码执行的行。

2. > Step Over (F6)：步过，一行一行地往下走，如果这一行上有方法不会进入方法。

3. > Step Into (F5)：步入，如果当前行有方法，可以进入方法内部，一般用于进入自定义方法内，不会进入官方类库的方法，如第25行的put方法。

4. > Force Step Into (Alt + Shift + F7)：强制步入，能进入任何方法，查看底层源码的时候可以用这个进入官方类库的方法。

5. > Step Out (F7)：步出，从步入的方法内退出到方法调用处，此时方法已执行完毕，只是还没有完成赋值。

6. > Drop Frame (默认无)：回退断点，后面章节详细说明。

7. > Run to Cursor (ctrl + r)：运行到光标处，你可以将光标定位到你需要查看的那一行，然后使用这个功能，代码会运行至光标行，而不需要打断点。

8. > Evaluate Expression (ctrl + u)：计算表达式，后面章节详细说明。
```

图示:

![](http://upload-images.jianshu.io/upload_images/7505161-107cb15205b02348.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 左侧的图示

![](http://upload-images.jianshu.io/upload_images/7505161-72c1358515e78717.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 执行debug测试

加断点的位置,我们最好狠一点.
1. Client.java

![](http://upload-images.jianshu.io/upload_images/7505161-c79792a73def86e5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2. Producer.java

在构造方法和Run方法前面加断点

![](http://upload-images.jianshu.io/upload_images/7505161-ace74ad4d7d5c389.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3. Consumer.java

在构造方法和Run方法前面加断点
4. Phone.java

在构造方法前面假的断点

5. Storage.java

在两个方法前面加断点

![](http://upload-images.jianshu.io/upload_images/7505161-3e6f1b313ce7c54c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 执行测试展示

1.一开始进入时候的参数展示

![](http://upload-images.jianshu.io/upload_images/7505161-326d5f90f80e22e8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

先分别进入Producer.java和Consumer.java的构造方法初始化对象.

从下面我们就可以直观的看出来:

![](http://upload-images.jianshu.io/upload_images/7505161-3de4ff3d947f4161.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2.生产者生产商品

![](http://upload-images.jianshu.io/upload_images/7505161-918619aee0fff5a2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

进入特定商品的构造方法

![](http://upload-images.jianshu.io/upload_images/7505161-7758504676b94cdb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

进入生产方法判断是否符合条件,很显然不符合条件.

![](http://upload-images.jianshu.io/upload_images/7505161-5c6489e0b6d456fa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

就执行下面的代码.

![](http://upload-images.jianshu.io/upload_images/7505161-c298d160f144680f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在上面的图示我们还未执行`notify()`方法,所以执行的结果暂时是:

![](http://upload-images.jianshu.io/upload_images/7505161-26e7da3b2bf34a84.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

执行完`notify()`方法我们快速跳过中间的一次执行,测试结果如下:

竞争具有随机性.

![](http://upload-images.jianshu.io/upload_images/7505161-8861f43f8ca84de8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3.最终的测试结果
![](http://upload-images.jianshu.io/upload_images/7505161-eaa16210cd2f310b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

wait()的作用就是释放锁,同时本线程进入阻塞队列.

[IDEA 下使用debug](https://www.linuxidc.com/Linux/2017-09/146772.htm)