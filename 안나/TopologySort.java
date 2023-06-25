package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologySort {
	static int N;
	static int E;
	static String[] movies;
	static ArrayList<Integer>[] graph;
	static int[] InputDegree;
	static Queue<Integer> q;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList [N];
		InputDegree = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		movies = br.readLine().split(", ");
		
//		for (int i = 0; i < 11; i++) {
//			System.out.println(movies[i]);
//		}
		
		for (int i = 0; i < E; i++) {
			String input[] = br.readLine().split(", ");
			int start =-1;
			int end =-1;
			for (int j = 0; j < N; j++) {
				if(input[0].equals(movies[j])) {
					start = j; 
				}
				if(input[1].equals(movies[j])) {
					end = j; 
				}
			}
			graph[start].add(end);
			InputDegree[end]++; //진입차수 기록
		}
		
		//---------------입력
		
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if(InputDegree[i] == 0) {
				q.add(i);
				visited[i] =true;
			}
		}
		
		while (!q.isEmpty()) {
			int n = q.poll();
			System.out.print(movies[n]+" -> ");
			for (int i = 0; i < graph[n].size(); i++) {
				InputDegree[graph[n].get(i)]--;
			}
			for (int i = 0; i < N; i++) {
				if(InputDegree[i] == 0 && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		
		//모든 원소들을 방문하기 전에 큐가 빈다면 사이클이 존재
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				System.out.println("사이클 존재");
			}
		}
			
		
	}
}
/*
input
11 11
아이언맨, 인크레더블 헐크, 토르: 다크월드, 가디언즈 오브 갤럭시, 퍼스트 어벤져, 어벤져스, 아이언맨3, 캡틴 아메리카: 윈터 솔져, 토르: 천둥의 신, 아이언맨2, 어벤져스: 에이지 오브 울트론
아이언맨, 아이언맨2
토르: 천둥의 신, 어벤져스
퍼스트 어벤져, 어벤져스
인크레더블 헐크, 어벤져스
아이언맨2, 아이언맨3
토르: 천둥의 신, 토르: 다크월드
퍼스트 어벤져, 캡틴 아메리카: 윈터 솔져
아이언맨3, 어벤져스: 에이지 오브 울트론
토르: 다크월드, 어벤져스: 에이지 오브 울트론
캡틴 아메리카: 윈터 솔져, 어벤져스: 에이지 오브 울트론
가디언즈 오브 갤럭시, 어벤져스: 에이지 오브 울트론

결과 : 아이언맨 -> 인크레더블 헐크 -> 가디언즈 오브 갤럭시 -> 퍼스트 어벤져 -> 토르: 천둥의 신 -> 아이언맨2 -> 캡틴 아메리카: 윈터 솔져 -> 토르: 다크월드 -> 어벤져스 -> 아이언맨3 -> 어벤져스: 에이지 오브 울트론 -> 

*/