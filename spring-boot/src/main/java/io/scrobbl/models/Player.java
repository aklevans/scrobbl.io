package io.scrobbl.models;

import io.scrobbl.library.lastfm.Album;
import io.scrobbl.library.lastfm.Period;
import io.scrobbl.library.lastfm.User;
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
    @CollectionTable(name="KNOWN_ALBUMS", joinColumns = @JoinColumn(name = "PLAYER_ID"))
    private List<String> knownAlbums = new ArrayList<>();


    protected Player() {}

    public List<String> getKnownAlbums(){
        return knownAlbums;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setKnownAlbums(List<String> albums){
        this.knownAlbums = albums;
    }

    public Player(String name){
        this.name = name;
        calcTopAlbums(50);

    }

    public void calcTopAlbums(int limit){
        Collection<Album> c = User.getTopAlbums(name, Period.OVERALL, limit, System.getenv("APIKEY"));
        for(Album a : c){
            knownAlbums.add(a.getMbid());
        }
    }

    public String getName(){
        return name;
    }

    public List<String> getAlbums(){
        return knownAlbums;
    }

    public void setName(String name){
        this.name = name;
    }

}
