package 并发的问题;

/**
 * @author Inspiration.DW
 * 指令重排
 */
public class ReorderDemo {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            reorder();
        }
    }

    static void reorder() throws InterruptedException{
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                /*操作1和操作2的执行先后关系无影响*/
                a = 1; // 操作1
                x = b; // 操作2
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                /*操作3和操作4的执行先后关系无影响*/
                b = 1; // 操作3
                y = a; // 操作4
            }
        });
        /*线程1和线程2整体考虑，线程内部的执行的先后关系就对结果有影响了*/

        t1.start();
        t2.start();

        // join方法是把其他线程加入当前线程，等加入线程执行完之后才会执行当前线程。
        t1.join();
        t2.join();

        // t1和t2执行完了，才会执行接下来的代码

        // 如果想要出现 x == 0 && y == 0，那么指令重排时，
        // 将操作2优化到操作1前面，操作4优化到操作3前面了。
        if (x == 0  && y == 0) {
            System.out.println("( " + x + "," + y + " )");
        }

        x = 0;
        y = 0;
        a = 0;
        b = 0;
    }
}
