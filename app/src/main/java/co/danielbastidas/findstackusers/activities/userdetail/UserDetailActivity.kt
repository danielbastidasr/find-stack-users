package co.danielbastidas.findstackusers.activities.userdetail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import co.danielbastidas.findstackusers.activities.userdetail.mvp.UserDetailPresenter
import co.danielbastidas.findstackusers.activities.userdetail.mvp.UserDetailView
import co.danielbastidas.findstackusers.app.FindStackUsersApp
import co.danielbastidas.findstackusers.app.api.model.StackUser
import com.squareup.picasso.Picasso

import javax.inject.Inject

class UserDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var userDetailView: UserDetailView

    @Inject
    lateinit var userDetailPresenter: UserDetailPresenter


    companion object {

        private const val EXTRA_USER:String = "EXTRA_USER"

        fun start(context: Context, user:StackUser){
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(EXTRA_USER,user)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!intent.hasExtra(EXTRA_USER))
            throw IllegalArgumentException("Extra user is missing")

        val user = intent.getParcelableExtra<StackUser>(EXTRA_USER)

        FindStackUsersApp.createUserDetailComponent(this,user).inject(this)

        setContentView(userDetailView)

        userDetailPresenter.onCreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        userDetailPresenter.onDestroy()
    }

}
