package artcreator.domain;

import java.util.HashMap;
import java.util.Map;

public class AllProfiles {
    private static final Map<Integer, Profile> allProfiles = new HashMap<>();

    static {
        // Mock-Data -> Muss über Use Case "Profil erstellen" dynamisch gespeichert werden
        allProfiles.put(0, new Profile("3:2", 256, "20", 60, 40));
        allProfiles.put(1, new Profile("16:9", 16, "17", 60, 34));
        allProfiles.put(2, new Profile("1:1", 256, "12", 60, 60));
    }

    public static Profile getProfile(int id) {
        return allProfiles.get(id);
    }
}

