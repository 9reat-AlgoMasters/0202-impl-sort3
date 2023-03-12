import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class TopologySort {
    static int N, M;
    static HashMap<String, Integer> movieIndexTable = new HashMap<>();
    static Graph g;
    static int[] inDegree;
    static boolean[] visited;

    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curDirPath = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curDirPath + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String path = "\\김주현\\case\\";
        String fileName = "testcase.txt";
        setInputFile(path, fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        g = new Graph(N);
        String[] input = br.readLine().split(",");
        String[] movies = new String[input.length + 1];
        for (int i=0; i<input.length; i++) {
            movies[i+1] = input[i].trim();
        }
        for (int i=1; i<=N; i++) {
            movieIndexTable.put(movies[i].trim(), i);
        }

        inDegree = new int[N+1];
        visited = new boolean[N + 1];

        // 1. 간선 입력 및 inDegree 만들기
        while (M-- > 0) {
            String[] edge = br.readLine().split(",");
            g.addEdge(movieIndexTable.get(edge[0].trim()), movieIndexTable.get(edge[1].trim()));
            inDegree[movieIndexTable.get(edge[1].trim())]++;
        }

        // 2. 시작점 큐에 넣기
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
                visited[i] = true;
            }
        }

        String[] movieOrder = new String[N];
        int index = 0;
        // 3. 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            int now = q.poll();
            movieOrder[index++] = movies[now];

            for (int next : g.adjList[now]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

        for (int i=0; i<N-1; i++) {
            System.out.printf("%s -> ", movieOrder[i]);
        }
        System.out.println(movieOrder[N-1]);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Graph {
        List<Integer>[] adjList;

        public Graph(int size) {
            adjList = new ArrayList[size+1];
            for (int i=1; i<=size; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        void addEdge(int from, int to) {
            adjList[from].add(to);
        }
    }

}
