package com.example.placapp.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.placapp.R
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreViewModel = ViewModelProviders
            .of(this).get(ScoreViewModel::class.java)

        tvEventName.text = intent.getStringExtra("event_name")
        tvHome.text = intent.getStringExtra("home_team_name")
        tvGoalHome.text = intent.getStringExtra("away_team_name")

        btGoadHome.setOnClickListener {
            scoreViewModel.markGoalHome()
        }
        btGoalAway.setOnClickListener {
            scoreViewModel.marlGoalAway()
        }

        btRestart.setOnClickListener {
            scoreViewModel.restart()
        }
        scoreViewModel.goalHome.observe(this, Observer {
            tvGoalHome.text = it.toString()
        })
        scoreViewModel.goalAway.observe(this, Observer {
            tvHome.text = it.toString()
        })
    }
}
