package com.example.phantan.ktmt.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phantan.ktmt.controller.Operator;
import com.example.phantan.ktmt.utils.Constant;
import com.example.phantan.ktmt.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner mSpinner;
    private EditText mEditText1;//input for convert
    private Button mButton1;//  convert number system
    private TextView mTextView1;// result of converter
    private int codeActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinner = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<>();
        list.add("10");
        list.add("2");
        list.add("8");
        list.add("16");
        ArrayAdapter<String> mStringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        mStringArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpinner.setAdapter(mStringArrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case Constant.DECIMAL_INPUT:
                        codeActive = Constant.DECIMAL_INPUT;
                        mEditText1.setText("");
                        mTextView1.setText("");
                        break;
                    case Constant.BINARY_INPUT:
                        codeActive = Constant.BINARY_INPUT;
                        mEditText1.setText("");
                        mTextView1.setText("");
                        break;
                    case Constant.OCTAL_INPUT:
                        codeActive = Constant.OCTAL_INPUT;
                        mEditText1.setText("");
                        mTextView1.setText("");
                        break;
                    case Constant.HEX_INPUT:
                        codeActive = Constant.HEX_INPUT;
                        mEditText1.setText("");
                        mTextView1.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mEditText1 = (EditText) findViewById(R.id.editText1);
        mEditText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();
                String output = "";
                String output1 = "", output2 = "", output3 = "";

                String binary = " -Hệ 2-\n", decimal = " -Hệ 10-\n", octal = " -Hệ 8-\n", hex = " -Hệ 16-\n";
                try {
                    switch (codeActive) {
                        case Constant.DECIMAL_INPUT:
                            if (Operator.checkDecimalPattern(input)) {
                                output1 = (Operator.numberConverter(10, 2, input)) + binary;
                                output2 = (Operator.numberConverter(10, 8, input)) + octal;
                                output3 = (Operator.numberConverter(10, 16, input)) + hex;
                            } else {
                                output = "Input không có trong hệ 10";
                            }
                            break;
                        case Constant.BINARY_INPUT:
                            if (Operator.checkBinaryPattern(input)) {
                                output1 = (Operator.numberConverter(2, 8, input)) + octal;
                                output2 = (Operator.numberConverter(2, 10, input)) + decimal;
                                output3 = (Operator.numberConverter(2, 16, input)) + hex;
                            } else {
                                output = "Input không có trong hệ 2";
                            }
                            break;
                        case Constant.OCTAL_INPUT:
                            if (Operator.checkOctalPattern(input)) {
                                output1 = (Operator.numberConverter(8, 2, input)) + binary;
                                output2 = (Operator.numberConverter(8, 10, input)) + decimal;
                                output3 = (Operator.numberConverter(8, 16, input)) + hex;
                            } else {
                                output = "Input không có trong hệ 8";
                            }
                            break;
                        case Constant.HEX_INPUT:
                            if (Operator.checkHexPattern(input)) {
                                output1 = (Operator.numberConverter(16, 2, input)) + binary;
                                output2 = (Operator.numberConverter(16, 8, input)) + octal;
                                output3 = (Operator.numberConverter(16, 10, input)) + decimal;
                            } else {
                                output = "Input không có trong hệ 16";
                            }
                            break;

                    }
                    output += output1 + output2 + output3;
                    Log.d("txt", output + "");
                    mTextView1.setText(output + "");
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    mTextView1.setText("");
                }
            }
        });
        mTextView1 = (TextView) findViewById(R.id.textView1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
