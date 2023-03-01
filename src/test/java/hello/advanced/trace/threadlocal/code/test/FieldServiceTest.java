package hello.advanced.trace.threadlocal.code.test;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");
        Runnable userA = ()->fieldService.logic("userA");
        Runnable userB = ()->fieldService.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-A");

        threadA.start();
        Thread.sleep(100); // 동시성 문제 x
        threadB.start();
        Thread.sleep(3000); // 메인 쓰레드 종료 대기

        log.info("main start");


    }
}
