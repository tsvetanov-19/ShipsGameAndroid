package com.example.ttsvetanov.shipsgame;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class HistoryFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String[] fromGame = new String[]{
            DatabaseHelper.COLUMN_GAME_DATE,
            DatabaseHelper.COLUMN_GAME_RESULT,
            DatabaseHelper.COLUMN_GAME_TURNS
    };

    int[] toGame = new int[]{
            R.id.history_date,
            R.id.history_result,
            R.id.history_turns
    };
    public DatabaseHelper dh;

    private OnFragmentInteractionListener mListener;
    SimpleCursorAdapter simpleCursorAdapter;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dh = new DatabaseHelper(this.getContext());
        Cursor cursor = dh.getGameData();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String result = cursor.getString(cursor.getColumnIndex("result"));
                String turns = cursor.getString(cursor.getColumnIndex("turns"));
                // do what ever you want here
                cursor.moveToNext();
            }
        }
        cursor.close();

        simpleCursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.fragment_history, cursor, fromGame, toGame
                , CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
}
