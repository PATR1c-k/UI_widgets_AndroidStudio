package com.example.pr_4_ui_widgets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.TextView;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    private EditText nameTextBox;
    private RadioGroup radioGroup1;
    private RadioButton radioButtonGender;
    private Button btnSubmit;
    private CheckBox checkboxEnglish,checkboxHindi,checkboxMarathi;
    private ToggleButton toggleButtonNeedHostel;
    private SeekBar AgeSeekBar;
    private RatingBar ratingbar;
    private ListView listView;
    String[] listItem;
    String listItemValue;
    Integer age;

    StringBuilder result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        nameTextBox = (EditText) findViewById(R.id.editTextName);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        checkboxEnglish = (CheckBox) findViewById(R.id.checkBoxEnglish);
        checkboxMarathi = (CheckBox) findViewById(R.id.checkBoxMarathi);
        checkboxHindi = (CheckBox) findViewById(R.id.checkBoxHindi);
        toggleButtonNeedHostel = (ToggleButton)findViewById(R.id.toggleButton);
        AgeSeekBar=(SeekBar)findViewById(R.id.seekBar);
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        listView=(ListView)findViewById(R.id.Citylist);
        result = new StringBuilder();

        //Add listener to radiobutton
        //ListView

        listItem = getResources().getStringArray(R.array.cities_array);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        //Listview listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                // TODO Auto-generated method stub
                listItemValue=adapter.getItem(position);
                Toast.makeText(getApplicationContext(),listItemValue,Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                result.delete(0,result.length()); //Clear buffer
                result.append("Entered Details:\nName: ");
                //Get text from Name textbox

                result.append(nameTextBox.getText()+"\n");

                //Get selected radio button text

                int selectedId = radioGroup1.getCheckedRadioButtonId();
                radioButtonGender = (RadioButton) findViewById(selectedId);
                result.append("Gender: " + radioButtonGender.getText()+"\n");

                //Get selected languages
                result.append("Can speak: " );
                if(checkboxEnglish.isChecked())
                {
                    result.append(checkboxEnglish.getText()+" ");
                }
                if(checkboxMarathi.isChecked())
                {
                    result.append(checkboxMarathi.getText()+" ");
                }
                if(checkboxHindi.isChecked())
                {
                    result.append(checkboxHindi.getText()+" ");
                }
                //Get Toggle Button text

                result.append("\nDo you Need Hostel: ").append(toggleButtonNeedHostel.getText());
                //Get age

                result.append("\nAge:" + String.valueOf(age));

                //Get rating value

                result.append("\nYour Rating:" + String.valueOf(ratingbar.getRating()));

                //Get city

                result.append("\nCity: " + listItemValue);

                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //Seekbar Listener
        AgeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();

                age = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getApplicationContext(),"seekbar touch stopped!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}