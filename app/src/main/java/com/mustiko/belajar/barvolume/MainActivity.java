package com.mustiko.belajar.barvolume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //todo 2
    private EditText edtWidth;
    private EditText edtHeight;
    private EditText edtLength;
    private Button btnCalculate;
    private TextView tvResult;

    // todo 3
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // kelas ini akan menampilkan tampilan dari layout activity_main
        setContentView(R.layout.activity_main);

        // casting view
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_heigth);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        // event ketika sebuah button di click
        // this merujuk pada obyek activity saat ini (yang telah mengimplementasikan listener)
        btnCalculate.setOnClickListener(this);

        // menngambil data dari saveinstancestated
        // tetapi terlebih dahulu di cek apakah saveinstancestated kosong atau tidak datanya
        if (savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_calculate) {

            // mengambil setiap isian dari editText lalu menyimpannya kedalam variabel
            // trim() untuk menghiraukan setiap spasi jika ada
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtLength.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edtWidth.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleh kosong");
            }

            // !isEmptyFields --> "jika semua inputan tidak kosong"
            // !isEmptyFields (false nilai akhirnya) = true (karena kurung didalam if harus dijalankan ketika nilainya true) --> maka bla..bla..bla..
            if (!isEmptyFields){
                double volume = Double.valueOf(inputLength)
                        * Double.valueOf(inputWidth)
                        * Double.valueOf(inputHeight);
                tvResult.setText(String.valueOf(volume));
            }
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // data apa yang akan di simpan di dalam saveinstancestate
        // yaitu data isian dari tvresult di simpan di dalam key dari savedinstacestate
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
}
