package lesson4;

public class Main {
    static Object mon = new Object();
    static volatile  int current = 1;
    static final int num = 5;

    public static void main(String[] args) {
        new Thread(()->{
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (current != 1) {
                            mon.wait();
                        }
                        System.out.print("A");
                        current = 2;
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (current != 2) {
                            mon.wait();
                        }
                        System.out.print("B");
                        current = 3;
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (current != 3) {
                            mon.wait();
                        }
                        System.out.print("C ");
                        current = 1;
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
