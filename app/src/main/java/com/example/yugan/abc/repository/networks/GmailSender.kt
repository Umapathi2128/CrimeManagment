//package com.example.yugan.abc.repository.networks
//
//import java.io.ByteArrayInputStream
//import java.io.IOException
//import java.io.InputStream
//import java.io.OutputStream
//import java.security.Security
//import java.util.*
//import javax.activation.DataHandler
//import javax.activation.DataSource
//import javax.mail.Message
//import javax.mail.PasswordAuthentication
//import javax.mail.Session
//import javax.mail.Transport
//import javax.mail.internet.InternetAddress
//import javax.mail.internet.MimeMessage
//
//class GmailSender :javax.mail.Authenticator() {
//
//    package com.example.yugan.abc.repository.networks
//
//    import javax.mail.internet.InternetAddress
//    import javax.mail.Message.RecipientType.TO
//    import javax.mail.internet.MimeMessage
//    import javax.swing.UIManager.put
//    import java.security.Security.addProvider
//
//
//    private val mailhost = "smtp.gmail.com"
//    private lateinit var user: String
//    private lateinit var password: String
//    private lateinit var session: Session
//
//    static {
//        Security.addProvider( com . provider . JSSEProvider ());
//    }
//
//    fun GMailSender(user: String, password: String): ??? {
//        this.user = user
//        this.password = password
//
//        val props = Properties()
//        props.setProperty("mail.transport.protocol", "smtp")
//        props.setProperty("mail.host", mailhost)
//        props.put("mail.smtp.auth", "true")
//        props.put("mail.smtp.port", "465")
//        props.put("mail.smtp.socketFactory.port", "465")
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory")
//        props.put("mail.smtp.socketFactory.fallback", "false")
//        props.setProperty("mail.smtp.quitwait", "false")
//
//        session = Session.getDefaultInstance(props, this)
//    }
//
//    protected override fun getPasswordAuthentication(): PasswordAuthentication {
//        return PasswordAuthentication(user, password)
//    }
//
//    @Synchronized
//    @Throws(Exception::class)
//    fun sendMail(subject: String, body: String, sender: String, recipients: String) {
//        try {
//            val message = MimeMessage(session)
//            val handler = DataHandler(ByteArrayDataSource(body.toByteArray(), "text/plain"))
//            message.setSender(InternetAddress(sender))
//            message.setSubject(subject)
//            message.setDataHandler(handler)
//            if (recipients.indexOf(',') > 0)
//                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients))
//            else
//                message.setRecipient(Message.RecipientType.TO, InternetAddress(recipients))
//            Transport.send(message)
//        } catch (e: Exception) {
//
//        }
//
//    }
//
////    inner class ByteArrayDataSource : DataSource {
////        private var data: ByteArray? = null
////        private var type: String? = null
////
////        val contentType: String
////            get() = if (type == null)
////                "application/octet-stream"
////            else
////                type
////
////        val inputStream: InputS tream
////            @Throws(IOException::class)
////            get() = ByteArrayInputStream(data)
////
////        val name: String
////            get() = "ByteArrayDataSource"
////
////        val outputStream: OutputStream
////            @Throws(IOException::class)
////            get() = throw IOException("Not Supported")
////
////        constructor(data: ByteArray, type: String) : super() {
////            this.data = data
////            this.type = type
////        }
////
////        constructor(data: ByteArray) : super() {
////            this.data = data
////        }
////
////        fun setType(type: String) {
////            this.type = type
////        }
////    }
//}