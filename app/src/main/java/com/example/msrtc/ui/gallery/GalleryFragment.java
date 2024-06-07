package com.example.msrtc.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.msrtc.DatabaseHelper;
import com.example.msrtc.R;
import com.example.msrtc.databinding.FragmentGalleryBinding;
import com.example.msrtc.ui.home.HomeFragment;
import com.example.msrtc.ui.home.HomeViewModel;

public class GalleryFragment extends Fragment {
    private TextView tx1;
    private EditText e1,e2,e3,e4;
    private Button b1;
    private FragmentGalleryBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        e1 = root.findViewById(R.id.book_name);
        e2 = root.findViewById(R.id.book_address);
        e3 = root.findViewById(R.id.pickup);
        e4 = root.findViewById(R.id.destination);
        b1 = root.findViewById(R.id.book);                            

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper = new DatabaseHelper(getActivity());

                if (!e1.getText().toString().isEmpty() && !e2.getText().toString().isEmpty() && !e3.getText().toString().isEmpty() && !e4.getText().toString().isEmpty()) {
                    boolean res = databaseHelper.insertBookUser(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString());
                    if (res) {
                        Bundle args = new Bundle();
                        args.putString("ename", e1.getText().toString());
                        args.putString("email", e2.getText().toString());
                        args.putString("pickup", e3.getText().toString());
                        args.putString("destin", e4.getText().toString());

                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_nav_gallery_to_nav_homeshu, args);
                    } else {
                        Toast.makeText(getActivity(), "Bus not booked", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
