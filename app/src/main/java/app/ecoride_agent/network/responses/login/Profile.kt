package app.ecoride_agent.network.responses.login

data class Profile(
    val created_at: String,
    val emergency: String,
    val id: Int,
    val photo: String,
    val role: String,
    val updated_at: String,
    val user_id: Int
)