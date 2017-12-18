package com.systematic.preventa.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.systematic.preventa.DataBase.ProductDB;
import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.Entity.Product;
import com.systematic.preventa.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ProductFragment extends Fragment {


    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();



    // TODO: Customize parameters
    private int mColumnCount = 1;
    private static final String  id_user = "id_user";
    private static final String  is_solicitud_global = "is_solicitud_global";
    private static final String  ARG_PRODUCTOS = "ARG_PRODUCTOS";
    private static final String  ARG_PEDIDOS = "ARG_PEDIDOS";

    private String mParam1;
    private Boolean mParam2;

    private ProductAdapter mAdapter;
    private RecyclerView recycleview_aux;
    private SearchView searchView;



    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductFragment() {

    }

    public static ProductFragment newProductionLine(String id, Boolean is_solicitud, List<Pedido> pedidos,List<Product> productos) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(id_user, id);
        args.putBoolean(is_solicitud_global,is_solicitud);
        args.putSerializable(ARG_PEDIDOS, (Serializable) pedidos);
        args.putSerializable(ARG_PRODUCTOS, (Serializable) productos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(id_user);
            mParam2 = getArguments().getBoolean(is_solicitud_global);
            products = (ArrayList<Product>) getArguments().getSerializable(ARG_PRODUCTOS);
            pedidos = (ArrayList<Pedido>) getArguments().getSerializable(ARG_PEDIDOS);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        setHasOptionsMenu(true);

        getActivity().closeOptionsMenu();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            recycleview_aux = recyclerView;
            if(products.size() > 0 ){
                recyclerView.setAdapter(new ProductAdapter(products,mListener,mParam2,pedidos));
                recycleview_aux = recyclerView;

            }else{
                Toast.makeText(view.getContext(), "No hay Productos Syncronizados", Toast.LENGTH_SHORT).show();
            }


        }


//        container.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

    @Override
    public void onPause() {  //not sure if you should use onDestroyView() instead
        super.onPause();
        try {
            if (!searchView.isIconified()) {  //true == searchView closed
                searchView.setIconified(true);
            }

        }
        catch (NullPointerException e){

        }


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Product item);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       // menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu,inflater);
        MenuItem search = menu.findItem(R.id.action_search);

        //MenuItemCompat.collapseActionView(search);
        searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setQueryHint("Buscar Productos");
        search(searchView);
    }

    private void search(final SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter = (ProductAdapter) recycleview_aux.getAdapter();
                if (mAdapter != null)
                    mAdapter.getFilter().filter(newText);
                return true;
            }
        });


    }

    @Override
    public void onResume() {
        getActivity().invalidateOptionsMenu();
        super.onResume();
    }



}
