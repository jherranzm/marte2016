package telefonica.aaee.marte.messages;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import telefonica.aaee.marte.model.InitMessage;

public class InitMessagePreparator implements MimeMessagePreparator {
	
	private InitMessage mensaje;
	
	
	

	public InitMessagePreparator(InitMessage mensaje) {
		super();
		this.mensaje = mensaje;
	}




	@Override
	public void prepare(MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		mimeMessageHelper.setTo(mensaje.getTo());
		mimeMessageHelper.setFrom(mensaje.getFrom());
		mimeMessageHelper.setSubject(mensaje.getSubject());
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta charset='UTF-8'>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div>").append("<h1>Aplicación funcionando!</h1>").append("</div>");
		sb.append("<div style='font-family:Arial;'>")
			.append("<p>").append(mensaje.getAhora()).append("</p>")
			.append("</div>");
		sb.append("<div style='font-family:Courier New;'>")
			.append("<ul>")
				.append("<li>Número de reinicios:").append(mensaje.getNumReinicios()).append("</li>")
				.append("<li>Número de acuerdos:").append(mensaje.getNumAcuerdos()).append("</li>")
			.append("</ul>")
			.append("</div>");
		sb.append("</body>");
		sb.append("</html>");
		
		mimeMessageHelper.setText(sb.toString(), true);
		

	}

}
