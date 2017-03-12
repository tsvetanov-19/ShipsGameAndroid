package com.example.ttsvetanov.shipsgame;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CheckerBoard board;
    private DatabaseHelper dh;
    private ImageAdapter mImageAdapter;
    private GridView mGridView;

    private OnFragmentInteractionListener mListener;

    public GameFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
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
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        board = new CheckerBoard();


        dh = new DatabaseHelper(this.getContext());
        Cursor cursor = dh.getGameData();
        int[] ships =  {5,5,5,5,5};
        int shots = 55;
        boolean[] isVertical = {true,false,true,true, false};
        if (cursor.moveToFirst()) {
            shots = cursor.getInt(cursor.getColumnIndex("max_shots"));
//
//            ships[0] = cursor.getInt(cursor.getColumnIndex("ship1"));
//            ships[1] = cursor.getInt(cursor.getColumnIndex("ship2"));
//            ships[2] = cursor.getInt(cursor.getColumnIndex("ship3"));
//            ships[3] = cursor.getInt(cursor.getColumnIndex("ship4"));
//            ships[4] = cursor.getInt(cursor.getColumnIndex("ship5"));
//
//
//            isVertical[0] = cursor.getInt(cursor.getColumnIndex("orientation_ship1")) > 0;
//            isVertical[1] = cursor.getInt(cursor.getColumnIndex("orientation_ship2")) > 0;
//            isVertical[2] = cursor.getInt(cursor.getColumnIndex("orientation_ship3")) > 0;
//            isVertical[3] = cursor.getInt(cursor.getColumnIndex("orientation_ship4")) > 0;
//            isVertical[4] = cursor.getInt(cursor.getColumnIndex("orientation_ship5")) > 0;
//            shots = cursor.getInt(cursor.getColumnIndex("max_shots"));
//
        }
        cursor.close();
//        ships = [2,3,4,5,5];
//        isVertical = {false,false,true, true,false};
        if (ships != null && ships.length > 0) {

            board.setShips(ships, isVertical);
        } else {
            Toast.makeText(getView().getContext(), "Error!", Toast.LENGTH_SHORT).show();
        }
        // Inflate the layout for this fragment
        mImageAdapter = new ImageAdapter(view.getContext());
        mGridView = (GridView) view.findViewById(R.id.gridview);
        mGridView.setAdapter(mImageAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                int hits = 0;
                if(mImageAdapter.getImageId(position) == ImageAdapter.getSpongebobJpg()) {
                    uncover(position);
                    hits++;

//                    Toast.makeText(v.getContext(), "pos: " + board.getSquare(position) + " id: " + id,
//                            Toast.LENGTH_SHORT).show();
                    if(hits == 55) {
//                        finishGame();
                        Toast.makeText(v.getContext(), "END" + hits,
                                Toast.LENGTH_SHORT).show();
                    }
                }

//                view.setImageResource(im.mThumbIds[position]);

            }
        });
        return view;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    protected void uncover(int position) {
        int value = board.getSquare(position);
//        if(value)
        mImageAdapter.flipImage(position, value);
        mImageAdapter.notifyDataSetChanged();

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
