package io.scrobbl.models;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import io.scrobbl.api.lastfm.Album;
import io.scrobbl.api.lastfm.User;



class GameTest {

	@Test
	void test() {
		Game g = new Game();
		User u = User.getInfo("alexkl3", Game.KEY);
		User b = User.getInfo("th0gus", Game.KEY);
		//System.out.println(a.getUserPlaycount());
		g.addPlayer(u);
		g.addPlayer(b);
		
		ArrayList<Album> overlap = g.getOverlap();
		for(Album a : overlap) {
			System.out.println(a.getArtist() + " - " + a.getName());
		}
	}

}
