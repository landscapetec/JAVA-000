import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static reactor.core.publisher.Mono.defer;

public class StudyMonoTest {
    private List<String> stringList = new ArrayList<>();
    private int index = 0;

    @Test
    public void testMono() {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");
        stringList.add("E");
        String result = monoMethod().block();
        System.out.println(result);
    }

    public Mono<String> monoMethod() {
        return Mono.defer(() -> {
            if (this.index < stringList.size()) {
                stringList.set(index, stringList.get(index) + "_" + (index++));
                System.out.println("Mono内部调用" + stringList.get(index - 1));
                return Mono.just(stringList.get(index - 1));
            } else {
                return Mono.empty();
            }
        });
    }
}
