import  java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string");
        String str = sc.nextLine();

        boolean palindrome = true;
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if((str.charAt(left)) != (str.charAt(right))) {
                palindrome = false;
                break;
            }
            left++;
            right--;
        }
        if(palindrome){System.out.println("Is palindrome: " + palindrome);}
        else{System.out.println("Is not palindrome: " + palindrome);}
    }
}
