package com.example.whatnextapp;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    CheckBox cbChess, cbCarrom, cbCricket, cbFootBall;
    Button btnWhatsApp, btnEmail;

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

        etName = findViewById(R.id.etName);
        cbCarrom = findViewById(R.id.cbCarrom);
        cbChess = findViewById(R.id.cbChess);
        cbCricket = findViewById(R.id.cbCricket);
        cbFootBall = findViewById(R.id.cbFootBall);
        btnWhatsApp = findViewById(R.id.btnWhatsApp);
        btnEmail = findViewById(R.id.btnEmail);

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = etName.getText().toString();
                if(n.isBlank()) {
                    Toast.makeText(MainActivity.this, "name shud not be empty", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }

                String hobbies = "";
                if (cbCarrom.isChecked()) hobbies += " Carrom ";
                if (cbChess.isChecked()) hobbies += " Chess ";
                if (cbCricket.isChecked()) hobbies += " Cricket ";
                if (cbFootBall.isChecked()) hobbies += " FootBall ";

                String msg = " Name = " + n + " Hobbies " + hobbies;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Sharing from my App");
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "whatsapp not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = etName.getText().toString();
                if(n.isBlank()) {
                    Toast.makeText(MainActivity.this, "name shud not be empty", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }

                String hobbies = "";
                if (cbCarrom.isChecked()) hobbies += " Carrom ";
                if (cbChess.isChecked()) hobbies += " Chess ";
                if (cbCricket.isChecked()) hobbies += " Cricket ";
                if (cbFootBall.isChecked()) hobbies += " FootBall ";

                String msg = " Name = " + n + " Hobbies " + hobbies;
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"eshavalunjj@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hobbie of "+ n);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }
}