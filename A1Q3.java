import java.util.*;

public class A1Q3 {
	public int[][][] rank = new int[1][1][1];
	public static int[] Knockout(int num_teams, int[][] events) {
		return null;
	}
	private static int[][] computeRank (int[][] events,int num_teams) {
		int[][] rank = new int[3][num_teams+1];
		int cols = events[0].length;
		for  (int i = 0; i < cols; i++) {
			int id = events[0][i];
			int gb = events[1][i];
			rank[0][id] = id;
			rank[1][id] += gb;
			rank[2][id]++;
		}
		return rank;
	}

	public static void main(String[] args) {
		 int[][] events = {{2,7},{3,5},{1,6},{1,9}};
		 int[][] rank = computeRank(events,3);
		 System.out.println(Arrays.toString(rank));
	}


}