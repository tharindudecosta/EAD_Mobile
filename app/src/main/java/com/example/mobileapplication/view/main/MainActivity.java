package com.example.mobileapplication.view.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobileapplication.R;
import com.example.mobileapplication.databinding.ActivityMainBinding;
import com.example.mobileapplication.view.home.HomeFragment;
import com.example.mobileapplication.view.home.OrdersFragment;
import com.example.mobileapplication.view.home.ProfileFragment;
import com.example.mobileapplication.view.home.SearchFragment;
import com.example.mobileapplication.view.home.ShoppingCartFragment;

/*
* https://youtu.be/jOFLmKMOcK0
* https://youtu.be/f2FHc77CbO0
* https://developer.android.com/develop/ui/views/animations/transitions
* */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.navBarHomeId) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.navBarOrdersId) {
                replaceFragment(new OrdersFragment());
            } else if (itemId == R.id.navBarShoppingCartId) {
                replaceFragment(new ShoppingCartFragment());
            } else if (itemId == R.id.navBarPersonId) {
                replaceFragment(new ProfileFragment());
            } else if (itemId == R.id.navBarSearch){
                replaceFragment(new SearchFragment());
            }


            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_right,  // Enter animation
                R.anim.slide_out_left,  // Exit animation
                R.anim.slide_in_left,   // Pop enter (when coming back from back stack)
                R.anim.slide_out_right  // Pop exit (when going back to the previous fragment)
        );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }



}