package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class NeighbourFavoriteServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void addFavoriteNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(1); //récupère un voisin
        //Si le neighbour n'est pas dans les favoris, l'ajoute à la liste
        if (!service.IsFavoriteNeighbour(neighbour)) {
            service.addFavoriteNeighbour(neighbour);
        }
        //Vérifie que le neighbour est bien dans la liste
        assertTrue(service.getFavoriteNeighbours().contains(neighbour));
    }

    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(1); //récupère un voisin
        //Si le neighbour n'est pas dans les favoris, l'ajoute à la liste
        if (service.IsFavoriteNeighbour(neighbour)) {
            service.deleteFavoriteNeighbour(neighbour);
        }
        //Vérifie que le neighbour est bien dans la liste
        assertFalse(service.getFavoriteNeighbours().contains(neighbour));
    }

    @Test
    public void getFavoriteNeighboursWithSuccess() {
        //Ajoute 2 favoris dans la liste et vérifie que la liste contient bien les 2 neighbours
        List<Neighbour> neighboursToAddToFavorites = new ArrayList<>();
        neighboursToAddToFavorites.add(service.getNeighbours().get(1));
        neighboursToAddToFavorites.add(service.getNeighbours().get(2));
        //Boucle jusqu'à la taille de la liste des neighbours à ajouter aux favoris
        for (int i = 0; i < neighboursToAddToFavorites.size(); i++) {
            Neighbour neighbour = neighboursToAddToFavorites.get(i);
            if (!service.IsFavoriteNeighbour(neighbour)) {
                service.addFavoriteNeighbour(neighbour);
            }
        }
        //asserEquals = compare.
        assertEquals(neighboursToAddToFavorites.size(),service.getFavoriteNeighbours().size());
    }

}
