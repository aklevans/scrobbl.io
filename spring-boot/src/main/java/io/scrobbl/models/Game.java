package io.scrobbl.models;

import java.util.*;

import io.scrobbl.api.lastfm.Album;
import io.scrobbl.api.lastfm.Period;
import io.scrobbl.api.lastfm.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "GAME")
public class Game {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	private ArrayList<Player> players;

	public static final String KEY = System.getenv("APIKEY");
	
	public Game() {
		this.players = new ArrayList<>();
	}
	
	public void addPlayer(User player) {
		//playerAlbums.put(player.getName(), User.getTopAlbums(player.getName(), Period.OVERALL, 75, KEY));
		players.add(new Player(player.getName()));
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
