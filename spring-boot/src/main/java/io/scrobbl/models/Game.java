package io.scrobbl.models;

import java.util.*;


import io.scrobbl.lastfm.Album;
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

	@ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
	@CollectionTable(name="OVERLAP", joinColumns = @JoinColumn(name = "GAME_ID"))
	private List<String> overlap = new ArrayList<>();

	public static final String KEY = System.getenv("APIKEY");


	public Game() {
	}
	
	public void addPlayer(Player player) {
		player.calcTopAlbums(50);
		players.add(player);
	}

	public void setPlayers(List<Player> players){

		this.players = players;
	}

	public void setOverlap(List<String>overlap){
		this.overlap = overlap;
	}

	public List<String> getOverlap(){
		return overlap;
	}

	public List<Player> getPlayers(){
		return players;
	}

	public int getId(){
		return id;
	}

	public List<String> calcOverlap() {
		for(Player player : players) {

			List<String> albums = player.getKnownAlbums();
			for(String albumMBID : albums) {
				Album album = Album.getInfo(null, albumMBID, player.getName(), KEY);
				if(album == null){
					continue;
				}
				boolean allMatch = true;
				for(Player other : players) {
					if(other.equals(player)) {
						continue;
					}
					//Artist oa2 = Artist.getInfo(a.getArtist(), other.getName(), KEY);

					if(Album.getInfo(album.getArtist(), album.getName(), other.getName(), KEY).getUserPlaycount() < 10 ) {
						allMatch = false;
						break;
					}
				}
				if(allMatch && !overlap.contains(album.getName())) {
					overlap.add(album.getName());
				}
			}
		}
		return overlap;
	}
}
