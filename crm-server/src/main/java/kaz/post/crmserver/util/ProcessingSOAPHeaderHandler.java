package kaz.post.crmserver.util;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

/**
 * Created by T.YELEMESOV on 26.10.2017.
 */
public class ProcessingSOAPHeaderHandler implements SOAPHandler<SOAPMessageContext> {
	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {

			try {
				//http://www.openwaygroup.com/wsint
				SOAPMessage message = context.getMessage();
				SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				String prefix = "wsin";
				if (header == null)
					header = envelope.addHeader();
				QName sessionContextStr = new QName("http://www.openwaygroup.com/wsint", "SessionContextStr");
				SOAPHeaderElement soapHeaderElementSes = header.addHeaderElement(sessionContextStr);
				soapHeaderElementSes.addTextNode("223");

				QName userInfo = new QName("http://www.openwaygroup.com/wsint", "UserInfo");
				SOAPHeaderElement soapHeaderElementUser = header.addHeaderElement(userInfo);
				soapHeaderElementUser.addTextNode("officer=\"wx_admin\"");

				QName correlation = new QName("http://www.openwaygroup.com/wsint", "CorrelationId");
				SOAPHeaderElement soapHeaderElementCor = header.addHeaderElement(correlation);
				soapHeaderElementCor.addTextNode("223");
				message.saveChanges();
				//message.writeTo(System.out);
			} catch (SOAPException e) {
				e.printStackTrace();
			} /*catch (IOException e) {
				e.printStackTrace();
			}*/

		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {

	}
}
