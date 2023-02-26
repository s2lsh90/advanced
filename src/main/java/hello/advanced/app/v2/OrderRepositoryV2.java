package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static java.lang.Thread.sleep;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){
        TraceStatus status = null;
        try {
            status=trace.beginSync(traceId,"OrderRepositoryV2.save()");
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
