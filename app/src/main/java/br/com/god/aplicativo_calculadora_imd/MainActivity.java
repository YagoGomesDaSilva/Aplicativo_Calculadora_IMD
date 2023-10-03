package br.com.god.aplicativo_calculadora_imd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


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
        this.createAlertDialog("Bem Vindo ao App!","Obrigado!");


    }

    void createAlertDialog(String mainMessage, String btnMessage){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(false);
        ad.setMessage(mainMessage);
        ad.setNeutralButton(btnMessage, null);
        ad.show();
    }

    private void swapFragment(int idFrame, Fragment frag){
        FragmentTransaction frag_tran = this.getSupportFragmentManager().beginTransaction();
        frag_tran.replace(idFrame,frag);
        frag_tran.commit();

    }

    public void btnFragmentManagemant(View view){

        int viewId = view.getId();
        if(viewId == R.id.btn_numerica){
            this.swapFragment(R.id.mainFrameFrag,fragCalNum);
            Toast.makeText(this, "Função Calculadora Numerica", Toast.LENGTH_SHORT).show();
        }

        if(viewId == R.id.btn_media){
            this.swapFragment(R.id.mainFrameFrag,fragCalMed);
            Toast.makeText(this, "Função Calculadora de Media", Toast.LENGTH_SHORT).show();
        }
    }
}