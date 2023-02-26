package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static java.lang.Thread.sleep;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(String itemId){
        TraceStatus status = null;
        try {
            status=trace.begin("OrderRepositoryV1.save()");
            //저장 로직
            if (itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");

            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            trace.end(status);

        }catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }
}
