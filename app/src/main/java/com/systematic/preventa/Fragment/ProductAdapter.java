package com.systematic.preventa.Fragment;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.systematic.preventa.DataBase.PedidoDB;
import com.systematic.preventa.DataBase.StringTitle;
import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.Entity.Product;
import com.systematic.preventa.Entity.Tag;
import com.systematic.preventa.R;
import com.systematic.preventa.Util.AmountFragment;


import java.util.ArrayList;
import java.util.List;


/**
 * specified {@link ProductFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {
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
            holder.mCodeView.setText("");
            holder.retail_priceviewView.setText(StringTitle.titleWholePrice +product.getCuotas().get(0).getMonto());
            holder.wholesale_priceView.setText(StringTitle.titleRetailPrice + product.getCuotas().get(1).getMonto());
            holder.nameView.setText(product.getName());
            Picasso.with(context).load(R.drawable.product).into(holder.imageView);
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
                AmountFragment.insertDialog(view,product,mPedidos);
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
        public final TextView wholesale_priceView;
        public final TextView retail_priceviewView;
        public final TextView mProductDescriptionView;
        public final FloatingActionButton mComprarView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mCodeView = (TextView) view.findViewById(R.id.fragment_product_text_code);
            imageView = (ImageView) view.findViewById(R.id.fragment_product_img);
            mProductDescriptionView = view.findViewById(R.id.fragment_product_text_description);
            this.nameView = view.findViewById(R.id.fragment_product_text_name);
            this.wholesale_priceView = view.findViewById(R.id.fragment_product_text_wholesale_price);
            this.retail_priceviewView = view.findViewById(R.id.fragment_product_text_retail_price);
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

                String charString = charSequence.toString().toLowerCase();

                if (charString.isEmpty()) {
                    mValuesFilter = mValues;
                } else {

                    ArrayList<Product> filteredList = new ArrayList<>();

                    for (Product product : mValues) {

                        if (product.getName() != null &&
                                (product.getName().toLowerCase().contains(charString) || product.getDescription().toLowerCase().contains(charString))) {
                            filteredList.add(product);


                        } else if (product.getTags().size() > 0) {
                            int cont = 0;
                            for (Tag tag : product.getTags()
                                    ) {
                                if (tag.getValue().toLowerCase().contains(charString)) {
                                    cont++;
                                }
                            }

                            if (cont > 0) {
                                filteredList.add(product);
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
