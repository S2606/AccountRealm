package com.android.paisacount.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.android.paisacount.R;
import com.android.paisacount.model.Account;

import java.text.SimpleDateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Shagun on 06/04/2017.
 */

public class pwidget extends AppWidgetProvider {


    private Realm realm;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;

        RealmResults<Account> allTransactions = realm.where(Account.class).findAllSorted("date");

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.simple_widget);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(allTransactions.last().getDate());
            remoteViews.setTextViewText(R.id.dat,formattedDate);
            remoteViews.setTextViewText(R.id.hackathon_name,allTransactions.last().getHackathon_name());
            remoteViews.setTextViewText(R.id.university_na,allTransactions.last().getUniversity_name());


            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}