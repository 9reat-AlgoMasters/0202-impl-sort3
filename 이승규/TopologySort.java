import java.io.*;
import java.util.*;

//

public class TopologySort {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("정점 수 입력 >> ");
        int N = Integer.parseInt(br.readLine());
        System.out.print("간선 수 입력 >> ");
        int M = Integer.parseInt(br.readLine());

        //진입차수 저장 배열
        int[] edgeLevel =new int[N + 1];
        //그래프(인접 행렬)
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            System.out.print("정점(from), 정점(to) 입력(공백으로 구분) >> ");
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            edgeLevel[to]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // 진입차수가 0인 값 큐에 넣기
        for (int i = 1; i < edgeLevel.length; i++) {
            if (edgeLevel[i] == 0) {
                q.offer(i);
            }
        }

        System.out.println("==========순서 출력==========");

        while (!q.isEmpty()) {
            int studentNo = q.poll();

            System.out.print(studentNo + " ");

            List<Integer> list = graph.get(studentNo);

            for (int ints : list) {
                edgeLevel[ints]--;
                if (edgeLevel[ints] == 0) {
                    q.offer(ints);
                }
            }
        }
    }
}