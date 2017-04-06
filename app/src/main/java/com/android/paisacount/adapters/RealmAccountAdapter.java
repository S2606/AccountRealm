package com.android.paisacount.adapters;

import android.content.Context;

import com.android.paisacount.model.Account;

import io.realm.RealmResults;

/**
 * Created by Shagun on 05/04/2017.
 */

public class RealmAccountAdapter extends RealmModelAdapter<Account> {

    public RealmAccountAdapter(Context context, RealmResults<Account> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}
