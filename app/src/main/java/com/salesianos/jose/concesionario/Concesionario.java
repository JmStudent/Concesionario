package com.salesianos.jose.concesionario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Concesionario extends AppCompatActivity {

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

    //Variables locales para tratar los datos
    private String model = null;
    private int mNumModel = 0; //Posibles valores 0, 1 y 2 para los distintos modelos
    private int mNumColor = 0; //values: 0, 1, 2 (red, green, blue)
    private boolean mostrar = false;
    private int importeBase = 0;
    private int importeExtra0 = 0;
    private int importeExtra1 = 0;
    private int importeExtra2 = 0;
    private int importeExtra3 = 0;

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
        mBtnCalculate = (Button) findViewById(R.id.calculate);
        mTvTextBox = (TextView) findViewById(R.id.textBox);

        //Recogemos el Spinner aquí.
        mspnModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model = mspnModel.getSelectedItem().toString();
                if (model.equalsIgnoreCase("Berlina")) {
                    importeBase = 15000;
                    mostrar = true;
                    mNumModel = 1;
                    if (mNumColor == 0)
                        mimgvCar.setImageResource(R.drawable.berlina_roja);
                    else if (mNumColor == 1)
                        mimgvCar.setImageResource(R.drawable.berlina_verde);
                    else if (mNumColor == 2)
                        mimgvCar.setImageResource(R.drawable.berlina_azul);
                } else if (model.equalsIgnoreCase("Deportivo")) {
                    importeBase = 30000;
                    mostrar = true;
                    mNumModel = 2;
                    if (mNumColor == 0)
                        mimgvCar.setImageResource(R.drawable.deportivo_rojo);
                    else if (mNumColor == 1)
                        mimgvCar.setImageResource(R.drawable.deportivo_verde);
                    else if (mNumColor == 2)
                        mimgvCar.setImageResource(R.drawable.deportivo_azul);
                } else if (model.equalsIgnoreCase("Utilitario")){
                    importeBase = 9000;
                    mostrar = true;
                    mNumModel = 0;
                    if (mNumColor == 0)
                        mimgvCar.setImageResource(R.drawable.utilitario_roja);
                    else if (mNumColor == 1)
                        mimgvCar.setImageResource(R.drawable.utilitario_verde);
                    else if (mNumColor == 2)
                        mimgvCar.setImageResource(R.drawable.utilitario_azul);
                } else {
                    mostrar = false;
                    mimgvCar.setVisibility(View.GONE);
                    mNumColor = 4;
                    mNumModel = 4;
                    mtgbPrev.setChecked(false);
                    importeBase = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Aquí contenemos al radiogroup y controlamos sus acciones
        mrgColor = (RadioGroup) findViewById(R.id.color);
        mrgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.red:
                        mNumColor = 0;
                        if (mNumModel == 0)
                            mimgvCar.setImageResource(R.drawable.utilitario_roja);
                        else if (mNumModel == 1)
                            mimgvCar.setImageResource(R.drawable.berlina_roja);
                        else if (mNumModel == 2)
                            mimgvCar.setImageResource(R.drawable.deportivo_rojo);
                        break;
                    case R.id.green:
                        mNumColor = 1;
                        if (mNumModel == 0)
                            mimgvCar.setImageResource(R.drawable.utilitario_verde);
                        else if (mNumModel == 1)
                            mimgvCar.setImageResource(R.drawable.berlina_verde);
                        else if (mNumModel == 2)
                            mimgvCar.setImageResource(R.drawable.deportivo_verde);
                        break;
                    case R.id.blue:
                        mNumColor = 2;
                        if (mNumModel == 0)
                            mimgvCar.setImageResource(R.drawable.utilitario_azul);
                        else if (mNumModel == 1)
                            mimgvCar.setImageResource(R.drawable.berlina_azul);
                        else if (mNumModel == 2)
                            mimgvCar.setImageResource(R.drawable.deportivo_azul);
                        break;
                }
            }
        });

        //Aquí recogemos la funcionalidad del botón de on/off para mostrar la imagen
        mtgbPrev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mostrar)
                        mimgvCar.setVisibility(View.VISIBLE);
                    else
                        mimgvCar.setVisibility(View.GONE);
                } else {
                    mimgvCar.setVisibility(View.GONE);
                }
            }
        });

        //Manejamos el comportamiento del botón
        mBtnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCbxAaExtra.isChecked())
                    importeExtra0 = 250;
                else
                    importeExtra0 = 0;

                if (mCbxAcExtra.isChecked())
                    importeExtra1 = 150;
                else
                    importeExtra1 = 0;

                if (mCbxRvExtra.isChecked())
                    importeExtra2 = 100;
                else
                    importeExtra2 = 0;

                if (mCbxSpExtra.isChecked())
                    importeExtra3 = 95;
                else
                    importeExtra3 = 0;

                mTvTextBox.setText("El importe de su vehículo es " + (importeBase + importeExtra0 + importeExtra1 + importeExtra2 + importeExtra3));
            }
        });
    }


}
