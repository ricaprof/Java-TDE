import java.util.Arrays;

public class Algoritmos {
    // Classe para armazenar o resultado de cada ordenação
    static class SortResult {
        int iterations;
        int swaps;
        long time;

        public SortResult(int iterations, int swaps, long time) {
            this.iterations = iterations;
            this.swaps = swaps;
            this.time = time;
        }
    }

    // Algoritmo de Selection Sort
    public static SortResult selectionSort(int[] arr) {
        int iterations = 0;
        int swaps = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                iterations++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }
        }

        long endTime = System.nanoTime();
        return new SortResult(iterations, swaps, endTime - startTime);
    }

    // Algoritmo de Quick Sort
    public static SortResult quickSort(int[] arr) {
        int[] iterations = {0};
        int[] swaps = {0};
        long startTime = System.nanoTime();

        quickSortRecursive(arr, 0, arr.length - 1, iterations, swaps);

        long endTime = System.nanoTime();
        return new SortResult(iterations[0], swaps[0], endTime - startTime);
    }

    private static void quickSortRecursive(int[] arr, int low, int high, int[] iterations, int[] swaps) {
        if (low < high) {
            int pi = partition(arr, low, high, iterations, swaps);
            quickSortRecursive(arr, low, pi - 1, iterations, swaps);
            quickSortRecursive(arr, pi + 1, high, iterations, swaps);
        }
    }

    private static int partition(int[] arr, int low, int high, int[] iterations, int[] swaps) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            iterations[0]++;
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swaps[0]++;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swaps[0]++;
        return i + 1;
    }

    // Algoritmo de Merge Sort
    public static SortResult mergeSort(int[] arr) {
        int[] iterations = {0};
        int[] swaps = {0};
        long startTime = System.nanoTime();

        mergeSortRecursive(arr, iterations, swaps);

        long endTime = System.nanoTime();
        return new SortResult(iterations[0], swaps[0], endTime - startTime);
    }

    private static void mergeSortRecursive(int[] arr, int[] iterations, int[] swaps) {
        if (arr.length <= 1) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSortRecursive(left, iterations, swaps);
        mergeSortRecursive(right, iterations, swaps);

        merge(arr, left, right, iterations, swaps);
    }

    private static void merge(int[] arr, int[] left, int[] right, int[] iterations, int[] swaps) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            iterations[0]++;
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                swaps[0]++;
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Algoritmo de Counting Sort
    public static SortResult countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
        int[] count = new int[max + 1];
        int iterations = 0;
        int swaps = 0;
        long startTime = System.nanoTime();

        for (int num : arr) {
            iterations++;
            count[num]++;
        }

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
                swaps++;
            }
        }

        long endTime = System.nanoTime();
        return new SortResult(iterations, swaps, endTime - startTime);
    }
}
