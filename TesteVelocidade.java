import java.util.Random;

public class TesteVelocidade {
    public static int[] generateRandomArray(int size, int seed) {
        Random random = new Random(seed);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 2);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};
        int[] seeds = {42, 123, 256, 512, 1024};

        for (int size : sizes) {
            System.out.println("Array size: " + size);
            for (int seed : seeds) {
                int[] arr = generateRandomArray(size, seed);

                Algoritmos.SortResult resultSelection = Algoritmos.selectionSort(arr.clone());
                System.out.println("Selection Sort -> Time: " + resultSelection.time +
                        "ns, Iterations: " + resultSelection.iterations +
                        ", Swaps: " + resultSelection.swaps);

                Algoritmos.SortResult resultQuick = Algoritmos.quickSort(arr.clone());
                System.out.println("Quick Sort -> Time: " + resultQuick.time +
                        "ns, Iterations: " + resultQuick.iterations +
                        ", Swaps: " + resultQuick.swaps);

                Algoritmos.SortResult resultMerge = Algoritmos.mergeSort(arr.clone());
                System.out.println("Merge Sort -> Time: " + resultMerge.time +
                        "ns, Iterations: " + resultMerge.iterations +
                        ", Swaps: " + resultMerge.swaps);

                Algoritmos.SortResult resultCounting = Algoritmos.countingSort(arr.clone());
                System.out.println("Counting Sort -> Time: " + resultCounting.time +
                        "ns, Iterations: " + resultCounting.iterations +
                        ", Swaps: " + resultCounting.swaps);
            }
        }
    }
}
