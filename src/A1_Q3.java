import java.util.*;

public class A1_Q3 {
	public static int[] Knockout(int num_teams, int[][] events) {
		return computeRank(events, num_teams);
	}
	private static int[] computeRank (int[][] events,int num_teams) {
		int[][] rank = new int[num_teams][3];
		int[] finalResult = new int[events.length];
		int cols = events.length;
		TreeSet<Integer> team = new TreeSet<>((idA, idB) -> {
			int[] statsA = rank[idA];
			int[] statsB = rank[idB];
			if (statsA[1] != statsB[1]) {
				return Integer.compare(statsB[1], statsA[1]);
			}
			else if  (statsA[0] != statsB[0]) {
				return Integer.compare(statsA[0], statsB[0]);
			}
			else {
				return Integer.compare(idA, idB);
			}
		});

		boolean teamOnePlayed = false;

		for  (int i = 0; i < cols; i++) {
			int id = events[i][0];
			int gb = events[i][1];
			team.remove(id-1); // check if the team exist already
            rank[id-1][0] += gb;
            rank[id-1][1]++;
            team.add(id-1); // update the team
			if (id == 1) {
				teamOnePlayed = true;
			}
			if (teamOnePlayed) {
				finalResult[i] = team.headSet(0).size() + 1;
			}
			else {
				finalResult[i] = team.size() + 1;
			}
        }
		return finalResult;
	}
	public static void main(String[] args) {
		 int[][] events = {{3,10},{2,2},{4,2},{1,5},{1,2},{2,5}};
		 int[] finalResult = Knockout(4,events);
		System.out.println(Arrays.toString(finalResult));
	}

}
