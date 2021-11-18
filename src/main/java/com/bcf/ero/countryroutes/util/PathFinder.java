package com.bcf.ero.countryroutes.util;

import com.bcf.ero.countryroutes.exception.PathNotFoundException;
import com.bcf.ero.countryroutes.model.Country;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@AllArgsConstructor
public class PathFinder {

    private final Map<String, Country> countriesMap;
    private final Country origin;
    private final Country destination;

    private final Set<String> visited = new HashSet<>();
    private final Map<String, String> toFromMap = new HashMap<>();

    public List<String> findPath() throws PathNotFoundException {
        Country currentCountry = origin;

        Queue<Country> queue = new ArrayDeque<>();
        queue.add(currentCountry);

        visited.add(currentCountry.getName());

        if(currentCountry.equals(destination)) {
            return Collections.singletonList(currentCountry.getName());
        }

        outer: while(!queue.isEmpty()) {
            currentCountry = queue.remove();
            log.info("Visiting "+ currentCountry.getName());

            for(String neigh: currentCountry.getBorders()){

                if(!visited.contains(neigh)) {
                    visited.add(neigh);

                    Country neighCountry = countriesMap.get(neigh);
                    queue.add(neighCountry);
                    toFromMap.put(neighCountry.getName(), currentCountry.getName());

                    if (neighCountry.equals(destination)) {
                        log.info("Found path");
                        currentCountry = neighCountry;
                        break outer;
                    }
                }

            }
        }

        if(!currentCountry.equals(destination)) {
            throw new PathNotFoundException("No path found");
        }

        List<String> path = new ArrayList<>();

        for(String country = destination.getName(); country != null; country = toFromMap.get(country)) {
            path.add(country);
        }

        return reverseList(path);
    }

    private List<String> reverseList(List<String> path) {
        for (int i = 0, j = path.size() - 1; i<j; i++) {
            path.add(i, path.remove(j));
        }
        return path;
    }
}
