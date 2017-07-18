package com.ushi.radiohandler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ushi.radiohandler.data.Menu;
import com.ushi.radiohandler.fragment.ExampleComplexLayoutFragment;
import com.ushi.radiohandler.fragment.ExampleCustomFragment;
import com.ushi.radiohandler.fragment.ExampleGroupFragment;
import com.ushi.radiohandler.fragment.ExampleRevertibleFragment;
import com.ushi.radiohandler.fragment.ExampleSimpleFragment;
import com.ushi.radiohandler.fragment.MenuFragment;


public class MainActivity extends AppCompatActivity
        implements MenuFragment.MenuItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MenuFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onMenuSelected(MenuFragment fragment, Menu menu) {
        Fragment nextScreenFragment;
        switch (menu) {

            default:
            case SIMPLE:
                nextScreenFragment = ExampleSimpleFragment.newInstance();
                break;

            case COMPLEX_LAYOUT:
                nextScreenFragment = ExampleComplexLayoutFragment.newInstance();
                break;

            case GROUP:
                nextScreenFragment = ExampleGroupFragment.newInstance();
                break;

            case REVERTIBLE:
                nextScreenFragment = ExampleRevertibleFragment.newInstance();
                break;

            case CUSTOM:
                nextScreenFragment = ExampleCustomFragment.newInstance();
                break;
        }

        Fragment currentScreenFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (!nextScreenFragment.getClass().isInstance(currentScreenFragment)) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("menu")
                    .setCustomAnimations(
                            R.anim.fragment_fade_in, R.anim.fragment_fade_out,
                            R.anim.fragment_fade_in, R.anim.fragment_fade_out)
                    .replace(R.id.container, nextScreenFragment)
                    .commit();
        }
    }
}
