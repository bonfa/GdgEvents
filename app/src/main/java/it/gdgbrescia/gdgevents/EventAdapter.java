package it.gdgbrescia.gdgevents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bonfa on 22/02/16.
 *
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private final List<Event> mEventList;
    private final OnEventClickListener mOnEventClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mPlaceTextView;
        public TextView mDateTextView;

        public ViewHolder(final View view) {

            super(view);

            mImageView = (ImageView) view.findViewById(R.id.image_view);
            mTitleTextView = (TextView) view.findViewById(R.id.title_text_view);
            mPlaceTextView = (TextView) view.findViewById(R.id.place_text_view);
            mDateTextView = (TextView) view.findViewById(R.id.date_text_view);
        }

        public void bind(final Event event, final OnEventClickListener onEventClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    onEventClickListener.onEventClick(event);
                }
            });
        }
    }

    public EventAdapter(final List<Event> eventList, final OnEventClickListener onEventClickListener) {

        mEventList = eventList;
        mOnEventClickListener = onEventClickListener;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Event event = mEventList.get(position);

        holder.bind(mEventList.get(position), mOnEventClickListener);

        holder.mImageView.setImageResource(event.getUrlImagePath());
        holder.mTitleTextView.setText(event.getTitle());
        holder.mPlaceTextView.setText(event.getLocation());
        holder.mDateTextView.setText(event.getDate());
    }

    @Override
    public int getItemCount() {

        return mEventList.size();
    }
}
