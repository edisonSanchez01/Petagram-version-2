package com.edisondeveloper.petagram.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edisondeveloper.petagram.Model.Mascota;
import com.edisondeveloper.petagram.Adapters.MascotaAdapter;
import com.edisondeveloper.petagram.R;

import java.util.ArrayList;

import static com.edisondeveloper.petagram.MainActivity.listPets;

public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipRefreshLayout);
        listPets = new ArrayList<>();
        listPets.add(new Mascota(getString(R.string.mascot01),R.drawable.mascotas_img_01,3));
        listPets.add(new Mascota(getString(R.string.mascot02),R.drawable.mascotas_img_02, 5));
        listPets.add(new Mascota(getString(R.string.mascot03),R.drawable.mascotas_img_03,6));
        listPets.add(new Mascota(getString(R.string.mascot04),R.drawable.mascotas_img_04, 2));
        listPets.add(new Mascota(getString(R.string.mascot05),R.drawable.mascotas_img_05, 1));
        listPets.add(new Mascota(getString(R.string.mascot06),R.drawable.mascotas_img_06, 9));
        listPets.add(new Mascota(getString(R.string.mascot07),R.drawable.mascotas_img_07, 7));
        listPets.add(new Mascota(getString(R.string.mascot08),R.drawable.mascotas_img_08, 10));
        listPets.add(new Mascota(getString(R.string.mascot09),R.drawable.mascotas_img_09, 8));
        listPets.add(new Mascota(getString(R.string.mascot10),R.drawable.mascotas_img_10,5));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        final MascotaAdapter adapter = new MascotaAdapter(getActivity(), listPets);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }
}