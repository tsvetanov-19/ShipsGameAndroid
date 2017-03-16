package com.example.ttsvetanov.shipsgame.fragments;

import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.example.ttsvetanov.shipsgame.database.Game;
import com.example.ttsvetanov.shipsgame.game.CheckerBoard;
import com.example.ttsvetanov.shipsgame.database.DatabaseHelper;
import com.example.ttsvetanov.shipsgame.utils.ImageAdapter;
import com.example.ttsvetanov.shipsgame.R;
import com.example.ttsvetanov.shipsgame.utils.Utils;

import java.util.Date;


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
    private ImageAdapter mImageAdapter;
    private GridView mGridView;
    private int shots = 0;

    private DatabaseHelper dh = new DatabaseHelper(getContext());


    private int hits = 0;


    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int incrementHits() {
        int hits = getHits()+1;
        setHits(hits);
        return getHits();
    }
    public int getShipSquaresTotal() {
        return shipSquaresTotal;
    }

    public void setShipSquaresTotal(int shipSquaresTotal) {
        this.shipSquaresTotal = shipSquaresTotal;
    }

    private int shipSquaresTotal = 0;

    public int getMaxShots() {
        return maxShots;
    }

    public void setMaxShots(int maxShots) {
        this.maxShots = maxShots;
    }

    private int maxShots = 0;

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int incrementShots() {
        int shots = getShots()+1;
        setShots(shots);
        return getShots();
    }



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
        Cursor cursor = dh.getShipsData();
        int[] ships =  {5,5,5,5,5};
        int max = 0;
        boolean[] isVertical = {true,true,true, true,true};
//        if (cursor.moveToFirst()) {
//            max = cursor.getInt(cursor.getColumnIndex("max_shots"));
//            setMaxShots(max);
//            int[] ship = new int[5];
//            boolean[] vertical = new boolean[5];
//            ship[0] = cursor.getInt(cursor.getColumnIndex("ship1")) ;
//            ship[1] = cursor.getInt(cursor.getColumnIndex("ship2"));
//            ship[2] = cursor.getInt(cursor.getColumnIndex("ship3"));
//            ship[3]= cursor.getInt(cursor.getColumnIndex("ship4"));
//            ship[4]= cursor.getInt(cursor.getColumnIndex("ship5"));
//
//
//            vertical[0] = cursor.getInt(cursor.getColumnIndex("orientation_ship1")) > 0;
//            vertical[1] = cursor.getInt(cursor.getColumnIndex("orientation_ship2")) > 0;
//            vertical[2] = cursor.getInt(cursor.getColumnIndex("orientation_ship3")) > 0;
//            vertical[3] = cursor.getInt(cursor.getColumnIndex("orientation_ship4")) > 0;
//            vertical[4] = cursor.getInt(cursor.getColumnIndex("orientation_ship5")) > 0;
//            int totalShips = 0;
//            for(int i = 0 ; i<ship.length;i++) {
//                if(ship[i] > 0) {
//                    totalShips++;
//                    ships[totalShips] = ship[i];
//                    isVertical[totalShips] = vertical[i];
//                }
//            }
//        }
        cursor.close();
        for (int s: ships) {
            shipSquaresTotal += s;
        }

        if (ships.length > 0) {

            board.setShips(ships, isVertical);
        } else {
            Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
        }
        // Inflate the layout for this fragment
        mImageAdapter = new ImageAdapter(view.getContext());
        mGridView = (GridView) view.findViewById(R.id.gridview);
        mGridView.setAdapter(mImageAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if(mImageAdapter.getImageId(position) == ImageAdapter.getSpongebobJpg()) {
                    int p = uncover(position);
                    switch (p) {
                        case 1: incrementHits();  break;
                        case 2: incrementHits();  break;
                        case 3: incrementHits();  break;
                        case 4: incrementHits();  break;
                        case 5: incrementHits(); break;
                        default: break;
                    }
                    incrementShots();

                    //Player wins!
                    if(getHits() == getShipSquaresTotal()) {
                        finishGame("won",getShots());
                    }

//                    Toast.makeText(v.getContext(), "pos: " + board.getSquare(position) + " id: " + id,
//                            Toast.LENGTH_SHORT).show();
                    //Player loses!
                    if(getShots() == getMaxShots()) {
                        finishGame("lost", getShots());
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

    protected int uncover(int position) {
        int value = board.getSquare(position);
//        if(value)
        mImageAdapter.flipImage(position, value);
        mImageAdapter.notifyDataSetChanged();
        return value;

    }

    private void finishGame(String result, int shots) {
        Game g = new Game();
        g.setResult(result);
        g.setTurns(Integer.toString(shots));
        g.setDate("data data vajna data");
        dh.insertNewGame(g);
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Snackbar.make(getView(), "Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
