public class MergeSort {

    public static void main(String[] args) {

        int[] data = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Before sorting:");
        display(data);

        sort(data);

        System.out.println("\nAfter sorting:");
        display(data);
    }

    // Wrapper method for merge sort
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    // Recursive merge sort using indices instead of creating many arrays
    private static void mergeSort(int[] arr, int start, int end) {

        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;

        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, start, mid, end);
    }

    // Merge two sorted halves
    private static void merge(int[] arr, int start, int mid, int end) {

        int[] temp = new int[end - start + 1];

        int left = start;
        int right = mid + 1;
        int idx = 0;

        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                temp[idx++] = arr[left++];
            } else {
                temp[idx++] = arr[right++];
            }
        }

        while (left <= mid) {
            temp[idx++] = arr[left++];
        }

        while (right <= end) {
            temp[idx++] = arr[right++];
        }

        // Copy back into original array
        for (int i = 0; i < temp.length; i++) {
            arr[start + i] = temp[i];
        }
    }

    // Print array
    private static void display(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
    }
}
