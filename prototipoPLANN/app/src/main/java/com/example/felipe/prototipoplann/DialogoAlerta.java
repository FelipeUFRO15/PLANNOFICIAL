package com.example.felipe.prototipoplann;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


/**
 * Created by Felipe on 25-09-2016.
 */
public class DialogoAlerta extends DialogFragment {

    private String mensaje;
    private String titulo;
    private String boton;

    public void setBuilder(String m, String t, String b){
        this.mensaje = m;
        this.titulo = t;
        this.boton = b;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(this.mensaje)
                .setTitle(this.titulo)
                .setPositiveButton(this.boton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

}
