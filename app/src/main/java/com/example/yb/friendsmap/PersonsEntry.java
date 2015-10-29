package com.example.yb.friendsmap;

import java.util.ArrayList;

/**
 * Created by YB on 16.10.2015.
 */
public class PersonsEntry {

    private final String personName;
    private final int personAvatar;
    private final String personFriendsList;

    public PersonsEntry(final String personName, final int personAvatar, String personFriendsList) {
        this.personName = personName;
        this.personAvatar = personAvatar;
        this.personFriendsList = personFriendsList;
    }

    public String getPersonName() {
        return personName;
    }

    public int getPersonAvatar() {
        return personAvatar;
    }

    public String getPersonFriendsList() {
        return personFriendsList;
    }

}
