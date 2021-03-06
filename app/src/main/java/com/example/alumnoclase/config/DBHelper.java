package com.example.alumnoclase.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DReyna on 18/12/2020.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME  = "DB2021";
    private static final int DB_VERSION = 1;
    public DBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //creamos la tabla de Usuario
        db.execSQL("create table usuarios(_id integer primary key autoincrement, " +"nombre text not null, login not null, clave text not null)");
        db.execSQL("insert into usuarios(nombre, login, clave) values('David Reyna', 'dreyna', '123')");

        //creamos la tabla de Alumno
        db.execSQL("create table alumnos(_id integer primary key autoincrement, " +"nombre text not null, telefono not null, dni text not null, correo text not null)");
        db.execSQL("insert into alumnos(nombre, telefono, dni, correo) values('Fernando Cruz', '991100897', '45275649','Ostin@mail.com')");
        db.execSQL("insert into alumnos(nombre, telefono, dni, correo) values('Jose Taipe', '1111111', '999999','Jose@mail.com')");
        db.execSQL("insert into alumnos(nombre, telefono, dni, correo) values('Reyna', '22222222', '888888888','Reyna@mail.com')");
        db.execSQL("insert into alumnos(nombre, telefono, dni, correo) values('Enji', '3333333', '4444444','Anna@mail.com')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static class Usuarios{
        public static final String TABLE = "usuarios";
        public static final String _ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String LOGIN = "login";
        public static final String CLAVE = "clave";
        public static final String[] COLUMNAS = new String[]{_ID, NOMBRE, LOGIN, CLAVE};
    }

    public static class Alumnos{
        public static final String TABLE = "alumnos";
        public static final String _ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String TELEFONO = "telefono";
        public static final String DNI = "dni";
        public static final String CORREO = "correo";

        public static final String[] COLUMNAS = new String[]{_ID, NOMBRE, TELEFONO, DNI, CORREO};
    }

}
