package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

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

    private class testeableTripService extends TripService {
        protected User getLoggedUser(){
            return  null;
        }
    }
}
