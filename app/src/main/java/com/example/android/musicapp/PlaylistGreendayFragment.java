package com.example.android.musicapp;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link Fragment} that displays a playlist for Green Day category.
 */
public class PlaylistGreendayFragment extends Fragment {

    @BindView(R.id.category_back_button) ImageButton categoryBackButton;

    private static final String KEY_IMAGE = "image";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SOUND = "sound";

    public PlaylistGreendayFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playlist_list, container, false);

        ButterKnife.bind(this, view);

        // Create a list of playlist
        final ArrayList<PlaylistList> playlist = new ArrayList<>();

        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g1, R.raw.greenday_16));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g2, R.raw.greenday_1000_hours));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g3, R.raw.greenday_2000_light_years_away));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g4, R.raw.greenday_86));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g5, R.raw.greenday_americanidiot));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g6, R.raw.greenday_are_we_the_waiting));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g7, R.raw.greenday_brainstew));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g8, R.raw.greenday_christie_road));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g9, R.raw.greenday_coming_clean));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g10, R.raw.greenday_extraordinary_girl));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g11, R.raw.greenday_geek_stink_breath));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g12, R.raw.greenday_give_me_novacaine));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g13, R.raw.greenday_good_riddance_time_of_your_life));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g14, R.raw.greenday_hitchin_a_ride));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g15, R.raw.greenday_holiday));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g16, R.raw.greenday_homecoming));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g17, R.raw.greenday_in_the_end));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g18, R.raw.greenday_letterbomb));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g19, R.raw.greenday_longview));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g20, R.raw.greenday_only_of_you));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g21, R.raw.greenday_same_in_the_end));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g22, R.raw.greenday_shes_a_rebel));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g23, R.raw.greenday_st_jimmy));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g24, R.raw.greenday_wake_me_up_when_september_ends));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g25, R.raw.greenday_walking_contradiction));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_greenday, R.string.g26, R.raw.greenday_when_i_come_around));

        // Create an {@link PlaylistAdapter}, whose data source is a list of {@link PlaylistList}. The
        // adapter knows how to create list items for each item in the list.
        PlaylistAdapter adapter = new PlaylistAdapter(getActivity(), playlist);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // playlist_list.xml layout file.
        ListView listView = view.findViewById(R.id.song_playlist);

        // Make the {@link ListView} use the {@link PlaylistAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link PlaylistList} in the list.
        listView.setAdapter(adapter);

        // Back to category button
        categoryBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                //Play sound while pressing the button
                MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.click);
                mp.start();

            }
        });

        //Set a click listener to pass the parcelable values to the PlayerActivity Activity when any of the list items is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                PlaylistList playlistList = playlist.get(position);

                int mplaylistImageId = playlistList.getmplaylistimageid();
                int mAuthorId = playlistList.getmAuthorId();
                int mTilteId = playlistList.getmTilteId();
                int mAudioResourceId = playlistList.getmAudioResourceId();

                Intent intent = new Intent(getActivity().getBaseContext(), PlayerActivity.class);

                intent.putExtra(KEY_IMAGE, mplaylistImageId);
                intent.putExtra(KEY_AUTHOR, mAuthorId);
                intent.putExtra(KEY_TITLE, mTilteId);
                intent.putExtra(KEY_SOUND, mAudioResourceId);
                getActivity().startActivity(intent);

            }
        });

        return view;
    }

    //This method returns to the previous fragment
    private void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

        }
    }
}
