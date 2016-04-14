package it.gdgbrescia.gdgevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int EVENT_SIZE = 6;
    private List<Event> mEventList;
    private String[] mEventTitleArray;
    private String[] mEventLocationArray;
    private String[] mEventDateArray;
    private String[] mEventDescriptionArray;
    private String[] mEventEmailAddressArray;
    private int[] mEventUrlImagePathArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initToolbar();

        getArrays();

        setUpEventList();

        setUpRecyclerView();
    }

    private void initToolbar() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }


    private void getArrays() {

        mEventTitleArray = getResources().getStringArray(R.array.event_title_array);
        mEventLocationArray = getResources().getStringArray(R.array.event_location_array);
        mEventDateArray = getResources().getStringArray(R.array.event_date_array);
        mEventDescriptionArray = getResources().getStringArray(R.array.event_description_array);
        mEventEmailAddressArray = getResources().getStringArray(R.array.event_email_array);
        mEventUrlImagePathArray = new int[]{
                R.mipmap.event_01,
                R.mipmap.event_02,
                R.mipmap.event_02,
                R.mipmap.event_02,
                R.mipmap.event_05,
                R.mipmap.event_06
        };
    }

    private void setUpEventList() {

        mEventList = new ArrayList<>();

        for (int i = 0; i < EVENT_SIZE; i++) {

            mEventList.add(getEvent(i));
        }
    }

    private void setUpRecyclerView() {

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.events_recycler_view);

        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.Adapter adapter = new EventAdapter(mEventList, new OnEventClickListener() {

            @Override
            public void onEventClick(final Event event) {

                openEventDetailActivity(event);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private Event getEvent(final int i) {

        final String title = mEventTitleArray[i];
        final String location = mEventLocationArray[i];
        final String date = mEventDateArray[i];
        final String description = mEventDescriptionArray[i];
        final String emailAddress = mEventEmailAddressArray[i];
        final int imageUrl = mEventUrlImagePathArray[i];

        return new Event(i, title, location, date, description, emailAddress, imageUrl);
    }

    private void openEventDetailActivity(final Event event) {

        final Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
        intent.putExtra(EventDetailActivity.EXTRA_EVENT, Parcels.wrap(event));
        startActivity(intent);
    }


}
