import java.util.ArrayList;
import java.util.Scanner;

public class RadixSort {
    static int[] arr;
    static int size, max = Integer.MIN_VALUE;
    static ArrayList<Integer>[] lists = new ArrayList[10];
    public static void main(String[] args) {
        //자릿수 별 분류를 위한 임시 저장 리스트 배열 초기화
        for (int i = 0; i < 10; i++) {
            lists[i] = new ArrayList<>();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("정렬할 숫자의 개수를 입력하세요>>");
        size = sc.nextInt();

        arr = new int[size];
        System.out.println("정렬할 숫자들을 입력하세요>>");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
            //정렬할 숫자들 중 가장 큰 수를 찾기
            max = Math.max(max, arr[i]);
        }

        System.out.println("1: 오름차순 2: 내림차순 정렬 방식을 고르세요>>");
        int mode = sc.nextInt();

        radix_sort(mode);
    }
    static void radix_sort(int mode){
        int d = 0;
        //가장 큰수가 몇자리인지 찾기
        while(max > 0){
            max /= 10;
            d++;
        }

        //위에서 찾은 자리 수만큼 정렬 반복 => 가장 큰 수의 자리 수만큼 해야 모든 원소들의 자리 별 분류 보장
        for (int i = 0; i < d; i++) {
            int t = (int) Math.pow(10, i);
            for (int j = 0; j < size; j++) {
                //어떤 수 v의 1의자리: (v % 10^1) / 1
                // 10의 자리 : (v % 10^2) / 10
                // 100의 자리 : (v % 10^3) / 10^2
                int value = (arr[j] % (10*t)) / t;
                //나온 값(0~9)대로 리스트에 저장 
                lists[value].add(arr[j]);
            }
            if(mode == 1){
                moveToArrayAsc();
            }else{
                moveToArrayDesc();
            }
        }

        System.out.print("정렬 결과: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    static void moveToArrayAsc(){
        int idx = 0;
        //오름차순 정렬을 위해서는 0~9까지의 임시 저장 리스트를 0부터 배열로 다시 옮겨준다
        for (int i = 0; i < 10; i++) {
            int listSize = lists[i].size();
            for (int j = 0; j < listSize; j++) {
                arr[idx++] = lists[i].get(j);
            }
            //임시 리스트 다 옮겼으면 저장 값 날리기
            lists[i].clear();
        }
    }
    static void moveToArrayDesc(){
        int idx = 0;
        //오름차순 정렬을 위해서는 0~9까지의 임시 저장 리스트를 9부터 배열로
        for (int i = 9; i >= 0; i--) {
            int listSize = lists[i].size();
            for (int j = 0; j < listSize; j++) {
                arr[idx++] = lists[i].get(j);
            }
            lists[i].clear();
        }
    }
}
