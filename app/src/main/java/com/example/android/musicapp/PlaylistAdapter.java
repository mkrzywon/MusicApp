package com.example.android.musicapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link PlaylistAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link PlaylistList} objects.
 */
public class PlaylistAdapter extends ArrayAdapter<PlaylistList> {

    /**
     * Create a new {@link PlaylistAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param categories is the list of {@link PlaylistList}s to be displayed.
     */
    PlaylistAdapter(Context context, ArrayList<PlaylistList> categories) {
        super(context, 0, categories);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_playlist, parent, false);
        }

        // Get the {@link PlaylistList} object located at this position in the list
        PlaylistList currentPlaylist = getItem(position);

        ImageView soundImage = listItemView.findViewById(R.id.sound_image);
        soundImage.setImageResource(currentPlaylist.getmplaylistimageid());

        TextView author = listItemView.findViewById(R.id.author);
        author.setText(currentPlaylist.getmAuthorId());

        TextView songTitle = listItemView.findViewById(R.id.song_title);
        songTitle.setText(currentPlaylist.getmTilteId());

        return listItemView;

    }
}