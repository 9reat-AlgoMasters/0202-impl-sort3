import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RadixSort {
    static int N, R;
    static final int bucketSize = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("입력할 숫자의 개수(N)를 입력하세요. >> ");
        N = Integer.parseInt(br.readLine());
        System.out.print("입력할 숫자의 자리수(R)를 입력하세요. >> ");
        R = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        System.out.print("R자리 숫자를 N개 입력하세요(공백으로 구분). >> ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        radix_Sort(arr);

        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

    public static void radix_Sort(int[] arr) {
        //bucket 초기화
        Queue<Integer>[] bucket = new LinkedList[bucketSize];
        for (int i = 0; i < bucketSize; ++i) {
            bucket[i] = new LinkedList<>();
        }

        int radix = 1;

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < N; ++j) {
                bucket[(arr[j] / radix) % 10].add(arr[j]);
            }

            for (int j = 0, k = 0; j < bucketSize; ++j) {
                while (!bucket[j].isEmpty()) {
                    arr[k++] = bucket[j].poll();
                }
            }
            radix *= 10;
        }
    }
}