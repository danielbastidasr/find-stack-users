package co.danielbastidas.findstackusers.activities.userdetail

import dagger.Subcomponent


@UserDetailScope
@Subcomponent(modules = [(UserDetailModule::class)])
interface UserDetailComponent {

    fun inject(searchActivity: UserDetailActivity)

}