package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalServcie {

    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("์ ์ฅ name = {} -> name store={}", name ,nameStore.get());
        nameStore.set(name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("์กฐํ nameStore = {}" , nameStore.get());
        return nameStore.get()  ;
    }
}
