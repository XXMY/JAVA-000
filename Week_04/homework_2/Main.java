import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caifw
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
//        main.method1();
//        main.method2();
//        main.method3();
//        main.method4();
        // CyclicBarrier、Lock 同理
    }

    public void method1() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> result =  executorService.submit(Main::getValue);

        System.out.println(result.get());
        executorService.shutdown();
    }

    public void method2() throws Exception {
        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> {
            result.set(getValue());
        });

        thread.start();
        thread.join();

        System.out.println(result.get());
    }

    public void method3() throws Exception {
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            synchronized (this){
                result.set(getValue());
                notifyAll();
            }
        }).start();

        synchronized (this){
            wait();
        }
        System.out.println(result.get());
    }

    public static void method4() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            result.set(getValue());
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();
        System.out.println(result);
    }

    public static int getValue(){
        return 1;
    }
}
