package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologySort2 {
	static int N;
	static int E;
	static ArrayList<Integer> [] graph;
	static int[] InputDegree; // 지입차수 기록
	static Queue<Integer> q;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList [N+1]; //노드번호 1~8까지 이므로 인덱스번호 1~8까지 사용
		InputDegree = new int[N+1];
		visited= new boolean[N+1];
		visited[0] = true;
		
		for (int i = 0; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph[start].add(end);
			InputDegree[end]++; //진입차수 기록
		}
		//===========입력

		q = new LinkedList<>();
		for (int i = 1; i < N+1; i++) {
			if(InputDegree[i] == 0) {
				q.add(i); //진입차수가 0인 것을 시작점으로 두기
				visited[i] = true;
			}
		}
	
		while (!q.isEmpty()) {
			int n = q.poll();
			System.out.print(n+" ");
			for (int i = 0; i < graph[n].size(); i++) {
				InputDegree[graph[n].get(i)]--;
			}
			for (int i = 1; i < N+1; i++) {
				if(InputDegree[i] == 0 && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		for (int i = 1; i < N+1; i++) {
			if(!visited[i]) {
				System.out.println("사이클 존재!!");
				break;
			}
		}
	}
}
/*
input
8 9
1 2
1 4
2 5
2 6
3 6
4 2
4 8
7 3
8 6

결과 : 1 7 4 3 2 8 6 5

input
8 9
1 2
1 4
2 5
2 6
3 6
4 2
4 8
7 3
8 1

결과 : 7 3 사이클 존재!!
*/
