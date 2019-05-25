package com.takatutustudio.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String STATE_RESULT ="state_result"; //pergantian orientasi (portrait-landscape) pada peranti
    EditText edtpanjang, edtlebar, edttinggi;
    Button btnhitung;
    TextView tvhasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtpanjang = findViewById(R.id.edt_panjang);
        edtlebar = findViewById(R.id.edt_lebar);
        edttinggi = findViewById(R.id.edt_tinggi);
        btnhitung = findViewById(R.id.btn_hitung);
        tvhasil = findViewById(R.id.tv_hasil);

        btnhitung.setOnClickListener(this);

        //pergantian orientasi (portrait-landscape) pada peranti
        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvhasil.setText(result);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_hitung) {
            String inputLength = edtpanjang.getText().toString().trim();
            String inputWidth = edtlebar.getText().toString().trim();
            String inputHeight = edttinggi.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtpanjang.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edtlebar.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edttinggi.setError("Field ini tidak boleh kosong");
            }

            Double lenght = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (lenght == null) {
                isInvalidDouble = true;
                edtpanjang.setError("Field ini harus berupa nomor yang valid");
            }

            if (width ==  null) {
                isInvalidDouble = true;
                edtlebar.setError("Field ini harus berupa nomor yang valid");
            }

            if (height == null) {
                isInvalidDouble = true;
                edttinggi.setError("Field ini harus berupa nomor yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = lenght * width * height;

                tvhasil.setText(String.valueOf(volume));
            }
        }

    }

    //pergantian orientasi (portrait-landscape) pada peranti
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvhasil.getText().toString());
    }
    Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
