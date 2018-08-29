package com.example.wallysoncunha.wallpaper.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wallysoncunha.wallpaper.Adapters.PhotosAdapter;
import com.example.wallysoncunha.wallpaper.Models.Collection;
import com.example.wallysoncunha.wallpaper.Models.Photo;
import com.example.wallysoncunha.wallpaper.R;
import com.example.wallysoncunha.wallpaper.Utils.GlideApp;
import com.example.wallysoncunha.wallpaper.WebServices.ApiInterface;
import com.example.wallysoncunha.wallpaper.WebServices.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Wallyson Galvão on 29/08/2018.
 */

public class CollectionFragment extends Fragment {

    private final String TAG = CollectionFragment.class.getSimpleName();
    @BindView(R.id.fragment_collection_recyclerview)
    RecyclerView photosRecyclerView;
    @BindView(R.id.fragment_collection_processbar)
    ProgressBar progressBar;
    @BindView(R.id.fragment_collection_user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.fragment_collection_username)
    TextView username;
    @BindView(R.id.fragment_collection_title)
    TextView title;
    @BindView(R.id.fragment_collection_description)
    TextView description;


    private Unbinder unbinder;

    private PhotosAdapter photosAdatper;
    private List<Photo> photos = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        unbinder = ButterKnife.bind(this, view);
        // RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        photosRecyclerView.setLayoutManager(linearLayoutManager);
        photosAdatper = new PhotosAdapter(getActivity(), photos);
        photosRecyclerView.setAdapter(photosAdatper);
        Bundle bundle = getArguments();
        int collectionId = bundle.getInt("collectionId");

        showProgressBar(true);
        getInformationOfCollection(collectionId);
        getPhotosOfCollection(collectionId);
        return view;
    }

    private void getInformationOfCollection(final int collectionId) {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<Collection> call = apiInterface.getInformationOfCollection(collectionId);
        call.enqueue(new Callback<Collection>() {
            @Override
            public void onResponse(@NonNull Call<Collection> call, @NonNull Response<Collection> response) {
                if (response.isSuccessful()) {
                    Collection collection = response.body();
                    title.setText(collection.getTitle());
                    description.setText(collection.getDescription());
                    username.setText(collection.getUser().getUsername());
                    GlideApp
                            .with(getActivity())
                            .load(collection.getUser().getProfileImage().getSmall())
                            .into(userAvatar);

                } else {
                    Log.d(TAG, "Fail" + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Collection> call, @NonNull Throwable t) {
                Log.d(TAG, "Fail: " + t.getMessage());
            }
        });
    }


    private void getPhotosOfCollection(int collectionId) {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Photo>> call = apiInterface.getPhotosOfCollection(collectionId);
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Loading successfully, size: " + response.body().size());
                    for (Photo photo : response.body()) {
                        photos.add(photo);
                        Log.d(TAG, photo.getUrl().getFull());
                    }
                    photosAdatper.notifyDataSetChanged();

                } else {
                    Log.d(TAG, "Fail" + response.message());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                Log.d(TAG, "Fail: " + t.getMessage());
                showProgressBar(false);
            }
        });
    }

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            photosRecyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            photosRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}