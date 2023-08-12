package com.example.uas10120200;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class AboutApk extends AppCompatActivity {

    private FragmentManager fragmentManager;

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_apk);

        fragmentManager = getSupportFragmentManager();

        final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataForOnBoarding());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, paperOnboardingFragment);
        fragmentTransaction.commit();

    }

    //10120200 - Mochamad Farhan Fadilah Ansori - IF5
    private ArrayList<PaperOnboardingPage> getDataForOnBoarding() {

        PaperOnboardingPage src1 = new PaperOnboardingPage("Tentang Aplikasi", "Aplikasi catatan sederhana berbasis Android " +
                "dengan pemanfaatan Firebase Realtime Database dan Firebase Authentication",
                Color.parseColor("#FFFFFF"), R.drawable.info, R.drawable.icon_about);
        PaperOnboardingPage src2 = new PaperOnboardingPage("Informasi", "Dibuat untuk memenuhi tugas pengganti UAS " +
                "Mata Kuliah Aplikasi Komputasi Bergerak",
                Color.parseColor("#FFFFFF"), R.drawable.info, R.drawable.icon_about);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(src1);
        elements.add(src2);
        return elements;

    }
}