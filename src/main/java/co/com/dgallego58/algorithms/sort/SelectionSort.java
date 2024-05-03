package co.com.dgallego58.algorithms.sort;

public class SelectionSort implements Sort {
    @Override
    public int[] sort(int[] inputArray) {

        selectionSort(inputArray);
        return inputArray;
    }

    private void selectionSort(int[] inputArray) {
        int n = inputArray.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (inputArray[j] < inputArray[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = inputArray[minIndex];
            inputArray[minIndex] = inputArray[i];
            inputArray[i] = temp;
        }
    }
}
