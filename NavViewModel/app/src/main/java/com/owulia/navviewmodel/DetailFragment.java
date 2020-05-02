package com.owulia.navviewmodel;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owulia.navviewmodel.databinding.FragmentDetailBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MyViewModel myViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory())
                .get(MyViewModel.class);

        FragmentDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_detailFragment_to_masterFragment);
            }
        });

        return binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}
