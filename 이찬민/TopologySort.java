import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologySort {
    static int[] degree;
    static int size;
    static int connection;
    static List<Integer>[] adj;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("갯수 입력: ");
        size = Integer.parseInt(br.readLine());
        degree = new int[size];
        adj = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new ArrayList<>();
        }

        System.out.println("순서 정보 갯수 입력: ");
        connection = Integer.parseInt(br.readLine());

        System.out.println("영화 입력( , 로 구분해주세요): ");
        String[] arr = br.readLine().split(",");
        System.out.println(Arrays.toString(arr));

        map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            // 노드번호 넣어둠
            map.put(arr[i].trim(), i);
        }

        System.out.println("연결 정보 입력: ");
        while (connection != 0) {
            String[] edge = br.readLine().split(",");
            // 간선 연결;
            adj[map.get(edge[0].trim())].add(map.get(edge[1].trim()));
            // indegree 증가
            degree[map.get(edge[1].trim())]++;
            connection--;
        }

        String[] answerOrder = new String[size];

        // 큐안에 시작점 넣기(indegree 0인)
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            if (degree[i] == 0) {
                deque.add(i);
            }
        }

        // 큐가 빌때까지 노드를 하나씩 제거하면 연결된 outdegree 확인후 
        // outDegree삭제(outdegree방향으로 연결된 노드기준으로는 indegree가 줄어드는것)
        int idx = 0;
        while (!deque.isEmpty()) {
            int t = deque.poll();
            answerOrder[idx++] = arr[t].trim();
//            System.out.println(answerOrder[idx]);

            for (int i = 0; i < adj[t].size(); i++) {
                int num = adj[t].get(i);
                degree[num]--;
                if (degree[num] == 0) {
                    deque.add(num);
                }

            }
        }

        for (int i = 0; i < answerOrder.length - 1; i++) {
            System.out.print(answerOrder[i] + " -> ");
        }
        System.out.print(answerOrder[answerOrder.length-1]);

    }
}
//11 11
//아이언맨, 인크레더블 헐크, 토르: 다크월드, 가디언즈 오브 갤럭시, 퍼스트 어벤져, 어벤져스, 아이언맨3, 캡틴 아메리카: 윈터 솔져, 토르: 천둥의 신, 아이언맨2, 어벤져스: 에이지 오브 울트론
//아이언맨, 아이언맨2
//토르: 천둥의 신, 어벤져스
//퍼스트 어벤져, 어벤져스
//인크레더블 헐크, 어벤져스
//아이언맨2, 아이언맨3
//토르: 천둥의 신, 토르: 다크월드
//퍼스트 어벤져, 캡틴 아메리카: 윈터 솔져
//아이언맨3, 어벤져스: 에이지 오브 울트론
//토르: 다크월드, 어벤져스: 에이지 오브 울트론
//캡틴 아메리카: 윈터 솔져, 어벤져스: 에이지 오브 울트론
//가디언즈 오브 갤럭시, 어벤져스: 에이지 오브 울트론
