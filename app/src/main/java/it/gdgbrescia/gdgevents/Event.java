package it.gdgbrescia.gdgevents;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by bonfa on 22/02/16.
 *
 */
@Parcel
public class Event {

    private final int mId;
    private final String mTitle;
    private final String mLocation;
    private final String mDate;
    private final String mDescription;
    private final String mEmailAddress;
    private final int mUrlImagePath;

    @ParcelConstructor
    public Event(final int mId, final String mTitle, final String mLocation, final String mDate, final String mDescription, final String mEmailAddress, final int mUrlImagePath) {

        this.mId = mId;
        this.mTitle = mTitle;
        this.mLocation = mLocation;
        this. mDate = mDate;
        this. mDescription = mDescription;
        this. mEmailAddress = mEmailAddress;
        this.mUrlImagePath = mUrlImagePath;
    }

    @Override
    public String toString() {
        return "Event{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mLocation='" + mLocation + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mEmailAddress='" + mEmailAddress + '\'' +
                ", mUrlImagePath=" + mUrlImagePath +
                '}';
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public int getUrlImagePath() {
        return mUrlImagePath;
    }
}
