package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class RadixSort {
	static ArrayList<Integer> arr = new ArrayList<>();
	static int[] data;
	static int[] temp;
	static int[] count;
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("*** Radix Sort***\n");
		System.out.printf("정렬할 숫자를 띄어쓰기하여 입력하세요 >> ");
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		data= new int[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			data[i] = arr.get(i);
		}
	
		temp = new int[arr.size()];
		System.out.print("오름차순은 1번, 내림차순은 2번을 입력하세요 >> ");
		int k = Integer.parseInt(br.readLine());
		
		
		System.out.print("정렬 전 => ");
		for (int j = 0; j < data.length; j++) {
			System.out.print(data[j]+" ");
		}
		System.out.println("\n");
		
		

		int max =0;
		for (int i = 0; i < data.length; i++) {
			if(max<data[i]) {
				max = data[i];
			}
		}
		
		String maxS = Integer.toString(max);
		if(k ==1) {
			asc(maxS);
		}else {
			desc(maxS);
		}
		
	}
	static void desc(String maxS) {
for (int i = 0; i < maxS.length(); i++) {
			
			count = new int[10];
			int mod []= new int[10];
			
			//각 자리수의 값의 개수 담기
			for (int j = 0; j < data.length; j++) {
				count[(data[j]/(int)Math.pow(10, i))%10]++;
				mod[j] = (data[j]/(int)Math.pow(10, i))%10;
			}
			
			//과정출력
			System.out.println("data : "+ Arrays.toString(data));
//			System.out.println("count : "+ Arrays.toString(count));
//			System.out.println("mod : "+ Arrays.toString(mod));
			
			
			
			//누적합으로 바꾸기 
			for (int j = count.length-1; j > 0; j--) {
				count[j-1]+=count[j];
			}
			
			
			//제자리 찾아주기
			for (int j = data.length-1; j>= 0; j--) {
				int idx = count[mod[j]]-1;
				temp[idx] = data[j];
				count[mod[j]]--;
			}
			
			//자리수 마다 소팅한 결과 출력
			System.out.println("temp : "+ Arrays.toString(temp)+"\n");
			data = Arrays.copyOf(temp, temp.length);
		}

		//결과 출력
		System.out.print("정렬 후 => ");
		for (int j = 0; j < data.length; j++) {
			System.out.print(data[j]+" ");
		}
		System.out.println("\n");
		
		return;
	}
	
	
	
	static void asc(String maxS) {
		for (int i = 0; i < maxS.length(); i++) {
			
			count = new int[10];
			int mod []= new int[10];
			
			//각 자리수의 값의 개수 담기
			for (int j = 0; j < data.length; j++) {
				count[(data[j]/(int)Math.pow(10, i))%10]++;
				mod[j] = (data[j]/(int)Math.pow(10, i))%10;
			}
			
			//과정출력
			System.out.println("data : "+ Arrays.toString(data));
//			System.out.println("count : "+ Arrays.toString(count));
//			System.out.println("mod : "+ Arrays.toString(mod));
			
			
			
			//누적합으로 바꾸기 
			for (int j = 1; j < 10; j++) {
				count[j]+=count[j-1];
			}
			
			
			//제자리 찾아주기
			for (int j = data.length-1; j>= 0; j--) {
				int idx = count[mod[j]]-1;
				temp[idx] = data[j];
				count[mod[j]]--;
			}
			
			//자리수 마다 소팅한 결과 출력
			System.out.println("temp : "+ Arrays.toString(temp)+"\n");
			
			data = Arrays.copyOf(temp, temp.length);
		}

		//결과 출력
		System.out.print("정렬 후 => ");
		for (int j = 0; j < data.length; j++) {
			System.out.print(data[j]+" ");
		}
		System.out.println("\n");
		
		return;
	}

}
/*
 * 계수정렬에서 가장 작은 수에서 가장 큰 수까지의 범위를 K라고 할때 시간복잡도가 O(n+K)이다. 
 * 이 때 K가 매우 큰 수를 가지게 되면 시간, 공간복잡도가 커진다는 단점을 가지고 있다.
 * 이를 개선하기 위해 기수정렬을 사용한다.
 * 
 * keypoint는 자리수 별로 정렬한다.
 * 일의 자리 부터 큰 자리수 까지 계수 정렬한다.
 * 이때 기수은 stable한 정렬이기 때문에 같은 수를 가진다면 먼저 만난 수를 먼저 적어준다.
 * 
 */
