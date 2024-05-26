package io.scrobbl.models;

import java.util.*;


import jakarta.persistence.*;


@Entity
@Table(name = "GAME")
public class Game {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ElementCollection(targetClass = Player.class, fetch = FetchType.EAGER)
	@CollectionTable(name="PLAYERS", joinColumns = @JoinColumn(name = "GAME_ID"))
	private List<Player> players = new ArrayList<>();

	public static final String KEY = System.getenv("APIKEY");
	
	public Game() {
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}

	public void setPlayers(List<Player> players){
		this.players = players;
	}

	public List<Player> getPlayers(){
		return players;
	}

	public int getId(){
		return id;
	}

	public void recalcTopAlbums(int limit){
        //playerAlbums.replaceAll((k, v) -> User.getTopAlbums(k, Period.OVERALL, limit, KEY));
	}


	public void recalcTopAlbums(String player, int limit){
		//playerAlbums.put(player, User.getTopAlbums(player, Period.OVERALL, limit, KEY));
	}


//	public ArrayList<Album> getOverlap() {
//		ArrayList<Album> overlap = new ArrayList<>();
//		for(Map.Entry<String,Collection<Album>> entry: playerAlbums.entrySet()) {
//			String usr = entry.getKey();
//			Collection<Album> albums = playerAlbums.get(usr);
//			for(Album a : albums) {
//				boolean allMatch = true;
//				for(String other : playerAlbums.keySet()) {
//					if(other.equals(usr)) {
//						continue;
//					}
//					//Artist oa2 = Artist.getInfo(a.getArtist(), other.getName(), KEY);
//					if(Album.getInfo(a.getArtist(), a.getName(), other, KEY).getUserPlaycount() < 10 ) {
//						allMatch = false;
//						break;
//					}
//				}
//				if(allMatch && !overlap.contains(a)) {
//					overlap.add(a);
//				}
//			}
//		}
//		return overlap;
//	}
}
