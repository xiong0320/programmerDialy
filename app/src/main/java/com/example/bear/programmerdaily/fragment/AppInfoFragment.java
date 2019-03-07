package com.example.bear.programmerdaily.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bear.programmerdaily.R;
import com.example.bear.programmerdaily.adapter.AppInfoAdapter;

public class AppInfoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.recycler_view_frag,container);
        initRecyclerView();
        return view;
    }
    private void initRecyclerView() {
        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
        final AppInfoAdapter appInfoAdapter = new AppInfoAdapter(getActivity());
        recyclerView.setAdapter(appInfoAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
