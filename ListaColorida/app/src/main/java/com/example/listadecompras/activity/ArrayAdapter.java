package com.example.listadecompras.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ArrayAdapter extends android.widget.ArrayAdapter<InsertedText> {
    private Context context;
    private List<InsertedText> values;

    public ArrayAdapter(@NonNull Context context, int resource, @NonNull List<InsertedText> objects) {
        super(context, resource, objects);
        this.context = context;
        this.values = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView; // Inicialização da view que será retornada
        if (view == null) {
            // Verifica se a view está nula e, se estiver, inflata?? uma nova view a partir de um layout predefinido
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        TextView textView = view.findViewById(android.R.id.text1); // Obtém a referência ao TextView dentro da view
        InsertedText item = values.get(position); // Obtém o objeto InsertedText na posição atual da lista

        // Define as cores
        if ("RED".equals(item.getColor())) {
            textView.setTextColor(Color.RED);
        } else if ("GREEN".equals(item.getColor())) {
            textView.setTextColor(Color.GREEN);
        } else if ("BLUE".equals(item.getColor())) {
            textView.setTextColor(Color.BLUE);
        }
        textView.setText(item.getText()); // Define o texto do TextView com base no objeto InsertedText
        return view;
    }
}
