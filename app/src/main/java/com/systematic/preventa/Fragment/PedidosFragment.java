package com.systematic.preventa.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class PedidosFragment extends Fragment {

    private List<Pedido> pedidos = new ArrayList<>();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_PEDIDOS = "pedidos";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecipeFragment.OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PedidosFragment() {
    }


    public static PedidosFragment newInstance(int columnCount, List<Pedido> pedidos) {
        PedidosFragment fragment = new PedidosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putSerializable(ARG_PEDIDOS, (Serializable) pedidos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            pedidos = (List<Pedido>) getArguments().getSerializable(ARG_PEDIDOS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carshop_list, container, false);
        setHasOptionsMenu(true);
        getActivity().closeOptionsMenu();
        try {

            // Set the adapter
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                if (mColumnCount <= 1) {

                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                } else {
                    recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                }
                recyclerView.setAdapter(new PedidosAdapter(pedidos, mListener));
            }
        } catch (NullPointerException e) {

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeFragment.OnListFragmentInteractionListener) {
            mListener = (RecipeFragment.OnListFragmentInteractionListener) context;
            System.out.println("attach listener");
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
