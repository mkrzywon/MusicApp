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
 * {@link Fragment} that displays a playlist for Elvis Presley category.
 */
public class PlaylistElvisFragment extends Fragment {

    @BindView(R.id.category_back_button) ImageButton categoryBackButton;

    private static final String KEY_IMAGE = "image";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SOUND = "sound";

    public PlaylistElvisFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playlist_list, container, false);

        ButterKnife.bind(this, view);

        // Create a list of playlist
        final ArrayList<PlaylistList> playlist = new ArrayList<>();

        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e1, R.raw.elvis_all_shook_up));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e2, R.raw.elvis_are_you_lonesome_tonight));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e3, R.raw.elvis_blue_suede_shoes));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e4, R.raw.elvis_burning_love));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e5, R.raw.elvis_cant_help_falling_in_love));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e6, R.raw.elvis_devil_in_disguise));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e7, R.raw.elvis_dont_be_cruel));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e8, R.raw.elvis_heartbreak_hotel));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e9, R.raw.elvis_his_latest_flame));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e10, R.raw.elvis_hound_dog));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e11, R.raw.elvis_in_the_ghetto));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e12, R.raw.elvis_jailhouse_rock));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e13, R.raw.elvis_return_to_sender));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e14, R.raw.elvis_suspicious_minds));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e15, R.raw.elvis_teddy_bear));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e16, R.raw.elvis_thats_all_right_mama));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_elvis, R.string.e17, R.raw.elvis_viva_las_vegas));

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
