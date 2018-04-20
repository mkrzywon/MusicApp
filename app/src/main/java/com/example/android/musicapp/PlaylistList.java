package com.example.android.musicapp;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaylistList implements Parcelable{

    /** Image resource ID for the playlist */
    private final int mplaylistImageId;

    /** String resource ID for the author text of playlist */
    private final int mAuthorId;

    /** String resource ID for the title text of playlist */
    private final int mTilteId;

    /** Audio resource ID for the playlist */
    private final int mAudioResourceId;

    /**
     * Create a new PlaylistList object.
     *
     * @param playlistImageId is the image resource ID for the playlist
     * @param authorId is the string resource Id for the author in playlist
     * @param titleId is the string resource ID for the title in playlist
     * @param audioResourceId is the resource id for audio file for playlist
     */
    public PlaylistList(int playlistImageId, int authorId, int titleId, int audioResourceId) {

        this.mplaylistImageId = playlistImageId;
        this.mAuthorId = authorId;
        this.mTilteId = titleId;
        this.mAudioResourceId = audioResourceId;

    }

    /**
     * Get the image resource ID for image in playlist
     */
    public int getmplaylistimageid() {
        return mplaylistImageId;
    }

    /**
     * Get the string resource ID for the author data in playlist
     */
    public int getmAuthorId() {
        return mAuthorId;
    }

    /**
     * Get the string resource ID for the song title in playlist
     */
    public int getmTilteId() {
        return mTilteId;
    }

    /**
     * Get the audio resource ID for playlist
     */
    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    //write the values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){

        dest.writeInt(mplaylistImageId);
        dest.writeInt(mAuthorId);
        dest.writeInt(mTilteId);
        dest.writeInt(mAudioResourceId);
    }

    //read and set saved values from parcel
    private PlaylistList(Parcel parcel){

        mplaylistImageId = parcel.readInt();
        mAuthorId = parcel.readInt();
        mTilteId = parcel.readInt();
        mAudioResourceId = parcel.readInt();

    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<PlaylistList> CREATOR = new Parcelable.Creator<PlaylistList>(){

        @Override
        public PlaylistList createFromParcel(Parcel parcel) {
            return new PlaylistList(parcel);
        }

        @Override
        public PlaylistList[] newArray(int size) {
            return new PlaylistList[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }

}

