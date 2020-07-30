package lesson5;

import lesson5.race.Car;
import lesson5.race.Race;
import lesson5.race.Road;
import lesson5.race.Stage;

import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Semaphore semaphore = new Semaphore(2);

        Race race = new Race(new Road(60), new Stage.Tunnel(semaphore), new Road(40));

        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT + 1);

        ExecutorService service = Executors.newFixedThreadPool(CARS_COUNT);

        for (int i = 0; i < CARS_COUNT; i++) {
            service.execute(new Car(cyclicBarrier, race, 20 + (int) (Math.random() * 10)));
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        service.shutdown();
    }
}

