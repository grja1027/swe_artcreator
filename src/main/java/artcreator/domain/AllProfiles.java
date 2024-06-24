package artcreator.domain;

import java.util.HashMap;
import java.util.Map;

public class AllProfiles {
    private static final Map<Integer, Profile> allProfiles = new HashMap<>();

    static {
        // Mock-Data -> Muss über Use Case "Profil erstellen" dynamisch gespeichert werden
        // Resolution = sqrt((widthImage x heightImage) / (width x height))
        allProfiles.put(0, new Profile("2:1", 16, 11, 60, 30));
        allProfiles.put(1, new Profile("16:9", 16, 16, 60, 34));
        allProfiles.put(2, new Profile("1:1", 32, 16, 60, 60));
    }

    public static Profile getProfile(int id) {
        return allProfiles.get(id);
    }
}

