package io.scrobbl.util;
import java.util.*;

import io.scrobbl.api.lastfm.Album;

public class PrintUtils {

    public static String AlbumsToCSV(ArrayList<Album> albums) {
        StringBuilder sb = new StringBuilder();
        for( Album a : albums) {
            sb.append(a.getName());
            sb.append(",");
        }
        return sb.toString();
    }
}
