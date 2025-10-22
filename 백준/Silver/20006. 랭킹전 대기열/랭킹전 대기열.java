import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int p = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Player[] players = new Player[p];
		
		StringBuilder sb = new StringBuilder();
		List<Integer> list = new ArrayList<>();
		int cnt = 0;
		
		for(int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			
			int l = Integer.parseInt(st.nextToken());
			String n = st.nextToken();
			players[i] = new Player(l, n);
		}
		
		for(int i = 0; i < p; i++) {
			ArrayList<Player> room = new ArrayList<>();
			if(!players[i].check) {
				for(int j = i; j < p; j++) {
					if(room.size() == m) {
						break;
					}
					
					int level = players[j].level;
					String name = players[j].name;
					if(!players[j].check && players[i].level - 10 <= level && players[i].level + 10 >= level) {
						players[j].check = true;
						room.add(new Player(level, name));
					}
				}
				
				Collections.sort(room);
				if(room.size() == m) {
					sb.append("Started!").append("\n");
				} else {
                    sb.append("Waiting!").append("\n");
                }
				
                for (Player player : room) {
                    sb.append(player.level).append(" ").append(player.name).append("\n");
                }
			}
		}
		
		System.out.println(sb);
	}
	
	static class Player implements Comparable<Player> {
        int level;
        String name;
        boolean check;

        Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return name.compareTo(o.name);
        }
    }

}
