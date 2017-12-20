package com.systematic.preventa.Util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.systematic.preventa.DataBase.PedidoDB;
import com.systematic.preventa.DataBase.StringTitle;
import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.Entity.Product;
import com.systematic.preventa.R;

import java.util.List;

public class AmountFragment {

    public static void generateDialog(View view, Product product, List<Pedido> pedidos, Boolean insert, int pedidoPos) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(view.getContext());

        // 2. Chain together various setter methods to set the dialog characteristic
        View dialog_view = inflater.inflate(R.layout.dialog_add_amount, null);
        Button amount = view.findViewById(R.id.recipe_edit_btn);
        if(amount!=null){
            ((TextView)dialog_view.findViewById(R.id.dialog_cantidad)).setText(pedidos.get(pedidoPos).getParseCantidad());
        }

        builder.setView(dialog_view)
                .setPositiveButton(R.string.action_buy, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String MESSAGE = "Introduzca una cantidad correcta.";
                        String cantidad = ((EditText) dialog_view.findViewById(R.id.dialog_cantidad)).getText().toString();
                        try {
                            int current_amount = Integer.parseInt(cantidad);
                            if (current_amount > 0) {
                                if (insert) {
                                    pedidos.add(PedidoDB.createByCantidadAndProduct(current_amount + "", product));
                                    MESSAGE = "Se ha agregado su pedido.";
                                } else {
                                    pedidos.get(pedidoPos).setCantidad(current_amount);
                                    amount.setText(cantidad);
                                    MESSAGE = "Se ha actualizado su pedido.";
                                }
                            }
                        } catch (NumberFormatException e) {
                        }
                        dialog.dismiss();
                        Toast.makeText(view.getContext(), MESSAGE, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.action_cancel, (dialog, which) ->
                    dialog.cancel()
                )
        ;

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void insertDialog(View view, Product product, List<Pedido> pedidos) {
        AmountFragment.generateDialog(view, product, pedidos, true, -1);
    }

    public static void updateDialog(View view, List<Pedido> pedidos, int pedidoPos) {
        AmountFragment.generateDialog(view, null, pedidos, false, pedidoPos);
    }
}
