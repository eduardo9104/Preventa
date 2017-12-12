package com.systematic.preventa.Fragment;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.systematic.preventa.DataBase.PedidoDB;
import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.Entity.Product;
import com.systematic.preventa.Entity.Tag;
import com.systematic.preventa.R;


import java.util.ArrayList;
import java.util.List;


/**
 * specified {@link ProductFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {
    public final String productCodeTitle = "Codigo: ";
    public final String productPreciosTitle = "Precios: ";

    private final List<Product> mValues;
    private final List<Pedido> mPedidos;
    private List<Product> mValuesFilter;
    private final ProductFragment.OnListFragmentInteractionListener mListener;
    private Context context;

    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private Animator mCurrentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;

    private Boolean is_solicitud;


    public ProductAdapter(List<Product> items, ProductFragment.OnListFragmentInteractionListener listener, Boolean is_solicitud, List<Pedido> pedidos) {
        mValues = items;
        mValuesFilter = items;
        mListener = listener;
        this.is_solicitud = is_solicitud;
        this.mPedidos = pedidos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //holder.mItem = mValues.get(position);


        if (mValuesFilter.size() > 0) {
            Product product = mValuesFilter.get(position);
            holder.mIdView.setText(product.getId());
            holder.mCodeView.setText(productCodeTitle + "\n" + product.getCode());
            holder.precioView.setText(productPreciosTitle + "\n" + product.cuotasStringFormat());
            holder.nameView.setText(product.getName());
            Picasso.with(context).load(R.drawable.applicationsoffice).into(holder.imageView);
        }


//        holder.imageView.setImageResource(R.drawable.ic_add_shopping_cart_black_24dp);

        holder.mProductDescriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                // 2. Chain together various setter methods to set the dialog characteristic
                builder.setMessage(mValuesFilter.get(position).getDescription())
                        .setTitle("Detalle Producto");

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cataloge_id = mValuesFilter.get(position).getId();
               /* if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                }*/
            }
        });

        holder.mComprarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product product = mValues.get(position);

                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                // Get the layout inflater
                LayoutInflater inflater = LayoutInflater.from(view.getContext());

                // 2. Chain together various setter methods to set the dialog characteristic
                View dialog_view = inflater.inflate(R.layout.test, null);
                builder.setView(dialog_view)
                        .setTitle("Cantidad")
                        .setPositiveButton(R.string.action_buy, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                String cantidad = ((EditText)dialog_view.findViewById(R.id.dialog_cantidad)).getText().toString();
                                addPedido(cantidad,product);
                            }
                        })
                        .setNegativeButton(R.string.action_cancel,(dialog, which) -> {
                           dialog.cancel();
                        })
                ;


                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mValuesFilter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mCodeView;
        public final ImageView imageView;
        public final TextView nameView;
        public final TextView precioView;
        public final TextView mProductDescriptionView;
        public final Button mComprarView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mCodeView = (TextView) view.findViewById(R.id.fragment_product_text_code);
            imageView = (ImageView) view.findViewById(R.id.fragment_product_img);
            mProductDescriptionView = view.findViewById(R.id.fragment_product_text_description);
            this.nameView = view.findViewById(R.id.fragment_product_text_name);
            this.precioView = view.findViewById(R.id.fragment_product_text_precios);
            this.mComprarView = view.findViewById(R.id.fragment_product_btn_comprar);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCodeView.getText() + "'";
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mValuesFilter = mValues;
                } else {

                    ArrayList<Product> filteredList = new ArrayList<>();

                    for (Product androidVersion : mValues) {

                        if (androidVersion.getName() != null &&
                                (androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getCode().toLowerCase().contains(charString))) {
                            filteredList.add(androidVersion);


                        } else if (androidVersion.getTags().size() > 0) {
                            int cont = 0;
                            for (Tag tag : androidVersion.getTags()
                                    ) {
                                if (tag.getValue().toLowerCase().contains(charString)) {
                                    cont++;
                                }
                            }

                            if (cont > 0) {
                                filteredList.add(androidVersion);
                            }
                        }
                    }

                    mValuesFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mValuesFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mValuesFilter = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private void addPedido(String cantidad,Product product){
        this.mPedidos.add(PedidoDB.createByCantidadAndProduct(cantidad,product));
    }


}
