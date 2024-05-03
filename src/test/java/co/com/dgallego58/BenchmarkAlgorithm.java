package co.com.dgallego58;

import co.com.dgallego58.algorithms.sort.BubbleSort;
import co.com.dgallego58.algorithms.sort.InsertionSort;
import co.com.dgallego58.algorithms.sort.MergeSort;
import co.com.dgallego58.algorithms.sort.QuickSort;
import co.com.dgallego58.algorithms.sort.SelectionSort;
import co.com.dgallego58.algorithms.sort.Sort;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class BenchmarkAlgorithm {


    @Benchmark
    public void mergeSort(BenchMarkState state, Blackhole bh) {
        var result = state.algorithmSelector.get(BenchMarkState.AlgType.MERGE_SORT).sort(state.nums);
        bh.consume(result);

    }


    @Benchmark
    public void bubbleSort(BenchMarkState state, Blackhole bh) {
        var result = state.algorithmSelector.get(BenchMarkState.AlgType.BUBBLE_SORT).sort(state.nums);
        bh.consume(result);

    }

    @Benchmark
    public void insertionSort(BenchMarkState state, Blackhole bh) {
        var result = state.algorithmSelector.get(BenchMarkState.AlgType.INSERTION_SORT).sort(state.nums);
        bh.consume(result);

    }

    @Benchmark
    public void quickSort(BenchMarkState state, Blackhole bh) {
        var result = state.algorithmSelector.get(BenchMarkState.AlgType.QUICK_SORT).sort(state.nums);
        bh.consume(result);

    }


    @Benchmark
    public void selectionSort(BenchMarkState state, Blackhole bh) {
        var result = state.algorithmSelector
                .get(BenchMarkState.AlgType.SELECTION_SORT)
                .sort(state.nums);
        bh.consume(result);
    }

    @State(Scope.Benchmark)
    public static class BenchMarkState {

        @Param({"100", "1000", "10000"})
        public int ONE_THOUSAND;
        private int[] nums;
        private Map<AlgType, Sort> algorithmSelector;

        @Setup
        public void prepare() {
            this.algorithmSelector = new EnumMap<>(AlgType.class);
            fillAlgorithmSelector();
            fillNums();
        }

        private void fillNums() {
            SecureRandom secureRandom = new SecureRandom();
            this.nums = new int[ONE_THOUSAND];
            for (int i = 0; i < ONE_THOUSAND; i++) {
                nums[i] = secureRandom.nextInt(ONE_THOUSAND);
            }
        }

        private void fillAlgorithmSelector() {
            this.algorithmSelector.putAll(
                    Map.of(
                            AlgType.MERGE_SORT, new MergeSort(),
                            AlgType.BUBBLE_SORT, new BubbleSort(),
                            AlgType.INSERTION_SORT, new InsertionSort(),
                            AlgType.QUICK_SORT, new QuickSort(),
                            AlgType.SELECTION_SORT, new SelectionSort()

                    )
            );
        }

        public enum AlgType {
            MERGE_SORT,
            BUBBLE_SORT,
            INSERTION_SORT,
            QUICK_SORT,
            SELECTION_SORT
        }

    }


}
