package com.brasilnewstart.launcher.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.brasilnewstart.launcher.LauncherApplication
import com.brasilnewstart.launcher.R
import com.brasilnewstart.launcher.UserConfig
import com.brasilnewstart.launcher.core.ServerConfig
import com.brasilnewstart.launcher.core.ServerResolveCallback
import com.brasilnewstart.launcher.core.ServerView
import com.brasilnewstart.launcher.ui.widgets.playbutton.PlayButton

class HomeFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceStatus: Bundle?
    ): View {
        // Get shared preferences
        //val sharedPreferences: SharedPreferences? = this.context?.getSharedPreferences("HomeFragment", Context.MODE_PRIVATE);
        //val preferencesEditor: SharedPreferences.Editor? = sharedPreferences?.edit();

        this.rootView = inflater.inflate(R.layout.activity_home, container, false);

        // Set nickname text
        val nicknameText: TextView = this.rootView.findViewById(R.id.nickname)

        val launcherApplication: LauncherApplication = activity?.application as LauncherApplication;
        nicknameText.text = launcherApplication.userConfig.Nickname;

        // Ao selecionar uma imagem na atividade (redireciona á um link do navegador):
        val imageSelectionFacebook = rootView.findViewById(R.id.linkFacebook) as ImageView
        val imageSelectionYoutube = rootView.findViewById(R.id.linkYoutube) as ImageView
        val imageSelectionDiscord = rootView.findViewById(R.id.linkDiscord) as ImageView
        val imageSelectionSite = rootView.findViewById(R.id.linkSite) as ImageView

        imageSelectionFacebook.setOnClickListener {
            val url = "https://facebook.com/brasilnewstart"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        imageSelectionYoutube.setOnClickListener {
            val url = "https://youtube.com/brasilnewstart"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        imageSelectionDiscord.setOnClickListener {
            val url = "https://discord.gg/ZsNSuPJ8Ex"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        imageSelectionSite.setOnClickListener {
            val url = "http://www.brasilnewstart.com.br"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        // Set port filter
        /*val portEditText: EditText = this.rootView.findViewById(R.id.port);
        portEditText.filters = Array(1) { PortFilter() };

        // Bind ip and port text edit fields
        val ipEditText: EditText = this.rootView.findViewById(R.id.ip);
        val passwordEditText:EditText = this.rootView.findViewById(R.id.password);

        // Restore from preferences
        if (sharedPreferences != null){
            ipEditText.setText(sharedPreferences.getString(R.id.ip.toString(), ""));
            portEditText.setText(sharedPreferences.getString(R.id.port.toString(), ""));
            passwordEditText.setText(sharedPreferences.getString(R.id.password.toString(), ""));
        }

        ipEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                preferencesEditor?.putString(R.id.ip.toString(), s.toString());
                preferencesEditor?.apply();
            }

            override fun afterTextChanged(s: Editable?) {
                updateServerConfig();
            }
        });
        portEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                preferencesEditor?.putString(R.id.port.toString(), s.toString());
                preferencesEditor?.apply();
            }

            override fun afterTextChanged(s: Editable?) {
                updateServerConfig();
            }
        });
        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                preferencesEditor?.putString(R.id.password.toString(), s.toString());
                preferencesEditor?.apply();
            }

            override fun afterTextChanged(s: Editable?) {
                updateServerConfig();
            }
        });*/

        // Setup play button
        val playButton: PlayButton = this.rootView.findViewById(R.id.play_btn) as PlayButton;
        playButton.SetOnSAMPLaunchCallback {
            println("Start SAMP");
        }

        this.updateServerConfig();
        return this.rootView;
    }

    /*private fun defineLinkFromImage(nameValdefLink: val, linkUri: Uri) {
        // Ao selecionar uma imagem na atividade (redireciona á um link do navegador):
        val imageSelectionFacebook = rootView.findViewById(R.id.linkFacebook) as ImageView

        defineLinkFromImage("https://facebook.com/brasilnewstart")

        imageSelectionFacebook.setOnClickListener {
            val url = "https://facebook.com/brasilnewstart"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }*/

    private fun updateServerConfig(){
        /*val ipEdit:EditText = this.rootView.findViewById(R.id.ip);
        val portEdit:EditText = this.rootView.findViewById(R.id.port);*/
        val userConfig:UserConfig = (activity?.application as LauncherApplication).userConfig;

        /*val IP:String = ipEdit.text.toString();
        val port:Int;

        if (portEdit.text.isNotEmpty()){
            port = portEdit.text.toString().toInt();
        }else{
            port = 0;
        }*/

        // Resolve server
        ServerConfig.Resolve("198.100.156.80", 7777, userConfig.PingTimeout, this.context, object : ServerResolveCallback {
            override fun OnFinish(OutConfig: ServerConfig?)  {
                // Update ServerView
                val serverView: ServerView = rootView.findViewById(R.id.server_view);
                serverView.SetServer(OutConfig);

                val playButton: PlayButton = rootView.findViewById(R.id.play_btn) as PlayButton;
                playButton.SetServerConfig(OutConfig);
            }

            override fun OnPingFinish(OutConfig: ServerConfig?) {
                // Update ServerView (again)
                val serverView: ServerView = rootView.findViewById(R.id.server_view);
                serverView.SetServer(OutConfig);
            }
        });
    }
}