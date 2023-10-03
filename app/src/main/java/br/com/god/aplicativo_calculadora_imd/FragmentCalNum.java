package br.com.god.aplicativo_calculadora_imd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentCalNum extends Fragment {

    float input_buffer = 0;
    String operator_buffer;

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
        Button btn_dot = view.findViewById(R.id.btn_dot);
        Button btn_result = view.findViewById(R.id.btn_result);

        this.resultClickListener(btn_result,ed_input,tv_result);
        this.clearClickListener(btn_clear, ed_input,tv_result);
        this.NumberClickListener(view,ed_input);
        this.operatorClickListener(view,ed_input);
        this.dotClickListener(btn_dot,ed_input);

        return view;
    }

    private void resultClickListener(Button btn_result, EditText ed_input, TextView tv_result) {
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ed_input.getText().toString().trim().isEmpty()){
                    calculate(ed_input);
                    tv_result.setText(String.valueOf(input_buffer));
                    ed_input.setText(tv_result.getText().toString());
                }
            }
        });
    }
    private void dotClickListener(Button btn_dot, EditText ed_input) {
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ed_input.getText().toString().matches(".*\\..*")){

                    if(ed_input.getText().toString().trim().isEmpty()){
                        ed_input.setText("0"+ btn_dot.getText().toString());
                    }else{
                        ed_input.setText(ed_input.getText().toString() + btn_dot.getText().toString());
                    }
                }

            }
        });
    }
    public void clearClickListener(Button button,EditText ed_input, TextView tv_result){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear(ed_input, tv_result);
            }
        });
    }
    public void operatorClickListener(View view, EditText ed_input) {

        for (int i = 1; i <= 4; i++) {
            int buttonId = getResources().getIdentifier("btn_op_" + i, "id", getActivity().getPackageName());
            Button button = view.findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOperator(button,ed_input);
                    ed_input.setText("");
                }
            });
        }
    }
    public void NumberClickListener(View view, EditText ed_input) {

        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("btn_" + i, "id", getActivity().getPackageName());
            Button button = view.findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setNumber(ed_input , button);
                }
            });
        }
    }


    private void setNumber(EditText ed_input , Button button){

        ed_input.setText(ed_input.getText() + button.getText().toString());
    }
    private void calculate(EditText ed_input){

        float ed_input_float = Float.valueOf(ed_input.getText().toString());

        if (operator_buffer.equals("+")){
            this.input_buffer += ed_input_float;
        }
        if (operator_buffer.equals("-")){
            this.input_buffer -= ed_input_float;
        }
        if (operator_buffer.equals("x")){
            this.input_buffer *= ed_input_float;
        }
        if (operator_buffer.equals("/")){
            if(ed_input_float != 0){
                this.input_buffer /= ed_input_float;
            }else{
                Toast.makeText(getActivity(), "Error! Divisão por zero", Toast.LENGTH_LONG).show();
            }
        }

    }
    private void setOperator(Button button, EditText ed_input){

        operator_buffer = button.getText().toString();

        if(!ed_input.getText().toString().isEmpty())
            this.input_buffer = Float.valueOf(ed_input.getText().toString());
    }
    private void clear(EditText ed_input, TextView tv_result){
        ed_input.setText("");
        tv_result.setText("");
        this.input_buffer = 0;
        this.operator_buffer = "";
    }


}
