package com.example.mobileda.englishcenter.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Date;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProfileFragment extends Fragment {

    @BindView(R.id.tvName)
    EditText edtName;
    @BindView(R.id.tvDoB)
    EditText edtDob;
    @BindView(R.id.tvLiteracy)
    EditText edtLiteracy;
    @BindView(R.id.tvAddress)
    EditText edtAddress;
    @BindView(R.id.btnOK)
    Button btnOK;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnEdit)
    ImageButton btnEdit;


    @OnClick(R.id.btnEdit)
    public void editProfile(View view) {
        btnCancel.setVisibility(View.VISIBLE);
        btnOK.setVisibility(View.VISIBLE);

        edtAddress.setEnabled(true);
        edtDob.setEnabled(true);
        edtLiteracy.setEnabled(true);
        edtName.setEnabled(true);

        edtAddress.setBackgroundResource(R.drawable.custom_shape1);
        edtDob.setBackgroundResource(R.drawable.custom_shape1);
        edtLiteracy.setBackgroundResource(R.drawable.custom_shape1);
        edtName.setBackgroundResource(R.drawable.custom_shape1);

        edtDob.setHint("dd/MM/YYYY");
        edtName.setHint("Nguyễn Văn A");
        edtAddress.setHint("Số A, hẻm B, Phố C, Thành phố D");
        edtLiteracy.setHint("Kỹ sư A");
    }

    @OnClick(R.id.btnCancel)
    public void cancelEdit(View view) {
        btnCancel.setVisibility(View.INVISIBLE);
        btnOK.setVisibility(View.INVISIBLE);

        edtAddress.setEnabled(false);
        edtDob.setEnabled(false);
        edtLiteracy.setEnabled(false);
        edtName.setEnabled(false);

        edtDob.setHint("");
        edtName.setHint("");
        edtAddress.setHint("");
        edtLiteracy.setHint("");
        setTextInfo();
    }

    @OnClick(R.id.btnOK)
    public void saveProfile(View view) {
        //TODO: replace or add infomation about student
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = edtDob.getText() + "";
            Date dob = formatter.parse(strDate);
            Student student = new Student(edtName.getText()+"",dob,edtLiteracy.getText()+"",edtAddress.getText()+"");
            mFirestore.document("students/"+mAuth.getUid()).set(student);
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),"Sai định dạng ngày",Toast.LENGTH_SHORT).show();
            return;
        }
        btnCancel.setVisibility(View.INVISIBLE);
        btnOK.setVisibility(View.INVISIBLE);

        edtAddress.setEnabled(false);
        edtDob.setEnabled(false);
        edtLiteracy.setEnabled(false);
        edtName.setEnabled(false);

        edtDob.setHint("");
        edtName.setHint("");
        edtAddress.setHint("");
        edtLiteracy.setHint("");

        setTextInfo();
        Toast.makeText(getActivity(), "Đã cập nhật", Toast.LENGTH_LONG).show();
    }

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        setTextInfo();
        return view;
    }

    private void setTextInfo()
    {
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        DocumentReference studentDoc = mFirestore.document("students/" + mAuth.getUid());
        if (studentDoc != null) {
            studentDoc.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                    Student student = documentSnapshot.toObject(Student.class);

                    try {
                        edtName.setText(student.getName());
                        Date date = student.getBirthday();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate =  formatter.format(date);
                        edtDob.setText(strDate);
                        edtLiteracy.setText(student.getLiteracy());
                        edtAddress.setText(student.getAddress());
                    } catch (Exception a) {

                    }
                }
            });
        }
        edtAddress.setBackground(null);
        edtDob.setBackground(null);
        edtLiteracy.setBackground(null);
        edtName.setBackground(null);

    }
}
