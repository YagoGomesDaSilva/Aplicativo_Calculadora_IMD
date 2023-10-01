package br.com.god.aplicativo_calculadora_imd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private FragmentCalMed fragCalMed;
    private FragmentCalNum fragCalNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fragCalMed = new FragmentCalMed();
        this.fragCalNum = new FragmentCalNum();

        this.swapFragment(R.id.mainFrameFrag,fragCalNum);
//
//        Button btn_numerica = this.findViewById(R.id.btn_numerica);
//        Button btn_media = this.findViewById(R.id.btn_media);
    }

    public void btnFragmentMangager(View view){

        int viewId = view.getId();
        if(viewId == R.id.btn_numerica)
            this.swapFragment(R.id.mainFrameFrag,fragCalNum);
        if(viewId == R.id.btn_media)
            this.swapFragment(R.id.mainFrameFrag,fragCalMed);
/*
        switch (view.getId()){
            case R.id.btn_numerica:
                this.swapFragment(R.id.mainFrameFrag,fragCalNum);
                break;
            case R.id.btn_media:
                this.swapFragment(R.id.mainFrameFrag,fragCalMed);
                break;
        }
        */

    }

    private void swapFragment(int idFrame, Fragment frag){
        FragmentTransaction frag_tran = this.getSupportFragmentManager().beginTransaction();
        frag_tran.replace(idFrame,frag);
        frag_tran.commit();
    }

}