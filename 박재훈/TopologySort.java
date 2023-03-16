import java.util.*;

public class TopologySort {
    static int[] arr, degree;
    static int size, conn;
    static ArrayList<Integer>[] lists;
    static HashMap<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("정렬할 숫자의 개수를 입력하세요>>");
        size = sc.nextInt();

        arr = new int[size];
        degree = new int[size];
        lists = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            lists[i] = new ArrayList<>();
        }

        System.out.println("정렬할 숫자들을 입력하세요>>");
        for (int i = 0; i < size; i++) {
            int val = sc.nextInt();
            //각 수들에 0부터 size-1까지 번호 부여, 이 번호를 degree(차수정보)배열과 lists(연결정보)리스트의 인덱스로 활용
            map.put(val, i);
            arr[i] = val;
        }

        System.out.println("순서 정보 개수를 입력하세요>>");
        conn = sc.nextInt();

        System.out.println(Arrays.toString(arr));
        System.out.printf("%d개의 순서 정보들을 입력하세요>> ex) 1 2 는 1이 2보다 앞에 나와야 함 의미\n", conn);
        for (int i = 0; i < conn; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            //s에서 출발해서 e로 도착하는 연결 정보 입력
            lists[map.get(s)].add(e);
            //e의 차수 증가
            degree[map.get(e)]++;
        }

        topology_sort();
        System.out.print("정렬 결과>> ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    static void topology_sort() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            //차수가 0인 인덱스를 큐에 저장
            if(degree[i] == 0){
                q.add(i);
            }
        }

        int idx = 0;
        //모든 수의 차수가 0이 될때까지 
        while(!q.isEmpty()){
            int s = q.poll();
            for (int i : map.keySet()) {
                //큐에서 뽑힌(차수가 0이 된) 인덱스에 해당하는 수가 배열에 차례로 저장될 것임
                int value = map.get(i);
                if(value == s){
                    arr[idx++] = i;
                    break;
                }
            }

            int listSize = lists[s].size();
            for (int i = 0; i < listSize; i++) {
                //차수 0인 곳에서 연결된 애들 찾고 차수를 1씩 낮춰줌
                int e = lists[s].get(i);
                //낮췄는데 차수 0 되었으면 큐에 넣기
                if(--degree[map.get(e)] == 0){
                    q.add(map.get(e));
                }
            }
        }
    }
}
