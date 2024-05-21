package io.scrobbl.models;

import java.util.*;

import io.scrobbl.api.lastfm.Album;
import io.scrobbl.api.lastfm.Period;
import io.scrobbl.api.lastfm.User;




public class Game {
	private ArrayList<User> players;
	private Map<User, Collection<Album>> playerAlbums;

	public static final String KEY = System.getenv("APIKEY");
	
	public Game() {
		this.players = new ArrayList<User>();
		this.playerAlbums = new HashMap<User, Collection<Album>>();

	}
	
	public void addPlayer(User player) {

		players.add(player);
		playerAlbums.put(player, User.getTopAlbums(player.getName(), Period.OVERALL, 50, KEY));

	}
	
	public void addPlayers(User[] newPlayers) {
        players.addAll(Arrays.asList(newPlayers));
	}
	
	public ArrayList<User> getPlayers() {
		return players;
	}

	public void recalcTopAlbums(int limit){
        playerAlbums.replaceAll((k, v) -> User.getTopAlbums(k.getName(), Period.OVERALL, limit, KEY));
	}


	public void recalcTopAlbums(User player, int limit){
		playerAlbums.put(player, User.getTopAlbums(player.getName(), Period.OVERALL, limit, KEY));
	}


	public ArrayList<Album> getOverlap() {
		ArrayList<Album> overlap = new ArrayList<Album>();
		for(Map.Entry<User,Collection<Album>> entry: playerAlbums.entrySet()) {
			User u = entry.getKey();
			Collection<Album> albums = playerAlbums.get(u);
			for(Album a : albums) {
				boolean allMatch = true;
				for(User other : players) {
					if(other.equals(u)) {
						continue;
					}
					//Artist oa2 = Artist.getInfo(a.getArtist(), other.getName(), KEY);
					if(Album.getInfo(a.getArtist(), a.getName(), other.getName(), KEY).getUserPlaycount() < 10 ) {
						allMatch = false;
						break;
					}
				}
				if(allMatch && !overlap.contains(a)) {
					overlap.add(a);
				}
			}
		}
		return overlap;
	}
}
