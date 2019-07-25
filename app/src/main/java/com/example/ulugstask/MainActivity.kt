package com.example.ulugstask

  import android.os.Bundle
  import android.support.v7.app.AppCompatActivity
  import android.support.v7.widget.LinearLayoutManager
  import com.squareup.picasso.Picasso
  import io.reactivex.android.schedulers.AndroidSchedulers
  import io.reactivex.disposables.CompositeDisposable
  import io.reactivex.schedulers.Schedulers
  import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     private lateinit var jsonApi: MyApi
      private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = RetrofitClient.instance
        jsonApi=retrofit.create(MyApi::class.java)

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        fetchData()
     }
    private fun fetchData(){
        val polzovatel=intent.getStringExtra("username")
        compositeDisposable.add(jsonApi.userinfo(polzovatel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{repos->displayData(repos)})
    }
    private fun displayData(repos: List<Repos>)
    {
        val avatarImage = intent.getStringExtra("avatar")
        val nameOfuser=intent.getStringExtra("name")
         tv_name.text=nameOfuser
         Picasso.get().load(avatarImage).resize(220,220).into(user_image)
         recycler_view.adapter= MainAdapter(repos)
    }
}
