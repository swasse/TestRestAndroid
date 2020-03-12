package be.ehb.testrest.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import be.ehb.testrest.R;
import be.ehb.testrest.model.JokeViewModel;
import be.ehb.testrest.model.RandomJoke;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private ImageView iv;
    private TextView tv;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        iv = rootView.findViewById(R.id.iv_icon);
        tv = rootView.findViewById(R.id.tv_joke);

        JokeViewModel jokeViewModel = new ViewModelProvider(getActivity()).get(JokeViewModel.class);
        jokeViewModel.getJokeOfTheDay().observe(getActivity(), new Observer<RandomJoke>() {
            @Override
            public void onChanged(RandomJoke randomJoke) {
                Picasso.get().load(randomJoke.getImageUrl()).into(iv);
                tv.setText(randomJoke.getJokeText());
            }
        });

        return rootView;
    }

}
