package app.ecoride_agent.network.responses.register

data class Register(
        val `data`: Data,
        val message: String,
        val success: Boolean
)