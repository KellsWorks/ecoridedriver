package app.ecoride_agent.network.responses.login

data class Login(
    val `data`: Data,
    val message: String,
    val success: Boolean
)