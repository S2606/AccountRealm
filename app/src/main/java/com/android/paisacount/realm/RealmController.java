package com.android.paisacount.realm;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.android.paisacount.model.Account;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Shagun on 05/04/2017.
 */

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Account.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(Account.class);
        realm.commitTransaction();
    }

    //find all objects in the Account.class
    public RealmResults<Account> getAccount() {

        return realm.where(Account.class).findAll();
    }

    //query a single item with the given id
    public Account getAccount(String id) {

        return realm.where(Account.class).equalTo("id", id).findFirst();
    }

    //check if Account.class is empty
    public boolean hasAccount() {

        return !realm.allObjects(Account.class).isEmpty();
    }

    //query example
    public RealmResults<Account> queryedAccount() {

        return realm.where(Account.class)
                .contains("payment_by", "Shagun")
                .or()
                .contains("hackathon_place", "banglore")
                .findAll();

    }
}
