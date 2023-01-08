package 并发的问题;

/**
 * @author Inspiration.DW
 * 线程原子性
 */
public class AtomicityDemo extends Thread {
    // 没有原子性
    private static int count;

    // 解决方式1
//    private volatile static int count;
    // 解决方式2
//    private static AtomicInteger count = new AtomicInteger(0);

    private static void addCount() {
        for (int i = 0; i < 1000; ++i) {
            count++;
//            count += 1;
//            count.incrementAndGet();
        }
        System.out.println(count);
    }


    @Override
    public void run() {
        addCount();
    }


    public static void main(String[] args) {
        int len = 10;
        // 10个线程
        AtomicityDemo[] arr = new AtomicityDemo[len];

        for (int i = 0; i < len; ++i) {
            // 初始化线程
            arr[i] = new AtomicityDemo();
        }

        for (int i = 0; i < len; i++) {
            // 开启线程
            arr[i].start();
        }
    }
}
