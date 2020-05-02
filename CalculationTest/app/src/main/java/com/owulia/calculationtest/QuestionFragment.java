package com.owulia.calculationtest;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owulia.calculationtest.databinding.FragmentQuestionBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {


    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final MyViewModel myViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.NewInstanceFactory())
                .get(MyViewModel.class);
        myViewModel.generator();

        final FragmentQuestionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        final StringBuilder builder = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button0:
                        builder.append(0);
                        break;
                    case R.id.button1:
                        builder.append(1);
                        break;
                    case R.id.button2:
                        builder.append(2);
                        break;
                    case R.id.button3:
                        builder.append(3);
                        break;
                    case R.id.button4:
                        builder.append(4);
                        break;
                    case R.id.button5:
                        builder.append(5);
                        break;
                    case R.id.button6:
                        builder.append(6);
                        break;
                    case R.id.button7:
                        builder.append(7);
                        break;
                    case R.id.button8:
                        builder.append(8);
                        break;
                    case R.id.button9:
                        builder.append(9);
                        break;
                    case R.id.buttonClear:
                        builder.setLength(0);
                        break;
                }
                if (builder.length() == 0) {
                    binding.textView9.setText(getString(R.string.input_indicator));
                } else {
                    binding.textView9.setText(builder.toString());
                }
            }
        };

        binding.button0.setOnClickListener(listener);
        binding.button1.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.buttonClear.setOnClickListener(listener);

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.valueOf(builder.toString()).intValue() == myViewModel.getAnswer().getValue()) {
                    myViewModel.answerCorrent();
                    builder.setLength(0);
                    binding.textView9.setText(R.string.answer_correct_message);
                } else {
                    NavController controller = Navigation.findNavController(view);
                    if (myViewModel.win_flag) {
                        controller.navigate(R.id.action_questionFragment_to_winFragment);
                        myViewModel.win_flag = false;
                        myViewModel.save();
                    } else {
                        controller.navigate(R.id.action_questionFragment_to_loseFragment);
                    }
                }
            }
        });

        return binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_question, container, false);
    }

}
