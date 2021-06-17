package com.example.alumnoclase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alumnoclase.util.Mensajes;

import com.example.alumnoclase.dao.AlumnoDao;
import com.example.alumnoclase.model.Alumno;

public class AlumnoActivity extends AppCompatActivity {
    private EditText edtNombre, edttelefono, edtdni, edtcorreo;
    private AlumnoDao alumnoDAO;
    private Alumno alumno;
    private int idalu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        alumnoDAO = new AlumnoDao(this);

        edtNombre = (EditText) findViewById(R.id.txtNombre);
        edttelefono = (EditText) findViewById(R.id.txtTelefono);
        edtdni = (EditText) findViewById(R.id.txtDNI);
        edtcorreo = (EditText) findViewById(R.id.txtCorreo);

        idalu = getIntent().getIntExtra("ALUMNO_ID",0);
        if(idalu > 0){
            Alumno model = alumnoDAO.buscarAlumnoPorID(idalu);
            edtNombre.setText(model.getNombres());
            edttelefono.setText(model.getTelefono());
            edtdni.setText(model.getDni());
            edtcorreo.setText(model.getCorreo());
            setTitle("Actualizar Alumno");
        }

    }

    @Override
    protected void onDestroy() {
        alumnoDAO.cerrarDB();
        super.onDestroy();
    }




    private void registrar(){
        boolean validar = true;
        String nombre = edtNombre.getText().toString();
        String telefono = edttelefono.getText().toString();
        String dni = edtdni.getText().toString();
        String correo = edtcorreo.getText().toString();

      if(nombre == null || nombre.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.validaAlumno));
        }
        if(telefono == null || telefono.equals("")){
            validar = false;
            edttelefono.setError(getString(R.string.validaTelefono));
        }
        if(dni == null || dni.equals("")){
            validar = false;
            edtdni.setError(getString(R.string.validaDni));
        }
        if(correo == null || correo.equals("")){
            validar = false;
            edtcorreo.setError(getString(R.string.validaCorreo));
        }

        if(validar){
            alumno = new Alumno();
            alumno.setNombres(nombre);
            alumno.setTelefono(telefono);
            alumno.setDni(dni);
            alumno.setCorreo(correo);
            if(idalu > 0){
                alumno.set_id(idalu);
            }
            long resultado = alumnoDAO.modificarAlumno(alumno);
            if(resultado != -1){
                if(idalu > 0) {
                    Mensajes.Msg(this, getString(R.string.msg_alum_modificado));
                    startActivity(new Intent(this, ListAlumnoActivity.class));
                }else{
                    Mensajes.Msg(this, getString(R.string.msg_alum_guardado));
                    startActivity(new Intent(this, ListAlumnoActivity.class));
                }
                finish();
                //startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensajes.Msg(this, getString(R.string.msg_user_error));
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alumno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardar:
                this.registrar();
                break;
            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
