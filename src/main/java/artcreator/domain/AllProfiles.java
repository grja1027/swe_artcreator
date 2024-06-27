package artcreator.domain;

import java.util.HashMap;
import java.util.Map;

public class AllProfiles {
    private static final Map<Integer, Profile> allProfiles = new HashMap<>();

    static {
        // Mock-Data -> Muss über Use Case "Profil erstellen" dynamisch gespeichert werden
        // Resolution = sqrt((widthOriginalImage x heightOriginalImage) / (width x height))
        allProfiles.put(1, new Profile("3:2", 64, 100, 60, 40)); // for ferrari.jpg
        allProfiles.put(0, new Profile("1:2", 16, 24, 30, 60)); // for lolli.jpg
        allProfiles.put(2, new Profile("1:1", 32, 16, 60, 60));
    }

    public static Profile getProfile(int id) {
        return allProfiles.get(id);
    }
}

