package br.com.god.aplicativo_calculadora_imd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentCalNum extends Fragment {


    String input_buffer;
    String operator_buffer;
    boolean opetator_select = false;

    public FragmentCalNum() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cal_num, container, false);

        EditText ed_input = view.findViewById(R.id.ed_input);
        TextView tv_result = view.findViewById(R.id.tv_result);
        Button btn_clear = view.findViewById(R.id.btn_clear);


        this.clearClickListener(btn_clear, ed_input,tv_result);
        this.NumberDotClickListener(view,ed_input);
        this.operatorClickListener(view);

        return view;
    }

    public void clearClickListener(Button button,EditText ed_input, TextView tv_result){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear(ed_input, tv_result);
            }
        });
    }

    public void operatorClickListener(View view) {

        for (int i = 1; i <= 4; i++) {
            int buttonId = getResources().getIdentifier("btn_op_" + i, "id", getActivity().getPackageName());
            Button button = view.findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOperator(button);
                }
            });
        }
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

    private void setOperator(Button button){
        if(!opetator_select){
            operator_buffer = button.getText().toString();
            opetator_select = true;
            //ed_input.setText(ed_input.getText() + button.getText().toString());
        }
    }

    private void clear(EditText ed_input, TextView tv_result){
        ed_input.setText("");
        tv_result.setText("");
        this.input_buffer = "";
        this.operator_buffer = "";
        this.opetator_select = false;
    }

}
