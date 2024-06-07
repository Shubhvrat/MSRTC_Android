package com.example.msrtc.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.msrtc.R;
import com.example.msrtc.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private TextView txName, txEmail, txPickup, txDestination;
    private FragmentHomeBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        txName = root.findViewById(R.id.txName);
        txEmail = root.findViewById(R.id.txEmail);
        txPickup = root.findViewById(R.id.txPickup);
        txDestination = root.findViewById(R.id.txDestination);

        Bundle args = getArguments();
        if (args != null) {
            txName.setText(args.getString("ename"));
            txEmail.setText(args.getString("email"));
            txPickup.setText(args.getString("pickup"));
            txDestination.setText(args.getString("destin"));
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
