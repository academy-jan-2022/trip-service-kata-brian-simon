package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    private User user;
    private testeableTripService tripService;

    @BeforeEach
    void setUp() {
        tripService = new testeableTripService();
        user = new User();
    }

    @Test void
    return_exception_given_null_user(){
        user = null;
        assertThrows(UserNotLoggedInException.class , ()->{
            tripService.getTripsByUser(user);
        });
    }

    @Test void
    return_no_trips_if_not_friend_with_user() {
        User stranger = new User();
        stranger.addFriend(new User());
        stranger.addTrip(new Trip());

        List<Trip> trips = tripService.getTripsByUser(stranger);
        assertEquals(trips.size(), 0);
    }

    @Test void
    return_trips_if_friends_with_user(){
        User friend = new User();
        friend.addFriend(user);
        Trip trip =new Trip();
        friend.addTrip(trip);
        List<Trip> trips = tripService.getTripsByUser(friend);
        assertEquals(trips.size(),1);
    }

    private class testeableTripService extends TripService {
        protected List<Trip> getTripByUser(User user) {
            return user.trips();
        }

        protected User getLoggedUser(){
            return  user;
        }
    }
}
