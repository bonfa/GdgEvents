package it.gdgbrescia.gdgevents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.List;

public class EventDetailActivity extends AppCompatActivity {

    public static final String EXTRA_EVENT = "EXTRA_EVENT";
    private static final String TAG = EventDetailActivity.class.getSimpleName();

    private Event mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_detail);

        initToolbar();

        getIntentData();

        setUpView();
    }

    private void initToolbar() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void getIntentData() {

        if (getIntent() != null && getIntent().hasExtra(EXTRA_EVENT)) {

            mEvent = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_EVENT));
        } else {

            Log.w(TAG, "event is null - terminating");
        }
    }

    private void setUpView() {

        setUpClickListener();

        fillEventData();
    }

    private void setUpClickListener() {

        final FrameLayout dateFrameLayout = (FrameLayout) findViewById(R.id.date_frame_layout);
        dateFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                openCalendar();
            }
        });

        final FrameLayout placeFrameLayout = (FrameLayout) findViewById(R.id.place_frame_layout);
        placeFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                openMaps();
            }
        });

        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                sendEmail();
            }
        });
    }

    private void fillEventData() {

        final TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
        final TextView dateTextView = (TextView) findViewById(R.id.date_text_view);
        final TextView placeTextView = (TextView) findViewById(R.id.place_text_view);
        final TextView descriptionTextView = (TextView) findViewById(R.id.description_text_view);

        titleTextView.setText(mEvent.getTitle());
        dateTextView.setText(mEvent.getDate());
        placeTextView.setText(mEvent.getLocation());
        descriptionTextView.setText(mEvent.getDescription());
    }

    private void sendEmail() {

        final String emailAddress = mEvent.getEmailAddress();
        final String emailSubject = getString(R.string.template_email_info_subject, mEvent.getTitle());
        final String emailBody = getString(R.string.template_email_info_body);

        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailBody);

        boolean isIntentSafe = isIntentSafe(intent);

        if (isIntentSafe) {

            startActivity(Intent.createChooser(intent, getString(R.string.chooser_title)));
        }
        else {

            Toast.makeText(this, getString(R.string.no_available_email_client_installed), Toast.LENGTH_SHORT).show();
        }
    }

    private void openCalendar() {

        final Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("title", mEvent.getTitle());
        intent.putExtra("description", mEvent.getDescription());
        intent.putExtra("beginTime", mEvent.getDate());
        startActivity(intent);

        boolean isIntentSafe = isIntentSafe(intent);

        if (isIntentSafe) {

            startActivity(Intent.createChooser(intent, getString(R.string.chooser_title)));
        }
        else {

            Toast.makeText(this, getString(R.string.no_available_calendar_client_installed), Toast.LENGTH_SHORT).show();
        }
    }

    private void openMaps() {

        final Uri uri= Uri.parse("http://maps.google.co.in/maps?q=" + mEvent.getLocation());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        boolean isIntentSafe = isIntentSafe(intent);

        if (isIntentSafe) {

            startActivity(Intent.createChooser(intent, getString(R.string.chooser_title)));
        }
        else {

            Toast.makeText(this, getString(R.string.no_available_map_client_installed), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isIntentSafe(final Intent intent) {
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }
}
