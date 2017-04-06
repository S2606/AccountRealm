package com.android.paisacount.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.paisacount.R;
import com.android.paisacount.model.Account;
import com.android.paisacount.realm.RealmController;

import java.text.SimpleDateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Shagun on 05/04/2017.
 */

public class AccountAdapter extends RealmRecyclerViewAdapter<Account> {

    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public AccountAdapter(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_entry, parent, false);
        return new CardViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();


        // get the article
        final Account account = getItem(position);
        // cast the generic view holder to our specific one
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        // set the title and the snippet
        holder.date.setText(new SimpleDateFormat("dd-MM-yyyy").format(account.getDate()));
        holder.hackathon_name.setText(account.getHackathon_name());
        holder.university_name.setText(account.getUniversity_name());
        holder.hackathon_place.setText(account.getHackathon_place());
        holder.amount.setText(Integer.toString(account.getAmount()));
        holder.payment_by.setText(account.getPayment_by());
        holder.reason.setText(account.getReason());

        //remove single match from realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RealmResults<Account> results = realm.where(Account.class).findAll();

                // Get the book title to show it in toast message
                Account b = results.get(position);
                String title = b.getHackathon_name();

                // All changes to data must happen in a transaction
                realm.beginTransaction();

                // remove single match
                results.remove(position);
                realm.commitTransaction();

                notifyDataSetChanged();

                Toast.makeText(context, title + " is removed from Realm", Toast.LENGTH_SHORT).show();
                return false;

            }
        });

        //update single match from realm
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View content = inflater.inflate(R.layout.edit_account, null);
                final TextView date = (TextView) content.findViewById(R.id.dat);
                final EditText edithackathon_name = (EditText) content.findViewById(R.id.hackathon_name);
                final EditText edituniversity_name = (EditText) content.findViewById(R.id.university_name);
                final EditText edithackathon_place = (EditText) content.findViewById(R.id.hackathon_place);
                final EditText editamount = (EditText) content.findViewById(R.id.amount);
                final EditText editpayment_by = (EditText) content.findViewById(R.id.payment_by);
                final EditText editreason = (EditText) content.findViewById(R.id.reason);

                date.setText(new SimpleDateFormat("dd-MM-yyyy").format(account.getDate()));
                edithackathon_name.setText(account.getHackathon_name());
                edituniversity_name.setText(account.getUniversity_name());
                edithackathon_place.setText(account.getHackathon_place());
                editamount.setText(Integer.toString(account.getAmount()));
                editpayment_by.setText(account.getPayment_by());
                editreason.setText(account.getReason());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(content)
                        .setTitle("Edit Entry")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                RealmResults<Account> results = realm.where(Account.class).findAll();

                                realm.beginTransaction();
                                results.get(position).setHackathon_name(edithackathon_name.getText().toString());
                                results.get(position).setUniversity_name(edituniversity_name.getText().toString());
                                results.get(position).setHackathon_place(edithackathon_place.getText().toString());
                                results.get(position).setAmount(Integer.parseInt(editamount.getText().toString()));
                                results.get(position).setPayment_by(editpayment_by.getText().toString());
                                results.get(position).setReason(editreason.getText().toString());

                                realm.commitTransaction();

                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    // return the size of your data set (invoked by the layout manager)
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView date;
        public TextView hackathon_name;
        public TextView university_name;
        public TextView hackathon_place;
        public TextView amount;
        public TextView payment_by;
        public TextView reason;


        public CardViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_books);
            date = (TextView) itemView.findViewById(R.id.dateof);
            hackathon_name = (TextView) itemView.findViewById(R.id.hackathon_name);
            university_name = (TextView) itemView.findViewById(R.id.university_name);
            hackathon_place = (TextView) itemView.findViewById(R.id.hackathon_place);
            amount = (TextView) itemView.findViewById(R.id.amount);
            payment_by = (TextView) itemView.findViewById(R.id.payment_by);
            reason = (TextView) itemView.findViewById(R.id.reason);
        }
    }
}
