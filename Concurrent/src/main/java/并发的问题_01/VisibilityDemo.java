package 并发的问题;

/**
 * @author Inspiration.DW
 * 线程可见性
 */
public class VisibilityDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();

        Thread.sleep(3000);

        threadVolatileDemo.setFlag(false);

        Thread.sleep(1000);
        System.out.println("flag = [ " + threadVolatileDemo.flag + " ]");
    }

    static class ThreadVolatileDemo extends Thread {
        public volatile boolean flag = true;
//        public boolean flag = true;

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ", == 开始执行 ==");
            while (true) {
                // 决定是否结束循环
                if (isFlag() == false) {
                    System.out.println("执行线程退出");
                    break;
                }
            }
            System.out.println(name + ", == 线程停止 ==");
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }

}
