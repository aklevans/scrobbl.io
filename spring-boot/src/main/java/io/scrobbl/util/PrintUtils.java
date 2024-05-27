package io.scrobbl.util;
import java.util.*;

import io.scrobbl.api.lastfm.Album;

public class PrintUtils {

    public static String toCSV(List<String> albums) {
        StringBuilder sb = new StringBuilder();
        for( String a : albums) {
            sb.append(a);
            sb.append(",");
        }
        return sb.toString();
    }
}
