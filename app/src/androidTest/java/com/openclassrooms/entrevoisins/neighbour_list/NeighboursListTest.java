
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITES_COUNT = 0;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);



    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours)) //va rechercher le layout qui a pour id ListNeighbours
                .check(matches(hasMinimumChildCount(1))); //vérifie s'il y a au moins 1 item
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }
    @Test
    public void myNeighbourgsDetailActivity_Openning(){
        //Pour que la page détail s'ouvre, nous devons générer un click sur une vue.
        //Pour cela je dois d'abord afficher la liste. Ensuite je dois gérer le click dessus.
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        //Je génère maintenant le click sur une des vues, comme la 2 par exemple
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

    }
    @Test
    public void myNeighbourgsDetailActivity_UserName_IsNotEmpty(){
        //Pour vérifier si le nom correspond bien au voisin sur lequel on a cliquer,
        // on procède comme pour la vérification de l'ouverture de la page détail activity
        // On doit ensuite vérifier si R.id.profilNameDetail est bien celui correspond, comme pour la position 2.
        // Nous devons alors retrouver "Chloé"
        //Pour cela je dois d'abord afficher la liste. Ensuite je dois gérer le click dessus.
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        //Je génère maintenant le click sur une des vues, ce sera la 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        //Je vérifie maintenant que le nom est bien "Chloé".
        onView(withId(R.id.profilNameDetail)).check(matches(withText("Chloé")));
    }
    @Test
    public void myFavoritList_TabLayout_isFavorited (){
        /**Pour vérifier que la liste des favoris comprend bien des favoris je dois :
         * Ouvrir la page Détail Activity
         * Générer le click sur le bouton Favori
         * Retourner en arrière sur le tabLayout
         * Switcher sur le tablayout favori
         * check qu'il y a bien le favori que j'ai ajouté.
         * Nous allons prendre "Vincent" en index 3.
         */
        //j'affiche la liste des favoris
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        //Je click sur Vincent pour l'ouvrir dans la Detail Activity
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        //Je click sur le bouton favoris
        onView(withId(R.id.floatingActionButtonDetail)).perform(click());

        //Je retourne en arrière pour switcher sur la tab favori
        onView(ViewMatchers.withId(R.id.BackButton)).perform(click());
        //sur le container, je dois passer sur le tab 2

        onView(ViewMatchers.withId(R.id.list_favorite_neighbours)) //va rechercher le layout qui a pour id ListNeighbours
                .check(matches(hasMinimumChildCount(1))); //vérifie s'il y a au moins 1 item


    }
}