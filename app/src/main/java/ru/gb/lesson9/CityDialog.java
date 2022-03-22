package ru.gb.lesson9;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;

public class CityDialog extends DialogFragment {

    public static final String CITY = "CITY";
    public static final String POSITION = "POSITION";

    public interface CityDialogController {
        void update(City city, int position);
        void create(String name, int population);
    }

    private City city;
    private int position;

    public static CityDialog getInstance(City city, int position)
    {
        CityDialog dialog = new CityDialog();
        Bundle args = new Bundle();
        args.putSerializable(CITY, city);
        args.putInt(POSITION, position);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        city = (City) args.getSerializable(CITY);
        position = args.getInt(POSITION, -1);

        String name = "";
        int population = 0;
        if(city != null)
        {
            name = city.getName();
            population = city.getPopulation();
        }

        View dialog = LayoutInflater.from(requireContext()).inflate(R.layout.city_dialog, null);
        TextInputLayout dialogName = dialog.findViewById(R.id.dialog_name);
        TextInputLayout dialogPopulation = dialog.findViewById(R.id.dialog_population);

        dialogName.getEditText().setText(name);
        dialogPopulation.getEditText().setText("" + population);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        // TODO с помощью builder проинициализировать Dialog
        String buttonText = "";
        if(city == null)
        {
            buttonText = "Create";
            builder.setTitle("Create a city");
        }
        else
        {
            buttonText = "Modify";
            builder.setTitle("Modify a city");
        }
        builder
                .setView(dialog)
                .setCancelable(true)
        ;

        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());

        builder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // TODO
                String newName = dialogName.getEditText().getText().toString();
                int newPopulation = Integer.parseInt(
                        dialogPopulation.getEditText().getText().toString()
                );
                if(city == null)
                {
                    ((CityDialogController) requireContext()).create(newName, newPopulation);
                }
                else {
                    city.setName(newName);
                    city.setPopulation(newPopulation);
                    ((CityDialogController) requireContext()).update(city, position);
                }


                dialogInterface.dismiss();
            }
        });

        return builder.create();

    }
}
