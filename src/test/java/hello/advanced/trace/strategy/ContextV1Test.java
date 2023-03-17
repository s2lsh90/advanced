package hello.advanced.trace.strategy;


import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@Slf4j
public class ContextV1Test {

     /*
      contextV1의 경우 필드에 전략을 주입 받는 형식으로 선 조립 후 실행
      context 실행 시점에는 이미 조립이 끝나서 전략 신경 쓰지 않아도 된다.
     */

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
