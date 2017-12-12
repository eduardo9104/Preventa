package com.systematic.preventa.Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.systematic.preventa.DataBase.StringTitle;
import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.Fragment.PedidosFragment.OnListFragmentInteractionListener;
import com.systematic.preventa.R;
import com.systematic.preventa.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.ViewHolder> {

    private final List<Pedido> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public PedidosAdapter(List<Pedido> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pedido, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getId());
        holder.mCantidad.setText(StringTitle.titleCantidad+"\n"+mValues.get(position).getParseCantidad());
        holder.mNombre.setText(StringTitle.titleNombre+"\n"+mValues.get(position).getProduct().getName());
        holder.mPrecio.setText(StringTitle.titlePrecio+"\n"+mValues.get(position).getProduct().getCuotas().get(0).getParseMonto());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

        holder.mEliminar.setOnClickListener(v -> {
            mValues.remove(position);
        });

        holder.mEditar.setOnClickListener(v -> {
            Toast.makeText(context, "Editar",Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mNombre;
        public final TextView mCantidad;
        public final TextView mPrecio;
        public final Button mEditar;
        public final Button mEliminar;

        public Pedido mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.fragment_pedido_id);
            mNombre = (TextView) view.findViewById(R.id.fragment_pedido_text_name);
            mCantidad = (TextView) view.findViewById(R.id.fragment_pedido_text_cantidad);
            mPrecio = (TextView) view.findViewById(R.id.fragment_pedido_text_precio);
            mEditar = (Button) view.findViewById(R.id.fragment_pedido_btn_edit);
            mEliminar = (Button) view.findViewById(R.id.fragment_pedido_btn_delete);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}
