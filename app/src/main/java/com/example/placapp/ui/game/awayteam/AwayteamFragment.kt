package com.example.placapp.ui.game.awayteam


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.example.placapp.R
import com.example.placapp.ui.score.ScoreActivity
import kotlinx.android.synthetic.main.fragment_awayteam.*

class AwayteamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_awayteam, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btNext.setOnClickListener {
           /* activity?.startActivity(Intent(activity,ScoreActivity::class.java))
            activity?.finish()*/
            val intent = Intent("FILTER_AWAY_TEAM_NAME")
            intent.putExtra("away_team_name",id_edit_time_casa.text.toString())

            LocalBroadcastManager
                .getInstance(requireContext())
                .sendBroadcast(intent)
        }
    }


}
