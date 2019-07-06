package com.example.placapp.ui.game

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.placapp.R
import com.example.placapp.ui.game.awayteam.AwayteamFragment
import com.example.placapp.ui.game.event.EventFragment
import com.example.placapp.ui.game.hometeam.HomeTeamFragment
import com.example.placapp.ui.score.ScoreActivity
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity() {

    lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        ivBack.setOnClickListener {
            //botao voltar
            onBackPressed()
        }
        if (savedInstanceState == null)
            showEventFragment()
        registerBroadcastReceiver()
    }

    private fun showEventFragment() {

        val ft = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag("EventFragment") == null) {
            ft.add(R.id.containerGame, EventFragment())
            ft.commit()
        }
    }

    private fun registerBroadcastReceiver() {
        val intentFilter = IntentFilter("FILTER_EVENT_NAME")
        intentFilter.addAction("FILTER_HOME_TEAM_NAME")
        intentFilter.addAction("FILTER_AWAY_TEAM_NAME")

        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter)

    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)

    }

    private val myReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

            if (intent.hasExtra("event_name")) {
                gameViewModel.eventName = intent.getStringExtra("event_name")
                next(HomeTeamFragment())
            }
            if (intent.hasExtra("home_team_name")) {
                gameViewModel.homeTeamName = intent.getStringExtra("home_team_name")
                next(AwayteamFragment())
            }
            if (intent.hasExtra("away_team_name")) {
                gameViewModel.awayTeamName = intent.getStringExtra("away_team_name")
                showScore()

            }
        }
    }

    private fun showScore() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("event_name", gameViewModel.eventName)
        intent.putExtra("home_team_name", gameViewModel.homeTeamName)
        intent.putExtra("away_team_name", gameViewModel.awayTeamName)
        startActivity(intent)
    }

    private fun next(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.containerGame, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }
}

