package com.nimbees.newdemo.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nimbees.newdemo.R;
import com.nimbees.platform.beans.NimbeesMessage;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * This adapter works with the RecyclerView of notifications to show each element in the UI
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    /**
     * Nimbees Message List
     */
    private List<NimbeesMessage> mMessageList;

    /**
     * Param constructor
     *
     * @param mMessageList The message list
     */
    public NotificationAdapter(List<NimbeesMessage> mMessageList) {
        this.mMessageList = mMessageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder NotificationViewHolder, int i) {

        NimbeesMessage nimbeesMessage = mMessageList.get(i);
        String date = new SimpleDateFormat("dd-MM-yyyy   HH:mm", new Locale("ES_es")).format(nimbeesMessage.getTimestamp());

        if (nimbeesMessage.getIdBeacon() == null) {
            NotificationViewHolder.vImageType.setImageResource(R.drawable.ic_sms_white_36dp);
        } else {
            NotificationViewHolder.vImageType.setImageResource(R.drawable.ic_track_changes_white_36dp);
        }

        if (nimbeesMessage.getDisplayed()) {
            NotificationViewHolder.vDisplayed.setImageResource(R.drawable.ic_visibility_white_36dp);
        } else {
            NotificationViewHolder.vDisplayed.setImageResource(R.drawable.ic_visibility_off_white_36dp);
        }
        NotificationViewHolder.vDate.setText(date);
        NotificationViewHolder.vContent.setText(nimbeesMessage.getContent());
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_item, viewGroup, false);

        return new NotificationViewHolder(itemView, new NotificationViewHolder.INotificationViewHolderClickListener() {
            @Override
            public void onNotificationClick(Integer position) {
//                Log.i("COSITO", String.valueOf(mMessageList.get(position).getId()));
                //               Intent intent = new Intent(NimbeesClient.getContext(), CustomNotificationDisplayActivity.class);
                //               intent.putExtra(CustomNotificationDisplayActivity.KEY_MESSAGE_CONTENT, mMessageList.get(position).getContent());
//                startActivity(intent);
            }
        });
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView vDate;
        TextView vContent;
        ImageView vImageType;
        ImageView vDisplayed;
        INotificationViewHolderClickListener mListener;

        NotificationViewHolder(View v, INotificationViewHolderClickListener listener) {
            super(v);

            vDate = (TextView) v.findViewById(R.id.message_date);
            vContent = (TextView) v.findViewById(R.id.message_content);
            vImageType = (ImageView) v.findViewById(R.id.notification_type);
            vDisplayed = (ImageView) v.findViewById(R.id.message_displayed);

            mListener = listener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onNotificationClick(getAdapterPosition());
        }

        private interface INotificationViewHolderClickListener {
            void onNotificationClick(Integer position);
        }
    }
}
