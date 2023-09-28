package com.example.listadecompras.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.listadecompras.activity.InsertedText;

public class Repository {
    @SuppressLint("Range")
    public ArrayList<InsertedText> getAllTexts(SQLiteDatabase bd) {
        ArrayList<InsertedText> textList = new ArrayList<>();
        String cmd = "SELECT id, texto, cor FROM textos";
        Cursor dadosLidos = bd.rawQuery(cmd, null);
        while (dadosLidos.moveToNext()) {
            InsertedText insertedText = new InsertedText();
            insertedText.setId(dadosLidos.getInt(dadosLidos.getColumnIndex("id")));
            insertedText.setText(dadosLidos.getString(dadosLidos.getColumnIndex("texto")));
            insertedText.setColor(dadosLidos.getString(dadosLidos.getColumnIndex("cor")));
            textList.add(insertedText);
        }
        return textList;
    }

    public void save(SQLiteDatabase bd, InsertedText insertedText){
        String cmd = String.format("INSERT INTO textos (texto, cor) VALUES ('%s', '%s')", insertedText.getText(), insertedText.getColor());
        bd.execSQL( cmd );
    }

    public void remove(SQLiteDatabase bd, InsertedText insertedText){
        String cmd = String.format("DELETE FROM textos WHERE id = %s", insertedText.getId());
        bd.execSQL( cmd );
    }

}