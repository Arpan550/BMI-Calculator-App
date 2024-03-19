package com.example.bmip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    AppCompatButton button;
    EditText height_ft, height_inch, weight_kg;
    ConstraintLayout main_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ft=Integer.parseInt(height_ft.getText().toString());
                int inch=Integer.parseInt(height_inch.getText().toString());
                int kg=Integer.parseInt(weight_kg.getText().toString());

                float total_inch=12*ft+inch;
                double total_m=0.0254*total_inch;

                double bmi= kg/(total_m*total_m);
                
                if(bmi<18.5){
                    Snackbar.make(main_lay, "Underweight", Snackbar.LENGTH_SHORT).show();
                    main_lay.setBackgroundColor(getResources().getColor(R.color.underweight));

                } else if (bmi>=18.5 && bmi<=24.9) {
                    Snackbar.make(main_lay, "Normal weight", Snackbar.LENGTH_SHORT).show();
                    main_lay.setBackgroundColor(getResources().getColor(R.color.normalweight));
                    
                } else if (bmi>=25 && bmi<=29.9) {
                    Snackbar.make(main_lay, "Overweight", Snackbar.LENGTH_SHORT).show();
                    main_lay.setBackgroundColor(getResources().getColor(R.color.overweight));

                } else{
                    Snackbar.make(main_lay, "Obesity", Snackbar.LENGTH_SHORT).show();
                    main_lay.setBackgroundColor(getResources().getColor(R.color.obesity));
                }
            }
        });
    }

    public  void init(){
        button=findViewById(R.id.btn_calculate);
        height_ft=findViewById(R.id.height_ft);
        height_inch=findViewById(R.id.height_inch);
        weight_kg=findViewById(R.id.weight_kg);
        main_lay=findViewById(R.id.main);
    }
}