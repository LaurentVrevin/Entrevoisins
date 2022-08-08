package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private final List<Neighbour> favoriteNeighbours = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        favoriteNeighbours.remove(neighbour);
    }


    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * @Override public List<Neighbour> getFavoriteNeighbours() {
     * return null;
     * }
     */

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        favoriteNeighbours.add(neighbour);
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        favoriteNeighbours.remove(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        return favoriteNeighbours;
    }

    @Override
    public boolean IsFavoriteNeighbour(Neighbour neighbour) {
        return favoriteNeighbours.contains(neighbour);
    }

}
