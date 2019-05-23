package com.example.labarys2019;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragCita.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragCita#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragCita extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String CITA_KEY = "cita";
    public static final String AUTOR_KEY = "autor";
    public static final String DB_CLOUD = "cloudCitas";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //adaptador
    ListView refListCitas;

    public FragCita() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragCita.
     */
    // TODO: Rename and change types and number of parameters
    public static FragCita newInstance(String param1, String param2) {
        FragCita fragment = new FragCita();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View viewMain = inflater.inflate(R.layout.fragment_frag_cita, container, false);
        refListCitas = viewMain.findViewById(R.id.lvCitas);   // Instancia del ListView.

        // produccion
//        adapterDataListCustom(viewMain);
//        listenListCitas();

        showCitasDbCloud();
        listenListCitas();


//        return inflater.inflate(R.layout.fragment_frag_cita, container, false);
        return viewMain;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // #################################################################################
    // #################################################################################

    private void showCitasDbCloud() {
        CollectionReference dbFirebase = FirebaseFirestore.getInstance().collection(DB_CLOUD);

        dbFirebase.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String msgInfo = "";
                List<InfoCita> citasDb = new ArrayList<>();
                if (task.isSuccessful()) {
                    InfoCita cita;
                    ObjectMapper mapper = new ObjectMapper();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        msgInfo += " " + document.getId() + " => " + document.getData() + "\n";
                        cita = document.toObject(InfoCita.class);
                        citasDb.add(cita);
                    }
                    // acomodamos los datos para mostrarlos en la lista
                    adapterDataListCitas(citasDb);
//                    AdapterCita adapterCustom  = new AdapterCita(getActivity(), data);
//                    refListCitas.setAdapter(adapterCustom);       //Relacionando la lista con el adaptador
                } else {
                    msgInfo += "Error getting documents: " + task.getException();
                }
//                Toast.makeText(getContext(), "Recuperando datos: " + msgInfo, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), "Recuperando datos objects: " + citasDb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void adapterDataListCustom(View viewMain){
//        refListCitas = viewMain.findViewById(R.id.lvCitas);   // Instancia del ListView.
//        AdapterCita adapterCustom  = new AdapterCita(getActivity(), LeadsRepository.getInstance().getLeads());
//        refListCitas.setAdapter(adapterCustom);       //Relacionando la lista con el adaptador
//
//    }

    private void adapterDataListCitas(List<InfoCita> data){
        AdapterCita adapterCustom  = new AdapterCita(getActivity(), data);
        refListCitas.setAdapter(adapterCustom);       //Relacionando la lista con el adaptador
    }

//    public void getAllCitas(){
//        CollectionReference dbFirebase = FirebaseFirestore.getInstance().collection(DB_CLOUD);
//
//        dbFirebase.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                String msgInfo = "";
//                Collection<InfoCita> citasDb = new ArrayList<>();
//                if (task.isSuccessful()) {
//                    InfoCita cita;
//                    ObjectMapper mapper = new ObjectMapper();
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        msgInfo += " " + document.getId() + " => " + document.getData() + "\n";
//                        cita = document.toObject(InfoCita.class);
//                        citasDb.add(cita);
//                    }
//                } else {
//                    msgInfo += "Error getting documents: " + task.getException();
//                }
//                Toast.makeText(getContext(), "Recuperando datos: " + msgInfo, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), "Recuperando datos objects: " + citasDb.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    // ponemos a escuchar todos los elementos de la lista
//    private void listenListCitas() {
//        refListCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Lead itemSelected = (Lead)refListCitas.getItemAtPosition(position);
//                passToPuntuacion(itemSelected);
//            }
//        });
//    }

    private void listenListCitas() {
        refListCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoCita itemSelected = (InfoCita) refListCitas.getItemAtPosition(position);
                passToPuntuacion(itemSelected);
            }
        });
    }

//    private void passToPuntuacion(Lead object){
    private void passToPuntuacion(InfoCita dataCita){
        Intent toPuntuacion = new Intent(getActivity(), PuntuacionCitaActivity.class);
//        toPuntuacion.putExtra("CITA_SELECTED",object);
        toPuntuacion.putExtra("CITA_SELECTED",dataCita);
        startActivity(toPuntuacion);
    }
}
