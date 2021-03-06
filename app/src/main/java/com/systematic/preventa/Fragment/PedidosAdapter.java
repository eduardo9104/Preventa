package com.systematic.preventa.Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.systematic.preventa.DataBase.StringTitle;
import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.R;
import com.systematic.preventa.Util.AmountFragment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link
 * }.
 * TODO: Replace the implementation with code for your data type.
 */
public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.ViewHolder> {

    private final List<Pedido> mValues;
    private final RecipeFragment.OnListFragmentInteractionListener mListener;
    private Context context;
    private View view;


    public PedidosAdapter(List<Pedido> items, RecipeFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

         view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pedido, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getId());
        holder.mEditar.setText(        mValues.get(position).getParseCantidad());
        holder.mNombre.setText(mValues.get(position).getProduct().getName());
        holder.mPrecio.setText(mValues.get(position).getProduct().getCuotas().get(0).getParseMonto());
        Picasso.with(context).load(R.drawable.product).into(holder.mImage);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    mListener.onCalculateTotal();
                }
            }
        });

        holder.mEliminar.setOnClickListener(v -> {
            mValues.remove(position);
            this.notifyItemRemoved(position);
        });

        holder.mEditar.setOnClickListener(v ->
                AmountFragment.updateDialog(view,mValues,position)
        );
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mNombre;
        public final TextView mPrecio;
        public final Button mEditar;
        public final Button mEliminar;
        public final ImageView mImage;

        public Pedido mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.recipe_id);
            mNombre = (TextView) view.findViewById(R.id.recipe_name_value);
            mPrecio = (TextView) view.findViewById(R.id.recipe_price_value);
            mEditar = (Button) view.findViewById(R.id.recipe_edit_btn);
            mEliminar = (Button) view.findViewById(R.id.recipe_delete_btn);
            mImage = (ImageView)view.findViewById(R.id.recipe_image);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}
