package com.study.message;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.study.message.adapters.MessageAdapter;
import com.study.message.models.Message;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Message> states = new ArrayList<Message>();
    MessageAdapter stateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listMessage=findViewById(R.id.list_message);
        stateAdapter=new MessageAdapter(this,R.layout.custom_mesage, states);
        listMessage.setAdapter(stateAdapter);
    }

    public void editClick(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle(R.string.edit_title);

        EditText textView=findViewById(R.id.textView);
        Button okButton = dialog.findViewById(R.id.ok_button);
        final EditText editText = dialog.findViewById(R.id.edit_text);
        editText.setText(textView.getText());
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();
                dialog.dismiss();
                textView.setText(text);
            }
        });
        Button cancelButton = dialog.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void onSendMessage(View view) {
        EditText textView=findViewById(R.id.textView);
       states.add(new Message("From my App",textView.getText().toString()));
       stateAdapter.notifyDataSetChanged();
        textView.setText("");
    }
}