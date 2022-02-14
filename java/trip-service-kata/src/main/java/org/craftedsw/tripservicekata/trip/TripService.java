package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		if (loggedUser != null) {
			var friends = user.getFriends();
			var index = friends.indexOf(loggedUser);
			if (index != -1){
				return  getTripByUser(user);
			}
			return new ArrayList<Trip>();
		}

		throw new UserNotLoggedInException();

	}

	protected List<Trip> getTripByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
