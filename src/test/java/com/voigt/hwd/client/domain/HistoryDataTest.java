package com.voigt.hwd.client.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HistoryDataTest {

    static Stream<Season> provideSeasons() {
        return HistoryData.getSeasons().stream();
    }

    @ParameterizedTest
    @MethodSource("provideSeasons")
    void testUniqueUsersPerSeason(Season season) {
        Map<User, UserSeasonRecord> users = season.getUsers();
        Set<User> uniqueUsers = new HashSet<>(users.keySet());
        assertEquals(users.size(), uniqueUsers.size(), 
            "Duplicate users found in season " + season.getYear());
    }

    @ParameterizedTest
    @MethodSource("provideSeasons")
    void testUniquePlacesPerSeason(Season season) {
        Map<User, UserSeasonRecord> users = season.getUsers();
        Set<Integer> uniquePlaces = new HashSet<>();
        for (UserSeasonRecord record : users.values()) {
            assertTrue(uniquePlaces.add(record.getPlace()), 
                "Duplicate place found in season " + season.getYear() + ": " + record.getPlace());
        }
    }

    @ParameterizedTest
    @MethodSource("provideSeasons")
    void testDescendingPointsPerSeason(Season season) {
        Map<User, UserSeasonRecord> users = season.getUsers();
        float previousPoints = Integer.MAX_VALUE;

        for (int place = 1; place <= users.size(); place++) {
            int finalPlace = place;
            UserSeasonRecord record = users.values().stream()
                .filter(r -> r.getPlace() == finalPlace)
                .findFirst()
                .orElse(null);

            assertNotNull(record, 
                "Missing record for place " + place + " in season " + season.getYear());
            assertTrue(record.getPoints() <= previousPoints, 
                "Points are not in descending order in season " + season.getYear() + 
                " for place " + place + ": " + record.getPoints() + " > " + previousPoints);
            previousPoints = record.getPoints();
        }
    }
}
