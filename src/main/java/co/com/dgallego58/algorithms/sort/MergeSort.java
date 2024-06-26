package co.com.dgallego58.algorithms.sort;

import java.security.SecureRandom;
import java.util.Arrays;

public class MergeSort implements Sort {

    @Override
    public int[] sort(int[] inputArray) {
        mergeSort(inputArray);
        return inputArray;

    }

    public static void main(String[] args) {

        int size = 10_000_000;
        SecureRandom secureRandom = new SecureRandom();
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i]=secureRandom.nextInt(size);
        }
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(ints);
    }

    public void mergeSort(int[] inputArray) {
        int inputLength = inputArray.length;

        if (inputLength < 2) {
            return;
        }
        int mid = inputLength / 2;

        // serve the memory space for the two arrays from the split
        int[] leftArray = new int[mid];
        int[] rightArray = new int[inputLength - mid];

        // fill left half of the array
        for (int i = 0; i < mid; i++) {
            leftArray[i] = inputArray[i];
        }
        // fill right half of the array
        for (int i = mid; i < inputLength; i++) {
            rightArray[i - mid] = inputArray[i];
        }

        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(inputArray, leftArray, rightArray);
    }

    private void merge(int[] inputArray, int[] leftArray, int[] rightArray) {
        int leftLength = leftArray.length;
        int rightLength = rightArray.length;
        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength) {
            if (leftArray[i] <= rightArray[j]) {
                inputArray[k] = leftArray[i];
                i++;
            } else {
                inputArray[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < leftLength) {
            inputArray[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightLength) {
            inputArray[k] = rightArray[j];
            j++;
            k++;
        }
    }


}
