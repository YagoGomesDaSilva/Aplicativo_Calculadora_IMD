package br.com.god.aplicativo_calculadora_imd;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentCalMed extends Fragment {

    public FragmentCalMed() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cal_med, container, false);

        List<EditText> ed_inputs =this.set_ed_inputs_id(view);
        TextView tv_situacao_info = view.findViewById(R.id.tv_situacao_info);
        Button btn_calcular = view.findViewById(R.id.btn_calcular);

        this.calculateClickListener(btn_calcular,tv_situacao_info,ed_inputs);

        return view;
    }

    private List<EditText> set_ed_inputs_id(View view ){
        List<EditText> ed_inputs = new ArrayList<>();
        for(int i =1; i <= 3; i++){
            int edit_text_id = getResources().getIdentifier("ed_input_n"+i,"id",getActivity().getPackageName());
            ed_inputs.add(view.findViewById(edit_text_id));
        }
        return ed_inputs;
    }
    private void calculateClickListener(Button btn_calcular,TextView tv_situacao_info,List<EditText> ed_inputs){
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tv_situacao_info.getText().toString().trim().isEmpty()){
                    clear(tv_situacao_info,ed_inputs);
                }
                else{
                    calculate(tv_situacao_info,ed_inputs);
                }
            }
        });
    }
    private void calculate(TextView tv_situacao_info, List<EditText> ed_inputs){

        int edCountEmpty = countEditTextEmpty(ed_inputs);

        if(edCountEmpty == 3)
        {
            Toast.makeText(getActivity(), "Digite ao menos uma nota!", Toast.LENGTH_LONG).show();
        }
        else
        {
            float resulMedia = calculateMedia(ed_inputs,edCountEmpty);

            if(edCountEmpty == 2 || edCountEmpty == 1){
                exibirToast(resulMedia);
                clearEditText(ed_inputs);
            }
            else
            {
                if(resulMedia >= 7.0){
                    tv_situacao_info.setText("APROVADO " + resulMedia);
                }
                else if(resulMedia >= 5.0)
                {
                    tv_situacao_info.setText("APROVADO POR NOTA " + resulMedia);
                }
                else
                {
                    tv_situacao_info.setText("REPROVADO " + resulMedia);
                }
            }
        }
    }
    private void exibirToast(float resulMedia) {
        Toast.makeText(getActivity(), "Falta " + (21.0-resulMedia) + " para Aprovação\n Falta " + (15.0-resulMedia) + " para Aprovação por Nota", Toast.LENGTH_LONG).show();
    }
    private float calculateMedia(List<EditText> edInputs, int edCountEmpty) {

        if(edCountEmpty == 2)
        {   auxmedia(edInputs);
            return (Float.valueOf(edInputs.get(0).getText().toString()) +
                    Float.valueOf(edInputs.get(1).getText().toString()) +
                    Float.valueOf(edInputs.get(2).getText().toString()) );
        }
        if(edCountEmpty == 1)
        {   auxmedia(edInputs);
            return (Float.valueOf(edInputs.get(0).getText().toString()) +
                    Float.valueOf(edInputs.get(1).getText().toString()) +
                    Float.valueOf(edInputs.get(2).getText().toString()) ) /2;
        }
        if(edCountEmpty == 0)
        {  auxmedia(edInputs);
           return  (Float.valueOf(edInputs.get(0).getText().toString()) +
                    Float.valueOf(edInputs.get(1).getText().toString()) +
                    Float.valueOf(edInputs.get(2).getText().toString()) ) /3;
        }
        return 0;
    }
    private void auxmedia(List<EditText> ed_inputs){
        for (EditText editText : ed_inputs) {
            String texto = editText.getText().toString().trim();
            if (texto.isEmpty()) {
                editText.setText("0");
            }
        }
    }
    private void clear(TextView tv_situacao_info,List<EditText> ed_inputs){
        for (EditText editText : ed_inputs) {
            String texto = editText.getText().toString().trim();
            if (!texto.isEmpty()) {
                editText.setText("");
            }
        }
        tv_situacao_info.setText("");
    }
    private void clearEditText(List<EditText> ed_Inputs){
        for (EditText editText : ed_Inputs) {

            if (editText.getText().toString().trim().equals("0")) {
                editText.setText("");
            }
        }
    }
    private int countEditTextEmpty(List<EditText> ed_inputs){
        int edEmpty = 0;
        for (EditText ed : ed_inputs) {
            String txt = ed.getText().toString().toString().trim();
            if(txt.isEmpty())
                edEmpty++;
        }
        return edEmpty;
    }
}