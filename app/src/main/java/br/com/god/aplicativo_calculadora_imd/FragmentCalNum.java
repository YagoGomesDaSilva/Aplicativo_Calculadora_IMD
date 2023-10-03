package br.com.god.aplicativo_calculadora_imd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentCalNum extends Fragment {

    public FragmentCalNum() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cal_num, container, false);
        EditText ed_input = view.findViewById(R.id.ed_input);

        this.NumberDotClickListener(view,ed_input);

        return view;
    }

    public void operatorClickListener(View view, EditText ed_input) {

        Button btn_adicao = view.findViewById(R.id.btn_adicao);
        Button btn_subtracao = view.findViewById(R.id.btn_subtracao);
        Button btn_multiplicacao = view.findViewById(R.id.btn_multiplicacao);
        Button btn_divisao = view.findViewById(R.id.btn_divisao);

        btn_adicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_subtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_multiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_divisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calcular(view);

            }
        });

    }

    private void calcular(View view){

    }

    public void NumberDotClickListener(View view, EditText ed_input) {

        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("btn_" + i, "id", getActivity().getPackageName());
            Button button = view.findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ed_input.setText(ed_input.getText() + button.getText().toString());
                }
            });
        }
    }

}
