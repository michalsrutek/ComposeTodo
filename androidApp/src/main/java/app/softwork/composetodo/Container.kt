package app.softwork.composetodo

import android.content.*
import app.softwork.composetodo.repository.*
import app.softwork.composetodo.viewmodels.*
import com.squareup.sqldelight.android.*
import com.squareup.sqldelight.db.*
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.http.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class Container(applicationContext: Context, private val scope: CoroutineScope) : AppContainer {
    override val driver: SqlDriver = AndroidSqliteDriver(ComposeTodoDB.Schema, applicationContext, "composetodo.db")

    override val client = HttpClient(Android) {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.todo.softwork.app"
            }
        }
    }

    override val isLoggedIn: MutableStateFlow<API> = MutableStateFlow(API.LoggedOut(client))

    override fun loginViewModel(api: API.LoggedOut) = LoginViewModel(scope, api = api) {
        isLoggedIn.value = it
    }

    override fun registerViewModel(api: API.LoggedOut) = RegisterViewModel(scope, api) {
        isLoggedIn.value = it
    }

    override fun todoViewModel(api: API.LoggedIn) = TodoViewModel(scope, TodoRepository(api = api, driver = driver))
}
