package co.danielbastidas.findstackusers.activities.search.mvp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.danielbastidas.findstackusers.R
import co.danielbastidas.findstackusers.app.api.model.StackUser
import io.reactivex.subjects.PublishSubject


class UserAdapter(private val observeClickDetailUser: PublishSubject<StackUser>): RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var users: ArrayList<StackUser> = ArrayList<StackUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserHolder(inflater.inflate(R.layout.user_view_holder, parent, false))
    }

    override fun getItemCount(): Int = users.size


    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.tvUserId.text = users[position].id
        holder.tvUserName.text = users[position].name
    }

    fun setUsers(listUser:List<StackUser>){
        users.clear()
        users.addAll(listUser)
        notifyDataSetChanged()
    }

    inner class UserHolder(val view:View):RecyclerView.ViewHolder(view) {

        val tvUserId:TextView = view.findViewById(R.id.card_user_name)
        val tvUserName:TextView = view.findViewById(R.id.card_user_id)

        init {
            view.setOnClickListener {
                observeClickDetailUser.onNext(users[adapterPosition])
            }
        }
    }

}


