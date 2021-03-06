package com.example.android.musicapp;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of components for Green Day category.
 */
public class CategoryGreendayFragment extends Fragment implements CategoryListAdapter.CustomButtonListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_list, container, false);

        // Create a list of categories
        ArrayList<CategoryList> categories = new ArrayList<>();

        categories.add(new CategoryList(R.string.category_greenday, R.drawable.green_day, R.string.category_greenday_description, R.string.category_enter, R.drawable.category_switch));

        // Create an {@link CategoryListAdapter}, whose data source is a list of {@link CategoryList}. The
        // adapter knows how to create list items for each item in the list.
        CategoryListAdapter adapter = new CategoryListAdapter(getActivity(), categories);

        // Linking adapter with custom onClickListener interface from CategoryListAdapter
        adapter.setCustomButtonListner(this);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // category_list.xml layout file.
        ListView listView = view.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link CategoryListAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link CategoryList} in the list.
        listView.setAdapter(adapter);

        return view;
    }

    // This method replace the current fragment with the new one
    private void changeFragment(Fragment fr) {
        getChildFragmentManager().beginTransaction().replace(R.id.main, fr).addToBackStack(null).commit();
    }

    // Custom onClickListener from CategoryListAdapter
    @Override
    public void onButtonClickListner() {

        changeFragment(new PlaylistGreendayFragment());
        //Play sound while pressing the button
        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.click);
        mp.start();
    }
}