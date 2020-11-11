package alireza.sn.witchoneislarger;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class GetNameDialog extends Dialog {
    private Button startBtn;

    private EditText nameEd;

    private OnSetNameListener listener;

    public GetNameDialog(Context context, OnSetNameListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        findViews();

        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onSetName(nameEd.getText().toString());
                dismiss();
            }
        });
    }

    private void findViews() {
        startBtn = findViewById(R.id.start_game);
        nameEd = findViewById(R.id.get_name_ed);
    }
}
