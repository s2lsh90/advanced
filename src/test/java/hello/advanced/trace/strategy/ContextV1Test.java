package hello.advanced.trace.strategy;


import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@Slf4j
public class ContextV1Test {
    void templateMethodV0(){
        logic1();
        logic2();
    }
    private void logic1(){
        long startTime = System.currentTimeMillis();
        //비즈니스  로직 실행
        log.info("비즈니스 로직1  실행");
        //비즈니스 로직 종료
        long endTime =System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}" , resultTime);
    }
    private void logic2(){
        long startTime = System.currentTimeMillis();
        //비즈니스  로직 실행
        log.info("비즈니스 로직1  실행");
        //비즈니스 로직 종료
        long endTime =System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}" , resultTime);
    }

    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        AbstractTemplate template2 = new SubClassLogic1();
        template1.execute();
    }

    @Test
    void templateMethodV2(){
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        log.info("클래스 이름1={}" , template1.getClass());
        template1.execute();
        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        log.info("클래스 이름2={}" , template2.getClass());
        template2.execute();
    }

    @Test
    void strategyV1(){
        StrategyLogic1 sl1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(sl1);
        contextV1.execute();

        StrategyLogic1 sl2 = new StrategyLogic1();
        ContextV1 contextV2 = new ContextV1(sl2);
        contextV2.execute();
    }

    @Test
    void strategyV2(){
        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override

            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        contextV1.execute();


        ContextV1 contextV2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        contextV2.execute();
    }
    @Test
    void strategyV4(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        contextV1.execute();


        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));

        contextV2.execute();
    }
}
