package de.tenect.tum.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class SocialChooserFragment extends DialogFragment {


    /* The activity that creates an instance of this dialog fragment must
 * implement this interface in order to receive event callbacks.
 * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogClick(int x);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select social profile")
                .setItems(R.array.social_profiles, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //set Image Resource of circle wrapper
                        //Toast.makeText(getActivity().getApplicationContext(), "position "+ which + " clicked", Toast.LENGTH_SHORT).show();
                        mListener.onDialogClick(which);
                    }

                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
