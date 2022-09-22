import com.github.pagehelper.PageHelper;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author hjy
 **/
public class Test {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        // Callable 方式创建线程
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        Integer integer = futureTask.get();

        threadLocal.set("hjy");

        new Thread().start();
        new Thread().run();

        System.out.println(Test.class.getClassLoader());
        System.out.println(Test.class.getClassLoader().getParent());
        System.out.println(Test.class.getClassLoader().getParent().getParent());

        PageHelper.startPage(1, 10);
    }
}

