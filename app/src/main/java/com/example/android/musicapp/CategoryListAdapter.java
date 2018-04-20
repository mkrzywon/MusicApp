package com.example.android.musicapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link CategoryListAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link CategoryList} objects.
 */
class CategoryListAdapter extends ArrayAdapter<CategoryList> {

    // Setting the customButtonListener interface to pass the onClick method into category fragments
    private CustomButtonListener customListner;

    public interface CustomButtonListener {
        void onButtonClickListner();
    }

    public void setCustomButtonListner(CustomButtonListener listener) {
        this.customListner = listener;
    }

    /**
     * Create a new {@link CategoryListAdapter} object.
     *
     * @param context    is the current context (i.e. Activity) that the adapter is being created in.
     * @param categories is the list of {@link CategoryList}s to be displayed.
     */
    CategoryListAdapter(Context context, ArrayList<CategoryList> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_category, parent, false);
        }

        // Get the {@link CategoryList} object located at this position in the list
        CategoryList currentCategoryList = getItem(position);

        TextView headText = listItemView.findViewById(R.id.head_text);
        headText.setText(currentCategoryList.getHeadTextId());

        ImageView imageView = listItemView.findViewById(R.id.category_image);
        imageView.setImageResource(currentCategoryList.getCategoryImageId());

        TextView categoryDescription = listItemView.findViewById(R.id.category_description);
        categoryDescription.setText(currentCategoryList.getCategoryDescriptionId());

        TextView categoryEnter = listItemView.findViewById(R.id.category_enter);
        categoryEnter.setText(currentCategoryList.getmCategoryEnter());

        ImageButton categorySwitch = listItemView.findViewById(R.id.playlist_button);
        categorySwitch.setImageResource(currentCategoryList.getmCategoryButton());

        //Listener for the playlist button
        categorySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (customListner != null) {
                    customListner.onButtonClickListner();
                }
            }
        });

        return listItemView;

    }
}

