package com.example.ttsvetanov.shipsgame.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ttsvetanov.shipsgame.database.DatabaseHelper;
import com.example.ttsvetanov.shipsgame.R;
import com.example.ttsvetanov.shipsgame.database.Ships;

import static com.example.ttsvetanov.shipsgame.R.styleable.CompoundButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatabaseHelper dh;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int stuff;

    private Ships ships;

    public int s1;
    public int s2;
    public int s3;
    public int s4;
    public int s5;

    public Ships getShips() {
        return ships;
    }

    public void setShips(Ships ships) {
        this.ships = ships;
    }

    public int getS1() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }

    public int getS2() {
        return s2;
    }

    public void setS2(int s2) {
        this.s2 = s2;
    }

    public int getS3() {
        return s3;
    }

    public void setS3(int s3) {
        this.s3 = s3;
    }

    public int getS4() {
        return s4;
    }

    public void setS4(int s4) {
        this.s4 = s4;
    }

    public int getS5() {
        return s5;
    }

    public void setS5(int s5) {
        this.s5 = s5;
    }


//    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        dh = new DatabaseHelper(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        SeekBar[] seekBars = new SeekBar[5];
        seekBars[0] = (SeekBar) view.findViewById(R.id.seekBar1);
        seekBars[1] = (SeekBar) view.findViewById(R.id.seekBar2);
        seekBars[2] = (SeekBar) view.findViewById(R.id.seekBar3);
        seekBars[3] = (SeekBar) view.findViewById(R.id.seekBar4);
        seekBars[4] = (SeekBar) view.findViewById(R.id.seekBar5);

        Switch[] switches = {
                (Switch) view.findViewById(R.id.switch1), (Switch) view.findViewById(R.id.switch2),
                (Switch) view.findViewById(R.id.switch3), (Switch) view.findViewById(R.id.switch4),
                (Switch) view.findViewById(R.id.switch5)
        };

        for(Switch s : switches) {
            if (s != null) {

//                s.setOnCheckedChangeListener(
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    Toast.makeText(this, "The Switch is " + (isChecked ? "on" : "off"),
//                            Toast.LENGTH_SHORT).show();
//                    if(isChecked) {
//                        //do stuff when Switch is ON
//                    } else {
//                        //do stuff when Switch if OFF
//                    }
//                });
            }

        }



        for (int bar = 0; bar < seekBars.length; bar++) {
            seekBars[bar].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // TODO Auto-generated method stub
                    Log.v("", "" + seekBar);

                    switch (seekBar.getId()) {
                        case R.id.seekBar1:
                            setS1((progress > 0 ) ? progress : 0 );
                            break;
                        case R.id.seekBar2:
                            setS2((progress > 0 ) ? progress : 0 );
                            break;
                        case R.id.seekBar3:
                            setS3((progress > 0 ) ? progress : 0 );
                            break;
                        case R.id.seekBar4:
                            setS4((progress > 0 ) ? progress : 0 );
                            break;
                        case R.id.seekBar5:
                            setS5((progress > 0 ) ? progress : 0 );
                            break;
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }
            });

            final Button saveButton = (Button) view.findViewById(R.id.save_button);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.equals(saveButton)) {
                        Ships shipConfiguration = new Ships(getS1(),getS2(),getS3(),getS4(),getS5(),0,0,0,0,0);
                        if(saveShipSettings(shipConfiguration)) {
                        Toast.makeText(getView().getContext(),"saved", Toast.LENGTH_LONG).show();
                            Cursor cursor = dh.getShipsData();
                            cursor.moveToFirst();
                            cursor.getCount();
                            cursor.getInt(cursor.getColumnIndex("ship1"));
                        }

//                      setShips(shipConfiguration);
//                        Bundle args = new Bundle();
//                        args.putSerializable(ships);
//                        Fragment toFragment = new GameFragment();
//                        toFragment.setArguments(args);
//                        getFragmentManager().beginTransaction().rep
//                        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("FRAG2");
//                        getActivity().getSupportFragmentManager().replace(R.id.yourFrameLayout,,"FRAG2");
                    } else {
                        // do something else
                    }
                }
            };
            saveButton.setOnClickListener(listener);
        }


        return view;
    }

    private boolean saveShipSettings(Ships s) {
        String result = dh.insertShipsData(s);
        if(result.equals("saved")) {
            return true;
        }
        return false;
    }



}
