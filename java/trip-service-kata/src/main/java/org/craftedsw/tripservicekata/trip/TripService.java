package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User anotherUser) throws UserNotLoggedInException {
		User currentUser = getLoggedUser();
		if (!isLogged(currentUser)) {
			throw new UserNotLoggedInException();
		}
		if (areFriends( currentUser, anotherUser)){
			return  getTripByUser(anotherUser);
		}
		return new ArrayList<Trip>();

	}

	private boolean isLogged(User currentUser) {
		return currentUser != null;
	}

	private boolean areFriends(User loggedUser, User anotherUser ) {
		return anotherUser.getFriends().contains(loggedUser);
	}

	protected List<Trip> getTripByUser(User friend) {
		return TripDAO.findTripsByUser(friend);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
