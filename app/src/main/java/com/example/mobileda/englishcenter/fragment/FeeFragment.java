package com.example.mobileda.englishcenter.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.activity.RegistorActivity;
import com.example.mobileda.englishcenter.adapter.RegistrationAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeeFragment extends Fragment {

    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private Query mQuery;
    private RegistrationAdapter mAdapter;
    private long sumFee = 0;
    public FeeFragment() { }


    @BindView(R.id.rvCourse)
    RecyclerView rvItems;
    @BindView(R.id.txtSumFee)
    TextView txtSumFee;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fee, container, false);
        ButterKnife.bind(this,rootView);

        rvItems.addItemDecoration(new DividerItemDecoration(rvItems.getContext(), DividerItemDecoration.VERTICAL));
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mQuery = mFirestore.collection("students").document(mAuth.getUid()).collection("courses");//.whereEqualTo(strQuery,FirebaseAuth.getInstance().getUid());

        mAdapter = new RegistrationAdapter(mQuery, new RegistrationAdapter.OnCourseClickListener() {
            @Override
            public void OnCourseClicked(DocumentSnapshot snapshot) {
                Toast.makeText(getActivity(),snapshot.getString("cost"),Toast.LENGTH_SHORT).show();
         }
        });

        rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvItems.setAdapter(mAdapter);

        mFirestore.collection("students/"+mAuth.getUid()+"/courses").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<DocumentSnapshot> lstDS = queryDocumentSnapshots.getDocuments();
                if( lstDS!=null)
                    for(DocumentSnapshot ds :lstDS )
                    {
                        try {
                            String fee = ds.getString("cost").replace(" VNĐ", "");
                            long lfee = Long.parseLong(fee);
                            sumFee += lfee;
                        }
                        catch (Exception a)
                        {
                        }
                        txtSumFee.setText(sumFee + " VNĐ");
                    }
            }
        });

        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        if(mAdapter!=null)
            mAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
    }

}
