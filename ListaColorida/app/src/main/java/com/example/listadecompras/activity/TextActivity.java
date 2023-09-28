package com.example.listadecompras.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listadecompras.R;

public class TextActivity extends AppCompatActivity {
    private EditText editText;
    private RadioGroup radioGroup;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_text);

        // Inicialização de elementos da interface do usuário
        editText = findViewById(R.id.txtTexto);
        radioGroup = findViewById(R.id.radioGroup);
        Button btnInserir = findViewById(R.id.btnInserir);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        // Abertura ou criação de um banco de dados SQLite chamado "database"
        database = openOrCreateDatabase("database", MODE_PRIVATE, null);

        // Criação de uma instância de Repository
        Repository repository = new Repository();

        // Configuração do botão "Inserir" para responder ao clique
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtém o texto inserido pelo usuário
                String text = editText.getText().toString();
                // Obtém o ID do RadioButton selecionado
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (text.isEmpty()) {
                    // Exibe um Toast se o campo de texto estiver vazio
                    Toast.makeText(TextActivity.this, "Digite alguma coisa!", Toast.LENGTH_SHORT).show();
                } else {
                    if (selectedRadioButtonId == -1) {
                        // Exibe um Toast se nenhum RadioButton estiver selecionado
                        Toast.makeText(TextActivity.this, "Escolha uma cor!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Obtém o RadioButton selecionado
                        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                        String color = selectedRadioButton.getText().toString();
                        // Cria um objeto InsertedText com o texto e a cor selecionados
                        InsertedText insertedText = new InsertedText(text, color);

                        // Chama o método save do objeto repository para salvar os dados no banco de dados
                        repository.save(database, insertedText);
                    }
                }
            }
        });

        // Configuração do botão "Cancelar" para responder ao clique
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma Intent para retornar à atividade MainActivity
                Intent intent = new Intent(TextActivity.this, MainActivity.class);
                startActivity(intent); // Inicia a MainActivity
            }
        });
    }
}

