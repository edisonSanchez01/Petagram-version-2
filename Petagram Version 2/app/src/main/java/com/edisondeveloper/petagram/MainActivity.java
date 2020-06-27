package com.edisondeveloper.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.edisondeveloper.petagram.Model.Mascota;
import com.edisondeveloper.petagram.Adapters.PagerAdapter;
import com.edisondeveloper.petagram.Fragments.MainFragment;
import com.edisondeveloper.petagram.Fragments.PerfilFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Mascota> listPets;
    public static ArrayList<Mascota> listTop;
    public static final String EXTRA_LIST = "list";
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new PerfilFragment());
        tabs = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setIcon(R.drawable.home);
        tabs.getTabAt(1).setIcon(R.drawable.perfil_dog);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_option:
                listTop = new ArrayList<>();
                Collections.sort(listPets, new Comparator<Mascota>() {
                    @Override
                    public int compare(Mascota mascota, Mascota t1) {
                        return Integer.valueOf(t1.getPuntuacion()).compareTo(mascota.getPuntuacion());
                    }
                });
                for(int i=0; i<5; i++){
                    Mascota mascotaTop = listPets.get(i);
                    listTop.add(mascotaTop);
                }
                Intent topActivity = new Intent(this, ActivityTopFive.class);
                topActivity.putParcelableArrayListExtra(EXTRA_LIST, listTop);
                startActivity(topActivity);
                break;
            case R.id.contacto:
                Intent mailActivity = new Intent(this, MailActivity.class);
                startActivity(mailActivity);
                break;
            case R.id.acerca:
                Intent aboutActivity = new Intent(this, AboutActivity.class);
                startActivity(aboutActivity);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}