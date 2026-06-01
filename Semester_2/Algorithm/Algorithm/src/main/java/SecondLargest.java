public class SecondLargest {
    public static void main(String[] args) {

        int arr[] = {12, 35, 5, 50, 35, 40};
        int largest = arr[0];
        int secondLargest = arr[0];

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            }else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        System.out.println(secondLargest);
    }
}
