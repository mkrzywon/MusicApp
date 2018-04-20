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
 * {@link Fragment} that displays a playlist for The Beatles category.
 */
public class PlaylistBeatlesFragment extends Fragment {

    @BindView(R.id.category_back_button) ImageButton categoryBackButton;

    private static final String KEY_IMAGE = "image";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SOUND = "sound";

    public PlaylistBeatlesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playlist_list, container, false);

        ButterKnife.bind(this, view);

        // Create a list of playlist
        final ArrayList<PlaylistList> playlist = new ArrayList<>();

        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b1, R.raw.beatles_any_time_at_all));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b2, R.raw.beatles_birthday));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b3, R.raw.beatles_black_bird));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b4, R.raw.beatles_boys));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b5, R.raw.beatles_cant_buy_me_love));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b6, R.raw.beatles_chains));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b7, R.raw.beatles_come_together));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b8, R.raw.beatles_do_you_wanna_know_a_secret));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b9, R.raw.beatles_free_as_a_bird));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b10, R.raw.beatles_from_me_to_you));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b11, R.raw.beatles_help));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b12, R.raw.beatles_here_comes_the_sun));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b13, R.raw.beatles_hey_jude));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b14, R.raw.beatles_i_saw_her));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b15, R.raw.beatles_i_want_to_hold_your_hand));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b16, R.raw.beatles_it_wont_be_long));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b17, R.raw.beatles_let_it_be));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b18, R.raw.beatles_love_me_do));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b19, R.raw.beatles_lucy_in_the_sky_with_diamonds));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b20, R.raw.beatles_penny_lane));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b21, R.raw.beatles_please_please_me));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b22, R.raw.beatles_real_love));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b23, R.raw.beatles_sexie_sadie));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b24, R.raw.beatles_sgt_peppers_lonely_hearts_club_band));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b25, R.raw.beatles_she_loves_you));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b26, R.raw.beatles_ticket_to_ride));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b27, R.raw.beatles_twist_and_shout));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b28, R.raw.beatles_we_can_work_it_out));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b29, R.raw.beatles_when_im_sixty_four));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b30, R.raw.beatles_with_a_little_help_from_my_friends));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_beatles, R.string.b31, R.raw.beatles_yesterday));

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
