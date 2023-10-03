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


    float input_buffer;
    String operator_buffer;
    boolean operator_select = false;

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
        this.operatorClickListener(view);
        this.dotClickListener(btn_dot,ed_input);

        return view;
    }

    private void resultClickListener(Button btn_result, EditText ed_input, TextView tv_result) {
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calculate(ed_input);
                ed_input.setText("");
                tv_result.setText(String.valueOf(input_buffer));
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
        if(!this.operator_select){
            ed_input.setText(ed_input.getText() + button.getText().toString());
        }
        else {
            this.operator_select = false;
            this.calculate( ed_input );
            ed_input.setText("");
            ed_input.setText(ed_input.getText() + button.getText().toString());
        }
    }
    private void setOperator(Button button){
        if(!operator_select){
            operator_buffer = button.getText().toString();
            operator_select = true;
        }
    }
    private void clear(EditText ed_input, TextView tv_result){
        ed_input.setText("");
        tv_result.setText("");
        this.input_buffer = 0;
        this.operator_buffer = "";
        this.operator_select = false;
    }
    private void calculate(EditText ed_input){

        float ed_input_float = Float.valueOf(ed_input.getText().toString());

        if (operator_buffer.equals("+")){
           this.input_buffer =+ ed_input_float;
        }
        if (operator_buffer == "-"){

        }
        if (operator_buffer == "*"){

        }
        if (operator_buffer == "/"){

        }

    }

}
