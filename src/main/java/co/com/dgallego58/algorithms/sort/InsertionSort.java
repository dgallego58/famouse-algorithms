package co.com.dgallego58.algorithms.sort;

public class InsertionSort implements Sort{


    @Override
    public void sort(int[] inputArray) {
        int n = inputArray.length;
        for (int i = 1; i < n; i++) {
            int key = inputArray[i];
            int j = i - 1;
            while (j >= 0 && inputArray[j] > key) {
                inputArray[j + 1] = inputArray[j];
                j--;
            }
            inputArray[j + 1] = key;
        }
    }
}
