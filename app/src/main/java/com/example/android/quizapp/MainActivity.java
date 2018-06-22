package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class MainActivity extends AppCompatActivity {
	int score;
	int finalScore;
	boolean isQuestion1Correct;
	boolean isQuestion2Correct;
	boolean isQuestion3Correct;
	boolean isQuestion4Correct;
	boolean isQuestion5Correct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	/**
	 * This method is called when the submit responses button is clicked
	 */
	public void submitResponses(View view){
		evaluateQuestion1();
		evaluateQuestion2();
		evaluateQuestion3();
		evaluateQuestion4();
		evaluateQuestion5();
		Toast.makeText(this, "You scored "+ score + " points out of 5!" ,Toast.LENGTH_LONG).show();
		finalScore = score;
		score = 0;
		//make the view analysis button visible
		Button button = (Button) findViewById(R.id.analyse_results_button);
		button.setVisibility(View.VISIBLE);
		//make the play again button visible
		Button button1 = (Button) findViewById(R.id.play_again_button);
		button1.setVisibility(View.VISIBLE);
		//make the submit button invisible
		Button button2 = (Button) findViewById(R.id.submit_responses_button);
		button2.setVisibility(View.GONE);
	}

	/**
	 * This method evaluates the response  given in question 1
	 */
	public void evaluateQuestion1(){
		RadioButton radioButton = (RadioButton) findViewById(R.id.max_plank_radiobutton);
		if(radioButton.isChecked()){
			isQuestion1Correct = true;
			score = score + 1;
		}else{
			isQuestion1Correct = false;
		}
	}

	/**
	 * This method evaluates the responses given in question 2
	 */
	public void evaluateQuestion2(){
		CheckBox checkBoxCalculus = (CheckBox) findViewById(R.id.inventing_calculus_checkbox);
		CheckBox checkBoxGravity = (CheckBox) findViewById(R.id.discovering_gravity_checkbox);
		CheckBox checkBoxColorSpectrum = (CheckBox) findViewById(R.id.colour_spectrum_checkbox);
		CheckBox checkBoxAtomicStructure = (CheckBox) findViewById(R.id.atomic_structure_checkbox);
		if(checkBoxCalculus.isChecked() && checkBoxGravity.isChecked() && checkBoxColorSpectrum.isChecked() && !checkBoxAtomicStructure.isChecked()){
			isQuestion2Correct = true;
			score = score + 1;
		}else{
			isQuestion2Correct = false;
		}
	}

	/**
	 * This method evaluates the response given in question 3
	 */
	public void evaluateQuestion3(){
		RadioButton radioButton = (RadioButton) findViewById(R.id.bohr_radiobutton);
		if (radioButton.isChecked()){
			isQuestion3Correct = true;
			score = score +1;
		}else{
			isQuestion3Correct = false;
		}
	}

	/**
	 * This method evaluates the response given in question 4
	 */
	public void evaluateQuestion4(){
		RadioButton radioButton = (RadioButton) findViewById(R.id.marie_curie_radiobutton);
		if (radioButton.isChecked()){
			isQuestion4Correct = true;
			score = score + 1;
		}else{
			isQuestion4Correct = false;
		}
	}

	/**
	 * This method evaluates the response given in question 5
	 */
	public void evaluateQuestion5(){
		EditText editText = (EditText) findViewById(R.id.question5_edittext);
		String question5Response = editText.getText().toString();
		if (question5Response.equals("Galilei")){
			isQuestion5Correct = true;
			score = score + 1;
		}else{
			isQuestion5Correct = false;
		}
	}

	/**
	 * This method provides an analysis of the responses recorded
	 */
	public void analyseResults(View view){
		//make the view analysis button invisible
		Button button = (Button) findViewById(R.id.analyse_results_button);
		button.setVisibility(View.GONE);
		//make the results linear layout that was invisible during the quiz to be visible
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.results_linearlayout);
		linearLayout.setVisibility(View.VISIBLE);
		TextView textViewHeader = (TextView) findViewById(R.id.results_header_textview);
		TextView textViewPercentage = (TextView) findViewById(R.id.results_percentage_textview);
		TextView textViewBody = (TextView) findViewById(R.id.results_body_textview);
		//display a comment on the result sheet
		if (finalScore == 5){
			textViewHeader.setText("Excellent!");
		}else if(finalScore == 4){
			textViewHeader.setText("Impressive!");
		}else if(finalScore == 3){
			textViewHeader.setText("Well done!");
		}else if(finalScore == 2){
			textViewHeader.setText("Not bad!");
		}else if(finalScore == 1){
			textViewHeader.setText("At least you got something.");
		}else {
			textViewHeader.setText("That's got to be rough");
		}
		//display the user's score in percentage form
		String percentage = calculatePercentage(finalScore) +"%";
		textViewPercentage.setText(percentage);
		//show which questions were answered right and which ones were not
		String body;
		if(isQuestion1Correct){
			body = "You got question 1 right!";
		}else{
			body = "You got question 1 wrong.";
		}
		if(isQuestion2Correct){
			body = body + "\nYou got question 2 right!";
		}else{
			body = body + "\nYou got question 2 wrong.";
		}
		if(isQuestion3Correct){
			body = body + "\nYou got question 3 right!";
		}else{
			body = body + "\nYou got question 3 wrong.";
		}
		if(isQuestion4Correct){
			body = body + "\nYou got question 4 right!";
		}else{
			body = body + "\nYou got question 4 wrong.";
		}
		if(isQuestion5Correct){
			body = body + "\nYou got question 5 right!";
		}else{
			body = body + "\nYou got question 5 wrong.";
		}
		textViewBody.setText(body);

		//this code scrolls the user to the bottom of the scrollview automatically
		final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollView));
		scrollview.post(new Runnable() {
			@Override
			public void run() {
				scrollview.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});

	}

	/**
	 * This method calculates the percenatage score of the user
	 * @param number is the user's actual score out of 5
	 * @return gives the user's score in percentage form
	 */
	public int calculatePercentage(int number){
		return  ((number * 100) / 5);
	}

	/**
	 * This method sets up the app for a new quiz
	 */
	public void setUpNewQuiz(View view){
		//make the results linearlayout invisible
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.results_linearlayout);
		linearLayout.setVisibility(View.GONE);
		//make the view analysis button invisible
		Button button = (Button) findViewById(R.id.analyse_results_button);
		button.setVisibility(View.GONE);
		//make the play again button invisible
		Button button1 = (Button) findViewById(R.id.play_again_button);
		button1.setVisibility(View.GONE);
		//make the submit button visible
		Button button2 = (Button) findViewById(R.id.submit_responses_button);
		button2.setVisibility(View.VISIBLE);

		//make sure all the checkboxes and radiobuttons are unchecked
		RadioButton radioButton = (RadioButton) findViewById(R.id.niels_bohr_radiobutton);
		radioButton.setChecked(false);
		RadioButton radioButton2 = (RadioButton) findViewById(R.id.albert_einstein_radiobutton);
		radioButton2.setChecked(false);
		RadioButton radioButton3 = (RadioButton) findViewById(R.id.max_plank_radiobutton);
		radioButton3.setChecked(false);
		RadioButton radioButton4 = (RadioButton) findViewById(R.id.charles_darwin_radiobutton);
		radioButton4.setChecked(false);

		CheckBox checkBox = (CheckBox) findViewById(R.id.inventing_calculus_checkbox);
		checkBox.setChecked(false);
		CheckBox checkBox2 = (CheckBox) findViewById(R.id.discovering_gravity_checkbox);
		checkBox2.setChecked(false);
		CheckBox checkBox3 = (CheckBox) findViewById(R.id.atomic_structure_checkbox);
		checkBox3.setChecked(false);
		CheckBox checkBox4 = (CheckBox) findViewById(R.id.colour_spectrum_checkbox);
		checkBox4.setChecked(false);

		RadioButton radioButton5 = (RadioButton) findViewById(R.id.rutherford_radiobutton);
		radioButton5.setChecked(false);
		RadioButton radioButton6 = (RadioButton) findViewById(R.id.maxwell_radiobutton);
		radioButton6.setChecked(false);
		RadioButton radioButton7 = (RadioButton) findViewById(R.id.bohr_radiobutton);
		radioButton7.setChecked(false);

		RadioButton radioButton8 = (RadioButton) findViewById(R.id.einstein_radiobutton);
		radioButton8.setChecked(false);
		RadioButton radioButton9 = (RadioButton) findViewById(R.id.marie_curie_radiobutton);
		radioButton9.setChecked(false);
		RadioButton radioButton10 = (RadioButton) findViewById(R.id.thomas_edison_radiobutton);
		radioButton10.setChecked(false);
		RadioButton radioButton11 = (RadioButton) findViewById(R.id.nikola_testla_radiobutton);
		radioButton11.setChecked(false);

		EditText editText = (EditText) findViewById(R.id.question5_edittext);
		editText.setText("");

		final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollView));
		scrollview.post(new Runnable() {
			@Override
			public void run() {
				scrollview.fullScroll(ScrollView.FOCUS_UP);
			}
		});
	}

}