package com.example.bear.programmerdaily.fragment;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bear.programmerdaily.R;
import com.example.bear.programmerdaily.adapter.AppInfoAdapter;
import com.example.bear.programmerdaily.data.db.AppInfoEntity;
import com.example.bear.programmerdaily.data.viewmodel.AppInfoViewModel;

import java.util.List;

public class AppInfoFragment extends Fragment {
    private AppInfoViewModel appInfoViewModel;
    private AppInfoAdapter appInfoAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        appInfoViewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(AppInfoViewModel.class);
//        appInfoViewModel.getAllAppInfos().observe((LifecycleOwner) getActivity(), new Observer<List<AppInfoEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<AppInfoEntity> appInfoEntities) {
//                appInfoAdapter.setAppInfoEntitys(appInfoEntities);
//            }
//        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        appInfoAdapter = new AppInfoAdapter(getActivity());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(appInfoAdapter);
    }
}
