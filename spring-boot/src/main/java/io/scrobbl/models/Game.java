package io.scrobbl.models;

import java.util.ArrayList;

import de.umass.lastfm.User;

public class Game {
	private ArrayList<User> users;
	
	public Game() {
		users = new ArrayList<User>();
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
}
