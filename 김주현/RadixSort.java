import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class RadixSort {
    static final boolean ASC = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("숫자 개수 : ");
        int N = Integer.parseInt(br.readLine());
        System.out.print("숫자 입력(띄어쓰기로 구분)\n-> ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.print("정렬 방법 (1:오름차순, 2:내림차순) : ");
        boolean order = br.readLine().equals("1");

        sort(arr, order);
        System.out.println("\n< 정렬결과 >");
        printArr(arr);
    }

    private static void sort(int[] arr, boolean order) {
        Deque<Integer>[] buckets = new ArrayDeque[10];
        for (int i=0; i<10; i++) {
            buckets[i] = new ArrayDeque<>();
        }

        int maxDigit = findMaxDigit(arr);
        System.out.println();
        for (int i=0; i<maxDigit; i++) {
            putElementToBucket(buckets, arr, i+1);
            if (order == ASC) {
                putElementToArr(buckets, arr);
            } else {
                putElementToArrDesc(buckets, arr);
            }
            System.out.printf("%d번 수행 후 : %s\n", i+1, Arrays.toString(arr));
        }
    }

    private static void putElementToArr(Deque<Integer>[] buckets, int[] arr) {
        int count = 0;
        while (count < arr.length) {
            for (Deque<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[count++] = bucket.poll();
                }
            }
        }
    }

    private static void putElementToArrDesc(Deque<Integer>[] buckets, int[] arr) {
        int count = 0;
        while (count < arr.length) {
            for (int i=9; i>=0 ;i--) {
                while (!buckets[i].isEmpty()) {
                    arr[count++] = buckets[i].poll();
                }
            }
        }
    }

    private static void putElementToBucket(Deque<Integer>[] buckets, int[] arr, int exp) {
        for (int num : arr) {
            int digit = findNthDigit(num, exp);
            buckets[digit].add(num);
        }
    }

    private static int findNthDigit(int num, int exp) {
        num %= (int) Math.pow(10, exp);
        num /= (int) Math.pow(10, exp-1);
        return num;
    }

    private static int findMaxDigit(int[] arr) {
        int digit = -1;
        for (int num : arr) {
            digit = Math.max(digit, findLength(num));
        }
        return digit;
    }

    private static int findLength(int num) {
        return (int) Math.floor(Math.log10(num)) + 1;
    }

    private static void printArr(int[] arr) {
        for (int i = 0 ; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }
}
