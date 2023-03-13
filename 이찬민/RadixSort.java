import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class RadixSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("갯수 입력: ");
        int N = Integer.parseInt(br.readLine());
        System.out.println("숫자들을 입력해주세요 (띄워쓰기 사용)>> ");

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println("원하는 정렬을 선택하세요>>" );
        StringBuilder sb = new StringBuilder();
        sb.append("1. 오름차순 정렬\n");
        sb.append("2. 내림차순 정렬\n");
        sb.append("3. 종료\n");
        System.out.println(sb.toString());

        int select = Integer.parseInt(br.readLine());

        if (select == 3) {
            System.exit(0);
        }
        sb.setLength(0);

        switch (select) {
            case 1:
                System.out.println("오름차순 정렬");
                radixSort(arr, select);

                print(arr);

                break;
            case 2:
                System.out.println("내림차순 정렬");
                radixSort(arr, select);

                print(arr);

                break;
        }
    }
    private static void radixSort(int[] arr, int order) {
        Deque<Integer>[] radixList = new ArrayDeque[10];
        for (int i = 0; i < 10; i++) {
            radixList[i] = new ArrayDeque<>();
        }

        int maxDigit = findMaxDigit(arr); // 확인할 자리수

        for (int i = 1; i <= maxDigit; i++) {
            // 데이터 수만큼 확인
            for (int j = 0; j < arr.length; j++) {
                // 낮은 자리수부터 확인
                int digitNum = getDigit(arr[j], i);

                radixList[digitNum].add(arr[j]);
            }

            // 큐에서 순서대로 꺼내서 다시 배열에 놓기
            if (order == 1) {
                makeAsc(radixList, arr, 0);
            }
            if (order == 2) {
                makeDesc(radixList, arr, 0);
            }

        }

    }

    private static int getDigit(int num, int pos) {
        num = (num % (int)Math.pow(10, pos)) / (int)Math.pow(10, pos-1);
        return num;
    }

    static void makeAsc(Deque<Integer>[] radixList, int[] arr, int idx) {
        for (int j = 0; j < 10; j++) {
            while (!radixList[j].isEmpty()) {
                arr[idx] = radixList[j].poll();
                idx++;
            }
        }
    }

    static void makeDesc(Deque<Integer>[] radixList, int[] arr, int idx) {
        for (int j = 9; j >= 0; j--) {
            while (!radixList[j].isEmpty()) {
                arr[idx] = radixList[j].poll();
                idx++;
            }
        }
    }

    static int findMaxDigit(int[] arr) {
        int digit = Integer.MIN_VALUE;
        for (int i : arr) {
            digit = Math.max(digit, (int) Math.log10(i) + 1);
        }
        return digit;
    }


    static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
}
