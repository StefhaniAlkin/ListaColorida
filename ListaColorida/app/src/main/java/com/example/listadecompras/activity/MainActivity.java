package com.example.listadecompras.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listadecompras.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista; // Declaração de um objeto ListView para exibir a lista de itens
    private ArrayList<InsertedText> insertedTexts = new ArrayList<>(); // Declaração de uma lista para armazenar objetos InsertedText
    SQLiteDatabase database; // Declaração de uma instância de banco de dados SQLiteDatabase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da atividade a partir do arquivo XML

        database = openOrCreateDatabase("database", MODE_PRIVATE, null); // Abre ou cria um banco de dados SQLite chamado "database"
        Repository repository = new Repository(); // Cria uma instância de Repository para interagir com o banco de dados
        criarTabela(); // Chama o método para criar a tabela no banco de dados
        lista = findViewById(R.id.lista); // Obtém a referência ao ListView no layout XML
        insertedTexts = repository.getAllTexts(database); // Obtém todos os textos do banco de dados
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, insertedTexts); // Cria um adaptador para a lista de textos
        lista.setAdapter(adaptador); // Define o adaptador para o ListView
        Button btnInserirTxt = findViewById(R.id.btnInserirTxt); // Obtém a referência ao botão no layout XML
        btnInserirTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TextActivity.class); // Cria uma intent para abrir a atividade TextActivity
                startActivity(intent); // Inicia a atividade TextActivity
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InsertedText textoSelecionado = insertedTexts.get(position); // Obtém o texto selecionado da lista
                String mensagem = String.format("Texto: %s, Cor: %s", textoSelecionado.getText(), textoSelecionado.getColor()); // Cria uma mensagem com o texto e a cor
                showToast(mensagem); // Exibe a mensagem em um Toast
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                InsertedText textoSelecionado = insertedTexts.get(position); // Obtém o texto selecionado da lista
                repository.remove(database, textoSelecionado); // Remove o texto do banco de dados
                insertedTexts.remove(textoSelecionado); // Remove o texto da lista
                showToast("Removido da lista com sucesso."); // Exibe uma mensagem informando que o item foi removido
                adaptador.notifyDataSetChanged(); // Notifica o adaptador sobre a alteração na lista
                return true;
            }
        });
    }

    // Método para exibir um Toast com uma mensagem
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Método para criar a tabela no banco de dados
    private void criarTabela() {
        String cmd = "CREATE TABLE IF NOT EXISTS textos " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "texto VARCHAR, " +
                "cor VARCHAR)";
        database.execSQL(cmd); // Executa o comando SQL para criar a tabela
    }
}
