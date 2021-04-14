package com.example.celebrityquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter {
    private List<Quiz> quizList;
    private Context context;

    // Constructor to initialize all arrayList
    FavoriteAdapter(List<Quiz> quizList, Context context) {
        this.quizList = quizList;
        this.context = context;
    }

    @NonNull
    @Override
    // Build view layout and call ViewHolder, QuizHolder class
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layoutInflater = LayoutInflater.from(context).
                inflate(R.layout.favorite, viewGroup, false);
        return new RecyclerView.ViewHolder(layoutInflater) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        // Define recycler views
        TextView viewQuestion = viewHolder.itemView.findViewById(R.id.celebrityQuestion);
        TextView userAnswer = viewHolder.itemView.findViewById(R.id.userAnswer);
        TextView correctAnswer = viewHolder.itemView.findViewById(R.id.correctAnswer);
        ImageView imageView = viewHolder.itemView.findViewById(R.id.celebrityImage);
        RadioGroup radioGroup = viewHolder.itemView.findViewById(R.id.celebrityOption);
        RadioButton radioButtonOne = viewHolder.itemView.findViewById(R.id.radioButtonOne);
        RadioButton radioButtonTwo = viewHolder.itemView.findViewById(R.id.radioButtonTwo);
        RadioButton radioButtonThree = viewHolder.itemView.findViewById(R.id.radioButtonThree);
        RadioButton radioButtonFour = viewHolder.itemView.findViewById(R.id.radioButtonFour);
        viewHolder.itemView.findViewById(R.id.horizontalDivider);

        SharedPreferences sf = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        String mode = sf.getString("mode", "객관식");
        if(mode.equals("객관식")) {
            correctAnswer.setVisibility(View.GONE);

            if(!quizList.isEmpty()) {
                Quiz quiz = quizList.get(position);

                viewQuestion.setText(String.format("%s. %s", position + 1, quiz.question));
                Glide.with(imageView.getContext()).load(quiz.imageUrl).into(imageView);
                radioButtonOne.setText(quiz.one);
                radioButtonTwo.setText(quiz.two);
                radioButtonThree.setText(quiz.three);
                radioButtonFour.setText(quiz.four);

                // This is crucial for Marking system
                /* First, determine if userAnswer is the same as correctAnswer, IF YES, mark it
                 * green and set it checked. ELSE, if user didn't select anything clearCheck() else if
                 * userAnswer is wrong, mark userAnswer red, locate
                 * correctAnswer and mark it green.
                 */

                if (1 == quiz.correctAnswer) {
                    radioButtonOne.setChecked(true);
                    radioButtonOne.setTextColor(Color.parseColor("#FF0BA512"));
                } else if (2 == quiz.correctAnswer) {
                    radioButtonTwo.setChecked(true);
                    radioButtonTwo.setTextColor(Color.parseColor("#FF0BA512"));
                } else if (3 == quiz.correctAnswer) {
                    radioButtonThree.setChecked(true);
                    radioButtonThree.setTextColor(Color.parseColor("#FF0BA512"));
                } else if (4 == quiz.correctAnswer) {
                    radioButtonFour.setChecked(true);
                    radioButtonFour.setTextColor(Color.parseColor("#FF0BA512"));
                }
                if (0 == quiz.userAnswer) radioGroup.clearCheck();

                // Disable all radioButton to avoid answer misinterpretations
                radioButtonOne.setEnabled(false);
                radioButtonTwo.setEnabled(false);
                radioButtonThree.setEnabled(false);
                radioButtonFour.setEnabled(false);
            }
        }
        else if(mode.equals("주관식")) {
            radioGroup.setVisibility(View.GONE);

            if(!quizList.isEmpty()) {
                Quiz quiz = quizList.get(position);

                viewQuestion.setText(String.format("%s. %s", position + 1, quiz.question));
                Glide.with(imageView.getContext()).load(quiz.imageUrl).into(imageView);

                String answerString = "";
                switch (quiz.correctAnswer) {
                    case 1:
                        answerString = quiz.one;
                        break;
                    case 2:
                        answerString = quiz.two;
                        break;
                    case 3:
                        answerString = quiz.three;
                        break;
                    case 4:
                        answerString = quiz.four;
                        break;
                }
                correctAnswer.setText(answerString);
                if(quiz.userTextAnswer != null)
                    if(quiz.userTextAnswer.equals(answerString)) userAnswer.setTextColor(Color.parseColor("#FF0BA512"));
            }
        }
    }

    // Default ViewHolder methods
    @Override
    public int getItemCount() {
        if (quizList == null) return 0;
        return quizList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
