package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    private User user;

    @Test void
    return_exception_given_null_user(){
        TripService tripService = new testeableTripService();
        user = null;
        assertThrows(UserNotLoggedInException.class , ()->{
            tripService.getTripsByUser(user);
        });
    }

    @Test
    void return_no_trips_if_not_friend_with_user() {
        user = new User();
        TripService tripService = new testeableTripService();
        User stranger = new User();
        stranger.addFriend(new User());
        stranger.addTrip(new Trip());

        List<Trip> trips = tripService.getTripsByUser(stranger);
        assertEquals(trips.size(), 0);
    }

    private class testeableTripService extends TripService {
        protected User getLoggedUser(){
            return  user;
        }
    }
}
