package lesson5.race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public abstract class Stage {
    protected int length;
    protected String description;
    Semaphore semaphore;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);

    public static class Tunnel extends Stage {
        public Tunnel(Semaphore semaphore) {
            this.semaphore = semaphore;
            this.length = 80;
            this.description = "Тоннель " + length + " метров";
        }

        @Override
        public void go(Car c) {
            try {
                try {
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    semaphore.acquire();
                    System.out.println(c.getName() + " начал этап: " + description);
                    Thread.sleep(length / c.getSpeed() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(c.getName() + " закончил этап: " + description);
                    semaphore.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
