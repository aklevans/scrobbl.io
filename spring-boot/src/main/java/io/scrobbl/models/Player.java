package io.scrobbl.models;


import io.scrobbl.lastfm.Album;
import io.scrobbl.lastfm.Period;
import io.scrobbl.lastfm.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name="TOP_ALBUMS", joinColumns = @JoinColumn(name = "PLAYER_ID"))
    private List<String> topAlbums = new ArrayList<>();

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name="KNOWN_ALBUMS", joinColumns = @JoinColumn(name = "PLAYER_ID"))
    private List<String> knownAlbums = new ArrayList<>();

    protected Player() {}

    public List<String> getTopAlbums(){
        return topAlbums;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setTopAlbums(List<String> albums){
        this.topAlbums = albums;
    }

    public Player(String name){
        this.name = name;
        calcTopAlbums(50);

    }

    public void calcTopAlbums(int limit){
        Collection<Album> c = User.getTopAlbums(name, Period.OVERALL, limit, System.getenv("APIKEY"));
        for(Album a : c){
            topAlbums.add(a.getMbid());
        }
    }

    public String getName(){
        return name;
    }

    public List<String> getAlbums(){
        return topAlbums;
    }

    public void setName(String name){
        this.name = name;
    }

}
