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
 * {@link Fragment} that displays a playlist for The Rolling Stones category.
 */
public class PlaylistStonesFragment extends Fragment {

    @BindView(R.id.category_back_button) ImageButton categoryBackButton;

    private static final String KEY_IMAGE = "image";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SOUND = "sound";

    public PlaylistStonesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playlist_list, container, false);

        ButterKnife.bind(this, view);

        // Create a list of playlist
        final ArrayList<PlaylistList> playlist = new ArrayList<>();

        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s1, R.raw.stones_19th_nervous_breakdown));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s2, R.raw.stones_angie_1));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s3, R.raw.stones_anybody_seen_my_baby));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s4, R.raw.stones_as_tearsgo_by));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s5, R.raw.stones_beast_of_burden));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s6, R.raw.stones_brown_sugar));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s7, R.raw.stones_cant_be_seen));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s8, R.raw.stones_fool_to_cry));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s9, R.raw.stones_gimme_shelter));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s10, R.raw.stones_honky_tonk_women));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s11, R.raw.stones_its_all_over_now));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s12, R.raw.stones_its_only_rock_n_roll));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s13, R.raw.stones_jumpin_jack_flash_1));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s14, R.raw.stones_lets_spend_the_night_together));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s15, R.raw.stones_love_is_strong));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s16, R.raw.stones_miss_you_1));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s17, R.raw.stones_mothers_little_helpers));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s18, R.raw.stones_paint_it_black));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s19, R.raw.stones_ruby_tuesday));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s20, R.raw.stones_satisfaction));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s21, R.raw.stones_shes_so_cold));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s22, R.raw.stones_start_me_up));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s23, R.raw.stones_time_is_on_my_side));
        playlist.add(new PlaylistList(R.drawable.sound_image, R.string.playlist_stones, R.string.s24, R.raw.stones_you_cant_always_get_what_you_want_1));

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
