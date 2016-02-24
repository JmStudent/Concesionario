package com.salesianos.jose.concesionario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Concesionario extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    //Creamos las variables
    private Spinner mspnModel = null;
    private RadioGroup mrgColor = null;
    private ToggleButton mtgbPrev = null;
    private ImageView mimgvCar = null;
    private CheckBox mCbxAaExtra = null;
    private CheckBox mCbxAcExtra = null;
    private CheckBox mCbxRvExtra = null;
    private CheckBox mCbxSpExtra = null;
    private Button mBtnCalculate = null;
    private TextView mTvTextBox = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concesionario);

        //Damos valores al spinner
        mspnModel = (Spinner) findViewById(R.id.model);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mCar, android.R.layout.simple_spinner_item);
        mspnModel.setAdapter(adapter);

        //Damos valores al resto de variables
        mtgbPrev = (ToggleButton) findViewById(R.id.prevToggle);
        mimgvCar = (ImageView) findViewById(R.id.imgCar);
        mCbxAaExtra = (CheckBox) findViewById(R.id.aaExtra);
        mCbxAcExtra = (CheckBox) findViewById(R.id.acExtra);
        mCbxRvExtra = (CheckBox) findViewById(R.id.rvExtra);
        mCbxSpExtra = (CheckBox) findViewById(R.id.spExtra);

        //Aquí contenemos al radiogroup y controlamos sus acciones
        mrgColor = (RadioGroup) findViewById(R.id.color);
        mrgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.red:
                        mimgvCar.setImageResource(R.drawable.berlina_roja);
                        break;
                    case R.id.green:
                        break;
                    case R.id.blue:
                        break;
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mimgvCar.setVisibility(View.VISIBLE);
        } else {
            mimgvCar.setVisibility(View.GONE);
        }
    }
}
