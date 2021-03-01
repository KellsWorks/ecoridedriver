package app.ecoride_agent.network.responses.login

data class Data(
    val email: String,
    val firebase: List<Firebase>,
    val name: String,
    val phone: String,
    val profile: List<Profile>,
    val token: String
)